package com.amazon.alexa;

import android.support.v4.media.session.MediaControllerCompat;
/* compiled from: MediaBrowserPlayer.java */
/* loaded from: classes.dex */
public class YbF implements Runnable {
    public final /* synthetic */ ErD BIo;
    public final /* synthetic */ MediaControllerCompat.TransportControls zZm;

    public YbF(JYe jYe, MediaControllerCompat.TransportControls transportControls, ErD erD) {
        this.zZm = transportControls;
        this.BIo = erD;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.zZm.seekTo(((rYY) this.BIo).zZm);
    }
}
