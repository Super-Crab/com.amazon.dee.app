package com.google.android.exoplayer2.analytics;

import android.os.SystemClock;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.analytics.PlaybackSessionManager;
import com.google.android.exoplayer2.analytics.PlaybackStats;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public final class PlaybackStatsListener implements AnalyticsListener, PlaybackSessionManager.Listener {
    @Nullable
    private String activeAdPlayback;
    @Nullable
    private String activeContentPlayback;
    @Nullable
    Format audioFormat;
    long bandwidthBytes;
    long bandwidthTimeMs;
    @Nullable
    private final Callback callback;
    int discontinuityReason;
    int droppedFrames;
    private final boolean keepHistory;
    @Nullable
    Exception nonFatalException;
    @Nullable
    private AnalyticsListener.EventTime onSeekStartedEventTime;
    @Nullable
    Format videoFormat;
    int videoHeight;
    int videoWidth;
    private final PlaybackSessionManager sessionManager = new DefaultPlaybackSessionManager();
    private final Map<String, PlaybackStatsTracker> playbackStatsTrackers = new HashMap();
    private final Map<String, AnalyticsListener.EventTime> sessionStartEventTimes = new HashMap();
    private PlaybackStats finishedPlaybackStats = PlaybackStats.EMPTY;
    private final Timeline.Period period = new Timeline.Period();

    /* loaded from: classes2.dex */
    public interface Callback {
        void onPlaybackStatsReady(AnalyticsListener.EventTime eventTime, PlaybackStats playbackStats);
    }

    /* loaded from: classes2.dex */
    private static final class PlaybackStatsTracker {
        private long audioFormatBitrateTimeProduct;
        private final List<PlaybackStats.EventTimeAndFormat> audioFormatHistory;
        private long audioFormatTimeMs;
        private long audioUnderruns;
        private long bandwidthBytes;
        private long bandwidthTimeMs;
        @Nullable
        private Format currentAudioFormat;
        private float currentPlaybackSpeed;
        private int currentPlaybackState;
        private long currentPlaybackStateStartTimeMs;
        @Nullable
        private Format currentVideoFormat;
        private long droppedFrames;
        private int fatalErrorCount;
        private final List<PlaybackStats.EventTimeAndException> fatalErrorHistory;
        private long firstReportedTimeMs;
        private boolean hasBeenReady;
        private boolean hasEnded;
        private boolean hasFatalError;
        private long initialAudioFormatBitrate;
        private long initialVideoFormatBitrate;
        private int initialVideoFormatHeight;
        private final boolean isAd;
        private boolean isForeground;
        private boolean isInterruptedByAd;
        private boolean isJoinTimeInvalid;
        private boolean isSeeking;
        private final boolean keepHistory;
        private long lastAudioFormatStartTimeMs;
        private long lastRebufferStartTimeMs;
        private long lastVideoFormatStartTimeMs;
        private long maxRebufferTimeMs;
        private final List<long[]> mediaTimeHistory;
        private int nonFatalErrorCount;
        private final List<PlaybackStats.EventTimeAndException> nonFatalErrorHistory;
        private int pauseBufferCount;
        private int pauseCount;
        private final long[] playbackStateDurationsMs = new long[16];
        private final List<PlaybackStats.EventTimeAndPlaybackState> playbackStateHistory;
        private int rebufferCount;
        private int seekCount;
        private boolean startedLoading;
        private long videoFormatBitrateTimeMs;
        private long videoFormatBitrateTimeProduct;
        private long videoFormatHeightTimeMs;
        private long videoFormatHeightTimeProduct;
        private final List<PlaybackStats.EventTimeAndFormat> videoFormatHistory;

        public PlaybackStatsTracker(boolean z, AnalyticsListener.EventTime eventTime) {
            this.keepHistory = z;
            this.playbackStateHistory = z ? new ArrayList<>() : Collections.emptyList();
            this.mediaTimeHistory = z ? new ArrayList<>() : Collections.emptyList();
            this.videoFormatHistory = z ? new ArrayList<>() : Collections.emptyList();
            this.audioFormatHistory = z ? new ArrayList<>() : Collections.emptyList();
            this.fatalErrorHistory = z ? new ArrayList<>() : Collections.emptyList();
            this.nonFatalErrorHistory = z ? new ArrayList<>() : Collections.emptyList();
            boolean z2 = false;
            this.currentPlaybackState = 0;
            this.currentPlaybackStateStartTimeMs = eventTime.realtimeMs;
            this.firstReportedTimeMs = C.TIME_UNSET;
            this.maxRebufferTimeMs = C.TIME_UNSET;
            MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
            if (mediaPeriodId != null && mediaPeriodId.isAd()) {
                z2 = true;
            }
            this.isAd = z2;
            this.initialAudioFormatBitrate = -1L;
            this.initialVideoFormatBitrate = -1L;
            this.initialVideoFormatHeight = -1;
            this.currentPlaybackSpeed = 1.0f;
        }

        private long[] guessMediaTimeBasedOnElapsedRealtime(long j) {
            long[] jArr = (long[]) GeneratedOutlineSupport1.outline25(this.mediaTimeHistory, 1);
            return new long[]{j, jArr[1] + (((float) (j - jArr[0])) * this.currentPlaybackSpeed)};
        }

        private static boolean isInvalidJoinTransition(int i, int i2) {
            return ((i != 1 && i != 2 && i != 14) || i2 == 1 || i2 == 2 || i2 == 14 || i2 == 3 || i2 == 4 || i2 == 9 || i2 == 11) ? false : true;
        }

        private static boolean isPausedState(int i) {
            return i == 4 || i == 7;
        }

        private static boolean isReadyState(int i) {
            return i == 3 || i == 4 || i == 9;
        }

        private static boolean isRebufferingState(int i) {
            return i == 6 || i == 7 || i == 10;
        }

        private void maybeRecordAudioFormatTime(long j) {
            Format format;
            int i;
            if (this.currentPlaybackState == 3 && (format = this.currentAudioFormat) != null && (i = format.bitrate) != -1) {
                long j2 = ((float) (j - this.lastAudioFormatStartTimeMs)) * this.currentPlaybackSpeed;
                this.audioFormatTimeMs += j2;
                this.audioFormatBitrateTimeProduct = (j2 * i) + this.audioFormatBitrateTimeProduct;
            }
            this.lastAudioFormatStartTimeMs = j;
        }

        private void maybeRecordVideoFormatTime(long j) {
            Format format;
            if (this.currentPlaybackState == 3 && (format = this.currentVideoFormat) != null) {
                long j2 = ((float) (j - this.lastVideoFormatStartTimeMs)) * this.currentPlaybackSpeed;
                int i = format.height;
                if (i != -1) {
                    this.videoFormatHeightTimeMs += j2;
                    this.videoFormatHeightTimeProduct = (i * j2) + this.videoFormatHeightTimeProduct;
                }
                int i2 = this.currentVideoFormat.bitrate;
                if (i2 != -1) {
                    this.videoFormatBitrateTimeMs += j2;
                    this.videoFormatBitrateTimeProduct = (j2 * i2) + this.videoFormatBitrateTimeProduct;
                }
            }
            this.lastVideoFormatStartTimeMs = j;
        }

        private void maybeUpdateAudioFormat(AnalyticsListener.EventTime eventTime, @Nullable Format format) {
            int i;
            if (Util.areEqual(this.currentAudioFormat, format)) {
                return;
            }
            maybeRecordAudioFormatTime(eventTime.realtimeMs);
            if (format != null && this.initialAudioFormatBitrate == -1 && (i = format.bitrate) != -1) {
                this.initialAudioFormatBitrate = i;
            }
            this.currentAudioFormat = format;
            if (!this.keepHistory) {
                return;
            }
            this.audioFormatHistory.add(new PlaybackStats.EventTimeAndFormat(eventTime, this.currentAudioFormat));
        }

        private void maybeUpdateMaxRebufferTimeMs(long j) {
            if (isRebufferingState(this.currentPlaybackState)) {
                long j2 = j - this.lastRebufferStartTimeMs;
                long j3 = this.maxRebufferTimeMs;
                if (j3 != C.TIME_UNSET && j2 <= j3) {
                    return;
                }
                this.maxRebufferTimeMs = j2;
            }
        }

        private void maybeUpdateMediaTimeHistory(long j, long j2) {
            if (!this.keepHistory) {
                return;
            }
            if (this.currentPlaybackState != 3) {
                if (j2 == C.TIME_UNSET) {
                    return;
                }
                if (!this.mediaTimeHistory.isEmpty()) {
                    long j3 = ((long[]) GeneratedOutlineSupport1.outline25(this.mediaTimeHistory, 1))[1];
                    if (j3 != j2) {
                        this.mediaTimeHistory.add(new long[]{j, j3});
                    }
                }
            }
            this.mediaTimeHistory.add(j2 == C.TIME_UNSET ? guessMediaTimeBasedOnElapsedRealtime(j) : new long[]{j, j2});
        }

        private void maybeUpdateVideoFormat(AnalyticsListener.EventTime eventTime, @Nullable Format format) {
            int i;
            int i2;
            if (Util.areEqual(this.currentVideoFormat, format)) {
                return;
            }
            maybeRecordVideoFormatTime(eventTime.realtimeMs);
            if (format != null) {
                if (this.initialVideoFormatHeight == -1 && (i2 = format.height) != -1) {
                    this.initialVideoFormatHeight = i2;
                }
                if (this.initialVideoFormatBitrate == -1 && (i = format.bitrate) != -1) {
                    this.initialVideoFormatBitrate = i;
                }
            }
            this.currentVideoFormat = format;
            if (!this.keepHistory) {
                return;
            }
            this.videoFormatHistory.add(new PlaybackStats.EventTimeAndFormat(eventTime, this.currentVideoFormat));
        }

        private int resolveNewPlaybackState(Player player) {
            int playbackState = player.getPlaybackState();
            if (!this.isSeeking || !this.isForeground) {
                if (this.hasFatalError) {
                    return 13;
                }
                if (!this.isForeground) {
                    return this.startedLoading ? 1 : 0;
                }
                if (this.isInterruptedByAd) {
                    return 14;
                }
                if (playbackState == 4) {
                    return 11;
                }
                if (playbackState != 2) {
                    if (playbackState == 3) {
                        if (!player.getPlayWhenReady()) {
                            return 4;
                        }
                        return player.getPlaybackSuppressionReason() != 0 ? 9 : 3;
                    } else if (playbackState == 1 && this.currentPlaybackState != 0) {
                        return 12;
                    } else {
                        return this.currentPlaybackState;
                    }
                }
                int i = this.currentPlaybackState;
                if (i == 0 || i == 1 || i == 2 || i == 14) {
                    return 2;
                }
                if (!player.getPlayWhenReady()) {
                    return 7;
                }
                return player.getPlaybackSuppressionReason() != 0 ? 10 : 6;
            }
            return 5;
        }

        private void updatePlaybackState(int i, AnalyticsListener.EventTime eventTime) {
            boolean z = false;
            Assertions.checkArgument(eventTime.realtimeMs >= this.currentPlaybackStateStartTimeMs);
            long j = eventTime.realtimeMs;
            long[] jArr = this.playbackStateDurationsMs;
            int i2 = this.currentPlaybackState;
            jArr[i2] = jArr[i2] + (j - this.currentPlaybackStateStartTimeMs);
            if (this.firstReportedTimeMs == C.TIME_UNSET) {
                this.firstReportedTimeMs = j;
            }
            this.isJoinTimeInvalid |= isInvalidJoinTransition(this.currentPlaybackState, i);
            this.hasBeenReady |= isReadyState(i);
            boolean z2 = this.hasEnded;
            if (i == 11) {
                z = true;
            }
            this.hasEnded = z2 | z;
            if (!isPausedState(this.currentPlaybackState) && isPausedState(i)) {
                this.pauseCount++;
            }
            if (i == 5) {
                this.seekCount++;
            }
            if (!isRebufferingState(this.currentPlaybackState) && isRebufferingState(i)) {
                this.rebufferCount++;
                this.lastRebufferStartTimeMs = eventTime.realtimeMs;
            }
            if (isRebufferingState(this.currentPlaybackState) && this.currentPlaybackState != 7 && i == 7) {
                this.pauseBufferCount++;
            }
            maybeUpdateMaxRebufferTimeMs(eventTime.realtimeMs);
            this.currentPlaybackState = i;
            this.currentPlaybackStateStartTimeMs = eventTime.realtimeMs;
            if (this.keepHistory) {
                this.playbackStateHistory.add(new PlaybackStats.EventTimeAndPlaybackState(eventTime, this.currentPlaybackState));
            }
        }

        public PlaybackStats build(boolean z) {
            long[] jArr;
            ArrayList arrayList;
            long[] jArr2 = this.playbackStateDurationsMs;
            List<long[]> list = this.mediaTimeHistory;
            if (!z) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                long[] copyOf = Arrays.copyOf(this.playbackStateDurationsMs, 16);
                long max = Math.max(0L, elapsedRealtime - this.currentPlaybackStateStartTimeMs);
                int i = this.currentPlaybackState;
                copyOf[i] = copyOf[i] + max;
                maybeUpdateMaxRebufferTimeMs(elapsedRealtime);
                maybeRecordVideoFormatTime(elapsedRealtime);
                maybeRecordAudioFormatTime(elapsedRealtime);
                ArrayList arrayList2 = new ArrayList(this.mediaTimeHistory);
                if (this.keepHistory && this.currentPlaybackState == 3) {
                    arrayList2.add(guessMediaTimeBasedOnElapsedRealtime(elapsedRealtime));
                }
                jArr = copyOf;
                arrayList = arrayList2;
            } else {
                jArr = jArr2;
                arrayList = list;
            }
            int i2 = (this.isJoinTimeInvalid || !this.hasBeenReady) ? 1 : 0;
            long j = i2 != 0 ? C.TIME_UNSET : jArr[2];
            int i3 = jArr[1] > 0 ? 1 : 0;
            List arrayList3 = z ? this.videoFormatHistory : new ArrayList(this.videoFormatHistory);
            List arrayList4 = z ? this.audioFormatHistory : new ArrayList(this.audioFormatHistory);
            List arrayList5 = z ? this.playbackStateHistory : new ArrayList(this.playbackStateHistory);
            long j2 = this.firstReportedTimeMs;
            boolean z2 = this.isForeground;
            int i4 = !this.hasBeenReady ? 1 : 0;
            boolean z3 = this.hasEnded;
            int i5 = i2 ^ 1;
            int i6 = this.pauseCount;
            int i7 = this.pauseBufferCount;
            int i8 = this.seekCount;
            int i9 = this.rebufferCount;
            long j3 = this.maxRebufferTimeMs;
            boolean z4 = this.isAd;
            return new PlaybackStats(1, jArr, arrayList5, arrayList, j2, z2 ? 1 : 0, i4, z3 ? 1 : 0, i3, j, i5, i6, i7, i8, i9, j3, z4 ? 1 : 0, arrayList3, arrayList4, this.videoFormatHeightTimeMs, this.videoFormatHeightTimeProduct, this.videoFormatBitrateTimeMs, this.videoFormatBitrateTimeProduct, this.audioFormatTimeMs, this.audioFormatBitrateTimeProduct, this.initialVideoFormatHeight == -1 ? 0 : 1, this.initialVideoFormatBitrate == -1 ? 0 : 1, this.initialVideoFormatHeight, this.initialVideoFormatBitrate, this.initialAudioFormatBitrate == -1 ? 0 : 1, this.initialAudioFormatBitrate, this.bandwidthTimeMs, this.bandwidthBytes, this.droppedFrames, this.audioUnderruns, this.fatalErrorCount > 0 ? 1 : 0, this.fatalErrorCount, this.nonFatalErrorCount, this.fatalErrorHistory, this.nonFatalErrorHistory);
        }

        public void onEvents(Player player, AnalyticsListener.EventTime eventTime, boolean z, boolean z2, boolean z3, int i, boolean z4, boolean z5, @Nullable ExoPlaybackException exoPlaybackException, @Nullable Exception exc, long j, long j2, @Nullable Format format, @Nullable Format format2, int i2, int i3) {
            TrackSelection[] all;
            if (z2) {
                this.isSeeking = true;
            }
            if (player.getPlaybackState() != 2) {
                this.isSeeking = false;
            }
            int playbackState = player.getPlaybackState();
            if (playbackState == 1 || playbackState == 4 || z3) {
                this.isInterruptedByAd = false;
            }
            if (exoPlaybackException != null) {
                this.hasFatalError = true;
                this.fatalErrorCount++;
                if (this.keepHistory) {
                    this.fatalErrorHistory.add(new PlaybackStats.EventTimeAndException(eventTime, exoPlaybackException));
                }
            } else if (player.getPlayerError() == null) {
                this.hasFatalError = false;
            }
            if (this.isForeground && !this.isInterruptedByAd) {
                boolean z6 = false;
                boolean z7 = false;
                for (TrackSelection trackSelection : player.getCurrentTrackSelections().getAll()) {
                    if (trackSelection != null && trackSelection.length() > 0) {
                        int trackType = MimeTypes.getTrackType(trackSelection.getFormat(0).sampleMimeType);
                        if (trackType == 2) {
                            z6 = true;
                        } else if (trackType == 1) {
                            z7 = true;
                        }
                    }
                }
                if (!z6) {
                    maybeUpdateVideoFormat(eventTime, null);
                }
                if (!z7) {
                    maybeUpdateAudioFormat(eventTime, null);
                }
            }
            if (format != null) {
                maybeUpdateVideoFormat(eventTime, format);
            }
            if (format2 != null) {
                maybeUpdateAudioFormat(eventTime, format2);
            }
            Format format3 = this.currentVideoFormat;
            if (format3 != null && format3.height == -1 && i2 != -1) {
                maybeUpdateVideoFormat(eventTime, format3.buildUpon().setWidth(i3).setHeight(i2).build());
            }
            if (z5) {
                this.startedLoading = true;
            }
            if (z4) {
                this.audioUnderruns++;
            }
            this.droppedFrames += i;
            this.bandwidthTimeMs += j;
            this.bandwidthBytes += j2;
            if (exc != null) {
                this.nonFatalErrorCount++;
                if (this.keepHistory) {
                    this.nonFatalErrorHistory.add(new PlaybackStats.EventTimeAndException(eventTime, exc));
                }
            }
            int resolveNewPlaybackState = resolveNewPlaybackState(player);
            float f = player.getPlaybackParameters().speed;
            if (this.currentPlaybackState != resolveNewPlaybackState || this.currentPlaybackSpeed != f) {
                maybeUpdateMediaTimeHistory(eventTime.realtimeMs, z ? eventTime.eventPlaybackPositionMs : C.TIME_UNSET);
                maybeRecordVideoFormatTime(eventTime.realtimeMs);
                maybeRecordAudioFormatTime(eventTime.realtimeMs);
            }
            this.currentPlaybackSpeed = f;
            if (this.currentPlaybackState != resolveNewPlaybackState) {
                updatePlaybackState(resolveNewPlaybackState, eventTime);
            }
        }

        public void onFinished(AnalyticsListener.EventTime eventTime, boolean z) {
            int i = 11;
            if (this.currentPlaybackState != 11 && !z) {
                i = 15;
            }
            maybeUpdateMediaTimeHistory(eventTime.realtimeMs, C.TIME_UNSET);
            maybeRecordVideoFormatTime(eventTime.realtimeMs);
            maybeRecordAudioFormatTime(eventTime.realtimeMs);
            updatePlaybackState(i, eventTime);
        }

        public void onForeground() {
            this.isForeground = true;
        }

        public void onInterruptedByAd() {
            this.isInterruptedByAd = true;
            this.isSeeking = false;
        }
    }

    public PlaybackStatsListener(boolean z, @Nullable Callback callback) {
        this.callback = callback;
        this.keepHistory = z;
        this.sessionManager.setListener(this);
    }

    private Pair<AnalyticsListener.EventTime, Boolean> findBestEventTime(AnalyticsListener.Events events, String str) {
        AnalyticsListener.EventTime eventTime;
        MediaSource.MediaPeriodId mediaPeriodId;
        AnalyticsListener.EventTime eventTime2 = this.onSeekStartedEventTime;
        boolean z = eventTime2 != null && this.sessionManager.belongsToSession(eventTime2, str);
        for (int i = 0; i < events.size(); i++) {
            AnalyticsListener.EventTime eventTime3 = events.getEventTime(events.get(i));
            boolean belongsToSession = this.sessionManager.belongsToSession(eventTime3, str);
            if (eventTime2 == null || ((belongsToSession && !z) || (belongsToSession == z && eventTime3.realtimeMs > eventTime2.realtimeMs))) {
                eventTime2 = eventTime3;
                z = belongsToSession;
            }
        }
        Assertions.checkNotNull(eventTime2);
        if (z || (mediaPeriodId = eventTime2.mediaPeriodId) == null || !mediaPeriodId.isAd()) {
            eventTime = eventTime2;
        } else {
            long adGroupTimeUs = eventTime2.timeline.getPeriodByUid(eventTime2.mediaPeriodId.periodUid, this.period).getAdGroupTimeUs(eventTime2.mediaPeriodId.adGroupIndex);
            if (adGroupTimeUs == Long.MIN_VALUE) {
                adGroupTimeUs = this.period.durationUs;
            }
            long positionInWindowUs = this.period.getPositionInWindowUs() + adGroupTimeUs;
            long j = eventTime2.realtimeMs;
            Timeline timeline = eventTime2.timeline;
            int i2 = eventTime2.windowIndex;
            MediaSource.MediaPeriodId mediaPeriodId2 = eventTime2.mediaPeriodId;
            eventTime = new AnalyticsListener.EventTime(j, timeline, i2, new MediaSource.MediaPeriodId(mediaPeriodId2.periodUid, mediaPeriodId2.windowSequenceNumber, mediaPeriodId2.adGroupIndex), C.usToMs(positionInWindowUs), eventTime2.timeline, eventTime2.currentWindowIndex, eventTime2.currentMediaPeriodId, eventTime2.currentPlaybackPositionMs, eventTime2.totalBufferedDurationMs);
            z = this.sessionManager.belongsToSession(eventTime, str);
        }
        return Pair.create(eventTime, Boolean.valueOf(z));
    }

    private boolean hasEvent(AnalyticsListener.Events events, String str, int i) {
        return events.contains(i) && this.sessionManager.belongsToSession(events.getEventTime(i), str);
    }

    private void maybeAddSessions(Player player, AnalyticsListener.Events events) {
        boolean z = true;
        if (!player.getCurrentTimeline().isEmpty() || player.getPlaybackState() != 1) {
            z = false;
        }
        for (int i = 0; i < events.size(); i++) {
            int i2 = events.get(i);
            AnalyticsListener.EventTime eventTime = events.getEventTime(i2);
            if (i2 == 0) {
                this.sessionManager.updateSessionsWithTimelineChange(eventTime);
            } else if (!z && i2 == 12) {
                this.sessionManager.updateSessionsWithDiscontinuity(eventTime, this.discontinuityReason);
            } else if (!z) {
                this.sessionManager.updateSessions(eventTime);
            }
        }
    }

    public PlaybackStats getCombinedPlaybackStats() {
        int i = 1;
        PlaybackStats[] playbackStatsArr = new PlaybackStats[this.playbackStatsTrackers.size() + 1];
        playbackStatsArr[0] = this.finishedPlaybackStats;
        for (PlaybackStatsTracker playbackStatsTracker : this.playbackStatsTrackers.values()) {
            playbackStatsArr[i] = playbackStatsTracker.build(false);
            i++;
        }
        return PlaybackStats.merge(playbackStatsArr);
    }

    @Nullable
    public PlaybackStats getPlaybackStats() {
        PlaybackStatsTracker playbackStatsTracker;
        String str = this.activeAdPlayback;
        if (str != null) {
            playbackStatsTracker = this.playbackStatsTrackers.get(str);
        } else {
            String str2 = this.activeContentPlayback;
            playbackStatsTracker = str2 != null ? this.playbackStatsTrackers.get(str2) : null;
        }
        if (playbackStatsTracker == null) {
            return null;
        }
        return playbackStatsTracker.build(false);
    }

    @Override // com.google.android.exoplayer2.analytics.PlaybackSessionManager.Listener
    public void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String str, String str2) {
        ((PlaybackStatsTracker) Assertions.checkNotNull(this.playbackStatsTrackers.get(str))).onInterruptedByAd();
    }

    @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
    public void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
        this.bandwidthTimeMs = i;
        this.bandwidthBytes = j;
    }

    @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        int i = mediaLoadData.trackType;
        if (i == 2 || i == 0) {
            this.videoFormat = mediaLoadData.trackFormat;
        } else if (i != 1) {
        } else {
            this.audioFormat = mediaLoadData.trackFormat;
        }
    }

    @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
    public void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
        this.nonFatalException = exc;
    }

    @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
    public void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i, long j) {
        this.droppedFrames = i;
    }

    @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
    public void onEvents(Player player, AnalyticsListener.Events events) {
        if (events.size() == 0) {
            return;
        }
        maybeAddSessions(player, events);
        for (String str : this.playbackStatsTrackers.keySet()) {
            Pair<AnalyticsListener.EventTime, Boolean> findBestEventTime = findBestEventTime(events, str);
            PlaybackStatsTracker playbackStatsTracker = this.playbackStatsTrackers.get(str);
            boolean z = hasEvent(events, str, 12) || hasEvent(events, str, 0);
            boolean hasEvent = hasEvent(events, str, 1023);
            boolean hasEvent2 = hasEvent(events, str, 1012);
            boolean hasEvent3 = hasEvent(events, str, 1000);
            boolean hasEvent4 = hasEvent(events, str, 11);
            boolean z2 = hasEvent(events, str, 1003) || hasEvent(events, str, 1032);
            boolean hasEvent5 = hasEvent(events, str, 1006);
            boolean hasEvent6 = hasEvent(events, str, 1004);
            boolean hasEvent7 = hasEvent(events, str, 1028);
            playbackStatsTracker.onEvents(player, (AnalyticsListener.EventTime) findBestEventTime.first, ((Boolean) findBestEventTime.second).booleanValue(), this.onSeekStartedEventTime != null, z, hasEvent ? this.droppedFrames : 0, hasEvent2, hasEvent3, hasEvent4 ? player.getPlayerError() : null, z2 ? this.nonFatalException : null, hasEvent5 ? this.bandwidthTimeMs : 0L, hasEvent5 ? this.bandwidthBytes : 0L, hasEvent6 ? this.videoFormat : null, hasEvent6 ? this.audioFormat : null, hasEvent7 ? this.videoHeight : -1, hasEvent7 ? this.videoWidth : -1);
        }
        this.onSeekStartedEventTime = null;
        this.videoFormat = null;
        this.audioFormat = null;
        if (!events.contains(1036)) {
            return;
        }
        this.sessionManager.finishAllSessions(events.getEventTime(1036));
    }

    @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
    public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.nonFatalException = iOException;
    }

    @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, int i) {
        this.discontinuityReason = i;
    }

    @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
    public void onSeekStarted(AnalyticsListener.EventTime eventTime) {
        this.onSeekStartedEventTime = eventTime;
    }

    @Override // com.google.android.exoplayer2.analytics.PlaybackSessionManager.Listener
    public void onSessionActive(AnalyticsListener.EventTime eventTime, String str) {
        ((PlaybackStatsTracker) Assertions.checkNotNull(this.playbackStatsTrackers.get(str))).onForeground();
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if (mediaPeriodId != null && mediaPeriodId.isAd()) {
            this.activeAdPlayback = str;
        } else {
            this.activeContentPlayback = str;
        }
    }

    @Override // com.google.android.exoplayer2.analytics.PlaybackSessionManager.Listener
    public void onSessionCreated(AnalyticsListener.EventTime eventTime, String str) {
        this.playbackStatsTrackers.put(str, new PlaybackStatsTracker(this.keepHistory, eventTime));
        this.sessionStartEventTimes.put(str, eventTime);
    }

    @Override // com.google.android.exoplayer2.analytics.PlaybackSessionManager.Listener
    public void onSessionFinished(AnalyticsListener.EventTime eventTime, String str, boolean z) {
        if (str.equals(this.activeAdPlayback)) {
            this.activeAdPlayback = null;
        } else if (str.equals(this.activeContentPlayback)) {
            this.activeContentPlayback = null;
        }
        PlaybackStatsTracker playbackStatsTracker = (PlaybackStatsTracker) Assertions.checkNotNull(this.playbackStatsTrackers.remove(str));
        AnalyticsListener.EventTime eventTime2 = (AnalyticsListener.EventTime) Assertions.checkNotNull(this.sessionStartEventTimes.remove(str));
        playbackStatsTracker.onFinished(eventTime, z);
        PlaybackStats build = playbackStatsTracker.build(true);
        this.finishedPlaybackStats = PlaybackStats.merge(this.finishedPlaybackStats, build);
        Callback callback = this.callback;
        if (callback != null) {
            callback.onPlaybackStatsReady(eventTime2, build);
        }
    }

    @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
    public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2, int i3, float f) {
        this.videoWidth = i;
        this.videoHeight = i2;
    }
}
