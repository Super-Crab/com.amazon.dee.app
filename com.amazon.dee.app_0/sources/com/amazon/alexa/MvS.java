package com.amazon.alexa;

import android.content.Context;
import android.os.ConditionVariable;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class MvS implements Runnable {
    public final /* synthetic */ VIX BIo;
    public final /* synthetic */ ConditionVariable zZm;

    public MvS(VIX vix, ConditionVariable conditionVariable) {
        this.BIo = vix;
        this.zZm = conditionVariable;
    }

    @Override // java.lang.Runnable
    public void run() {
        uXm uxm;
        Context context;
        DefaultTrackSelector defaultTrackSelector;
        DefaultLoadControl createDefaultLoadControl = new DefaultLoadControl.Builder().setBufferDurationsMs(50000, VIX.zyO, 2500, 5000).createDefaultLoadControl();
        uxm = this.BIo.HvC;
        ((Bdr) uxm).zZm();
        VIX vix = this.BIo;
        context = vix.Qle;
        defaultTrackSelector = this.BIo.noQ;
        vix.Tbw = ExoPlayerFactory.newSimpleInstance(context, defaultTrackSelector, createDefaultLoadControl);
        this.BIo.zZm();
        this.zZm.open();
    }
}
