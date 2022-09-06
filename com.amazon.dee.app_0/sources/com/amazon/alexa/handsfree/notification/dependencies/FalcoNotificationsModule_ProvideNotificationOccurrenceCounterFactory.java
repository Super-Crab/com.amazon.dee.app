package com.amazon.alexa.handsfree.notification.dependencies;

import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class FalcoNotificationsModule_ProvideNotificationOccurrenceCounterFactory implements Factory<NotificationOccurrenceCounter> {
    private final FalcoNotificationsModule module;

    public FalcoNotificationsModule_ProvideNotificationOccurrenceCounterFactory(FalcoNotificationsModule falcoNotificationsModule) {
        this.module = falcoNotificationsModule;
    }

    public static FalcoNotificationsModule_ProvideNotificationOccurrenceCounterFactory create(FalcoNotificationsModule falcoNotificationsModule) {
        return new FalcoNotificationsModule_ProvideNotificationOccurrenceCounterFactory(falcoNotificationsModule);
    }

    public static NotificationOccurrenceCounter provideInstance(FalcoNotificationsModule falcoNotificationsModule) {
        return proxyProvideNotificationOccurrenceCounter(falcoNotificationsModule);
    }

    public static NotificationOccurrenceCounter proxyProvideNotificationOccurrenceCounter(FalcoNotificationsModule falcoNotificationsModule) {
        return (NotificationOccurrenceCounter) Preconditions.checkNotNull(falcoNotificationsModule.provideNotificationOccurrenceCounter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationOccurrenceCounter mo10268get() {
        return provideInstance(this.module);
    }
}
