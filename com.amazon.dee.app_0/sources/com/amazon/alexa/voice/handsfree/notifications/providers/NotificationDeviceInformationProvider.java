package com.amazon.alexa.voice.handsfree.notifications.providers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.api.DeviceInformationProvider;
import com.amazon.alexa.handsfree.vendor.bridge.VoiceAppPackageInfoProvider;
/* loaded from: classes11.dex */
public class NotificationDeviceInformationProvider implements DeviceInformationProvider {
    private final VoiceAppPackageInfoProvider mVoiceAppPackageInfoProvider;

    public NotificationDeviceInformationProvider(@NonNull Context context) {
        this(new VoiceAppPackageInfoProvider(context));
    }

    @Override // com.amazon.alexa.handsfree.notification.api.DeviceInformationProvider
    public boolean isTrueTurnkey() {
        return this.mVoiceAppPackageInfoProvider.getSupportedVoiceApp() != null;
    }

    @VisibleForTesting
    NotificationDeviceInformationProvider(@NonNull VoiceAppPackageInfoProvider voiceAppPackageInfoProvider) {
        this.mVoiceAppPackageInfoProvider = voiceAppPackageInfoProvider;
    }
}
