package com.amazon.alexa.wakeword.speakerverification.encryption;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.crypto.ByteEncryptor;
import com.amazon.crypto.KeyManager;
import com.amazon.crypto.asymmetric.AsymmetricKeys;
import com.amazon.crypto.asymmetric.AsymmetricKeysManager;
import com.amazon.crypto.symmetric.SymmetricKey;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
@RequiresApi(api = 23)
/* loaded from: classes11.dex */
public class SymmetricKeyProvider {
    private static final String SYMMETRIC_KEY_FILE_NAME = "kfile";
    private static final String TAG = "SymmetricKeyProvider";
    private static final String UTTERANCE_CRYPTO_ASYMMETRIC_KEY_PAIR_ALIAS = "UserUtteranceAsymmetricCryptoKeys";
    private final Context context;
    private final CryptoFactory cryptoFactory;
    private final KeyManager<AsymmetricKeys> keysManager;
    private SymmetricKey symmetricKey;

    public SymmetricKeyProvider(@NonNull Context context) {
        this.symmetricKey = null;
        this.context = context;
        this.keysManager = AsymmetricKeysManager.with(context);
        this.cryptoFactory = new CryptoFactory();
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0042: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:20:0x0042 */
    @Nullable
    private SymmetricKey generateAndPersistSymmetricKey() {
        FileOutputStream fileOutputStream;
        OutputStream outputStream;
        AsymmetricKeys asymmetricKeys = getAsymmetricKeys();
        OutputStream outputStream2 = null;
        if (asymmetricKeys == null) {
            return null;
        }
        ByteEncryptor asymmetricEncryptor = this.cryptoFactory.getAsymmetricEncryptor(asymmetricKeys.publishedKey());
        try {
            try {
                SymmetricKey generateSymmetricKey = generateSymmetricKey();
                byte[] encrypt = asymmetricEncryptor.encrypt(generateSymmetricKey.toBytes());
                fileOutputStream = this.context.openFileOutput(SYMMETRIC_KEY_FILE_NAME, 0);
                try {
                    fileOutputStream.write(encrypt);
                    IOUtils.closeQuietly((OutputStream) fileOutputStream);
                    return generateSymmetricKey;
                } catch (Exception e) {
                    e = e;
                    Log.e(TAG, "Unable to write file to persist key", e, new Object[0]);
                    IOUtils.closeQuietly((OutputStream) fileOutputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                outputStream2 = outputStream;
                IOUtils.closeQuietly(outputStream2);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly(outputStream2);
            throw th;
        }
    }

    @Nullable
    private AsymmetricKeys getAsymmetricKeys() {
        if (this.keysManager.contains(UTTERANCE_CRYPTO_ASYMMETRIC_KEY_PAIR_ALIAS)) {
            return this.keysManager.mo3299retrieve(UTTERANCE_CRYPTO_ASYMMETRIC_KEY_PAIR_ALIAS);
        }
        try {
            return this.keysManager.mo3298generate(UTTERANCE_CRYPTO_ASYMMETRIC_KEY_PAIR_ALIAS);
        } catch (IllegalArgumentException | SecurityException e) {
            Log.e(TAG, "Encountered exception while obtaining keys from keystore", e, new Object[0]);
            return null;
        }
    }

    private byte[] retrievePersistedSymmetricKey() {
        Throwable th;
        FileInputStream fileInputStream;
        try {
            try {
                fileInputStream = this.context.openFileInput(SYMMETRIC_KEY_FILE_NAME);
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly((InputStream) null);
                throw th;
            }
        } catch (FileNotFoundException unused) {
            fileInputStream = null;
        } catch (Exception e) {
            e = e;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            IOUtils.closeQuietly((InputStream) null);
            throw th;
        }
        try {
            byte[] bytesFromInputStream = getBytesFromInputStream(fileInputStream);
            IOUtils.closeQuietly((InputStream) fileInputStream);
            return bytesFromInputStream;
        } catch (FileNotFoundException unused2) {
            Log.e(TAG, "File could not be found to read key");
            IOUtils.closeQuietly((InputStream) fileInputStream);
            return null;
        } catch (Exception e2) {
            e = e2;
            Log.e(TAG, "Unable to read file from disk", e, new Object[0]);
            IOUtils.closeQuietly((InputStream) fileInputStream);
            return null;
        }
    }

    @VisibleForTesting
    SymmetricKey fromDecryptedBytes(byte[] bArr) {
        return SymmetricKey.from(bArr);
    }

    @VisibleForTesting
    SymmetricKey generateSymmetricKey() {
        return SymmetricKey.generate();
    }

    @VisibleForTesting
    byte[] getBytesFromInputStream(FileInputStream fileInputStream) throws IOException {
        return IOUtils.toByteArray(fileInputStream);
    }

    @Nullable
    public SymmetricKey getSymmetricKey() {
        if (this.symmetricKey != null) {
            Log.i(TAG, "return known key");
            return this.symmetricKey;
        }
        byte[] retrievePersistedSymmetricKey = retrievePersistedSymmetricKey();
        boolean z = false;
        if (retrievePersistedSymmetricKey != null) {
            try {
                AsymmetricKeys asymmetricKeys = getAsymmetricKeys();
                if (asymmetricKeys == null) {
                    return null;
                }
                this.symmetricKey = fromDecryptedBytes(this.cryptoFactory.getAsymmetricDecryptor(asymmetricKeys.withheldKey()).decrypt(retrievePersistedSymmetricKey));
                return this.symmetricKey;
            } catch (Exception e) {
                Log.e(TAG, "Exception when decrypting key", e, new Object[0]);
            }
        }
        this.symmetricKey = generateAndPersistSymmetricKey();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully created new key? ");
        if (this.symmetricKey != null) {
            z = true;
        }
        outline107.append(z);
        Log.i(str, outline107.toString());
        return this.symmetricKey;
    }

    @VisibleForTesting
    SymmetricKeyProvider(@NonNull Context context, @NonNull KeyManager<AsymmetricKeys> keyManager, @NonNull CryptoFactory cryptoFactory) {
        this.symmetricKey = null;
        this.context = context;
        this.keysManager = keyManager;
        this.cryptoFactory = cryptoFactory;
    }
}
