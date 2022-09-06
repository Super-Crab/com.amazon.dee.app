package com.amazon.clouddrive.utils;

import java.util.Collection;
/* loaded from: classes11.dex */
public class AssertUtils {
    private AssertUtils() {
        throw new UnsupportedOperationException("Do not instantiate.");
    }

    public static <V> void assertNotNull(V v, String str) {
        if (v != null) {
            return;
        }
        throw new RuntimeException(str);
    }

    public static void assertNotNullOrEmpty(String str, String str2) {
        if (str == null || str.equals("")) {
            throw new RuntimeException(str2);
        }
    }

    public static <V> void assertNotNullOrEmpty(Collection<V> collection, String str) {
        if (collection == null || collection.isEmpty()) {
            throw new RuntimeException(str);
        }
    }

    public static <V> void assertNotNullOrEmpty(V[] vArr, String str) {
        if (vArr == null || vArr.length == 0) {
            throw new RuntimeException(str);
        }
    }
}
