package com.amazon.alexa.alertsca.utils;
/* loaded from: classes6.dex */
public class StringUtils {
    public static final String EMPTY = "";

    public static String getLastPart(String str, int i) {
        return str == null ? "" : str.substring(str.lastIndexOf(i) + 1);
    }
}
