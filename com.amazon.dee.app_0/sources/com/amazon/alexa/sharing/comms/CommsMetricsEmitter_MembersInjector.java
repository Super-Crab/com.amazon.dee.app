package com.amazon.alexa.sharing.comms;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class CommsMetricsEmitter_MembersInjector implements MembersInjector<CommsMetricsEmitter> {
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;

    public CommsMetricsEmitter_MembersInjector(Provider<AlexaCommsCoreMetricsService> provider) {
        this.metricsServiceLazyProvider = provider;
    }

    public static MembersInjector<CommsMetricsEmitter> create(Provider<AlexaCommsCoreMetricsService> provider) {
        return new CommsMetricsEmitter_MembersInjector(provider);
    }

    public static void injectMetricsServiceLazy(CommsMetricsEmitter commsMetricsEmitter, Lazy<AlexaCommsCoreMetricsService> lazy) {
        commsMetricsEmitter.metricsServiceLazy = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsMetricsEmitter commsMetricsEmitter) {
        injectMetricsServiceLazy(commsMetricsEmitter, DoubleCheck.lazy(this.metricsServiceLazyProvider));
    }
}
