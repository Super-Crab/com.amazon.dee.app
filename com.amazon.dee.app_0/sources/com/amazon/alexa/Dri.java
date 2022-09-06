package com.amazon.alexa;

import com.amazon.alexa.VIX;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class Dri implements Runnable {
    public final /* synthetic */ long BIo;
    public final /* synthetic */ VIX.zQM zQM;
    public final /* synthetic */ Exception zZm;

    public Dri(VIX.zQM zqm, Exception exc, long j) {
        this.zQM = zqm;
        this.zZm = exc;
        this.BIo = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str = VIX.zZm;
        StringBuilder zZm = C0179Pya.zZm("onPlaybackFailed for item: ");
        zZm.append(this.zQM.BIo.mo947BIo());
        zZm.toString();
        Exception exc = this.zZm;
        VIX.zQM zqm = this.zQM;
        zqm.zQM.zZm(zqm.BIo, this.BIo, exc);
    }
}
