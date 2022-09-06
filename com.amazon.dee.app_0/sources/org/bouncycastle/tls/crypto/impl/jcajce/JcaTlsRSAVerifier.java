package org.bouncycastle.tls.crypto.impl.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.Signature;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes5.dex */
public class JcaTlsRSAVerifier implements TlsVerifier {
    private final JcaTlsCrypto crypto;
    private final PublicKey publicKey;
    private Signature rawVerifier = null;

    public JcaTlsRSAVerifier(JcaTlsCrypto jcaTlsCrypto, PublicKey publicKey) {
        if (jcaTlsCrypto != null) {
            if (publicKey == null) {
                throw new NullPointerException("publicKey");
            }
            this.crypto = jcaTlsCrypto;
            this.publicKey = publicKey;
            return;
        }
        throw new NullPointerException("crypto");
    }

    protected Signature getRawVerifier() throws GeneralSecurityException {
        if (this.rawVerifier == null) {
            this.rawVerifier = this.crypto.getHelper().createSignature("NoneWithRSA");
            this.rawVerifier.initVerify(this.publicKey);
        }
        return this.rawVerifier;
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) throws IOException {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm == null || algorithm.getSignature() != 1 || !JcaUtils.isSunMSCAPIProviderActive() || !isSunMSCAPIRawVerifier()) {
            return null;
        }
        return this.crypto.createStreamVerifier(digitallySigned, this.publicKey);
    }

    protected boolean isSunMSCAPIRawVerifier() throws IOException {
        try {
            return JcaUtils.isSunMSCAPIProvider(getRawVerifier().getProvider());
        } catch (GeneralSecurityException unused) {
            return true;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) throws IOException {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        try {
            Signature rawVerifier = getRawVerifier();
            if (algorithm == null) {
                rawVerifier.update(bArr, 0, bArr.length);
            } else if (algorithm.getSignature() != 1) {
                throw new IllegalStateException("Invalid algorithm: " + algorithm);
            } else {
                byte[] encoded = new DigestInfo(new AlgorithmIdentifier(TlsUtils.getOIDForHashAlgorithm(algorithm.getHash()), DERNull.INSTANCE), bArr).getEncoded();
                rawVerifier.update(encoded, 0, encoded.length);
            }
            return rawVerifier.verify(digitallySigned.getSignature());
        } catch (GeneralSecurityException e) {
            throw Exceptions.illegalStateException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("unable to process signature: ")), e);
        }
    }
}
