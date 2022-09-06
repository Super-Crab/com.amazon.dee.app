package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class nvu implements VIX.zZm<Long> {
    public nvu(VIX vix) {
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Long zZm(SimpleExoPlayer simpleExoPlayer) {
        return Long.valueOf(simpleExoPlayer.getDuration());
    }
}
