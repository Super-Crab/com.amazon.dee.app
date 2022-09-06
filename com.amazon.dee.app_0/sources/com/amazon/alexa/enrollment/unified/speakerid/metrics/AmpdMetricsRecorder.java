package com.amazon.alexa.enrollment.unified.speakerid.metrics;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import dagger.Lazy;
/* loaded from: classes7.dex */
public class AmpdMetricsRecorder {
    @VisibleForTesting
    static final String VOICE_ENROLL_COMPONENT = "VoiceEnrollment";
    @VisibleForTesting
    static final String VOICE_ENROLL_SUB_COMPONENT = "VoiceEnrollment";
    private String callerContext = "VoiceEnrollment";
    private final Lazy<Mobilytics> mobilytics;

    public AmpdMetricsRecorder(Lazy<Mobilytics> lazy) {
        this.mobilytics = lazy;
    }

    private void recordUserInteraction(String str, String str2) {
        this.mobilytics.mo358get().recordUserInteractionEvent(this.mobilytics.mo358get().createUserInteractionEvent(str, str2, "VoiceEnrollment", this.callerContext, OwnerIdentifier.ALEXA_APP_HANDS_FREE_ANDROID));
        recordCounter(str);
    }

    public void finishTimer(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        mobilyticsMetricsTimer.finishTimer();
        this.mobilytics.mo358get().recordOperationalEvent(mobilyticsMetricsTimer);
    }

    public void initializeMetricsContext(String str) {
        if (TextUtils.isEmpty(str)) {
            this.callerContext = "VoiceEnrollment";
        } else {
            this.callerContext = str;
        }
    }

    public void recordCounter(String str) {
        this.mobilytics.mo358get().recordCounter(this.mobilytics.mo358get().createCounter(str, "VoiceEnrollment", this.callerContext, OwnerIdentifier.ALEXA_APP_HANDS_FREE_ANDROID));
        this.mobilytics.mo358get().recordCounter(this.mobilytics.mo358get().createCounter(str, String.format("%s_%s", "VoiceEnrollment", this.callerContext), this.callerContext, OwnerIdentifier.ALEXA_APP_HANDS_FREE_ANDROID));
    }

    public MobilyticsMetricsTimer startTimer(String str) {
        return this.mobilytics.mo358get().createTimer(str, "VoiceEnrollment", this.callerContext, OwnerIdentifier.ALEXA_APP_HANDS_FREE_ANDROID);
    }
}
