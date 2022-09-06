package com.amazon.alexa;

import com.amazon.alexa.VIX;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class kAI implements Runnable {
    public final /* synthetic */ long BIo;
    public final /* synthetic */ VIX.zQM zQM;
    public final /* synthetic */ long zZm;

    public kAI(VIX.zQM zqm, long j, long j2) {
        this.zQM = zqm;
        this.zZm = j;
        this.BIo = j2;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str = VIX.zZm;
        StringBuilder zZm = C0179Pya.zZm("onPlaybackPositionUpdated at: ");
        zZm.append(this.zZm);
        zZm.append(", to: ");
        zZm.append(this.BIo);
        zZm.toString();
        VIX.zQM zqm = this.zQM;
        zqm.zQM.zZm(zqm.BIo, this.BIo, this.zZm);
    }
}
