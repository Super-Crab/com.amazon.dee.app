package com.amazon.alexa.handsfree.notification.views.voiceprofile;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceProfileNotification_Factory implements Factory<VoiceProfileNotification> {
    private final Provider<String> channelIdProvider;
    private final Provider<Integer> notificationIdProvider;
    private final Provider<VoiceProfileNotificationPresenter> voiceProfileNotificationPresenterProvider;

    public VoiceProfileNotification_Factory(Provider<String> provider, Provider<VoiceProfileNotificationPresenter> provider2, Provider<Integer> provider3) {
        this.channelIdProvider = provider;
        this.voiceProfileNotificationPresenterProvider = provider2;
        this.notificationIdProvider = provider3;
    }

    public static VoiceProfileNotification_Factory create(Provider<String> provider, Provider<VoiceProfileNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new VoiceProfileNotification_Factory(provider, provider2, provider3);
    }

    public static VoiceProfileNotification newVoiceProfileNotification(String str, Object obj, int i) {
        return new VoiceProfileNotification(str, (VoiceProfileNotificationPresenter) obj, i);
    }

    public static VoiceProfileNotification provideInstance(Provider<String> provider, Provider<VoiceProfileNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new VoiceProfileNotification(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get().intValue());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceProfileNotification mo10268get() {
        return provideInstance(this.channelIdProvider, this.voiceProfileNotificationPresenterProvider, this.notificationIdProvider);
    }
}
