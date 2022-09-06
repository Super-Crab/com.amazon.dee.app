package com.amazon.alexa;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class KKC implements Runnable {
    public final /* synthetic */ VIX zZm;

    public KKC(VIX vix) {
        this.zZm = vix;
    }

    @Override // java.lang.Runnable
    public void run() {
        VIX vix = this.zZm;
        vix.zZm(Thread.currentThread().getName() + " thread starting");
    }
}
