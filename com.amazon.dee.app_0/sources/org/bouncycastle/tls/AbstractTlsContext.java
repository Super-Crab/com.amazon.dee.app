package org.bouncycastle.tls;

import java.io.IOException;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsNonceGenerator;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Times;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public abstract class AbstractTlsContext implements TlsContext {
    private static long counter = Times.nanoTime();
    private int connectionEnd;
    private TlsCrypto crypto;
    private TlsNonceGenerator nonceGenerator;
    private SecurityParameters securityParametersHandshake = null;
    private SecurityParameters securityParametersConnection = null;
    private ProtocolVersion[] clientSupportedVersions = null;
    private ProtocolVersion clientVersion = null;
    private ProtocolVersion rsaPreMasterSecretVersion = null;
    private TlsSession session = null;
    private Object userObject = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractTlsContext(TlsCrypto tlsCrypto, int i) {
        this.crypto = tlsCrypto;
        this.connectionEnd = i;
        this.nonceGenerator = createNonceGenerator(tlsCrypto, i);
    }

    private static TlsNonceGenerator createNonceGenerator(TlsCrypto tlsCrypto, int i) {
        byte[] bArr = new byte[16];
        Pack.longToBigEndian(nextCounterValue(), bArr, 0);
        Pack.longToBigEndian(Times.nanoTime(), bArr, 8);
        bArr[0] = (byte) i;
        return tlsCrypto.createNonceGenerator(bArr);
    }

    private static synchronized long nextCounterValue() {
        long j;
        synchronized (AbstractTlsContext.class) {
            j = counter + 1;
            counter = j;
        }
        return j;
    }

    protected TlsSecret checkEarlyExportSecret(TlsSecret tlsSecret) {
        if (tlsSecret != null) {
            return tlsSecret;
        }
        throw new IllegalStateException("Export of early key material not available for this handshake");
    }

    protected TlsSecret checkExportSecret(TlsSecret tlsSecret) {
        if (tlsSecret != null) {
            return tlsSecret;
        }
        throw new IllegalStateException("Export of key material only available from notifyHandshakeComplete()");
    }

    @Override // org.bouncycastle.tls.TlsContext
    public byte[] exportChannelBinding(int i) {
        SecurityParameters securityParametersConnection = getSecurityParametersConnection();
        if (securityParametersConnection != null) {
            if (i != 0) {
                if (i != 1) {
                    throw new UnsupportedOperationException();
                }
                return Arrays.clone(securityParametersConnection.getTLSUnique());
            }
            byte[] tLSServerEndPoint = securityParametersConnection.getTLSServerEndPoint();
            if (!TlsUtils.isNullOrEmpty(tLSServerEndPoint)) {
                return Arrays.clone(tLSServerEndPoint);
            }
            return null;
        }
        throw new IllegalStateException("Export of channel bindings unavailable before handshake completion");
    }

    @Override // org.bouncycastle.tls.TlsContext
    public byte[] exportEarlyKeyingMaterial(String str, byte[] bArr, int i) {
        SecurityParameters securityParametersHandshake = getSecurityParametersHandshake();
        if (securityParametersHandshake != null) {
            return exportKeyingMaterial13(checkEarlyExportSecret(securityParametersHandshake.getEarlyExporterMasterSecret()), securityParametersHandshake.getPRFHashAlgorithm(), str, bArr, i);
        }
        throw new IllegalStateException("Export of early key material only available during handshake");
    }

    @Override // org.bouncycastle.tls.TlsContext
    public byte[] exportKeyingMaterial(String str, byte[] bArr, int i) {
        SecurityParameters securityParametersConnection = getSecurityParametersConnection();
        if (securityParametersConnection != null) {
            if (!securityParametersConnection.isExtendedMasterSecret()) {
                throw new IllegalStateException("Export of key material requires extended_master_secret");
            }
            if (TlsUtils.isTLSv13(securityParametersConnection.getNegotiatedVersion())) {
                return exportKeyingMaterial13(checkExportSecret(securityParametersConnection.getExporterMasterSecret()), securityParametersConnection.getPRFHashAlgorithm(), str, bArr, i);
            }
            return TlsUtils.PRF(securityParametersConnection, checkExportSecret(securityParametersConnection.getMasterSecret()), str, TlsUtils.calculateExporterSeed(securityParametersConnection, bArr), i).extract();
        }
        throw new IllegalStateException("Export of key material unavailable before handshake completion");
    }

    protected byte[] exportKeyingMaterial13(TlsSecret tlsSecret, short s, String str, byte[] bArr, int i) {
        if (bArr == null) {
            bArr = TlsUtils.EMPTY_BYTES;
        } else if (!TlsUtils.isValidUint16(bArr.length)) {
            throw new IllegalArgumentException("'context' must have length less than 2^16 (or be null)");
        }
        try {
            return TlsCryptoUtils.hkdfExpandLabel(tlsSecret, s, str, bArr, i).extract();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.bouncycastle.tls.TlsContext
    public ProtocolVersion[] getClientSupportedVersions() {
        return this.clientSupportedVersions;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public ProtocolVersion getClientVersion() {
        return this.clientVersion;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public TlsCrypto getCrypto() {
        return this.crypto;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public TlsNonceGenerator getNonceGenerator() {
        return this.nonceGenerator;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public ProtocolVersion getRSAPreMasterSecretVersion() {
        return this.rsaPreMasterSecretVersion;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public TlsSession getResumableSession() {
        TlsSession session = getSession();
        if (session == null || !session.isResumable()) {
            return null;
        }
        return session;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public synchronized SecurityParameters getSecurityParameters() {
        return this.securityParametersHandshake != null ? this.securityParametersHandshake : this.securityParametersConnection;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public synchronized SecurityParameters getSecurityParametersConnection() {
        return this.securityParametersConnection;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public synchronized SecurityParameters getSecurityParametersHandshake() {
        return this.securityParametersHandshake;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public ProtocolVersion getServerVersion() {
        return getSecurityParameters().getNegotiatedVersion();
    }

    @Override // org.bouncycastle.tls.TlsContext
    public TlsSession getSession() {
        return this.session;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public Object getUserObject() {
        return this.userObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void handshakeBeginning(TlsPeer tlsPeer) throws IOException {
        if (this.securityParametersHandshake != null) {
            throw new TlsFatalAlert((short) 80, "Handshake already started");
        }
        this.securityParametersHandshake = new SecurityParameters();
        this.securityParametersHandshake.entity = this.connectionEnd;
        if (this.securityParametersConnection != null) {
            throw new TlsFatalAlert((short) 80, "Renegotiation not supported");
        }
        tlsPeer.notifyHandshakeBeginning();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void handshakeComplete(TlsPeer tlsPeer, TlsSession tlsSession) throws IOException {
        if (this.securityParametersHandshake == null) {
            throw new TlsFatalAlert((short) 80);
        }
        this.session = tlsSession;
        this.securityParametersConnection = this.securityParametersHandshake;
        tlsPeer.notifyHandshakeComplete();
        this.securityParametersHandshake = null;
    }

    public void setClientSupportedVersions(ProtocolVersion[] protocolVersionArr) {
        this.clientSupportedVersions = protocolVersionArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setClientVersion(ProtocolVersion protocolVersion) {
        this.clientVersion = protocolVersion;
    }

    public void setRSAPreMasterSecretVersion(ProtocolVersion protocolVersion) {
        this.rsaPreMasterSecretVersion = protocolVersion;
    }

    @Override // org.bouncycastle.tls.TlsContext
    public void setUserObject(Object obj) {
        this.userObject = obj;
    }
}
