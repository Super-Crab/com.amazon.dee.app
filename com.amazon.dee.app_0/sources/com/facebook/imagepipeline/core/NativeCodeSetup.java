package com.facebook.imagepipeline.core;
/* loaded from: classes2.dex */
public class NativeCodeSetup {
    private static boolean sUseNativeCode = true;

    private NativeCodeSetup() {
    }

    public static boolean getUseNativeCode() {
        return sUseNativeCode;
    }

    public static void setUseNativeCode(boolean useNativeCode) {
        sUseNativeCode = useNativeCode;
    }
}
