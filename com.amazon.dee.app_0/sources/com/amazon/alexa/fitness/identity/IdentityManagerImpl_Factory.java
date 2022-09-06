package com.amazon.alexa.fitness.identity;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.identity.auth.device.api.TokenManagement;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class IdentityManagerImpl_Factory implements Factory<IdentityManagerImpl> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;
    private final Provider<TokenManagement> tokenManagementProvider;

    public IdentityManagerImpl_Factory(Provider<EventBus> provider, Provider<IdentityService> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<TokenManagement> provider5, Provider<ILog> provider6) {
        this.eventBusProvider = provider;
        this.identityServiceProvider = provider2;
        this.metricEventFactoryProvider = provider3;
        this.metricEventRecorderProvider = provider4;
        this.tokenManagementProvider = provider5;
        this.logProvider = provider6;
    }

    public static IdentityManagerImpl_Factory create(Provider<EventBus> provider, Provider<IdentityService> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<TokenManagement> provider5, Provider<ILog> provider6) {
        return new IdentityManagerImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static IdentityManagerImpl newIdentityManagerImpl(EventBus eventBus, IdentityService identityService, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, TokenManagement tokenManagement, ILog iLog) {
        return new IdentityManagerImpl(eventBus, identityService, metricEventFactory, metricEventRecorder, tokenManagement, iLog);
    }

    public static IdentityManagerImpl provideInstance(Provider<EventBus> provider, Provider<IdentityService> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<TokenManagement> provider5, Provider<ILog> provider6) {
        return new IdentityManagerImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityManagerImpl mo10268get() {
        return provideInstance(this.eventBusProvider, this.identityServiceProvider, this.metricEventFactoryProvider, this.metricEventRecorderProvider, this.tokenManagementProvider, this.logProvider);
    }
}
