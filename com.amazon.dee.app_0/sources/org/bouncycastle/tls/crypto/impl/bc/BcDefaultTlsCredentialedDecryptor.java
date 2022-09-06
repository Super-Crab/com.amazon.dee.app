package org.bouncycastle.tls.crypto.impl.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.SecureRandom;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsCredentialedDecryptor;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class BcDefaultTlsCredentialedDecryptor implements TlsCredentialedDecryptor {
    protected Certificate certificate;
    protected BcTlsCrypto crypto;
    protected AsymmetricKeyParameter privateKey;

    public BcDefaultTlsCredentialedDecryptor(BcTlsCrypto bcTlsCrypto, Certificate certificate, AsymmetricKeyParameter asymmetricKeyParameter) {
        if (bcTlsCrypto != null) {
            if (certificate == null) {
                throw new IllegalArgumentException("'certificate' cannot be null");
            }
            if (certificate.isEmpty()) {
                throw new IllegalArgumentException("'certificate' cannot be empty");
            }
            if (asymmetricKeyParameter == null) {
                throw new IllegalArgumentException("'privateKey' cannot be null");
            }
            if (!asymmetricKeyParameter.isPrivate()) {
                throw new IllegalArgumentException("'privateKey' must be private");
            }
            if (!(asymmetricKeyParameter instanceof RSAKeyParameters)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'privateKey' type not supported: ");
                outline107.append(asymmetricKeyParameter.getClass().getName());
                throw new IllegalArgumentException(outline107.toString());
            }
            this.crypto = bcTlsCrypto;
            this.certificate = certificate;
            this.privateKey = asymmetricKeyParameter;
            return;
        }
        throw new IllegalArgumentException("'crypto' cannot be null");
    }

    @Override // org.bouncycastle.tls.TlsCredentialedDecryptor
    public TlsSecret decrypt(TlsCryptoParameters tlsCryptoParameters, byte[] bArr) throws IOException {
        return safeDecryptPreMasterSecret(tlsCryptoParameters, (RSAKeyParameters) this.privateKey, bArr);
    }

    @Override // org.bouncycastle.tls.TlsCredentials
    public Certificate getCertificate() {
        return this.certificate;
    }

    protected TlsSecret safeDecryptPreMasterSecret(TlsCryptoParameters tlsCryptoParameters, RSAKeyParameters rSAKeyParameters, byte[] bArr) {
        SecureRandom secureRandom = this.crypto.getSecureRandom();
        ProtocolVersion rSAPreMasterSecretVersion = tlsCryptoParameters.getRSAPreMasterSecretVersion();
        byte[] bArr2 = new byte[48];
        secureRandom.nextBytes(bArr2);
        byte[] clone = Arrays.clone(bArr2);
        try {
            PKCS1Encoding pKCS1Encoding = new PKCS1Encoding(new RSABlindedEngine(), bArr2);
            pKCS1Encoding.init(false, new ParametersWithRandom(rSAKeyParameters, secureRandom));
            clone = pKCS1Encoding.processBlock(bArr, 0, bArr.length);
        } catch (Exception unused) {
        }
        int minorVersion = (((rSAPreMasterSecretVersion.getMinorVersion() ^ (clone[1] & 255)) | (rSAPreMasterSecretVersion.getMajorVersion() ^ (clone[0] & 255))) - 1) >> 31;
        for (int i = 0; i < 48; i++) {
            clone[i] = (byte) ((clone[i] & minorVersion) | (bArr2[i] & (~minorVersion)));
        }
        return this.crypto.createSecret(clone);
    }
}
