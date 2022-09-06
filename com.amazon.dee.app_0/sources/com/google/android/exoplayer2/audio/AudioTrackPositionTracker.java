package com.google.android.exoplayer2.audio;

import android.media.AudioTrack;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
final class AudioTrackPositionTracker {
    private static final long FORCE_RESET_WORKAROUND_TIMEOUT_MS = 200;
    private static final long MAX_AUDIO_TIMESTAMP_OFFSET_US = 5000000;
    private static final long MAX_LATENCY_US = 5000000;
    private static final int MAX_PLAYHEAD_OFFSET_COUNT = 10;
    private static final int MIN_LATENCY_SAMPLE_INTERVAL_US = 500000;
    private static final int MIN_PLAYHEAD_OFFSET_SAMPLE_INTERVAL_US = 30000;
    private static final long MODE_SWITCH_SMOOTHING_DURATION_US = 1000000;
    private static final int PLAYSTATE_PAUSED = 2;
    private static final int PLAYSTATE_PLAYING = 3;
    private static final int PLAYSTATE_STOPPED = 1;
    private static final String TAG = "AudioTrackPositionTracker";
    private boolean applyDolbyPassThroughQuirk;
    @Nullable
    private AudioTimestampPoller audioTimestampPoller;
    @Nullable
    private AudioTrack audioTrack;
    private float audioTrackPlaybackSpeed;
    private int bufferSize;
    private long bufferSizeUs;
    private long endPlaybackHeadPosition;
    private long forceResetWorkaroundTimeMs;
    @Nullable
    private Method getLatencyMethod;
    private boolean hasData;
    private boolean isLatencyQuirkEnabled;
    private boolean isOutputPcm;
    private long lastLatencySampleTimeUs;
    private long lastPlayheadSampleTimeUs;
    private long lastPositionUs;
    private long lastRawPlaybackHeadPosition;
    private boolean lastSampleUsedGetTimestampMode;
    private long lastSystemTimeUs;
    private long latencyUs;
    private final Listener listener;
    private boolean needsPassthroughWorkarounds;
    private int nextPlayheadOffsetIndex;
    private boolean notifiedPositionIncreasing;
    private int outputPcmFrameSize;
    private int outputSampleRate;
    private long passthroughWorkaroundPauseOffset;
    private int playheadOffsetCount;
    private final long[] playheadOffsets;
    private long previousModePositionUs;
    private long previousModeSystemTimeUs;
    private long rawPlaybackHeadWrapCount;
    private long resumeSystemTimeUs;
    private long smoothedPlayheadOffsetUs;
    private long stopPlaybackHeadPosition;
    private long stopTimestampUs;
    private final Logger log = new Logger(Logger.Module.Audio, TAG);
    private final boolean DBG = this.log.allowDebug();
    private final boolean VDBG = this.log.allowVerbose();

    /* loaded from: classes2.dex */
    public interface Listener {
        void onInvalidLatency(long j);

        void onPositionAdvancing(long j);

        void onPositionFramesMismatch(long j, long j2, long j3, long j4);

        void onSystemTimeUsMismatch(long j, long j2, long j3, long j4);

        void onUnderrun(int i, long j);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    private @interface PlayState {
    }

    public AudioTrackPositionTracker(Listener listener, boolean z) {
        this.listener = (Listener) Assertions.checkNotNull(listener);
        if (Util.SDK_INT >= 18) {
            try {
                this.getLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
            } catch (Throwable unused) {
            }
        }
        this.playheadOffsets = new long[10];
        this.isLatencyQuirkEnabled = z;
    }

