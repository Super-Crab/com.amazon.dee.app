package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class Ofn implements VIX.zZm<Boolean> {
    public final /* synthetic */ VIX zZm;

    public Ofn(VIX vix) {
        this.zZm = vix;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Boolean zZm(SimpleExoPlayer simpleExoPlayer) {
        boolean z = simpleExoPlayer.getPlayWhenReady() && (simpleExoPlayer.getPlaybackState() == 2 || simpleExoPlayer.getPlaybackState() == 3);
        VIX vix = this.zZm;
        vix.zZm("isPlaying? " + z);
        return Boolean.valueOf(z);
    }
}
