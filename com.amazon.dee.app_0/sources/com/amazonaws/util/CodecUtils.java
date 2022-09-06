package com.amazonaws.util;
/* loaded from: classes.dex */
public enum CodecUtils {
    ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int sanitize(String str, byte[] bArr) {
        int length = bArr.length;
        char[] charArray = str.toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char c = charArray[i2];
            if (c != '\r' && c != '\n' && c != ' ') {
                if (c <= 127) {
                    bArr[i] = (byte) c;
                    i++;
                } else {
                    throw new IllegalArgumentException("Invalid character found at position " + i2 + " for " + str);
                }
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sanityCheckLastPos(int i, int i2) {
        if ((i & i2) == 0) {
            return;
        }
        throw new IllegalArgumentException("Invalid last non-pad character detected");
    }

    public static byte[] toBytesDirect(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i < bArr.length; i++) {
            char c = charArray[i];
            if (c <= 127) {
                bArr[i] = (byte) c;
            } else {
                throw new IllegalArgumentException("Invalid character found at position " + i + " for " + str);
            }
        }
        return bArr;
    }

    public static String toStringDirect(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            cArr[i2] = (char) bArr[i];
            i++;
            i2++;
        }
        return new String(cArr);
    }
}
