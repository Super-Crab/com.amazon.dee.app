package com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear;
/* loaded from: classes13.dex */
public class Checks {
    public static void checkArgument(boolean z, String str) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(str);
    }

    public static void checkNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new NullPointerException(str);
    }
}
