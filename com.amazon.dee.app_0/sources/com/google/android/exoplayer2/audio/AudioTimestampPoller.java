package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
final class AudioTimestampPoller {
    private static final int ERROR_POLL_INTERVAL_US = 500000;
    private static final int FAST_POLL_INTERVAL_US = 10000;
    private static final int INITIALIZING_DURATION_US = 500000;
    private static final int SLOW_POLL_INTERVAL_US = 10000000;
    private static final int STATE_ERROR = 4;
    private static final int STATE_INITIALIZING = 0;
    private static final int STATE_NO_TIMESTAMP = 3;
    private static final int STATE_TIMESTAMP = 1;
    private static final int STATE_TIMESTAMP_ADVANCING = 2;
    @Nullable
    private final AudioTimestampV19 audioTimestamp;
    private long initialTimestampPositionFrames;
    private long initializeSystemTimeUs;
    private long lastTimestampSampleTimeUs;
    private long sampleIntervalUs;
    private int state;

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(19)
    /* loaded from: classes2.dex */
    public static final class AudioTimestampV19 {
        private final AudioTimestamp audioTimestamp = new AudioTimestamp();
        private final AudioTrack audioTrack;
        private long lastTimestampPositionFrames;
        private long lastTimestampRawPositionFrames;
        private long rawTimestampFramePositionWrapCount;

        public AudioTimestampV19(AudioTrack audioTrack) {
            this.audioTrack = audioTrack;
        }

        public long getTimestampPositionFrames() {
            return this.lastTimestampPositionFrames;
        }

        public long getTimestampSystemTimeUs() {
            return this.audioTimestamp.nanoTime / 1000;
        }

        public boolean maybeUpdateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j = this.audioTimestamp.framePosition;
                if (this.lastTimestampRawPositionFrames > j) {
                    this.rawTimestampFramePositionWrapCount++;
                }
                this.lastTimestampRawPositionFrames = j;
                this.lastTimestampPositionFrames = j + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    private @interface State {
    }

    public AudioTimestampPoller(AudioTrack audioTrack) {
        if (Util.SDK_INT >= 19) {
            this.audioTimestamp = new AudioTimestampV19(audioTrack);
            reset();
            return;
        }
        this.audioTimestamp = null;
        updateState(3);
    }

    private void updateState(int i) {
        this.state = i;
        if (i == 0) {
            this.lastTimestampSampleTimeUs = 0L;
            this.initialTimestampPositionFrames = -1L;
            this.initializeSystemTimeUs = System.nanoTime() / 1000;
            this.sampleIntervalUs = 10000L;
        } else if (i == 1) {
            this.sampleIntervalUs = 10000L;
        } else if (i == 2 || i == 3) {
            this.sampleIntervalUs = 10000000L;
        } else if (i == 4) {
            this.sampleIntervalUs = 500000L;
        } else {
            throw new IllegalStateException();
        }
    }

    public void acceptTimestamp() {
        if (this.state == 4) {
            reset();
        }
    }

    @TargetApi(19)
    public long getTimestampPositionFrames() {
        AudioTimestampV19 audioTimestampV19 = this.audioTimestamp;
        if (audioTimestampV19 != null) {
            return audioTimestampV19.getTimestampPositionFrames();
        }
        return -1L;
    }

    @TargetApi(19)
    public long getTimestampSystemTimeUs() {
        AudioTimestampV19 audioTimestampV19 = this.audioTimestamp;
        return audioTimestampV19 != null ? audioTimestampV19.getTimestampSystemTimeUs() : C.TIME_UNSET;
    }

    public boolean hasAdvancingTimestamp() {
        return this.state == 2;
    }

    public boolean hasTimestamp() {
        int i = this.state;
        return i == 1 || i == 2;
    }

    public boolean maybePollTimestamp(long j) {
        return maybePollTimestamp(j, false);
    }

    public void rejectTimestamp() {
        updateState(4);
    }

    public void reset() {
        if (this.audioTimestamp != null) {
            updateState(0);
        }
    }

    @TargetApi(19)
    public boolean maybePollTimestamp(long j, boolean z) {
        if (z || (this.audioTimestamp != null && j - this.lastTimestampSampleTimeUs >= this.sampleIntervalUs)) {
            this.lastTimestampSampleTimeUs = j;
            boolean maybeUpdateTimestamp = this.audioTimestamp.maybeUpdateTimestamp();
            int i = this.state;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                throw new IllegalStateException();
                            }
                        } else if (maybeUpdateTimestamp) {
                            reset();
                        }
                    } else if (!maybeUpdateTimestamp) {
                        reset();
                    }
                } else if (maybeUpdateTimestamp) {
                    if (this.audioTimestamp.getTimestampPositionFrames() > this.initialTimestampPositionFrames) {
                        updateState(2);
                    }
                } else {
                    reset();
                }
            } else if (maybeUpdateTimestamp) {
                if (this.audioTimestamp.getTimestampSystemTimeUs() < this.initializeSystemTimeUs && !z) {
                    return false;
                }
                this.initialTimestampPositionFrames = this.audioTimestamp.getTimestampPositionFrames();
                updateState(1);
            } else if (j - this.initializeSystemTimeUs > 500000) {
                updateState(3);
            }
            return maybeUpdateTimestamp;
        }
        return false;
    }
}
