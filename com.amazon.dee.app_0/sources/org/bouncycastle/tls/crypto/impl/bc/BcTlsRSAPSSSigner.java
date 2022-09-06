package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.tls.HashAlgorithm;
import org.bouncycastle.tls.SignatureAlgorithm;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes5.dex */
public class BcTlsRSAPSSSigner extends BcTlsSigner {
    private final short signatureAlgorithm;

    public BcTlsRSAPSSSigner(BcTlsCrypto bcTlsCrypto, RSAKeyParameters rSAKeyParameters, short s) {
        super(bcTlsCrypto, rSAKeyParameters);
        if (SignatureAlgorithm.isRSAPSS(s)) {
            this.signatureAlgorithm = s;
            return;
        }
        throw new IllegalArgumentException("signatureAlgorithm");
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsSigner, org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm == null || signatureAndHashAlgorithm.getSignature() != this.signatureAlgorithm || signatureAndHashAlgorithm.getHash() != 8) {
            throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
        }
        short rSAPSSHashAlgorithm = SignatureAlgorithm.getRSAPSSHashAlgorithm(this.signatureAlgorithm);
        PSSSigner pSSSigner = new PSSSigner(new RSABlindedEngine(), this.crypto.createDigest(rSAPSSHashAlgorithm), HashAlgorithm.getOutputSize(rSAPSSHashAlgorithm));
        pSSSigner.init(true, new ParametersWithRandom(this.privateKey, this.crypto.getSecureRandom()));
        return new BcTlsStreamSigner(pSSSigner);
    }
}
