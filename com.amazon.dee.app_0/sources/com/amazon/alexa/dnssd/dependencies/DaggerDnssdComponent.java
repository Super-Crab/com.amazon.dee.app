package com.amazon.alexa.dnssd.dependencies;

import com.amazon.alexa.dnssd.DefaultDnssdService;
import com.amazon.alexa.dnssd.DefaultDnssdService_MembersInjector;
import com.amazon.alexa.dnssd.RxNsd;
import com.amazon.alexa.dnssd.subscribers.StartDiscoverySubscriber;
import com.amazon.alexa.dnssd.util.Metrics;
import com.amazon.alexa.dnssd.util.ObjectMapperFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DaggerDnssdComponent implements DnssdComponent {
    private DnssdModule dnssdModule;
    private DnssdModule_ProvideEventBusFactory provideEventBusProvider;
    private Provider<Metrics> provideMetricsProvider;
    private DnssdModule_ProvideMetricsServiceFactory provideMetricsServiceProvider;
    private Provider<ObjectMapperFactory> provideObjectMapperFactoryProvider;
    private Provider<RxNsd> provideRxNsdProvider;
    private Provider<StartDiscoverySubscriber> provideStartDiscoverySubscriberProvider;
    private DnssdModule_ProvideTaskManagerFactory provideTaskManagerProvider;
    private DnssdModule_ProvidesMulticastLockFactory providesMulticastLockProvider;
    private DnssdModule_ProvidesNsdManagerFactory providesNsdManagerProvider;
    private DnssdModule_ProvidesWifiManagerFactory providesWifiManagerProvider;

    /* loaded from: classes7.dex */
    public static final class Builder {
        private DnssdModule dnssdModule;

        public DnssdComponent build() {
            Preconditions.checkBuilderRequirement(this.dnssdModule, DnssdModule.class);
            return new DaggerDnssdComponent(this);
        }

        public Builder dnssdModule(DnssdModule dnssdModule) {
            this.dnssdModule = (DnssdModule) Preconditions.checkNotNull(dnssdModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideEventBusProvider = DnssdModule_ProvideEventBusFactory.create(builder.dnssdModule);
        this.provideMetricsServiceProvider = DnssdModule_ProvideMetricsServiceFactory.create(builder.dnssdModule);
        this.provideMetricsProvider = DoubleCheck.provider(DnssdModule_ProvideMetricsFactory.create(builder.dnssdModule, this.provideMetricsServiceProvider));
        this.provideObjectMapperFactoryProvider = DoubleCheck.provider(DnssdModule_ProvideObjectMapperFactoryFactory.create(builder.dnssdModule));
        this.providesNsdManagerProvider = DnssdModule_ProvidesNsdManagerFactory.create(builder.dnssdModule);
        this.providesWifiManagerProvider = DnssdModule_ProvidesWifiManagerFactory.create(builder.dnssdModule);
        this.providesMulticastLockProvider = DnssdModule_ProvidesMulticastLockFactory.create(builder.dnssdModule, this.providesWifiManagerProvider);
        this.provideRxNsdProvider = DoubleCheck.provider(DnssdModule_ProvideRxNsdFactory.create(builder.dnssdModule, this.provideMetricsProvider, this.providesNsdManagerProvider, this.providesWifiManagerProvider, this.providesMulticastLockProvider));
        this.provideTaskManagerProvider = DnssdModule_ProvideTaskManagerFactory.create(builder.dnssdModule);
        this.provideStartDiscoverySubscriberProvider = DoubleCheck.provider(DnssdModule_ProvideStartDiscoverySubscriberFactory.create(builder.dnssdModule, this.provideEventBusProvider, this.provideMetricsProvider, this.provideObjectMapperFactoryProvider, this.provideRxNsdProvider, this.provideTaskManagerProvider));
    }

    private DefaultDnssdService injectDefaultDnssdService(DefaultDnssdService defaultDnssdService) {
        DefaultDnssdService_MembersInjector.injectEventBus(defaultDnssdService, DnssdModule_ProvideEventBusFactory.proxyProvideEventBus(this.dnssdModule));
        DefaultDnssdService_MembersInjector.injectStartDiscoverySubscriber(defaultDnssdService, this.provideStartDiscoverySubscriberProvider.mo10268get());
        return defaultDnssdService;
    }

    @Override // com.amazon.alexa.dnssd.dependencies.DnssdComponent
    public void inject(DefaultDnssdService defaultDnssdService) {
        injectDefaultDnssdService(defaultDnssdService);
    }

    private DaggerDnssdComponent(Builder builder) {
        this.dnssdModule = builder.dnssdModule;
        initialize(builder);
    }
}
