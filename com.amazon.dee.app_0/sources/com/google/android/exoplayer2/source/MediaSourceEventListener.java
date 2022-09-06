package com.google.android.exoplayer2.source;

import android.os.Handler;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public interface MediaSourceEventListener {

    /* loaded from: classes2.dex */
    public static class EventDispatcher {
        private final CopyOnWriteArrayList<ListenerAndHandler> listenerAndHandlers;
        @Nullable
        public final MediaSource.MediaPeriodId mediaPeriodId;
        private final long mediaTimeOffsetMs;
        public final int windowIndex;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static final class ListenerAndHandler {
            public Handler handler;
            public MediaSourceEventListener listener;

            public ListenerAndHandler(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
                this.handler = handler;
                this.listener = mediaSourceEventListener;
            }
        }

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, null, 0L);
        }

        private long adjustMediaTime(long j) {
            long usToMs = C.usToMs(j);
            return usToMs == C.TIME_UNSET ? C.TIME_UNSET : this.mediaTimeOffsetMs + usToMs;
        }

        public void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            Assertions.checkNotNull(handler);
            Assertions.checkNotNull(mediaSourceEventListener);
            this.listenerAndHandlers.add(new ListenerAndHandler(handler, mediaSourceEventListener));
        }

        public void downstreamFormatChanged(int i, @Nullable Format format, int i2, @Nullable Object obj, long j) {
            downstreamFormatChanged(new MediaLoadData(1, i, format, i2, obj, adjustMediaTime(j), C.TIME_UNSET));
        }

        public /* synthetic */ void lambda$downstreamFormatChanged$5$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onDownstreamFormatChanged(this.windowIndex, this.mediaPeriodId, mediaLoadData);
        }

        public /* synthetic */ void lambda$loadCanceled$2$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onLoadCanceled(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        public /* synthetic */ void lambda$loadCompleted$1$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onLoadCompleted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        public /* synthetic */ void lambda$loadError$3$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            mediaSourceEventListener.onLoadError(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData, iOException, z);
        }

        public /* synthetic */ void lambda$loadStarted$0$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onLoadStarted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        public /* synthetic */ void lambda$upstreamDiscarded$4$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onUpstreamDiscarded(this.windowIndex, mediaPeriodId, mediaLoadData);
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, int i) {
            loadCanceled(loadEventInfo, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, int i) {
            loadCompleted(loadEventInfo, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void loadError(LoadEventInfo loadEventInfo, int i, IOException iOException, boolean z) {
            loadError(loadEventInfo, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, iOException, z);
        }

        public void loadStarted(LoadEventInfo loadEventInfo, int i) {
            loadStarted(loadEventInfo, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                if (next.listener == mediaSourceEventListener) {
                    this.listenerAndHandlers.remove(next);
                }
            }
        }

        public void upstreamDiscarded(int i, long j, long j2) {
            upstreamDiscarded(new MediaLoadData(1, i, null, 3, null, adjustMediaTime(j), adjustMediaTime(j2)));
        }

        @CheckResult
        public EventDispatcher withParameters(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, long j) {
            return new EventDispatcher(this.listenerAndHandlers, i, mediaPeriodId, j);
        }

        private EventDispatcher(CopyOnWriteArrayList<ListenerAndHandler> copyOnWriteArrayList, int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, long j) {
            this.listenerAndHandlers = copyOnWriteArrayList;
            this.windowIndex = i;
            this.mediaPeriodId = mediaPeriodId;
            this.mediaTimeOffsetMs = j;
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, int i, int i2, @Nullable Format format, int i3, @Nullable Object obj, long j, long j2) {
            loadCanceled(loadEventInfo, new MediaLoadData(i, i2, format, i3, obj, adjustMediaTime(j), adjustMediaTime(j2)));
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, int i, int i2, @Nullable Format format, int i3, @Nullable Object obj, long j, long j2) {
            loadCompleted(loadEventInfo, new MediaLoadData(i, i2, format, i3, obj, adjustMediaTime(j), adjustMediaTime(j2)));
        }

        public void loadError(LoadEventInfo loadEventInfo, int i, int i2, @Nullable Format format, int i3, @Nullable Object obj, long j, long j2, IOException iOException, boolean z) {
            loadError(loadEventInfo, new MediaLoadData(i, i2, format, i3, obj, adjustMediaTime(j), adjustMediaTime(j2)), iOException, z);
        }

        public void loadStarted(LoadEventInfo loadEventInfo, int i, int i2, @Nullable Format format, int i3, @Nullable Object obj, long j, long j2) {
            loadStarted(loadEventInfo, new MediaLoadData(i, i2, format, i3, obj, adjustMediaTime(j), adjustMediaTime(j2)));
        }

        public void downstreamFormatChanged(final MediaLoadData mediaLoadData) {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final MediaSourceEventListener mediaSourceEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$lXgSj-TgNs51HV_3_11Y-SYdt1k
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceEventListener.EventDispatcher.this.lambda$downstreamFormatChanged$5$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, mediaLoadData);
                    }
                });
            }
        }

        public void upstreamDiscarded(final MediaLoadData mediaLoadData) {
            final MediaSource.MediaPeriodId mediaPeriodId = (MediaSource.MediaPeriodId) Assertions.checkNotNull(this.mediaPeriodId);
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final MediaSourceEventListener mediaSourceEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$wGoNlRlXeYTKNdaMsEykBJTWloc
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceEventListener.EventDispatcher.this.lambda$upstreamDiscarded$4$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, mediaPeriodId, mediaLoadData);
                    }
                });
            }
        }

        public void loadCanceled(final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData) {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final MediaSourceEventListener mediaSourceEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$Jm0OeQ0IopM_KXSMoqCgenhI3pE
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceEventListener.EventDispatcher.this.lambda$loadCanceled$2$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, loadEventInfo, mediaLoadData);
                    }
                });
            }
        }

        public void loadCompleted(final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData) {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final MediaSourceEventListener mediaSourceEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$h8bUcr8Ot-gMcu9Yr8fz_qHgReM
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceEventListener.EventDispatcher.this.lambda$loadCompleted$1$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, loadEventInfo, mediaLoadData);
                    }
                });
            }
        }

        public void loadError(final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData, final IOException iOException, final boolean z) {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final MediaSourceEventListener mediaSourceEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$RIFDO9msYkn72bNDpmyhv5rAFKY
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceEventListener.EventDispatcher.this.lambda$loadError$3$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, loadEventInfo, mediaLoadData, iOException, z);
                    }
                });
            }
        }

        public void loadStarted(final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData) {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final MediaSourceEventListener mediaSourceEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$jgZ8dQ5CK34j6VrAzMlnVEac2G8
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSourceEventListener.EventDispatcher.this.lambda$loadStarted$0$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, loadEventInfo, mediaLoadData);
                    }
                });
            }
        }
    }

    default void onDownstreamFormatChanged(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }

    default void onLoadCanceled(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    default void onLoadCompleted(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    default void onLoadError(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
    }

    default void onLoadStarted(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    default void onUpstreamDiscarded(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }
}
