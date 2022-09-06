package com.brentvatne.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
/* loaded from: classes13.dex */
public class AudioBecomingNoisyReceiver extends BroadcastReceiver {
    private final Context context;
    private BecomingNoisyListener listener = BecomingNoisyListener.NO_OP;

    public AudioBecomingNoisyReceiver(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
            this.listener.onAudioBecomingNoisy();
        }
    }

    public void removeListener() {
        this.listener = BecomingNoisyListener.NO_OP;
        try {
            this.context.unregisterReceiver(this);
        } catch (Exception unused) {
        }
    }

    public void setListener(BecomingNoisyListener becomingNoisyListener) {
        this.listener = becomingNoisyListener;
        this.context.registerReceiver(this, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
    }
}
