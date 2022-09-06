package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
/* compiled from: AudioPlayerInteraction.java */
/* loaded from: classes.dex */
public class ach implements Runnable {
    public final /* synthetic */ nkN BIo;
    public final /* synthetic */ TtM zZm;

    public ach(nkN nkn, TtM ttM) {
        this.BIo = nkn;
        this.zZm = ttM;
    }

    @Override // java.lang.Runnable
    public void run() {
        JjI zZm;
        zZm = this.BIo.BIo.zZm(AvsApiConstants.AudioPlayer.Events.PlaybackNearlyFinished.zZm, this.zZm);
        this.BIo.BIo.zzR.zyO(zZm);
    }
}
