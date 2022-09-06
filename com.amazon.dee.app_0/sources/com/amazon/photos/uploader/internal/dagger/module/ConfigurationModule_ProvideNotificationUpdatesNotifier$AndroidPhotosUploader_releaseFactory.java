package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseFactory implements Factory<NotificationUpdatesNotifier> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static NotificationUpdatesNotifier provideNotificationUpdatesNotifier$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (NotificationUpdatesNotifier) Preconditions.checkNotNull(configurationModule.provideNotificationUpdatesNotifier$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationUpdatesNotifier mo10268get() {
        return provideNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.module);
    }
}
