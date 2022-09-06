package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.service.DefaultFeatureServiceV2;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.dee.app.http.CoralService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideDefaultFeatureServiceV2Factory implements Factory<DefaultFeatureServiceV2> {
    private final Provider<Context> contextProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideDefaultFeatureServiceV2Factory(ServiceModule serviceModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<CoralService> provider3, Provider<Mobilytics> provider4) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.eventBusProvider = provider2;
        this.coralServiceProvider = provider3;
        this.mobilyticsProvider = provider4;
    }

    public static ServiceModule_ProvideDefaultFeatureServiceV2Factory create(ServiceModule serviceModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<CoralService> provider3, Provider<Mobilytics> provider4) {
        return new ServiceModule_ProvideDefaultFeatureServiceV2Factory(serviceModule, provider, provider2, provider3, provider4);
    }

    public static DefaultFeatureServiceV2 provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<CoralService> provider3, Provider<Mobilytics> provider4) {
        return proxyProvideDefaultFeatureServiceV2(serviceModule, provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static DefaultFeatureServiceV2 proxyProvideDefaultFeatureServiceV2(ServiceModule serviceModule, Context context, Lazy<EventBus> lazy, Lazy<CoralService> lazy2, Lazy<Mobilytics> lazy3) {
        return (DefaultFeatureServiceV2) Preconditions.checkNotNull(serviceModule.provideDefaultFeatureServiceV2(context, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultFeatureServiceV2 mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.eventBusProvider, this.coralServiceProvider, this.mobilyticsProvider);
    }
}
