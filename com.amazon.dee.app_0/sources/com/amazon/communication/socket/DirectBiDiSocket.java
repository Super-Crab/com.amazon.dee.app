package com.amazon.communication.socket;

import amazon.communication.Message;
import amazon.communication.authentication.RequestContext;
import amazon.communication.authentication.RequestSigner;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.ExplicitServiceIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.communication.ByteBufferBackedMessage;
import com.amazon.communication.NativeSocket;
import com.amazon.communication.PowerManagerWrapper;
import com.amazon.communication.ProtocolException;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.ProtocolHandlerFactory;
import com.amazon.communication.ProtocolHandlerManager;
import com.amazon.communication.ThreadName;
import com.amazon.communication.TuningFailedException;
import com.amazon.communication.TuningMessageParser;
import com.amazon.communication.TuningProtocolHandler;
import com.amazon.communication.TuningProtocolParameters;
import com.amazon.communication.WebSocketClientByteBufferChainHandler;
import com.amazon.communication.WebSocketClientQueuedByteBufferChainHandler;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.identity.ServiceUniqueEndpointIdentifier;
import com.amazon.communication.identity.UniqueEndpointIdentifier;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SelectionKeyChangeQueue;
import com.amazon.communication.socket.ssl.SslSocketChannel;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseReason;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.communication.websocket.WebSocketClient;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.framework.StreamCodec;
import com.dp.utils.FailFast;
import com.dp.utils.ThreadGuard;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
/* loaded from: classes12.dex */
public class DirectBiDiSocket extends ProtocolSocketBase implements WebSocketClient.WebSocketListener, IncompleteIoListener, SupportsLocking {
    public static final String CONNECT_REASON_HEADER = "x-dp-reason";
    private static final int DEFAULT_PORT = 80;
    private static final String METRICS_SOURCE = "DirectBiDiSocket";
    public static final String PURPOSE_HEADER_NAME = "x-dp-tcomm-purpose";
    protected static final int SELECTION_KEY_OP_ALL = 0;
    public static final String TCOMM_PATH = "/tcomm/";
    protected SelectionKeyChangeQueue mChangeQueue;
    private CloseDetail mCloseDetail;
    private CloseReason mCloseReason;
    protected final CloseSocketCallable mCloseSocketCallable;
    protected ConnectReason mConnectReason;
    private final int mDelayCloseTimeInMillis;
    private final ReentrantLock mExternalLock;
    protected HandleDataCallable mHandleDataCallable;
    protected HandleTuningCallable mHandleTuningCallable;
    protected String mHttpMethodForWebSocketUpgrade;
    private final int mMessageQueueSize;
    private final ProtocolSocketSingletonCallable mNotifyStateChangeCallable;
    private final PowerManagerWrapper mPowerManager;
    protected ProtocolHandler mProtocolHandler;
    private final ProtocolHandlerManager mProtocolHandlerManager;
    private final ReentrantLock mRefCountLock;
    private final RequestSigner mRequestSigner;
    private final boolean mReuseSocketOnIncomingData;
    private final SelectorProvider mSelectorProvider;
    protected String mServiceName;
    private String mServiceNameForMetrics;
    protected SocketChannel mSocketChannel;
    private volatile SocketConnectionState mSocketConnectionState;
    private final int mSocketIdentifier;
    private final SocketUsageWriter mSocketUsageWriter;
    private final SSLContext mSslContext;
    private final StreamCodec mStreamCodec;
    private final TryReuseOrCallCloseCallable mTryReuseOrCallCloseWorkable;
    private TuningProtocolHandler mTuningHandler;
    private final String mTuningHeaderValue;
    protected UniqueEndpointIdentifier mUniqueEndpointIdentifier;
    protected URI mUri;
    protected final boolean mUseCompression;
    protected final boolean mUseSecureConnection;
    private final HostnameVerifier mVerifier;
    protected WebSocketClient mWebSocket;
    private static final DPLogger log = new DPLogger("TComm.DirectBiDiSocket");
    private static AtomicInteger sConnectionCounter = new AtomicInteger();
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    /* renamed from: com.amazon.communication.socket.DirectBiDiSocket$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$socket$DirectBiDiSocket$SocketConnectionState = new int[SocketConnectionState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$socket$DirectBiDiSocket$SocketConnectionState[SocketConnectionState.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$DirectBiDiSocket$SocketConnectionState[SocketConnectionState.CHANNEL_DISCONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$DirectBiDiSocket$SocketConnectionState[SocketConnectionState.CHANNEL_DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$DirectBiDiSocket$SocketConnectionState[SocketConnectionState.CHANNEL_CONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$DirectBiDiSocket$SocketConnectionState[SocketConnectionState.SSL_CONNECTING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$DirectBiDiSocket$SocketConnectionState[SocketConnectionState.WEBSOCKET_CONNECTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$DirectBiDiSocket$SocketConnectionState[SocketConnectionState.TUNING_FINISHED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$DirectBiDiSocket$SocketConnectionState[SocketConnectionState.NOT_CONNECTED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public final class FinishUpgradeToWebSocketCallable extends ProtocolSocketSingletonCallable {
        public FinishUpgradeToWebSocketCallable(DirectBiDiSocket directBiDiSocket, WorkExecutor workExecutor) {
            super(directBiDiSocket, workExecutor);
        }

        @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
        public void actualCall() throws Exception {
            ThreadGuard.ensureThreadPrefix(ThreadName.HANDLER);
            DirectBiDiSocket.log.verbose("FinishUpgradeToWebSocket", "finishing upgrade", new Object[0]);
            try {
                if (!DirectBiDiSocket.this.mWebSocket.finishUpgrade()) {
                    DirectBiDiSocket.log.warn("FinishUpgradeToWebSocket", "received invalid WebSocket handshake", "socket", DirectBiDiSocket.this);
                    DirectBiDiSocket.this.close(new CloseDetail(CloseStatusCodes.TUNING_FAILED, "Received invalid WebSocket handshake"));
                } else {
                    DirectBiDiSocket.this.setSocketConnectionState(SocketConnectionState.WEBSOCKET_CONNECTED);
                    if (DirectBiDiSocket.this.mWebSocket.getDataBytesAvailable() > 0) {
                        DirectBiDiSocket.log.verbose("FinishUpgradeToWebSocket", "looks like we already have the tuning info as well", new Object[0]);
                        DirectBiDiSocket.this.handleTuning();
                    } else {
                        DirectBiDiSocket.log.verbose("FinishUpgradeToWebSocket", "upgrade succeeded, selecting for READ now", new Object[0]);
                        DirectBiDiSocket.this.mChangeQueue.queueChange(DirectBiDiSocket.this, SelectionKeyChangeQueue.SelectionKeyOperation.ADD, 1);
                    }
                }
            } catch (IOException e) {
                DirectBiDiSocket.log.error("FinishUpgradeToWebSocket", "exception while finishing WebSocket upgrade", e);
                DirectBiDiSocket.this.close(new CloseDetail(CloseStatusCodes.IO_ERROR, e.toString()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public enum SocketConnectionState {
        NOT_CONNECTED,
        CONNECTING,
        SSL_CONNECTING,
        CHANNEL_CONNECTED,
        WEBSOCKET_CONNECTED,
        TUNING_FINISHED,
        CHANNEL_DISCONNECTING,
        CHANNEL_DISCONNECTED
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class UpgradeToWebSocketCallable extends ProtocolSocketSingletonCallable {
        public UpgradeToWebSocketCallable(DirectBiDiSocket directBiDiSocket, WorkExecutor workExecutor) {
            super(directBiDiSocket, workExecutor);
        }

        /* JADX WARN: Removed duplicated region for block: B:52:0x01c0  */
        @Override // com.amazon.communication.socket.ProtocolSocketSingletonCallable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void actualCall() throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 468
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.socket.DirectBiDiSocket.UpgradeToWebSocketCallable.actualCall():void");
        }
    }

    public DirectBiDiSocket(EndpointIdentity endpointIdentity, WorkExecutor workExecutor, ProtocolHandlerManager protocolHandlerManager, Set<String> set, StreamCodec streamCodec, RequestSigner requestSigner, SelectionKeyChangeQueue selectionKeyChangeQueue, IdentityResolver identityResolver, SocketUsageWriter socketUsageWriter, SelectorProvider selectorProvider, Set<ProtocolSocket.ProtocolSocketAttribute> set2, int i, int i2, HostnameVerifier hostnameVerifier, SSLContext sSLContext, String str, String str2, boolean z, PowerManagerWrapper powerManagerWrapper) {
        this(endpointIdentity, workExecutor, protocolHandlerManager, set, streamCodec, requestSigner, selectionKeyChangeQueue, identityResolver, socketUsageWriter, selectorProvider, set2, i, i2, hostnameVerifier, sSLContext, z, powerManagerWrapper, Purpose.REGULAR);
        this.mServiceName = str;
        this.mServiceNameForMetrics = str;
        this.mHttpMethodForWebSocketUpgrade = str2;
    }

    private void doFinishUpgradeToWebSocket() {
        this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.REMOVE, 1);
        new FinishUpgradeToWebSocketCallable(this, this.mWorkExecutor).enqueue();
    }

    private void doUpgradeToWebSocket() {
        this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.REMOVE, 8);
        new UpgradeToWebSocketCallable(this, this.mWorkExecutor).enqueue();
    }

    private void finishSocketConnectAndUpgradeToWebSocket() {
        String str;
        this.mSocketUsageWriter.writeTimestamp(Measurements.COUNT_SOCKETS_OPENED_TO_ENDPOINT, this.mIdentity, GlobalTimeSource.INSTANCE.currentTimeMillis());
        setSocketConnectionState(SocketConnectionState.CHANNEL_CONNECTED);
        log.verbose("finishSocketConnectAndUpgradeToWebSocket", "set socketConnectionState to CONNECTED", new Object[0]);
        this.mNotifyStateChangeCallable.enqueue();
        String str2 = this.mServiceName;
        if (str2 != null && (str = this.mHttpMethodForWebSocketUpgrade) != null) {
            this.mWebSocket = new WebSocketClient(this.mSocketChannel, this, str2, str);
        } else {
            this.mWebSocket = new WebSocketClient(this.mSocketChannel, this);
        }
        if (this.mTuningHandler == null) {
            this.mTuningHandler = new TuningProtocolHandler(this, this.mStreamCodec, new WebSocketClientByteBufferChainHandler(this.mWebSocket));
        } else {
            log.info("finishSocketConnectAndUpgradeToWebSocket", "not creating a new TuningProtocolHandler because one already exists.", new Object[0]);
        }
        this.mHandleTuningCallable = new HandleTuningCallable(this, this.mWorkExecutor, this.mWebSocket, this.mTuningHandler, this.mChangeQueue);
        doUpgradeToWebSocket();
    }

    private int getInterestedOperations(SelectionKey selectionKey, SelectionKeyChangeQueue.SelectionKeyOperation selectionKeyOperation, int i) {
        int i2;
        if (selectionKey != null) {
            i2 = selectionKey.interestOps();
            log.debug("getResultingInterestedOps", "got existing operations", "existingInterestedOps", Integer.valueOf(i2));
        } else {
            log.debug("getResultingInterestedOps", "no existing interested operations", new Object[0]);
            i2 = 0;
        }
        if (selectionKeyOperation.equals(SelectionKeyChangeQueue.SelectionKeyOperation.ADD)) {
            return i2 | i;
        }
        if (!selectionKeyOperation.equals(SelectionKeyChangeQueue.SelectionKeyOperation.REMOVE)) {
            throw new IllegalArgumentException("Unknown SelectionKeyOperation");
        }
        return i2 & (~i);
    }

    private void handleClose() throws IOException {
        log.verbose("handleClose", "begin", new Object[0]);
        this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.REMOVE, 1);
        if (this.mWebSocket != null) {
            log.verbose("handleClose", "finishing the WebSocket close", new Object[0]);
            this.mWebSocket.finishClose();
        }
    }

    private void handleSslHandshake() throws IOException {
        FailFast.expectTrue(this.mUseSecureConnection, "SSL handshake only expected for secure connections");
        if (((SslSocketChannel) this.mSocketChannel).continueHandshake()) {
            finishSocketConnectAndUpgradeToWebSocket();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTuning() {
        this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.REMOVE, 1);
        this.mHandleTuningCallable.enqueue();
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void close(CloseDetail closeDetail) {
        boolean z;
        log.verbose("close", "closing connection", "socket", this, MessagingControllerConstant.MESSAGING_CONTROLLER_DETAIL_KEY, closeDetail);
        synchronized (this) {
            SocketConnectionState socketConnectionState = getSocketConnectionState();
            z = (socketConnectionState == SocketConnectionState.CHANNEL_DISCONNECTING || socketConnectionState == SocketConnectionState.CHANNEL_DISCONNECTED || socketConnectionState == SocketConnectionState.NOT_CONNECTED) ? false : true;
            if (z) {
                log.verbose("close", "setting connection state to CHANNEL_DISCONNECTING", "socket", this);
                this.mCloseDetail = closeDetail;
                this.mCloseReason = CloseReason.CLOSE_CALLER;
                setSocketConnectionState(SocketConnectionState.CHANNEL_DISCONNECTING);
            } else {
                log.verbose("close", "not closing anything because it's not necessary", "state", socketConnectionState);
            }
        }
        if (z) {
            this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.REMOVE, 0);
            this.mNotifyStateChangeCallable.enqueue();
            this.mCloseSocketCallable.setCloseReason(this.mCloseReason);
            this.mCloseSocketCallable.setCloseDetail(this.mCloseDetail);
            this.mCloseSocketCallable.enqueue();
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public CloseDetail closeDetail() {
        return this.mCloseDetail;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public CloseReason closeReason() {
        return this.mCloseReason;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void closeSocketChannel(CloseReason closeReason, CloseDetail closeDetail) {
        CloseReason closeReason2;
        CloseDetail closeDetail2;
        log.verbose("closeSocketChannel", "closing socket channel", "closeReason", closeReason, "closeDetail", closeDetail);
        if (this.mWebSocket == null) {
            try {
                if (this.mSocketChannel != null) {
                    this.mSocketChannel.close();
                }
            } catch (IOException e) {
                log.warn("closeSocketChannel", "IOException when closing socket channel", "socket", this, e);
                if (closeReason == null || closeDetail == null) {
                    closeReason2 = CloseReason.CLOSE_ERROR;
                    closeDetail2 = new CloseDetail(CloseStatusCodes.CLIENT_ERROR, "Attempting to close a connection that is not yet open");
                }
            }
            if (closeReason == null || closeDetail == null) {
                closeReason2 = CloseReason.CLOSE_ERROR;
                closeDetail2 = new CloseDetail(CloseStatusCodes.CLIENT_ERROR, "Attempting to close a connection that is not yet open");
                onClosed(closeReason2, closeDetail2);
            }
            onClosed(closeReason, closeDetail);
        } else {
            try {
                if (this.mWebSocket.isClosed()) {
                    log.info("closeSocketChannel", "looks like WebSocket connection didn't finish closing after initiating the close. Finishing it explicitly", new Object[0]);
                    this.mWebSocket.finishClose();
                } else {
                    log.verbose("closeSocketChannel", "initiating the WebSocket close", new Object[0]);
                    this.mWebSocket.initiateClose();
                    log.verbose("closeSocketChannel", "registering for read to receive the peer's response", new Object[0]);
                    this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.ADD, 1);
                    log.verbose("closeSocketChannel", "change queued", new Object[0]);
                    log.verbose("closeSocketChannel", "attempted to enqueue close callable", "enqueued", Boolean.valueOf(this.mCloseSocketCallable.enqueueAfter(2000L)));
                }
            } catch (IOException e2) {
                log.warn("closeSocketChannel", "IOException when closing websocket", "socket", this, e2);
                try {
                    if (this.mSocketChannel != null) {
                        this.mSocketChannel.close();
                    }
                } catch (IOException e3) {
                    log.warn("closeSocketChannel", "IOException when closing socket channel", "socket", this, e3);
                }
                onClosed(CloseReason.CLOSE_ERROR, new CloseDetail(CloseStatusCodes.IO_ERROR, "Error shutting down websocket"));
            }
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public void enableKeepAlive(int i, int i2, int i3) throws SocketException {
        SocketChannel socketChannel = this.mSocketChannel;
        if (socketChannel != null) {
            NativeSocket.enableKeepAlive(socketChannel.socket(), i, i2, i3);
            return;
        }
        throw new IllegalStateException("Socket must be in connected state");
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public ConnectReason getConnectReason() {
        return this.mConnectReason;
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public String getFqdn() {
        return this.mUri.toString();
    }

    protected synchronized InetAddress getInetAddresByName(String str) throws NoRouteToEndpointException {
        try {
            try {
                log.verbose("getInetAddresByName", "resolving IP address for URI", "host", str);
            } catch (UnknownHostException e) {
                log.error("getInetAddresByName", "UnknownHostException when resolving IP address for host", "host", str);
                throw new NoRouteToEndpointException(e);
            }
        } catch (SecurityException e2) {
            log.error("getInetAddresByName", "SecurityException when resolving IP address for host", "host", str);
            throw new NoRouteToEndpointException(e2);
        }
        return InetAddress.getByName(str);
    }

    @Override // com.amazon.communication.socket.SupportsLocking
    public Lock getLock() {
        return this.mExternalLock;
    }

    protected RequestContext getRequestContext() {
        return null;
    }

    public SocketConnectionState getSocketConnectionState() {
        return this.mSocketConnectionState;
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public UniqueEndpointIdentifier getUniqueEndpointIdentifier() {
        return this.mUniqueEndpointIdentifier;
    }

    protected void handleData() throws IOException {
        this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.REMOVE, 1);
        this.mHandleDataCallable.enqueue();
    }

    public synchronized void handleSelectionKey(SelectionKey selectionKey) throws IOException {
        log.verbose("handleSelectionKey", "beginning execution", "key.interestOps", Integer.valueOf(selectionKey.interestOps()), "socketConnectionState", getSocketConnectionState(), "mIdentity", this.mIdentity);
        ThreadGuard.ensureThreadPrefix(ThreadName.SELECTOR);
        if ((this.mUseSecureConnection && !((SslSocketChannel) this.mSocketChannel).isValidChannel(selectionKey.channel())) || (!this.mUseSecureConnection && selectionKey.channel() != this.mSocketChannel)) {
            throw new IllegalArgumentException("handleSelectionKey was called on the wrong connection");
        }
        if (selectionKey.isValid() && selectionKey.isConnectable()) {
            SocketConnectionState socketConnectionState = getSocketConnectionState();
            int ordinal = socketConnectionState.ordinal();
            if (ordinal != 1) {
                if (ordinal == 6) {
                    log.warn("handleSelectionKey", "tried to open disconnecting socket.", "socketConnectionState", socketConnectionState, "mUseSecureConnection", Boolean.valueOf(this.mUseSecureConnection));
                    this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.REMOVE, 8);
                } else if (ordinal == 7) {
                    log.error("handleSelectionKey", "tried to open disconnected socket.", "socketConnectionState", socketConnectionState, "mUseSecureConnection", Boolean.valueOf(this.mUseSecureConnection));
                    FailFast.expectFalse(this.mSocketChannel.isConnected(), "Never expect SocketChannel connected while in CHANNEL_DISCONNECTED state");
                } else {
                    throw new IOException("isConnectable() flagged when SocketChannel already connected. state = " + socketConnectionState);
                }
            } else if (this.mSocketChannel.isConnectionPending()) {
                if (this.mSocketChannel.finishConnect()) {
                    if (this.mUseSecureConnection && !((SslSocketChannel) this.mSocketChannel).isSslHandshakeComplete()) {
                        setSocketConnectionState(SocketConnectionState.SSL_CONNECTING);
                    } else {
                        finishSocketConnectAndUpgradeToWebSocket();
                    }
                } else {
                    throw new IllegalStateException("BUGBUG: Channel not connected yet, selector might spin");
                }
            }
        }
        if (selectionKey.isValid() && selectionKey.isReadable()) {
            int ordinal2 = getSocketConnectionState().ordinal();
            if (ordinal2 == 2) {
                handleSslHandshake();
            } else if (ordinal2 == 3) {
                doFinishUpgradeToWebSocket();
            } else if (ordinal2 == 4) {
                handleTuning();
            } else if (ordinal2 == 5) {
                handleData();
            } else if (ordinal2 == 6) {
                handleClose();
            } else {
                throw new IllegalStateException("Data received on unconnected/disconnected SocketChannel");
            }
        }
        if (selectionKey.isValid() && selectionKey.isWritable()) {
            FailFast.expectTrue(this.mUseSecureConnection, "Write registrations happen only for SslSocketChannel");
            this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.REMOVE, 4);
            try {
                ((SslSocketChannel) this.mSocketChannel).flushIntermediateWriteBuffer();
            } catch (IOException e) {
                log.error("handleSelectionKey", "flushIntermediateWriteBuffer failed with IOException", e);
                close(new CloseDetail(CloseStatusCodes.IO_ERROR, e.toString()));
            }
        }
        if (!selectionKey.isValid()) {
            log.debug("handleSelectionKey", "invalid key, closing socket", new Object[0]);
            close(new CloseDetail(CloseStatusCodes.SELECTION_KEY_INVALID, "Invalid selection key"));
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public boolean isConnectReasonSupported() {
        return true;
    }

    public void messageReceived() {
        if (this.mReuseSocketOnIncomingData) {
            this.mTryReuseOrCallCloseWorkable.tryReuseSocket();
        }
    }

    @Override // com.amazon.communication.websocket.WebSocketClient.WebSocketListener
    public void onClosed(CloseReason closeReason, CloseDetail closeDetail) {
        log.verbose("onClosed", "beginning execution", Settings.ListeningSettings.EXTRA_REASON, closeReason, "details", closeDetail, "socket", this);
        synchronized (this) {
            if (getSocketConnectionState() != SocketConnectionState.CHANNEL_DISCONNECTED) {
                setSocketConnectionState(SocketConnectionState.CHANNEL_DISCONNECTED);
            } else {
                log.error("onClosed", "unexpected state, socket already disconnected!", new Object[0]);
                throw new AssertionError("Bad socket state in onClosed()");
            }
        }
        this.mCloseDetail = closeDetail;
        this.mCloseReason = closeReason;
        this.mNotifyStateChangeCallable.enqueue();
        this.mWebSocket = null;
    }

    @Override // com.amazon.communication.socket.IncompleteIoListener
    public void onIncompleteRead() {
        this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.ADD, 1);
    }

    @Override // com.amazon.communication.socket.IncompleteIoListener
    public void onIncompleteWrite() {
        this.mChangeQueue.queueChange(this, SelectionKeyChangeQueue.SelectionKeyOperation.ADD, 4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onOpened() {
        this.mProtocolSocketStats.recordTimeEstablished();
        notifyStateChanged();
    }

    public void onSelectionKeyChange(Selector selector, SelectionKeyChangeQueue.SelectionKeyOperation selectionKeyOperation, int i) throws IllegalSocketStateException {
        SelectionKey keyFor;
        log.debug("onSelectionKeyChange", "beginning execution", "selector", selector, "operation", selectionKeyOperation, "operationSet", Integer.valueOf(i));
        if (selectionKeyOperation.equals(SelectionKeyChangeQueue.SelectionKeyOperation.ADD) && (i & 8) == 8 && getSocketConnectionState() != SocketConnectionState.CONNECTING) {
            log.warn("onSelectionKeyChange", "Invalid state for operation. OP_CONNECT when socketstate is not CONNECTING", new Object[0]);
            return;
        }
        try {
            if (this.mSocketChannel == null || !this.mSocketChannel.isOpen()) {
                return;
            }
            if (this.mUseSecureConnection) {
                keyFor = ((SslSocketChannel) this.mSocketChannel).keyForDelegate(selector);
            } else {
                keyFor = this.mSocketChannel.keyFor(selector);
            }
            if (keyFor != null && !keyFor.isValid()) {
                log.verbose("onSelectionkeyChange", "selectionKey is invalid", new Object[0]);
                return;
            }
            int interestedOperations = getInterestedOperations(keyFor, selectionKeyOperation, i);
            if (this.mUseSecureConnection) {
                log.verbose("onSelectionKeyChange", "registering delegate with SslSocketChannel", "interestedOps", Integer.valueOf(interestedOperations));
                ((SslSocketChannel) this.mSocketChannel).registerDelegate(selector, interestedOperations, this);
                return;
            }
            log.verbose("onSelectionKeyChange", "registering with non-ssl SocketChannel", "interestedOps", Integer.valueOf(interestedOperations));
            this.mSocketChannel.register(selector, interestedOperations, this);
        } catch (Exception e) {
            throw new IllegalSocketStateException(GeneratedOutlineSupport1.outline49("SocketChannel could not be registered with operations ", -1), e);
        }
    }

    public int read(ByteBuffer byteBuffer) throws ClosedChannelException, IOException, NotYetConnectedException {
        ThreadGuard.ensureThreadPrefix(ThreadName.HANDLER);
        if (getSocketConnectionState() == SocketConnectionState.TUNING_FINISHED) {
            int read = this.mWebSocket.read(byteBuffer);
            this.mProtocolSocketStats.recordTimeLastMessageReceived();
            return read;
        }
        throw new IllegalStateException("Connection is not yet in the connected state");
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public int release() {
        this.mRefCountLock.lock();
        try {
            int release = super.release();
            if (release == 0) {
                this.mTryReuseOrCallCloseWorkable.enqueueAfter(this.mDelayCloseTimeInMillis);
            }
            return release;
        } finally {
            this.mRefCountLock.unlock();
        }
    }

    public void resetHandleDataCallable() {
        this.mHandleDataCallable = new HandleDataCallable(this, this.mWorkExecutor, this.mWebSocket, this.mProtocolHandler, this.mChangeQueue, this.mPowerManager);
    }

    public void resetHandleTuningCallable() {
        this.mHandleTuningCallable = new HandleTuningCallable(this, this.mWorkExecutor, this.mWebSocket, this.mTuningHandler, this.mChangeQueue);
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public int retain() {
        this.mRefCountLock.lock();
        try {
            this.mTryReuseOrCallCloseWorkable.tryReuseSocket();
            return super.retain();
        } finally {
            this.mRefCountLock.unlock();
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void sendMessage(Message message, String str, int i) throws IOException {
        if (getSocketConnectionState() == SocketConnectionState.TUNING_FINISHED) {
            try {
                this.mProtocolHandler.encodeMessage(message, str, i);
                return;
            } catch (ProtocolException e) {
                log.error("sendMessage", "exception while encoding or sending data", "socket", this, e);
                return;
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Underlying socket channel was not in the correct state. Socket state : ");
        outline107.append(socketState());
        throw new IOException(outline107.toString());
    }

    public void setConnectReason(ConnectReason connectReason) {
        this.mConnectReason = connectReason;
    }

    public void setProtocolHandler(ProtocolHandler protocolHandler) {
        this.mProtocolHandler = protocolHandler;
    }

    public synchronized void setSocketConnectionState(SocketConnectionState socketConnectionState) {
        if (socketConnectionState == SocketConnectionState.TUNING_FINISHED) {
            SocketConnectionState socketConnectionState2 = SocketConnectionState.TUNING_FINISHED;
        }
        this.mSocketConnectionState = socketConnectionState;
    }

    public void setTuningHandler(TuningProtocolHandler tuningProtocolHandler) {
        this.mTuningHandler = tuningProtocolHandler;
    }

    public void setWebSocketClient(WebSocketClient webSocketClient) {
        this.mWebSocket = webSocketClient;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public ProtocolSocket.ProtocolSocketState socketState() {
        switch (getSocketConnectionState().ordinal()) {
            case 1:
            case 2:
            case 3:
            case 4:
                return ProtocolSocket.ProtocolSocketState.CONNECTING;
            case 5:
                return ProtocolSocket.ProtocolSocketState.CONNECTED;
            case 6:
                return ProtocolSocket.ProtocolSocketState.DISCONNECTING;
            case 7:
                return ProtocolSocket.ProtocolSocketState.DISCONNECTED;
            default:
                return ProtocolSocket.ProtocolSocketState.UNKNOWN;
        }
    }

    public synchronized void startConnection() throws SocketAcquisitionFailedException {
        if (getSocketConnectionState() == SocketConnectionState.NOT_CONNECTED) {
            setSocketConnectionState(SocketConnectionState.CONNECTING);
            this.mCloseDetail = null;
            int port = this.mUri.getPort();
            char c = 65535;
            if (port == -1) {
                port = 80;
            }
            try {
                log.verbose("startConnection", "starting connection to uri", "mUri", this.mUri);
                try {
                    log.verbose("startConnection", "creating a socket channel", "mUseSecureConnection", Boolean.valueOf(this.mUseSecureConnection));
                    if (this.mUseSecureConnection) {
                        this.mSocketChannel = this.mSelectorProvider.openSocketChannel();
                        this.mSocketChannel = SslSocketChannel.open(this.mSslContext, this.mSocketChannel, this.mUri.getHost(), port, this, this.mVerifier);
                    } else {
                        this.mSocketChannel = this.mSelectorProvider.openSocketChannel();
                    }
                    this.mSocketChannel.configureBlocking(false);
                    this.mSocketChannel.connect(new InetSocketAddress(getInetAddresByName(this.mUri.getHost()), port));
                    try {
                        log.verbose("startConnection", "initiated connection through socket", "mSocketChannel", this.mSocketChannel);
                        this.mProtocolSocketStats.recordTimeStartConnection();
                    } catch (UnresolvedAddressException e) {
                        e = e;
                        throw new NoRouteToEndpointException(e);
                    } catch (Exception e2) {
                        e = e2;
                        throw new SocketAcquisitionFailedException(e);
                    } catch (Throwable th) {
                        th = th;
                        c = 1;
                        if (c == 0) {
                            try {
                                if (this.mSocketChannel != null) {
                                    this.mSocketChannel.close();
                                }
                            } catch (IOException e3) {
                                log.error("startConnection", "exception when closing socket", "socket", this, e3);
                            }
                        }
                        throw th;
                    }
                } catch (UnresolvedAddressException e4) {
                    e = e4;
                } catch (Exception e5) {
                    e = e5;
                } catch (Throwable th2) {
                    th = th2;
                    c = 0;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            log.error("startConnection", "connection already started", new Object[0]);
            throw new IllegalStateException("[startConnection] Connection already started");
        }
    }

    public String toString() {
        return formatProtocolSocketString(String.valueOf(this.mSocketIdentifier), this.mUri, this.mSocketConnectionState.toString(), this.mPurpose);
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public void verifyTuningResult(ByteBufferBackedMessage byteBufferBackedMessage) throws TuningFailedException {
        log.verbose("verifyTuningResult", "beginning execution", "mIdentity", this.mIdentity);
        TuningMessageParser tuningMessageParser = new TuningMessageParser();
        InputStream inputStream = null;
        try {
            try {
                inputStream = byteBufferBackedMessage.getInputStream();
                TuningProtocolParameters parseProtocolParameters = tuningMessageParser.parseProtocolParameters(inputStream);
                log.verbose("verifyTuningResult", "received tuning parameters", "remoteProtocolParameters", parseProtocolParameters);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        log.warn("verifyTuningResult", "error closing message InputStream", e);
                    }
                }
                ProtocolHandlerFactory protocolHandlerFactory = this.mProtocolHandlerManager.getProtocolHandlerFactory(parseProtocolParameters.getProtocolName());
                synchronized (this) {
                    if (getSocketConnectionState() != SocketConnectionState.WEBSOCKET_CONNECTED) {
                        throw new TuningFailedException("Tuning cannot finish as websocket is not connection. State: " + getSocketConnectionState());
                    } else if (protocolHandlerFactory != null) {
                        this.mProtocolHandler = protocolHandlerFactory.makeProtocolHandler(new WebSocketClientQueuedByteBufferChainHandler(this.mWorkExecutor, this.mWebSocket, this, this.mMessageQueueSize), this.mWorkExecutor, this, this.mUseCompression, parseProtocolParameters.getParameters());
                        this.mHandleDataCallable = new HandleDataCallable(this, this.mWorkExecutor, this.mWebSocket, this.mProtocolHandler, this.mChangeQueue, this.mPowerManager);
                    } else {
                        throw new TuningFailedException("Tuning failure: protocol not supported by client: " + byteBufferBackedMessage);
                    }
                }
                try {
                    TuningProtocolParameters tuningProtocolParameters = new TuningProtocolParameters(protocolHandlerFactory.getProtocolName(), protocolHandlerFactory.getLocalProtocolParameters(this.mProtocolHandler));
                    log.verbose("verifyTuningResult", "local tuning parameters selected", "localTuningParameters", tuningProtocolParameters);
                    this.mTuningHandler.encodeMessage(tuningMessageParser.encodeProtocolParameters(tuningProtocolParameters), ProtocolHandler.MESSAGE_MESSAGE_TYPE, 0);
                } catch (ProtocolException e2) {
                    throw new TuningFailedException("Tuning protocol parameters can't be encoded", e2);
                } catch (IOException e3) {
                    throw new TuningFailedException("Tuning message can't be sent through websocket connection.", e3);
                }
            } catch (ProtocolException e4) {
                throw new TuningFailedException("Tuning protocol parameters can't be parsed", e4);
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    log.warn("verifyTuningResult", "error closing message InputStream", e5);
                }
            }
            throw th;
        }
    }

    @Deprecated
    public DirectBiDiSocket(EndpointIdentity endpointIdentity, WorkExecutor workExecutor, ProtocolHandlerManager protocolHandlerManager, Set<String> set, StreamCodec streamCodec, RequestSigner requestSigner, SelectionKeyChangeQueue selectionKeyChangeQueue, IdentityResolver identityResolver, SocketUsageWriter socketUsageWriter, SelectorProvider selectorProvider, Set<ProtocolSocket.ProtocolSocketAttribute> set2, int i, int i2, HostnameVerifier hostnameVerifier, SSLContext sSLContext, boolean z, PowerManagerWrapper powerManagerWrapper) {
        this(endpointIdentity, workExecutor, protocolHandlerManager, set, streamCodec, requestSigner, selectionKeyChangeQueue, identityResolver, socketUsageWriter, selectorProvider, set2, i, i2, hostnameVerifier, sSLContext, z, powerManagerWrapper, Purpose.REGULAR);
    }

    public DirectBiDiSocket(EndpointIdentity endpointIdentity, WorkExecutor workExecutor, ProtocolHandlerManager protocolHandlerManager, Set<String> set, StreamCodec streamCodec, RequestSigner requestSigner, SelectionKeyChangeQueue selectionKeyChangeQueue, IdentityResolver identityResolver, SocketUsageWriter socketUsageWriter, SelectorProvider selectorProvider, Set<ProtocolSocket.ProtocolSocketAttribute> set2, int i, int i2, HostnameVerifier hostnameVerifier, SSLContext sSLContext, boolean z, PowerManagerWrapper powerManagerWrapper, Purpose purpose) {
        this.mRefCountLock = new ReentrantLock();
        this.mExternalLock = new ReentrantLock();
        log.verbose("constructor", "creating new DirectBiDiSocket", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
        if (identityResolver != null) {
            if (!(endpointIdentity instanceof DeviceIdentity)) {
                this.mIdentity = endpointIdentity;
                this.mUri = null;
                this.mServiceName = null;
                this.mServiceNameForMetrics = MetricsName.NULL;
                this.mHttpMethodForWebSocketUpgrade = null;
                this.mReuseSocketOnIncomingData = z;
                this.mPowerManager = powerManagerWrapper;
                this.mPurpose = purpose;
                Set<ProtocolSocket.ProtocolSocketAttribute> set3 = set2 == null ? ProtocolSocket.ProtocolSocketAttribute.EMPTY_ATTRIBUTES : set2;
                this.mUseSecureConnection = set3.contains(ProtocolSocket.ProtocolSocketAttribute.SECURE);
                if (endpointIdentity instanceof ServiceIdentity) {
                    ServiceIdentity serviceIdentity = (ServiceIdentity) endpointIdentity;
                    this.mServiceNameForMetrics = serviceIdentity.getServiceName();
                    String hostname = serviceIdentity.getHostname();
                    IRServiceEndpoint.Scheme scheme = this.mUseSecureConnection ? IRServiceEndpoint.Scheme.WSS : IRServiceEndpoint.Scheme.WS;
                    if (!(serviceIdentity instanceof ExplicitServiceIdentity) && hostname != null && !hostname.isEmpty()) {
                        this.mUri = URI.create(scheme.toString() + "://" + hostname + ":" + serviceIdentity.getPort() + "/" + TCOMM_PATH);
                        this.mUniqueEndpointIdentifier = new ServiceUniqueEndpointIdentifier(endpointIdentity);
                        log.verbose("constructor", "Creating S2S URI", "uri", this.mUri);
                    } else {
                        IRServiceEndpoint resolveServiceEndpoint = identityResolver.resolveServiceEndpoint(serviceIdentity, this.mPurpose);
                        log.verbose("constructor", "Resolving service endpoint", "irse", resolveServiceEndpoint);
                        if (resolveServiceEndpoint != null) {
                            this.mUri = URI.create(resolveServiceEndpoint.toResolvedUri(scheme) + TCOMM_PATH);
                            this.mUniqueEndpointIdentifier = new ServiceUniqueEndpointIdentifier(resolveServiceEndpoint);
                            log.verbose("constructor", "Creating URI", "uri", this.mUri);
                        } else {
                            throw new IllegalArgumentException("Invalid EndpointIdentity: Not a valid service- " + endpointIdentity);
                        }
                    }
                } else {
                    this.mUri = URI.create(endpointIdentity.toString());
                    this.mUniqueEndpointIdentifier = new ServiceUniqueEndpointIdentifier(endpointIdentity);
                }
                log.debug("constructor", "made URI", "mUri", this.mUri);
                this.mTuningHeaderValue = TuningMessageParser.encodeProtocolListString(set);
                this.mWebSocket = null;
                this.mSocketChannel = null;
                setSocketConnectionState(SocketConnectionState.NOT_CONNECTED);
                this.mWorkExecutor = workExecutor;
                this.mChangeQueue = selectionKeyChangeQueue;
                this.mProtocolHandlerManager = protocolHandlerManager;
                this.mStreamCodec = streamCodec;
                this.mRequestSigner = requestSigner;
                this.mSocketIdentifier = sConnectionCounter.incrementAndGet();
                this.mMessageQueueSize = i;
                this.mDelayCloseTimeInMillis = i2;
                this.mSocketUsageWriter = socketUsageWriter;
                this.mSelectorProvider = selectorProvider;
                this.mVerifier = hostnameVerifier;
                this.mSslContext = sSLContext;
                this.mUseCompression = set3.contains(ProtocolSocket.ProtocolSocketAttribute.COMPRESSED);
                this.mNotifyStateChangeCallable = new NotifyStateChangeCallable(this, this.mWorkExecutor);
                this.mCloseSocketCallable = new CloseSocketCallable(this, this.mWorkExecutor, this.mSocketUsageWriter);
                this.mTryReuseOrCallCloseWorkable = new TryReuseOrCallCloseCallable(this, this.mWorkExecutor, this.mDelayCloseTimeInMillis);
                addSupportedAttributes(set3);
                return;
            }
            throw new IllegalArgumentException("Invalid EndpointIdentity: Should not be DeviceIdentity");
        }
        throw new IllegalArgumentException("IdentityResolver cannot be null");
    }
}
