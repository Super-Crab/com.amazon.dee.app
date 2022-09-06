package com.amazon.tarazed.activity;

import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import dagger.internal.Factory;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class ActivityTracker_Factory implements Factory<ActivityTracker> {
    private final Provider<CoroutineScope> coroutineScopeProvider;
    private final Provider<DeviceInfoUtility> deviceInfoUtilityProvider;
    private final Provider<DispatcherProvider> dispatcherProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;

    public ActivityTracker_Factory(Provider<TarazedSessionLogger> provider, Provider<DeviceInfoUtility> provider2, Provider<TarazedMetricsHelper> provider3, Provider<CoroutineScope> provider4, Provider<DispatcherProvider> provider5) {
        this.loggerProvider = provider;
        this.deviceInfoUtilityProvider = provider2;
        this.metricsHelperProvider = provider3;
        this.coroutineScopeProvider = provider4;
        this.dispatcherProvider = provider5;
    }

    public static ActivityTracker_Factory create(Provider<TarazedSessionLogger> provider, Provider<DeviceInfoUtility> provider2, Provider<TarazedMetricsHelper> provider3, Provider<CoroutineScope> provider4, Provider<DispatcherProvider> provider5) {
        return new ActivityTracker_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static ActivityTracker newActivityTracker(TarazedSessionLogger tarazedSessionLogger, DeviceInfoUtility deviceInfoUtility, TarazedMetricsHelper tarazedMetricsHelper, CoroutineScope coroutineScope, DispatcherProvider dispatcherProvider) {
        return new ActivityTracker(tarazedSessionLogger, deviceInfoUtility, tarazedMetricsHelper, coroutineScope, dispatcherProvider);
    }

    public static ActivityTracker provideInstance(Provider<TarazedSessionLogger> provider, Provider<DeviceInfoUtility> provider2, Provider<TarazedMetricsHelper> provider3, Provider<CoroutineScope> provider4, Provider<DispatcherProvider> provider5) {
        return new ActivityTracker(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ActivityTracker mo10268get() {
        return provideInstance(this.loggerProvider, this.deviceInfoUtilityProvider, this.metricsHelperProvider, this.coroutineScopeProvider, this.dispatcherProvider);
    }
}
