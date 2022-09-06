package com.amazon.communication.socket.ssl;

import com.amazon.communication.socket.IncompleteIoListener;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.FailFast;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLSession;
/* loaded from: classes12.dex */
public class SslSocketChannel extends SocketChannel {
    private static final int BUFFER_MULTIPLIER = 2;
    private static final int MAX_CLOSE_ATTEMPTS = 100;
    private final String mHost;
    private final AtomicBoolean mIsSslHandshakeComplete;
    private final IncompleteIoListener mListener;
    private final ByteBuffer mMyNetData;
    private final ByteBuffer mPeerAppData;
    private final ByteBuffer mPeerNetData;
    private final Object mReadLock;
    private final SocketChannel mSocketChannel;
    private final SSLEngine mSslEngine;
    private final SSLSession mSslSession;
    private final HostnameVerifier mVerifier;
    private final Object mWriteLock;
    private static final DPLogger log = new DPLogger("TComm.SslSocketChannel");
    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocate(0);
    private static final String[] PREFERED_CIPHER_SUITES = {"TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_AES_128_CBC_SHA256", "TLS_RSA_WITH_AES_128_CBC_SHA"};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.communication.socket.ssl.SslSocketChannel$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = new int[SSLEngineResult.HandshakeStatus.values().length];
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$Status;

