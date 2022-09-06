package com.amazon.alexa;

import android.os.ConditionVariable;
import android.util.Log;
import com.amazon.alexa.VIX;
import com.google.android.exoplayer2.SimpleExoPlayer;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class CKS implements Runnable {
    public final /* synthetic */ AtomicReference BIo;
    public final /* synthetic */ VIX jiA;
    public final /* synthetic */ VIX.zZm zQM;
    public final /* synthetic */ String zZm;
    public final /* synthetic */ ConditionVariable zyO;

    public CKS(VIX vix, String str, AtomicReference atomicReference, VIX.zZm zzm, ConditionVariable conditionVariable) {
        this.jiA = vix;
        this.zZm = str;
        this.BIo = atomicReference;
        this.zQM = zzm;
        this.zyO = conditionVariable;
    }

    @Override // java.lang.Runnable
    public void run() {
        SimpleExoPlayer simpleExoPlayer = this.jiA.Tbw;
        if (simpleExoPlayer == null) {
            String str = VIX.zZm;
            StringBuilder zZm = C0179Pya.zZm("Attempting to execute command ");
            zZm.append(this.zZm);
            zZm.append(" before initializing the player.");
            Log.w(str, zZm.toString());
            return;
        }
        VIX vix = this.jiA;
        StringBuilder zZm2 = C0179Pya.zZm("Executing ");
        zZm2.append(this.zZm);
        vix.zZm(zZm2.toString());
        this.BIo.set(this.zQM.zZm(simpleExoPlayer));
        this.zyO.open();
    }
}
