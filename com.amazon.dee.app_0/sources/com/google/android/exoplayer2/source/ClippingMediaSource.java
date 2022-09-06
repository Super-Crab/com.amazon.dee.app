package com.google.android.exoplayer2.source;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public final class ClippingMediaSource extends CompositeMediaSource<Void> {
    private final boolean allowDynamicClippingUpdates;
    @Nullable
    private IllegalClippingException clippingError;
    @Nullable
    private ClippingTimeline clippingTimeline;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private long periodEndUs;
    private long periodStartUs;
    private final boolean relativeToDefaultPosition;
    private final long startUs;
    private final Timeline.Window window;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ClippingTimeline extends ForwardingTimeline {
        private final long durationUs;
        private final long endUs;
        private final boolean isDynamic;
        private final long startUs;

        /* JADX WARN: Code restructure failed: missing block: B:37:0x0072, code lost:
            if (r11 == r8) goto L34;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public ClippingTimeline(com.google.android.exoplayer2.Timeline r8, long r9, long r11) throws com.google.android.exoplayer2.source.ClippingMediaSource.IllegalClippingException {
            /*
                r7 = this;
                r7.<init>(r8)
                int r0 = r8.getPeriodCount()
                r1 = 0
                r2 = 1
                if (r0 != r2) goto L78
                com.google.android.exoplayer2.Timeline$Window r0 = new com.google.android.exoplayer2.Timeline$Window
                r0.<init>()
                com.google.android.exoplayer2.Timeline$Window r8 = r8.getWindow(r1, r0)
                r3 = 0
                long r9 = java.lang.Math.max(r3, r9)
                boolean r0 = r8.isPlaceholder
                if (r0 != 0) goto L2d
                int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
                if (r0 == 0) goto L2d
                boolean r0 = r8.isSeekable
                if (r0 == 0) goto L27
                goto L2d
            L27:
                com.google.android.exoplayer2.source.ClippingMediaSource$IllegalClippingException r8 = new com.google.android.exoplayer2.source.ClippingMediaSource$IllegalClippingException
                r8.<init>(r2)
                throw r8
            L2d:
                r5 = -9223372036854775808
                int r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
                if (r0 != 0) goto L36
                long r11 = r8.durationUs
                goto L3a
            L36:
                long r11 = java.lang.Math.max(r3, r11)
            L3a:
                long r3 = r8.durationUs
                r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 == 0) goto L56
                int r0 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
                if (r0 <= 0) goto L4a
                r11 = r3
            L4a:
                int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r0 > 0) goto L4f
                goto L56
            L4f:
                com.google.android.exoplayer2.source.ClippingMediaSource$IllegalClippingException r8 = new com.google.android.exoplayer2.source.ClippingMediaSource$IllegalClippingException
                r9 = 2
                r8.<init>(r9)
                throw r8
            L56:
                r7.startUs = r9
                r7.endUs = r11
                int r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
                if (r0 != 0) goto L60
                r9 = r5
                goto L62
            L60:
                long r9 = r11 - r9
            L62:
                r7.durationUs = r9
                boolean r9 = r8.isDynamic
                if (r9 == 0) goto L75
                if (r0 == 0) goto L74
                long r8 = r8.durationUs
                int r10 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
                if (r10 == 0) goto L75
                int r8 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
                if (r8 != 0) goto L75
            L74:
                r1 = r2
            L75:
                r7.isDynamic = r1
                return
            L78:
                com.google.android.exoplayer2.source.ClippingMediaSource$IllegalClippingException r8 = new com.google.android.exoplayer2.source.ClippingMediaSource$IllegalClippingException
                r8.<init>(r1)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ClippingMediaSource.ClippingTimeline.<init>(com.google.android.exoplayer2.Timeline, long, long):void");
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(0, period, z);
            long positionInWindowUs = period.getPositionInWindowUs() - this.startUs;
            long j = this.durationUs;
            return period.set(period.id, period.uid, 0, j == C.TIME_UNSET ? -9223372036854775807L : j - positionInWindowUs, positionInWindowUs);
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            this.timeline.getWindow(0, window, 0L);
            long j2 = window.positionInFirstPeriodUs;
            long j3 = this.startUs;
            window.positionInFirstPeriodUs = j2 + j3;
            window.durationUs = this.durationUs;
            window.isDynamic = this.isDynamic;
            long j4 = window.defaultPositionUs;
            if (j4 != C.TIME_UNSET) {
                window.defaultPositionUs = Math.max(j4, j3);
                long j5 = this.endUs;
                window.defaultPositionUs = j5 == C.TIME_UNSET ? window.defaultPositionUs : Math.min(window.defaultPositionUs, j5);
                window.defaultPositionUs -= this.startUs;
            }
            long usToMs = C.usToMs(this.startUs);
            long j6 = window.presentationStartTimeMs;
            if (j6 != C.TIME_UNSET) {
                window.presentationStartTimeMs = j6 + usToMs;
            }
            long j7 = window.windowStartTimeMs;
            if (j7 != C.TIME_UNSET) {
                window.windowStartTimeMs = j7 + usToMs;
            }
            return window;
        }
    }

    /* loaded from: classes2.dex */
    public static final class IllegalClippingException extends IOException {
        public static final int REASON_INVALID_PERIOD_COUNT = 0;
        public static final int REASON_NOT_SEEKABLE_TO_START = 1;
        public static final int REASON_START_EXCEEDS_END = 2;
        public final int reason;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface Reason {
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public IllegalClippingException(int r3) {
            /*
                r2 = this;
                java.lang.String r0 = "Illegal clipping: "
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
                java.lang.String r1 = getReasonDescription(r3)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r2.<init>(r0)
                r2.reason = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ClippingMediaSource.IllegalClippingException.<init>(int):void");
        }

        private static String getReasonDescription(int i) {
            return i != 0 ? i != 1 ? i != 2 ? "unknown" : "start exceeds end" : "not seekable to start" : "invalid period count";
        }
    }

    public ClippingMediaSource(MediaSource mediaSource, long j, long j2) {
        this(mediaSource, j, j2, true, false, false);
    }

    private void refreshClippedTimeline(Timeline timeline) {
        long j;
        timeline.getWindow(0, this.window);
        long positionInFirstPeriodUs = this.window.getPositionInFirstPeriodUs();
        long j2 = Long.MIN_VALUE;
        if (this.clippingTimeline != null && !this.mediaPeriods.isEmpty() && !this.allowDynamicClippingUpdates) {
            long j3 = this.periodStartUs - positionInFirstPeriodUs;
            if (this.endUs != Long.MIN_VALUE) {
                j2 = this.periodEndUs - positionInFirstPeriodUs;
            }
            j = j3;
        } else {
            long j4 = this.startUs;
            long j5 = this.endUs;
            if (this.relativeToDefaultPosition) {
                long defaultPositionUs = this.window.getDefaultPositionUs();
                j4 += defaultPositionUs;
                j5 += defaultPositionUs;
            }
            this.periodStartUs = positionInFirstPeriodUs + j4;
            if (this.endUs != Long.MIN_VALUE) {
                j2 = positionInFirstPeriodUs + j5;
            }
            this.periodEndUs = j2;
            int size = this.mediaPeriods.size();
            for (int i = 0; i < size; i++) {
                this.mediaPeriods.get(i).updateClipping(this.periodStartUs, this.periodEndUs);
            }
            j = j4;
            j2 = j5;
        }
        try {
            this.clippingTimeline = new ClippingTimeline(timeline, j, j2);
            refreshSourceInfo(this.clippingTimeline);
        } catch (IllegalClippingException e) {
            this.clippingError = e;
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    /* renamed from: createPeriod */
    public MediaPeriod mo7373createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.mo7373createPeriod(mediaPeriodId, allocator, j), this.enableInitialDiscontinuity, this.periodStartUs, this.periodEndUs);
        this.mediaPeriods.add(clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    @Nullable
    @Deprecated
    public Object getTag() {
        return this.mediaSource.getTag();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }
        throw illegalClippingException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareChildSource(null, this.mediaSource);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
        if (!this.mediaPeriods.isEmpty() || this.allowDynamicClippingUpdates) {
            return;
        }
        refreshClippedTimeline(((ClippingTimeline) Assertions.checkNotNull(this.clippingTimeline)).timeline);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.clippingError = null;
        this.clippingTimeline = null;
    }

    public ClippingMediaSource(MediaSource mediaSource, long j) {
        this(mediaSource, 0L, j, true, false, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public long getMediaTimeForChildMediaTime(Void r7, long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long usToMs = C.usToMs(this.startUs);
        long max = Math.max(0L, j - usToMs);
        long j2 = this.endUs;
        return j2 != Long.MIN_VALUE ? Math.min(C.usToMs(j2) - usToMs, max) : max;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(Void r1, MediaSource mediaSource, Timeline timeline) {
        if (this.clippingError != null) {
            return;
        }
        refreshClippedTimeline(timeline);
    }

    public ClippingMediaSource(MediaSource mediaSource, long j, long j2, boolean z, boolean z2, boolean z3) {
        Assertions.checkArgument(j >= 0);
        this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource);
        this.startUs = j;
        this.endUs = j2;
        this.enableInitialDiscontinuity = z;
        this.allowDynamicClippingUpdates = z2;
        this.relativeToDefaultPosition = z3;
        this.mediaPeriods = new ArrayList<>();
        this.window = new Timeline.Window();
    }
}
