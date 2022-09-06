package com.amazon.alexa;

import java.util.List;
/* compiled from: WakeWordAuthority.java */
/* loaded from: classes.dex */
public class dbd implements Runnable {
    public final /* synthetic */ List BIo;
    public final /* synthetic */ bjR zQM;
    public final /* synthetic */ boolean zZm;

    public dbd(bjR bjr, boolean z, List list) {
        this.zQM = bjr;
        this.zZm = z;
        this.BIo = list;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.zZm) {
            this.zQM.zZm((List<String>) this.BIo, false);
            this.zQM.Qle();
            this.zQM.jiA();
            return;
        }
        this.zQM.zZm((List<String>) this.BIo, true);
    }
}
