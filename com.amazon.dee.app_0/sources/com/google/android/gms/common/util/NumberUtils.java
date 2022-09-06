package com.google.android.gms.common.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.annotation.KeepForSdk;
@VisibleForTesting
@KeepForSdk
/* loaded from: classes2.dex */
public class NumberUtils {
    private NumberUtils() {
    }

    @KeepForSdk
    public static long parseHexLong(String str) {
        if (str.length() <= 16) {
            if (str.length() == 16) {
                return (Long.parseLong(str.substring(0, 8), 16) << 32) | Long.parseLong(str.substring(8), 16);
            }
            return Long.parseLong(str, 16);
        }
        throw new NumberFormatException(GeneratedOutlineSupport1.outline30(str.length() + 37, "Invalid input: ", str, " exceeds 16 characters"));
    }
}
