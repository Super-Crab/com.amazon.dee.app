package com.amazon.whisperjoin.common.sharedtypes.utility;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
/* loaded from: classes13.dex */
public class HexStringConverter {
    public static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(String.format(Locale.ENGLISH, "%02X", Byte.valueOf(bArr[i])));
        }
        return sb.toString();
    }

    public static String byteArrayToString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format(Locale.ENGLISH, "%02x", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    private static boolean isHexString(String str) {
        return str.matches("^\\p{XDigit}+$") && str.length() % 2 == 0;
    }

    public static byte[] stringToHexArray(String str) {
        if (isHexString(str)) {
            int length = str.length();
            byte[] bArr = new byte[length / 2];
            for (int i = 0; i < length; i += 2) {
                bArr[i / 2] = (byte) (Character.digit(str.charAt(i + 1), 16) + (Character.digit(str.charAt(i), 16) << 4));
            }
            return bArr;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(str, " is not a valid hex string"));
    }
}
