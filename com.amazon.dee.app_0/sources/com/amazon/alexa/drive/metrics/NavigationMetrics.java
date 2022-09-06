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
public class NavigationMetrics {
    private static final String COMPONENT_NAME = "DriveMode";
    private static final String TAG = "NavigationMetrics";
    private final LazyComponent<Mobilytics> mMobilytics = ComponentRegistry.getInstance().getLazy(Mobilytics.class);
    private final Map<String, MobilyticsMetricsTimer> mTimerMap = new ConcurrentHashMap();
    private final Map<String, MobilyticsMetricsCounter> mCounterMap = new ConcurrentHashMap();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface AlsApiInterface {
        public static final String REPORTLOCATIONS = "ReportLocations";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface ApiStatus {
        public static final String FAILURE = "Failure";
        public static final String SUCCESS = "Success";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface AutoModeType {
        public static final String ACCESSORY = "Accessory";
        public static final String AUTOBLUETOOTH = "AutoBluetooth";
        public static final String DEFAULT = "Default";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface AwtsApiInterface {
        public static final String GETCOMMUTEDESTINATION = "GetCommuteDestination";
        public static final String GETCOMMUTEDETAILS = "GetCommuteDetails";
        public static final String SENDCOMMUTENOTIFICATION = "SendCommuteNotification";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface CardType {
        public static final String HOME = "Home";
        public static final String WORK = "Work";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface MetricNames {
        public static final String NAVIGATION_AUTOMODETYPE_HOMEBUTTONSELECTED = "Navigation_%sHomeButtonSelected";
        public static final String NAVIGATION_AUTOMODETYPE_HOMELANDINGCARDSELECTED = "Navigation_%sHomeLandingCardSelected";
        public static final String NAVIGATION_AUTOMODETYPE_STOREDLOCATIONSELECTED = "Navigation_%sStoredLocationSelected";
        public static final String NAVIGATION_AUTOMODETYPE_WORKBUTTONSELECTED = "Navigation_%sWorkButtonSelected";
        public static final String NAVIGATION_AUTOMODETYPE_WORKLANDINGCARDSELECTED = "Navigation_%sWorkLandingCardSelected";
        public static final String NAVIGATION_CABAPI_APISTATUS = "Navigation_CABApi%s";
        public static final String NAVIGATION_HOMEBUTTONDISPLAYED = "Navigation_HomeButtonDisplayed";
        public static final String NAVIGATION_HOMELANDINGCARDDISPLAYED = "Navigation_HomeLandingCardDisplayed";
        public static final String NAVIGATION_STOREDLOCATIONERRORDISPLAYED = "Navigation_StoredLocationErrorDisplayed";
        public static final String NAVIGATION_STOREDLOCATIONSDISPLAYED = "Navigation_StoredLocationsDisplayed";
        public static final String NAVIGATION_STOREDLOCATIONSPAGEDDOWN = "Navigation_StoredLocationsPagedDown";
        public static final String NAVIGATION_WORKBUTTONDISPLAYED = "Navigation_WorkButtonDisplayed";
        public static final String NAVIGATION_WORKLANDINGCARDDISPLAYED = "Navigation_WorkLandingCardDisplayed";
        public static final String TRAFFICNOTIFICATION_ALS_ALSAPIINTERFACE_APISTATUS = "TrafficNotification_Als%s%s";
        public static final String TRAFFICNOTIFICATION_AUTOMODETYPE_CARDTYPE_TRAFFICCARDNAVIGATESELECTED = "TrafficNotification_%s%sTrafficCardNavigateSelected";
        public static final String TRAFFICNOTIFICATION_AUTOMODETYPE_CARDTYPE_TRAFFICCARDSELECTED = "TrafficNotification_%s%sTrafficCardSelected";
        public static final String TRAFFICNOTIFICATION_AWTS_AWTSAPIINTERFACE_APISTATUS = "TrafficNotification_Awts%s%s";
        public static final String TRAFFICNOTIFICATION_CARDTYPE_TRAFFICCARDDISPLAYED = "TrafficNotification_%sTrafficCardDisplayed";
        public static final String TRAFFICNOTIFICATION_CARDTYPE_TRAFFICERRORDISPLAYED = "TrafficNotification_%sTrafficErrorDisplayed";
        public static final String TRAFFICNOTIFICATION_TRAFFICCARDLATENCYMEASURINGENDED = "TrafficNotification_TrafficCardLatencyMeasuringEnded";
        public static final String TRAFFICNOTIFICATION_TRAFFICCARDLATENCYMEASURINGSTARTED = "TrafficNotification_TrafficCardLatencyMeasuringStarted";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface SubComponentType {
        public static final String NAVIGATION = "Navigation";
        public static final String TRAFFICNOTIFICATION = "TrafficNotification";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface TimerNames {
        public static final String TRAFFICCARDLATENCYTIMER = "TrafficNotification_TrafficCardLatencyTimer";
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

    public void logAls(String str, String str2) {
        logMetric(String.format(MetricNames.TRAFFICNOTIFICATION_ALS_ALSAPIINTERFACE_APISTATUS, str, str2), SubComponentType.TRAFFICNOTIFICATION);
    }

    public void logAwts(String str, String str2) {
        logMetric(String.format(MetricNames.TRAFFICNOTIFICATION_AWTS_AWTSAPIINTERFACE_APISTATUS, str, str2), SubComponentType.TRAFFICNOTIFICATION);
    }

    public void logCABApi(String str) {
        logMetric(String.format(MetricNames.NAVIGATION_CABAPI_APISTATUS, str), "Navigation");
    }

    public void logHomeButtonDisplayed() {
        logMetric(MetricNames.NAVIGATION_HOMEBUTTONDISPLAYED, "Navigation");
    }

    public void logHomeButtonSelected(String str) {
        logMetric(String.format(MetricNames.NAVIGATION_AUTOMODETYPE_HOMEBUTTONSELECTED, str), "Navigation");
    }

    public void logHomeLandingCardDisplayed() {
        logMetric(MetricNames.NAVIGATION_HOMELANDINGCARDDISPLAYED, "Navigation");
    }

    public void logHomeLandingCardSelected(String str) {
        logMetric(String.format(MetricNames.NAVIGATION_AUTOMODETYPE_HOMELANDINGCARDSELECTED, str), "Navigation");
    }

    public void logStoredLocationErrorDisplayed() {
        logMetric(MetricNames.NAVIGATION_STOREDLOCATIONERRORDISPLAYED, "Navigation");
    }

    public void logStoredLocationSelected(String str) {
        logMetric(String.format(MetricNames.NAVIGATION_AUTOMODETYPE_STOREDLOCATIONSELECTED, str), "Navigation");
    }

    public void logStoredLocationsDisplayed() {
        logMetric(MetricNames.NAVIGATION_STOREDLOCATIONSDISPLAYED, "Navigation");
    }

    public void logStoredLocationsPagedDown() {
        logMetric(MetricNames.NAVIGATION_STOREDLOCATIONSPAGEDDOWN, "Navigation");
    }

    public void logTrafficCardDisplayed(String str) {
        logMetric(String.format(MetricNames.TRAFFICNOTIFICATION_CARDTYPE_TRAFFICCARDDISPLAYED, str), SubComponentType.TRAFFICNOTIFICATION);
    }

    public void logTrafficCardLatencyMeasuringEndedWithTimers() {
        logMetric(MetricNames.TRAFFICNOTIFICATION_TRAFFICCARDLATENCYMEASURINGENDED, SubComponentType.TRAFFICNOTIFICATION);
        recordTimer(TimerNames.TRAFFICCARDLATENCYTIMER);
    }

    public void logTrafficCardLatencyMeasuringStartedWithTimers() {
        logMetric(MetricNames.TRAFFICNOTIFICATION_TRAFFICCARDLATENCYMEASURINGSTARTED, SubComponentType.TRAFFICNOTIFICATION);
        createTimer(TimerNames.TRAFFICCARDLATENCYTIMER, SubComponentType.TRAFFICNOTIFICATION);
    }

    public void logTrafficCardNavigateSelected(String str, String str2) {
        logMetric(String.format(MetricNames.TRAFFICNOTIFICATION_AUTOMODETYPE_CARDTYPE_TRAFFICCARDNAVIGATESELECTED, str, str2), SubComponentType.TRAFFICNOTIFICATION);
    }

    public void logTrafficCardSelected(String str, String str2) {
        logMetric(String.format(MetricNames.TRAFFICNOTIFICATION_AUTOMODETYPE_CARDTYPE_TRAFFICCARDSELECTED, str, str2), SubComponentType.TRAFFICNOTIFICATION);
    }

    public void logTrafficErrorDisplayed(String str) {
        logMetric(String.format(MetricNames.TRAFFICNOTIFICATION_CARDTYPE_TRAFFICERRORDISPLAYED, str), SubComponentType.TRAFFICNOTIFICATION);
    }

    public void logWorkButtonDisplayed() {
        logMetric(MetricNames.NAVIGATION_WORKBUTTONDISPLAYED, "Navigation");
    }

    public void logWorkButtonSelected(String str) {
        logMetric(String.format(MetricNames.NAVIGATION_AUTOMODETYPE_WORKBUTTONSELECTED, str), "Navigation");
    }

    public void logWorkLandingCardDisplayed() {
        logMetric(MetricNames.NAVIGATION_WORKLANDINGCARDDISPLAYED, "Navigation");
    }

    public void logWorkLandingCardSelected(String str) {
        logMetric(String.format(MetricNames.NAVIGATION_AUTOMODETYPE_WORKLANDINGCARDSELECTED, str), "Navigation");
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
