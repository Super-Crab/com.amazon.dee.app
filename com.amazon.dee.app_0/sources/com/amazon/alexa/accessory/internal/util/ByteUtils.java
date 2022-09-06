package com.amazon.alexa.accessory.internal.util;

import com.amazon.deecomms.common.metrics.MetricKeys;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class ByteUtils {
    private static final int BYTE_MASK = 255;
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private ByteUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static String bytesToHex(byte[] bArr, char c) {
        if (bArr == null) {
            return "null";
        }
        if (bArr.length == 0) {
            return MetricKeys.EMPTY_VALUE;
        }
        int length = (bArr.length * 3) - 1;
        char[] cArr = new char[length];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 3;
            char[] cArr2 = HEX_ARRAY;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
            int i4 = i3 + 2;
            if (i4 < length) {
                cArr[i4] = c;
            }
        }
        return new String(cArr);
    }

    public static byte toByte(int i) {
        return (byte) (i & 255);
    }

    public static String bytesToHex(byte[] bArr) {
        return bytesToHex(bArr, Chars.SPACE);
    }
}
