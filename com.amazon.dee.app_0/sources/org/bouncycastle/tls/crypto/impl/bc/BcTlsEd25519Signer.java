package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes5.dex */
public class BcTlsEd25519Signer extends BcTlsSigner {
    public BcTlsEd25519Signer(BcTlsCrypto bcTlsCrypto, Ed25519PrivateKeyParameters ed25519PrivateKeyParameters) {
        super(bcTlsCrypto, ed25519PrivateKeyParameters);
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsSigner, org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm != null && signatureAndHashAlgorithm.getSignature() == 7 && signatureAndHashAlgorithm.getHash() == 8) {
            Ed25519Signer ed25519Signer = new Ed25519Signer();
            ed25519Signer.init(true, this.privateKey);
            return new BcTlsStreamSigner(ed25519Signer);
        }
        throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
    }
}
