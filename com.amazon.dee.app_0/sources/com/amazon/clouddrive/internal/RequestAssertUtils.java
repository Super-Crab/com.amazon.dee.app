package com.amazon.clouddrive.internal;

import java.util.List;
/* loaded from: classes11.dex */
class RequestAssertUtils {
    private RequestAssertUtils() {
        throw new UnsupportedOperationException("Do not instantiate.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <V> void assertNotNull(V v, String str) {
        if (v != null) {
            return;
        }
        throw new IllegalArgumentException(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void assertNotNullOrEmpty(String str, String str2) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void assertPositive(int i, String str) {
        if (i >= 1) {
            return;
        }
        throw new IllegalArgumentException(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void assertNotNullOrEmpty(List list, String str) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(str);
        }
    }
}
