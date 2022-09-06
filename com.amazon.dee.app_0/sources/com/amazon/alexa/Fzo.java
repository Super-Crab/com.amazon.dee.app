package com.amazon.alexa;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class Fzo implements Runnable {
    public final /* synthetic */ VIX BIo;
    public final /* synthetic */ long zZm;

    public Fzo(VIX vix, long j) {
        this.BIo = vix;
        this.zZm = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        long zyO;
        zyO = this.BIo.zyO();
        VIX vix = this.BIo;
        vix.zZm("Current position: " + zyO);
        VIX.zZm(this.BIo, zyO, this.zZm);
    }
}
