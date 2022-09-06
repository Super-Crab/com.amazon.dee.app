package org.bouncycastle.jsse.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCApplicationProtocolSelector;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLConnection;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsProtocol;
/* loaded from: classes4.dex */
class ProvSSLSocketDirect extends ProvSSLSocketBase implements ProvTlsManager {
    private static final Logger LOG = Logger.getLogger(ProvSSLSocketDirect.class.getName());
    protected final AppDataInput appDataIn;
    protected final AppDataOutput appDataOut;
    protected ProvSSLConnection connection;
    protected final ContextData contextData;
    protected boolean enableSessionCreation;
    protected ProvSSLSessionHandshake handshakeSession;
    protected String peerHost;
    protected String peerHostSNI;
    protected TlsProtocol protocol;
    protected ProvTlsPeer protocolPeer;
    protected final ProvSSLParameters sslParameters;
    protected boolean useClientMode;

    /* loaded from: classes4.dex */
    class AppDataInput extends InputStream {
        AppDataInput() {
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            int applicationDataAvailable;
            synchronized (ProvSSLSocketDirect.this) {
                applicationDataAvailable = ProvSSLSocketDirect.this.protocol == null ? 0 : ProvSSLSocketDirect.this.protocol.applicationDataAvailable();
            }
            return applicationDataAvailable;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            ProvSSLSocketDirect.this.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            ProvSSLSocketDirect.this.handshakeIfNecessary(true);
            byte[] bArr = new byte[1];
            if (ProvSSLSocketDirect.this.protocol.readApplicationData(bArr, 0, 1) < 0) {
                return -1;
            }
            return bArr[0] & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (i2 < 1) {
                return 0;
            }
            ProvSSLSocketDirect.this.handshakeIfNecessary(true);
            return ProvSSLSocketDirect.this.protocol.readApplicationData(bArr, i, i2);
        }
    }

