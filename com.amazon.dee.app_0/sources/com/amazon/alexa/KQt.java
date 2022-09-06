package com.amazon.alexa;

import android.util.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* compiled from: Sound.java */
/* loaded from: classes.dex */
public class KQt {
    public static final String zZm = "KQt";
    public final gOC BIo;
    public boolean Qle;
    public boolean jiA;
    public final int zQM;
    public final CountDownLatch zyO = new CountDownLatch(1);

    public KQt(gOC goc, int i) {
        this.BIo = goc;
        this.zQM = i;
    }

    public boolean zZm() {
        try {
            this.zyO.await(2L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Loading sound interrupted: ");
            zZm2.append(this.zQM);
            Log.e(str, zZm2.toString());
        }
        return this.jiA;
    }
}
