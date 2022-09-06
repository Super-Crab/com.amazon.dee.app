package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession;
import dagger.internal.Factory;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class TarazedController_Factory implements Factory<TarazedController> {
    private final Provider<BrowserPresenceDetectorToResumeSuspendedSession> browserPresenceDetectorProvider;
    private final Provider<CoroutineScope> coroutineScopeProvider;
    private final Provider<DeviceInfoUtility> deviceInfoProvider;
    private final Provider<DispatcherProvider> dispatchersProvider;
    private final Provider<HeadsUpNotificationManager> headsUpNotificationManagerProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final Provider<TarazedResourceManager> resourceManagerProvider;

    public TarazedController_Factory(Provider<TarazedResourceManager> provider, Provider<TarazedMetricsHelper> provider2, Provider<TarazedSessionLogger> provider3, Provider<DeviceInfoUtility> provider4, Provider<BrowserPresenceDetectorToResumeSuspendedSession> provider5, Provider<CoroutineScope> provider6, Provider<DispatcherProvider> provider7, Provider<HeadsUpNotificationManager> provider8) {
        this.resourceManagerProvider = provider;
        this.metricsHelperProvider = provider2;
        this.loggerProvider = provider3;
        this.deviceInfoProvider = provider4;
        this.browserPresenceDetectorProvider = provider5;
        this.coroutineScopeProvider = provider6;
        this.dispatchersProvider = provider7;
        this.headsUpNotificationManagerProvider = provider8;
    }

    public static TarazedController_Factory create(Provider<TarazedResourceManager> provider, Provider<TarazedMetricsHelper> provider2, Provider<TarazedSessionLogger> provider3, Provider<DeviceInfoUtility> provider4, Provider<BrowserPresenceDetectorToResumeSuspendedSession> provider5, Provider<CoroutineScope> provider6, Provider<DispatcherProvider> provider7, Provider<HeadsUpNotificationManager> provider8) {
        return new TarazedController_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static TarazedController newTarazedController(TarazedResourceManager tarazedResourceManager, TarazedMetricsHelper tarazedMetricsHelper, TarazedSessionLogger tarazedSessionLogger, DeviceInfoUtility deviceInfoUtility, BrowserPresenceDetectorToResumeSuspendedSession browserPresenceDetectorToResumeSuspendedSession, CoroutineScope coroutineScope, DispatcherProvider dispatcherProvider, HeadsUpNotificationManager headsUpNotificationManager) {
        return new TarazedController(tarazedResourceManager, tarazedMetricsHelper, tarazedSessionLogger, deviceInfoUtility, browserPresenceDetectorToResumeSuspendedSession, coroutineScope, dispatcherProvider, headsUpNotificationManager);
    }

    public static TarazedController provideInstance(Provider<TarazedResourceManager> provider, Provider<TarazedMetricsHelper> provider2, Provider<TarazedSessionLogger> provider3, Provider<DeviceInfoUtility> provider4, Provider<BrowserPresenceDetectorToResumeSuspendedSession> provider5, Provider<CoroutineScope> provider6, Provider<DispatcherProvider> provider7, Provider<HeadsUpNotificationManager> provider8) {
        return new TarazedController(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedController mo10268get() {
        return provideInstance(this.resourceManagerProvider, this.metricsHelperProvider, this.loggerProvider, this.deviceInfoProvider, this.browserPresenceDetectorProvider, this.coroutineScopeProvider, this.dispatchersProvider, this.headsUpNotificationManagerProvider);
    }
}
