package com.amazon.alexa;

import android.content.pm.PackageManager;
import android.util.Log;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* compiled from: MotoIOComponentsStateProvider.java */
/* loaded from: classes.dex */
public class AVk extends AbstractC0207hoU {
    public static final String jiA = "AVk";
    public final PackageManager Qle;

    public AVk(String str, PackageManager packageManager, IKe iKe) {
        super(str, iKe, "com.motorola.motoalexa");
        this.Qle = packageManager;
    }

    @Override // com.amazon.alexa.AbstractC0207hoU
    public cMY BIo() {
        try {
            int i = this.Qle.getPackageInfo("com.motorola.motoalexa", 0).versionCode;
            int i2 = this.Qle.getPackageInfo("com.motorola.audiomonitor", 0).versionCode;
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(i));
            sb.append(ProcessIdUtil.DEFAULT_PROCESSID);
            sb.append(String.valueOf(i2));
            return cMY.zZm(sb.toString());
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(jiA, "Unable to find Moto Hands-Free package: ", e);
            return cMY.zZm;
        }
    }
}
