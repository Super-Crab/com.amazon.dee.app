package com.amazon.alexa.smarthomecameras.service;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class CamerasMobilyticsService {
    private final Mobilytics mobilytics;

    public CamerasMobilyticsService(Mobilytics mobilytics) {
        this.mobilytics = mobilytics;
    }

    public void recordAlvError(String str, String str2) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return;
        }
        recordOperationalEvent(MobilyticsConstants.ALVMetrics.ERROR_PREFIX + str + "_" + str2);
    }

    public void recordEngagementEvent(String str, String str2) {
        this.mobilytics.recordUserInteractionEvent(this.mobilytics.createUserInteractionEvent(str, str2, MobilyticsConstants.COMPONENT, MobilyticsConstants.SUBCOMPONENT));
    }

    public void recordEngagementEventWithProvider(String str, String str2, String str3) {
        MobilyticsUserInteractionEvent createUserInteractionEvent = this.mobilytics.createUserInteractionEvent(GeneratedOutlineSupport1.outline75(str, "_", str3), str2, MobilyticsConstants.COMPONENT, MobilyticsConstants.SUBCOMPONENT);
        createUserInteractionEvent.setContentProvider(str3);
        this.mobilytics.recordUserInteractionEvent(createUserInteractionEvent);
    }

    public void recordNetworkError() {
        recordOperationalEvent("AppLV_error_network");
    }

    public void recordOperationalEvent(String str) {
        MobilyticsMetricsCounter createCounter = this.mobilytics.createCounter(str, MobilyticsConstants.COMPONENT, MobilyticsConstants.SUBCOMPONENT);
        createCounter.incrementCounter();
        this.mobilytics.recordCounter(createCounter);
    }

    public void recordOperationalEventWithProvider(String str, String str2) {
        if (str2.equalsIgnoreCase("amazon")) {
            recordOperationalEvent(str);
            return;
        }
        MobilyticsMetricsCounter createCounter = this.mobilytics.createCounter(GeneratedOutlineSupport1.outline75(str, "_", str2), MobilyticsConstants.COMPONENT, MobilyticsConstants.SUBCOMPONENT);
        createCounter.incrementCounter();
        createCounter.setContentProvider(str2);
        this.mobilytics.recordCounter(createCounter);
    }

    public void recordRtcError(String str) {
        recordOperationalEvent("AppLV_error_rtc_" + str);
    }

    public void recordTimedOperationalEvent(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        if (mobilyticsMetricsTimer != null) {
            mobilyticsMetricsTimer.finishTimer();
            this.mobilytics.recordTimer(mobilyticsMetricsTimer);
        }
    }

    public MobilyticsMetricsTimer startTimedOperationalEvent(String str) {
        return this.mobilytics.createTimer(str, MobilyticsConstants.COMPONENT, MobilyticsConstants.SUBCOMPONENT);
    }

    public MobilyticsMetricsTimer startTimedOperationalEventWithProvider(String str, String str2) {
        return startTimedOperationalEvent(str + "_" + str2);
    }
}
