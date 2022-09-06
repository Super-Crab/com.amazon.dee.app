package com.amazon.alexa;

import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class ZBj implements VIX.zZm<Void> {
    public final /* synthetic */ NTV BIo;
    public final /* synthetic */ VIX zQM;
    public final /* synthetic */ kQf zZm;

    public ZBj(VIX vix, kQf kqf, NTV ntv) {
        this.zQM = vix;
        this.zZm = kqf;
        this.BIo = ntv;
    }

    @Override // com.amazon.alexa.VIX.zZm
    public Void zZm(SimpleExoPlayer simpleExoPlayer) {
        this.zQM.zZm("Executing prepare command");
        this.zQM.BIo(simpleExoPlayer, this.zZm, this.BIo);
        return null;
    }
}
