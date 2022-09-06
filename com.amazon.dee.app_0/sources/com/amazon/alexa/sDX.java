package com.amazon.alexa;

import android.content.pm.PackageManager;
import android.util.Log;
/* compiled from: MetroIOComponentsStateProvider.java */
/* loaded from: classes.dex */
public class sDX extends AbstractC0207hoU {
    public static final String jiA = "sDX";
    public final PackageManager Qle;

    public sDX(String str, PackageManager packageManager, IKe iKe) {
        super(str, iKe, "com.amazon.dee.app");
        this.Qle = packageManager;
    }

    @Override // com.amazon.alexa.AbstractC0207hoU
    public cMY BIo() {
        try {
            return cMY.zZm(this.Qle.getPackageInfo("com.amazon.dee.app", 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(jiA, "Unable to find package: com.amazon.dee.app", e);
            return cMY.zZm;
        }
    }
}
