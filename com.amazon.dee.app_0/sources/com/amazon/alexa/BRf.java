package com.amazon.alexa;

import android.os.SystemClock;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
/* compiled from: MediaBrowserPlayer.java */
/* loaded from: classes.dex */
public class BRf implements Runnable {
    public final /* synthetic */ MediaControllerCompat.TransportControls BIo;
    public final /* synthetic */ JYe zQM;
    public final /* synthetic */ STS zZm;

    public BRf(JYe jYe, STS sts, MediaControllerCompat.TransportControls transportControls) {
        this.zQM = jYe;
        this.zZm = sts;
        this.BIo = transportControls;
    }

    @Override // java.lang.Runnable
    public void run() {
        PlaybackStateCompat playbackState = this.zQM.zyO.zyO().getPlaybackState();
        if (playbackState != null) {
            this.BIo.seekTo(Math.max((playbackState.getPlaybackSpeed() * ((float) (SystemClock.elapsedRealtime() - playbackState.getLastPositionUpdateTime()))) + ((float) playbackState.getPosition()) + ((float) ((jmO) this.zZm).zZm), 0.0f));
        }
    }
}
