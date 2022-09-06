package com.amazon.alexa;

import android.support.v4.media.session.MediaSessionCompat;
/* compiled from: MediaSessionWrapper.java */
/* loaded from: classes.dex */
public class ZMt implements Runnable {
    public final /* synthetic */ OIb BIo;
    public final /* synthetic */ MediaSessionCompat.Callback zZm;

    public ZMt(OIb oIb, MediaSessionCompat.Callback callback) {
        this.BIo = oIb;
        this.zZm = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaSessionCompat mediaSessionCompat;
        MediaSessionCompat mediaSessionCompat2;
        if (this.BIo.Qle()) {
            StringBuilder zZm = C0179Pya.zZm("setcallback on: ");
            mediaSessionCompat = this.BIo.zyO;
            zZm.append(mediaSessionCompat);
            zZm.append(" callback: ");
            zZm.append(this.zZm);
            zZm.toString();
            mediaSessionCompat2 = this.BIo.zyO;
            mediaSessionCompat2.setCallback(this.zZm);
        }
    }
}
