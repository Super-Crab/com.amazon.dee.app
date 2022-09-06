package com.amazon.deecomms.core;

import com.amazon.deecomms.notifications.NotificationLatencyMetricHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesNotificationLatencyMetricHelperFactory implements Factory<NotificationLatencyMetricHelper> {
    private final LibraryModule module;

    public LibraryModule_ProvidesNotificationLatencyMetricHelperFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesNotificationLatencyMetricHelperFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesNotificationLatencyMetricHelperFactory(libraryModule);
    }

    public static NotificationLatencyMetricHelper provideInstance(LibraryModule libraryModule) {
        return (NotificationLatencyMetricHelper) Preconditions.checkNotNull(libraryModule.providesNotificationLatencyMetricHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static NotificationLatencyMetricHelper proxyProvidesNotificationLatencyMetricHelper(LibraryModule libraryModule) {
        return (NotificationLatencyMetricHelper) Preconditions.checkNotNull(libraryModule.providesNotificationLatencyMetricHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationLatencyMetricHelper mo10268get() {
        return provideInstance(this.module);
    }
}
