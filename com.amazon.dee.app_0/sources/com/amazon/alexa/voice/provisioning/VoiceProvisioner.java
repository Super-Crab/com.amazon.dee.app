package com.amazon.alexa.voice.provisioning;

import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.provisioning.FeatureProvisioner;
import rx.functions.Action1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class VoiceProvisioner implements FeatureProvisioner {
    private final AccountUpgradeService accountUpgradeService;
    private final MetricsService metricsService;
    private final NetworkService networkService;
    private boolean provisioning;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoiceProvisioner(AccountUpgradeService accountUpgradeService, NetworkService networkService, MetricsService metricsService) {
        this.accountUpgradeService = accountUpgradeService;
        this.networkService = networkService;
        this.metricsService = metricsService;
    }

    private void doProvisioning4Real(final FeatureProvisioner.Callback callback) {
        if (this.provisioning || hasProvisioned()) {
            return;
        }
        this.provisioning = true;
        callback.onProvisioningStarted();
        this.metricsService.recordEvent(VoiceMetricsConstants.UPGRADE_ACCOUNT_ATTEMPT, "vox_speech", null);
        this.accountUpgradeService.upgradeAccount().subscribe(new Action1() { // from class: com.amazon.alexa.voice.provisioning.-$$Lambda$VoiceProvisioner$74Ejsl2Xb1xbtsHlsbNdpB1XaiI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VoiceProvisioner.this.lambda$doProvisioning4Real$1$VoiceProvisioner(callback, (UserIdentity) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.voice.provisioning.-$$Lambda$VoiceProvisioner$PlTZO8IvMqPMZngrbggxMCVdARw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VoiceProvisioner.this.lambda$doProvisioning4Real$2$VoiceProvisioner(callback, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.voice.provisioning.FeatureProvisioner
    public boolean hasProvisioned() {
        return this.accountUpgradeService.isAccountUpgraded();
    }

    public /* synthetic */ void lambda$doProvisioning4Real$1$VoiceProvisioner(FeatureProvisioner.Callback callback, UserIdentity userIdentity) {
        if (userIdentity != null) {
            callback.onProvisioningSucceeded();
        } else {
            this.metricsService.recordEvent(VoiceMetricsConstants.UPGRADE_ACCOUNT_FAILURE, "vox_speech", null);
            callback.onProvisioningFailed(new RuntimeException("No user identity after account upgrade."));
        }
        this.provisioning = false;
    }

    public /* synthetic */ void lambda$doProvisioning4Real$2$VoiceProvisioner(FeatureProvisioner.Callback callback, Throwable th) {
        callback.onProvisioningFailed(new RuntimeException(th));
        this.provisioning = false;
    }

    public /* synthetic */ void lambda$provision$0$VoiceProvisioner(FeatureProvisioner.Callback callback, Boolean bool) {
        if (bool.booleanValue()) {
            doProvisioning4Real(callback);
        }
    }

    @Override // com.amazon.alexa.voice.provisioning.FeatureProvisioner
    public void provision(final FeatureProvisioner.Callback callback) {
        if (this.networkService.isConnected()) {
            doProvisioning4Real(callback);
        } else {
            this.networkService.onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.alexa.voice.provisioning.-$$Lambda$VoiceProvisioner$kSGQ3NSUK2ZY4K5qbveyrrAhDtU
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    VoiceProvisioner.this.lambda$provision$0$VoiceProvisioner(callback, (Boolean) obj);
                }
            });
        }
    }
}
