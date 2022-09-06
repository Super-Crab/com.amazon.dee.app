package com.amazon.tarazed.arcus;

import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ArcusSyncWorker_MembersInjector implements MembersInjector<ArcusSyncWorker> {
    private final Provider<ArcusHelper> arcusHelperProvider;
    private final Provider<DeviceInfoUtilityAndroid> deviceInfoUtilityProvider;
    private final Provider<TarazedLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsProvider;
    private final Provider<RemoteConfigurationManager> remoteConfigurationManagerProvider;

    public ArcusSyncWorker_MembersInjector(Provider<DeviceInfoUtilityAndroid> provider, Provider<RemoteConfigurationManager> provider2, Provider<TarazedLogger> provider3, Provider<TarazedMetricsHelper> provider4, Provider<ArcusHelper> provider5) {
        this.deviceInfoUtilityProvider = provider;
        this.remoteConfigurationManagerProvider = provider2;
        this.loggerProvider = provider3;
        this.metricsProvider = provider4;
        this.arcusHelperProvider = provider5;
    }

    public static MembersInjector<ArcusSyncWorker> create(Provider<DeviceInfoUtilityAndroid> provider, Provider<RemoteConfigurationManager> provider2, Provider<TarazedLogger> provider3, Provider<TarazedMetricsHelper> provider4, Provider<ArcusHelper> provider5) {
        return new ArcusSyncWorker_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectArcusHelper(ArcusSyncWorker arcusSyncWorker, ArcusHelper arcusHelper) {
        arcusSyncWorker.arcusHelper = arcusHelper;
    }

    public static void injectDeviceInfoUtility(ArcusSyncWorker arcusSyncWorker, DeviceInfoUtilityAndroid deviceInfoUtilityAndroid) {
        arcusSyncWorker.deviceInfoUtility = deviceInfoUtilityAndroid;
    }

    public static void injectLogger(ArcusSyncWorker arcusSyncWorker, TarazedLogger tarazedLogger) {
        arcusSyncWorker.logger = tarazedLogger;
    }

    public static void injectMetrics(ArcusSyncWorker arcusSyncWorker, TarazedMetricsHelper tarazedMetricsHelper) {
        arcusSyncWorker.metrics = tarazedMetricsHelper;
    }

    public static void injectRemoteConfigurationManager(ArcusSyncWorker arcusSyncWorker, RemoteConfigurationManager remoteConfigurationManager) {
        arcusSyncWorker.remoteConfigurationManager = remoteConfigurationManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ArcusSyncWorker arcusSyncWorker) {
        injectDeviceInfoUtility(arcusSyncWorker, this.deviceInfoUtilityProvider.mo10268get());
        injectRemoteConfigurationManager(arcusSyncWorker, this.remoteConfigurationManagerProvider.mo10268get());
        injectLogger(arcusSyncWorker, this.loggerProvider.mo10268get());
        injectMetrics(arcusSyncWorker, this.metricsProvider.mo10268get());
        injectArcusHelper(arcusSyncWorker, this.arcusHelperProvider.mo10268get());
    }
}
