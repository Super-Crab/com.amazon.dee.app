package org.bouncycastle.jsse.provider;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import org.bouncycastle.jsse.BCApplicationProtocolSelector;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLConnection;
import org.bouncycastle.jsse.BCSSLEngine;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.tls.RecordPreview;
import org.bouncycastle.tls.TlsClientProtocol;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsProtocol;
import org.bouncycastle.tls.TlsServerProtocol;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ProvSSLEngine extends SSLEngine implements BCSSLEngine, ProvTlsManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = Logger.getLogger(ProvSSLEngine.class.getName());
    protected boolean closedEarly;
    protected ProvSSLConnection connection;
    protected final ContextData contextData;
    protected SSLException deferredException;
    protected boolean enableSessionCreation;
    protected ProvSSLSessionHandshake handshakeSession;
    protected SSLEngineResult.HandshakeStatus handshakeStatus;
    protected boolean initialHandshakeBegun;
    protected TlsProtocol protocol;
    protected ProvTlsPeer protocolPeer;
    protected final ProvSSLParameters sslParameters;
    protected boolean useClientMode;
    protected boolean useClientModeSet;

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLEngine(ContextData contextData) {
        this(contextData, null, -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLEngine(ContextData contextData, String str, int i) {
        super(str, i);
        this.enableSessionCreation = true;
        this.useClientMode = true;
        this.useClientModeSet = false;
        this.closedEarly = false;
        this.initialHandshakeBegun = false;
        this.handshakeStatus = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        this.protocol = null;
        this.protocolPeer = null;
        this.connection = null;
        this.handshakeSession = null;
        this.deferredException = null;
        this.contextData = contextData;
        this.sslParameters = contextData.getContext().getDefaultSSLParameters(this.useClientMode);
    }

    private RecordPreview getRecordPreview(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 5) {
            return null;
        }
        byte[] bArr = new byte[5];
        int position = byteBuffer.position();
        byteBuffer.get(bArr);
        byteBuffer.position(position);
        return this.protocol.previewInputRecord(bArr);
    }

    private int getTotalRemaining(ByteBuffer[] byteBufferArr, int i, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int remaining = byteBufferArr[i + i5].remaining();
            if (remaining >= i3 - i4) {
                return i3;
            }
            i4 += remaining;
        }
        return i4;
    }

    private boolean hasInsufficientSpace(ByteBuffer[] byteBufferArr, int i, int i2, int i3) {
        return getTotalRemaining(byteBufferArr, i, i2, i3) < i3;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void beginHandshake() throws SSLException {
        SSLEngineResult.HandshakeStatus handshakeStatus;
        if (!this.useClientModeSet) {
            throw new IllegalStateException("Client/Server mode must be set before the handshake can begin");
        }
        if (this.closedEarly) {
            throw new SSLException("Connection is already closed");
        }
        if (this.initialHandshakeBegun) {
            throw new UnsupportedOperationException("Renegotiation not supported");
        }
        this.initialHandshakeBegun = true;
        try {
            if (this.useClientMode) {
                TlsClientProtocol tlsClientProtocol = new TlsClientProtocol();
                this.protocol = tlsClientProtocol;
                ProvTlsClient provTlsClient = new ProvTlsClient(this, this.sslParameters);
                this.protocolPeer = provTlsClient;
                tlsClientProtocol.connect(provTlsClient);
                handshakeStatus = SSLEngineResult.HandshakeStatus.NEED_WRAP;
            } else {
                TlsServerProtocol tlsServerProtocol = new TlsServerProtocol();
                this.protocol = tlsServerProtocol;
                ProvTlsServer provTlsServer = new ProvTlsServer(this, this.sslParameters);
                this.protocolPeer = provTlsServer;
                tlsServerProtocol.accept(provTlsServer);
                handshakeStatus = SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
            }
            this.handshakeStatus = handshakeStatus;
        } catch (SSLException e) {
            throw e;
        } catch (IOException e2) {
            throw new SSLException(e2);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws IOException {
        try {
            this.contextData.getX509TrustManager().checkClientTrusted((X509Certificate[]) x509CertificateArr.clone(), str, this);
        } catch (CertificateException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws IOException {
        try {
            this.contextData.getX509TrustManager().checkServerTrusted((X509Certificate[]) x509CertificateArr.clone(), str, this);
        } catch (CertificateException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public BCX509Key chooseClientKey(String[] strArr, Principal[] principalArr) {
        return getContextData().getX509KeyManager().chooseEngineClientKeyBC(strArr, (Principal[]) JsseUtils.clone(principalArr), this);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public BCX509Key chooseServerKey(String str, Principal[] principalArr) {
        return getContextData().getX509KeyManager().chooseEngineServerKeyBC(str, (Principal[]) JsseUtils.clone(principalArr), this);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void closeInbound() throws SSLException {
        if (!this.closedEarly) {
            if (this.protocol == null) {
                this.closedEarly = true;
            } else {
                try {
                    this.protocol.closeInput();
                } catch (IOException e) {
                    throw new SSLException(e);
                }
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void closeOutbound() {
        if (!this.closedEarly) {
            if (this.protocol == null) {
                this.closedEarly = true;
            } else {
                try {
                    this.protocol.close();
                } catch (IOException e) {
                    LOG.log(Level.WARNING, "Failed to close outbound", (Throwable) e);
                }
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.BCSSLEngine
    public synchronized String getApplicationProtocol() {
        return this.connection == null ? null : this.connection.getApplicationProtocol();
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized BCApplicationProtocolSelector<SSLEngine> getBCHandshakeApplicationProtocolSelector() {
        return this.sslParameters.getEngineAPSelector();
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized BCExtendedSSLSession getBCHandshakeSession() {
        return this.handshakeSession;
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public BCExtendedSSLSession getBCSession() {
        return getSessionImpl();
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized BCSSLConnection getConnection() {
        return this.connection;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public ContextData getContextData() {
        return this.contextData;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized Runnable getDelegatedTask() {
        return null;
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized boolean getEnableSessionCreation() {
        return this.enableSessionCreation;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized String[] getEnabledCipherSuites() {
        return this.sslParameters.getCipherSuites();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized String[] getEnabledProtocols() {
        return this.sslParameters.getProtocols();
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.BCSSLEngine
    public synchronized String getHandshakeApplicationProtocol() {
        return this.handshakeSession == null ? null : this.handshakeSession.getApplicationProtocol();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized SSLSession getHandshakeSession() {
        return this.handshakeSession == null ? null : this.handshakeSession.getExportSSLSession();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        return this.handshakeStatus;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized boolean getNeedClientAuth() {
        return this.sslParameters.getNeedClientAuth();
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized BCSSLParameters getParameters() {
        return SSLParametersUtil.getParameters(this.sslParameters);
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.provider.ProvTlsManager
    public String getPeerHost() {
        return super.getPeerHost();
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public String getPeerHostSNI() {
        return super.getPeerHost();
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.provider.ProvTlsManager
    public int getPeerPort() {
        return super.getPeerPort();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized SSLParameters getSSLParameters() {
        return SSLParametersUtil.getSSLParameters(this.sslParameters);
    }

    @Override // javax.net.ssl.SSLEngine
    public SSLSession getSession() {
        return getSessionImpl().getExportSSLSession();
    }

    ProvSSLSession getSessionImpl() {
        ProvSSLConnection provSSLConnection = this.connection;
        return provSSLConnection == null ? ProvSSLSession.NULL_SESSION : provSSLConnection.mo12855getSession();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized String[] getSupportedCipherSuites() {
        return this.contextData.getContext().getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized String[] getSupportedProtocols() {
        return this.contextData.getContext().getSupportedProtocols();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized boolean getUseClientMode() {
        return this.useClientMode;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized boolean getWantClientAuth() {
        return this.sslParameters.getWantClientAuth();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000f, code lost:
        if (r1.protocol.isClosed() != false) goto L14;
     */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isInboundDone() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.closedEarly     // Catch: java.lang.Throwable -> L17
            if (r0 != 0) goto L14
            org.bouncycastle.tls.TlsProtocol r0 = r1.protocol     // Catch: java.lang.Throwable -> L17
            if (r0 == 0) goto L12
            org.bouncycastle.tls.TlsProtocol r0 = r1.protocol     // Catch: java.lang.Throwable -> L17
            boolean r0 = r0.isClosed()     // Catch: java.lang.Throwable -> L17
            if (r0 == 0) goto L12
            goto L14
        L12:
            r0 = 0
            goto L15
        L14:
            r0 = 1
        L15:
            monitor-exit(r1)
            return r0
        L17:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jsse.provider.ProvSSLEngine.isInboundDone():boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r2.protocol.getAvailableOutputBytes() < 1) goto L13;
     */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isOutboundDone() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.closedEarly     // Catch: java.lang.Throwable -> L1e
            r1 = 1
            if (r0 != 0) goto L1c
            org.bouncycastle.tls.TlsProtocol r0 = r2.protocol     // Catch: java.lang.Throwable -> L1e
            if (r0 == 0) goto L1b
            org.bouncycastle.tls.TlsProtocol r0 = r2.protocol     // Catch: java.lang.Throwable -> L1e
            boolean r0 = r0.isClosed()     // Catch: java.lang.Throwable -> L1e
            if (r0 == 0) goto L1b
            org.bouncycastle.tls.TlsProtocol r0 = r2.protocol     // Catch: java.lang.Throwable -> L1e
            int r0 = r0.getAvailableOutputBytes()     // Catch: java.lang.Throwable -> L1e
            if (r0 >= r1) goto L1b
            goto L1c
        L1b:
            r1 = 0
        L1c:
            monitor-exit(r2)
            return r1
        L1e:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jsse.provider.ProvSSLEngine.isOutboundDone():boolean");
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized void notifyHandshakeComplete(ProvSSLConnection provSSLConnection) {
        if (this.handshakeSession != null) {
            if (!this.handshakeSession.isValid()) {
                provSSLConnection.mo12855getSession().invalidate();
            }
            this.handshakeSession.getJsseSecurityParameters().clear();
        }
        this.handshakeSession = null;
        this.connection = provSSLConnection;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized void notifyHandshakeSession(ProvSSLSessionHandshake provSSLSessionHandshake) {
        this.handshakeSession = provSSLSessionHandshake;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String selectApplicationProtocol(List<String> list) {
        return this.sslParameters.getEngineAPSelector().select(this, list);
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized void setBCHandshakeApplicationProtocolSelector(BCApplicationProtocolSelector<SSLEngine> bCApplicationProtocolSelector) {
        this.sslParameters.setEngineAPSelector(bCApplicationProtocolSelector);
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized void setBCSessionToResume(BCExtendedSSLSession bCExtendedSSLSession) {
        try {
            if (bCExtendedSSLSession == null) {
                throw new NullPointerException("'session' cannot be null");
            }
            if (!(bCExtendedSSLSession instanceof ProvSSLSession)) {
                throw new IllegalArgumentException("Session-to-resume must be a session returned from 'getBCSession'");
            }
            if (this.initialHandshakeBegun) {
                throw new IllegalArgumentException("Session-to-resume cannot be set after the handshake has begun");
            }
            this.sslParameters.setSessionToResume((ProvSSLSession) bCExtendedSSLSession);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setEnableSessionCreation(boolean z) {
        this.enableSessionCreation = z;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setEnabledCipherSuites(String[] strArr) {
        this.sslParameters.setCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setEnabledProtocols(String[] strArr) {
        this.sslParameters.setProtocols(strArr);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setNeedClientAuth(boolean z) {
        this.sslParameters.setNeedClientAuth(z);
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized void setParameters(BCSSLParameters bCSSLParameters) {
        SSLParametersUtil.setParameters(this.sslParameters, bCSSLParameters);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setSSLParameters(SSLParameters sSLParameters) {
        SSLParametersUtil.setSSLParameters(this.sslParameters, sSLParameters);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setUseClientMode(boolean z) {
        if (this.initialHandshakeBegun) {
            throw new IllegalArgumentException("Client/Server mode cannot be changed after the handshake has begun");
        }
        if (this.useClientMode != z) {
            this.contextData.getContext().updateDefaultSSLParameters(this.sslParameters, z);
            this.useClientMode = z;
        }
        this.useClientModeSet = true;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setWantClientAuth(boolean z) {
        this.sslParameters.setWantClientAuth(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0090 A[Catch: all -> 0x00eb, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x0005, B:6:0x0008, B:8:0x0013, B:40:0x0088, B:42:0x0090, B:44:0x0098, B:45:0x009f, B:47:0x00a7, B:48:0x00ae, B:50:0x00b6, B:51:0x00bc, B:10:0x0018, B:12:0x001e, B:15:0x0029, B:17:0x0033, B:18:0x0036, B:20:0x0046, B:25:0x0055, B:27:0x0061, B:28:0x006d, B:56:0x00c5, B:58:0x00cb, B:60:0x00cf, B:61:0x00d6, B:64:0x00e5, B:65:0x00ea, B:31:0x0075, B:32:0x007c, B:38:0x0084), top: B:72:0x0001 }] */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized javax.net.ssl.SSLEngineResult unwrap(java.nio.ByteBuffer r10, java.nio.ByteBuffer[] r11, int r12, int r13) throws javax.net.ssl.SSLException {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jsse.provider.ProvSSLEngine.unwrap(java.nio.ByteBuffer, java.nio.ByteBuffer[], int, int):javax.net.ssl.SSLEngineResult");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0080 A[Catch: all -> 0x00d5, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0009, B:8:0x000c, B:10:0x0015, B:12:0x001d, B:13:0x0020, B:16:0x0029, B:18:0x0035, B:20:0x0049, B:24:0x0053, B:26:0x0061, B:33:0x0078, B:35:0x0080, B:37:0x008a, B:38:0x0097, B:39:0x0099, B:43:0x00a4, B:45:0x00ac, B:46:0x00b3, B:48:0x00bb, B:49:0x00c2, B:50:0x00c8, B:30:0x0071, B:31:0x0076, B:53:0x00cf, B:54:0x00d4), top: B:58:0x0001, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ac A[Catch: all -> 0x00d5, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0009, B:8:0x000c, B:10:0x0015, B:12:0x001d, B:13:0x0020, B:16:0x0029, B:18:0x0035, B:20:0x0049, B:24:0x0053, B:26:0x0061, B:33:0x0078, B:35:0x0080, B:37:0x008a, B:38:0x0097, B:39:0x0099, B:43:0x00a4, B:45:0x00ac, B:46:0x00b3, B:48:0x00bb, B:49:0x00c2, B:50:0x00c8, B:30:0x0071, B:31:0x0076, B:53:0x00cf, B:54:0x00d4), top: B:58:0x0001, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b3 A[Catch: all -> 0x00d5, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0009, B:8:0x000c, B:10:0x0015, B:12:0x001d, B:13:0x0020, B:16:0x0029, B:18:0x0035, B:20:0x0049, B:24:0x0053, B:26:0x0061, B:33:0x0078, B:35:0x0080, B:37:0x008a, B:38:0x0097, B:39:0x0099, B:43:0x00a4, B:45:0x00ac, B:46:0x00b3, B:48:0x00bb, B:49:0x00c2, B:50:0x00c8, B:30:0x0071, B:31:0x0076, B:53:0x00cf, B:54:0x00d4), top: B:58:0x0001, inners: #1 }] */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized javax.net.ssl.SSLEngineResult wrap(java.nio.ByteBuffer[] r9, int r10, int r11, java.nio.ByteBuffer r12) throws javax.net.ssl.SSLException {
        /*
            Method dump skipped, instructions count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jsse.provider.ProvSSLEngine.wrap(java.nio.ByteBuffer[], int, int, java.nio.ByteBuffer):javax.net.ssl.SSLEngineResult");
    }
}
