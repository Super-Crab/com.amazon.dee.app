package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed448Signer;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes5.dex */
public class BcTlsEd448Signer extends BcTlsSigner {
    public BcTlsEd448Signer(BcTlsCrypto bcTlsCrypto, Ed448PrivateKeyParameters ed448PrivateKeyParameters) {
        super(bcTlsCrypto, ed448PrivateKeyParameters);
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsSigner, org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm != null && signatureAndHashAlgorithm.getSignature() == 8 && signatureAndHashAlgorithm.getHash() == 8) {
            Ed448Signer ed448Signer = new Ed448Signer(TlsUtils.EMPTY_BYTES);
            ed448Signer.init(true, this.privateKey);
            return new BcTlsStreamSigner(ed448Signer);
        }
        throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
    }
}
