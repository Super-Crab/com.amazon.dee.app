package com.amazon.alexa;

import com.amazon.alexa.QYV;
/* compiled from: TextCapabilityAgent.java */
/* loaded from: classes.dex */
public class BbQ implements Runnable {
    public final /* synthetic */ Jvr BIo;
    public final /* synthetic */ QYV.jiA zZm;

    public BbQ(Jvr jvr, QYV.jiA jia) {
        this.BIo = jvr;
        this.zZm = jia;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.BIo.zZm(this.zZm);
    }
}
