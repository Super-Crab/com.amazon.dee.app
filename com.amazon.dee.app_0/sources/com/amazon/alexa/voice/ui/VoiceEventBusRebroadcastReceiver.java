package com.amazon.alexa.voice.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Publisher;
/* loaded from: classes11.dex */
public class VoiceEventBusRebroadcastReceiver {
    private BroadcastReceiver broadcastReceiver = null;
    private final Context context;
    private final Publisher localPublisher;

    public VoiceEventBusRebroadcastReceiver(@NonNull Context context, @NonNull Publisher publisher) {
        this.context = context;
        this.localPublisher = publisher;
    }

    public void start() {
        if (this.broadcastReceiver == null) {
            this.broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.alexa.voice.ui.VoiceEventBusRebroadcastReceiver.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    VoiceEventBusRebroadcastReceiver.this.localPublisher.publish((Message) intent.getParcelableExtra("com.amazon.alexa.voice.ui.intent.extras.REBROADCAST_EVENT_MESSAGE"));
                }
            };
            this.context.registerReceiver(this.broadcastReceiver, new IntentFilter("com.amazon.alexa.voice.ui.intent.REBROADCAST_EVENT"));
        }
    }

    public void stop() {
        BroadcastReceiver broadcastReceiver = this.broadcastReceiver;
        if (broadcastReceiver != null) {
            this.context.unregisterReceiver(broadcastReceiver);
        }
    }
}
