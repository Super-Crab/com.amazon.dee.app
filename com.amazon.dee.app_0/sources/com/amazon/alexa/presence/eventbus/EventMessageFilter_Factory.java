package com.amazon.alexa.presence.eventbus;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class EventMessageFilter_Factory implements Factory<EventMessageFilter> {
    private final Provider<String> matchStringProvider;

    public EventMessageFilter_Factory(Provider<String> provider) {
        this.matchStringProvider = provider;
    }

    public static EventMessageFilter_Factory create(Provider<String> provider) {
        return new EventMessageFilter_Factory(provider);
    }

    public static EventMessageFilter newEventMessageFilter(String str) {
        return new EventMessageFilter(str);
    }

    public static EventMessageFilter provideInstance(Provider<String> provider) {
        return new EventMessageFilter(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventMessageFilter mo10268get() {
        return provideInstance(this.matchStringProvider);
    }
}
