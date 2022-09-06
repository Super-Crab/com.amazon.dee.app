package com.amazon.alexa.accessory.internal.connection;

import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.queue.TransportQueue;
import com.amazon.alexa.accessorykit.ModelTransformer;
import java.io.Closeable;
import java.io.InterruptedIOException;
/* loaded from: classes.dex */
public final class ConnectionTransportDispatcher implements Closeable {
    private final Accessory accessory;
    private final ConnectionCallback callback;
    private volatile boolean closed;
    private final Handler handler;
    private final long idleTimeoutMillis;
    private final TransportQueue queue;
    private DispatcherThread thread;

    /* loaded from: classes.dex */
    final class DispatcherThread extends Thread {
        DispatcherThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Logger.d("Connection transport dispatcher (%s) started", ConnectionTransportDispatcher.this.accessory);
            final ConnectionTransportDispatcher connectionTransportDispatcher = ConnectionTransportDispatcher.this;
            Runnable runnable = new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$2acsCFIGWtbKG-L87UteSYmnoV4
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectionTransportDispatcher.this.notifyIdle();
                }
            };
            try {
                try {
                    connectionTransportDispatcher.queue.prepare();
                    ConnectionTransportDispatcher.this.notifyConnected();
                    while (!isInterrupted()) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (ConnectionTransportDispatcher.this.idleTimeoutMillis > 0) {
                            ConnectionTransportDispatcher.this.handler.postDelayed(runnable, ConnectionTransportDispatcher.this.idleTimeoutMillis);
                        }
                        ConnectionTransportDispatcher.this.queue.executeNext();
                        if (ConnectionTransportDispatcher.this.idleTimeoutMillis > 0) {
                            ConnectionTransportDispatcher.this.handler.removeCallbacks(runnable);
                            if (System.currentTimeMillis() - currentTimeMillis >= ConnectionTransportDispatcher.this.idleTimeoutMillis) {
                                Handler handler = ConnectionTransportDispatcher.this.handler;
                                final ConnectionTransportDispatcher connectionTransportDispatcher2 = ConnectionTransportDispatcher.this;
                                handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$aTBXclhzMVTWda-IuT3tKzLbCFA
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        ConnectionTransportDispatcher.this.notifyActive();
                                    }
                                });
                            }
                        }
                    }
                    Logger.d("Connection transport dispatcher (%s) is terminating", ConnectionTransportDispatcher.this.accessory);
                    ConnectionTransportDispatcher.this.notifyDisconnected();
                } catch (InterruptedIOException unused) {
                    Logger.d("Connection transport dispatcher (%s) is terminating", ConnectionTransportDispatcher.this.accessory);
                    ConnectionTransportDispatcher.this.notifyDisconnected();
                } catch (Exception e) {
                    if (ConnectionTransportDispatcher.this.closed) {
                        Logger.d("Connection transport dispatcher (%s) is terminating", ConnectionTransportDispatcher.this.accessory);
                        ConnectionTransportDispatcher.this.notifyDisconnected();
                    } else {
                        Logger.d("Connection transport dispatcher (%s) is terminating with error", e, ConnectionTransportDispatcher.this.accessory);
                        ConnectionTransportDispatcher.this.notifyTerminated(e);
                    }
                }
            } finally {
                ConnectionTransportDispatcher.this.handler.removeCallbacks(runnable);
            }
        }
    }

    public ConnectionTransportDispatcher(Accessory accessory, TransportQueue transportQueue, Handler handler, long j, ConnectionCallback connectionCallback) {
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(transportQueue, "queue");
        Preconditions.notNull(handler, "handler");
        this.accessory = accessory;
        this.queue = transportQueue;
        this.handler = handler;
        this.callback = connectionCallback;
        this.idleTimeoutMillis = j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        DispatcherThread dispatcherThread = this.thread;
        if (dispatcherThread == null) {
            return;
        }
        dispatcherThread.interrupt();
    }

    public /* synthetic */ void lambda$notifyActive$4$ConnectionTransportDispatcher() {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onActive();
        }
    }

    public /* synthetic */ void lambda$notifyConnected$0$ConnectionTransportDispatcher() {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onConnected();
        }
    }

    public /* synthetic */ void lambda$notifyDisconnected$2$ConnectionTransportDispatcher() {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onDisconnected();
        }
    }

    public /* synthetic */ void lambda$notifyIdle$3$ConnectionTransportDispatcher() {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onIdle();
        }
    }

    public /* synthetic */ void lambda$notifyTerminated$1$ConnectionTransportDispatcher(Throwable th) {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onFailed(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyActive() {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportDispatcher$jZ87pHonML-YiDU45yzkThOgECo
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportDispatcher.this.lambda$notifyActive$4$ConnectionTransportDispatcher();
            }
        });
    }

    void notifyConnected() {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportDispatcher$1-tq3pYoSUlX_0rUx8Cov_kw77o
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportDispatcher.this.lambda$notifyConnected$0$ConnectionTransportDispatcher();
            }
        });
    }

    void notifyDisconnected() {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportDispatcher$c2lEIyaKSdx0tOa0Pa-Fu9uC39U
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportDispatcher.this.lambda$notifyDisconnected$2$ConnectionTransportDispatcher();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyIdle() {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportDispatcher$9B4A40A6ZDdQOde0bQYdacj-9ec
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportDispatcher.this.lambda$notifyIdle$3$ConnectionTransportDispatcher();
            }
        });
    }

    void notifyTerminated(final Throwable th) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportDispatcher$YN6rtG2fJ9nnyW_rQ5vBpfRfrAU
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportDispatcher.this.lambda$notifyTerminated$1$ConnectionTransportDispatcher(th);
            }
        });
    }

    public void open() {
        if (!this.closed) {
            if (this.thread != null) {
                return;
            }
            this.thread = new DispatcherThread();
            this.thread.start();
            return;
        }
        throw new IllegalStateException("Dispatcher is closed");
    }

    @VisibleForTesting
    boolean peekIsThreadInterruptedForTesting() {
        DispatcherThread dispatcherThread = this.thread;
        if (dispatcherThread != null) {
            return dispatcherThread.isInterrupted();
        }
        throw new IllegalStateException("No thread");
    }
}
