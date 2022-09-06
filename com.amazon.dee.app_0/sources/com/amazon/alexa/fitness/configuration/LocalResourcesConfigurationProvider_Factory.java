package com.amazon.alexa.fitness.configuration;

import android.content.res.Resources;
import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class LocalResourcesConfigurationProvider_Factory implements Factory<LocalResourcesConfigurationProvider> {
    private final Provider<ILog> logProvider;
    private final Provider<Resources> resourcesProvider;

    public LocalResourcesConfigurationProvider_Factory(Provider<Resources> provider, Provider<ILog> provider2) {
        this.resourcesProvider = provider;
        this.logProvider = provider2;
    }

    public static LocalResourcesConfigurationProvider_Factory create(Provider<Resources> provider, Provider<ILog> provider2) {
        return new LocalResourcesConfigurationProvider_Factory(provider, provider2);
    }

    public static LocalResourcesConfigurationProvider newLocalResourcesConfigurationProvider(Resources resources, ILog iLog) {
        return new LocalResourcesConfigurationProvider(resources, iLog);
    }

    public static LocalResourcesConfigurationProvider provideInstance(Provider<Resources> provider, Provider<ILog> provider2) {
        return new LocalResourcesConfigurationProvider(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocalResourcesConfigurationProvider mo10268get() {
        return provideInstance(this.resourcesProvider, this.logProvider);
    }
}
