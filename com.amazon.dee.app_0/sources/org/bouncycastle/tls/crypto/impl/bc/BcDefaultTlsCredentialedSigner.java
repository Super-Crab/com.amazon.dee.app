package org.bouncycastle.tls.crypto.impl.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.DefaultTlsCredentialedSigner;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsSigner;
/* loaded from: classes5.dex */
public class BcDefaultTlsCredentialedSigner extends DefaultTlsCredentialedSigner {
    public BcDefaultTlsCredentialedSigner(TlsCryptoParameters tlsCryptoParameters, BcTlsCrypto bcTlsCrypto, AsymmetricKeyParameter asymmetricKeyParameter, Certificate certificate, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        super(tlsCryptoParameters, makeSigner(bcTlsCrypto, asymmetricKeyParameter, certificate, signatureAndHashAlgorithm), certificate, signatureAndHashAlgorithm);
    }

    private static BcTlsCertificate getEndEntity(BcTlsCrypto bcTlsCrypto, Certificate certificate) throws IOException {
        if (certificate == null || certificate.isEmpty()) {
            throw new IllegalArgumentException("No certificate");
        }
        return BcTlsCertificate.convert(bcTlsCrypto, certificate.getCertificateAt(0));
    }

    private static TlsSigner makeSigner(BcTlsCrypto bcTlsCrypto, AsymmetricKeyParameter asymmetricKeyParameter, Certificate certificate, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (asymmetricKeyParameter instanceof RSAKeyParameters) {
            RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) asymmetricKeyParameter;
            if (signatureAndHashAlgorithm != null) {
                short signature = signatureAndHashAlgorithm.getSignature();
                switch (signature) {
                    case 4:
                    case 5:
                    case 6:
                    case 9:
                    case 10:
                    case 11:
                        return new BcTlsRSAPSSSigner(bcTlsCrypto, rSAKeyParameters, signature);
                }
            }
            try {
                return new BcTlsRSASigner(bcTlsCrypto, rSAKeyParameters, getEndEntity(bcTlsCrypto, certificate).getPubKeyRSA());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (asymmetricKeyParameter instanceof DSAPrivateKeyParameters) {
            return new BcTlsDSASigner(bcTlsCrypto, (DSAPrivateKeyParameters) asymmetricKeyParameter);
        } else {
            if (asymmetricKeyParameter instanceof ECPrivateKeyParameters) {
                return new BcTlsECDSASigner(bcTlsCrypto, (ECPrivateKeyParameters) asymmetricKeyParameter);
            }
            if (asymmetricKeyParameter instanceof Ed25519PrivateKeyParameters) {
                return new BcTlsEd25519Signer(bcTlsCrypto, (Ed25519PrivateKeyParameters) asymmetricKeyParameter);
            }
            if (asymmetricKeyParameter instanceof Ed448PrivateKeyParameters) {
                return new BcTlsEd448Signer(bcTlsCrypto, (Ed448PrivateKeyParameters) asymmetricKeyParameter);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'privateKey' type not supported: ");
            outline107.append(asymmetricKeyParameter.getClass().getName());
            throw new IllegalArgumentException(outline107.toString());
        }
    }
}
