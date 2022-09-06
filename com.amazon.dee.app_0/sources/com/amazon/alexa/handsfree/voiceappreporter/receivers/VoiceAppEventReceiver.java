package com.amazon.alexa.handsfree.voiceappreporter.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.voiceappreporter.services.VoiceAppEventReporterService;
/* loaded from: classes8.dex */
public class VoiceAppEventReceiver extends BroadcastReceiver {
    private final Initializer mInitializer;

    public VoiceAppEventReceiver() {
        this.mInitializer = InitializerProvider.getInitializer();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        this.mInitializer.initialize(context);
        VoiceAppEventReporterService.enqueueWork(context, intent);
    }

    @VisibleForTesting
    VoiceAppEventReceiver(@NonNull Initializer initializer) {
        this.mInitializer = initializer;
    }
}
