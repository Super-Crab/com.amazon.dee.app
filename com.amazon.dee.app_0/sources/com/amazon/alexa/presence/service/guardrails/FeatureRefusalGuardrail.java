package com.amazon.alexa.presence.service.guardrails;

import android.util.Log;
import com.amazon.alexa.presence.library.MetricsRecorder;
import com.amazon.alexa.presence.metrics.MetricsId;
import com.amazon.alexa.presence.metrics.MetricsMethod;
import com.amazon.alexa.presence.service.GuestConnectNotifInfo;
/* loaded from: classes9.dex */
public class FeatureRefusalGuardrail implements PresenceFeatureGuardrailInterface {
    private static final String TAG = "com.amazon.alexa.presence.service.guardrails.FeatureRefusalGuardrail";
    private final GuestConnectNotifInfo guestConnectNotifInfo;
    private MetricsRecorder metrics;

    public FeatureRefusalGuardrail(GuestConnectNotifInfo guestConnectNotifInfo) {
        this.guestConnectNotifInfo = guestConnectNotifInfo;
        try {
            this.metrics = MetricsRecorder.getMetricsUtil();
        } catch (Throwable unused) {
            Log.w(TAG, "Unable to setup metrics service.");
        }
    }

    @Override // com.amazon.alexa.presence.service.guardrails.PresenceFeatureGuardrailInterface
    public boolean checkGuardrail() {
        boolean z = !this.guestConnectNotifInfo.hasOpened();
        if (z) {
            this.metrics.recordCount(MetricsId.PASSED_GUARDRAIL, MetricsMethod.REFUSAL_GUARDRAIL);
        } else {
            this.metrics.recordCount(MetricsId.FAILED_GUARDRAIL, MetricsMethod.REFUSAL_GUARDRAIL);
        }
        return z;
    }
}
