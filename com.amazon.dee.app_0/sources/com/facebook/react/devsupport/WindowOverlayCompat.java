package com.facebook.react.devsupport;

import android.os.Build;
/* loaded from: classes2.dex */
class WindowOverlayCompat {
    private static final int ANDROID_OREO = 26;
    private static final int TYPE_APPLICATION_OVERLAY = 2038;
    static final int TYPE_SYSTEM_ALERT;
    static final int TYPE_SYSTEM_OVERLAY;

    static {
        int i = Build.VERSION.SDK_INT;
        TYPE_SYSTEM_ALERT = TYPE_APPLICATION_OVERLAY;
        TYPE_SYSTEM_OVERLAY = TYPE_APPLICATION_OVERLAY;
    }

    WindowOverlayCompat() {
    }
}