        static {
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_TASK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_UNWRAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_WRAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$javax$net$ssl$SSLEngineResult$Status = new int[SSLEngineResult.Status.values().length];
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.BUFFER_OVERFLOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.BUFFER_UNDERFLOW.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    protected SslSocketChannel(SelectorProvider selectorProvider, SSLContext sSLContext, SocketChannel socketChannel, String str, int i, IncompleteIoListener incompleteIoListener, HostnameVerifier hostnameVerifier) {
        super(selectorProvider);
        String[] strArr;
        this.mWriteLock = new Object();
        this.mReadLock = new Object();
        this.mIsSslHandshakeComplete = new AtomicBoolean(false);
        this.mHost = str;
        this.mSslEngine = sSLContext.createSSLEngine(str, i);
        this.mSslEngine.setUseClientMode(true);
        HashSet hashSet = new HashSet(Arrays.asList(this.mSslEngine.getSupportedCipherSuites()));
        ArrayList arrayList = new ArrayList();
        for (String str2 : PREFERED_CIPHER_SUITES) {
            if (hashSet.contains(str2)) {
                log.debug("SslSocketChannel", "Enabled cipher suit", "name", str2);
                arrayList.add(str2);
            }
        }
        this.mSslEngine.setEnabledCipherSuites((String[]) arrayList.toArray(new String[arrayList.size()]));
        this.mSocketChannel = socketChannel;
        this.mListener = incompleteIoListener;
        this.mSslSession = this.mSslEngine.getSession();
        this.mMyNetData = ByteBuffer.allocate(this.mSslSession.getPacketBufferSize() * 2);
        this.mPeerAppData = ByteBuffer.allocate(this.mSslSession.getApplicationBufferSize() * 2);
        this.mPeerNetData = ByteBuffer.allocate(this.mSslSession.getPacketBufferSize() * 2);
        ByteBuffer byteBuffer = this.mPeerAppData;
        byteBuffer.position(byteBuffer.limit());
        ByteBuffer byteBuffer2 = this.mMyNetData;
        byteBuffer2.position(byteBuffer2.limit());
        this.mVerifier = hostnameVerifier;
        log.verbose("SslSocketChannel.constructor", "SslSocketChannel created", "SslSocketChannel", this);
    }

    private void doBlockingHandshakeTasks() {
        while (true) {
            Runnable delegatedTask = this.mSslEngine.getDelegatedTask();
            if (delegatedTask != null) {
                delegatedTask.run();
            } else {
                return;
            }
        }
    }

    private int flushData() throws IOException {
        try {
            int write = this.mMyNetData.hasRemaining() ? this.mSocketChannel.write(this.mMyNetData) : 0;
            if (this.mMyNetData.hasRemaining()) {
                this.mListener.onIncompleteWrite();
            }
            return write;
        } catch (IOException e) {
            ByteBuffer byteBuffer = this.mMyNetData;
            byteBuffer.position(byteBuffer.limit());
            throw e;
        }
    }

    private String getCompactByteBufferState(ByteBuffer byteBuffer) {
        return "[" + byteBuffer.position() + "," + byteBuffer.limit() + "," + byteBuffer.capacity() + "](" + byteBuffer.remaining() + ")";
    }

    private void logState(String str, String str2, Object... objArr) {
        if (log.isDebugEnabled()) {
            Object[] objArr2 = new Object[objArr.length + 4];
            System.arraycopy(objArr, 0, objArr2, 4, objArr.length);
            objArr2[0] = "peerApp";
            objArr2[1] = getCompactByteBufferState(this.mPeerAppData);
            objArr2[2] = "peerNet";
            objArr2[3] = getCompactByteBufferState(this.mPeerNetData);
            log.debug(str, str2, objArr2);
        }
    }

    private void logStateWithTarget(String str, String str2, ByteBuffer byteBuffer, Object... objArr) {
        if (log.isDebugEnabled()) {
            Object[] objArr2 = new Object[objArr.length + 6];
            System.arraycopy(objArr, 0, objArr2, 6, objArr.length);
            objArr2[0] = "peerApp";
            objArr2[1] = getCompactByteBufferState(this.mPeerAppData);
            objArr2[2] = "peerNet";
            objArr2[3] = getCompactByteBufferState(this.mPeerNetData);
            objArr2[4] = "target";
            objArr2[5] = getCompactByteBufferState(byteBuffer);
            log.debug(str, str2, objArr2);
        }
    }

    public static SocketChannel open(SSLContext sSLContext, SocketChannel socketChannel, String str, int i, IncompleteIoListener incompleteIoListener, HostnameVerifier hostnameVerifier) {
        log.verbose(TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "opening a new secure socket channel", "host", str, "securePort", Integer.valueOf(i));
        return new SslSocketChannel(SelectorProvider.provider(), sSLContext, socketChannel, str, i, incompleteIoListener, hostnameVerifier);
    }

    private int readAndUnwrap() throws IOException {
        SSLEngineResult unwrap;
        synchronized (this.mReadLock) {
            if (!this.mPeerAppData.hasRemaining()) {
                this.mPeerAppData.clear();
                int read = this.mSocketChannel.read(this.mPeerNetData);
                this.mPeerNetData.flip();
                int remaining = this.mPeerNetData.remaining();
                if (read == -1 && !this.mPeerNetData.hasRemaining()) {
                    return -1;
                }
                do {
                    unwrap = this.mSslEngine.unwrap(this.mPeerNetData, this.mPeerAppData);
                    if (unwrap.getStatus() != SSLEngineResult.Status.OK || unwrap.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NEED_UNWRAP || unwrap.bytesProduced() != 0) {
                        break;
                    }
                } while (this.mPeerNetData.remaining() > 0);
                if (this.mPeerAppData.position() == 0 && unwrap.getStatus() == SSLEngineResult.Status.OK && this.mPeerNetData.hasRemaining()) {
                    log.verbose("readAndUnwrap", "no data was produced, unwrapping once more", new Object[0]);
                    unwrap = this.mSslEngine.unwrap(this.mPeerNetData, this.mPeerAppData);
                    log.debug("readAndUnwrap", "finished re-unwrap", "result", unwrap);
                }
                SSLEngineResult.Status status = unwrap.getStatus();
                boolean z = status == SSLEngineResult.Status.BUFFER_OVERFLOW;
                FailFast.expectFalse(z, "Unable to finish unwrap since we ran overflowed peerAppData. peerAppData Size: " + this.mPeerAppData.limit() + ", Encrypted contents size: " + remaining);
                if (status != SSLEngineResult.Status.CLOSED && (read != -1 || this.mPeerAppData.position() != 0)) {
                    SSLEngineResult.HandshakeStatus handshakeStatus = unwrap.getHandshakeStatus();
                    if (isSslHandshakeComplete() && handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED && handshakeStatus != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
                        log.error("readAndUnwrap", "SSL Hanshake renegotiation requested", "mHost", this.mHost);
                        throw new IOException("Received request for SSL Handshake renegotiation from: " + this.mHost);
                    }
                    this.mPeerNetData.compact();
                    this.mPeerAppData.flip();
                    return this.mPeerAppData.remaining();
                }
                logState("readAndUnwrap", "Connection is being closed by peer", new Object[0]);
                close();
                return -1;
            }
            throw new IllegalStateException("Existing unencrypted data still available for consumption: position: " + this.mPeerAppData.position() + ", remaining: " + this.mPeerAppData.remaining());
        }
    }

    @Override // java.nio.channels.SocketChannel
    public boolean connect(SocketAddress socketAddress) throws IOException {
        return this.mSocketChannel.connect(socketAddress);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e8, code lost:
        if (r9.mIsSslHandshakeComplete.compareAndSet(false, true) != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ea, code lost:
        com.amazon.communication.socket.ssl.SslSocketChannel.log.warn("continueHandshake", "handshake finished again", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0102, code lost:
        if (r9.mVerifier.verify(r9.mHost, r9.mSslEngine.getSession()) == false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0104, code lost:
        com.amazon.communication.socket.ssl.SslSocketChannel.log.debug("continueHandshake", "handshake finished", "handshakeStatus", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0111, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0127, code lost:
        throw new javax.net.ssl.SSLPeerUnverifiedException(com.amazon.dp.logger.DPFormattedMessage.toDPFormat("continueHandshake", "Hostname could not be verified by certificate", com.amazon.communication.remotesetting.StageSwitchService.CONFIG_HOSTNAME_KEY, r9.mHost));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean continueHandshake() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.socket.ssl.SslSocketChannel.continueHandshake():boolean");
    }

    @Override // java.nio.channels.SocketChannel
    public boolean finishConnect() throws IOException {
        boolean finishConnect = this.mSocketChannel.finishConnect();
        if (finishConnect) {
            log.verbose("finishConnect", "beginning SSL handshake.", new Object[0]);
            this.mSslEngine.beginHandshake();
            continueHandshake();
            return finishConnect;
        }
        throw new IOException("Unable to finish connection to peer");
    }

    public void flushIntermediateWriteBuffer() throws IOException {
        synchronized (this.mWriteLock) {
            flushData();
        }
    }

    @Override // java.nio.channels.spi.AbstractSelectableChannel
    protected synchronized void implCloseSelectableChannel() throws IOException {
        log.debug("implCloseSelectableChannel", "implCloseSelectableChannel started (stack trace will be printed)", "remote host", this.mHost, new Throwable());
        this.mSslEngine.closeOutbound();
        this.mMyNetData.compact();
        int i = 0;
        while (!this.mSslEngine.isOutboundDone()) {
            i++;
            SSLEngineResult wrap = this.mSslEngine.wrap(EMPTY_BUFFER, this.mMyNetData);
            log.debug("implCloseSelectableChannel", "outbound is not done", "status", wrap.getStatus(), "outbound attempt", Integer.valueOf(i));
            if (wrap.getStatus() != SSLEngineResult.Status.OK) {
                break;
            }
            this.mMyNetData.flip();
            int i2 = 0;
            while (this.mMyNetData.hasRemaining() && (i2 = i2 + 1) <= 100) {
                int write = this.mSocketChannel.write(this.mMyNetData);
                log.debug("implCloseSelectableChannel", "wrote to socket channel", "remaining", Integer.valueOf(this.mMyNetData.remaining()), "bytes written", Integer.valueOf(write), "outbound attempt", Integer.valueOf(i), "write attempt", Integer.valueOf(i2));
                if (write == -1) {
                    throw new IOException("Underlying socket channel was closed before shutting down ssl engine");
                }
            }
        }
        this.mSocketChannel.close();
    }

    @Override // java.nio.channels.spi.AbstractSelectableChannel
    protected void implConfigureBlocking(boolean z) throws IOException {
        this.mSocketChannel.configureBlocking(z);
    }

    @Override // java.nio.channels.SocketChannel
    public boolean isConnected() {
        return this.mSocketChannel.isConnected();
    }

    @Override // java.nio.channels.SocketChannel
    public boolean isConnectionPending() {
        return this.mSocketChannel.isConnectionPending();
    }

    public boolean isSslHandshakeComplete() {
        return this.mIsSslHandshakeComplete.get();
    }

    public boolean isValidChannel(SelectableChannel selectableChannel) {
        return this.mSocketChannel == selectableChannel;
    }

    public SelectionKey keyForDelegate(Selector selector) {
        return this.mSocketChannel.keyFor(selector);
    }

    @Override // java.nio.channels.SocketChannel, java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        int readAndUnwrap;
        synchronized (this.mReadLock) {
            int i = 0;
            if (this.mSslEngine.isInboundDone()) {
                log.debug("read", "EOF reached", new Object[0]);
                return -1;
            }
            while (byteBuffer.hasRemaining()) {
                if (!this.mPeerAppData.hasRemaining() && ((readAndUnwrap = readAndUnwrap()) == -1 || readAndUnwrap == 0)) {
                    return i > 0 ? i : readAndUnwrap;
                }
                int min = Math.min(this.mPeerAppData.remaining(), byteBuffer.remaining());
                while (min > 0) {
                    byteBuffer.put(this.mPeerAppData.get());
                    min--;
                    i++;
                }
            }
            return i;
        }
    }

    public SelectionKey registerDelegate(Selector selector, int i, Object obj) throws ClosedChannelException {
        log.debug("registerDelegate", "registering selector for operations", "interestedOperations", Integer.valueOf(i));
        return this.mSocketChannel.register(selector, i, obj);
    }

    @Override // java.nio.channels.SocketChannel
    public Socket socket() {
        return this.mSocketChannel.socket();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SslSocketChannel netData size: ");
        outline107.append(this.mMyNetData.capacity());
        outline107.append(", peerAppData size: ");
        outline107.append(this.mPeerAppData.capacity());
        outline107.append(", peerNetData size: ");
        outline107.append(this.mPeerNetData.capacity());
        return outline107.toString();
    }

    @Override // java.nio.channels.SocketChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        synchronized (this.mWriteLock) {
            if (byteBuffer.remaining() == 0) {
                log.info("write", "0-byte write attempted", new Object[0]);
                return 0;
            } else if (this.mMyNetData.hasRemaining()) {
                log.warn("write", "There is still encrypted data waiting to be flushed. Attempting to flush it now.", "mMyNetData", this.mMyNetData);
                flushData();
                return 0;
            } else {
                this.mMyNetData.clear();
                SSLEngineResult wrap = this.mSslEngine.wrap(byteBuffer, this.mMyNetData);
                this.mMyNetData.flip();
                flushData();
                return wrap.bytesConsumed();
            }
        }
    }

    @Override // java.nio.channels.SocketChannel, java.nio.channels.ScatteringByteChannel
    public long read(ByteBuffer[] byteBufferArr, int i, int i2) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.nio.channels.SocketChannel, java.nio.channels.GatheringByteChannel
    public long write(ByteBuffer[] byteBufferArr, int i, int i2) throws IOException {
        if (i >= 0 && i2 >= 0 && i + i2 <= byteBufferArr.length) {
            synchronized (this.mWriteLock) {
                if (this.mMyNetData.hasRemaining()) {
                    log.warn("write(ByteBuffer[])", "There is still encrypted data waiting to be flushed. Attempting to flush it now.", "mMyNetData", this.mMyNetData);
                    flushData();
                    return 0L;
                }
                this.mMyNetData.clear();
                SSLEngineResult wrap = this.mSslEngine.wrap(byteBufferArr, i, i2, this.mMyNetData);
                this.mMyNetData.flip();
                flushData();
                return wrap.bytesConsumed();
            }
        }
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Invalid offset: ", i, " and/or length: ", i2, ". Sources length: ");
        outline110.append(byteBufferArr.length);
        throw new IllegalArgumentException(outline110.toString());
    }
}
