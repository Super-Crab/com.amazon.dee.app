package com.amazon.whisperjoin.provisioning.metrics.client.dash;

import android.content.Context;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.whisperjoin.provisioning.Radios;
import com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics;
import com.amazon.whisperjoin.provisioning.metrics.client.WhisperJoinSetupAttemptMetrics;
/* loaded from: classes13.dex */
public class DashSetupAttemptMetrics extends WhisperJoinSetupAttemptMetrics {

    /* loaded from: classes13.dex */
    public static class Builder extends SetupAttemptMetrics.Builder<DashSetupAttemptMetrics> {
        @Override // com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics.Builder
        public DashSetupAttemptMetrics build() {
            super.mo6644setActiveRadio(Radios.BLE);
            validateConfiguration();
            return new DashSetupAttemptMetrics(this.mContext, this.mMetricsFactory, this.mSourceName, this.mAppVersion, this.mDirectedId, this.mActiveRadio);
        }
    }

    DashSetupAttemptMetrics(Context context, MetricsFactory metricsFactory, String str, String str2, String str3, Radios radios) {
        super(context, metricsFactory, str, str2, str3, radios);
    }

    @Override // com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics
    public void onTerminationSuccess() {
        this.internalMetrics.wifiSetupMetrics.onConfiguredNetworkConnected();
        this.internalMetrics.registrationMetrics.onRegistrationSuccess();
        super.onTerminationSuccess();
    }
}
