package com.amazon.tarazed.sessionmanager;

import android.content.SharedPreferences;
import android.os.Handler;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.rotation.android.DisplayRotationListener;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.manager.TarazedUIManagerFactory;
import com.amazon.tarazed.ui.tv.MoveTVUIHandler;
import dagger.internal.Factory;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class TarazedResourceManager_Factory implements Factory<TarazedResourceManager> {
    private final Provider<ActivityTracker> activityTrackerProvider;
    private final Provider<ArcusHelper> arcusHelperProvider;
    private final Provider<BizMetricsHelper> bizMetricsHelperProvider;
    private final Provider<CoroutineScope> coroutineScopeProvider;
    private final Provider<DeviceInfoUtility> deviceInfoProvider;
    private final Provider<DispatcherProvider> dispatchersProvider;
    private final Provider<DisplayRotationListener> displayRotationListenerProvider;
    private final Provider<TarazedEventDispatcher> eventDispatcherProvider;
    private final Provider<TarazedIoTManager> iotManagerProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<Handler> mainLooperHandlerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final Provider<MoveTVUIHandler> moveTVUIHandlerProvider;
    private final Provider<TarazedSessionNotifier> sessionNotifierProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<TarazedUIManagerFactory> uiManagerFactoryProvider;
    private final Provider<ViewGroupManager> viewGroupManagerProvider;

    public TarazedResourceManager_Factory(Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2, Provider<BizMetricsHelper> provider3, Provider<TarazedSessionNotifier> provider4, Provider<DeviceInfoUtility> provider5, Provider<TarazedEventDispatcher> provider6, Provider<TarazedIoTManager> provider7, Provider<DisplayRotationListener> provider8, Provider<ViewGroupManager> provider9, Provider<MoveTVUIHandler> provider10, Provider<ArcusHelper> provider11, Provider<SharedPreferences> provider12, Provider<Handler> provider13, Provider<TarazedUIManagerFactory> provider14, Provider<CoroutineScope> provider15, Provider<DispatcherProvider> provider16, Provider<ActivityTracker> provider17) {
        this.loggerProvider = provider;
        this.metricsHelperProvider = provider2;
        this.bizMetricsHelperProvider = provider3;
        this.sessionNotifierProvider = provider4;
        this.deviceInfoProvider = provider5;
        this.eventDispatcherProvider = provider6;
        this.iotManagerProvider = provider7;
        this.displayRotationListenerProvider = provider8;
        this.viewGroupManagerProvider = provider9;
        this.moveTVUIHandlerProvider = provider10;
        this.arcusHelperProvider = provider11;
        this.sharedPreferencesProvider = provider12;
        this.mainLooperHandlerProvider = provider13;
        this.uiManagerFactoryProvider = provider14;
        this.coroutineScopeProvider = provider15;
        this.dispatchersProvider = provider16;
        this.activityTrackerProvider = provider17;
    }

    public static TarazedResourceManager_Factory create(Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2, Provider<BizMetricsHelper> provider3, Provider<TarazedSessionNotifier> provider4, Provider<DeviceInfoUtility> provider5, Provider<TarazedEventDispatcher> provider6, Provider<TarazedIoTManager> provider7, Provider<DisplayRotationListener> provider8, Provider<ViewGroupManager> provider9, Provider<MoveTVUIHandler> provider10, Provider<ArcusHelper> provider11, Provider<SharedPreferences> provider12, Provider<Handler> provider13, Provider<TarazedUIManagerFactory> provider14, Provider<CoroutineScope> provider15, Provider<DispatcherProvider> provider16, Provider<ActivityTracker> provider17) {
        return new TarazedResourceManager_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17);
    }

    public static TarazedResourceManager newTarazedResourceManager(TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper, BizMetricsHelper bizMetricsHelper, TarazedSessionNotifier tarazedSessionNotifier, DeviceInfoUtility deviceInfoUtility, TarazedEventDispatcher tarazedEventDispatcher, TarazedIoTManager tarazedIoTManager, DisplayRotationListener displayRotationListener, Provider<ViewGroupManager> provider, Provider<MoveTVUIHandler> provider2, ArcusHelper arcusHelper, Provider<SharedPreferences> provider3, Handler handler, TarazedUIManagerFactory tarazedUIManagerFactory, CoroutineScope coroutineScope, DispatcherProvider dispatcherProvider, ActivityTracker activityTracker) {
        return new TarazedResourceManager(tarazedSessionLogger, tarazedMetricsHelper, bizMetricsHelper, tarazedSessionNotifier, deviceInfoUtility, tarazedEventDispatcher, tarazedIoTManager, displayRotationListener, provider, provider2, arcusHelper, provider3, handler, tarazedUIManagerFactory, coroutineScope, dispatcherProvider, activityTracker);
    }

    public static TarazedResourceManager provideInstance(Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2, Provider<BizMetricsHelper> provider3, Provider<TarazedSessionNotifier> provider4, Provider<DeviceInfoUtility> provider5, Provider<TarazedEventDispatcher> provider6, Provider<TarazedIoTManager> provider7, Provider<DisplayRotationListener> provider8, Provider<ViewGroupManager> provider9, Provider<MoveTVUIHandler> provider10, Provider<ArcusHelper> provider11, Provider<SharedPreferences> provider12, Provider<Handler> provider13, Provider<TarazedUIManagerFactory> provider14, Provider<CoroutineScope> provider15, Provider<DispatcherProvider> provider16, Provider<ActivityTracker> provider17) {
        return new TarazedResourceManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9, provider10, provider11.mo10268get(), provider12, provider13.mo10268get(), provider14.mo10268get(), provider15.mo10268get(), provider16.mo10268get(), provider17.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedResourceManager mo10268get() {
        return provideInstance(this.loggerProvider, this.metricsHelperProvider, this.bizMetricsHelperProvider, this.sessionNotifierProvider, this.deviceInfoProvider, this.eventDispatcherProvider, this.iotManagerProvider, this.displayRotationListenerProvider, this.viewGroupManagerProvider, this.moveTVUIHandlerProvider, this.arcusHelperProvider, this.sharedPreferencesProvider, this.mainLooperHandlerProvider, this.uiManagerFactoryProvider, this.coroutineScopeProvider, this.dispatchersProvider, this.activityTrackerProvider);
    }
}
