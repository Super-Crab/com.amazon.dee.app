package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class aa {
    private static final String TAG = "com.amazon.identity.auth.device.aa";
    private static z cn;

    private aa() {
    }

    public static synchronized z f(Context context) {
        synchronized (aa.class) {
            if (cn != null) {
                return cn;
            }
            ed M = ed.M(context.getApplicationContext());
            if (((ds) M.getSystemService("sso_platform")).dr()) {
                io.i(TAG, "Returning Profile multiple profile settings");
                return new ad(M);
            }
            io.i(TAG, "Returning Default multiple profile settings");
            return new v(M);
        }
    }
}
