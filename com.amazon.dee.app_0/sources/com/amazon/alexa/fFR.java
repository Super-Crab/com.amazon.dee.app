package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class fFR implements VIX.zZm<Long> {
    public final /* synthetic */ VIX zZm;

    public fFR(VIX vix) {
        this.zZm = vix;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Long zZm(SimpleExoPlayer simpleExoPlayer) {
        long j;
        if (this.zZm.LPk()) {
            long jiA = this.zZm.jiA();
            j = this.zZm.MNR;
            long j2 = jiA - j;
            if (j2 <= 0) {
                return Long.valueOf(this.zZm.jiA());
            }
            return Long.valueOf(j2);
        }
        return Long.valueOf(simpleExoPlayer.getCurrentPosition());
    }
}
