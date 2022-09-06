package com.amazon.alexa.accessory.internal.connection;

import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.TransportData;
import com.amazon.alexa.accessory.transport.TransportReceiver;
import com.amazon.alexa.accessory.transport.queue.TransportQueue;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.deecomms.common.Constants;
import java.io.Closeable;
import java.io.InterruptedIOException;
/* loaded from: classes.dex */
public final class ConnectionTransportReceiver implements Closeable {
    private final Accessory accessory;
    private final ConnectionCallback callback;
    private volatile boolean closed;
    private final Handler handler;
    private final long idleTimeoutMillis;
    private final TransportQueue queue;
    private final ReceiverThread thread;

    /* loaded from: classes.dex */
    final class ReceiverThread extends Thread {
        private final Object monitor = new Object();
        private TransportReceiver receiver;

        public ReceiverThread() {
        }

        private TransportReceiver awaitReceiver() throws InterruptedException {
            TransportReceiver transportReceiver;
            synchronized (this.monitor) {
                while (this.receiver == null) {
                    this.monitor.wait();
                }
                transportReceiver = this.receiver;
            }
            return transportReceiver;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Logger.d("Connection transport receiver (%s) started", ConnectionTransportReceiver.this.accessory);
            final ConnectionTransportReceiver connectionTransportReceiver = ConnectionTransportReceiver.this;
            Runnable runnable = new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$18YfDqgpFn4vYv5tq-4JVotDHrs
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectionTransportReceiver.this.notifyIdle();
                }
            };
            try {
                try {
                    connectionTransportReceiver.queue.prepare();
                    ConnectionTransportReceiver.this.notifyConnected();
                    while (!isInterrupted()) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (ConnectionTransportReceiver.this.idleTimeoutMillis > 0) {
                            ConnectionTransportReceiver.this.handler.postDelayed(runnable, ConnectionTransportReceiver.this.idleTimeoutMillis);
                        }
                        TransportReceiver awaitReceiver = awaitReceiver();
                        TransportData read = ConnectionTransportReceiver.this.queue.read();
                        if (ConnectionTransportReceiver.this.idleTimeoutMillis > 0) {
                            ConnectionTransportReceiver.this.handler.removeCallbacks(runnable);
                            if (System.currentTimeMillis() - currentTimeMillis >= ConnectionTransportReceiver.this.idleTimeoutMillis) {
                                Handler handler = ConnectionTransportReceiver.this.handler;
                                final ConnectionTransportReceiver connectionTransportReceiver2 = ConnectionTransportReceiver.this;
                                handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$rbQ7TBIIvDf_pig6KcpA68JOzsk
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        ConnectionTransportReceiver.this.notifyActive();
                                    }
                                });
                            }
                        }
                        awaitReceiver.onDataReceived(read.getSource(), read.getStream());
                    }
                    Logger.d("Connection transport receiver (%s) is terminating", ConnectionTransportReceiver.this.accessory);
                    ConnectionTransportReceiver.this.notifyDisconnected();
                } catch (InterruptedIOException | InterruptedException unused) {
                    Logger.d("Connection transport receiver (%s) is terminating", ConnectionTransportReceiver.this.accessory);
                    ConnectionTransportReceiver.this.notifyDisconnected();
                } catch (Exception e) {
                    if (ConnectionTransportReceiver.this.closed) {
                        Logger.d("Connection transport receiver (%s) is terminating", ConnectionTransportReceiver.this.accessory);
                        ConnectionTransportReceiver.this.notifyDisconnected();
                    } else {
                        Logger.d("Connection transport receiver (%s) is terminating with error", e, ConnectionTransportReceiver.this.accessory);
                        ConnectionTransportReceiver.this.notifyTerminated(e);
                    }
                }
            } finally {
                ConnectionTransportReceiver.this.handler.removeCallbacks(runnable);
            }
        }

        public void setReceiver(TransportReceiver transportReceiver) {
            synchronized (this.monitor) {
                this.receiver = transportReceiver;
                this.monitor.notifyAll();
            }
        }
    }

    public ConnectionTransportReceiver(Accessory accessory, TransportQueue transportQueue, Handler handler, long j, ConnectionCallback connectionCallback) {
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(transportQueue, "queue");
        Preconditions.notNull(handler, "handler");
        this.accessory = accessory;
        this.queue = transportQueue;
        this.handler = handler;
        this.callback = connectionCallback;
        this.idleTimeoutMillis = j;
        this.thread = new ReceiverThread();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.thread.interrupt();
    }

    public /* synthetic */ void lambda$notifyActive$4$ConnectionTransportReceiver() {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onActive();
        }
    }

    public /* synthetic */ void lambda$notifyConnected$0$ConnectionTransportReceiver() {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onConnected();
        }
    }

    public /* synthetic */ void lambda$notifyDisconnected$2$ConnectionTransportReceiver() {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onDisconnected();
        }
    }

    public /* synthetic */ void lambda$notifyIdle$3$ConnectionTransportReceiver() {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onIdle();
        }
    }

    public /* synthetic */ void lambda$notifyTerminated$1$ConnectionTransportReceiver(Throwable th) {
        ConnectionCallback connectionCallback = this.callback;
        if (connectionCallback != null) {
            connectionCallback.onFailed(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyActive() {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportReceiver$DIbVR63884IIC42QZfM9IcakWNA
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportReceiver.this.lambda$notifyActive$4$ConnectionTransportReceiver();
            }
        });
    }

    void notifyConnected() {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportReceiver$3aFOXrFps3h9EhyMnpHQV3RgK0Q
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportReceiver.this.lambda$notifyConnected$0$ConnectionTransportReceiver();
            }
        });
    }

    void notifyDisconnected() {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportReceiver$K6EnMckabCOvC2RAwvpX3B3Ltws
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportReceiver.this.lambda$notifyDisconnected$2$ConnectionTransportReceiver();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyIdle() {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportReceiver$XcCk2LW8HTjFq-sYBD-5I6M-N-4
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportReceiver.this.lambda$notifyIdle$3$ConnectionTransportReceiver();
            }
        });
    }

    void notifyTerminated(final Throwable th) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$ConnectionTransportReceiver$BsuvEXLfgQFiSd1ObC7YF4KWWUE
            @Override // java.lang.Runnable
            public final void run() {
                ConnectionTransportReceiver.this.lambda$notifyTerminated$1$ConnectionTransportReceiver(th);
            }
        });
    }

    public void open() {
        if (!this.closed) {
            this.thread.start();
            return;
        }
        throw new IllegalStateException("Receiver is closed");
    }

    @VisibleForTesting
    boolean peekIsThreadInterruptedForTesting() {
        ReceiverThread receiverThread = this.thread;
        if (receiverThread != null) {
            return receiverThread.isInterrupted();
        }
        throw new IllegalStateException("No thread");
    }

    public void setReceiver(TransportReceiver transportReceiver) {
        Preconditions.notNull(transportReceiver, Constants.BUNDLE_RECEIVER_TAG);
        this.thread.setReceiver(transportReceiver);
    }
}
