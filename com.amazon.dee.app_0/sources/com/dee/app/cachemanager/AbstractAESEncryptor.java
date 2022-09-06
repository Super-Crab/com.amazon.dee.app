package com.dee.app.cachemanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.google.common.base.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.security.auth.x500.X500Principal;
/* loaded from: classes9.dex */
public abstract class AbstractAESEncryptor implements Encryptor {
    private static final int AES_KEY_SIZE = 128;
    private static final Object ANDROID_KEY_STORE_LOCK = new Object();
    private static final String ANDROID_KEY_STORE_NAME = "AndroidKeyStore";
    private static final String ENCRYPTED_KEY_NAME = "key";
    private static final String RSA_ALGORITHM_NAME = "RSA";
    private static final int RSA_KEY_SIZE = 2048;
    static final String RSA_MODE = "RSA/ECB/PKCS1Padding";
    private static final String TAG = "AbstractAESEncryptor";
    @GuardedBy("ANDROID_KEY_STORE_LOCK")
    private static KeyStore androidKeyStore;
    private final Context context;
    private volatile Key key;
    private final String keyAlias;
    private final String sharedPreference;

    public AbstractAESEncryptor(@NonNull Context context, @NonNull String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        this.context = context;
        this.keyAlias = str;
        this.sharedPreference = getSharedPreferenceName(str);
    }

    @SuppressLint({"ApplySharedPref", "CommitPrefEdits"})
    private Key getAESKeyFromSharedPrefs() throws CertificateException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, IOException, IllegalBlockSizeException {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(this.sharedPreference, 0);
        String string = sharedPreferences.getString("key", null);
        if (string == null) {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            keyGenerator.init(128);
            SecretKey generateKey = keyGenerator.generateKey();
            String encodeToString = Base64.encodeToString(rsaEncryptKey(generateKey), 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("key", encodeToString);
            edit.commit();
            return generateKey;
        }
        return rsaDecryptKey(Base64.decode(string, 0));
    }

    private static KeyStore getKeyStore() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException {
        synchronized (ANDROID_KEY_STORE_LOCK) {
            if (androidKeyStore == null) {
                KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
                keyStore.load(null);
                androidKeyStore = keyStore;
            }
        }
        return androidKeyStore;
    }

    private synchronized void initKeys() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException, UnrecoverableEntryException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException {
        if (this.key != null) {
            return;
        }
        KeyStore keyStore = getKeyStore();
        if (!keyStore.containsAlias(this.keyAlias)) {
            initValidKeys();
        } else {
            try {
                this.key = getAESKeyFromSharedPrefs();
            } catch (Exception e) {
                Log.e(TAG, "Failed to fetch AES key. Clearing keys.", e);
                removeKeys(keyStore);
                initValidKeys();
            }
        }
    }

    private void initValidKeys() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CertificateException, UnrecoverableEntryException, NoSuchPaddingException, KeyStoreException, InvalidKeyException, IOException, IllegalBlockSizeException {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(1, 1);
        KeyPairGeneratorSpec build = new KeyPairGeneratorSpec.Builder(this.context).setAlias(this.keyAlias).setSubject(new X500Principal("CN=Amazon, O=Android Authority")).setSerialNumber(BigInteger.ONE).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).setKeySize(2048).build();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", getKeyStore().getProvider());
        keyPairGenerator.initialize(build);
        keyPairGenerator.generateKeyPair();
        this.key = getAESKeyFromSharedPrefs();
    }

    @SuppressLint({"ApplySharedPref", "CommitPrefEdits"})
    private void removeSavedSharedPreferences() {
        this.context.getSharedPreferences(this.sharedPreference, 0).edit().clear().commit();
    }

    private Key rsaDecryptKey(byte[] bArr) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableEntryException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException {
        Key key = getKeyStore().getKey(this.keyAlias, null);
        Cipher wrappingCipher = getWrappingCipher();
        wrappingCipher.init(4, key);
        return wrappingCipher.unwrap(bArr, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, 3);
    }

    private byte[] rsaEncryptKey(Key key) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException {
        PublicKey publicKey = getKeyStore().getCertificate(this.keyAlias).getPublicKey();
        Cipher wrappingCipher = getWrappingCipher();
        wrappingCipher.init(3, publicKey);
        return wrappingCipher.wrap(key);
    }

    @Override // com.dee.app.cachemanager.Encryptor
    public boolean clearState() {
        try {
            removeKeys(getKeyStore());
            return true;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
            return false;
        }
    }

    @Override // com.dee.app.cachemanager.Encryptor
    public byte[] decrypt(@NonNull byte[] bArr) throws EncryptorException {
        Preconditions.checkNotNull(bArr);
        try {
            initKeys();
            Cipher uninitializedCipher = getUninitializedCipher();
            initAESDecryptCipher(uninitializedCipher, this.key);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, uninitializedCipher);
            cipherOutputStream.write(bArr);
            cipherOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableEntryException | CertificateException | IllegalBlockSizeException | NoSuchPaddingException e) {
            Log.e(TAG, "Error while decrypting.", e);
            clearState();
            throw new EncryptorException(e);
        }
    }

    @Override // com.dee.app.cachemanager.Encryptor
    public byte[] encrypt(@NonNull byte[] bArr) throws EncryptorException {
        Preconditions.checkNotNull(bArr);
        try {
            initKeys();
            Cipher uninitializedCipher = getUninitializedCipher();
            initAESEncryptCipher(uninitializedCipher, this.key);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, uninitializedCipher);
            cipherOutputStream.write(bArr);
            cipherOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableEntryException | CertificateException | IllegalBlockSizeException | NoSuchPaddingException e) {
            Log.e(TAG, "Error while encrypting", e);
            clearState();
            throw new EncryptorException(e);
        }
    }

    protected abstract String getSharedPreferenceName(String str);

    protected abstract Cipher getUninitializedCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException;

    protected abstract Cipher getWrappingCipher() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException;

    @Override // com.dee.app.cachemanager.Encryptor
    public void init() throws EncryptorException {
        try {
            initKeys();
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableEntryException | CertificateException | IllegalBlockSizeException | NoSuchPaddingException e) {
            Log.e(TAG, "Error in init", e);
            clearState();
            throw new EncryptorException(e);
        }
    }

    protected abstract void initAESDecryptCipher(Cipher cipher, Key key) throws InvalidAlgorithmParameterException, InvalidKeyException;

    protected abstract void initAESEncryptCipher(Cipher cipher, Key key) throws InvalidAlgorithmParameterException, InvalidKeyException;

    void removeKeys(KeyStore keyStore) throws KeyStoreException {
        keyStore.deleteEntry(this.keyAlias);
        removeSavedSharedPreferences();
        this.key = null;
    }
}
