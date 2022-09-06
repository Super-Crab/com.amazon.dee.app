package org.bouncycastle.tls.crypto.impl.jcajce;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsCredentialedDecryptor;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class JceDefaultTlsCredentialedDecryptor implements TlsCredentialedDecryptor {
    protected Certificate certificate;
    protected JcaTlsCrypto crypto;
    protected PrivateKey privateKey;

    public JceDefaultTlsCredentialedDecryptor(JcaTlsCrypto jcaTlsCrypto, Certificate certificate, PrivateKey privateKey) {
        if (jcaTlsCrypto != null) {
            if (certificate == null) {
                throw new IllegalArgumentException("'certificate' cannot be null");
            }
            if (certificate.isEmpty()) {
                throw new IllegalArgumentException("'certificate' cannot be empty");
            }
            if (privateKey == null) {
                throw new IllegalArgumentException("'privateKey' cannot be null");
            }
            if (!(privateKey instanceof RSAPrivateKey) && !KeyUtils.ALGORITHM_RSA.equals(privateKey.getAlgorithm())) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'privateKey' type not supported: ");
                outline107.append(privateKey.getClass().getName());
                throw new IllegalArgumentException(outline107.toString());
            }
            this.crypto = jcaTlsCrypto;
            this.certificate = certificate;
            this.privateKey = privateKey;
            return;
        }
        throw new IllegalArgumentException("'crypto' cannot be null");
    }

    @Override // org.bouncycastle.tls.TlsCredentialedDecryptor
    public TlsSecret decrypt(TlsCryptoParameters tlsCryptoParameters, byte[] bArr) throws IOException {
        return safeDecryptPreMasterSecret(tlsCryptoParameters, this.privateKey, bArr);
    }

    @Override // org.bouncycastle.tls.TlsCredentials
    public Certificate getCertificate() {
        return this.certificate;
    }

    protected TlsSecret safeDecryptPreMasterSecret(TlsCryptoParameters tlsCryptoParameters, PrivateKey privateKey, byte[] bArr) {
        SecureRandom secureRandom = this.crypto.getSecureRandom();
        ProtocolVersion rSAPreMasterSecretVersion = tlsCryptoParameters.getRSAPreMasterSecretVersion();
        byte[] bArr2 = new byte[48];
        secureRandom.nextBytes(bArr2);
        byte[] clone = Arrays.clone(bArr2);
        try {
            Cipher createRSAEncryptionCipher = this.crypto.createRSAEncryptionCipher();
            createRSAEncryptionCipher.init(2, privateKey);
            byte[] doFinal = createRSAEncryptionCipher.doFinal(bArr);
            if (doFinal != null) {
                if (doFinal.length == 48) {
                    clone = doFinal;
                }
            }
        } catch (Exception unused) {
        }
        int minorVersion = (((rSAPreMasterSecretVersion.getMinorVersion() ^ (clone[1] & 255)) | (rSAPreMasterSecretVersion.getMajorVersion() ^ (clone[0] & 255))) - 1) >> 31;
        for (int i = 0; i < 48; i++) {
            clone[i] = (byte) ((clone[i] & minorVersion) | (bArr2[i] & (~minorVersion)));
        }
        return this.crypto.createSecret(clone);
    }
}
