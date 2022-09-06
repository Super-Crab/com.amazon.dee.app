package com.amazon.alexa.accessory.crypto;

import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
/* loaded from: classes.dex */
public interface NegotiatedDataPersistor {
    void deleteData(Iterable<String> iterable) throws CryptoKeyDataStore.CryptoKeyDataStoreException;

    CryptoKeyDataStore.NegotiatedData getData(String str) throws CryptoKeyDataStore.InvalidInputsException, CryptoKeyDataStore.CryptoKeyDataStoreException;

    boolean hasData(String str) throws CryptoKeyDataStore.CryptoKeyDataStoreException;

    CryptoKeyDataStore.ListResult listData(int i, CryptoKeyDataStore.PaginationToken paginationToken) throws CryptoKeyDataStore.InvalidInputsException, CryptoKeyDataStore.CryptoKeyDataStoreException;

    void putData(String str, CryptoKeyDataStore.NegotiatedData negotiatedData) throws CryptoKeyDataStore.InvalidInputsException, CryptoKeyDataStore.CryptoKeyDataStoreException;
}
