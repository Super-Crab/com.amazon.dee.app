package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
@Deprecated
/* loaded from: classes12.dex */
public final class iu {
    private static final String TAG = "com.amazon.identity.auth.device.iu";

    private iu() {
    }

    public static String O(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeBundle(bundle);
            return e(obtain);
        } finally {
            obtain.recycle();
        }
    }

    public static Collection<Map<String, String>> dr(String str) {
        Parcel dt;
        if (str == null || (dt = dt(str)) == null) {
            return null;
        }
        try {
            return (Collection) dt.readSerializable();
        } finally {
            dt.recycle();
        }
    }

    public static Bundle ds(String str) {
        if (str == null) {
            return null;
        }
        try {
            Parcel dt = dt(str);
            if (dt == null) {
                return null;
            }
            Bundle readBundle = dt.readBundle();
            dt.recycle();
            return readBundle;
        } catch (Exception e) {
            io.w(TAG, "Failed to deserialize parcel", e);
            return null;
        }
    }

    private static Parcel dt(String str) {
        byte[] decode = Base64.decode(str, 0);
        if (decode == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(decode, 0, decode.length);
        obtain.setDataPosition(0);
        return obtain;
    }

    public static Serializable du(String str) {
        if (str == null) {
            return null;
        }
        Parcel dt = dt(str);
        try {
            return dt.readSerializable();
        } finally {
            dt.recycle();
        }
    }

    private static String e(Parcel parcel) {
        return Base64.encodeToString(parcel.marshall(), 0);
    }

    public static String f(Collection<Map<String, String>> collection) {
        if (collection == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            ArrayList arrayList = new ArrayList();
            for (Map<String, String> map : collection) {
                arrayList.add(new HashMap(map));
            }
            obtain.writeSerializable(arrayList);
            return e(obtain);
        } finally {
            obtain.recycle();
        }
    }
}
