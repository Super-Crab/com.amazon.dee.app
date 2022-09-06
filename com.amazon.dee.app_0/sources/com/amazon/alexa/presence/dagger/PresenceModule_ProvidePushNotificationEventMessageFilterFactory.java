package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.eventbus.EventMessageFilter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidePushNotificationEventMessageFilterFactory implements Factory<EventMessageFilter> {
    private final PresenceModule module;

    public PresenceModule_ProvidePushNotificationEventMessageFilterFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_ProvidePushNotificationEventMessageFilterFactory create(PresenceModule presenceModule) {
        return new PresenceModule_ProvidePushNotificationEventMessageFilterFactory(presenceModule);
    }

    public static EventMessageFilter provideInstance(PresenceModule presenceModule) {
        return proxyProvidePushNotificationEventMessageFilter(presenceModule);
    }

    public static EventMessageFilter proxyProvidePushNotificationEventMessageFilter(PresenceModule presenceModule) {
        return (EventMessageFilter) Preconditions.checkNotNull(presenceModule.providePushNotificationEventMessageFilter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventMessageFilter mo10268get() {
        return provideInstance(this.module);
    }
}
