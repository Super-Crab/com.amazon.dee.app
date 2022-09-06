package com.amazon.deecomms.commscore;

import androidx.annotation.VisibleForTesting;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commscore.api.remoteconfiguration.RemoteConfigValue;
import com.amazon.deecomms.api.CommsDynamicFeature;
import com.amazon.deecomms.commscore.legacy.AlexaCommsCoreRemoteConfigurationServiceLegacy;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.core.FeatureFlagManager;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
/* loaded from: classes12.dex */
public class AlexaCommsCoreRemoteConfigurationServiceImpl implements AlexaCommsCoreRemoteConfigurationService {
    private AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService;
    private ArcusConfig arcusConfig;
    private FeatureFlagManager featureFlagManager;

    public AlexaCommsCoreRemoteConfigurationServiceImpl() {
        if (CommsDaggerWrapper.getComponent() != null) {
            this.featureFlagManager = CommsDaggerWrapper.getComponent().getFeatureFlagManager();
            this.arcusConfig = CommsDaggerWrapper.getComponent().getArcusConfig();
            initialize();
        }
    }

    private void initialize() {
        if (!this.featureFlagManager.isFeatureEnabled(CommsDynamicFeature.COMMS_CORE_REMOTE_CONFIG_SERVICE, false)) {
            this.alexaCommsCoreRemoteConfigurationService = new AlexaCommsCoreRemoteConfigurationServiceLegacy(this.arcusConfig);
            return;
        }
        throw new UnsupportedOperationException("Interface not yet implemented");
    }

    @VisibleForTesting
    AlexaCommsCoreRemoteConfigurationService getCommsCoreConfigServiceImplementation() {
        return this.alexaCommsCoreRemoteConfigurationService;
    }

    @Override // com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService
    public RemoteConfigValue getRemoteConfiguration(String str, Object obj) {
        return this.alexaCommsCoreRemoteConfigurationService.getRemoteConfiguration(str, obj);
    }

    @VisibleForTesting
    AlexaCommsCoreRemoteConfigurationServiceImpl(ArcusConfig arcusConfig, FeatureFlagManager featureFlagManager) {
        this.arcusConfig = arcusConfig;
        this.featureFlagManager = featureFlagManager;
        initialize();
    }
}
