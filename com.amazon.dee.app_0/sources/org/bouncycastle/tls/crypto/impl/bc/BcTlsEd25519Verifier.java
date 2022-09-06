package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
/* loaded from: classes5.dex */
public class BcTlsEd25519Verifier extends BcTlsVerifier {
    public BcTlsEd25519Verifier(BcTlsCrypto bcTlsCrypto, Ed25519PublicKeyParameters ed25519PublicKeyParameters) {
        super(bcTlsCrypto, ed25519PublicKeyParameters);
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsVerifier, org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null && algorithm.getSignature() == 7 && algorithm.getHash() == 8) {
            Ed25519Signer ed25519Signer = new Ed25519Signer();
            ed25519Signer.init(false, this.publicKey);
            return new BcTlsStreamVerifier(ed25519Signer, digitallySigned.getSignature());
        }
        throw new IllegalStateException("Invalid algorithm: " + algorithm);
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) throws IOException {
        throw new UnsupportedOperationException();
    }
}
