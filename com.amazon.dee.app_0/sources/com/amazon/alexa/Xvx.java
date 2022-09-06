package com.amazon.alexa;
/* compiled from: SendMessageTaskContainer.java */
/* loaded from: classes.dex */
public class Xvx implements Runnable {
    public final /* synthetic */ Runnable BIo;
    public final /* synthetic */ lSb zQM;
    public final /* synthetic */ RrI zZm;

    public Xvx(lSb lsb, RrI rrI, Runnable runnable) {
        this.zQM = lsb;
        this.zZm = rrI;
        this.BIo = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.zQM) {
            if (this.zQM.zQM.remove(this.zZm)) {
                this.zQM.zZm(this.zZm);
            }
        }
        this.BIo.run();
    }
}