    /* loaded from: classes4.dex */
    class AppDataOutput extends OutputStream {
        AppDataOutput() {
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            ProvSSLSocketDirect.this.close();
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            synchronized (ProvSSLSocketDirect.this) {
                if (ProvSSLSocketDirect.this.protocol != null) {
                    ProvSSLSocketDirect.this.protocol.flush();
                }
            }
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            ProvSSLSocketDirect.this.handshakeIfNecessary(true);
            ProvSSLSocketDirect.this.protocol.writeApplicationData(new byte[]{(byte) i}, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (i2 > 0) {
                ProvSSLSocketDirect.this.handshakeIfNecessary(true);
                ProvSSLSocketDirect.this.protocol.writeApplicationData(bArr, i, i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData) {
        this.appDataIn = new AppDataInput();
        this.appDataOut = new AppDataOutput();
        this.peerHost = null;
        this.peerHostSNI = null;
        this.enableSessionCreation = true;
        this.useClientMode = true;
        this.protocol = null;
        this.protocolPeer = null;
        this.connection = null;
        this.handshakeSession = null;
        this.contextData = contextData;
        this.sslParameters = contextData.getContext().getDefaultSSLParameters(this.useClientMode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData, String str, int i) throws IOException, UnknownHostException {
        this.appDataIn = new AppDataInput();
        this.appDataOut = new AppDataOutput();
        this.peerHost = null;
        this.peerHostSNI = null;
        this.enableSessionCreation = true;
        this.useClientMode = true;
        this.protocol = null;
        this.protocolPeer = null;
        this.connection = null;
        this.handshakeSession = null;
        this.contextData = contextData;
        this.sslParameters = contextData.getContext().getDefaultSSLParameters(this.useClientMode);
        this.peerHost = str;
        implConnect(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData, String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        this.appDataIn = new AppDataInput();
        this.appDataOut = new AppDataOutput();
        this.peerHost = null;
        this.peerHostSNI = null;
        this.enableSessionCreation = true;
        this.useClientMode = true;
        this.protocol = null;
        this.protocolPeer = null;
        this.connection = null;
        this.handshakeSession = null;
        this.contextData = contextData;
        this.sslParameters = contextData.getContext().getDefaultSSLParameters(this.useClientMode);
        this.peerHost = str;
        implBind(inetAddress, i2);
        implConnect(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData, InetAddress inetAddress, int i) throws IOException {
        this.appDataIn = new AppDataInput();
        this.appDataOut = new AppDataOutput();
        this.peerHost = null;
        this.peerHostSNI = null;
        this.enableSessionCreation = true;
        this.useClientMode = true;
        this.protocol = null;
        this.protocolPeer = null;
        this.connection = null;
        this.handshakeSession = null;
        this.contextData = contextData;
        this.sslParameters = contextData.getContext().getDefaultSSLParameters(this.useClientMode);
        implConnect(inetAddress, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData, InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        this.appDataIn = new AppDataInput();
        this.appDataOut = new AppDataOutput();
        this.peerHost = null;
        this.peerHostSNI = null;
        this.enableSessionCreation = true;
        this.useClientMode = true;
        this.protocol = null;
        this.protocolPeer = null;
        this.connection = null;
        this.handshakeSession = null;
        this.contextData = contextData;
        this.sslParameters = contextData.getContext().getDefaultSSLParameters(this.useClientMode);
        implBind(inetAddress2, i2);
        implConnect(inetAddress, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSocketDirect(ContextData contextData, boolean z, boolean z2, ProvSSLParameters provSSLParameters) {
        this.appDataIn = new AppDataInput();
        this.appDataOut = new AppDataOutput();
        this.peerHost = null;
        this.peerHostSNI = null;
        this.enableSessionCreation = true;
        this.useClientMode = true;
        this.protocol = null;
        this.protocolPeer = null;
        this.connection = null;
        this.handshakeSession = null;
        this.contextData = contextData;
        this.enableSessionCreation = z;
        this.useClientMode = z2;
        this.sslParameters = provSSLParameters;
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
        return getContextData().getX509KeyManager().chooseClientKeyBC(strArr, (Principal[]) JsseUtils.clone(principalArr), this);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public BCX509Key chooseServerKey(String str, Principal[] principalArr) {
        return getContextData().getX509KeyManager().chooseServerKeyBC(str, (Principal[]) JsseUtils.clone(principalArr), this);
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.protocol == null) {
            closeSocket();
        } else {
            this.protocol.close();
        }
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress, int i) throws IOException {
        if (socketAddress instanceof InetSocketAddress) {
            super.connect(socketAddress, i);
            notifyConnected();
            return;
        }
        throw new SocketException("Only InetSocketAddress is supported.");
    }

    protected void finalize() throws Throwable {
        try {
            try {
                try {
                    close();
                } catch (IOException unused) {
                    super.close();
                }
            } catch (IOException unused2) {
            }
        } finally {
            super.finalize();
        }
    }

    @Override // javax.net.ssl.SSLSocket, org.bouncycastle.jsse.BCSSLSocket
    public synchronized String getApplicationProtocol() {
        return this.connection == null ? null : this.connection.getApplicationProtocol();
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCApplicationProtocolSelector<SSLSocket> getBCHandshakeApplicationProtocolSelector() {
        return this.sslParameters.getSocketAPSelector();
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCExtendedSSLSession getBCHandshakeSession() {
        return this.handshakeSession;
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public BCExtendedSSLSession getBCSession() {
        return getSessionImpl();
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCSSLConnection getConnection() {
        try {
            handshakeIfNecessary(false);
        } catch (IOException e) {
            LOG.log(Level.FINE, "Failed to establish connection", (Throwable) e);
        }
        return this.connection;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public ContextData getContextData() {
        return this.contextData;
    }

    @Override // javax.net.ssl.SSLSocket, org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized boolean getEnableSessionCreation() {
        return this.enableSessionCreation;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getEnabledCipherSuites() {
        return this.sslParameters.getCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getEnabledProtocols() {
        return this.sslParameters.getProtocols();
    }

    @Override // javax.net.ssl.SSLSocket, org.bouncycastle.jsse.BCSSLSocket
    public synchronized String getHandshakeApplicationProtocol() {
        return this.handshakeSession == null ? null : this.handshakeSession.getApplicationProtocol();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized SSLSession getHandshakeSession() {
        return this.handshakeSession == null ? null : this.handshakeSession.getExportSSLSession();
    }

    @Override // java.net.Socket
    public InputStream getInputStream() throws IOException {
        return this.appDataIn;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized boolean getNeedClientAuth() {
        return this.sslParameters.getNeedClientAuth();
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() throws IOException {
        return this.appDataOut;
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCSSLParameters getParameters() {
        return SSLParametersUtil.getParameters(this.sslParameters);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String getPeerHost() {
        return this.peerHost;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String getPeerHostSNI() {
        return this.peerHostSNI;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public int getPeerPort() {
        return getPort();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized SSLParameters getSSLParameters() {
        return SSLParametersUtil.getSSLParameters(this.sslParameters);
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLSession getSession() {
        return getSessionImpl().getExportSSLSession();
    }

    synchronized ProvSSLSession getSessionImpl() {
        getConnection();
        return this.connection == null ? ProvSSLSession.NULL_SESSION : this.connection.mo12855getSession();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getSupportedCipherSuites() {
        return this.contextData.getContext().getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getSupportedProtocols() {
        return this.contextData.getContext().getSupportedProtocols();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized boolean getUseClientMode() {
        return this.useClientMode;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized boolean getWantClientAuth() {
        return this.sslParameters.getWantClientAuth();
    }

    synchronized void handshakeIfNecessary(boolean z) throws IOException {
        if (this.protocol == null || this.protocol.isHandshaking()) {
            startHandshake(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void notifyConnected() {
        if (this.peerHost != null && this.peerHost.length() > 0) {
            this.peerHostSNI = this.peerHost;
            return;
        }
        InetAddress inetAddress = getInetAddress();
        if (inetAddress == null) {
            return;
        }
        this.peerHost = (!this.useClientMode || !ProvSSLSocketBase.provJdkTlsTrustNameService) ? inetAddress.getHostAddress() : inetAddress.getHostName();
        this.peerHostSNI = null;
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
        notifyHandshakeCompletedListeners(provSSLConnection.mo12855getSession().exportSSLSession);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized void notifyHandshakeSession(ProvSSLSessionHandshake provSSLSessionHandshake) {
        this.handshakeSession = provSSLSessionHandshake;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String selectApplicationProtocol(List<String> list) {
        return this.sslParameters.getSocketAPSelector().select(this, list);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setBCHandshakeApplicationProtocolSelector(BCApplicationProtocolSelector<SSLSocket> bCApplicationProtocolSelector) {
        this.sslParameters.setSocketAPSelector(bCApplicationProtocolSelector);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setBCSessionToResume(BCExtendedSSLSession bCExtendedSSLSession) {
        try {
            if (bCExtendedSSLSession == null) {
                throw new NullPointerException("'session' cannot be null");
            }
            if (!(bCExtendedSSLSession instanceof ProvSSLSession)) {
                throw new IllegalArgumentException("Session-to-resume must be a session returned from 'getBCSession'");
            }
            if (this.protocol != null) {
                throw new IllegalArgumentException("Session-to-resume cannot be set after the handshake has begun");
            }
            this.sslParameters.setSessionToResume((ProvSSLSession) bCExtendedSSLSession);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setEnableSessionCreation(boolean z) {
        this.enableSessionCreation = z;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setEnabledCipherSuites(String[] strArr) {
        this.sslParameters.setCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setEnabledProtocols(String[] strArr) {
        this.sslParameters.setProtocols(strArr);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setHost(String str) {
        this.peerHost = str;
        this.peerHostSNI = str;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setNeedClientAuth(boolean z) {
        this.sslParameters.setNeedClientAuth(z);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setParameters(BCSSLParameters bCSSLParameters) {
        SSLParametersUtil.setParameters(this.sslParameters, bCSSLParameters);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setSSLParameters(SSLParameters sSLParameters) {
        SSLParametersUtil.setSSLParameters(this.sslParameters, sSLParameters);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setUseClientMode(boolean z) {
        if (this.protocol != null) {
            throw new IllegalArgumentException("Mode cannot be changed after the initial handshake has begun");
        }
        if (this.useClientMode != z) {
            this.contextData.getContext().updateDefaultSSLParameters(this.sslParameters, z);
            this.useClientMode = z;
        }
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setWantClientAuth(boolean z) {
        this.sslParameters.setWantClientAuth(z);
    }

    @Override // java.net.Socket
    public void shutdownInput() throws IOException {
        throw new UnsupportedOperationException("shutdownInput() not supported in TLS");
    }

    @Override // java.net.Socket
    public void shutdownOutput() throws IOException {
        throw new UnsupportedOperationException("shutdownOutput() not supported in TLS");
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void startHandshake() throws IOException {
        startHandshake(true);
    }

    protected void startHandshake(boolean z) throws IOException {
        TlsProtocol tlsProtocol = this.protocol;
        if (tlsProtocol != null) {
            if (!tlsProtocol.isHandshaking()) {
                throw new UnsupportedOperationException("Renegotiation not supported");
            }
            this.protocol.setResumableHandshake(z);
            this.protocol.resumeHandshake();
            return;
        }
        InputStream inputStream = super.getInputStream();
        OutputStream outputStream = super.getOutputStream();
        if (this.useClientMode) {
            ProvTlsClientProtocol provTlsClientProtocol = new ProvTlsClientProtocol(inputStream, outputStream, this.socketCloser);
            provTlsClientProtocol.setResumableHandshake(z);
            this.protocol = provTlsClientProtocol;
            ProvTlsClient provTlsClient = new ProvTlsClient(this, this.sslParameters);
            this.protocolPeer = provTlsClient;
            provTlsClientProtocol.connect(provTlsClient);
            return;
        }
        ProvTlsServerProtocol provTlsServerProtocol = new ProvTlsServerProtocol(inputStream, outputStream, this.socketCloser);
        provTlsServerProtocol.setResumableHandshake(z);
        this.protocol = provTlsServerProtocol;
        ProvTlsServer provTlsServer = new ProvTlsServer(this, this.sslParameters);
        this.protocolPeer = provTlsServer;
        provTlsServerProtocol.accept(provTlsServer);
    }
}
