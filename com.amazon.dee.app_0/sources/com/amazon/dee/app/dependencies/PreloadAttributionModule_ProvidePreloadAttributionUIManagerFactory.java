package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.ui.preload.PreloadAttributionUIManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class PreloadAttributionModule_ProvidePreloadAttributionUIManagerFactory implements Factory<PreloadAttributionUIManager> {
    private final Provider<PreloadAttributionManager> attributionManagerProvider;
    private final Provider<Context> contextProvider;
    private final PreloadAttributionModule module;
    private final Provider<RoutingService> routingServiceProvider;

    public PreloadAttributionModule_ProvidePreloadAttributionUIManagerFactory(PreloadAttributionModule preloadAttributionModule, Provider<Context> provider, Provider<RoutingService> provider2, Provider<PreloadAttributionManager> provider3) {
        this.module = preloadAttributionModule;
        this.contextProvider = provider;
        this.routingServiceProvider = provider2;
        this.attributionManagerProvider = provider3;
    }

    public static PreloadAttributionModule_ProvidePreloadAttributionUIManagerFactory create(PreloadAttributionModule preloadAttributionModule, Provider<Context> provider, Provider<RoutingService> provider2, Provider<PreloadAttributionManager> provider3) {
        return new PreloadAttributionModule_ProvidePreloadAttributionUIManagerFactory(preloadAttributionModule, provider, provider2, provider3);
    }

    public static PreloadAttributionUIManager provideInstance(PreloadAttributionModule preloadAttributionModule, Provider<Context> provider, Provider<RoutingService> provider2, Provider<PreloadAttributionManager> provider3) {
        return proxyProvidePreloadAttributionUIManager(preloadAttributionModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static PreloadAttributionUIManager proxyProvidePreloadAttributionUIManager(PreloadAttributionModule preloadAttributionModule, Context context, RoutingService routingService, PreloadAttributionManager preloadAttributionManager) {
        return (PreloadAttributionUIManager) Preconditions.checkNotNull(preloadAttributionModule.providePreloadAttributionUIManager(context, routingService, preloadAttributionManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PreloadAttributionUIManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.routingServiceProvider, this.attributionManagerProvider);
    }
}
