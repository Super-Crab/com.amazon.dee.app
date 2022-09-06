package com.amazon.alexa.accessory.crypto.persistence;

import android.database.Cursor;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.crypto.persistence.KeyStoreAlias;
import com.amazon.alexa.accessory.crypto.persistence.KeyWrapper;
import com.amazon.alexa.accessory.crypto.persistence.NegotiatedDBEntry;
import com.amazon.alexa.accessory.crypto.persistence.NegotiatedDataSQLLiteContract;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
@RequiresApi(api = 23)
/* loaded from: classes.dex */
public class KeyStoreMediator {
    private final KeyStoreLoader keyStoreLoader;
    private final KeyWrapper keyWrapper;
    private final KeyStoreProtectionParamsBuilder protectionParamsBuilder;

    public KeyStoreMediator() {
        this(new KeyStoreLoader(), new KeyWrapper(), new KeyStoreProtectionParamsBuilder());
    }

    private void deleteFromKeyStore(String str, String... strArr) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException {
        KeyStore keyStore = this.keyStoreLoader.getKeyStore();
        try {
            for (String str2 : strArr) {
                if (str2 != null) {
                    keyStore.deleteEntry(str2);
                }
            }
        } catch (KeyStoreException e) {
            throw new CryptoKeyDataStore.CryptoKeyDataStoreIOException(str, "Error removing accessory key entries from the Key Store", e);
        }
    }

    public void deleteKeysAtCurrentCursorEntry(Cursor cursor) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException {
        String string = cursor.getString(cursor.getColumnIndexOrThrow(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_ACCESSORY_ID));
        deleteFromKeyStore(string, (String[]) NegotiatedDBEntry.getKeyAliasesAtCurrentCursorEntry(string, cursor).toArray(new String[0]));
    }

    public void deleteKeysFromStore(String str, NegotiatedDBEntry.V1 v1, boolean z) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException {
        CryptoKeyDataStore.InvalidInputsException.accumulator(str).accumulateField(str != null, "accessoryId").accumulateField(v1 != null, "toPersist").throwIfInvalid("Cannot write data to persistence for invalid input(s).");
        String[] strArr = new String[3];
        strArr[0] = v1.decryptionKey.alias;
        strArr[1] = v1.authenticationKey.alias;
        strArr[2] = z ? v1.wrapperKey.alias : null;
        deleteFromKeyStore(str, strArr);
    }

    public CryptoKeyDataStore.NegotiatedData getKeysFromStore(String str, NegotiatedDBEntry.V1 v1) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException, CryptoKeyDataStore.InvalidStoreStateException {
        KeyStore keyStore = this.keyStoreLoader.getKeyStore();
        try {
            if (keyStore.entryInstanceOf(v1.decryptionKey.alias, KeyStore.SecretKeyEntry.class)) {
                if (keyStore.entryInstanceOf(v1.authenticationKey.alias, KeyStore.SecretKeyEntry.class)) {
                    try {
                        return v1.toNegotiatedData(this.keyWrapper.unwrapKey(str, new KeyWrapper.WrappedKey(v1.wrappedRootKey, v1.cipherSuite.encryptionAlgorithm, v1.wrappingCipherTransformation), v1.wrapperKey, keyStore), ((KeyStore.SecretKeyEntry) keyStore.getEntry(v1.decryptionKey.alias, null)).getSecretKey(), ((KeyStore.SecretKeyEntry) keyStore.getEntry(v1.authenticationKey.alias, null)).getSecretKey());
                    } catch (KeyStoreException e) {
                        e = e;
                        throw new CryptoKeyDataStore.CryptoKeyDataStoreIOException(str, "Unable to recover keys from the Android KeyStore", e);
                    } catch (NoSuchAlgorithmException e2) {
                        throw new CryptoKeyDataStore.InvalidStoreStateException(str, "Unable to extract keys from the Android KeyStore", e2);
                    } catch (UnrecoverableEntryException e3) {
                        e = e3;
                        throw new CryptoKeyDataStore.CryptoKeyDataStoreIOException(str, "Unable to recover keys from the Android KeyStore", e);
                    }
                }
            }
            return null;
        } catch (KeyStoreException unused) {
            throw new CryptoKeyDataStore.CryptoKeyDataStoreIOException(str, "Error reading keys from Android KeyStore.");
        }
    }

