package com.amazon.devicesetup.provisioning.data.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
/* loaded from: classes12.dex */
public class DataMap {
    private final Map<String, DataMapValue> mDataMap = new HashMap();

    private DataMapValue getDataMapValue(String str) {
        if (str != null) {
            DataMapValue dataMapValue = this.mDataMap.get(str);
            if (dataMapValue == null) {
                throw new NoSuchElementException(GeneratedOutlineSupport1.outline72("No DataMapValue for key ", str));
            }
            return dataMapValue;
        }
        throw new IllegalArgumentException("key can not be null");
    }

    private void putDataMapValue(String str, DataMapValue dataMapValue) {
        if (str != null) {
            if (dataMapValue != null) {
                if (!this.mDataMap.containsKey(str)) {
                    this.mDataMap.put(str, dataMapValue);
                    return;
                }
                throw new RuntimeException(GeneratedOutlineSupport1.outline72("Value already exists for key: ", str));
            }
            throw new IllegalArgumentException("value can not be null");
        }
        throw new IllegalArgumentException("key can not be null");
    }

    public boolean containsKey(String str) {
        if (str != null) {
            return this.mDataMap.containsKey(str);
        }
        throw new IllegalArgumentException("key can not be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && DataMap.class == obj.getClass()) {
            Map<String, DataMapValue> map = this.mDataMap;
            Map<String, DataMapValue> map2 = ((DataMap) obj).mDataMap;
            try {
                for (String str : map2.keySet()) {
                    if (!map.get(str).equals(map2.get(str))) {
                        return false;
                    }
                }
                for (String str2 : map.keySet()) {
                    if (!map2.containsKey(str2)) {
                        return false;
                    }
                }
                return true;
            } catch (NullPointerException unused) {
            }
        }
        return false;
    }

    public Boolean getBooleanValue(String str) {
        return getDataMapValue(str).getBooleanValue();
    }

    public Integer getIntegerValue(String str) {
        DataMapValue dataMapValue = getDataMapValue(str);
        if (dataMapValue.getIntegerValue() != null) {
            return dataMapValue.getIntegerValue();
        }
        throw new NoSuchElementException(GeneratedOutlineSupport1.outline72("No integer value found for key: ", str));
    }

    public Map<String, DataMapValue> getMap() {
        return Collections.unmodifiableMap(this.mDataMap);
    }

    public int getSize() {
        return this.mDataMap.size();
    }

    public String getStringValue(String str) {
        return getDataMapValue(str).getStringValue();
    }

    public byte[] getValue(String str) {
        return getDataMapValue(str).getBytesValue();
    }

    public int hashCode() {
        return Objects.hashCode(this.mDataMap);
    }

    public boolean isEmpty() {
        return this.mDataMap.isEmpty();
    }

    public void putAllValues(DataMap dataMap) {
        for (Map.Entry<String, DataMapValue> entry : dataMap.getMap().entrySet()) {
            putDataMapValue(entry.getKey(), entry.getValue());
        }
    }

    public void putBooleanValue(String str, Boolean bool) {
        putDataMapValue(str, new DataMapValue(bool));
    }

    public void putIntegerValue(String str, Integer num) {
        putDataMapValue(str, new DataMapValue(num));
    }

    public void putStringValue(String str, String str2) {
        putDataMapValue(str, new DataMapValue(str2));
    }

    public void putValue(String str, byte[] bArr) {
        putDataMapValue(str, new DataMapValue(bArr));
    }

    public void removeValue(String str) {
        if (str != null) {
            this.mDataMap.remove(str);
            return;
        }
        throw new IllegalArgumentException("key can not be null");
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mDataMap", this.mDataMap).toString();
    }
}
