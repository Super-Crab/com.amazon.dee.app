package com.amazon.whisperjoin.provisioning.metrics.client.dash;

import android.content.Context;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.whisperjoin.provisioning.Radios;
import com.amazon.whisperjoin.provisioning.metrics.client.WhisperJoinSetupAttemptMetrics;
/* loaded from: classes13.dex */
public class NoopSetupAttemptMetrics extends WhisperJoinSetupAttemptMetrics {
    public NoopSetupAttemptMetrics(Context context, MetricsFactory metricsFactory, String str, String str2, String str3, Radios radios) {
        super(context, metricsFactory, str, str2, str3, radios);
    }

    @Override // com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics
    public void onTerminationAborted(String str) {
    }

    @Override // com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics
    public void onTerminationFailure(String str, Throwable th) {
    }

    @Override // com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics
    public void onTerminationSuccess() {
    }
}
