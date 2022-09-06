package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.dee.app.services.bluetooth.BluetoothService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideBluetoothServiceFactory implements Factory<BluetoothService> {
    private final Provider<Context> contextProvider;
    private final Provider<MainActivityLifecycleService> mainActivityLifecycleServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideBluetoothServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<MainActivityLifecycleService> provider2, Provider<Mobilytics> provider3) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.mainActivityLifecycleServiceProvider = provider2;
        this.mobilyticsProvider = provider3;
    }

    public static ServiceModule_ProvideBluetoothServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<MainActivityLifecycleService> provider2, Provider<Mobilytics> provider3) {
        return new ServiceModule_ProvideBluetoothServiceFactory(serviceModule, provider, provider2, provider3);
    }

    public static BluetoothService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<MainActivityLifecycleService> provider2, Provider<Mobilytics> provider3) {
        return proxyProvideBluetoothService(serviceModule, provider.mo10268get(), provider2.mo10268get(), DoubleCheck.lazy(provider3));
    }

    public static BluetoothService proxyProvideBluetoothService(ServiceModule serviceModule, Context context, MainActivityLifecycleService mainActivityLifecycleService, Lazy<Mobilytics> lazy) {
        return (BluetoothService) Preconditions.checkNotNull(serviceModule.provideBluetoothService(context, mainActivityLifecycleService, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BluetoothService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.mainActivityLifecycleServiceProvider, this.mobilyticsProvider);
    }
}
