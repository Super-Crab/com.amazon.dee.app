package com.amazon.deecomms.remoteConfig;

import com.amazon.deecomms.auth.Stage;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ConfigurationSyncService_MembersInjector implements MembersInjector<ConfigurationSyncService> {
    private final Provider<CommsConnectivityMonitor> commsConnectivityMonitorProvider;
    private final Provider<Stage> stageProvider;

    public ConfigurationSyncService_MembersInjector(Provider<Stage> provider, Provider<CommsConnectivityMonitor> provider2) {
        this.stageProvider = provider;
        this.commsConnectivityMonitorProvider = provider2;
    }

    public static MembersInjector<ConfigurationSyncService> create(Provider<Stage> provider, Provider<CommsConnectivityMonitor> provider2) {
        return new ConfigurationSyncService_MembersInjector(provider, provider2);
    }

    public static void injectCommsConnectivityMonitor(ConfigurationSyncService configurationSyncService, CommsConnectivityMonitor commsConnectivityMonitor) {
        configurationSyncService.commsConnectivityMonitor = commsConnectivityMonitor;
    }

    public static void injectStage(ConfigurationSyncService configurationSyncService, Stage stage) {
        configurationSyncService.stage = stage;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ConfigurationSyncService configurationSyncService) {
        configurationSyncService.stage = this.stageProvider.mo10268get();
        configurationSyncService.commsConnectivityMonitor = this.commsConnectivityMonitorProvider.mo10268get();
    }
}
