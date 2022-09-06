package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.tv.TVUIManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideTVIndicatorManager$TarazedAndroidLibrary_releaseFactory implements Factory<TVUIManager> {
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final GlobalModule module;
    private final Provider<ViewGroupManager> viewGroupManagerProvider;

    public GlobalModule_ProvideTVIndicatorManager$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<ViewGroupManager> provider2, Provider<TarazedMetricsHelper> provider3) {
        this.module = globalModule;
        this.loggerProvider = provider;
        this.viewGroupManagerProvider = provider2;
        this.metricsHelperProvider = provider3;
    }

    public static GlobalModule_ProvideTVIndicatorManager$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<ViewGroupManager> provider2, Provider<TarazedMetricsHelper> provider3) {
        return new GlobalModule_ProvideTVIndicatorManager$TarazedAndroidLibrary_releaseFactory(globalModule, provider, provider2, provider3);
    }

    public static TVUIManager provideInstance(GlobalModule globalModule, Provider<TarazedSessionLogger> provider, Provider<ViewGroupManager> provider2, Provider<TarazedMetricsHelper> provider3) {
        return proxyProvideTVIndicatorManager$TarazedAndroidLibrary_release(globalModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static TVUIManager proxyProvideTVIndicatorManager$TarazedAndroidLibrary_release(GlobalModule globalModule, TarazedSessionLogger tarazedSessionLogger, ViewGroupManager viewGroupManager, TarazedMetricsHelper tarazedMetricsHelper) {
        return (TVUIManager) Preconditions.checkNotNull(globalModule.provideTVIndicatorManager$TarazedAndroidLibrary_release(tarazedSessionLogger, viewGroupManager, tarazedMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TVUIManager mo10268get() {
        return provideInstance(this.module, this.loggerProvider, this.viewGroupManagerProvider, this.metricsHelperProvider);
    }
}
