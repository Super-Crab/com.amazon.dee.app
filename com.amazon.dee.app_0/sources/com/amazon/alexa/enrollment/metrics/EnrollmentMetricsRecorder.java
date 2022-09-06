package com.amazon.alexa.enrollment.metrics;

import android.text.TextUtils;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import dagger.Lazy;
/* loaded from: classes7.dex */
public class EnrollmentMetricsRecorder {
    private String callerContext = "VoiceEnrollment";
    private final Lazy<Mobilytics> mobilytics;

    public EnrollmentMetricsRecorder(Lazy<Mobilytics> lazy) {
        this.mobilytics = lazy;
    }

    private void recordUserInteraction(String str, String str2) {
        this.mobilytics.mo358get().recordUserInteractionEvent(this.mobilytics.mo358get().createUserInteractionEvent(str, str2, "VoiceEnrollment", this.callerContext, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9"));
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
        this.mobilytics.mo358get().recordCounter(this.mobilytics.mo358get().createCounter(str, "VoiceEnrollment", this.callerContext, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9"));
        this.mobilytics.mo358get().recordCounter(this.mobilytics.mo358get().createCounter(str, String.format("%s_%s", "VoiceEnrollment", this.callerContext), this.callerContext, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9"));
    }

    public void recordUserClickInteraction(String str) {
        recordUserInteraction(str, "click");
    }

    public void recordUserUtterenceInteraction(String str) {
        recordUserInteraction(str, InteractionType.UTTERANCE);
    }

    public void recordUserViewInteraction(String str) {
        recordUserInteraction(str, InteractionType.PAGE_VIEW);
    }

    public MobilyticsMetricsTimer startTimer(String str) {
        return this.mobilytics.mo358get().createTimer(str, "VoiceEnrollment", this.callerContext, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }
}
