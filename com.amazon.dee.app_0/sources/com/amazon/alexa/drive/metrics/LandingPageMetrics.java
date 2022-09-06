package com.amazon.alexa.drive.metrics;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes7.dex */
public class LandingPageMetrics {
    private static final String COMPONENT_NAME = "DriveMode";
    private static final String TAG = "LandingPageMetrics";
    private final LazyComponent<Mobilytics> mMobilytics = ComponentRegistry.getInstance().getLazy(Mobilytics.class);
    private final Map<String, MobilyticsMetricsTimer> mTimerMap = new ConcurrentHashMap();
    private final Map<String, MobilyticsMetricsCounter> mCounterMap = new ConcurrentHashMap();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface AutoModeType {
        public static final String ACCESSORY = "Accessory";
        public static final String AUTOBLUETOOTH = "AutoBluetooth";
        public static final String DEFAULT = "Default";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface Channel {
        public static final String COMMS = "Comms";
        public static final String DEVICES = "Devices";
        public static final String ENTERTAINMENT = "Entertainment";
        public static final String HOME = "Home";
        public static final String NAVIGATION = "Navigation";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface MetricNames {
        public static final String LANDINGPAGE_AUTOMODETYPE_SWITCHTO_CHANNEL = "LandingPage_%sSwitchTo%s";
        public static final String LANDINGPAGE_CHANNEL_SESSIONTIMERENDED = "LandingPage_%sSessionTimerEnded";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface SubComponentType {
        public static final String LANDINGPAGE = "LandingPage";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface TimerNames {
        public static final String CHANNEL_TIMER = "LandingPage_%sTimer";
    }

    private void createTimer(String str, String str2) {
        if (this.mMobilytics.mo10268get() == null) {
            return;
        }
        this.mTimerMap.put(str, this.mMobilytics.mo10268get().createTimer(str, "DriveMode", str2, OwnerIdentifier.ALEXA_APP_AUTOMOTIVE_OTHER));
    }

    private void logMetric(String str, String str2) {
        MobilyticsMetricsCounter createCounter;
        if (this.mMobilytics.mo10268get() == null || (createCounter = this.mMobilytics.mo10268get().createCounter(str, "DriveMode", str2, OwnerIdentifier.ALEXA_APP_AUTOMOTIVE_OTHER)) == null) {
            return;
        }
        createCounter.incrementCounter();
        this.mMobilytics.mo10268get().recordCounter(createCounter);
    }

    private void removeTimer(String str) {
        MobilyticsMetricsTimer remove;
        if (this.mMobilytics.mo10268get() == null || (remove = this.mTimerMap.remove(str)) == null) {
            return;
        }
        remove.finishTimer();
    }

    public MobilyticsMetricsCounter createCounter(String str, String str2) {
        if (this.mMobilytics.mo10268get() == null) {
            return null;
        }
        MobilyticsMetricsCounter createCounter = this.mMobilytics.mo10268get().createCounter(str, "DriveMode", str2, OwnerIdentifier.ALEXA_APP_AUTOMOTIVE_OTHER);
        this.mCounterMap.put(str, createCounter);
        return createCounter;
    }

    public void logSessionTimerEndedWithTimers(String str) {
        logMetric(String.format(MetricNames.LANDINGPAGE_CHANNEL_SESSIONTIMERENDED, str), SubComponentType.LANDINGPAGE);
        recordTimer(String.format(TimerNames.CHANNEL_TIMER, str));
    }

    public void logSwitchToWithTimers(String str, String str2) {
        logMetric(String.format(MetricNames.LANDINGPAGE_AUTOMODETYPE_SWITCHTO_CHANNEL, str, str2), SubComponentType.LANDINGPAGE);
        createTimer(String.format(TimerNames.CHANNEL_TIMER, str2), SubComponentType.LANDINGPAGE);
    }

    public void recordCounter(String str) {
        if (this.mMobilytics.mo10268get() == null) {
            return;
        }
        MobilyticsMetricsCounter remove = this.mCounterMap.remove(str);
        if (remove == null) {
            GeneratedOutlineSupport1.outline158("Tried to record a counter that does not exist yet: ", str);
        } else {
            this.mMobilytics.mo10268get().recordCounter(remove);
        }
    }

    public void recordTimer(String str) {
        if (this.mMobilytics.mo10268get() == null) {
            return;
        }
        MobilyticsMetricsTimer remove = this.mTimerMap.remove(str);
        if (remove == null) {
            GeneratedOutlineSupport1.outline158("Tried to record a timer that does not exist yet: ", str);
            return;
        }
        remove.finishTimer();
        this.mMobilytics.mo10268get().recordTimer(remove);
    }
}
