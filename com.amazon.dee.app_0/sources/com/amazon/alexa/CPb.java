package com.amazon.alexa;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
/* compiled from: MediaSessionWrapper.java */
/* loaded from: classes.dex */
public class CPb implements Runnable {
    public final /* synthetic */ OIb BIo;
    public final /* synthetic */ MediaMetadataCompat zZm;

    public CPb(OIb oIb, MediaMetadataCompat mediaMetadataCompat) {
        this.BIo = oIb;
        this.zZm = mediaMetadataCompat;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaSessionCompat mediaSessionCompat;
        if (this.BIo.Qle()) {
            StringBuilder zZm = C0179Pya.zZm("setMetadata: ");
            zZm.append(this.zZm);
            zZm.toString();
            mediaSessionCompat = this.BIo.zyO;
            mediaSessionCompat.setMetadata(this.zZm);
        }
    }
}
