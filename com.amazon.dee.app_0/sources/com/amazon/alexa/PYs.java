package com.amazon.alexa;

import android.content.pm.PackageManager;
import android.util.Log;
/* compiled from: QualcommIOComponentsStateProvider.java */
/* loaded from: classes.dex */
public class PYs extends AbstractC0207hoU {
    public static final String jiA = "PYs";
    public final String JTe;
    public final PackageManager Qle;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PYs(java.lang.String r5, android.content.pm.PackageManager r6, com.amazon.alexa.IKe r7) {
        /*
            r4 = this;
            java.lang.String r0 = "com.quicinc.voice.activation"
            r1 = 0
            android.content.pm.PackageInfo r1 = r6.getPackageInfo(r0, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L11
            int r1 = r1.versionCode     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L11
            r2 = 400001(0x61a81, float:5.60521E-40)
            if (r1 < r2) goto L19
            java.lang.String r0 = "com.amazon.dee.app"
            goto L19
        L11:
            r1 = move-exception
            java.lang.String r2 = com.amazon.alexa.PYs.jiA
            java.lang.String r3 = "Unable to find package: com.quicinc.voice.activation"
            android.util.Log.e(r2, r3, r1)
        L19:
            r4.<init>(r5, r7, r0)
            r4.Qle = r6
            r4.JTe = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.PYs.<init>(java.lang.String, android.content.pm.PackageManager, com.amazon.alexa.IKe):void");
    }

    @Override // com.amazon.alexa.AbstractC0207hoU
    public cMY BIo() {
        try {
            return cMY.zZm(this.Qle.getPackageInfo(this.JTe, 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            String str = jiA;
            StringBuilder zZm = C0179Pya.zZm("Unable to find package: ");
            zZm.append(this.JTe);
            Log.e(str, zZm.toString(), e);
            return cMY.zZm;
        }
    }
}
