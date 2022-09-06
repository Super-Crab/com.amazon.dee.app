package com.google.android.exoplayer2.upstream;

import android.os.Handler;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public interface BandwidthMeter {

    /* loaded from: classes2.dex */
    public interface EventListener {

        /* loaded from: classes2.dex */
        public static final class EventDispatcher {
            private final CopyOnWriteArrayList<HandlerAndListener> listeners = new CopyOnWriteArrayList<>();

            /* JADX INFO: Access modifiers changed from: private */
            /* loaded from: classes2.dex */
            public static final class HandlerAndListener {
                private final Handler handler;
                private final EventListener listener;
                private boolean released;

                public HandlerAndListener(Handler handler, EventListener eventListener) {
                    this.handler = handler;
                    this.listener = eventListener;
                }

                public void release() {
                    this.released = true;
                }
            }

            public void addListener(Handler handler, EventListener eventListener) {
                Assertions.checkNotNull(handler);
                Assertions.checkNotNull(eventListener);
                removeListener(eventListener);
                this.listeners.add(new HandlerAndListener(handler, eventListener));
            }

            public void bandwidthSample(final int i, final long j, final long j2) {
                Iterator<HandlerAndListener> it2 = this.listeners.iterator();
                while (it2.hasNext()) {
                    final HandlerAndListener next = it2.next();
                    if (!next.released) {
                        next.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.upstream.-$$Lambda$BandwidthMeter$EventListener$EventDispatcher$ZC--h263yldu3dFfxd--BCsztCw
                            @Override // java.lang.Runnable
                            public final void run() {
                                BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener.this.listener.onBandwidthSample(i, j, j2);
                            }
                        });
                    }
                }
            }

            public void removeListener(EventListener eventListener) {
                Iterator<HandlerAndListener> it2 = this.listeners.iterator();
                while (it2.hasNext()) {
                    HandlerAndListener next = it2.next();
                    if (next.listener == eventListener) {
                        next.release();
                        this.listeners.remove(next);
                    }
                }
            }
        }

        void onBandwidthSample(int i, long j, long j2);
    }

    void addEventListener(Handler handler, EventListener eventListener);

    long getBitrateEstimate();

    @Nullable
    TransferListener getTransferListener();

    void removeEventListener(EventListener eventListener);
}
