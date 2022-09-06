package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.featureservice.implementation.FeatureServiceViewModel;
import com.amazon.alexa.featureservice.sessionManagement.SessionManager;
import com.amazon.alexa.featureservice.util.Analytics;
import com.amazon.alexa.featureservice.util.SafeEventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ImplementationModule_ProvidesPlatformFeatureServiceFactory implements Factory<PlatformFeatureServiceV2> {
    private final Provider<Analytics> analyticsProvider;
    private final ImplementationModule module;
    private final Provider<SafeEventBus> safeEventBusProvider;
    private final Provider<SessionManager> sessionManagerProvider;
    private final Provider<FeatureServiceViewModel> viewModelProvider;

    public ImplementationModule_ProvidesPlatformFeatureServiceFactory(ImplementationModule implementationModule, Provider<FeatureServiceViewModel> provider, Provider<SessionManager> provider2, Provider<SafeEventBus> provider3, Provider<Analytics> provider4) {
        this.module = implementationModule;
        this.viewModelProvider = provider;
        this.sessionManagerProvider = provider2;
        this.safeEventBusProvider = provider3;
        this.analyticsProvider = provider4;
    }

    public static ImplementationModule_ProvidesPlatformFeatureServiceFactory create(ImplementationModule implementationModule, Provider<FeatureServiceViewModel> provider, Provider<SessionManager> provider2, Provider<SafeEventBus> provider3, Provider<Analytics> provider4) {
        return new ImplementationModule_ProvidesPlatformFeatureServiceFactory(implementationModule, provider, provider2, provider3, provider4);
    }

    public static PlatformFeatureServiceV2 provideInstance(ImplementationModule implementationModule, Provider<FeatureServiceViewModel> provider, Provider<SessionManager> provider2, Provider<SafeEventBus> provider3, Provider<Analytics> provider4) {
        return proxyProvidesPlatformFeatureService(implementationModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static PlatformFeatureServiceV2 proxyProvidesPlatformFeatureService(ImplementationModule implementationModule, FeatureServiceViewModel featureServiceViewModel, SessionManager sessionManager, SafeEventBus safeEventBus, Analytics analytics) {
        return (PlatformFeatureServiceV2) Preconditions.checkNotNull(implementationModule.providesPlatformFeatureService(featureServiceViewModel, sessionManager, safeEventBus, analytics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PlatformFeatureServiceV2 mo10268get() {
        return provideInstance(this.module, this.viewModelProvider, this.sessionManagerProvider, this.safeEventBusProvider, this.analyticsProvider);
    }
}
