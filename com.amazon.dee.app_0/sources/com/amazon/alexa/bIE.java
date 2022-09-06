package com.amazon.alexa;

import android.os.ConditionVariable;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: MediaSessionWrapper.java */
/* loaded from: classes.dex */
public class bIE implements Runnable {
    public final /* synthetic */ ConditionVariable BIo;
    public final /* synthetic */ OIb zQM;
    public final /* synthetic */ AtomicReference zZm;

    public bIE(OIb oIb, AtomicReference atomicReference, ConditionVariable conditionVariable) {
        this.zQM = oIb;
        this.zZm = atomicReference;
        this.BIo = conditionVariable;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaSessionCompat mediaSessionCompat;
        if (this.zQM.Qle()) {
            AtomicReference atomicReference = this.zZm;
            mediaSessionCompat = this.zQM.zyO;
            atomicReference.set(mediaSessionCompat.getSessionToken());
            StringBuilder zZm = C0179Pya.zZm("getToken: ");
            zZm.append(this.zZm.get());
            zZm.toString();
        }
        this.BIo.open();
    }
}
