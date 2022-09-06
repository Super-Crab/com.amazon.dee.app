package com.amazon.alexa;

import android.support.v4.media.session.MediaSessionCompat;
/* compiled from: MediaSessionWrapper.java */
/* loaded from: classes.dex */
public class VJr implements Runnable {
    public final /* synthetic */ OIb BIo;
    public final /* synthetic */ boolean zZm;

    public VJr(OIb oIb, boolean z) {
        this.BIo = oIb;
        this.zZm = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaSessionCompat mediaSessionCompat;
        MediaSessionCompat mediaSessionCompat2;
        MediaSessionCompat mediaSessionCompat3;
        if (this.BIo.Qle()) {
            if (this.zZm) {
                mediaSessionCompat3 = this.BIo.zyO;
                mediaSessionCompat3.setActive(this.zZm);
                return;
            }
            mediaSessionCompat = this.BIo.zyO;
            mediaSessionCompat.setActive(false);
            mediaSessionCompat2 = this.BIo.zyO;
            mediaSessionCompat2.release();
            this.BIo.zyO = null;
        }
    }
}
