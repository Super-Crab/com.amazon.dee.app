package com.amazon.whisperjoin.provisioning.metrics.client;

import android.content.Context;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.whisperjoin.provisioning.Radios;
import com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics;
import com.amazon.whisperjoin.provisioning.metrics.client.helpers.ConnectionMetrics;
import com.amazon.whisperjoin.provisioning.metrics.client.helpers.DeviceMetrics;
import com.amazon.whisperjoin.provisioning.metrics.client.helpers.RegistrationMetrics;
import com.amazon.whisperjoin.provisioning.metrics.client.helpers.SecureSessionMetrics;
import com.amazon.whisperjoin.provisioning.metrics.client.helpers.WifiSetupMetrics;
/* loaded from: classes13.dex */
public class WhisperJoinSetupAttemptMetrics extends SetupAttemptMetrics {
    public InternalMetrics internalMetrics;

    /* loaded from: classes13.dex */
    public static class Builder extends SetupAttemptMetrics.Builder<WhisperJoinSetupAttemptMetrics> {
        @Override // com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics.Builder
        public WhisperJoinSetupAttemptMetrics build() {
            mo6644setActiveRadio(Radios.BLE);
            validateConfiguration();
            return new WhisperJoinSetupAttemptMetrics(this.mContext, this.mMetricsFactory, this.mSourceName, this.mAppVersion, this.mDirectedId, this.mActiveRadio);
        }
    }

    /* loaded from: classes13.dex */
    public class InternalMetrics {
        public final ConnectionMetrics connectionMetrics;
        public final DeviceMetrics deviceMetrics;
        public RegistrationMetrics registrationMetrics;
        public final SecureSessionMetrics secureSessionMetrics;
        public WifiSetupMetrics wifiSetupMetrics;

        public InternalMetrics(MetricHelper metricHelper, Radios radios) {
            this.deviceMetrics = new DeviceMetrics(metricHelper);
            this.connectionMetrics = new ConnectionMetrics(metricHelper, radios);
            this.secureSessionMetrics = new SecureSessionMetrics(metricHelper);
            this.registrationMetrics = new RegistrationMetrics(WhisperJoinSetupAttemptMetrics.this.mMetricHelper);
            this.wifiSetupMetrics = new WifiSetupMetrics(WhisperJoinSetupAttemptMetrics.this.mMetricHelper);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WhisperJoinSetupAttemptMetrics(Context context, MetricsFactory metricsFactory, String str, String str2, String str3, Radios radios) {
        super(context, metricsFactory, str, str2, str3, radios);
        this.internalMetrics = new InternalMetrics(this.mMetricHelper, this.mActiveRadio);
    }
}
