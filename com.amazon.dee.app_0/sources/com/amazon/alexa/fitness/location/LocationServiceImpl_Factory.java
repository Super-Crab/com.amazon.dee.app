package com.amazon.alexa.fitness.location;

import android.content.Context;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.google.android.gms.location.FusedLocationProviderClient;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class LocationServiceImpl_Factory implements Factory<LocationServiceImpl> {
    private final Provider<FusedLocationProviderClient> clientProvider;
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<LocationServiceConfiguration> configurationProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ILog> logProvider;

    public LocationServiceImpl_Factory(Provider<ComponentRegistry> provider, Provider<Context> provider2, Provider<FusedLocationProviderClient> provider3, Provider<LocationServiceConfiguration> provider4, Provider<ILog> provider5) {
        this.componentRegistryProvider = provider;
        this.contextProvider = provider2;
        this.clientProvider = provider3;
        this.configurationProvider = provider4;
        this.logProvider = provider5;
    }

    public static LocationServiceImpl_Factory create(Provider<ComponentRegistry> provider, Provider<Context> provider2, Provider<FusedLocationProviderClient> provider3, Provider<LocationServiceConfiguration> provider4, Provider<ILog> provider5) {
        return new LocationServiceImpl_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static LocationServiceImpl newLocationServiceImpl(ComponentRegistry componentRegistry, Context context, FusedLocationProviderClient fusedLocationProviderClient, LocationServiceConfiguration locationServiceConfiguration, ILog iLog) {
        return new LocationServiceImpl(componentRegistry, context, fusedLocationProviderClient, locationServiceConfiguration, iLog);
    }

    public static LocationServiceImpl provideInstance(Provider<ComponentRegistry> provider, Provider<Context> provider2, Provider<FusedLocationProviderClient> provider3, Provider<LocationServiceConfiguration> provider4, Provider<ILog> provider5) {
        return new LocationServiceImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocationServiceImpl mo10268get() {
        return provideInstance(this.componentRegistryProvider, this.contextProvider, this.clientProvider, this.configurationProvider, this.logProvider);
    }
}
