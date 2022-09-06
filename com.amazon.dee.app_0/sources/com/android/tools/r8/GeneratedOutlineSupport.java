package com.android.tools.r8;

import com.amazon.comms.log.CommsLogger;
/* compiled from: outline */
/* loaded from: classes.dex */
public class GeneratedOutlineSupport {
    public static String outline0(String str, String str2) {
        return GeneratedOutlineSupport1.outline72(str, str2);
    }

    public static StringBuilder outline1(String str) {
        return GeneratedOutlineSupport1.outline107(str);
    }

    public static StringBuilder outline2(StringBuilder sb, String str, String str2, String str3, String str4) {
        GeneratedOutlineSupport1.outline181(sb, str, str2, str3, str4);
        return sb;
    }

    public static void outline3(String str, String str2, CommsLogger commsLogger) {
        commsLogger.e(str + str2);
    }

    public static void outline4(String str, String str2, CommsLogger commsLogger) {
        commsLogger.i(str + str2);
    }

    public static void outline5(String str, boolean z, CommsLogger commsLogger) {
        commsLogger.i(str + z);
    }
}
