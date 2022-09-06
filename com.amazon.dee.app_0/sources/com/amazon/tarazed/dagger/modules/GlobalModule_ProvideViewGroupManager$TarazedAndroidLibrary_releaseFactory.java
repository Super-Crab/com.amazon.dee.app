package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideViewGroupManager$TarazedAndroidLibrary_releaseFactory implements Factory<ViewGroupManager> {
    private final Provider<DeviceInfoUtilityAndroid> deviceInfoUtilityProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final GlobalModule module;

    public GlobalModule_ProvideViewGroupManager$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule, Provider<DeviceInfoUtilityAndroid> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3) {
        this.module = globalModule;
        this.deviceInfoUtilityProvider = provider;
        this.loggerProvider = provider2;
        this.metricsHelperProvider = provider3;
    }

    public static GlobalModule_ProvideViewGroupManager$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule, Provider<DeviceInfoUtilityAndroid> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3) {
        return new GlobalModule_ProvideViewGroupManager$TarazedAndroidLibrary_releaseFactory(globalModule, provider, provider2, provider3);
    }

    public static ViewGroupManager provideInstance(GlobalModule globalModule, Provider<DeviceInfoUtilityAndroid> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3) {
        return proxyProvideViewGroupManager$TarazedAndroidLibrary_release(globalModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static ViewGroupManager proxyProvideViewGroupManager$TarazedAndroidLibrary_release(GlobalModule globalModule, DeviceInfoUtilityAndroid deviceInfoUtilityAndroid, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return (ViewGroupManager) Preconditions.checkNotNull(globalModule.provideViewGroupManager$TarazedAndroidLibrary_release(deviceInfoUtilityAndroid, tarazedSessionLogger, tarazedMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewGroupManager mo10268get() {
        return provideInstance(this.module, this.deviceInfoUtilityProvider, this.loggerProvider, this.metricsHelperProvider);
    }
}
