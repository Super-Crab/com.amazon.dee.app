package com.amazon.tarazed.worker.logging.upload;

import android.content.SharedPreferences;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class TarazedLogUploadWorker_MembersInjector implements MembersInjector<TarazedLogUploadWorker> {
    private final Provider<DeviceInfoUtility> deviceInfoUtilityProvider;
    private final Provider<TarazedLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public TarazedLogUploadWorker_MembersInjector(Provider<TarazedLogger> provider, Provider<TarazedMetricsHelper> provider2, Provider<DeviceInfoUtility> provider3, Provider<SharedPreferences> provider4) {
        this.loggerProvider = provider;
        this.metricsProvider = provider2;
        this.deviceInfoUtilityProvider = provider3;
        this.sharedPreferencesProvider = provider4;
    }

    public static MembersInjector<TarazedLogUploadWorker> create(Provider<TarazedLogger> provider, Provider<TarazedMetricsHelper> provider2, Provider<DeviceInfoUtility> provider3, Provider<SharedPreferences> provider4) {
        return new TarazedLogUploadWorker_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectDeviceInfoUtility(TarazedLogUploadWorker tarazedLogUploadWorker, DeviceInfoUtility deviceInfoUtility) {
        tarazedLogUploadWorker.deviceInfoUtility = deviceInfoUtility;
    }

    public static void injectLogger(TarazedLogUploadWorker tarazedLogUploadWorker, TarazedLogger tarazedLogger) {
        tarazedLogUploadWorker.logger = tarazedLogger;
    }

    public static void injectMetrics(TarazedLogUploadWorker tarazedLogUploadWorker, TarazedMetricsHelper tarazedMetricsHelper) {
        tarazedLogUploadWorker.metrics = tarazedMetricsHelper;
    }

    public static void injectSharedPreferences(TarazedLogUploadWorker tarazedLogUploadWorker, Provider<SharedPreferences> provider) {
        tarazedLogUploadWorker.sharedPreferences = provider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TarazedLogUploadWorker tarazedLogUploadWorker) {
        injectLogger(tarazedLogUploadWorker, this.loggerProvider.mo10268get());
        injectMetrics(tarazedLogUploadWorker, this.metricsProvider.mo10268get());
        injectDeviceInfoUtility(tarazedLogUploadWorker, this.deviceInfoUtilityProvider.mo10268get());
        injectSharedPreferences(tarazedLogUploadWorker, this.sharedPreferencesProvider);
    }
}
