package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.crypto.params.Ed448PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed448Signer;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
/* loaded from: classes5.dex */
public class BcTlsEd448Verifier extends BcTlsVerifier {
    public BcTlsEd448Verifier(BcTlsCrypto bcTlsCrypto, Ed448PublicKeyParameters ed448PublicKeyParameters) {
        super(bcTlsCrypto, ed448PublicKeyParameters);
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsVerifier, org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null && algorithm.getSignature() == 8 && algorithm.getHash() == 8) {
            Ed448Signer ed448Signer = new Ed448Signer(TlsUtils.EMPTY_BYTES);
            ed448Signer.init(false, this.publicKey);
            return new BcTlsStreamVerifier(ed448Signer, digitallySigned.getSignature());
        }
        throw new IllegalStateException("Invalid algorithm: " + algorithm);
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) throws IOException {
        throw new UnsupportedOperationException();
    }
}
