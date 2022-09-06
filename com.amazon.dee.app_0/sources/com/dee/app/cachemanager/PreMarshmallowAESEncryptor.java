package com.dee.app.cachemanager;

import android.content.Context;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
/* loaded from: classes9.dex */
public class PreMarshmallowAESEncryptor extends AbstractAESEncryptor {
    private static final String AES_MODE = "AES/ECB/PKCS7Padding";

    public PreMarshmallowAESEncryptor(@NonNull Context context, @NonNull String str) {
        super(context, str);
    }

    @Override // com.dee.app.cachemanager.AbstractAESEncryptor
    protected String getSharedPreferenceName(String str) {
        return GeneratedOutlineSupport1.outline72("encryptor_legacy_", str);
    }

    @Override // com.dee.app.cachemanager.AbstractAESEncryptor
    protected Cipher getUninitializedCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        return Cipher.getInstance(AES_MODE);
    }

    @Override // com.dee.app.cachemanager.AbstractAESEncryptor
    protected Cipher getWrappingCipher() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        return Cipher.getInstance("RSA/ECB/PKCS1Padding");
    }

    @Override // com.dee.app.cachemanager.AbstractAESEncryptor
    protected void initAESDecryptCipher(Cipher cipher, Key key) throws InvalidAlgorithmParameterException, InvalidKeyException {
        cipher.init(2, key);
    }

    @Override // com.dee.app.cachemanager.AbstractAESEncryptor
    protected void initAESEncryptCipher(Cipher cipher, Key key) throws InvalidAlgorithmParameterException, InvalidKeyException {
        cipher.init(1, key);
    }
}
