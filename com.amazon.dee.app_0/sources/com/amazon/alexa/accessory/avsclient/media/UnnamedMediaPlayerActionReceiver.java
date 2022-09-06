package com.amazon.alexa.accessory.avsclient.media;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.protocol.Media;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
/* loaded from: classes.dex */
public class UnnamedMediaPlayerActionReceiver extends BroadcastReceiver {
    private static final String TAG = UnnamedMediaPlayerActionReceiver.class.getSimpleName();
    @VisibleForTesting
    static final String UNKNOWN_MEDIA_PLAYER_MEDIA_NEXT_ACTION = "com.amazon.alexa.intent.action.UNKNOWN_MEDIA_PLAYER_MEDIA_NEXT_ACTION";
    @VisibleForTesting
    static final String UNKNOWN_MEDIA_PLAYER_MEDIA_PAUSE_ACTION = "com.amazon.alexa.intent.action.UNKNOWN_MEDIA_PLAYER_MEDIA_PAUSE_ACTION";
    @VisibleForTesting
    static final String UNKNOWN_MEDIA_PLAYER_MEDIA_PLAY_ACTION = "com.amazon.alexa.intent.action.UNKNOWN_MEDIA_PLAYER_MEDIA_PLAY_ACTION";
    @VisibleForTesting
    static final String UNKNOWN_MEDIA_PLAYER_MEDIA_PREVIOUS_ACTION = "com.amazon.alexa.intent.action.UNKNOWN_MEDIA_PLAYER_MEDIA_PREVIOUS_ACTION";
    private MediaControlActionHandler handler;

    private void ensureInitialization() {
        if (this.handler == null) {
            this.handler = new MediaControlActionHandler();
        }
    }

    @SuppressLint({"CheckResult"})
    private void sendMediaControl(final List<AccessorySession> list, final Media.MediaControl mediaControl) {
        this.handler.sendMediaControl(list, mediaControl).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.media.-$$Lambda$UnnamedMediaPlayerActionReceiver$So2vwbxgVYVT0Bay18rNAFvO_WA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Logger.d("%s: Successfully negotiated MediaControl %s to session %s", UnnamedMediaPlayerActionReceiver.TAG, Media.MediaControl.this, ((AccessorySession) obj).getAddress());
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.media.-$$Lambda$UnnamedMediaPlayerActionReceiver$L28S1x1xnNacFNH-MJ_qh5qFIE4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                Logger.e("%s: Unable to negotiate MediateControl %s, session count: %d", UnnamedMediaPlayerActionReceiver.TAG, Media.MediaControl.this, Integer.valueOf(list.size()));
            }
        });
    }

    @VisibleForTesting
    List<AccessorySession> getActiveSessions() {
        return Accessories.getSharedInstance().getActiveSessions();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ensureInitialization();
        List<AccessorySession> activeSessions = getActiveSessions();
        String action = intent.getAction();
        if (action != null) {
            char c = 65535;
            switch (action.hashCode()) {
                case -1211440762:
                    if (action.equals(UNKNOWN_MEDIA_PLAYER_MEDIA_PLAY_ACTION)) {
                        c = 0;
                        break;
                    }
                    break;
                case 430434682:
                    if (action.equals(UNKNOWN_MEDIA_PLAYER_MEDIA_PAUSE_ACTION)) {
                        c = 1;
                        break;
                    }
                    break;
                case 599363043:
                    if (action.equals(UNKNOWN_MEDIA_PLAYER_MEDIA_PREVIOUS_ACTION)) {
                        c = 2;
                        break;
                    }
                    break;
                case 717192423:
                    if (action.equals(UNKNOWN_MEDIA_PLAYER_MEDIA_NEXT_ACTION)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                sendMediaControl(activeSessions, Media.MediaControl.PLAY);
            } else if (c == 1) {
                sendMediaControl(activeSessions, Media.MediaControl.PAUSE);
            } else if (c == 2) {
                sendMediaControl(activeSessions, Media.MediaControl.PREVIOUS);
            } else if (c != 3) {
                Logger.e("Received an unsupported unnamed media player action: %s", intent.getAction());
            } else {
                sendMediaControl(activeSessions, Media.MediaControl.NEXT);
            }
        }
    }

    @VisibleForTesting
    void set(MediaControlActionHandler mediaControlActionHandler) {
        this.handler = mediaControlActionHandler;
    }
}
