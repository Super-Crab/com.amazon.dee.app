package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class Xft implements VIX.zZm<Void> {
    public final /* synthetic */ VIX zZm;

    public Xft(VIX vix) {
        this.zZm = vix;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Void zZm(SimpleExoPlayer simpleExoPlayer) {
        Player.EventListener eventListener;
        AnalyticsListener analyticsListener;
        this.zZm.zZm("Adding default listeners");
        eventListener = this.zZm.yPL;
        simpleExoPlayer.addListener(eventListener);
        analyticsListener = this.zZm.Mlj;
        simpleExoPlayer.addAnalyticsListener(analyticsListener);
        return null;
    }
}
