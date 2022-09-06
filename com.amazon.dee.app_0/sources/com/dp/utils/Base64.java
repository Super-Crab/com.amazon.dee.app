package com.dp.utils;
/* loaded from: classes2.dex */
public class Base64 {
    public static byte[] decode(String str) {
        return android.util.Base64.decode(str, 0);
    }

    public static byte[] encodeNoWrap(byte[] bArr) {
        return android.util.Base64.encode(bArr, 2);
    }

    public static String encodeString(byte[] bArr) {
        return android.util.Base64.encodeToString(bArr, 0);
    }
}
