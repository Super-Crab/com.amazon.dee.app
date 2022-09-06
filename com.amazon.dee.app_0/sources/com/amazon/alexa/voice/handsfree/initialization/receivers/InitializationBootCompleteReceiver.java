package com.amazon.alexa.voice.handsfree.initialization.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
/* loaded from: classes11.dex */
public class InitializationBootCompleteReceiver extends BroadcastReceiver {
    private final Initializer mInitializer;

    public InitializationBootCompleteReceiver() {
        this(InitializerProvider.getInitializer());
    }

    @VisibleForTesting
    DeviceInformation getDeviceInformation(@NonNull Context context) {
        return DeviceTypeInformationProvider.getInstance(context).getSupportedDeviceInformation(context);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (!"android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) || getDeviceInformation(context) == null) {
            return;
        }
        this.mInitializer.initialize(context);
    }

    @VisibleForTesting
    InitializationBootCompleteReceiver(@NonNull Initializer initializer) {
        this.mInitializer = initializer;
    }
}
