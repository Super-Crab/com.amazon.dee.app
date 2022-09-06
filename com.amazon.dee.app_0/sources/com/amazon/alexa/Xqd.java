package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class Xqd implements VIX.zZm<Long> {
    public final /* synthetic */ VIX zZm;

    public Xqd(VIX vix) {
        this.zZm = vix;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Long zZm(SimpleExoPlayer simpleExoPlayer) {
        Timeline.Period period = new Timeline.Period();
        long currentPosition = simpleExoPlayer.getCurrentPosition();
        Timeline currentTimeline = simpleExoPlayer.getCurrentTimeline();
        try {
            if (!currentTimeline.isEmpty()) {
                currentPosition -= currentTimeline.getPeriod(simpleExoPlayer.getCurrentPeriodIndex(), period).getPositionInWindowMs();
            }
        } catch (IndexOutOfBoundsException unused) {
            this.zZm.zZm("Failed to calculate live stream position");
        }
        return Long.valueOf(currentPosition);
    }
}
