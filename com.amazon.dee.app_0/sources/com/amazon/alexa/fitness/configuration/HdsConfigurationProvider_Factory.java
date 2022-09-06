package com.amazon.alexa.fitness.configuration;

import android.content.res.Resources;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.marketplace.MarketplaceService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class HdsConfigurationProvider_Factory implements Factory<HdsConfigurationProvider> {
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MarketplaceService> marketplaceServiceProvider;
    private final Provider<Resources> resourcesProvider;

    public HdsConfigurationProvider_Factory(Provider<EnvironmentService> provider, Provider<MarketplaceService> provider2, Provider<Resources> provider3, Provider<ILog> provider4) {
        this.environmentServiceProvider = provider;
        this.marketplaceServiceProvider = provider2;
        this.resourcesProvider = provider3;
        this.logProvider = provider4;
    }

    public static HdsConfigurationProvider_Factory create(Provider<EnvironmentService> provider, Provider<MarketplaceService> provider2, Provider<Resources> provider3, Provider<ILog> provider4) {
        return new HdsConfigurationProvider_Factory(provider, provider2, provider3, provider4);
    }

    public static HdsConfigurationProvider newHdsConfigurationProvider(EnvironmentService environmentService, MarketplaceService marketplaceService, Resources resources, ILog iLog) {
        return new HdsConfigurationProvider(environmentService, marketplaceService, resources, iLog);
    }

    public static HdsConfigurationProvider provideInstance(Provider<EnvironmentService> provider, Provider<MarketplaceService> provider2, Provider<Resources> provider3, Provider<ILog> provider4) {
        return new HdsConfigurationProvider(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HdsConfigurationProvider mo10268get() {
        return provideInstance(this.environmentServiceProvider, this.marketplaceServiceProvider, this.resourcesProvider, this.logProvider);
    }
}
