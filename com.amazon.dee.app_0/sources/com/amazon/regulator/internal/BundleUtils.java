package com.amazon.regulator.internal;

import android.os.Bundle;
import android.util.SparseArray;
import com.drew.metadata.mov.QuickTimeAtomTypes;
/* loaded from: classes13.dex */
public final class BundleUtils {
    private BundleUtils() {
        throw new UnsupportedOperationException("No instances!");
    }

    public static SparseArray<String> getSparseStringArray(Bundle bundle, String str) {
        Preconditions.nonNull(bundle, "bundle == null");
        Preconditions.nonNull(str, "key == null");
        Bundle bundle2 = bundle.getBundle(str);
        if (bundle2 == null) {
            return null;
        }
        int[] intArray = bundle2.getIntArray(QuickTimeAtomTypes.ATOM_KEYS);
        String[] stringArray = bundle2.getStringArray("values");
        if (intArray == null || stringArray == null) {
            return null;
        }
        int length = intArray.length;
        SparseArray<String> sparseArray = new SparseArray<>(length);
        for (int i = 0; i < length; i++) {
            sparseArray.put(intArray[i], stringArray[i]);
        }
        return sparseArray;
    }

    public static void putSparseStringArray(Bundle bundle, String str, SparseArray<String> sparseArray) {
        Preconditions.nonNull(bundle, "bundle == null");
        Preconditions.nonNull(str, "key == null");
        Preconditions.nonNull(sparseArray, "value == null");
        int size = sparseArray.size();
        int[] iArr = new int[size];
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = sparseArray.keyAt(i);
            strArr[i] = sparseArray.valueAt(i);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putIntArray(QuickTimeAtomTypes.ATOM_KEYS, iArr);
        bundle2.putStringArray("values", strArr);
        bundle.putBundle(str, bundle2);
    }
}
