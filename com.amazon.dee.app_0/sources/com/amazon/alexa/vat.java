package com.amazon.alexa;

import com.amazon.alexa.mode.metrics.DriveModeMetrics;
/* compiled from: PlayerEvent.java */
/* loaded from: classes.dex */
public enum vat {
    PLAY_MODE_CHANGED("PlayModeChanged"),
    TRACK_CHANGED("TrackChanged"),
    PLAYBACK_SESSION_STARTED("PlaybackSessionStarted"),
    PLAYBACK_SESSION_ENDED("PlaybackSessionEnded"),
    PLAYBACK_STARTED("PlaybackStarted"),
    PLAYBACK_STOPPED("PlaybackStopped"),
    PLAYBACK_PREVIOUS("PlaybackPrevious"),
    PLAYBACK_NEXT("PlaybackNext"),
    DISCONNECTED(DriveModeMetrics.ConnectionStatus.DISCONNECTED);
    
    public final String eventName;

    vat(String str) {
        this.eventName = str;
    }

    public boolean BIo() {
        return !this.eventName.equals(PLAYBACK_SESSION_ENDED.zZm()) && !this.eventName.equals(PLAYBACK_STOPPED.zZm());
    }

    public String zZm() {
        return this.eventName;
    }
}
