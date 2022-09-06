package com.amazon.alexa.handsfree.notification.dependencies;

import com.amazon.alexa.handsfree.notification.NotificationModule;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class FalcoNotificationsModule_ProvideNotificationModuleFactory implements Factory<NotificationModule> {
    private final FalcoNotificationsModule module;

    public FalcoNotificationsModule_ProvideNotificationModuleFactory(FalcoNotificationsModule falcoNotificationsModule) {
        this.module = falcoNotificationsModule;
    }

    public static FalcoNotificationsModule_ProvideNotificationModuleFactory create(FalcoNotificationsModule falcoNotificationsModule) {
        return new FalcoNotificationsModule_ProvideNotificationModuleFactory(falcoNotificationsModule);
    }

    public static NotificationModule provideInstance(FalcoNotificationsModule falcoNotificationsModule) {
        return proxyProvideNotificationModule(falcoNotificationsModule);
    }

    public static NotificationModule proxyProvideNotificationModule(FalcoNotificationsModule falcoNotificationsModule) {
        return (NotificationModule) Preconditions.checkNotNull(falcoNotificationsModule.provideNotificationModule(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationModule mo10268get() {
        return provideInstance(this.module);
    }
}
