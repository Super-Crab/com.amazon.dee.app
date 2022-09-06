package org.bouncycastle.tls;

import java.io.IOException;
import org.bouncycastle.tls.crypto.TlsCrypto;
/* loaded from: classes5.dex */
public abstract class DefaultTlsServer extends AbstractTlsServer {
    private static final int[] DEFAULT_CIPHER_SUITES = {CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256, 159, 158, 107, 103, 57, 51, 157, 156, 61, 60, 53, 47};

    public DefaultTlsServer(TlsCrypto tlsCrypto) {
        super(tlsCrypto);
    }

    public TlsCredentials getCredentials() throws IOException {
        int keyExchangeAlgorithm = this.context.getSecurityParametersHandshake().getKeyExchangeAlgorithm();
        if (keyExchangeAlgorithm != 1) {
            if (keyExchangeAlgorithm == 3) {
                return getDSASignerCredentials();
            }
            if (keyExchangeAlgorithm != 5) {
                if (keyExchangeAlgorithm == 11) {
                    return null;
                }
                if (keyExchangeAlgorithm == 17) {
                    return getECDSASignerCredentials();
                }
                if (keyExchangeAlgorithm != 19) {
                    if (keyExchangeAlgorithm != 20) {
                        throw new TlsFatalAlert((short) 80);
                    }
                    return null;
                }
            }
            return getRSASignerCredentials();
        }
        return getRSAEncryptionCredentials();
    }

    protected TlsCredentialedSigner getDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    protected TlsCredentialedSigner getECDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    protected TlsCredentialedDecryptor getRSAEncryptionCredentials() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    protected TlsCredentialedSigner getRSASignerCredentials() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.AbstractTlsPeer
    public int[] getSupportedCipherSuites() {
        return TlsUtils.getSupportedCipherSuites(mo12857getCrypto(), DEFAULT_CIPHER_SUITES);
    }
}
