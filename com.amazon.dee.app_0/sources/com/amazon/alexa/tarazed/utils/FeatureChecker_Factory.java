package com.amazon.alexa.tarazed.utils;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import dagger.internal.Factory;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes10.dex */
public final class FeatureChecker_Factory implements Factory<FeatureChecker> {
    private final Provider<DispatcherProvider> dispatchersProvider;
    private final Provider<FeatureServiceV2> featureServiceProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<CoroutineScope> mainScopeProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;

    public FeatureChecker_Factory(Provider<FeatureServiceV2> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3, Provider<DispatcherProvider> provider4, Provider<CoroutineScope> provider5) {
        this.featureServiceProvider = provider;
        this.loggerProvider = provider2;
        this.metricsHelperProvider = provider3;
        this.dispatchersProvider = provider4;
        this.mainScopeProvider = provider5;
    }

    public static FeatureChecker_Factory create(Provider<FeatureServiceV2> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3, Provider<DispatcherProvider> provider4, Provider<CoroutineScope> provider5) {
        return new FeatureChecker_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static FeatureChecker newFeatureChecker(FeatureServiceV2 featureServiceV2, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper, DispatcherProvider dispatcherProvider, CoroutineScope coroutineScope) {
        return new FeatureChecker(featureServiceV2, tarazedSessionLogger, tarazedMetricsHelper, dispatcherProvider, coroutineScope);
    }

    public static FeatureChecker provideInstance(Provider<FeatureServiceV2> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3, Provider<DispatcherProvider> provider4, Provider<CoroutineScope> provider5) {
        return new FeatureChecker(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureChecker mo10268get() {
        return provideInstance(this.featureServiceProvider, this.loggerProvider, this.metricsHelperProvider, this.dispatchersProvider, this.mainScopeProvider);
    }
}
