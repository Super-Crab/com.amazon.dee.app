package com.amazon.whisperjoin.deviceprovisioningservice.util;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class ParcelableMapHelper {
    public static <K extends Parcelable, V extends Parcelable> Map<K, V> readParcelableMap(Parcel parcel, Class<K> cls, Class<V> cls2) {
        int readInt = parcel.readInt();
        HashMap hashMap = new HashMap(readInt);
        for (int i = 0; i < readInt; i++) {
            hashMap.put(cls.cast(parcel.readParcelable(cls.getClassLoader())), cls2.cast(parcel.readParcelable(cls2.getClassLoader())));
        }
        return hashMap;
    }

    public static <K extends Parcelable, V extends Parcelable> void writeParcelableMap(Parcel parcel, int i, Map<K, V> map) {
        parcel.writeInt(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            parcel.writeParcelable(entry.getKey(), i);
            parcel.writeParcelable(entry.getValue(), i);
        }
    }
}
