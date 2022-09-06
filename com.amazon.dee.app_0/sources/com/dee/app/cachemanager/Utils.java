package com.dee.app.cachemanager;
/* loaded from: classes9.dex */
public final class Utils {
    private Utils() {
    }

    public static int estimateSizeOfString(String str) {
        return (str.length() * 2) + 32;
    }
}
