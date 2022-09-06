package com.amazon.commsnetworking.util;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public final class TextUtils {
    private static String[] emptyStringArray = new String[0];

    private TextUtils() {
    }

    public static boolean isEmpty(@Nullable CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static String[] split(String str, String str2) {
        if (str.length() == 0) {
            return emptyStringArray;
        }
        return str.split(str2, -1);
    }
}
