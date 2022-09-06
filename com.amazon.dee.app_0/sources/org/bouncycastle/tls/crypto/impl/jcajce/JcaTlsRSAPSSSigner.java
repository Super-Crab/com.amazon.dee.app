package org.bouncycastle.tls.crypto.impl.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.PrivateKey;
import org.bouncycastle.tls.SignatureAlgorithm;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsSigner;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes5.dex */
public class JcaTlsRSAPSSSigner implements TlsSigner {
    private final JcaTlsCrypto crypto;
    private final PrivateKey privateKey;
    private final short signatureAlgorithm;

    public JcaTlsRSAPSSSigner(JcaTlsCrypto jcaTlsCrypto, PrivateKey privateKey, short s) {
        if (jcaTlsCrypto != null) {
            if (privateKey == null) {
                throw new NullPointerException("privateKey");
            }
            if (!SignatureAlgorithm.isRSAPSS(s)) {
                throw new IllegalArgumentException("signatureAlgorithm");
            }
            this.crypto = jcaTlsCrypto;
            this.privateKey = privateKey;
            this.signatureAlgorithm = s;
            return;
        }
        throw new NullPointerException("crypto");
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) throws IOException {
        if (signatureAndHashAlgorithm == null || signatureAndHashAlgorithm.getSignature() != this.signatureAlgorithm || signatureAndHashAlgorithm.getHash() != 8) {
            throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
        }
        short rSAPSSHashAlgorithm = SignatureAlgorithm.getRSAPSSHashAlgorithm(this.signatureAlgorithm);
        String digestName = this.crypto.getDigestName(rSAPSSHashAlgorithm);
        return this.crypto.createStreamSigner(GeneratedOutlineSupport1.outline91(new StringBuilder(), RSAUtil.getDigestSigAlgName(digestName), "WITHRSAANDMGF1"), RSAUtil.getPSSParameterSpec(rSAPSSHashAlgorithm, digestName, this.crypto.getHelper()), this.privateKey, true);
    }
}
