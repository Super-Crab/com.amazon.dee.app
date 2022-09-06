package com.amazon.tarazed.utility;

import android.content.Context;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class BrowserPresenceDetectorToResumeSuspendedSession_Factory implements Factory<BrowserPresenceDetectorToResumeSuspendedSession> {
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInfoUtility> deviceInfoUtilityProvider;
    private final Provider<TarazedEventDispatcher> eventDispatcherProvider;
    private final Provider<TarazedIoTManager> iotManagerProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final Provider<TarazedSessionNotifier> sessionNotifierProvider;

    public BrowserPresenceDetectorToResumeSuspendedSession_Factory(Provider<Context> provider, Provider<TarazedSessionLogger> provider2, Provider<DeviceInfoUtility> provider3, Provider<TarazedMetricsHelper> provider4, Provider<TarazedSessionNotifier> provider5, Provider<TarazedEventDispatcher> provider6, Provider<TarazedIoTManager> provider7) {
        this.contextProvider = provider;
        this.loggerProvider = provider2;
        this.deviceInfoUtilityProvider = provider3;
        this.metricsHelperProvider = provider4;
        this.sessionNotifierProvider = provider5;
        this.eventDispatcherProvider = provider6;
        this.iotManagerProvider = provider7;
    }

    public static BrowserPresenceDetectorToResumeSuspendedSession_Factory create(Provider<Context> provider, Provider<TarazedSessionLogger> provider2, Provider<DeviceInfoUtility> provider3, Provider<TarazedMetricsHelper> provider4, Provider<TarazedSessionNotifier> provider5, Provider<TarazedEventDispatcher> provider6, Provider<TarazedIoTManager> provider7) {
        return new BrowserPresenceDetectorToResumeSuspendedSession_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static BrowserPresenceDetectorToResumeSuspendedSession newBrowserPresenceDetectorToResumeSuspendedSession(Context context, TarazedSessionLogger tarazedSessionLogger, DeviceInfoUtility deviceInfoUtility, TarazedMetricsHelper tarazedMetricsHelper, TarazedSessionNotifier tarazedSessionNotifier, TarazedEventDispatcher tarazedEventDispatcher, TarazedIoTManager tarazedIoTManager) {
        return new BrowserPresenceDetectorToResumeSuspendedSession(context, tarazedSessionLogger, deviceInfoUtility, tarazedMetricsHelper, tarazedSessionNotifier, tarazedEventDispatcher, tarazedIoTManager);
    }

    public static BrowserPresenceDetectorToResumeSuspendedSession provideInstance(Provider<Context> provider, Provider<TarazedSessionLogger> provider2, Provider<DeviceInfoUtility> provider3, Provider<TarazedMetricsHelper> provider4, Provider<TarazedSessionNotifier> provider5, Provider<TarazedEventDispatcher> provider6, Provider<TarazedIoTManager> provider7) {
        return new BrowserPresenceDetectorToResumeSuspendedSession(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BrowserPresenceDetectorToResumeSuspendedSession mo10268get() {
        return provideInstance(this.contextProvider, this.loggerProvider, this.deviceInfoUtilityProvider, this.metricsHelperProvider, this.sessionNotifierProvider, this.eventDispatcherProvider, this.iotManagerProvider);
    }
}
