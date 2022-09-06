package com.amazon.alexa.sharing.presenter;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class FallbackStrategyPresenter_Factory implements Factory<FallbackStrategyPresenter> {
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;

    public FallbackStrategyPresenter_Factory(Provider<AlexaCommsCoreMetricsService> provider) {
        this.metricsServiceLazyProvider = provider;
    }

    public static FallbackStrategyPresenter_Factory create(Provider<AlexaCommsCoreMetricsService> provider) {
        return new FallbackStrategyPresenter_Factory(provider);
    }

    public static FallbackStrategyPresenter newFallbackStrategyPresenter(Lazy<AlexaCommsCoreMetricsService> lazy) {
        return new FallbackStrategyPresenter(lazy);
    }

    public static FallbackStrategyPresenter provideInstance(Provider<AlexaCommsCoreMetricsService> provider) {
        return new FallbackStrategyPresenter(DoubleCheck.lazy(provider));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FallbackStrategyPresenter mo10268get() {
        return provideInstance(this.metricsServiceLazyProvider);
    }
}
