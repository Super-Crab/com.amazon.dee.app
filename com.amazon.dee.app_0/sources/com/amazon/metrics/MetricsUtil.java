package com.amazon.metrics;

import amazon.speech.util.SystemPropertiesHelper;
import android.content.Context;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes9.dex */
public abstract class MetricsUtil {
    public static final String KNIGHT_METRICS_TEST_PROPERTY = "persist.knight.metrics.test";
    static final MetricsUtil mInstance = createInstance();

    private static MetricsUtil createInstance() {
        if (isTest()) {
            return new TestMetricsUtil();
        }
        return new MetricsUtilImpl();
    }

    public static MetricsUtil getInstance() {
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTest() {
        return new SystemPropertiesHelper().getBoolean(KNIGHT_METRICS_TEST_PROPERTY, false);
    }

    public abstract void addGlobalMetadata(Map<String, String> map);

    public boolean addTimer(Context context, String str, String str2, String str3, long j, int i) {
        return addTimer(context, str, str2, str3, j, i, null);
    }

    public abstract boolean addTimer(Context context, String str, String str2, String str3, long j, int i, MetadataHelper metadataHelper);

    public abstract boolean recordCounter(Context context, String str, String str2, String str3, MetadataHelper metadataHelper, double d);

    public abstract boolean recordCounter(Context context, String str, String str2, String str3, MetadataHelper metadataHelper, double d, boolean z);

    public abstract boolean recordDiscreteValue(Context context, String str, String str2, String str3, String str4, boolean z, MetadataHelper metadataHelper);

    public boolean recordOccurrence(Context context, String str, String str2, String str3, MetadataHelper metadataHelper) {
        return recordCounter(context, str, str2, str3, metadataHelper, 1.0d);
    }

    public abstract boolean removeTimer(Context context, String str);

    public abstract boolean startTimer(Context context, String str, String str2, String str3);

    public abstract boolean stopTimer(Context context, String str, MetadataHelper metadataHelper);

    /* loaded from: classes9.dex */
    public static class MetadataHelper {
        public final Map<String, String> mMetadataMap;

        public MetadataHelper() {
            this.mMetadataMap = new ConcurrentHashMap();
        }

        public void addMetadata(String str, String str2) {
            if (str != null && str2 != null && !str.isEmpty() && !str2.isEmpty()) {
                this.mMetadataMap.put(str, str2);
                return;
            }
            throw new IllegalArgumentException();
        }

        public boolean containsName(String str) {
            if (str != null) {
                return this.mMetadataMap.containsKey(str);
            }
            throw new IllegalArgumentException();
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof MetadataHelper)) {
                return this.mMetadataMap.equals(((MetadataHelper) obj).mMetadataMap);
            }
            return false;
        }

        public String getMetadataValue(String str) {
            if (str != null) {
                return this.mMetadataMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        public int hashCode() {
            return this.mMetadataMap.hashCode();
        }

        public boolean isEmpty() {
            return this.mMetadataMap.isEmpty();
        }

        public MetadataHelper(String str, String str2) {
            this();
            if (str != null && str2 != null) {
                addMetadata(str, str2);
                return;
            }
            throw new IllegalArgumentException();
        }

        public MetadataHelper(Map<String, String> map) {
            this.mMetadataMap = map != null ? new ConcurrentHashMap(map) : new ConcurrentHashMap();
        }
    }
}
