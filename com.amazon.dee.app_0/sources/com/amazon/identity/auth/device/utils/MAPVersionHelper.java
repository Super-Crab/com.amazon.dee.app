package com.amazon.identity.auth.device.utils;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidParameterException;
/* loaded from: classes12.dex */
public final class MAPVersionHelper {
    private static final String DELIMETER = "\\.";

    private MAPVersionHelper() {
    }

    public static String getVersionAsString(int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : iArr) {
            stringBuffer.append(i);
            stringBuffer.append('.');
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public static int[] getVersionInfo(String str) {
        String[] split = str.split("\\.");
        if (split.length >= 1) {
            int[] iArr = new int[split.length];
            try {
                int length = split.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 + 1;
                    iArr[i2] = Integer.parseInt(split[i]);
                    i++;
                    i2 = i3;
                }
                return iArr;
            } catch (NumberFormatException unused) {
                throw new InvalidParameterException(GeneratedOutlineSupport1.outline75("getVersionInfo: version (", str, ") must be in format of X[.X.X]... - where X must be an integer"));
            }
        }
        throw new InvalidParameterException(GeneratedOutlineSupport1.outline75("version (", str, ") must be in format of X.X.X"));
    }
}
