package com.amazon.alexa.presence.bleconn.identity.encryption;

import com.amazon.alexa.presence.bleconn.helpers.ByteHelper;
import com.amazon.whispercloak.KeyUtils;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
class IdentityTokenCryptographyModule implements EncryptionModule, DecryptionModule {
    private static final Logger LOG = LoggerFactory.getLogger(IdentityTokenCryptographyModule.class);
    private static final int MAX_DATA_LENGTH_BYTES = 190;
    private static final String TRANSFORMATION = "RSA/ECB/OAEPPadding";
    private final Cipher decryptionCipher;
    private final Cipher encryptionCipher;

    public IdentityTokenCryptographyModule(byte[] bArr, byte[] bArr2) {
        Cipher cipher = null;
        this.encryptionCipher = bArr != null ? encryptionCipher(bArr) : null;
        this.decryptionCipher = bArr2 != null ? decryptionCipher(bArr2) : cipher;
    }

    private static Cipher decryptionCipher(byte[] bArr) {
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance(KeyUtils.ALGORITHM_RSA).generatePrivate(new PKCS8EncodedKeySpec(bArr));
            OAEPParameterSpec oAEPParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(2, generatePrivate, oAEPParameterSpec);
            return cipher;
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    private static Cipher encryptionCipher(byte[] bArr) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance(KeyUtils.ALGORITHM_RSA).generatePublic(new X509EncodedKeySpec(bArr));
            OAEPParameterSpec oAEPParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, generatePublic, oAEPParameterSpec);
            return cipher;
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    @Override // com.amazon.alexa.presence.bleconn.identity.encryption.DecryptionModule
    public byte[] decrypt(byte[] bArr) {
        Cipher cipher = this.decryptionCipher;
        if (cipher != null) {
            try {
                return cipher.doFinal(bArr);
            } catch (Throwable th) {
                throw new RuntimeException(th);
            }
        }
        throw new IllegalStateException("Can not decrypt data, no private key defined");
    }

    @Override // com.amazon.alexa.presence.bleconn.identity.encryption.EncryptionModule
    public byte[] encrypt(byte[] bArr) {
        if (this.encryptionCipher != null) {
            if (bArr.length > 190) {
                LOG.error("Asked to RSA encrypt a data packet larger than supported.");
                bArr = ByteHelper.slice(bArr, 0, 190);
            }
            try {
                return this.encryptionCipher.doFinal(bArr);
            } catch (Throwable th) {
                throw new RuntimeException(th);
            }
        }
        throw new IllegalStateException("Can not encrypt data, no public key defined");
    }
}
