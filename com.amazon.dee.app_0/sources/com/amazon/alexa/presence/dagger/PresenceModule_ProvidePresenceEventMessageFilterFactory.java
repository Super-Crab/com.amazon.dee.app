package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.eventbus.EventMessageFilter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidePresenceEventMessageFilterFactory implements Factory<EventMessageFilter> {
    private final PresenceModule module;

    public PresenceModule_ProvidePresenceEventMessageFilterFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_ProvidePresenceEventMessageFilterFactory create(PresenceModule presenceModule) {
        return new PresenceModule_ProvidePresenceEventMessageFilterFactory(presenceModule);
    }

    public static EventMessageFilter provideInstance(PresenceModule presenceModule) {
        return proxyProvidePresenceEventMessageFilter(presenceModule);
    }

    public static EventMessageFilter proxyProvidePresenceEventMessageFilter(PresenceModule presenceModule) {
        return (EventMessageFilter) Preconditions.checkNotNull(presenceModule.providePresenceEventMessageFilter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventMessageFilter mo10268get() {
        return provideInstance(this.module);
    }
}
