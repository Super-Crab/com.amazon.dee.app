package com.google.android.exoplayer2.drm;

import android.os.Handler;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public interface DrmSessionEventListener {

    /* loaded from: classes2.dex */
    public static class EventDispatcher {
        private final CopyOnWriteArrayList<ListenerAndHandler> listenerAndHandlers;
        @Nullable
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final int windowIndex;

        /* loaded from: classes2.dex */
        private static final class ListenerAndHandler {
            public Handler handler;
            public DrmSessionEventListener listener;

            public ListenerAndHandler(Handler handler, DrmSessionEventListener drmSessionEventListener) {
                this.handler = handler;
                this.listener = drmSessionEventListener;
            }
        }

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, null);
        }

        public void addEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener) {
            Assertions.checkNotNull(handler);
            Assertions.checkNotNull(drmSessionEventListener);
            this.listenerAndHandlers.add(new ListenerAndHandler(handler, drmSessionEventListener));
        }

        public void drmKeysLoaded() {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final DrmSessionEventListener drmSessionEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.drm.-$$Lambda$DrmSessionEventListener$EventDispatcher$fYiFNdiweiDl4WqcmrKqoi7uPXo
                    @Override // java.lang.Runnable
                    public final void run() {
                        DrmSessionEventListener.EventDispatcher.this.lambda$drmKeysLoaded$1$DrmSessionEventListener$EventDispatcher(drmSessionEventListener);
                    }
                });
            }
        }

        public void drmKeysRemoved() {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final DrmSessionEventListener drmSessionEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.drm.-$$Lambda$DrmSessionEventListener$EventDispatcher$KD_RaCSHlbFOrd4CxN3m4X_0R3Y
                    @Override // java.lang.Runnable
                    public final void run() {
                        DrmSessionEventListener.EventDispatcher.this.lambda$drmKeysRemoved$4$DrmSessionEventListener$EventDispatcher(drmSessionEventListener);
                    }
                });
            }
        }

        public void drmKeysRestored() {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final DrmSessionEventListener drmSessionEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.drm.-$$Lambda$DrmSessionEventListener$EventDispatcher$2JRkxTB_15LTwmCfOwaCNIRkcCY
                    @Override // java.lang.Runnable
                    public final void run() {
                        DrmSessionEventListener.EventDispatcher.this.lambda$drmKeysRestored$3$DrmSessionEventListener$EventDispatcher(drmSessionEventListener);
                    }
                });
            }
        }

        public void drmSessionAcquired() {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final DrmSessionEventListener drmSessionEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.drm.-$$Lambda$DrmSessionEventListener$EventDispatcher$H-Qm33JPqp2i6ox0UCL3NwNAYZs
                    @Override // java.lang.Runnable
                    public final void run() {
                        DrmSessionEventListener.EventDispatcher.this.lambda$drmSessionAcquired$0$DrmSessionEventListener$EventDispatcher(drmSessionEventListener);
                    }
                });
            }
        }

        public void drmSessionManagerError(final Exception exc) {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final DrmSessionEventListener drmSessionEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.drm.-$$Lambda$DrmSessionEventListener$EventDispatcher$Qh5FNOjOggDDibjPxDs8w9x3Rr8
                    @Override // java.lang.Runnable
                    public final void run() {
                        DrmSessionEventListener.EventDispatcher.this.lambda$drmSessionManagerError$2$DrmSessionEventListener$EventDispatcher(drmSessionEventListener, exc);
                    }
                });
            }
        }

        public void drmSessionReleased() {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                final DrmSessionEventListener drmSessionEventListener = next.listener;
                Util.postOrRun(next.handler, new Runnable() { // from class: com.google.android.exoplayer2.drm.-$$Lambda$DrmSessionEventListener$EventDispatcher$GO6ZWlOpi-sDVcDYg232Fpb2o8w
                    @Override // java.lang.Runnable
                    public final void run() {
                        DrmSessionEventListener.EventDispatcher.this.lambda$drmSessionReleased$5$DrmSessionEventListener$EventDispatcher(drmSessionEventListener);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$drmKeysLoaded$1$DrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener) {
            drmSessionEventListener.onDrmKeysLoaded(this.windowIndex, this.mediaPeriodId);
        }

        public /* synthetic */ void lambda$drmKeysRemoved$4$DrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener) {
            drmSessionEventListener.onDrmKeysRemoved(this.windowIndex, this.mediaPeriodId);
        }

        public /* synthetic */ void lambda$drmKeysRestored$3$DrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener) {
            drmSessionEventListener.onDrmKeysRestored(this.windowIndex, this.mediaPeriodId);
        }

        public /* synthetic */ void lambda$drmSessionAcquired$0$DrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener) {
            drmSessionEventListener.onDrmSessionAcquired(this.windowIndex, this.mediaPeriodId);
        }

        public /* synthetic */ void lambda$drmSessionManagerError$2$DrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener, Exception exc) {
            drmSessionEventListener.onDrmSessionManagerError(this.windowIndex, this.mediaPeriodId, exc);
        }

        public /* synthetic */ void lambda$drmSessionReleased$5$DrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener) {
            drmSessionEventListener.onDrmSessionReleased(this.windowIndex, this.mediaPeriodId);
        }

        public void removeEventListener(DrmSessionEventListener drmSessionEventListener) {
            Iterator<ListenerAndHandler> it2 = this.listenerAndHandlers.iterator();
            while (it2.hasNext()) {
                ListenerAndHandler next = it2.next();
                if (next.listener == drmSessionEventListener) {
                    this.listenerAndHandlers.remove(next);
                }
            }
        }

        @CheckResult
        public EventDispatcher withParameters(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            return new EventDispatcher(this.listenerAndHandlers, i, mediaPeriodId);
        }

        private EventDispatcher(CopyOnWriteArrayList<ListenerAndHandler> copyOnWriteArrayList, int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            this.listenerAndHandlers = copyOnWriteArrayList;
            this.windowIndex = i;
            this.mediaPeriodId = mediaPeriodId;
        }
    }

    default void onDrmKeysLoaded(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
    }

    default void onDrmKeysRemoved(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
    }

    default void onDrmKeysRestored(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
    }

    default void onDrmSessionAcquired(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
    }

    default void onDrmSessionManagerError(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
    }

    default void onDrmSessionReleased(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
    }
}
