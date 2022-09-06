package com.amazon.alexa;

import android.support.v4.media.session.MediaSessionCompat;
/* compiled from: MediaSessionWrapper.java */
/* loaded from: classes.dex */
public class PCi implements Runnable {
    public final /* synthetic */ OIb BIo;
    public final /* synthetic */ int zZm;

    public PCi(OIb oIb, int i) {
        this.BIo = oIb;
        this.zZm = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaSessionCompat mediaSessionCompat;
        if (this.BIo.Qle()) {
            StringBuilder zZm = C0179Pya.zZm("setFlags: ");
            zZm.append(this.zZm);
            zZm.toString();
            mediaSessionCompat = this.BIo.zyO;
            mediaSessionCompat.setFlags(this.zZm);
        }
    }
}
