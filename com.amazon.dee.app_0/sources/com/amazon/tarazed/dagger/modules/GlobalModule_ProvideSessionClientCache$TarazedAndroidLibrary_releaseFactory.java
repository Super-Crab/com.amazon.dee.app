package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideSessionClientCache$TarazedAndroidLibrary_releaseFactory implements Factory<SessionClientCache> {
    private final Provider<DeviceInfoUtilityAndroid> deviceInfoProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final GlobalModule module;

    public GlobalModule_ProvideSessionClientCache$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2, Provider<DeviceInfoUtilityAndroid> provider3) {
        this.module = globalModule;
        this.loggerProvider = provider;
        this.metricsHelperProvider = provider2;
        this.deviceInfoProvider = provider3;
    }

    public static GlobalModule_ProvideSessionClientCache$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2, Provider<DeviceInfoUtilityAndroid> provider3) {
        return new GlobalModule_ProvideSessionClientCache$TarazedAndroidLibrary_releaseFactory(globalModule, provider, provider2, provider3);
    }

    public static SessionClientCache provideInstance(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2, Provider<DeviceInfoUtilityAndroid> provider3) {
        return proxyProvideSessionClientCache$TarazedAndroidLibrary_release(globalModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static SessionClientCache proxyProvideSessionClientCache$TarazedAndroidLibrary_release(GlobalModule globalModule, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper, DeviceInfoUtilityAndroid deviceInfoUtilityAndroid) {
        return (SessionClientCache) Preconditions.checkNotNull(globalModule.provideSessionClientCache$TarazedAndroidLibrary_release(tarazedSessionLogger, tarazedMetricsHelper, deviceInfoUtilityAndroid), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionClientCache mo10268get() {
        return provideInstance(this.module, this.loggerProvider, this.metricsHelperProvider, this.deviceInfoProvider);
    }
}
