package org.bouncycastle.tls.crypto.impl.jcajce;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.RSAPrivateKey;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.DefaultTlsCredentialedSigner;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsSigner;
/* loaded from: classes5.dex */
public class JcaDefaultTlsCredentialedSigner extends DefaultTlsCredentialedSigner {
    public JcaDefaultTlsCredentialedSigner(TlsCryptoParameters tlsCryptoParameters, JcaTlsCrypto jcaTlsCrypto, PrivateKey privateKey, Certificate certificate, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        super(tlsCryptoParameters, makeSigner(jcaTlsCrypto, privateKey, certificate, signatureAndHashAlgorithm), certificate, signatureAndHashAlgorithm);
    }

    private static JcaTlsCertificate getEndEntity(JcaTlsCrypto jcaTlsCrypto, Certificate certificate) throws IOException {
        if (certificate == null || certificate.isEmpty()) {
            throw new IllegalArgumentException("No certificate");
        }
        return JcaTlsCertificate.convert(jcaTlsCrypto, certificate.getCertificateAt(0));
    }

    private static TlsSigner makeSigner(JcaTlsCrypto jcaTlsCrypto, PrivateKey privateKey, Certificate certificate, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        String algorithm = privateKey.getAlgorithm();
        if ((privateKey instanceof RSAPrivateKey) || KeyUtils.ALGORITHM_RSA.equalsIgnoreCase(algorithm) || "RSASSA-PSS".equalsIgnoreCase(algorithm)) {
            if (signatureAndHashAlgorithm != null) {
                short signature = signatureAndHashAlgorithm.getSignature();
                switch (signature) {
                    case 4:
                    case 5:
                    case 6:
                    case 9:
                    case 10:
                    case 11:
                        return new JcaTlsRSAPSSSigner(jcaTlsCrypto, privateKey, signature);
                }
            }
            try {
                return new JcaTlsRSASigner(jcaTlsCrypto, privateKey, getEndEntity(jcaTlsCrypto, certificate).getPubKeyRSA());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ((privateKey instanceof DSAPrivateKey) || "DSA".equalsIgnoreCase(algorithm)) {
            return new JcaTlsDSASigner(jcaTlsCrypto, privateKey);
        } else {
            if (ECUtil.isECPrivateKey(privateKey)) {
                return new JcaTlsECDSASigner(jcaTlsCrypto, privateKey);
            }
            if (EdDSAParameterSpec.Ed25519.equalsIgnoreCase(algorithm)) {
                return new JcaTlsEd25519Signer(jcaTlsCrypto, privateKey);
            }
            if (EdDSAParameterSpec.Ed448.equalsIgnoreCase(algorithm)) {
                return new JcaTlsEd448Signer(jcaTlsCrypto, privateKey);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'privateKey' type not supported: ");
            outline107.append(privateKey.getClass().getName());
            throw new IllegalArgumentException(outline107.toString());
        }
    }
}
