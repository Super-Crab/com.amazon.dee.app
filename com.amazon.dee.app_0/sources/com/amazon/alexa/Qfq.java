package com.amazon.alexa;
/* compiled from: RequestComposer.java */
/* loaded from: classes.dex */
public class Qfq implements Runnable {
    public final /* synthetic */ aew BIo;
    public final /* synthetic */ rjK zZm;

    public Qfq(aew aewVar, rjK rjk) {
        this.BIo = aewVar;
        this.zZm = rjk;
    }

    @Override // java.lang.Runnable
    public void run() {
        aew.zZm(this.BIo, this.zZm);
    }
}
