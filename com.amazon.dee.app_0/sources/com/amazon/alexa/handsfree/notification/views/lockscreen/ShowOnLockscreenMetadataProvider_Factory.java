package com.amazon.alexa.handsfree.notification.views.lockscreen;

import com.amazon.alexa.handsfree.notification.views.NotificationStateProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class ShowOnLockscreenMetadataProvider_Factory implements Factory<ShowOnLockscreenMetadataProvider> {
    private final Provider<NotificationStateProvider> notificationStateProvider;

    public ShowOnLockscreenMetadataProvider_Factory(Provider<NotificationStateProvider> provider) {
        this.notificationStateProvider = provider;
    }

    public static ShowOnLockscreenMetadataProvider_Factory create(Provider<NotificationStateProvider> provider) {
        return new ShowOnLockscreenMetadataProvider_Factory(provider);
    }

    public static ShowOnLockscreenMetadataProvider newShowOnLockscreenMetadataProvider(NotificationStateProvider notificationStateProvider) {
        return new ShowOnLockscreenMetadataProvider(notificationStateProvider);
    }

    public static ShowOnLockscreenMetadataProvider provideInstance(Provider<NotificationStateProvider> provider) {
        return new ShowOnLockscreenMetadataProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ShowOnLockscreenMetadataProvider mo10268get() {
        return provideInstance(this.notificationStateProvider);
    }
}
