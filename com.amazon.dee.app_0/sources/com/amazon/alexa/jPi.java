package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class jPi implements VIX.zZm<Void> {
    public final /* synthetic */ VIX zZm;

    public jPi(VIX vix) {
        this.zZm = vix;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Void zZm(SimpleExoPlayer simpleExoPlayer) {
        if (this.zZm.JXl != null) {
            if (this.zZm.wUw) {
                VIX.zQM zqm = this.zZm.JXl;
                if (zqm.JTe && zqm.zyO) {
                    zqm.zZm(new UOx(zqm));
                }
            } else if (!this.zZm.JXl.Qle) {
                VIX.zQM zqm2 = this.zZm.JXl;
                if (!zqm2.Qle) {
                    zqm2.Qle = true;
                    zqm2.zZm(new unf(zqm2));
                }
            }
            this.zZm.wUw = false;
            simpleExoPlayer.setPlayWhenReady(true);
            return null;
        }
        this.zZm.zZm("Nothing to play, ignoring play command");
        return null;
    }
}
