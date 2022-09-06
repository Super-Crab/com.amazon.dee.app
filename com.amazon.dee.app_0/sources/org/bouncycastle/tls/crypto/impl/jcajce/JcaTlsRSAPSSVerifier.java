package org.bouncycastle.tls.crypto.impl.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.PublicKey;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAlgorithm;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes5.dex */
public class JcaTlsRSAPSSVerifier implements TlsVerifier {
    private final JcaTlsCrypto crypto;
    private final PublicKey publicKey;
    private final short signatureAlgorithm;

    public JcaTlsRSAPSSVerifier(JcaTlsCrypto jcaTlsCrypto, PublicKey publicKey, short s) {
        if (jcaTlsCrypto != null) {
            if (publicKey == null) {
                throw new NullPointerException("publicKey");
            }
            if (!SignatureAlgorithm.isRSAPSS(s)) {
                throw new IllegalArgumentException("signatureAlgorithm");
            }
            this.crypto = jcaTlsCrypto;
            this.publicKey = publicKey;
            this.signatureAlgorithm = s;
            return;
        }
        throw new NullPointerException("crypto");
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) throws IOException {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm == null || algorithm.getSignature() != this.signatureAlgorithm || algorithm.getHash() != 8) {
            throw new IllegalStateException("Invalid algorithm: " + algorithm);
        }
        short rSAPSSHashAlgorithm = SignatureAlgorithm.getRSAPSSHashAlgorithm(this.signatureAlgorithm);
        String digestName = this.crypto.getDigestName(rSAPSSHashAlgorithm);
        return this.crypto.createStreamVerifier(GeneratedOutlineSupport1.outline91(new StringBuilder(), RSAUtil.getDigestSigAlgName(digestName), "WITHRSAANDMGF1"), RSAUtil.getPSSParameterSpec(rSAPSSHashAlgorithm, digestName, this.crypto.getHelper()), digitallySigned.getSignature(), this.publicKey);
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) throws IOException {
        throw new UnsupportedOperationException();
    }
}
