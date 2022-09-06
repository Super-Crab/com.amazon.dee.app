package com.amazon.alexa.presence.service.guardrails;

import android.util.Log;
import com.amazon.alexa.presence.library.MetricsRecorder;
import com.amazon.alexa.presence.metrics.MetricsId;
import com.amazon.alexa.presence.metrics.MetricsMethod;
import com.amazon.alexa.presence.service.GuestConnectNotifInfo;
import java.util.Date;
import org.joda.time.DateTimeConstants;
/* loaded from: classes9.dex */
public class FrequencyGuardrail implements PresenceFeatureGuardrailInterface {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MILLIS_IN_SECOND = 1000;
    private static final int NOTIFICATION_CAP = 3;
    private static final int SECONDS_IN_DAY = 86400;
    private static final String TAG = "com.amazon.alexa.presence.service.guardrails.FrequencyGuardrail";
    private final GuestConnectNotifInfo guestConnectNotifInfo;
    private MetricsRecorder metrics;

    public FrequencyGuardrail(GuestConnectNotifInfo guestConnectNotifInfo) {
        this.guestConnectNotifInfo = guestConnectNotifInfo;
        try {
            this.metrics = MetricsRecorder.getMetricsUtil();
        } catch (Throwable unused) {
            Log.w(TAG, "Unable to setup metrics service.");
        }
    }

    @Override // com.amazon.alexa.presence.service.guardrails.PresenceFeatureGuardrailInterface
    public boolean checkGuardrail() {
        boolean z = this.guestConnectNotifInfo.getNumSent() < 3 && this.guestConnectNotifInfo.getDateLastNotified() + ((long) (this.guestConnectNotifInfo.getNumSent() * DateTimeConstants.MILLIS_PER_WEEK)) < new Date().getTime();
        if (z) {
            this.metrics.recordCount(MetricsId.PASSED_GUARDRAIL, MetricsMethod.FREQUENCY_GUARDRAIL);
        } else {
            this.metrics.recordCount(MetricsId.FAILED_GUARDRAIL, MetricsMethod.FREQUENCY_GUARDRAIL);
        }
        return z;
    }
}
