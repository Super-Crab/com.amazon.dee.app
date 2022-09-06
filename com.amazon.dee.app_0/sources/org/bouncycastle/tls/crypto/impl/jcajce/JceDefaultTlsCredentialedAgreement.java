package org.bouncycastle.tls.crypto.impl.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import javax.crypto.interfaces.DHPrivateKey;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.TlsCredentialedAgreement;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCryptoException;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes5.dex */
public class JceDefaultTlsCredentialedAgreement implements TlsCredentialedAgreement {
    private final String agreementAlgorithm;
    private final Certificate certificate;
    private final JcaTlsCrypto crypto;
    private final PrivateKey privateKey;

    public JceDefaultTlsCredentialedAgreement(JcaTlsCrypto jcaTlsCrypto, Certificate certificate, PrivateKey privateKey) {
        if (jcaTlsCrypto != null) {
            if (certificate == null) {
                throw new IllegalArgumentException("'certificate' cannot be null");
            }
            if (certificate.isEmpty()) {
                throw new IllegalArgumentException("'certificate' cannot be empty");
            }
            if (privateKey == null) {
                throw new IllegalArgumentException("'privateKey' cannot be null");
            }
            this.crypto = jcaTlsCrypto;
            this.certificate = certificate;
            this.privateKey = privateKey;
            this.agreementAlgorithm = getAgreementAlgorithm(privateKey);
            return;
        }
        throw new IllegalArgumentException("'crypto' cannot be null");
    }

    public static String getAgreementAlgorithm(PrivateKey privateKey) {
        if (privateKey instanceof DHPrivateKey) {
            return "DH";
        }
        if (ECUtil.isECPrivateKey(privateKey)) {
            return "ECDH";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'privateKey' type not supported: ");
        outline107.append(privateKey.getClass().getName());
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // org.bouncycastle.tls.TlsCredentialedAgreement
    public TlsSecret generateAgreement(TlsCertificate tlsCertificate) throws IOException {
        try {
            return this.crypto.adoptLocalSecret(this.crypto.calculateKeyAgreement(this.agreementAlgorithm, this.privateKey, JcaTlsCertificate.convert(this.crypto, tlsCertificate).getPublicKey(), "TlsPremasterSecret"));
        } catch (GeneralSecurityException e) {
            throw new TlsCryptoException("unable to perform agreement", e);
        }
    }

    @Override // org.bouncycastle.tls.TlsCredentials
    public Certificate getCertificate() {
        return this.certificate;
    }
}
