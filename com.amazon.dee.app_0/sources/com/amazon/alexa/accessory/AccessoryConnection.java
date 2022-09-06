package com.amazon.alexa.accessory;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.crypto.CryptoBundleProvider;
import com.amazon.alexa.accessory.crypto.UnsupportedCryptoBundleProvider;
import com.amazon.alexa.accessory.internal.connection.ConnectionCallback;
import com.amazon.alexa.accessory.internal.connection.ConnectionTransportDispatcher;
import com.amazon.alexa.accessory.internal.connection.ConnectionTransportReceiver;
import com.amazon.alexa.accessory.internal.connection.DelegateConnectionCallback;
import com.amazon.alexa.accessory.internal.connection.SharedConnectionCallback;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.UIUtils;
import com.amazon.alexa.accessory.io.DataSink;
import com.amazon.alexa.accessory.io.DataSource;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import com.amazon.alexa.accessory.transport.TransportFeaturesProvider;
import com.amazon.alexa.accessory.transport.TransportReceiver;
import com.amazon.alexa.accessory.transport.TransportVersion;
import com.amazon.alexa.accessory.transport.codecs.TransportCodec;
import com.amazon.alexa.accessory.transport.codecs.V2.V2TransportCodec;
import com.amazon.alexa.accessory.transport.codecs.V3.V3TransactionDataDecryptor;
import com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec;
import com.amazon.alexa.accessory.transport.codecs.muffin.MuffinTransportCodec;
import com.amazon.alexa.accessory.transport.queue.PrioritizedTransportQueue;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.deecomms.common.Constants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class AccessoryConnection implements Closeable {
    private static final long KEEP_ALIVE_INTERVAL = 3000;
    private static final int STATE_CLOSED = 3;
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_IDLE = 0;
    private final Accessory accessory;
    private final AccessorySessionOptions accessorySessionOptions;
    private final ConnectionTransportDispatcher dispatcher;
    private final FeatureChecker featureChecker;
    private final CryptoBundleProvider keyProvider;
    private final List<ConnectionListener> listenerList;
    private final PrioritizedTransportQueue queue;
    private final ConnectionTransportReceiver receiver;
    private int state;
    private final TransportFeaturesProvider transportFeaturesProvider;
    private TransportVersion transportVersion;

    /* loaded from: classes.dex */
    static final class ConnectionActivityTracker {
        private final Runnable action;
        private final Handler handler;
        private boolean inputActive;
        private boolean outputActive;

        public ConnectionActivityTracker(Handler handler, Runnable runnable) {
            this.handler = handler;
            this.action = runnable;
        }

        private void update(boolean z, boolean z2) {
            this.inputActive = z;
            this.outputActive = z2;
            this.handler.removeCallbacks(this.action);
            if (!z || z2) {
                return;
            }
            this.handler.postDelayed(this.action, 1000L);
        }

        public void activeInput() {
            update(true, this.outputActive);
        }

        public void activeOutput() {
            update(this.inputActive, true);
        }

        public void idleInput() {
            update(false, this.outputActive);
        }

        public void idleOutput() {
            update(this.inputActive, false);
        }
    }

    /* loaded from: classes.dex */
    public interface ConnectionListener {
        void onConnectionClosed(AccessoryConnection accessoryConnection);

        void onConnectionError(AccessoryConnection accessoryConnection, Throwable th);

        void onConnectionKeepAlive(AccessoryConnection accessoryConnection);

        void onConnectionOpened(AccessoryConnection accessoryConnection);
    }

    /* loaded from: classes.dex */
    public static class SimpleConnectionListener implements ConnectionListener {
        @Override // com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
        public void onConnectionClosed(AccessoryConnection accessoryConnection) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
        public void onConnectionError(AccessoryConnection accessoryConnection, Throwable th) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
        public void onConnectionKeepAlive(AccessoryConnection accessoryConnection) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
        public void onConnectionOpened(AccessoryConnection accessoryConnection) {
        }
    }

    /* loaded from: classes.dex */
    public static final class UnsupportedTransportVersionException extends IOException {
        private final TransportVersion transportVersion;

        public UnsupportedTransportVersionException(TransportVersion transportVersion) {
            super("Unsupported transport version " + transportVersion);
            this.transportVersion = transportVersion;
        }

        public TransportVersion getTransportVersion() {
            return this.transportVersion;
        }
    }

    public AccessoryConnection(AccessoryTransport accessoryTransport, TransportVersion transportVersion, CryptoBundleProvider cryptoBundleProvider, TransportFeaturesProvider transportFeaturesProvider, AccessorySessionOptions accessorySessionOptions, FeatureChecker featureChecker) {
        Preconditions.notNull(accessoryTransport, "transport");
        this.transportVersion = transportVersion;
        this.keyProvider = cryptoBundleProvider;
        this.transportFeaturesProvider = transportFeaturesProvider;
        this.accessorySessionOptions = accessorySessionOptions;
        this.featureChecker = featureChecker;
        this.queue = new PrioritizedTransportQueue(accessoryTransport, new TransportCodec.Factory() { // from class: com.amazon.alexa.accessory.-$$Lambda$AccessoryConnection$dzbiMX-KTWF853BqzdHHuxVunm8
            @Override // com.amazon.alexa.accessory.transport.codecs.TransportCodec.Factory
            public final TransportCodec create(AccessoryTransport accessoryTransport2) {
                TransportCodec makeCodec;
                makeCodec = AccessoryConnection.this.makeCodec(accessoryTransport2);
                return makeCodec;
            }
        });
        this.accessory = accessoryTransport.getAccessory();
        this.state = 0;
        this.listenerList = new ArrayList();
        Handler handler = new Handler(Looper.getMainLooper());
        final ConnectionActivityTracker connectionActivityTracker = new ConnectionActivityTracker(handler, new Runnable() { // from class: com.amazon.alexa.accessory.-$$Lambda$XLPGVK0ryM4kGaBELM9EMF6cqkQ
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryConnection.this.notifyConnectionKeepAlive();
            }
        });
        SharedConnectionCallback sharedConnectionCallback = new SharedConnectionCallback(2, new ConnectionCallback() { // from class: com.amazon.alexa.accessory.AccessoryConnection.1
            @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
            public void onActive() {
                throw new IllegalStateException("Shall never be called!");
            }

            @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
            public void onConnected() {
                AccessoryConnection.this.state = 2;
                AccessoryConnection.this.notifyConnectionOpened();
            }

            @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
            public void onDisconnected() {
                AccessoryConnection.this.state = 3;
                AccessoryConnection.this.closeResources();
                AccessoryConnection.this.notifyConnectionClosed();
            }

            @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
            public void onFailed(Throwable th) {
                AccessoryConnection.this.state = 3;
                AccessoryConnection.this.closeResources();
                AccessoryConnection.this.notifyConnectionError(th);
            }

            @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
            public void onIdle() {
                throw new IllegalStateException("Shall never be called!");
            }
        });
        this.dispatcher = new ConnectionTransportDispatcher(this.accessory, this.queue, handler, 3000L, new DelegateConnectionCallback(sharedConnectionCallback) { // from class: com.amazon.alexa.accessory.AccessoryConnection.2
            @Override // com.amazon.alexa.accessory.internal.connection.DelegateConnectionCallback, com.amazon.alexa.accessory.internal.connection.ConnectionCallback
            public void onActive() {
                connectionActivityTracker.activeOutput();
            }

            @Override // com.amazon.alexa.accessory.internal.connection.DelegateConnectionCallback, com.amazon.alexa.accessory.internal.connection.ConnectionCallback
            public void onIdle() {
                connectionActivityTracker.idleOutput();
            }
        });
        this.receiver = new ConnectionTransportReceiver(this.accessory, this.queue, handler, 3000L, new DelegateConnectionCallback(sharedConnectionCallback) { // from class: com.amazon.alexa.accessory.AccessoryConnection.3
            @Override // com.amazon.alexa.accessory.internal.connection.DelegateConnectionCallback, com.amazon.alexa.accessory.internal.connection.ConnectionCallback
            public void onActive() {
                connectionActivityTracker.activeInput();
            }

            @Override // com.amazon.alexa.accessory.internal.connection.DelegateConnectionCallback, com.amazon.alexa.accessory.internal.connection.ConnectionCallback
            public void onIdle() {
                connectionActivityTracker.idleInput();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TransportCodec makeCodec(AccessoryTransport accessoryTransport) throws IOException {
        TransportVersion requireVersion = requireVersion(accessoryTransport);
        int major = requireVersion.getMajor();
        if (major == 1) {
            Logger.d("V1 transport version codec is used for connection with %s", this.accessory);
            return new MuffinTransportCodec(accessoryTransport);
        } else if (major == 2) {
            Logger.d("V2 transport version codec is used for connection with %s", this.accessory);
            return new V2TransportCodec(accessoryTransport, requireVersion.getMaxTransactionSize(), requireVersion.getPacketFragmentSize());
        } else if (major == 3) {
            Logger.d("V3 transport version codec is used for connection with %s", this.accessory);
            return new V3TransportCodec(accessoryTransport, requireVersion.getMaxTransactionSize(), requireVersion.getPacketFragmentSize(), requireVersion.getRequiredSupport(), new V3TransactionDataDecryptor(this.keyProvider));
        } else {
            throw new UnsupportedTransportVersionException(requireVersion);
        }
    }

    private synchronized TransportVersion requireVersion(AccessoryTransport accessoryTransport) throws IOException {
        if (this.transportVersion != null) {
            return this.transportVersion;
        }
        this.transportVersion = TransportVersion.read(new DataSource(accessoryTransport.source()));
        if (this.featureChecker.hasAccess(AccessoryFeature.VERSION_NOTIFICATION_RESPONSE)) {
            TransportVersion.sendTransportVersionResponse(this.transportVersion, new DataSink(accessoryTransport.sink()), this.accessorySessionOptions);
        }
        if (this.transportFeaturesProvider != null) {
            this.transportFeaturesProvider.provideTransportFeatures(this.transportVersion.getRequiredSupport());
        }
        return this.transportVersion;
    }

    public void addConnectionListener(ConnectionListener connectionListener) {
        Preconditions.mainThread();
        Preconditions.notNull(connectionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listenerList.add(connectionListener);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.state == 3) {
            return;
        }
        this.state = 3;
        closeResources();
    }

    void closeResources() {
        this.receiver.close();
        this.dispatcher.close();
        this.queue.dispose();
    }

    public Accessory getAccessory() {
        return this.accessory;
    }

    public TransportDispatcher getDispatcher() {
        return this.queue;
    }

    public TransportVersion getTransportVersion() {
        return this.transportVersion;
    }

    public boolean isClosed() {
        return this.state == 3;
    }

    public boolean isConnected() {
        return this.state == 2;
    }

    public boolean isConnecting() {
        return this.state == 1;
    }

    public /* synthetic */ void lambda$notifyConnectionClosed$2$AccessoryConnection() {
        for (int size = this.listenerList.size() - 1; size >= 0; size--) {
            this.listenerList.get(size).onConnectionClosed(this);
        }
    }

    public /* synthetic */ void lambda$notifyConnectionError$0$AccessoryConnection(Throwable th) {
        for (int size = this.listenerList.size() - 1; size >= 0; size--) {
            this.listenerList.get(size).onConnectionError(this, th);
        }
    }

    public /* synthetic */ void lambda$notifyConnectionKeepAlive$3$AccessoryConnection() {
        for (int size = this.listenerList.size() - 1; size >= 0; size--) {
            this.listenerList.get(size).onConnectionKeepAlive(this);
        }
    }

    public /* synthetic */ void lambda$notifyConnectionOpened$1$AccessoryConnection() {
        for (int size = this.listenerList.size() - 1; size >= 0; size--) {
            this.listenerList.get(size).onConnectionOpened(this);
        }
    }

    void notifyConnectionClosed() {
        UIUtils.schedule(new Runnable() { // from class: com.amazon.alexa.accessory.-$$Lambda$AccessoryConnection$PE-Rm9JuZo02q6GUjEMe-wWpa00
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryConnection.this.lambda$notifyConnectionClosed$2$AccessoryConnection();
            }
        });
    }

    void notifyConnectionError(final Throwable th) {
        UIUtils.schedule(new Runnable() { // from class: com.amazon.alexa.accessory.-$$Lambda$AccessoryConnection$fqV-MdU8a-paDUG6UNJZcGYiOdM
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryConnection.this.lambda$notifyConnectionError$0$AccessoryConnection(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyConnectionKeepAlive() {
        UIUtils.schedule(new Runnable() { // from class: com.amazon.alexa.accessory.-$$Lambda$AccessoryConnection$GhXtex-24KFgGScza_jGdmI_dIQ
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryConnection.this.lambda$notifyConnectionKeepAlive$3$AccessoryConnection();
            }
        });
    }

    void notifyConnectionOpened() {
        UIUtils.schedule(new Runnable() { // from class: com.amazon.alexa.accessory.-$$Lambda$AccessoryConnection$JKMzUpEbjpvN4ABL-ta6nIczfHo
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryConnection.this.lambda$notifyConnectionOpened$1$AccessoryConnection();
            }
        });
    }

    public void open() {
        if (this.state == 0) {
            this.state = 1;
            this.receiver.open();
            this.dispatcher.open();
            return;
        }
        throw new IllegalStateException("Connection was already opened or terminated!");
    }

    public void removeConnectionListener(ConnectionListener connectionListener) {
        Preconditions.mainThread();
        Preconditions.notNull(connectionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listenerList.remove(connectionListener);
    }

    public void setReceiver(TransportReceiver transportReceiver) {
        Preconditions.notNull(transportReceiver, Constants.BUNDLE_RECEIVER_TAG);
        this.receiver.setReceiver(transportReceiver);
    }

    public AccessoryConnection(AccessoryTransport accessoryTransport, TransportFeaturesProvider transportFeaturesProvider, AccessorySessionOptions accessorySessionOptions, FeatureChecker featureChecker) {
        this(accessoryTransport, null, new UnsupportedCryptoBundleProvider(), transportFeaturesProvider, accessorySessionOptions, featureChecker);
    }
}
