package com.amazon.alexa.accessory.crypto.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.crypto.cipher.CipherSuiteParser;
import com.amazon.alexa.accessory.crypto.cipher.SupportedCipherSuite;
import com.amazon.alexa.accessory.crypto.persistence.NegotiatedDataSQLLiteContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Optional;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class NegotiatedDBEntry {
    final SupportedCipherSuite cipherSuite;
    final String cipherTransformation;
    final long currentVersion;
    final int keyExchangeProtocolVersion;
    final long keyExchangeTimestampMillis;
    final long lastKeyRotationTimestampMillis;
    final String wrappedRootKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class V1 extends NegotiatedDBEntry {
        final KeyStoreAlias authenticationKey;
        final KeyStoreAlias decryptionKey;
        final KeyStoreAlias wrapperKey;
        final String wrappingCipherTransformation;

        V1(int i, SupportedCipherSuite supportedCipherSuite, String str, long j, long j2, long j3, String str2, String str3, KeyStoreAlias keyStoreAlias, KeyStoreAlias keyStoreAlias2, KeyStoreAlias keyStoreAlias3) {
            super(i, supportedCipherSuite, str, j, j2, j3, str2);
            this.wrappingCipherTransformation = str3;
            this.wrapperKey = keyStoreAlias;
            this.decryptionKey = keyStoreAlias2;
            this.authenticationKey = keyStoreAlias3;
        }

        static V1 fromCurrentCursorEntry(String str, Cursor cursor) throws CryptoKeyDataStore.InvalidStoreStateException {
            int i = cursor.getInt(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_EXCHANGE_PROTOCOL_VERSION));
            String string = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_CIPHER_SUITE));
            String string2 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_CIPHER_TRANSFORMATION));
            long j = cursor.getLong(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_EXCHANGE_TIMESTAMP));
            long j2 = cursor.getLong(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_ROTATION_TIMESTAMP));
            long j3 = cursor.getLong(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_ACTIVE_KEY_VERSION));
            String string3 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_WRAPPED_ROOT_KEY));
            String string4 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_WRAPPING_CIPHER_TRANSFORMATION));
            String string5 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_WRAPPING_KEY_ALIAS));
            String string6 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_DECRYPTION_KEY_ALIAS));
            String string7 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_AUTHENTICATION_KEY_ALIAS));
            Optional<SupportedCipherSuite> fromAccessoryDescriptor = CipherSuiteParser.fromAccessoryDescriptor(string);
            if (fromAccessoryDescriptor.isPresent()) {
                return new V1(i, fromAccessoryDescriptor.get(), string2, j, j2, j3, string3, string4, new KeyStoreAlias(string5), new KeyStoreAlias(string6), new KeyStoreAlias(string7));
            }
            throw new CryptoKeyDataStore.InvalidStoreStateException(str, GeneratedOutlineSupport1.outline72("The stored keys were generated from a cipher-suite that is no longer supported: ", string));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List<String> getV1KeyAliasesAtCurrentCursorEntry(String str, Cursor cursor) throws CryptoKeyDataStore.InvalidStoreStateException {
            return Arrays.asList(cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_WRAPPING_KEY_ALIAS)), cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_DECRYPTION_KEY_ALIAS)), cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_AUTHENTICATION_KEY_ALIAS)));
        }

        @Override // com.amazon.alexa.accessory.crypto.persistence.NegotiatedDBEntry
        protected ContentValues asContentValues(String str) {
            ContentValues asContentValues = super.asContentValues(str);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_WRAPPING_CIPHER_TRANSFORMATION, this.wrappingCipherTransformation);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_WRAPPING_KEY_ALIAS, this.wrapperKey.alias);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_DECRYPTION_KEY_ALIAS, this.decryptionKey.alias);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V1.COLUMN_NAME_AUTHENTICATION_KEY_ALIAS, this.authenticationKey.alias);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_STORAGE_VERSION, (Integer) 1);
            return asContentValues;
        }

        @Override // com.amazon.alexa.accessory.crypto.persistence.NegotiatedDBEntry
        @RequiresApi(api = 23)
        protected void performDelete(String str, KeyStoreMediator keyStoreMediator, boolean z) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException, CryptoKeyDataStore.InvalidStoreStateException {
            keyStoreMediator.deleteKeysFromStore(str, this, z);
        }

        @Override // com.amazon.alexa.accessory.crypto.persistence.NegotiatedDBEntry
        @RequiresApi(api = 23)
        protected CryptoKeyDataStore.NegotiatedData toNegotiatedData(String str, KeyStoreMediator keyStoreMediator) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException, CryptoKeyDataStore.InvalidStoreStateException {
            return keyStoreMediator.getKeysFromStore(str, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class V2 extends NegotiatedDBEntry {
        final KeyStoreAlias authenticationKeyWrapperAlias;
        final KeyStoreAlias decryptionKeyWrapperAlias;
        final KeyStoreAlias rootKeyWrapperAlias;
        final String wrappedAuthenticationKey;
        final String wrappedAuthenticationKeyCipherTransformation;
        final String wrappedDecryptionKey;
        final String wrappedDecryptionKeyCipherTransformation;
        final String wrappedRootKeyCipherTransformation;

        /* JADX INFO: Access modifiers changed from: package-private */
        public V2(int i, SupportedCipherSuite supportedCipherSuite, String str, long j, long j2, long j3, String str2, String str3, KeyStoreAlias keyStoreAlias, String str4, String str5, KeyStoreAlias keyStoreAlias2, String str6, String str7, KeyStoreAlias keyStoreAlias3) {
            super(i, supportedCipherSuite, str, j, j2, j3, str2);
            this.wrappedRootKeyCipherTransformation = str3;
            this.rootKeyWrapperAlias = keyStoreAlias;
            this.wrappedDecryptionKey = str4;
            this.wrappedDecryptionKeyCipherTransformation = str5;
            this.decryptionKeyWrapperAlias = keyStoreAlias2;
            this.wrappedAuthenticationKey = str6;
            this.wrappedAuthenticationKeyCipherTransformation = str7;
            this.authenticationKeyWrapperAlias = keyStoreAlias3;
        }

        static V2 fromCurrentCursorEntry(String str, Cursor cursor) throws CryptoKeyDataStore.InvalidStoreStateException {
            int i = cursor.getInt(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_EXCHANGE_PROTOCOL_VERSION));
            String string = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_CIPHER_SUITE));
            String string2 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_CIPHER_TRANSFORMATION));
            long j = cursor.getLong(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_EXCHANGE_TIMESTAMP));
            long j2 = cursor.getLong(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_ROTATION_TIMESTAMP));
            long j3 = cursor.getLong(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_ACTIVE_KEY_VERSION));
            String string3 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_WRAPPED_ROOT_KEY));
            String string4 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_ROOT_KEY_CIPHER_TRANSFORMATION));
            String string5 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_ROOT_KEY_WRAPPER_ALIAS));
            String string6 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_DECRYPTION_KEY));
            String string7 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_DECRYPTION_KEY_CIPHER_TRANSFORMATION));
            String string8 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_DECRYPTION_KEY_WRAPPER_ALIAS));
            String string9 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_AUTHENTICATION_KEY));
            String string10 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_AUTHENTICATION_KEY_CIPHER_TRANSFORMATION));
            String string11 = cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_AUTHENTICATION_KEY_WRAPPER_ALIAS));
            Optional<SupportedCipherSuite> fromAccessoryDescriptor = CipherSuiteParser.fromAccessoryDescriptor(string);
            if (fromAccessoryDescriptor.isPresent()) {
                return new V2(i, fromAccessoryDescriptor.get(), string2, j, j2, j3, string3, string4, new KeyStoreAlias(string5), string6, string7, new KeyStoreAlias(string8), string9, string10, new KeyStoreAlias(string11));
            }
            throw new CryptoKeyDataStore.InvalidStoreStateException(str, GeneratedOutlineSupport1.outline72("The stored keys were generated from a cipher-suite that is no longer supported: ", string));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List<String> getV2KeyAliasesAtCurrentCursorEntry(String str, Cursor cursor) throws CryptoKeyDataStore.InvalidStoreStateException {
            return Arrays.asList(cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_ROOT_KEY_WRAPPER_ALIAS)), cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_DECRYPTION_KEY_WRAPPER_ALIAS)), cursor.getString(NegotiatedDBEntry.getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_AUTHENTICATION_KEY_WRAPPER_ALIAS)));
        }

        @Override // com.amazon.alexa.accessory.crypto.persistence.NegotiatedDBEntry
        protected ContentValues asContentValues(String str) {
            ContentValues asContentValues = super.asContentValues(str);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_ROOT_KEY_CIPHER_TRANSFORMATION, this.wrappedRootKeyCipherTransformation);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_ROOT_KEY_WRAPPER_ALIAS, this.rootKeyWrapperAlias.alias);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_DECRYPTION_KEY, this.wrappedDecryptionKey);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_DECRYPTION_KEY_CIPHER_TRANSFORMATION, this.wrappedDecryptionKeyCipherTransformation);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_DECRYPTION_KEY_WRAPPER_ALIAS, this.decryptionKeyWrapperAlias.alias);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_AUTHENTICATION_KEY, this.wrappedAuthenticationKey);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_WRAPPED_AUTHENTICATION_KEY_CIPHER_TRANSFORMATION, this.wrappedAuthenticationKeyCipherTransformation);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.V2.COLUMN_NAME_AUTHENTICATION_KEY_WRAPPER_ALIAS, this.authenticationKeyWrapperAlias.alias);
            asContentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_STORAGE_VERSION, (Integer) 2);
            return asContentValues;
        }

        @Override // com.amazon.alexa.accessory.crypto.persistence.NegotiatedDBEntry
        @RequiresApi(api = 23)
        protected void performDelete(String str, KeyStoreMediator keyStoreMediator, boolean z) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException, CryptoKeyDataStore.InvalidStoreStateException {
            keyStoreMediator.deleteKeysFromStore(str, this, z);
        }

        @Override // com.amazon.alexa.accessory.crypto.persistence.NegotiatedDBEntry
        @RequiresApi(api = 23)
        protected CryptoKeyDataStore.NegotiatedData toNegotiatedData(String str, KeyStoreMediator keyStoreMediator) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException, CryptoKeyDataStore.InvalidStoreStateException {
            return keyStoreMediator.getKeysFromStore(str, this);
        }
    }

    NegotiatedDBEntry(int i, SupportedCipherSuite supportedCipherSuite, String str, long j, long j2, long j3, String str2) {
        this.keyExchangeProtocolVersion = i;
        this.cipherSuite = supportedCipherSuite;
        this.cipherTransformation = str;
        this.keyExchangeTimestampMillis = j;
        this.lastKeyRotationTimestampMillis = j2;
        this.currentVersion = j3;
        this.wrappedRootKey = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 23)
    public static NegotiatedDBEntry fromCurrentCursorEntry(String str, Cursor cursor) throws CryptoKeyDataStore.InvalidStoreStateException {
        int i = cursor.getInt(getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_STORAGE_VERSION));
        if (i != 1) {
            if (i == 2) {
                return V2.fromCurrentCursorEntry(str, cursor);
            }
            throw new CryptoKeyDataStore.InvalidStoreStateException(str, GeneratedOutlineSupport1.outline49("Unexpected key storage version ", i));
        }
        return V1.fromCurrentCursorEntry(str, cursor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getColumnIndex(String str, Cursor cursor, String str2) throws CryptoKeyDataStore.InvalidStoreStateException {
        try {
            return cursor.getColumnIndexOrThrow(str2);
        } catch (IllegalArgumentException e) {
            throw new CryptoKeyDataStore.InvalidStoreStateException(str, String.format("Required column %s missing from SQLite DB %s ", str2, NegotiatedDataSQLLiteContract.SQLITE_DB_NAME), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 23)
    public static List<String> getKeyAliasesAtCurrentCursorEntry(String str, Cursor cursor) throws CryptoKeyDataStore.InvalidStoreStateException {
        int i = cursor.getInt(getColumnIndex(str, cursor, NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_STORAGE_VERSION));
        if (i != 1) {
            if (i == 2) {
                return V2.getV2KeyAliasesAtCurrentCursorEntry(str, cursor);
            }
            throw new CryptoKeyDataStore.InvalidStoreStateException(str, GeneratedOutlineSupport1.outline49("Unexpected key storage version ", i));
        }
        return V1.getV1KeyAliasesAtCurrentCursorEntry(str, cursor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContentValues asContentValues(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_ACCESSORY_ID, str);
        contentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_EXCHANGE_PROTOCOL_VERSION, Integer.valueOf(this.keyExchangeProtocolVersion));
        contentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_CIPHER_SUITE, this.cipherSuite.descriptor);
        contentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_CIPHER_TRANSFORMATION, this.cipherTransformation);
        contentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_EXCHANGE_TIMESTAMP, Long.valueOf(this.keyExchangeTimestampMillis));
        contentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_KEY_ROTATION_TIMESTAMP, Long.valueOf(this.lastKeyRotationTimestampMillis));
        contentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_ACTIVE_KEY_VERSION, Long.valueOf(this.currentVersion));
        contentValues.put(NegotiatedDataSQLLiteContract.Columns.COLUMN_NAME_WRAPPED_ROOT_KEY, this.wrappedRootKey);
        return contentValues;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void performDelete(String str, KeyStoreMediator keyStoreMediator, boolean z) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException, CryptoKeyDataStore.InvalidStoreStateException;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract CryptoKeyDataStore.NegotiatedData toNegotiatedData(String str, KeyStoreMediator keyStoreMediator) throws CryptoKeyDataStore.CryptoKeyDataStoreIOException, CryptoKeyDataStore.InvalidStoreStateException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CryptoKeyDataStore.NegotiatedData toNegotiatedData(Key key, Key key2, Key key3) {
        return new CryptoKeyDataStore.NegotiatedData(this.keyExchangeProtocolVersion, this.cipherSuite, this.cipherTransformation, key, key2, key3, this.keyExchangeTimestampMillis, this.lastKeyRotationTimestampMillis);
    }
}
