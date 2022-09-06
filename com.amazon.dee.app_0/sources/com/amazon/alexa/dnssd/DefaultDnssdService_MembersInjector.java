package com.amazon.alexa.dnssd;

import com.amazon.alexa.dnssd.subscribers.StartDiscoverySubscriber;
import com.amazon.alexa.eventbus.api.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DefaultDnssdService_MembersInjector implements MembersInjector<DefaultDnssdService> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<StartDiscoverySubscriber> startDiscoverySubscriberProvider;

    public DefaultDnssdService_MembersInjector(Provider<EventBus> provider, Provider<StartDiscoverySubscriber> provider2) {
        this.eventBusProvider = provider;
        this.startDiscoverySubscriberProvider = provider2;
    }

    public static MembersInjector<DefaultDnssdService> create(Provider<EventBus> provider, Provider<StartDiscoverySubscriber> provider2) {
        return new DefaultDnssdService_MembersInjector(provider, provider2);
    }

    public static void injectEventBus(DefaultDnssdService defaultDnssdService, EventBus eventBus) {
        defaultDnssdService.eventBus = eventBus;
    }

    public static void injectStartDiscoverySubscriber(DefaultDnssdService defaultDnssdService, StartDiscoverySubscriber startDiscoverySubscriber) {
        defaultDnssdService.startDiscoverySubscriber = startDiscoverySubscriber;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DefaultDnssdService defaultDnssdService) {
        injectEventBus(defaultDnssdService, this.eventBusProvider.mo10268get());
        injectStartDiscoverySubscriber(defaultDnssdService, this.startDiscoverySubscriberProvider.mo10268get());
    }
}
