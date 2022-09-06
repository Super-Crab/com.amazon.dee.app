package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideTarazedMetricsHelper$TarazedAndroidLibrary_releaseFactory implements Factory<TarazedMetricsHelper> {
    private final Provider<DeviceInfoUtilityAndroid> deviceInfoUtilityProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final GlobalModule module;

    public GlobalModule_ProvideTarazedMetricsHelper$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<DeviceInfoUtilityAndroid> provider2) {
        this.module = globalModule;
        this.loggerProvider = provider;
        this.deviceInfoUtilityProvider = provider2;
    }

    public static GlobalModule_ProvideTarazedMetricsHelper$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<DeviceInfoUtilityAndroid> provider2) {
        return new GlobalModule_ProvideTarazedMetricsHelper$TarazedAndroidLibrary_releaseFactory(globalModule, provider, provider2);
    }

    public static TarazedMetricsHelper provideInstance(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<DeviceInfoUtilityAndroid> provider2) {
        return proxyProvideTarazedMetricsHelper$TarazedAndroidLibrary_release(globalModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static TarazedMetricsHelper proxyProvideTarazedMetricsHelper$TarazedAndroidLibrary_release(GlobalModule globalModule, TarazedSessionLogger tarazedSessionLogger, DeviceInfoUtilityAndroid deviceInfoUtilityAndroid) {
        return (TarazedMetricsHelper) Preconditions.checkNotNull(globalModule.provideTarazedMetricsHelper$TarazedAndroidLibrary_release(tarazedSessionLogger, deviceInfoUtilityAndroid), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedMetricsHelper mo10268get() {
        return provideInstance(this.module, this.loggerProvider, this.deviceInfoUtilityProvider);
    }
}
