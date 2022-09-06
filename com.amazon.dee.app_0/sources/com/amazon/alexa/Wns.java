package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class Wns implements VIX.zZm<Void> {
    public final /* synthetic */ VIX zZm;

    public Wns(VIX vix) {
        this.zZm = vix;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Void zZm(SimpleExoPlayer simpleExoPlayer) {
        if (!this.zZm.wUw) {
            this.zZm.zZm("Stopping the audio");
            simpleExoPlayer.stop();
            return null;
        }
        VIX.zyO(this.zZm);
        return null;
    }
}
