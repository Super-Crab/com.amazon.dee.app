package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.api.MAPInit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jk {
    private static final String TAG = "com.amazon.identity.auth.device.jk";
    private static final boolean rI = gS();

    private jk() {
    }

    public static boolean gR() {
        return rI && !MAPInit.isRunningInFunctionalTest();
    }

    private static boolean gS() {
        try {
            Class.forName("android.test.mock.MockContext");
            Class.forName("com.amazon.identity.auth.unittest.IsRunningInUnitTest");
            io.e(TAG, "Is running in unit test!");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
