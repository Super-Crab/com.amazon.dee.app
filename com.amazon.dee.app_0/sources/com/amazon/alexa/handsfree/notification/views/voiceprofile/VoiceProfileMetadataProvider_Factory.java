package com.amazon.alexa.handsfree.notification.views.voiceprofile;

import com.amazon.alexa.handsfree.notification.views.NotificationStateProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceProfileMetadataProvider_Factory implements Factory<VoiceProfileMetadataProvider> {
    private final Provider<NotificationStateProvider> notificationStateProvider;

    public VoiceProfileMetadataProvider_Factory(Provider<NotificationStateProvider> provider) {
        this.notificationStateProvider = provider;
    }

    public static VoiceProfileMetadataProvider_Factory create(Provider<NotificationStateProvider> provider) {
        return new VoiceProfileMetadataProvider_Factory(provider);
    }

    public static VoiceProfileMetadataProvider newVoiceProfileMetadataProvider(NotificationStateProvider notificationStateProvider) {
        return new VoiceProfileMetadataProvider(notificationStateProvider);
    }

    public static VoiceProfileMetadataProvider provideInstance(Provider<NotificationStateProvider> provider) {
        return new VoiceProfileMetadataProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceProfileMetadataProvider mo10268get() {
        return provideInstance(this.notificationStateProvider);
    }
}