    public NegotiatedDBEntry.V2 prepareKeysForStore(String str, CryptoKeyDataStore.NegotiatedData negotiatedData, NegotiatedDBEntry negotiatedDBEntry) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException, CryptoKeyDataStore.InvalidStoreStateException {
        boolean z = true;
        CryptoKeyDataStore.InvalidInputsException.Accumulator accumulateField = CryptoKeyDataStore.InvalidInputsException.accumulator(str).accumulateField(str != null, "accessoryId");
        if (negotiatedData == null) {
            z = false;
        }
        accumulateField.accumulateField(z, "toPersist").throwIfInvalid("Cannot write data to persistence for invalid input(s).");
        long j = negotiatedDBEntry == null ? 0L : negotiatedDBEntry.currentVersion;
        if (j != Long.MAX_VALUE) {
            long j2 = j + 1;
            KeyStore keyStore = this.keyStoreLoader.getKeyStore();
            KeyStoreAlias composeFrom = KeyStoreAlias.composeFrom(str, KeyStoreAlias.KeyType.DECRYPTION_WRAPPER, j2);
            KeyWrapper.WrappedKey wrapKey = this.keyWrapper.wrapKey(str, composeFrom, negotiatedData.encryptionKey, keyStore);
            KeyStoreAlias composeFrom2 = KeyStoreAlias.composeFrom(str, KeyStoreAlias.KeyType.AUTHENTICATION_WRAPPER, j2);
            KeyWrapper.WrappedKey wrapKey2 = this.keyWrapper.wrapKey(str, composeFrom2, negotiatedData.authenticationKey, keyStore);
            if (negotiatedDBEntry == null) {
                KeyStoreAlias composeFrom3 = KeyStoreAlias.composeFrom(str, KeyStoreAlias.KeyType.ROOT_WRAPPER, j2);
                KeyWrapper.WrappedKey wrapKey3 = this.keyWrapper.wrapKey(str, composeFrom3, negotiatedData.rootKey, keyStore);
                return new NegotiatedDBEntry.V2(negotiatedData.keyExchangeProtocolVersion, negotiatedData.cipherSuite, negotiatedData.cipherTransform, negotiatedData.keyExchangeTimestampMillis, negotiatedData.lastKeyRotationTimestampMillis, j2, wrapKey3.getWrappedKey(), wrapKey3.getWrappingTransformation(), composeFrom3, wrapKey.getWrappedKey(), wrapKey.getWrappingTransformation(), composeFrom, wrapKey2.getWrappedKey(), wrapKey2.getWrappingTransformation(), composeFrom2);
            } else if (negotiatedDBEntry instanceof NegotiatedDBEntry.V1) {
                NegotiatedDBEntry.V1 v1 = (NegotiatedDBEntry.V1) negotiatedDBEntry;
                return new NegotiatedDBEntry.V2(negotiatedData.keyExchangeProtocolVersion, negotiatedData.cipherSuite, negotiatedData.cipherTransform, negotiatedData.keyExchangeTimestampMillis, negotiatedData.lastKeyRotationTimestampMillis, j2, v1.wrappedRootKey, v1.wrappingCipherTransformation, v1.wrapperKey, wrapKey.getWrappedKey(), wrapKey.getWrappingTransformation(), composeFrom, wrapKey2.getWrappedKey(), wrapKey2.getWrappingTransformation(), composeFrom2);
            } else if (negotiatedDBEntry instanceof NegotiatedDBEntry.V2) {
                NegotiatedDBEntry.V2 v2 = (NegotiatedDBEntry.V2) negotiatedDBEntry;
                return new NegotiatedDBEntry.V2(negotiatedData.keyExchangeProtocolVersion, negotiatedData.cipherSuite, negotiatedData.cipherTransform, negotiatedData.keyExchangeTimestampMillis, negotiatedData.lastKeyRotationTimestampMillis, j2, v2.wrappedRootKey, v2.wrappedRootKeyCipherTransformation, v2.rootKeyWrapperAlias, wrapKey.getWrappedKey(), wrapKey.getWrappingTransformation(), composeFrom, wrapKey2.getWrappedKey(), wrapKey2.getWrappingTransformation(), composeFrom2);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected database entry type: ");
                outline107.append(negotiatedDBEntry.getClass().getName());
                throw new CryptoKeyDataStore.InvalidStoreStateException(str, outline107.toString());
            }
        }
        throw new CryptoKeyDataStore.InvalidStoreStateException(str, "Unable to update keys for accessory - maximum update count reached.");
    }

    public KeyStoreMediator(KeyStoreLoader keyStoreLoader, KeyWrapper keyWrapper, KeyStoreProtectionParamsBuilder keyStoreProtectionParamsBuilder) {
        Preconditions.notNull(keyStoreLoader, "keyStoreLoader");
        Preconditions.notNull(keyWrapper, "keyWrapper");
        Preconditions.notNull(keyStoreProtectionParamsBuilder, "protectionParamsBuilder");
        this.keyStoreLoader = keyStoreLoader;
        this.keyWrapper = keyWrapper;
        this.protectionParamsBuilder = keyStoreProtectionParamsBuilder;
    }

    public void deleteKeysFromStore(String str, NegotiatedDBEntry.V2 v2, boolean z) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException {
        CryptoKeyDataStore.InvalidInputsException.accumulator(str).accumulateField(str != null, "accessoryId").accumulateField(v2 != null, "toPersist").throwIfInvalid("Cannot delete from persistence for invalid input(s).");
        String[] strArr = new String[3];
        strArr[0] = v2.decryptionKeyWrapperAlias.alias;
        strArr[1] = v2.authenticationKeyWrapperAlias.alias;
        strArr[2] = z ? v2.rootKeyWrapperAlias.alias : null;
        deleteFromKeyStore(str, strArr);
    }

    public CryptoKeyDataStore.NegotiatedData getKeysFromStore(String str, NegotiatedDBEntry.V2 v2) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException, CryptoKeyDataStore.InvalidStoreStateException {
        KeyStore keyStore = this.keyStoreLoader.getKeyStore();
        if (!this.keyWrapper.canUnwrapKeys(v2.decryptionKeyWrapperAlias, keyStore) || !this.keyWrapper.canUnwrapKeys(v2.authenticationKeyWrapperAlias, keyStore)) {
            return null;
        }
        return v2.toNegotiatedData(this.keyWrapper.unwrapKey(str, new KeyWrapper.WrappedKey(v2.wrappedRootKey, v2.cipherSuite.encryptionAlgorithm, v2.wrappedRootKeyCipherTransformation), v2.rootKeyWrapperAlias, keyStore), this.keyWrapper.unwrapKey(str, new KeyWrapper.WrappedKey(v2.wrappedDecryptionKey, v2.cipherSuite.encryptionAlgorithm, v2.wrappedDecryptionKeyCipherTransformation), v2.decryptionKeyWrapperAlias, keyStore), this.keyWrapper.unwrapKey(str, new KeyWrapper.WrappedKey(v2.wrappedAuthenticationKey, v2.cipherSuite.macAlgorithm, v2.wrappedAuthenticationKeyCipherTransformation), v2.authenticationKeyWrapperAlias, keyStore));
    }
}
