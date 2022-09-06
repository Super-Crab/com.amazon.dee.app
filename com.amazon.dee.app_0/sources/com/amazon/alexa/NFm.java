package com.amazon.alexa;
/* compiled from: DownchannelScheduler.java */
/* loaded from: classes.dex */
public class NFm implements Runnable {
    public final /* synthetic */ DVu zZm;

    public NFm(DVu dVu) {
        this.zZm = dVu;
    }

    @Override // java.lang.Runnable
    public void run() {
        RZN rzn;
        rzn = this.zZm.jiA;
        rzn.mo291call();
    }
}
