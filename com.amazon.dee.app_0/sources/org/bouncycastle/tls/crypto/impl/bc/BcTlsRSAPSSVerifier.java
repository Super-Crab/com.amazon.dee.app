package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAlgorithm;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
/* loaded from: classes5.dex */
public class BcTlsRSAPSSVerifier extends BcTlsVerifier {
    private final short signatureAlgorithm;

    public BcTlsRSAPSSVerifier(BcTlsCrypto bcTlsCrypto, RSAKeyParameters rSAKeyParameters, short s) {
        super(bcTlsCrypto, rSAKeyParameters);
        if (SignatureAlgorithm.isRSAPSS(s)) {
            this.signatureAlgorithm = s;
            return;
        }
        throw new IllegalArgumentException("signatureAlgorithm");
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsVerifier, org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm == null || algorithm.getSignature() != this.signatureAlgorithm || algorithm.getHash() != 8) {
            throw new IllegalStateException("Invalid algorithm: " + algorithm);
        }
        Digest createDigest = this.crypto.createDigest(SignatureAlgorithm.getRSAPSSHashAlgorithm(this.signatureAlgorithm));
        PSSSigner pSSSigner = new PSSSigner(new RSAEngine(), createDigest, createDigest.getDigestSize());
        pSSSigner.init(false, this.publicKey);
        return new BcTlsStreamVerifier(pSSSigner, digitallySigned.getSignature());
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) throws IOException {
        throw new UnsupportedOperationException();
    }
}
