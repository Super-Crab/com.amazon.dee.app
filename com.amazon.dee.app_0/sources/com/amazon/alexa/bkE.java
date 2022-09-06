package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class bkE implements VIX.zZm<Void> {
    public final /* synthetic */ VIX BIo;
    public final /* synthetic */ IkF zZm;

    public bkE(VIX vix, IkF ikF) {
        this.BIo = vix;
        this.zZm = ikF;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Void zZm(SimpleExoPlayer simpleExoPlayer) {
        simpleExoPlayer.setAudioAttributes(this.zZm.zZm(), false);
        if (this.BIo.JXl != null) {
            VIX vix = this.BIo;
            vix.zZm(simpleExoPlayer, vix.JXl.zZm(), this.BIo.JXl.zQM);
            return null;
        }
        return null;
    }
}
