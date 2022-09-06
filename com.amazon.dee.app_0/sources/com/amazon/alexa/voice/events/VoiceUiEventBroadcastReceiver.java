package com.amazon.alexa.voice.events;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.voice.dagger.VoiceDependencies;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class VoiceUiEventBroadcastReceiver extends BroadcastReceiver {
    @VisibleForTesting
    public static final String ACTION_PUBLISH_UI_EVENT = "com.amazon.alexa.intent.action.PUBLISH_UI_EVENT";
    @VisibleForTesting
    public static final String EXTRA_EVENT_DATA = "com.amazon.alexa.intent.extra.EVENT_DATA";
    @VisibleForTesting
    public static final String EXTRA_EVENT_NAME = "com.amazon.alexa.intent.extra.EVENT_NAME";
    @Inject
    VoxUiEventProcessingService eventProcessingService;
    private volatile boolean isRegistered;

    @VisibleForTesting
    void injectDependencies() {
        VoiceDependencies.inject(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!"com.amazon.alexa.intent.action.PUBLISH_UI_EVENT".equals(intent.getAction())) {
            return;
        }
        injectDependencies();
        String stringExtra = intent.getStringExtra("com.amazon.alexa.intent.extra.EVENT_NAME");
        long j = 0;
        Bundle bundleExtra = intent.getBundleExtra("com.amazon.alexa.intent.extra.EVENT_DATA");
        if (bundleExtra != null) {
            j = bundleExtra.getLong(AlexaMetadataBundleKey.EVENT_REALTIME_MS.name());
        }
        Logger.verbose("VoiceUiEventBroadcastReceiver, event: " + stringExtra + ", timestamp: " + j);
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        this.eventProcessingService.process(VoxUiEvent.create(stringExtra, j));
    }

    public void register(Context context) {
        if (!this.isRegistered) {
            context.registerReceiver(this, new IntentFilter("com.amazon.alexa.intent.action.PUBLISH_UI_EVENT"));
            this.isRegistered = true;
        }
    }

    public void unregister(Context context) {
        if (this.isRegistered) {
            context.unregisterReceiver(this);
            this.isRegistered = false;
        }
    }
}
