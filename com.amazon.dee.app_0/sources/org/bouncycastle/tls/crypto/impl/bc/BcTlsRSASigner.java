package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.GenericSigner;
import org.bouncycastle.crypto.signers.RSADigestSigner;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
/* loaded from: classes5.dex */
public class BcTlsRSASigner extends BcTlsSigner {
    private final RSAKeyParameters publicKey;

    public BcTlsRSASigner(BcTlsCrypto bcTlsCrypto, RSAKeyParameters rSAKeyParameters, RSAKeyParameters rSAKeyParameters2) {
        super(bcTlsCrypto, rSAKeyParameters);
        this.publicKey = rSAKeyParameters2;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) throws IOException {
        Signer genericSigner;
        Digest createDigest = this.crypto.createDigest((short) 0);
        if (signatureAndHashAlgorithm == null) {
            genericSigner = new GenericSigner(new PKCS1Encoding(new RSABlindedEngine()), createDigest);
        } else if (signatureAndHashAlgorithm.getSignature() != 1) {
            throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
        } else {
            genericSigner = new RSADigestSigner(createDigest, TlsUtils.getOIDForHashAlgorithm(signatureAndHashAlgorithm.getHash()));
        }
        genericSigner.init(true, new ParametersWithRandom(this.privateKey, this.crypto.getSecureRandom()));
        genericSigner.update(bArr, 0, bArr.length);
        try {
            byte[] generateSignature = genericSigner.generateSignature();
            genericSigner.init(false, this.publicKey);
            genericSigner.update(bArr, 0, bArr.length);
            if (!genericSigner.verifySignature(generateSignature)) {
                throw new TlsFatalAlert((short) 80);
            }
            return generateSignature;
        } catch (CryptoException e) {
            throw new TlsFatalAlert((short) 80, (Throwable) e);
        }
    }
}
