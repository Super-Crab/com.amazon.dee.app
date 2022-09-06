package com.amazon.crypto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.crypto.asymmetric.AsymmetricCryptoParameters;
import com.amazon.crypto.asymmetric.AsymmetricDecryptor;
import com.amazon.crypto.asymmetric.AsymmetricEncryptor;
import com.amazon.crypto.asymmetric.AsymmetricKeys;
import com.amazon.crypto.asymmetric.PublishedKey;
import com.amazon.crypto.asymmetric.WithheldKey;
import com.amazon.crypto.symmetric.SymmetricDecryptor;
import com.amazon.crypto.symmetric.SymmetricEncryptor;
import com.amazon.crypto.symmetric.SymmetricKey;
import com.amazon.crypto.utils.Base64;
import com.amazon.crypto.utils.Preconditions;
import com.amazon.crypto.utils.UtilityInstanceAttempt;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
/* loaded from: classes12.dex */
public final class Crypto {
    public static final String IDENTITY = "com.amazon.crypto";
    private static final String RSA = "RSA";
    private static final String RSA_PRIVATE_KEY_FOOTER = "-----END RSA PRIVATE KEY-----";
    private static final String RSA_PRIVATE_KEY_HEADER = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String RSA_PUBLIC_KEY_FOOTER = "-----END PUBLIC KEY-----";
    private static final String RSA_PUBLIC_KEY_HEADER = "-----BEGIN PUBLIC KEY-----";
    private static final String SHA256WITHRSA = "SHA256withRSA";

    private Crypto() {
        UtilityInstanceAttempt.in(this);
    }

    public static byte[] decode(@Nullable byte[] bArr) throws IllegalArgumentException {
        return Base64.decode(bArr);
    }

    @Nullable
    public static byte[] decrypt(@Nullable byte[] bArr, @NonNull SymmetricKey symmetricKey) throws IllegalArgumentException, SecurityException {
        return SymmetricDecryptor.using(symmetricKey).decrypt(bArr);
    }

    @NonNull
    public static byte[] decryptWithAESKeyAndAAD(@NonNull byte[] bArr, @NonNull byte[] bArr2, @NonNull String str) throws IllegalArgumentException, SecurityException {
        Preconditions.notNull(bArr);
        Preconditions.notNull(bArr2);
        Preconditions.notBlank(str);
        return decrypt(bArr, bArr2, SymmetricKey.from(decode(str.getBytes())));
    }

    @NonNull
    public static byte[] decryptWithPrivateKey(@NonNull byte[] bArr, @NonNull String str) throws IllegalArgumentException, SecurityException {
        Preconditions.notNull(bArr);
        Preconditions.notBlank(str);
        return decrypt(bArr, WithheldKey.from(decode(removeExtraCharactersFromKey(str, true).getBytes())), AsymmetricCryptoParameters.SHA256);
    }

    @Nullable
    public static byte[] encode(@Nullable byte[] bArr) {
        return Base64.encode(bArr);
    }

    @Nullable
    public static byte[] encrypt(@Nullable byte[] bArr, @NonNull SymmetricKey symmetricKey) throws IllegalArgumentException, SecurityException {
        return SymmetricEncryptor.using(symmetricKey).encrypt(bArr);
    }

    @NonNull
    public static byte[] encryptWithAESKeyAndAAD(@NonNull byte[] bArr, @NonNull byte[] bArr2, @NonNull String str) throws IllegalArgumentException, SecurityException {
        Preconditions.notNull(bArr);
        Preconditions.notNull(bArr2);
        Preconditions.notBlank(str);
        return encrypt(bArr, bArr2, SymmetricKey.from(decode(str.getBytes())));
    }

    @NonNull
    public static byte[] encryptWithPublicKey(@NonNull byte[] bArr, @NonNull String str) throws IllegalArgumentException, SecurityException {
        Preconditions.notNull(bArr);
        Preconditions.notBlank(str);
        return encrypt(bArr, PublishedKey.from(decode(removeExtraCharactersFromKey(str, false).getBytes())), AsymmetricCryptoParameters.SHA256);
    }

    @NonNull
    public static AsymmetricKeys generateAsymmetricKeys() throws SecurityException {
        return AsymmetricKeys.generate();
    }

    @NonNull
    public static SymmetricKey generateSymmetricKey() throws SecurityException {
        return SymmetricKey.generate();
    }

    private static PublicKey getPublicKeyFromString(@NonNull String str) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decode(str.getBytes(StandardCharsets.UTF_8))));
    }

    private static String removeExtraCharactersFromKey(String str, boolean z) {
        String str2;
        String str3;
        if (z) {
            str2 = RSA_PRIVATE_KEY_HEADER;
            str3 = RSA_PRIVATE_KEY_FOOTER;
        } else {
            str2 = RSA_PUBLIC_KEY_HEADER;
            str3 = RSA_PUBLIC_KEY_FOOTER;
        }
        int indexOf = str.indexOf(str2);
        int indexOf2 = str.indexOf(str3);
        return (indexOf == -1 || indexOf2 == -1) ? str : str.substring(str2.length() + indexOf, indexOf2);
    }

    @NonNull
    public static boolean verifyWithRSAPublicKey(@NonNull String str, @NonNull String str2, @NonNull String str3) throws IllegalArgumentException, SecurityException {
        try {
            Signature signature = Signature.getInstance(SHA256WITHRSA);
            signature.initVerify(getPublicKeyFromString(removeExtraCharactersFromKey(str3, false)));
            signature.update(str.getBytes(StandardCharsets.UTF_8));
            return signature.verify(decode(Base64.base64FromBase64Url(str2).getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Nullable
    public static byte[] decrypt(@Nullable byte[] bArr, @Nullable byte[] bArr2, @NonNull SymmetricKey symmetricKey) throws IllegalArgumentException, SecurityException {
        return SymmetricDecryptor.using(symmetricKey).decrypt(bArr, bArr2);
    }

    @Nullable
    public static byte[] encrypt(@Nullable byte[] bArr, @Nullable byte[] bArr2, @NonNull SymmetricKey symmetricKey) throws IllegalArgumentException, SecurityException {
        return SymmetricEncryptor.using(symmetricKey).encrypt(bArr, bArr2);
    }

    @Nullable
    public static byte[] decrypt(@Nullable byte[] bArr, @NonNull WithheldKey withheldKey) throws IllegalArgumentException, SecurityException {
        return decrypt(bArr, withheldKey, AsymmetricCryptoParameters.SHA1);
    }

    @Nullable
    public static byte[] encrypt(@Nullable byte[] bArr, @NonNull PublishedKey publishedKey) throws IllegalArgumentException, SecurityException {
        return encrypt(bArr, publishedKey, AsymmetricCryptoParameters.SHA1);
    }

    @Nullable
    public static byte[] decrypt(@Nullable byte[] bArr, @NonNull WithheldKey withheldKey, @NonNull AsymmetricCryptoParameters asymmetricCryptoParameters) throws IllegalArgumentException, SecurityException {
        return AsymmetricDecryptor.using(withheldKey, asymmetricCryptoParameters).decrypt(bArr);
    }

    @Nullable
    public static byte[] encrypt(@Nullable byte[] bArr, @NonNull PublishedKey publishedKey, @NonNull AsymmetricCryptoParameters asymmetricCryptoParameters) throws IllegalArgumentException, SecurityException {
        return AsymmetricEncryptor.using(publishedKey, asymmetricCryptoParameters).encrypt(bArr);
    }
}
