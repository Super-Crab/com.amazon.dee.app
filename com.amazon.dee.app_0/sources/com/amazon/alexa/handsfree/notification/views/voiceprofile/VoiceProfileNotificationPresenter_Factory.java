package com.amazon.alexa.handsfree.notification.views.voiceprofile;

import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceProfileNotificationPresenter_Factory implements Factory<VoiceProfileNotificationPresenter> {
    private final Provider<DismissIntentProvider> dismissIntentProvider;
    private final Provider<NotificationMetadataProvider> metadataProvider;
    private final Provider<NotificationType> notificationTypeProvider;

    public VoiceProfileNotificationPresenter_Factory(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<NotificationMetadataProvider> provider3) {
        this.dismissIntentProvider = provider;
        this.notificationTypeProvider = provider2;
        this.metadataProvider = provider3;
    }

    public static VoiceProfileNotificationPresenter_Factory create(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<NotificationMetadataProvider> provider3) {
        return new VoiceProfileNotificationPresenter_Factory(provider, provider2, provider3);
    }

    public static VoiceProfileNotificationPresenter newVoiceProfileNotificationPresenter(DismissIntentProvider dismissIntentProvider, NotificationType notificationType, NotificationMetadataProvider notificationMetadataProvider) {
        return new VoiceProfileNotificationPresenter(dismissIntentProvider, notificationType, notificationMetadataProvider);
    }

    public static VoiceProfileNotificationPresenter provideInstance(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<NotificationMetadataProvider> provider3) {
        return new VoiceProfileNotificationPresenter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceProfileNotificationPresenter mo10268get() {
        return provideInstance(this.dismissIntentProvider, this.notificationTypeProvider, this.metadataProvider);
    }
}
