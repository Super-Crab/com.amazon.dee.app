package com.amazon.alexa.biloba.view;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.emergencyHelpline.EmergencyHelplineRoutingHelper;
import com.amazon.alexa.biloba.view.webview.WebviewViewRoutingHelper;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class RootViewControllerFactory_MembersInjector implements MembersInjector<RootViewControllerFactory> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<DeferredRoutingHelper> deferredRoutingHelperProvider;
    private final Provider<EmergencyHelplineRoutingHelper> emergencyHelplineRoutingHelperProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;
    private final Provider<WebviewViewRoutingHelper> webviewViewRoutingHelperProvider;

    public RootViewControllerFactory_MembersInjector(Provider<BilobaMetricsService> provider, Provider<CareActorsStore> provider2, Provider<RoutingService> provider3, Provider<EmergencyHelplineRoutingHelper> provider4, Provider<WebviewViewRoutingHelper> provider5, Provider<EventBus> provider6, Provider<PersistentStorage.Factory> provider7, Provider<IdentityService> provider8, Provider<DeferredRoutingHelper> provider9) {
        this.bilobaMetricsServiceProvider = provider;
        this.careActorsStoreProvider = provider2;
        this.routingServiceProvider = provider3;
        this.emergencyHelplineRoutingHelperProvider = provider4;
        this.webviewViewRoutingHelperProvider = provider5;
        this.eventBusProvider = provider6;
        this.storageFactoryProvider = provider7;
        this.identityServiceProvider = provider8;
        this.deferredRoutingHelperProvider = provider9;
    }

    public static MembersInjector<RootViewControllerFactory> create(Provider<BilobaMetricsService> provider, Provider<CareActorsStore> provider2, Provider<RoutingService> provider3, Provider<EmergencyHelplineRoutingHelper> provider4, Provider<WebviewViewRoutingHelper> provider5, Provider<EventBus> provider6, Provider<PersistentStorage.Factory> provider7, Provider<IdentityService> provider8, Provider<DeferredRoutingHelper> provider9) {
        return new RootViewControllerFactory_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static void injectBilobaMetricsService(RootViewControllerFactory rootViewControllerFactory, Lazy<BilobaMetricsService> lazy) {
        rootViewControllerFactory.bilobaMetricsService = lazy;
    }

    public static void injectCareActorsStore(RootViewControllerFactory rootViewControllerFactory, Lazy<CareActorsStore> lazy) {
        rootViewControllerFactory.careActorsStore = lazy;
    }

    public static void injectDeferredRoutingHelper(RootViewControllerFactory rootViewControllerFactory, Lazy<DeferredRoutingHelper> lazy) {
        rootViewControllerFactory.deferredRoutingHelper = lazy;
    }

    public static void injectEmergencyHelplineRoutingHelper(RootViewControllerFactory rootViewControllerFactory, Lazy<EmergencyHelplineRoutingHelper> lazy) {
        rootViewControllerFactory.emergencyHelplineRoutingHelper = lazy;
    }

    public static void injectEventBus(RootViewControllerFactory rootViewControllerFactory, Lazy<EventBus> lazy) {
        rootViewControllerFactory.eventBus = lazy;
    }

    public static void injectIdentityService(RootViewControllerFactory rootViewControllerFactory, Lazy<IdentityService> lazy) {
        rootViewControllerFactory.identityService = lazy;
    }

    public static void injectRoutingService(RootViewControllerFactory rootViewControllerFactory, Lazy<RoutingService> lazy) {
        rootViewControllerFactory.routingService = lazy;
    }

    public static void injectStorageFactory(RootViewControllerFactory rootViewControllerFactory, Lazy<PersistentStorage.Factory> lazy) {
        rootViewControllerFactory.storageFactory = lazy;
    }

    public static void injectWebviewViewRoutingHelper(RootViewControllerFactory rootViewControllerFactory, Lazy<WebviewViewRoutingHelper> lazy) {
        rootViewControllerFactory.webviewViewRoutingHelper = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RootViewControllerFactory rootViewControllerFactory) {
        injectBilobaMetricsService(rootViewControllerFactory, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectCareActorsStore(rootViewControllerFactory, DoubleCheck.lazy(this.careActorsStoreProvider));
        injectRoutingService(rootViewControllerFactory, DoubleCheck.lazy(this.routingServiceProvider));
        injectEmergencyHelplineRoutingHelper(rootViewControllerFactory, DoubleCheck.lazy(this.emergencyHelplineRoutingHelperProvider));
        injectWebviewViewRoutingHelper(rootViewControllerFactory, DoubleCheck.lazy(this.webviewViewRoutingHelperProvider));
        injectEventBus(rootViewControllerFactory, DoubleCheck.lazy(this.eventBusProvider));
        injectStorageFactory(rootViewControllerFactory, DoubleCheck.lazy(this.storageFactoryProvider));
        injectIdentityService(rootViewControllerFactory, DoubleCheck.lazy(this.identityServiceProvider));
        injectDeferredRoutingHelper(rootViewControllerFactory, DoubleCheck.lazy(this.deferredRoutingHelperProvider));
    }
}
