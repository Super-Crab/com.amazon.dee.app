package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideTarazedSessionNotifier$TarazedAndroidLibrary_releaseFactory implements Factory<TarazedSessionNotifier> {
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final GlobalModule module;

    public GlobalModule_ProvideTarazedSessionNotifier$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule, Provider<TarazedMetricsHelper> provider) {
        this.module = globalModule;
        this.metricsHelperProvider = provider;
    }

    public static GlobalModule_ProvideTarazedSessionNotifier$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule, Provider<TarazedMetricsHelper> provider) {
        return new GlobalModule_ProvideTarazedSessionNotifier$TarazedAndroidLibrary_releaseFactory(globalModule, provider);
    }

    public static TarazedSessionNotifier provideInstance(GlobalModule globalModule, Provider<TarazedMetricsHelper> provider) {
        return proxyProvideTarazedSessionNotifier$TarazedAndroidLibrary_release(globalModule, provider.mo10268get());
    }

    public static TarazedSessionNotifier proxyProvideTarazedSessionNotifier$TarazedAndroidLibrary_release(GlobalModule globalModule, TarazedMetricsHelper tarazedMetricsHelper) {
        return (TarazedSessionNotifier) Preconditions.checkNotNull(globalModule.provideTarazedSessionNotifier$TarazedAndroidLibrary_release(tarazedMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedSessionNotifier mo10268get() {
        return provideInstance(this.module, this.metricsHelperProvider);
    }
}
