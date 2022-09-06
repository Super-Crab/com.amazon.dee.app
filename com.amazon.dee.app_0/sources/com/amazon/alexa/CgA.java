package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class CgA implements VIX.zZm<Void> {
    public final /* synthetic */ long zZm;

    public CgA(VIX vix, long j) {
        this.zZm = j;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Void zZm(SimpleExoPlayer simpleExoPlayer) {
        simpleExoPlayer.seekTo(this.zZm);
        return null;
    }
}
