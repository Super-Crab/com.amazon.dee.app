package com.amazon.dee.app.dependencies;

import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.RoutingViewService;
import com.amazon.dee.app.services.toolbar.ToolbarService;
import com.amazon.dee.app.services.toolbar.ToolbarWatcher;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideToolbarWatcherFactory implements Factory<ToolbarWatcher> {
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final ElementsModule module;
    private final Provider<RoutingService> routeServiceProvider;
    private final Provider<ToolbarService> toolbarServiceProvider;
    private final Provider<RoutingViewService> viewServiceProvider;

    public ElementsModule_ProvideToolbarWatcherFactory(ElementsModule elementsModule, Provider<RoutingService> provider, Provider<ToolbarService> provider2, Provider<RoutingViewService> provider3, Provider<CrashMetadata> provider4) {
        this.module = elementsModule;
        this.routeServiceProvider = provider;
        this.toolbarServiceProvider = provider2;
        this.viewServiceProvider = provider3;
        this.crashMetadataProvider = provider4;
    }

    public static ElementsModule_ProvideToolbarWatcherFactory create(ElementsModule elementsModule, Provider<RoutingService> provider, Provider<ToolbarService> provider2, Provider<RoutingViewService> provider3, Provider<CrashMetadata> provider4) {
        return new ElementsModule_ProvideToolbarWatcherFactory(elementsModule, provider, provider2, provider3, provider4);
    }

    public static ToolbarWatcher provideInstance(ElementsModule elementsModule, Provider<RoutingService> provider, Provider<ToolbarService> provider2, Provider<RoutingViewService> provider3, Provider<CrashMetadata> provider4) {
        return proxyProvideToolbarWatcher(elementsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static ToolbarWatcher proxyProvideToolbarWatcher(ElementsModule elementsModule, RoutingService routingService, ToolbarService toolbarService, RoutingViewService routingViewService, CrashMetadata crashMetadata) {
        return (ToolbarWatcher) Preconditions.checkNotNull(elementsModule.provideToolbarWatcher(routingService, toolbarService, routingViewService, crashMetadata), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ToolbarWatcher mo10268get() {
        return provideInstance(this.module, this.routeServiceProvider, this.toolbarServiceProvider, this.viewServiceProvider, this.crashMetadataProvider);
    }
}
