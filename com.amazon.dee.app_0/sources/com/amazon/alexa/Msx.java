package com.amazon.alexa;

import android.app.KeyguardManager;
import android.os.Build;
import javax.inject.Inject;
/* compiled from: LockScreenManager.java */
/* loaded from: classes.dex */
public class Msx {
    public final KeyguardManager zZm;

    @Inject
    public Msx(KeyguardManager keyguardManager) {
        this.zZm = keyguardManager;
    }

    public static boolean zZm(KeyguardManager keyguardManager) {
        if (keyguardManager == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        return keyguardManager.isDeviceLocked();
    }

    public boolean zZm() {
        return zZm(this.zZm);
    }
}
