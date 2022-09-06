package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideBizMetricsHelper$TarazedAndroidLibrary_releaseFactory implements Factory<BizMetricsHelper> {
    private final Provider<DeviceInfoUtility> deviceInfoUtilityProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final GlobalModule module;
    private final Provider<TarazedMetricsHelper> tarazedMetricsHelperProvider;

    public GlobalModule_ProvideBizMetricsHelper$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<DeviceInfoUtility> provider2, Provider<TarazedMetricsHelper> provider3) {
        this.module = globalModule;
        this.loggerProvider = provider;
        this.deviceInfoUtilityProvider = provider2;
        this.tarazedMetricsHelperProvider = provider3;
    }

    public static GlobalModule_ProvideBizMetricsHelper$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<DeviceInfoUtility> provider2, Provider<TarazedMetricsHelper> provider3) {
        return new GlobalModule_ProvideBizMetricsHelper$TarazedAndroidLibrary_releaseFactory(globalModule, provider, provider2, provider3);
    }

    public static BizMetricsHelper provideInstance(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<DeviceInfoUtility> provider2, Provider<TarazedMetricsHelper> provider3) {
        return proxyProvideBizMetricsHelper$TarazedAndroidLibrary_release(globalModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static BizMetricsHelper proxyProvideBizMetricsHelper$TarazedAndroidLibrary_release(GlobalModule globalModule, TarazedSessionLogger tarazedSessionLogger, DeviceInfoUtility deviceInfoUtility, TarazedMetricsHelper tarazedMetricsHelper) {
        return (BizMetricsHelper) Preconditions.checkNotNull(globalModule.provideBizMetricsHelper$TarazedAndroidLibrary_release(tarazedSessionLogger, deviceInfoUtility, tarazedMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BizMetricsHelper mo10268get() {
        return provideInstance(this.module, this.loggerProvider, this.deviceInfoUtilityProvider, this.tarazedMetricsHelperProvider);
    }
}
