package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.sessionmanager.HeadsUpNotificationManager;
import com.amazon.tarazed.sessionmanager.TarazedController;
import com.amazon.tarazed.sessionmanager.TarazedResourceManager;
import com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideTarazedControllerFactory implements Factory<TarazedController> {
    private final Provider<BrowserPresenceDetectorToResumeSuspendedSession> browserPresenceDetectorImplProvider;
    private final Provider<CoroutineScope> coroutineScopeProvider;
    private final Provider<DeviceInfoUtilityAndroid> deviceInfoUtilityAndroidProvider;
    private final Provider<DispatcherProvider> dispatcherProvider;
    private final Provider<HeadsUpNotificationManager> headsUpNotificationManagerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final LibraryModule module;
    private final Provider<TarazedResourceManager> resourceManagerProvider;
    private final Provider<TarazedSessionLogger> sessionLoggerProvider;

    public LibraryModule_ProvideTarazedControllerFactory(LibraryModule libraryModule, Provider<TarazedResourceManager> provider, Provider<TarazedMetricsHelper> provider2, Provider<TarazedSessionLogger> provider3, Provider<DeviceInfoUtilityAndroid> provider4, Provider<BrowserPresenceDetectorToResumeSuspendedSession> provider5, Provider<CoroutineScope> provider6, Provider<DispatcherProvider> provider7, Provider<HeadsUpNotificationManager> provider8) {
        this.module = libraryModule;
        this.resourceManagerProvider = provider;
        this.metricsHelperProvider = provider2;
        this.sessionLoggerProvider = provider3;
        this.deviceInfoUtilityAndroidProvider = provider4;
        this.browserPresenceDetectorImplProvider = provider5;
        this.coroutineScopeProvider = provider6;
        this.dispatcherProvider = provider7;
        this.headsUpNotificationManagerProvider = provider8;
    }

    public static LibraryModule_ProvideTarazedControllerFactory create(LibraryModule libraryModule, Provider<TarazedResourceManager> provider, Provider<TarazedMetricsHelper> provider2, Provider<TarazedSessionLogger> provider3, Provider<DeviceInfoUtilityAndroid> provider4, Provider<BrowserPresenceDetectorToResumeSuspendedSession> provider5, Provider<CoroutineScope> provider6, Provider<DispatcherProvider> provider7, Provider<HeadsUpNotificationManager> provider8) {
        return new LibraryModule_ProvideTarazedControllerFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static TarazedController provideInstance(LibraryModule libraryModule, Provider<TarazedResourceManager> provider, Provider<TarazedMetricsHelper> provider2, Provider<TarazedSessionLogger> provider3, Provider<DeviceInfoUtilityAndroid> provider4, Provider<BrowserPresenceDetectorToResumeSuspendedSession> provider5, Provider<CoroutineScope> provider6, Provider<DispatcherProvider> provider7, Provider<HeadsUpNotificationManager> provider8) {
        return proxyProvideTarazedController(libraryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    public static TarazedController proxyProvideTarazedController(LibraryModule libraryModule, TarazedResourceManager tarazedResourceManager, TarazedMetricsHelper tarazedMetricsHelper, TarazedSessionLogger tarazedSessionLogger, DeviceInfoUtilityAndroid deviceInfoUtilityAndroid, BrowserPresenceDetectorToResumeSuspendedSession browserPresenceDetectorToResumeSuspendedSession, CoroutineScope coroutineScope, DispatcherProvider dispatcherProvider, HeadsUpNotificationManager headsUpNotificationManager) {
        return (TarazedController) Preconditions.checkNotNull(libraryModule.provideTarazedController(tarazedResourceManager, tarazedMetricsHelper, tarazedSessionLogger, deviceInfoUtilityAndroid, browserPresenceDetectorToResumeSuspendedSession, coroutineScope, dispatcherProvider, headsUpNotificationManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedController mo10268get() {
        return provideInstance(this.module, this.resourceManagerProvider, this.metricsHelperProvider, this.sessionLoggerProvider, this.deviceInfoUtilityAndroidProvider, this.browserPresenceDetectorImplProvider, this.coroutineScopeProvider, this.dispatcherProvider, this.headsUpNotificationManagerProvider);
    }
}
