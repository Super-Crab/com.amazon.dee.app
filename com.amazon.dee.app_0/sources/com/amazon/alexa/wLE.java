package com.amazon.alexa;
/* compiled from: RequestComposer.java */
/* loaded from: classes.dex */
public class wLE implements Runnable {
    public final /* synthetic */ JjI BIo;
    public final /* synthetic */ aew zQM;
    public final /* synthetic */ RrI zZm;

    public wLE(aew aewVar, RrI rrI, JjI jjI) {
        this.zQM = aewVar;
        this.zZm = rrI;
        this.BIo = jjI;
    }

    @Override // java.lang.Runnable
    public void run() {
        aew.zZm(this.zQM, this.zZm, this.BIo);
    }
}
