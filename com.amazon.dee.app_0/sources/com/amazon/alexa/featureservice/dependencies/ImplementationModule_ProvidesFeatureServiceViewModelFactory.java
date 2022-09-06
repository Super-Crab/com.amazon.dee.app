package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.featureservice.implementation.FeatureCache;
import com.amazon.alexa.featureservice.implementation.FeatureRegistryUtil;
import com.amazon.alexa.featureservice.implementation.FeatureServiceViewModel;
import com.amazon.alexa.featureservice.recordTrigger.RecordTriggerService;
import com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo;
import com.amazon.alexa.featureservice.service.FeatureSubscriptionManager;
import com.amazon.alexa.featureservice.util.Analytics;
import com.amazon.alexa.featureservice.util.SafeEventBus;
import com.amazon.alexa.featureservice.util.TimeUtil;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ImplementationModule_ProvidesFeatureServiceViewModelFactory implements Factory<FeatureServiceViewModel> {
    private final Provider<Analytics> analyticsProvider;
    private final Provider<FeatureCache> featureCacheProvider;
    private final Provider<FeatureRegistryUtil> featureRegistryUtilProvider;
    private final Provider<FeatureServiceConfiguration> featureServiceConfigurationProvider;
    private final Provider<FeatureSubscriptionManager> featureSubscriptionManagerProvider;
    private final Provider<Gson> gsonProvider;
    private final ImplementationModule module;
    private final Provider<RecordTriggerService> recordTriggerServiceProvider;
    private final Provider<FeatureDataRepo> repoProvider;
    private final Provider<SafeEventBus> safeEventBusProvider;
    private final Provider<TimeUtil> timeUtilProvider;

    public ImplementationModule_ProvidesFeatureServiceViewModelFactory(ImplementationModule implementationModule, Provider<FeatureDataRepo> provider, Provider<FeatureRegistryUtil> provider2, Provider<RecordTriggerService> provider3, Provider<FeatureCache> provider4, Provider<FeatureSubscriptionManager> provider5, Provider<Analytics> provider6, Provider<SafeEventBus> provider7, Provider<Gson> provider8, Provider<TimeUtil> provider9, Provider<FeatureServiceConfiguration> provider10) {
        this.module = implementationModule;
        this.repoProvider = provider;
        this.featureRegistryUtilProvider = provider2;
        this.recordTriggerServiceProvider = provider3;
        this.featureCacheProvider = provider4;
        this.featureSubscriptionManagerProvider = provider5;
        this.analyticsProvider = provider6;
        this.safeEventBusProvider = provider7;
        this.gsonProvider = provider8;
        this.timeUtilProvider = provider9;
        this.featureServiceConfigurationProvider = provider10;
    }

    public static ImplementationModule_ProvidesFeatureServiceViewModelFactory create(ImplementationModule implementationModule, Provider<FeatureDataRepo> provider, Provider<FeatureRegistryUtil> provider2, Provider<RecordTriggerService> provider3, Provider<FeatureCache> provider4, Provider<FeatureSubscriptionManager> provider5, Provider<Analytics> provider6, Provider<SafeEventBus> provider7, Provider<Gson> provider8, Provider<TimeUtil> provider9, Provider<FeatureServiceConfiguration> provider10) {
        return new ImplementationModule_ProvidesFeatureServiceViewModelFactory(implementationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static FeatureServiceViewModel provideInstance(ImplementationModule implementationModule, Provider<FeatureDataRepo> provider, Provider<FeatureRegistryUtil> provider2, Provider<RecordTriggerService> provider3, Provider<FeatureCache> provider4, Provider<FeatureSubscriptionManager> provider5, Provider<Analytics> provider6, Provider<SafeEventBus> provider7, Provider<Gson> provider8, Provider<TimeUtil> provider9, Provider<FeatureServiceConfiguration> provider10) {
        return proxyProvidesFeatureServiceViewModel(implementationModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get());
    }

    public static FeatureServiceViewModel proxyProvidesFeatureServiceViewModel(ImplementationModule implementationModule, FeatureDataRepo featureDataRepo, FeatureRegistryUtil featureRegistryUtil, RecordTriggerService recordTriggerService, FeatureCache featureCache, FeatureSubscriptionManager featureSubscriptionManager, Analytics analytics, SafeEventBus safeEventBus, Gson gson, TimeUtil timeUtil, FeatureServiceConfiguration featureServiceConfiguration) {
        return (FeatureServiceViewModel) Preconditions.checkNotNull(implementationModule.providesFeatureServiceViewModel(featureDataRepo, featureRegistryUtil, recordTriggerService, featureCache, featureSubscriptionManager, analytics, safeEventBus, gson, timeUtil, featureServiceConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceViewModel mo10268get() {
        return provideInstance(this.module, this.repoProvider, this.featureRegistryUtilProvider, this.recordTriggerServiceProvider, this.featureCacheProvider, this.featureSubscriptionManagerProvider, this.analyticsProvider, this.safeEventBusProvider, this.gsonProvider, this.timeUtilProvider, this.featureServiceConfigurationProvider);
    }
}
