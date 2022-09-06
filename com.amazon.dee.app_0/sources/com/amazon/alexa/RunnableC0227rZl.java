package com.amazon.alexa;

import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaControllerCompat;
/* compiled from: MediaBrowserPlayer.java */
/* renamed from: com.amazon.alexa.rZl  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class RunnableC0227rZl implements Runnable {
    public final /* synthetic */ MediaControllerCompat.TransportControls zZm;

    public RunnableC0227rZl(JYe jYe, MediaControllerCompat.TransportControls transportControls) {
        this.zZm = transportControls;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.zZm.setRating(RatingCompat.newThumbRating(true));
    }
}
