package com.amazon.alexa.dnssd.dependencies;

import com.amazon.alexa.dnssd.RxNsd;
import com.amazon.alexa.dnssd.subscribers.StartDiscoverySubscriber;
import com.amazon.alexa.dnssd.util.Metrics;
import com.amazon.alexa.dnssd.util.ObjectMapperFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.tasks.api.TaskManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvideStartDiscoverySubscriberFactory implements Factory<StartDiscoverySubscriber> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<Metrics> metricsProvider;
    private final DnssdModule module;
    private final Provider<RxNsd> nsdProvider;
    private final Provider<ObjectMapperFactory> objectMapperFactoryProvider;
    private final Provider<TaskManager> taskManagerProvider;

    public DnssdModule_ProvideStartDiscoverySubscriberFactory(DnssdModule dnssdModule, Provider<EventBus> provider, Provider<Metrics> provider2, Provider<ObjectMapperFactory> provider3, Provider<RxNsd> provider4, Provider<TaskManager> provider5) {
        this.module = dnssdModule;
        this.eventBusProvider = provider;
        this.metricsProvider = provider2;
        this.objectMapperFactoryProvider = provider3;
        this.nsdProvider = provider4;
        this.taskManagerProvider = provider5;
    }

    public static DnssdModule_ProvideStartDiscoverySubscriberFactory create(DnssdModule dnssdModule, Provider<EventBus> provider, Provider<Metrics> provider2, Provider<ObjectMapperFactory> provider3, Provider<RxNsd> provider4, Provider<TaskManager> provider5) {
        return new DnssdModule_ProvideStartDiscoverySubscriberFactory(dnssdModule, provider, provider2, provider3, provider4, provider5);
    }

    public static StartDiscoverySubscriber provideInstance(DnssdModule dnssdModule, Provider<EventBus> provider, Provider<Metrics> provider2, Provider<ObjectMapperFactory> provider3, Provider<RxNsd> provider4, Provider<TaskManager> provider5) {
        return proxyProvideStartDiscoverySubscriber(dnssdModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static StartDiscoverySubscriber proxyProvideStartDiscoverySubscriber(DnssdModule dnssdModule, EventBus eventBus, Metrics metrics, ObjectMapperFactory objectMapperFactory, RxNsd rxNsd, TaskManager taskManager) {
        return (StartDiscoverySubscriber) Preconditions.checkNotNull(dnssdModule.provideStartDiscoverySubscriber(eventBus, metrics, objectMapperFactory, rxNsd, taskManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public StartDiscoverySubscriber mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.metricsProvider, this.objectMapperFactoryProvider, this.nsdProvider, this.taskManagerProvider);
    }
}
