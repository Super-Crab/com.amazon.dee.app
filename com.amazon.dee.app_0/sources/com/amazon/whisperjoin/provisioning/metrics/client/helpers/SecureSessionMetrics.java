package com.amazon.whisperjoin.provisioning.metrics.client.helpers;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.provisioning.metrics.client.MetricHelper;
/* loaded from: classes13.dex */
public class SecureSessionMetrics {
    private static final String SECURE_CHANNEL_ESTABLISHED_PIVOT = "SecureChannelEstablished";
    private static final String SECURE_CHANNEL_REQUIRED_PIVOT = "SecureChannelRequired";
    private static final String SECURE_CHANNEL_SETUP_SUCCESS_METRIC = "SecureChannelSetupSuccess";
    private final MetricHelper mMetricHelper;

    public SecureSessionMetrics(MetricHelper metricHelper) {
        this.mMetricHelper = metricHelper;
    }

    public void onSecureSessionSetupFailed() {
        this.mMetricHelper.recordString(SECURE_CHANNEL_REQUIRED_PIVOT, true, new Object[0]);
        this.mMetricHelper.recordString(SECURE_CHANNEL_ESTABLISHED_PIVOT, false, new Object[0]);
        this.mMetricHelper.recordCounter(SECURE_CHANNEL_SETUP_SUCCESS_METRIC, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, new Object[0]);
    }

    public void onSecureSessionSetupSuccess() {
        this.mMetricHelper.recordString(SECURE_CHANNEL_REQUIRED_PIVOT, true, new Object[0]);
        this.mMetricHelper.recordString(SECURE_CHANNEL_ESTABLISHED_PIVOT, true, new Object[0]);
        this.mMetricHelper.recordCounter(SECURE_CHANNEL_SETUP_SUCCESS_METRIC, 1.0d, new Object[0]);
    }
}