    private boolean forceHasPendingData() {
        if (this.needsPassthroughWorkarounds && ((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState() == 2 && getPlaybackHeadPosition() == 0) {
            return true;
        }
        return AmazonQuirks.isLatencyQuirkEnabled() && ((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState() == 3 && (System.nanoTime() / 1000) - this.resumeSystemTimeUs < 1000000;
    }

    private long framesToDurationUs(long j) {
        return (j * 1000000) / this.outputSampleRate;
    }

    private int getAudioSWLatencies() {
        Method method = this.getLatencyMethod;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(this.audioTrack, null)).intValue() * (this.outputSampleRate / 1000);
        } catch (Exception unused) {
            return 0;
        }
    }

    private long getPlaybackHeadPosition() {
        long playbackHeadPosition;
        AudioTrack audioTrack = (AudioTrack) Assertions.checkNotNull(this.audioTrack);
        if (this.stopTimestampUs != C.TIME_UNSET) {
            return Math.min(this.endPlaybackHeadPosition, this.stopPlaybackHeadPosition + ((((SystemClock.elapsedRealtime() * 1000) - this.stopTimestampUs) * this.outputSampleRate) / 1000000));
        }
        int playState = audioTrack.getPlayState();
        if (playState == 1) {
            return 0L;
        }
        if (this.isLatencyQuirkEnabled) {
            int playbackHeadPosition2 = audioTrack.getPlaybackHeadPosition();
            if (this.VDBG) {
                this.log.v("php = " + playbackHeadPosition2);
            }
            int playState2 = audioTrack.getPlayState();
            if (playState2 == 3 || (playState2 == 2 && playbackHeadPosition2 != 0)) {
                playbackHeadPosition2 += getAudioSWLatencies();
            }
            if (playbackHeadPosition2 < 0 && (System.nanoTime() / 1000) - this.resumeSystemTimeUs < 1000000) {
                playbackHeadPosition2 = 0;
                this.log.i("php is negative during latency stabilization phase ...resetting to 0");
            }
            playbackHeadPosition = playbackHeadPosition2 & BodyPartID.bodyIdMax;
        } else {
            playbackHeadPosition = audioTrack.getPlaybackHeadPosition() & BodyPartID.bodyIdMax;
            if (this.VDBG) {
                this.log.v("rawPlaybackHeadPosition = " + playbackHeadPosition);
            }
            if (this.needsPassthroughWorkarounds) {
                if (playState == 2 && playbackHeadPosition == 0) {
                    this.passthroughWorkaroundPauseOffset = this.lastRawPlaybackHeadPosition;
                }
                playbackHeadPosition += this.passthroughWorkaroundPauseOffset;
            }
        }
        if (Util.SDK_INT <= 29) {
            if (playbackHeadPosition == 0 && this.lastRawPlaybackHeadPosition > 0 && playState == 3) {
                if (this.forceResetWorkaroundTimeMs == C.TIME_UNSET) {
                    this.forceResetWorkaroundTimeMs = SystemClock.elapsedRealtime();
                }
                return this.lastRawPlaybackHeadPosition;
            }
            this.forceResetWorkaroundTimeMs = C.TIME_UNSET;
        }
        long j = this.lastRawPlaybackHeadPosition;
        if (j > playbackHeadPosition && j > 2147483647L && j - playbackHeadPosition >= 2147483647L) {
            this.log.i("The playback head position wrapped around");
            this.rawPlaybackHeadWrapCount++;
        }
        this.lastRawPlaybackHeadPosition = playbackHeadPosition;
        return playbackHeadPosition + (this.rawPlaybackHeadWrapCount << 32);
    }

    private long getPlaybackHeadPositionUs() {
        return framesToDurationUs(getPlaybackHeadPosition());
    }

    private void maybePollAndCheckTimestamp(long j, long j2) {
        AudioTimestampPoller audioTimestampPoller = (AudioTimestampPoller) Assertions.checkNotNull(this.audioTimestampPoller);
        if (!audioTimestampPoller.maybePollTimestamp(j)) {
            return;
        }
        long timestampSystemTimeUs = audioTimestampPoller.getTimestampSystemTimeUs();
        long timestampPositionFrames = audioTimestampPoller.getTimestampPositionFrames();
        if (Math.abs(timestampSystemTimeUs - j) > 5000000) {
            this.listener.onSystemTimeUsMismatch(timestampPositionFrames, timestampSystemTimeUs, j, j2);
            audioTimestampPoller.rejectTimestamp();
        } else if (Math.abs(framesToDurationUs(timestampPositionFrames) - j2) > 5000000) {
            this.listener.onPositionFramesMismatch(timestampPositionFrames, timestampSystemTimeUs, j, j2);
            audioTimestampPoller.rejectTimestamp();
        } else {
            audioTimestampPoller.acceptTimestamp();
        }
    }

    private void maybeSampleSyncParams() {
        long playbackHeadPositionUs = getPlaybackHeadPositionUs();
        if (playbackHeadPositionUs == 0) {
            return;
        }
        long nanoTime = System.nanoTime() / 1000;
        if (nanoTime - this.lastPlayheadSampleTimeUs >= 30000) {
            long[] jArr = this.playheadOffsets;
            int i = this.nextPlayheadOffsetIndex;
            jArr[i] = playbackHeadPositionUs - nanoTime;
            this.nextPlayheadOffsetIndex = (i + 1) % 10;
            int i2 = this.playheadOffsetCount;
            if (i2 < 10) {
                this.playheadOffsetCount = i2 + 1;
            }
            this.lastPlayheadSampleTimeUs = nanoTime;
            this.smoothedPlayheadOffsetUs = 0L;
            int i3 = 0;
            while (true) {
                int i4 = this.playheadOffsetCount;
                if (i3 >= i4) {
                    break;
                }
                this.smoothedPlayheadOffsetUs = (this.playheadOffsets[i3] / i4) + this.smoothedPlayheadOffsetUs;
                i3++;
            }
        }
        if (this.needsPassthroughWorkarounds) {
            return;
        }
        maybePollAndCheckTimestamp(nanoTime, playbackHeadPositionUs);
        maybeUpdateLatency(nanoTime);
    }

    private void maybeUpdateLatency(long j) {
        Method method;
        if (this.isLatencyQuirkEnabled) {
            this.latencyUs = AmazonQuirks.getAudioHWLatency();
        } else if (!this.isOutputPcm || (method = this.getLatencyMethod) == null || j - this.lastLatencySampleTimeUs < 500000) {
        } else {
            try {
                this.latencyUs = (((Integer) Util.castNonNull((Integer) method.invoke(Assertions.checkNotNull(this.audioTrack), new Object[0]))).intValue() * 1000) - this.bufferSizeUs;
                this.latencyUs = Math.max(this.latencyUs, 0L);
                if (this.latencyUs > 5000000) {
                    this.listener.onInvalidLatency(this.latencyUs);
                    this.latencyUs = 0L;
                }
            } catch (Exception unused) {
                this.getLatencyMethod = null;
            }
            this.lastLatencySampleTimeUs = j;
        }
    }

    private static boolean needsPassthroughWorkarounds(int i) {
        return Util.SDK_INT < 23 && (i == 5 || i == 6);
    }

    private void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0L;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0L;
        this.lastSystemTimeUs = 0L;
        this.previousModeSystemTimeUs = 0L;
        this.notifiedPositionIncreasing = false;
    }

    public int getAvailableBufferSize(long j) {
        return this.bufferSize - ((int) (j - (getPlaybackHeadPosition() * this.outputPcmFrameSize)));
    }

    public long getCurrentPositionUs(boolean z) {
        long j;
        boolean z2;
        if (((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState() == 3 && !this.applyDolbyPassThroughQuirk) {
            maybeSampleSyncParams();
        }
        long nanoTime = System.nanoTime() / 1000;
        AudioTimestampPoller audioTimestampPoller = (AudioTimestampPoller) Assertions.checkNotNull(this.audioTimestampPoller);
        boolean hasAdvancingTimestamp = audioTimestampPoller.hasAdvancingTimestamp();
        long j2 = 0;
        if (this.applyDolbyPassThroughQuirk) {
            if (audioTimestampPoller.maybePollTimestamp(nanoTime, true)) {
                j2 = audioTimestampPoller.getTimestampSystemTimeUs();
            }
            if (this.VDBG) {
                Logger logger = this.log;
                logger.v("getCurrentPositionUs : applyDolbyPassThroughQuirk positionUs = " + j2);
            }
            return j2;
        } else if (hasAdvancingTimestamp) {
            long timestampPositionFrames = audioTimestampPoller.getTimestampPositionFrames();
            long framesToDurationUs = framesToDurationUs(timestampPositionFrames);
            long mediaDurationForPlayoutDuration = Util.getMediaDurationForPlayoutDuration(nanoTime - audioTimestampPoller.getTimestampSystemTimeUs(), this.audioTrackPlaybackSpeed);
            long j3 = framesToDurationUs + mediaDurationForPlayoutDuration;
            long timestampSystemTimeUs = audioTimestampPoller.getTimestampSystemTimeUs();
            if (this.VDBG) {
                Logger logger2 = this.log;
                z2 = hasAdvancingTimestamp;
                StringBuilder outline111 = GeneratedOutlineSupport1.outline111("getCurrentPositionUs : hasTimestamp: positionUs = ", j3, " timestampPositionFrames = ");
                outline111.append(timestampPositionFrames);
                outline111.append(" timestampPositionUs = ");
                outline111.append(framesToDurationUs);
                outline111.append(" elapsedSinceTimestampUs = ");
                outline111.append(mediaDurationForPlayoutDuration);
                outline111.append(" systemTimeUs = ");
                outline111.append(nanoTime);
                outline111.append(" timestampSysTimeUs  = ");
                outline111.append(timestampSystemTimeUs);
                logger2.v(outline111.toString());
            } else {
                z2 = hasAdvancingTimestamp;
            }
            boolean z3 = z2;
            if (this.lastSampleUsedGetTimestampMode != z3) {
                this.previousModeSystemTimeUs = this.lastSystemTimeUs;
                this.previousModePositionUs = this.lastPositionUs;
            }
            long j4 = nanoTime - this.previousModeSystemTimeUs;
            if (j4 < 1000000) {
                long j5 = (j4 * 1000) / 1000000;
                long j6 = j3 * j5;
                long j7 = 1000 - j5;
                j3 = ((j7 * (Util.getMediaDurationForPlayoutDuration(j4, this.audioTrackPlaybackSpeed) + this.previousModePositionUs)) + j6) / 1000;
            }
            if (!this.notifiedPositionIncreasing) {
                long j8 = this.lastPositionUs;
                if (j3 > j8) {
                    this.notifiedPositionIncreasing = true;
                    long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(C.usToMs(j3 - j8), this.audioTrackPlaybackSpeed);
                    this.listener.onPositionAdvancing(System.currentTimeMillis() - C.usToMs(playoutDurationForMediaDuration));
                }
            }
            this.lastSystemTimeUs = nanoTime;
            this.lastPositionUs = j3;
            this.lastSampleUsedGetTimestampMode = z3;
            return j3;
        } else {
            if (this.playheadOffsetCount == 0) {
                j = getPlaybackHeadPositionUs();
                if (this.VDBG) {
                    Logger logger3 = this.log;
                    logger3.v("getCurrentPositionUs : pre-latency adjustment positionUs = " + j);
                }
            } else {
                long j9 = this.smoothedPlayheadOffsetUs + nanoTime;
                if (this.VDBG) {
                    Logger logger4 = this.log;
                    StringBuilder outline1112 = GeneratedOutlineSupport1.outline111("getCurrentPositionUs : pre-latency adjustment positionUs = ", j9, " smoothedPlayheadOffsetUs = ");
                    outline1112.append(this.smoothedPlayheadOffsetUs);
                    outline1112.append(" systemTimeUs = ");
                    outline1112.append(nanoTime);
                    logger4.v(outline1112.toString());
                }
                j = j9;
            }
            if (!z) {
                j = Math.max(0L, j - this.latencyUs);
            }
            if (this.VDBG) {
                Logger logger5 = this.log;
                StringBuilder outline1113 = GeneratedOutlineSupport1.outline111("getCurrentPositionUs : post-latency adjustment positionUs = ", j, " latencyUs = ");
                outline1113.append(this.latencyUs);
                logger5.v(outline1113.toString());
            }
            return j;
        }
    }

    public long getPendingBufferDurationMs(long j) {
        return C.usToMs(framesToDurationUs(j - getPlaybackHeadPosition()));
    }

    public void handleEndOfStream(long j) {
        this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
        this.stopTimestampUs = SystemClock.elapsedRealtime() * 1000;
        this.endPlaybackHeadPosition = j;
    }

    public boolean hasPendingData(long j) {
        boolean z = this.applyDolbyPassThroughQuirk || j > getPlaybackHeadPosition() || forceHasPendingData();
        if (this.VDBG) {
            Logger logger = this.log;
            logger.v("hasPendingData = " + z);
        }
        return z;
    }

    public boolean isPlaying() {
        return ((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState() == 3;
    }

    public boolean isStalled(long j) {
        return this.forceResetWorkaroundTimeMs != C.TIME_UNSET && j > 0 && SystemClock.elapsedRealtime() - this.forceResetWorkaroundTimeMs >= FORCE_RESET_WORKAROUND_TIMEOUT_MS;
    }

    public boolean mayHandleBuffer(long j) {
        int playState = ((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState();
        if (this.needsPassthroughWorkarounds && !this.applyDolbyPassThroughQuirk) {
            if (playState == 2) {
                this.hasData = false;
                return false;
            } else if (playState == 1 && getPlaybackHeadPosition() != 0) {
                return false;
            }
        }
        boolean z = this.hasData;
        this.hasData = hasPendingData(j);
        if (z && !this.hasData && playState != 1) {
            this.listener.onUnderrun(this.bufferSize, C.usToMs(this.bufferSizeUs));
        }
        return true;
    }

    public boolean pause() {
        if (this.DBG) {
            this.log.d("pause");
        }
        resetSyncParams();
        if (this.stopTimestampUs == C.TIME_UNSET) {
            ((AudioTimestampPoller) Assertions.checkNotNull(this.audioTimestampPoller)).reset();
            return true;
        }
        return false;
    }

    public void reset() {
        if (this.DBG) {
            this.log.d("reset");
        }
        resetSyncParams();
        this.audioTrack = null;
        this.audioTimestampPoller = null;
    }

    public void setAudioTrack(AudioTrack audioTrack, boolean z, int i, int i2, int i3, boolean z2) {
        this.audioTrack = audioTrack;
        this.outputPcmFrameSize = i2;
        this.bufferSize = i3;
        this.applyDolbyPassThroughQuirk = z2;
        this.audioTimestampPoller = new AudioTimestampPoller(audioTrack);
        this.outputSampleRate = audioTrack.getSampleRate();
        this.needsPassthroughWorkarounds = z && needsPassthroughWorkarounds(i);
        this.isOutputPcm = Util.isEncodingLinearPcm(i);
        this.bufferSizeUs = this.isOutputPcm ? framesToDurationUs(i3 / i2) : -9223372036854775807L;
        this.lastRawPlaybackHeadPosition = 0L;
        this.rawPlaybackHeadWrapCount = 0L;
        this.passthroughWorkaroundPauseOffset = 0L;
        this.hasData = false;
        this.stopTimestampUs = C.TIME_UNSET;
        this.forceResetWorkaroundTimeMs = C.TIME_UNSET;
        this.lastLatencySampleTimeUs = 0L;
        this.latencyUs = 0L;
        this.audioTrackPlaybackSpeed = 1.0f;
    }

    public void setAudioTrackPlaybackSpeed(float f) {
        this.audioTrackPlaybackSpeed = f;
        AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
        if (audioTimestampPoller != null) {
            audioTimestampPoller.reset();
        }
    }

    public void start() {
        if (this.DBG) {
            this.log.d("start");
        }
        ((AudioTimestampPoller) Assertions.checkNotNull(this.audioTimestampPoller)).reset();
        this.resumeSystemTimeUs = System.nanoTime() / 1000;
    }
}
