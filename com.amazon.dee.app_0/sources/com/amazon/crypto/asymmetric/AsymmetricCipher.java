package com.amazon.crypto.asymmetric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.crypto.ByteCipher;
import com.amazon.crypto.utils.Preconditions;
/* loaded from: classes12.dex */
public final class AsymmetricCipher implements ByteCipher {
    private final AsymmetricDecryptor decryptor;
    private final AsymmetricEncryptor encryptor;

    private AsymmetricCipher(AsymmetricEncryptor asymmetricEncryptor, AsymmetricDecryptor asymmetricDecryptor) {
        this.encryptor = asymmetricEncryptor;
        this.decryptor = asymmetricDecryptor;
    }

    @NonNull
    public static AsymmetricCipher using(@NonNull AsymmetricKeys asymmetricKeys) {
        return using(asymmetricKeys, AsymmetricCryptoParameters.SHA1);
    }

    @Override // com.amazon.crypto.ByteDecryptor
    @Nullable
    public byte[] decrypt(@Nullable byte[] bArr) throws IllegalArgumentException, SecurityException {
        return this.decryptor.decrypt(bArr);
    }

    @Override // com.amazon.crypto.ByteEncryptor
    @Nullable
    public byte[] encrypt(@Nullable byte[] bArr) throws IllegalArgumentException, SecurityException {
        return this.encryptor.encrypt(bArr);
    }

    @NonNull
    public static AsymmetricCipher using(@NonNull AsymmetricKeys asymmetricKeys, @NonNull AsymmetricCryptoParameters asymmetricCryptoParameters) {
        Preconditions.notNull(asymmetricKeys);
        Preconditions.notNull(asymmetricCryptoParameters);
        return new AsymmetricCipher(AsymmetricEncryptor.using(asymmetricKeys.publishedKey(), asymmetricCryptoParameters), AsymmetricDecryptor.using(asymmetricKeys.withheldKey(), asymmetricCryptoParameters));
    }
}
