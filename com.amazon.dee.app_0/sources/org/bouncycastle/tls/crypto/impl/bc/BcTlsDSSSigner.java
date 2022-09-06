package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsFatalAlert;
/* loaded from: classes5.dex */
public abstract class BcTlsDSSSigner extends BcTlsSigner {
    /* JADX INFO: Access modifiers changed from: protected */
    public BcTlsDSSSigner(BcTlsCrypto bcTlsCrypto, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(bcTlsCrypto, asymmetricKeyParameter);
    }

    protected abstract DSA createDSAImpl(short s);

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) throws IOException {
        if (signatureAndHashAlgorithm != null && signatureAndHashAlgorithm.getSignature() != getSignatureAlgorithm()) {
            throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
        }
        DSADigestSigner dSADigestSigner = new DSADigestSigner(createDSAImpl(signatureAndHashAlgorithm == null ? (short) 2 : signatureAndHashAlgorithm.getHash()), this.crypto.createDigest((short) 0));
        dSADigestSigner.init(true, new ParametersWithRandom(this.privateKey, this.crypto.getSecureRandom()));
        if (signatureAndHashAlgorithm == null) {
            dSADigestSigner.update(bArr, 16, 20);
        } else {
            dSADigestSigner.update(bArr, 0, bArr.length);
        }
        try {
            return dSADigestSigner.generateSignature();
        } catch (CryptoException e) {
            throw new TlsFatalAlert((short) 80, (Throwable) e);
        }
    }

    protected abstract short getSignatureAlgorithm();
}
