package com.amazon.alexa.protocols.datastore;
/* loaded from: classes9.dex */
public interface DataStoreService {
    public static final String COLUMN_NAME_KEY = "key";
    public static final String COLUMN_NAME_VALUE = "value";
    public static final String TABLE_NAME = "DataItem";

    void clear();

    String retrieveValue(String str);

    void storeValue(String str, String str2);
}
