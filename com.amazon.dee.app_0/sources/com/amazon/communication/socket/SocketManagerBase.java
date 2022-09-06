package com.amazon.communication.socket;

import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IdentityResolver;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceConnection;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.communication.ProtocolHandlerManager;
import com.amazon.communication.ResponseRouter;
import com.amazon.communication.ThreadName;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.identity.UniqueEndpointIdentifier;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SelectionKeyChangeQueue;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.DpExecutors;
import com.dp.utils.DpScheduledThreadPoolExecutor;
import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
/* loaded from: classes12.dex */
public abstract class SocketManagerBase implements Runnable, SocketManager, SelectionKeyChangeQueue, ProtocolSocket.ProtocolSocketStateListener, ProtocolSocket.ProtocolSocketTransactionListener {
    private static final long SELECTOR_THREAD_SHUTDOWN_TIMEOUT_MS = 1000;
    protected static final long SOCKET_CONNECTION_TIMEOUT_MS = 10000;
    private static final DPLogger log = new DPLogger("TComm.SocketManagerBase");
    protected SocketCollection mActiveProtocolSockets;
    protected final HostnameVerifier mHostnameVerifier;
    protected IdentityResolver mIdentityResolver;
    protected ProtocolHandlerManager mProtocolHandlerManager;
    protected ResponseRouter mResponseRouter;
    protected SelectorProvider mSelectorProvider;
    protected SSLContext mSslContext;
    protected WorkExecutor mWorkExecutor;
    private final AtomicBoolean mRunning = new AtomicBoolean();
    private final AtomicBoolean mIsInitialized = new AtomicBoolean();
    private final LinkedList<SelectionKeyChangeQueue.SelectionKeyChangeOperation> mToDoList = new LinkedList<>();
    private final DpScheduledThreadPoolExecutor mSelectorExecutor = DpExecutors.newSingleThreadScheduledExecutor(ThreadName.SELECTOR);
    protected long mMaxWaitTimeDuringShutdownMs = 5000;
    private final Lock mLargeTransactionLock = new ReentrantLock();
    private final Condition mLargeTransactionComplete = this.mLargeTransactionLock.newCondition();
    private int mLargeTransactionsCount = 0;
    protected Selector mSelector = null;

    /* loaded from: classes12.dex */
    public static class BaseSocketCollection implements SocketCollection {
        private final Map<UniqueEndpointIdentifier, List<ProtocolSocket>> mSocketMap = GeneratedOutlineSupport1.outline136();
        private final List<ProtocolSocket> mSocketList = Collections.synchronizedList(new ArrayList());

