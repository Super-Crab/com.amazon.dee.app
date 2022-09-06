package org.bouncycastle.tls;

import java.io.IOException;
import org.bouncycastle.tls.crypto.TlsCrypto;
/* loaded from: classes5.dex */
public class PSKTlsServer extends AbstractTlsServer {
    private static final int[] DEFAULT_CIPHER_SUITES = {CipherSuite.TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256, 171, 170, 179, 178, 145, 144};
    protected TlsPSKIdentityManager pskIdentityManager;

    public PSKTlsServer(TlsCrypto tlsCrypto, TlsPSKIdentityManager tlsPSKIdentityManager) {
        super(tlsCrypto);
        this.pskIdentityManager = tlsPSKIdentityManager;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsCredentials getCredentials() throws IOException {
        int keyExchangeAlgorithm = this.context.getSecurityParametersHandshake().getKeyExchangeAlgorithm();
        if (keyExchangeAlgorithm != 24) {
            switch (keyExchangeAlgorithm) {
                case 13:
                case 14:
                    return null;
                case 15:
                    return getRSAEncryptionCredentials();
                default:
                    throw new TlsFatalAlert((short) 80);
            }
        }
        return null;
    }

    public ProtocolVersion getMaximumVersion() {
        return ProtocolVersion.TLSv12;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public TlsPSKIdentityManager getPSKIdentityManager() {
        return this.pskIdentityManager;
    }

    protected TlsCredentialedDecryptor getRSAEncryptionCredentials() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.AbstractTlsPeer
    public int[] getSupportedCipherSuites() {
        return TlsUtils.getSupportedCipherSuites(mo12857getCrypto(), DEFAULT_CIPHER_SUITES);
    }
}
