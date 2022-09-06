package com.amazon.deecomms.core;

import com.amazon.deecomms.notifications.util.OfflinePushNotificationRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesOfflinePushNotificationRepositoryFactory implements Factory<OfflinePushNotificationRepository> {
    private final LibraryModule module;

    public LibraryModule_ProvidesOfflinePushNotificationRepositoryFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesOfflinePushNotificationRepositoryFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesOfflinePushNotificationRepositoryFactory(libraryModule);
    }

    public static OfflinePushNotificationRepository provideInstance(LibraryModule libraryModule) {
        return (OfflinePushNotificationRepository) Preconditions.checkNotNull(libraryModule.providesOfflinePushNotificationRepository(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static OfflinePushNotificationRepository proxyProvidesOfflinePushNotificationRepository(LibraryModule libraryModule) {
        return (OfflinePushNotificationRepository) Preconditions.checkNotNull(libraryModule.providesOfflinePushNotificationRepository(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OfflinePushNotificationRepository mo10268get() {
        return provideInstance(this.module);
    }
}
