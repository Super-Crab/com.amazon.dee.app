package com.amazon.alexa;

import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
/* compiled from: MediaSessionWrapper.java */
/* loaded from: classes.dex */
public class VIZ implements Runnable {
    public final /* synthetic */ OIb BIo;
    public final /* synthetic */ PlaybackStateCompat zZm;

    public VIZ(OIb oIb, PlaybackStateCompat playbackStateCompat) {
        this.BIo = oIb;
        this.zZm = playbackStateCompat;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaSessionCompat mediaSessionCompat;
        if (this.BIo.Qle()) {
            StringBuilder zZm = C0179Pya.zZm("setPlaybackState: ");
            zZm.append(this.zZm);
            zZm.toString();
            mediaSessionCompat = this.BIo.zyO;
            mediaSessionCompat.setPlaybackState(this.zZm);
        }
    }
}
