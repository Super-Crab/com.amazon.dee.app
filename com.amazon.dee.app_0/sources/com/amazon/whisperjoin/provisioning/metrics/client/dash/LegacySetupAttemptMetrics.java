package com.amazon.whisperjoin.provisioning.metrics.client.dash;

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
public class LegacySetupAttemptMetrics extends SetupAttemptMetrics {
    public final ConnectionMetrics connectionMetrics;
    public final DeviceMetrics deviceMetrics;
    public final RegistrationMetrics registrationMetrics;
    public final SecureSessionMetrics secureSessionMetrics;
    public final WifiSetupMetrics wifiSetupMetrics;

    /* loaded from: classes13.dex */
    public static class Builder extends SetupAttemptMetrics.Builder<LegacySetupAttemptMetrics> {
        @Override // com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics.Builder
        public LegacySetupAttemptMetrics build() {
            validateConfiguration();
            return new LegacySetupAttemptMetrics(this.mContext, this.mMetricsFactory, this.mSourceName, this.mAppVersion, this.mDirectedId, this.mActiveRadio);
        }

        @Override // com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics.Builder
        /* renamed from: setActiveRadio  reason: collision with other method in class */
        public Builder mo6644setActiveRadio(Radios radios) {
            super.mo6644setActiveRadio(radios);
            return this;
        }
    }

    LegacySetupAttemptMetrics(Context context, MetricsFactory metricsFactory, String str, String str2, String str3, Radios radios) {
        super(context, metricsFactory, str, str2, str3, radios);
        this.deviceMetrics = new DeviceMetrics(this.mMetricHelper);
        this.connectionMetrics = new ConnectionMetrics(this.mMetricHelper, this.mActiveRadio);
        this.secureSessionMetrics = new SecureSessionMetrics(this.mMetricHelper);
        this.registrationMetrics = new RegistrationMetrics(this.mMetricHelper);
        this.wifiSetupMetrics = new WifiSetupMetrics(this.mMetricHelper);
    }
}
