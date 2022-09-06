package com.amazon.alexa.handsfree.notification.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.handsfree.notification.services.QuickSettingsNotifierService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
/* loaded from: classes8.dex */
public class QuickSettingsReceiver extends BroadcastReceiver {
    private final Initializer mInitializer = InitializerProvider.getInitializer();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.mInitializer.initialize(context);
        QuickSettingsNotifierService.enqueueWork(context, intent);
    }
}
