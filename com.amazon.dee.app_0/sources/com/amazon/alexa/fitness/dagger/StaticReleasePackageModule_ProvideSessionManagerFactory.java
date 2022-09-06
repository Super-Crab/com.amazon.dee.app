package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery;
import com.amazon.alexa.fitness.sdk.SessionManager;
import com.amazon.alexa.fitness.sdk.SessionRecoveryManager;
import com.amazon.alexa.fitness.sdk.TimeoutHandler;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideSessionManagerFactory implements Factory<SessionManager> {
    private final Provider<AfxMessageProcessor> afxMessageProcessorProvider;
    private final Provider<FitnessAlgorithmsManager> fitnessAlgorithmsManagerProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;
    private final Provider<MetricsAggregatorRecovery> metricsAggregatorRecoveryProvider;
    private final StaticReleasePackageModule module;
    private final Provider<SessionRecoveryManager> recoveryManagerProvider;
    private final Provider<SampleStore> sampleStoreProvider;
    private final Provider<TimeoutHandler> timeoutHandlerProvider;
    private final Provider<UserPreferenceStore> userPreferenceStoreProvider;

    public StaticReleasePackageModule_ProvideSessionManagerFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<AfxMessageProcessor> provider, Provider<MetricsAggregator> provider2, Provider<MetricsAggregatorRecovery> provider3, Provider<FitnessAlgorithmsManager> provider4, Provider<SessionRecoveryManager> provider5, Provider<ILog> provider6, Provider<SampleStore> provider7, Provider<TimeoutHandler> provider8, Provider<UserPreferenceStore> provider9) {
        this.module = staticReleasePackageModule;
        this.afxMessageProcessorProvider = provider;
        this.metricsAggregatorProvider = provider2;
        this.metricsAggregatorRecoveryProvider = provider3;
        this.fitnessAlgorithmsManagerProvider = provider4;
        this.recoveryManagerProvider = provider5;
        this.logProvider = provider6;
        this.sampleStoreProvider = provider7;
        this.timeoutHandlerProvider = provider8;
        this.userPreferenceStoreProvider = provider9;
    }

    public static StaticReleasePackageModule_ProvideSessionManagerFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<AfxMessageProcessor> provider, Provider<MetricsAggregator> provider2, Provider<MetricsAggregatorRecovery> provider3, Provider<FitnessAlgorithmsManager> provider4, Provider<SessionRecoveryManager> provider5, Provider<ILog> provider6, Provider<SampleStore> provider7, Provider<TimeoutHandler> provider8, Provider<UserPreferenceStore> provider9) {
        return new StaticReleasePackageModule_ProvideSessionManagerFactory(staticReleasePackageModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static SessionManager provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<AfxMessageProcessor> provider, Provider<MetricsAggregator> provider2, Provider<MetricsAggregatorRecovery> provider3, Provider<FitnessAlgorithmsManager> provider4, Provider<SessionRecoveryManager> provider5, Provider<ILog> provider6, Provider<SampleStore> provider7, Provider<TimeoutHandler> provider8, Provider<UserPreferenceStore> provider9) {
        return proxyProvideSessionManager(staticReleasePackageModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get());
    }

    public static SessionManager proxyProvideSessionManager(StaticReleasePackageModule staticReleasePackageModule, AfxMessageProcessor afxMessageProcessor, MetricsAggregator metricsAggregator, MetricsAggregatorRecovery metricsAggregatorRecovery, FitnessAlgorithmsManager fitnessAlgorithmsManager, SessionRecoveryManager sessionRecoveryManager, ILog iLog, SampleStore sampleStore, TimeoutHandler timeoutHandler, UserPreferenceStore userPreferenceStore) {
        return (SessionManager) Preconditions.checkNotNull(staticReleasePackageModule.provideSessionManager(afxMessageProcessor, metricsAggregator, metricsAggregatorRecovery, fitnessAlgorithmsManager, sessionRecoveryManager, iLog, sampleStore, timeoutHandler, userPreferenceStore), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionManager mo10268get() {
        return provideInstance(this.module, this.afxMessageProcessorProvider, this.metricsAggregatorProvider, this.metricsAggregatorRecoveryProvider, this.fitnessAlgorithmsManagerProvider, this.recoveryManagerProvider, this.logProvider, this.sampleStoreProvider, this.timeoutHandlerProvider, this.userPreferenceStoreProvider);
    }
}
