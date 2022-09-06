package com.amazon.crypto.asymmetric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.crypto.ByteEncryptor;
import com.amazon.crypto.utils.Preconditions;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
/* loaded from: classes12.dex */
public final class AsymmetricEncryptor implements ByteEncryptor {
    private final AsymmetricCryptoParameters cryptoParameters;
    private final PublishedKey publishedKey;

    private AsymmetricEncryptor(PublishedKey publishedKey, AsymmetricCryptoParameters asymmetricCryptoParameters) {
        Preconditions.notNull(publishedKey);
        Preconditions.notNull(asymmetricCryptoParameters);
        this.publishedKey = publishedKey;
        this.cryptoParameters = asymmetricCryptoParameters;
    }

    @NonNull
    public static AsymmetricEncryptor using(@NonNull PublishedKey publishedKey) {
        return new AsymmetricEncryptor(publishedKey, AsymmetricCryptoParameters.SHA1);
    }

    @Override // com.amazon.crypto.ByteEncryptor
    @Nullable
    public byte[] encrypt(@Nullable byte[] bArr) throws IllegalArgumentException, SecurityException {
        if (bArr == null) {
            return bArr;
        }
        try {
            Cipher cipher = Cipher.getInstance(this.cryptoParameters.getTransformation());
            cipher.init(1, this.publishedKey.toJavaPublicKey(), this.cryptoParameters.getParameterSpec());
            return cipher.doFinal(bArr);
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e);
        }
    }

    @NonNull
    public static AsymmetricEncryptor using(@NonNull PublishedKey publishedKey, @NonNull AsymmetricCryptoParameters asymmetricCryptoParameters) {
        return new AsymmetricEncryptor(publishedKey, asymmetricCryptoParameters);
    }
}
