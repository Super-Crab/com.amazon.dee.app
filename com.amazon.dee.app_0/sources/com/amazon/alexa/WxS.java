package com.amazon.alexa;

import android.support.v4.media.session.MediaControllerCompat;
/* compiled from: MediaBrowserPlayer.java */
/* loaded from: classes.dex */
public class WxS implements Runnable {
    public final /* synthetic */ MediaControllerCompat.TransportControls zZm;

    public WxS(JYe jYe, MediaControllerCompat.TransportControls transportControls) {
        this.zZm = transportControls;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.zZm.skipToPrevious();
    }
}
