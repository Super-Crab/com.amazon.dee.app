package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class Elq implements VIX.zZm<Void> {
    public final /* synthetic */ VIX zZm;

    public Elq(VIX vix) {
        this.zZm = vix;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Void zZm(SimpleExoPlayer simpleExoPlayer) {
        this.zZm.wUw = true;
        simpleExoPlayer.setPlayWhenReady(false);
        return null;
    }
}
