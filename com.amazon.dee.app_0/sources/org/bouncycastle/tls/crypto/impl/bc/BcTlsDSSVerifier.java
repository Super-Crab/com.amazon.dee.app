package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
/* loaded from: classes5.dex */
public abstract class BcTlsDSSVerifier extends BcTlsVerifier {
    /* JADX INFO: Access modifiers changed from: protected */
    public BcTlsDSSVerifier(BcTlsCrypto bcTlsCrypto, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(bcTlsCrypto, asymmetricKeyParameter);
    }

    protected abstract DSA createDSAImpl(short s);

    protected abstract short getSignatureAlgorithm();

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null && algorithm.getSignature() != getSignatureAlgorithm()) {
            throw new IllegalStateException("Invalid algorithm: " + algorithm);
        }
        DSADigestSigner dSADigestSigner = new DSADigestSigner(createDSAImpl(algorithm == null ? (short) 2 : algorithm.getHash()), this.crypto.createDigest((short) 0));
        dSADigestSigner.init(false, this.publicKey);
        if (algorithm == null) {
            dSADigestSigner.update(bArr, 16, 20);
        } else {
            dSADigestSigner.update(bArr, 0, bArr.length);
        }
        return dSADigestSigner.verifySignature(digitallySigned.getSignature());
    }
}
