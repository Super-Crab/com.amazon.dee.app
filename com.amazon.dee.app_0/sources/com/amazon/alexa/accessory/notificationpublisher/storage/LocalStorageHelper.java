package com.amazon.alexa.accessory.notificationpublisher.storage;
/* loaded from: classes.dex */
public final class LocalStorageHelper {
    public static final String DATA_ENTRY_TIMESTAMP = "timestamp";
    public static final String DATA_ENTRY_TTL = "ttl";
    protected static final int DATA_ENTRY_TTL_VALUE = 60000;
    public static final String EMPTY_VALUE_JSON_OBJECT_STR = "{\"value\":\"{}\"}";
    public static final String ENCRYPT_KEY = "encrypt";
    public static final String EVICTION_TIER_1 = "1";
    public static final String EVICTION_TIER_2 = "2";
    public static final String EVICTION_TIER_KEY = "evictionTier";
    public static final String FOCUS_FILTER_TAG = "NotificationFilterSettings";
    public static final String KEY_KEY = "key";
    public static final String MODIFICATION_DATE_KEY = "modificationDate";
    public static final String VALUE_KEY = "value";

    private LocalStorageHelper() {
    }
}
