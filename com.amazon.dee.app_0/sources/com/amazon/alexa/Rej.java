package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class Rej implements VIX.zZm<Boolean> {
    public final /* synthetic */ VIX zZm;

    public Rej(VIX vix) {
        this.zZm = vix;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Boolean zZm(SimpleExoPlayer simpleExoPlayer) {
        try {
            Timeline.Period period = simpleExoPlayer.getCurrentTimeline().getPeriod(simpleExoPlayer.getCurrentPeriodIndex(), new Timeline.Period());
            boolean z = true;
            if (simpleExoPlayer.getCurrentTimeline().getPeriodCount() != 1 || period.getDurationMs() != C.TIME_UNSET) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (IndexOutOfBoundsException unused) {
            this.zZm.zZm("Failed to determine if current stream is live");
            return false;
        }
    }
}
