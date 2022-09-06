package com.amazon.alexa;

import android.os.ConditionVariable;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: MediaSessionWrapper.java */
/* renamed from: com.amazon.alexa.zij  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class RunnableC0244zij implements Runnable {
    public final /* synthetic */ ConditionVariable BIo;
    public final /* synthetic */ OIb zQM;
    public final /* synthetic */ AtomicBoolean zZm;

    public RunnableC0244zij(OIb oIb, AtomicBoolean atomicBoolean, ConditionVariable conditionVariable) {
        this.zQM = oIb;
        this.zZm = atomicBoolean;
        this.BIo = conditionVariable;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaSessionCompat mediaSessionCompat;
        if (this.zQM.Qle()) {
            AtomicBoolean atomicBoolean = this.zZm;
            mediaSessionCompat = this.zQM.zyO;
            atomicBoolean.set(mediaSessionCompat.isActive());
        }
        this.BIo.open();
    }
}
