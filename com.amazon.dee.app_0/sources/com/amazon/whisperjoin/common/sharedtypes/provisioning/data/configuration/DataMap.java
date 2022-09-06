package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
/* loaded from: classes13.dex */
public class DataMap implements Parcelable {
    public static final Parcelable.Creator<DataMap> CREATOR = new Parcelable.Creator<DataMap>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMap.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DataMap mo5436createFromParcel(Parcel parcel) {
            if (parcel != null) {
                DataMap dataMap = new DataMap();
                int readInt = parcel.readInt();
                for (int i = 0; i < readInt; i++) {
                    dataMap.putDataMapValue(parcel.readString(), DataMapValue.CREATOR.createFromParcel(parcel));
                }
                return dataMap;
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DataMap[] mo5437newArray(int i) {
            if (i >= 0) {
                return new DataMap[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }
    };
    private final Map<String, DataMapValue> mDataMap;

    public DataMap() {
        this.mDataMap = new HashMap();
    }

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

    /* JADX INFO: Access modifiers changed from: private */
    public void putDataMapValue(String str, DataMapValue dataMapValue) {
        putDataMapValue(str, dataMapValue, false);
    }

    public boolean containsKey(String str) {
        if (str != null) {
            return this.mDataMap.containsKey(str);
        }
        throw new IllegalArgumentException("key can not be null");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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

    public Map<String, com.amazon.devicesetup.common.DataMapValue> getDataMapForRequest() {
        HashMap hashMap = new HashMap();
        for (String str : this.mDataMap.keySet()) {
            DataMapValue dataMapValue = this.mDataMap.get(str);
            com.amazon.devicesetup.common.DataMapValue dataMapValue2 = new com.amazon.devicesetup.common.DataMapValue();
            if (dataMapValue.getStringValue() != null) {
                dataMapValue2.setStringValue(dataMapValue.getStringValue());
            } else if (dataMapValue.getBooleanValue() != null) {
                dataMapValue2.setBoolValue(dataMapValue.getBooleanValue().booleanValue());
            } else if (dataMapValue.getIntegerValue() != null) {
                dataMapValue2.setIntegerValue(dataMapValue.getIntegerValue().intValue());
            } else if (dataMapValue.getBytesValue() != null) {
                dataMapValue2.setBytesValue(ByteBuffer.wrap(dataMapValue.getBytesValue()));
            }
            hashMap.put(str, dataMapValue2);
        }
        return hashMap;
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

    public void putAllValuesOverwrite(DataMap dataMap) {
        for (Map.Entry<String, DataMapValue> entry : dataMap.getMap().entrySet()) {
            putDataMapValue(entry.getKey(), entry.getValue(), true);
        }
    }

    public void putBooleanValue(String str, Boolean bool) {
        putDataMapValue(str, new DataMapValue(bool));
    }

    public void putBooleanValueOverwrite(String str, Boolean bool) {
        putDataMapValue(str, new DataMapValue(bool), true);
    }

    public void putIntegerValue(String str, Integer num) {
        putDataMapValue(str, new DataMapValue(num));
    }

    public void putIntegerValueOverwrite(String str, Integer num) {
        putDataMapValue(str, new DataMapValue(num), true);
    }

    public void putStringValue(String str, String str2) {
        putDataMapValue(str, new DataMapValue(str2));
    }

    public void putStringValueOverwrite(String str, String str2) {
        putDataMapValue(str, new DataMapValue(str2), true);
    }

    public void putValue(String str, byte[] bArr) {
        putDataMapValue(str, new DataMapValue(bArr));
    }

    public void putValueOverwrite(String str, byte[] bArr) {
        putDataMapValue(str, new DataMapValue(bArr), true);
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

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.mDataMap.size());
            for (Map.Entry<String, DataMapValue> entry : this.mDataMap.entrySet()) {
                parcel.writeString(entry.getKey());
                entry.getValue().writeToParcel(parcel, i);
            }
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    private void putDataMapValue(String str, DataMapValue dataMapValue, boolean z) {
        if (str != null) {
            if (dataMapValue != null) {
                if (!z && this.mDataMap.containsKey(str)) {
                    throw new RuntimeException(GeneratedOutlineSupport1.outline72("Value already exists for key: ", str));
                }
                this.mDataMap.put(str, dataMapValue);
                return;
            }
            throw new IllegalArgumentException("value can not be null");
        }
        throw new IllegalArgumentException("key can not be null");
    }

    public DataMap(DataMap dataMap) {
        this.mDataMap = new HashMap(dataMap.mDataMap);
    }
}
