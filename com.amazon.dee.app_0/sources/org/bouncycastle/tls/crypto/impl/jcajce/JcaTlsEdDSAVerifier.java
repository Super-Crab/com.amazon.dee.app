package org.bouncycastle.tls.crypto.impl.jcajce;

import java.io.IOException;
import java.security.PublicKey;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes5.dex */
public class JcaTlsEdDSAVerifier implements TlsVerifier {
    protected final String algorithmName;
    protected final short algorithmType;
    protected final JcaTlsCrypto crypto;
    protected final PublicKey publicKey;

    public JcaTlsEdDSAVerifier(JcaTlsCrypto jcaTlsCrypto, PublicKey publicKey, short s, String str) {
        if (jcaTlsCrypto != null) {
            if (publicKey == null) {
                throw new NullPointerException("publicKey");
            }
            this.crypto = jcaTlsCrypto;
            this.publicKey = publicKey;
            this.algorithmType = s;
            this.algorithmName = str;
            return;
        }
        throw new NullPointerException("crypto");
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) throws IOException {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null && algorithm.getSignature() == this.algorithmType && algorithm.getHash() == 8) {
            return this.crypto.createStreamVerifier(this.algorithmName, null, digitallySigned.getSignature(), this.publicKey);
        }
        throw new IllegalStateException("Invalid algorithm: " + algorithm);
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) throws IOException {
        throw new UnsupportedOperationException();
    }
}
