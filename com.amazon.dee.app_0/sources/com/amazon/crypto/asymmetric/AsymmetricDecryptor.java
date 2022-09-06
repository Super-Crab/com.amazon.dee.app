package com.amazon.crypto.asymmetric;

import androidx.annotation.NonNull;
import com.amazon.crypto.ByteDecryptor;
import com.amazon.crypto.utils.Preconditions;
import java.security.GeneralSecurityException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
/* loaded from: classes12.dex */
public final class AsymmetricDecryptor implements ByteDecryptor {
    private final AsymmetricCryptoParameters cryptoParameters;
    private final WithheldKey withheldKey;

    private AsymmetricDecryptor(WithheldKey withheldKey, AsymmetricCryptoParameters asymmetricCryptoParameters) {
        Preconditions.notNull(withheldKey);
        Preconditions.notNull(asymmetricCryptoParameters);
        this.withheldKey = withheldKey;
        this.cryptoParameters = asymmetricCryptoParameters;
    }

    @NonNull
    public static AsymmetricDecryptor using(@NonNull WithheldKey withheldKey) {
        return new AsymmetricDecryptor(withheldKey, AsymmetricCryptoParameters.SHA1);
    }

    @Override // com.amazon.crypto.ByteDecryptor
    @NonNull
    public byte[] decrypt(@NonNull byte[] bArr) throws IllegalArgumentException, SecurityException {
        if (bArr == null) {
            return bArr;
        }
        boolean z = true;
        boolean[] zArr = new boolean[1];
        if (bArr.length > 512) {
            z = false;
        }
        zArr[0] = z;
        Preconditions.isTrue(zArr);
        try {
            Cipher cipher = Cipher.getInstance(this.cryptoParameters.getTransformation());
            cipher.init(2, this.withheldKey.toJavaPrivateKey(), this.cryptoParameters.getParameterSpec());
            return cipher.doFinal(bArr);
        } catch (BadPaddingException e) {
            throw new IllegalArgumentException(e);
        } catch (GeneralSecurityException e2) {
            throw new SecurityException(e2);
        }
    }

    @NonNull
    public static AsymmetricDecryptor using(@NonNull WithheldKey withheldKey, @NonNull AsymmetricCryptoParameters asymmetricCryptoParameters) {
        return new AsymmetricDecryptor(withheldKey, asymmetricCryptoParameters);
    }
}
