package com.amazon.alexa.voice.provisioning;

import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class ProvisioningModule {
    private ProvisioningModule() {
    }

    @Provides
    @Singleton
    public static FeatureProvisioner provideVoiceProvisioner(AccountUpgradeService accountUpgradeService, NetworkService networkService, MetricsService metricsService) {
        return new VoiceProvisioner(accountUpgradeService, networkService, metricsService);
    }
}
