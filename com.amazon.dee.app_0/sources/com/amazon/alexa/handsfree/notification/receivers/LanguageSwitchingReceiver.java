package com.amazon.alexa.handsfree.notification.receivers;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.metrics.MetricType;
import com.amazon.alexa.handsfree.notification.services.LanguageSwitchingNotifierService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class LanguageSwitchingReceiver extends NotificationsBroadcastReceiver {
    private static final String TAG = LanguageSwitchingReceiver.class.getSimpleName();
    private final Initializer mInitializer;

    public LanguageSwitchingReceiver() {
        this.mInitializer = InitializerProvider.getInitializer();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        this.mInitializer.initialize(context);
        if (!isInitialized(context, MetricType.NOTIFICATION_MODULE_NOT_INITIALIZED_LANGUAGE_SWITCHING_RECEIVER)) {
            Log.e(TAG, "onReceive: Notification Module not initialized.");
        } else {
            LanguageSwitchingNotifierService.enqueueWork(context, intent);
        }
    }

    @VisibleForTesting
    LanguageSwitchingReceiver(@NonNull Initializer initializer, @NonNull NotificationModule notificationModule) {
        super(initializer, notificationModule);
        this.mInitializer = initializer;
    }
}
