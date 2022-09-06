package com.amazon.alexa.dnssd.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvideEventBusFactory implements Factory<EventBus> {
    private final DnssdModule module;

    public DnssdModule_ProvideEventBusFactory(DnssdModule dnssdModule) {
        this.module = dnssdModule;
    }

    public static DnssdModule_ProvideEventBusFactory create(DnssdModule dnssdModule) {
        return new DnssdModule_ProvideEventBusFactory(dnssdModule);
    }

    public static EventBus provideInstance(DnssdModule dnssdModule) {
        return proxyProvideEventBus(dnssdModule);
    }

    public static EventBus proxyProvideEventBus(DnssdModule dnssdModule) {
        return (EventBus) Preconditions.checkNotNull(dnssdModule.provideEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
