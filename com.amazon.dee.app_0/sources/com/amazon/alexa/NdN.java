package com.amazon.alexa;

import android.support.v4.media.session.PlaybackStateCompat;
/* compiled from: MediaOperations.java */
/* loaded from: classes.dex */
public enum NdN {
    IDLE,
    PLAYING,
    PAUSED,
    STOPPED,
    FAST_FORWARDING,
    REWINDING,
    BUFFER_UNDERRUN;

    public static NdN zZm(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return IDLE;
        }
        switch (playbackStateCompat.getState()) {
            case 1:
                return STOPPED;
            case 2:
                return PAUSED;
            case 3:
                return PLAYING;
            case 4:
                return FAST_FORWARDING;
            case 5:
                return REWINDING;
            case 6:
                return BUFFER_UNDERRUN;
            default:
                return IDLE;
        }
    }
}