        public static synchronized List<ProtocolSocket> getSocketsSupportingAttributes(List<ProtocolSocket> list, Set<ProtocolSocket.ProtocolSocketAttribute> set) {
            ArrayList arrayList;
            synchronized (BaseSocketCollection.class) {
                arrayList = new ArrayList(list.size());
                if (set != null && set.size() > 0) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        ProtocolSocket protocolSocket = list.get(i);
                        if (isAllAttributesSupported(protocolSocket, set)) {
                            arrayList.add(protocolSocket);
                        }
                    }
                } else {
                    arrayList.addAll(list);
                }
            }
            return arrayList;
        }

        private static boolean isAllAttributesSupported(ProtocolSocket protocolSocket, Set<ProtocolSocket.ProtocolSocketAttribute> set) {
            for (ProtocolSocket.ProtocolSocketAttribute protocolSocketAttribute : set) {
                if (!protocolSocket.isAttributeSupported(protocolSocketAttribute)) {
                    SocketManagerBase.log.debug("isAllAttributesSupported", "missing attribute for this socket", "socket", protocolSocket, "attribute", protocolSocketAttribute);
                    return false;
                }
            }
            return true;
        }

        @Override // com.amazon.communication.socket.SocketCollection
        public synchronized void addSocket(UniqueEndpointIdentifier uniqueEndpointIdentifier, ProtocolSocket protocolSocket) {
            this.mSocketList.add(protocolSocket);
            List<ProtocolSocket> list = this.mSocketMap.get(uniqueEndpointIdentifier);
            if (list != null) {
                list.add(protocolSocket);
            } else {
                List<ProtocolSocket> synchronizedList = Collections.synchronizedList(new ArrayList());
                synchronizedList.add(protocolSocket);
                this.mSocketMap.put(uniqueEndpointIdentifier, synchronizedList);
            }
        }

        @Override // com.amazon.communication.socket.SocketCollection
        public synchronized void clear() {
            this.mSocketMap.clear();
            this.mSocketList.clear();
        }

        @Override // com.amazon.communication.socket.SocketCollection
        public synchronized List<ProtocolSocket> getAllSockets() {
            return Collections.unmodifiableList(new ArrayList(this.mSocketList));
        }

        @Override // com.amazon.communication.socket.SocketCollection
        public synchronized List<ProtocolSocket> getSocketsForEndpoint(UniqueEndpointIdentifier uniqueEndpointIdentifier, Set<ProtocolSocket.ProtocolSocketAttribute> set) throws EndpointNotAuthenticatedException {
            List<ProtocolSocket> list = this.mSocketMap.get(uniqueEndpointIdentifier);
            if (list == null) {
                SocketManagerBase.log.debug("getSocketsForEndpoint", "no sockets to desired endpoint", "endpoint", uniqueEndpointIdentifier);
                return Collections.emptyList();
            }
            return Collections.unmodifiableList(getSocketsSupportingAttributes(list, set));
        }

        @Override // com.amazon.communication.socket.SocketCollection
        public synchronized void removeSocket(ProtocolSocket protocolSocket) {
            if (this.mSocketList.contains(protocolSocket)) {
                this.mSocketList.remove(protocolSocket);
                UniqueEndpointIdentifier uniqueEndpointIdentifier = protocolSocket.getUniqueEndpointIdentifier();
                List<ProtocolSocket> list = this.mSocketMap.get(uniqueEndpointIdentifier);
                list.remove(protocolSocket);
                if (list.isEmpty()) {
                    this.mSocketMap.remove(uniqueEndpointIdentifier);
                }
            } else {
                if (this.mSocketMap.containsKey(protocolSocket.getUniqueEndpointIdentifier()) && this.mSocketMap.get(protocolSocket.getUniqueEndpointIdentifier()).contains(protocolSocket)) {
                    throw new IllegalArgumentException("Inconsistency - socket present in list but not map");
                }
                SocketManagerBase.log.verbose("removeSocket", "attempting to removing socket that was never added; connect() was likely never called", new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class SocketExceptionPair {
        public final Exception exception;
        public final ProtocolSocket socket;

        public SocketExceptionPair(ProtocolSocket protocolSocket, Exception exc) {
            this.socket = protocolSocket;
            this.exception = exc;
        }
    }

    public SocketManagerBase(WorkExecutor workExecutor, ProtocolHandlerManager protocolHandlerManager, IdentityResolver identityResolver, SelectorProvider selectorProvider, HostnameVerifier hostnameVerifier) throws IOException {
        this.mWorkExecutor = workExecutor;
        this.mProtocolHandlerManager = protocolHandlerManager;
        this.mIdentityResolver = identityResolver;
        this.mSelectorProvider = selectorProvider;
        this.mHostnameVerifier = hostnameVerifier;
        try {
            this.mSslContext = SSLContext.getDefault();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Error getting default SSLContext", e);
        }
    }

    private void selectorLoop() {
        while (this.mRunning.get()) {
            LinkedList<SocketExceptionPair> linkedList = null;
            try {
                synchronized (this.mToDoList) {
                    while (!this.mToDoList.isEmpty()) {
                        SelectionKeyChangeQueue.SelectionKeyChangeOperation removeFirst = this.mToDoList.removeFirst();
                        try {
                            removeFirst.socket.onSelectionKeyChange(this.mSelector, removeFirst.operation, removeFirst.operationSet);
                        } catch (IllegalSocketStateException e) {
                            log.error("selectorLoop", "onSelectionChange error, closing socket.", "op.socket", removeFirst.socket, e);
                            if (linkedList == null) {
                                linkedList = new LinkedList();
                            }
                            linkedList.add(new SocketExceptionPair(removeFirst.socket, e));
                        }
                    }
                }
                if (linkedList != null) {
                    for (SocketExceptionPair socketExceptionPair : linkedList) {
                        socketExceptionPair.socket.close(new CloseDetail(CloseStatusCodes.SELECTOR_LOOP_ERROR, socketExceptionPair.exception.toString()));
                    }
                }
                this.mSelector.select();
                Iterator<SelectionKey> it2 = this.mSelector.selectedKeys().iterator();
                while (it2.hasNext()) {
                    SelectionKey next = it2.next();
                    it2.remove();
                    DirectBiDiSocket directBiDiSocket = (DirectBiDiSocket) next.attachment();
                    if (directBiDiSocket != null) {
                        try {
                            if (directBiDiSocket.socketState() != ProtocolSocket.ProtocolSocketState.DISCONNECTED) {
                                directBiDiSocket.handleSelectionKey(next);
                            } else {
                                log.info("selectorLoop", "got a callback for a disconnected socket; ignoring and cancelling the key", "attachedSocket", directBiDiSocket);
                                next.cancel();
                            }
                        } catch (IOException e2) {
                            log.warn("selectorLoop", "handleSelectionKey error, closing socket and canceling key", "attachedSocket", directBiDiSocket, e2);
                            directBiDiSocket.close(new CloseDetail(CloseStatusCodes.IO_ERROR, "IOException handling selection key: " + e2.getMessage()));
                            next.cancel();
                        } catch (Exception e3) {
                            log.error("selectorLoop", "unhandled exception while handling selection key; close socket and canceling key", "attachedSocket", directBiDiSocket, e3);
                            directBiDiSocket.close(new CloseDetail(4000, "Unhandled exception handling selection key: " + e3.getMessage()));
                            next.cancel();
                        }
                    } else {
                        throw new IOException("Could not find attached DirectBiDiSocket in selector key");
                        break;
                    }
                }
                continue;
            } catch (IOException e4) {
                log.error("selectorLoop", "IOException in selector loop, quitting...", e4);
                throw new RuntimeException(e4);
            } catch (ClosedSelectorException e5) {
                if (this.mRunning.get()) {
                    log.error("selectorLoop", "selector was closed but shutdown is not in progress", e5);
                } else {
                    log.info("selectorLoop", "selector was closed, shutdown is likely in progress", e5);
                }
                shutdown();
            } catch (Exception e6) {
                log.error("selectorLoop", "exception in selector loop, quitting...", e6);
                throw new RuntimeException(e6);
            }
        }
    }

    public void connect(final ProtocolSocket protocolSocket) throws SocketAcquisitionFailedException {
        ensureInitializedAndRunning();
        log.verbose(EmulateConnection.EXTRA_CONNECT, "beginning execution", new Object[0]);
        if (protocolSocket instanceof DirectBiDiSocket) {
            DirectBiDiSocket directBiDiSocket = (DirectBiDiSocket) protocolSocket;
            directBiDiSocket.startConnection();
            UniqueEndpointIdentifier uniqueEndpointIdentifier = directBiDiSocket.getUniqueEndpointIdentifier();
            log.debug(EmulateConnection.EXTRA_CONNECT, "adding socket", TracePublisherServiceConnection.UNIQUE_IDENTIFIER, uniqueEndpointIdentifier);
            this.mActiveProtocolSockets.addSocket(uniqueEndpointIdentifier, directBiDiSocket);
            directBiDiSocket.addStateListener(this);
            directBiDiSocket.addTransactionListener(this);
            queueChange(directBiDiSocket, SelectionKeyChangeQueue.SelectionKeyOperation.ADD, 8);
        }
        this.mWorkExecutor.enqueueWorkAfter(protocolSocket, new Callable<Void>() { // from class: com.amazon.communication.socket.SocketManagerBase.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                String valueOf;
                SocketManagerBase.log.debug("purgeStaleSocket", "about to inspect socket", "socket", protocolSocket);
                if (ProtocolSocket.ProtocolSocketState.CONNECTING.equals(protocolSocket.socketState())) {
                    ProtocolSocket protocolSocket2 = protocolSocket;
                    if (protocolSocket2 instanceof DirectBiDiSocket) {
                        valueOf = String.valueOf(((DirectBiDiSocket) protocolSocket2).getSocketConnectionState());
                    } else {
                        valueOf = String.valueOf(protocolSocket2.socketState());
                    }
                    SocketManagerBase.log.info("purgeStaleSocket", "socket stuck in CONNECTING state", "socket", protocolSocket, "state", valueOf, "timeout", 10000L);
                    protocolSocket.close(new CloseDetail(CloseStatusCodes.CONNECTION_TIMEOUT, "Socket didn't finish connection within time (ms): 10000"));
                    return null;
                }
                return null;
            }
        }, 10000L);
    }

    public ProtocolSocket createProtocolSocket(EndpointIdentity endpointIdentity, Set<ProtocolSocket.ProtocolSocketAttribute> set, Purpose purpose, ConnectReason connectReason, String str) {
        ensureInitializedAndRunning();
        ProtocolSocket createProtocolSocket = createProtocolSocket(endpointIdentity, set, connectReason, str);
        if (purpose != null) {
            createProtocolSocket.setPurpose(purpose);
        }
        return createProtocolSocket;
    }

    protected SocketCollection createSocketCollection() {
        return new BaseSocketCollection();
    }

    protected void ensureInitialized() {
        if (this.mIsInitialized.get()) {
            return;
        }
        throw new IllegalStateException("Socket manager has not completed initialization");
    }

    protected void ensureInitializedAndRunning() {
        ensureInitialized();
        if (this.mRunning.get()) {
            return;
        }
        throw new IllegalStateException("Socket manager selector is not running");
    }

    @Override // com.amazon.communication.socket.SocketManager
    public List<ProtocolSocket> getActiveProtocolSockets() {
        ensureInitialized();
        return this.mActiveProtocolSockets.getAllSockets();
    }

    @Override // com.amazon.communication.socket.SocketManager
    public List<ProtocolSocket> getExistingProtocolSockets(UniqueEndpointIdentifier uniqueEndpointIdentifier, Set<ProtocolSocket.ProtocolSocketAttribute> set) throws EndpointNotAuthenticatedException {
        ensureInitialized();
        return this.mActiveProtocolSockets.getSocketsForEndpoint(uniqueEndpointIdentifier, set);
    }

    @PostConstruct
    public synchronized void initialize() throws IOException {
        if (!this.mIsInitialized.get()) {
            log.verbose("initialize", "beginning execution", new Object[0]);
            this.mSelector = this.mSelectorProvider.openSelector();
            this.mActiveProtocolSockets = createSocketCollection();
            this.mRunning.set(true);
            this.mSelectorExecutor.submit(this);
            this.mIsInitialized.set(true);
        } else {
            throw new IllegalStateException("SocketManager has already been initialized");
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public void notifyStateChanged(ProtocolSocket protocolSocket) {
        ensureInitialized();
        if (protocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.DISCONNECTED) {
            log.verbose("notifyStateChanged", "removing disconnected socket from active socket map.", "protocolSocket", protocolSocket);
            this.mActiveProtocolSockets.removeSocket(protocolSocket);
            protocolSocket.removeStateListener(this);
            protocolSocket.removeTransactionListener(this);
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocket.ProtocolSocketTransactionListener
    public void onLargeMessageTransactionBegin() {
        this.mLargeTransactionLock.lock();
        this.mLargeTransactionsCount++;
        this.mLargeTransactionLock.unlock();
    }

    @Override // com.amazon.communication.socket.ProtocolSocket.ProtocolSocketTransactionListener
    public void onLargeMessageTransactionComplete() {
        this.mLargeTransactionLock.lock();
        try {
            int i = this.mLargeTransactionsCount - 1;
            this.mLargeTransactionsCount = i;
            if (i == 0) {
                this.mLargeTransactionComplete.signal();
            }
        } finally {
            this.mLargeTransactionLock.unlock();
        }
    }

    @Override // com.amazon.communication.socket.SelectionKeyChangeQueue
    public void queueChange(DirectBiDiSocket directBiDiSocket, SelectionKeyChangeQueue.SelectionKeyOperation selectionKeyOperation, int i) {
        ensureInitialized();
        synchronized (this.mToDoList) {
            this.mToDoList.add(new SelectionKeyChangeQueue.SelectionKeyChangeOperation(directBiDiSocket, selectionKeyOperation, i));
        }
        this.mSelector.wakeup();
    }

    @Override // java.lang.Runnable
    public void run() {
        log.verbose("run", "starting selector loop...", new Object[0]);
        selectorLoop();
        log.verbose("run", "selector loop exiting.", new Object[0]);
    }

    public void setMaxWaitTimeDuringShutdownMs(long j) {
        this.mMaxWaitTimeDuringShutdownMs = j;
    }

    public void setSslContext(SSLContext sSLContext) {
        this.mSslContext = sSLContext;
    }

    public void shutdown() {
        log.verbose("shutdown", "beginning execution", new Object[0]);
        if (this.mRunning.getAndSet(false)) {
            waitForLargeMessageTransactionsToComplete(this.mMaxWaitTimeDuringShutdownMs);
            List<ProtocolSocket> allSockets = this.mActiveProtocolSockets.getAllSockets();
            int size = allSockets.size();
            for (int i = 0; i < size; i++) {
                ProtocolSocket protocolSocket = allSockets.get(i);
                log.verbose("shutdown", "releasing socket", "socket", protocolSocket);
                protocolSocket.removeStateListener(this);
                protocolSocket.removeTransactionListener(this);
                protocolSocket.close(new CloseDetail(CloseStatusCodes.SOCKET_MANAGER_SHUTTING_DOWN, "SocketManager shutting down"));
            }
            this.mActiveProtocolSockets.clear();
            try {
                this.mSelector.close();
                this.mSelectorExecutor.properShutdown(1000L, 1000L, 1000L);
                return;
            } catch (IOException e) {
                log.warn("shutdown", "closing selector failed", e);
                return;
            }
        }
        log.warn("shutdown", "Detected redundant call to shutdown", new Object[0]);
    }

    protected void waitForLargeMessageTransactionsToComplete(long j) {
        this.mLargeTransactionLock.lock();
        try {
            if (this.mLargeTransactionsCount > 0 && !this.mLargeTransactionComplete.await(j, TimeUnit.MILLISECONDS)) {
                log.info("waitForLargeMessageTransactionsToComplete", "large transactions still pending but can't wait longer", new Object[0]);
            }
        } catch (InterruptedException unused) {
        } catch (Throwable th) {
            this.mLargeTransactionLock.unlock();
            throw th;
        }
        this.mLargeTransactionLock.unlock();
    }
}
