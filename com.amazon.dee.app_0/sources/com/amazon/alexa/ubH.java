package com.amazon.alexa;

import android.os.ConditionVariable;
import android.support.v4.media.session.MediaSessionCompat;
/* compiled from: MediaSessionWrapper.java */
/* loaded from: classes.dex */
public class ubH implements Runnable {
    public final /* synthetic */ OIb BIo;
    public final /* synthetic */ ConditionVariable zZm;

    public ubH(OIb oIb, ConditionVariable conditionVariable) {
        this.BIo = oIb;
        this.zZm = conditionVariable;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaSessionCompat mediaSessionCompat;
        MediaSessionCompat mediaSessionCompat2;
        MediaSessionCompat mediaSessionCompat3;
        StringBuilder zZm = C0179Pya.zZm("releasing: ");
        mediaSessionCompat = this.BIo.zyO;
        zZm.append(mediaSessionCompat);
        zZm.toString();
        if (this.BIo.Qle()) {
            mediaSessionCompat2 = this.BIo.zyO;
            mediaSessionCompat2.setActive(false);
            mediaSessionCompat3 = this.BIo.zyO;
            mediaSessionCompat3.release();
            this.BIo.zyO = null;
        }
        this.zZm.open();
    }
}
