package com.amazon.alexa;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;
/* compiled from: PlaybackEventInterpreter.java */
/* loaded from: classes.dex */
public class WHc {
    public static final String zZm = "WHc";
    public final vkx BIo;
    public boolean zQM = false;

    public WHc(vkx vkxVar) {
        this.BIo = vkxVar;
    }

    public Set<vat> zZm(PlaybackStateCompat playbackStateCompat, PlaybackStateCompat playbackStateCompat2) {
        HashSet hashSet = new HashSet();
        String str = zZm;
        Object[] objArr = new Object[2];
        Object obj = "[null]";
        objArr[0] = playbackStateCompat != null ? Integer.valueOf(playbackStateCompat.getState()) : obj;
        if (playbackStateCompat2 != null) {
            obj = Integer.valueOf(playbackStateCompat2.getState());
        }
        objArr[1] = obj;
        Log.i(str, String.format("currentState: %s, newState: %s.", objArr));
        if (playbackStateCompat2 != null && playbackStateCompat2.getState() != 7) {
            if (playbackStateCompat != null && playbackStateCompat.getState() == playbackStateCompat2.getState()) {
                Log.i(zZm, String.format("eventToSend: %s.", hashSet));
                return hashSet;
            } else if (!this.zQM && playbackStateCompat2.getState() == 3) {
                hashSet.add(vat.PLAYBACK_STARTED);
                this.zQM = true;
                Log.i(zZm, String.format("eventToSend: %s.", hashSet));
                return hashSet;
            } else if (this.zQM && playbackStateCompat2.getState() == 2 && this.BIo.zyO() != wSq.LISTENING) {
                hashSet.add(vat.PLAYBACK_STOPPED);
                this.zQM = false;
                Log.i(zZm, String.format("eventToSend: %s.", hashSet));
                return hashSet;
            } else {
                if (this.zQM) {
                    int state = playbackStateCompat.getState();
                    if (state == 4 || state == 5) {
                        hashSet.add(vat.PLAY_MODE_CHANGED);
                    } else if (state == 9) {
                        hashSet.add(vat.PLAYBACK_PREVIOUS);
                    } else if (state == 10) {
                        hashSet.add(vat.PLAYBACK_NEXT);
                    }
                }
                Log.i(zZm, String.format("eventToSend: %s.", hashSet));
                return hashSet;
            }
        }
        Log.i(zZm, String.format("eventToSend: %s.", hashSet));
        return hashSet;
    }
}
