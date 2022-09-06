package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.tcomm.TCommServiceManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TCommServiceModule_ProvideTCommServiceManagerFactory implements Factory<TCommServiceManager> {
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MainActivityLifecycleService> mainActivityLifecycleServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final TCommServiceModule module;
    private final Provider<NetworkService> networkServiceProvider;

    public TCommServiceModule_ProvideTCommServiceManagerFactory(TCommServiceModule tCommServiceModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<NetworkService> provider3, Provider<EventBus> provider4, Provider<MainActivityLifecycleService> provider5, Provider<Mobilytics> provider6, Provider<DeviceInformation> provider7, Provider<FeatureServiceV2> provider8) {
        this.module = tCommServiceModule;
        this.contextProvider = provider;
        this.identityServiceProvider = provider2;
        this.networkServiceProvider = provider3;
        this.eventBusProvider = provider4;
        this.mainActivityLifecycleServiceProvider = provider5;
        this.mobilyticsProvider = provider6;
        this.deviceInformationProvider = provider7;
        this.featureServiceV2LazyProvider = provider8;
    }

    public static TCommServiceModule_ProvideTCommServiceManagerFactory create(TCommServiceModule tCommServiceModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<NetworkService> provider3, Provider<EventBus> provider4, Provider<MainActivityLifecycleService> provider5, Provider<Mobilytics> provider6, Provider<DeviceInformation> provider7, Provider<FeatureServiceV2> provider8) {
        return new TCommServiceModule_ProvideTCommServiceManagerFactory(tCommServiceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static TCommServiceManager provideInstance(TCommServiceModule tCommServiceModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<NetworkService> provider3, Provider<EventBus> provider4, Provider<MainActivityLifecycleService> provider5, Provider<Mobilytics> provider6, Provider<DeviceInformation> provider7, Provider<FeatureServiceV2> provider8) {
        return proxyProvideTCommServiceManager(tCommServiceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), DoubleCheck.lazy(provider8));
    }

    public static TCommServiceManager proxyProvideTCommServiceManager(TCommServiceModule tCommServiceModule, Context context, IdentityService identityService, NetworkService networkService, EventBus eventBus, MainActivityLifecycleService mainActivityLifecycleService, Mobilytics mobilytics, DeviceInformation deviceInformation, Lazy<FeatureServiceV2> lazy) {
        return (TCommServiceManager) Preconditions.checkNotNull(tCommServiceModule.provideTCommServiceManager(context, identityService, networkService, eventBus, mainActivityLifecycleService, mobilytics, deviceInformation, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TCommServiceManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.identityServiceProvider, this.networkServiceProvider, this.eventBusProvider, this.mainActivityLifecycleServiceProvider, this.mobilyticsProvider, this.deviceInformationProvider, this.featureServiceV2LazyProvider);
    }
}
