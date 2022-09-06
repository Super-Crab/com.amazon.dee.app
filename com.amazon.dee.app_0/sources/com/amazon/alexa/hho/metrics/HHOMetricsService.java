package com.amazon.alexa.hho.metrics;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* loaded from: classes8.dex */
public class HHOMetricsService {
    private final LazyComponent<Mobilytics> mobilytics;

    /* loaded from: classes8.dex */
    public class Timer {
        private final MobilyticsMetricsTimer originalTimer;

        Timer(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
            this.originalTimer = mobilyticsMetricsTimer;
        }

        public void setEventName(String str) {
            this.originalTimer.setEventName(str);
        }
    }

    public HHOMetricsService(LazyComponent<Mobilytics> lazyComponent) {
        this.mobilytics = lazyComponent;
    }

    public Timer createTimer(String str, String str2, String str3) {
        return new Timer(this.mobilytics.mo10268get().createTimer(str, str2, str3, OwnerIdentifier.HHO_MOBILE));
    }

    public void recordOperationalEvent(String str, String str2, String str3, String str4) {
        this.mobilytics.mo10268get().recordOperationalEvent(this.mobilytics.mo10268get().createOperationalEvent(str, str2, str3, str4, OwnerIdentifier.HHO_MOBILE));
    }

    public void recordTimer(Timer timer) {
        this.mobilytics.mo10268get().recordTimer(timer.originalTimer);
    }

    public void recordOperationalEvent(String str, String str2, String str3) {
        this.mobilytics.mo10268get().recordOperationalEvent(str, str2, str3, OwnerIdentifier.HHO_MOBILE);
    }
}
