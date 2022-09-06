package com.amazon.alexa.redesign.utils;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.redesign.HomeContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class HomeOEInteractor implements HomeContract.OEInteractor {
    public static final String CACHE_CLEAR = "CacheClear";
    public static final String CACHE_EXCEPTION = ".CacheException";
    public static final String CACHE_READ = "CacheRead";
    public static final String CACHE_WRITE = "CacheWrite";
    public static final String CORAL_SERVICE_EXCEPTION = ".CoralServiceException";
    public static final String DOT = ".";
    public static final String ENTER_HOME = ".enterHome";
    public static final String ERROR_PAGE_VIEW = ".errorPageView";
    public static final String FAULT = ".fault";
    public static final String GET_FEED = "GetFeed";
    public static final String GET_WEATHER = "GetWeather";
    public static final String MALFORMED = ".malformed";
    public static final String MALFORMED_ACTION = ".malformedAction";
    public static final String MALFORMED_VIEW = ".malformedView";
    public static final String METRIC_PREFIX = "jasper-home-native";
    public static final String MISSING_METRICS = ".missingMetrics";
    public static final String OUTAGE = ".outage";
    public static final String OUTAGE_OCCURRENCE = ".outageOccurrence";
    public static final String PULL_TO_REFRESH = ".pullToRefresh";
    private static final String RN_RENDER_REQUEST = "HomeChannelReactNativeRenderRequest";
    public static final String ROUTE_INVALID = ".routeInvalid";
    public static final String ROUTE_NOT_MATCHED = ".routeNotMatched";
    public static final String ROUTE_NULL = ".routeNull";
    public static final String SUBCOMPONENT = "Home";
    public static final String TIMER = ".time";
    public static final String UNKNOWN_TEMPLATE_TYPE = "UnknownTemplateType";
    private final Mobilytics mobilytics;

    public HomeOEInteractor(Mobilytics mobilytics) {
        this.mobilytics = mobilytics;
    }

    @VisibleForTesting
    MobilyticsOperationalEvent createOE(String str, String str2) {
        MobilyticsOperationalEvent createOperationalEvent = this.mobilytics.createOperationalEvent(str, "data", HomeMetricsRecorder.APP_COMPONENT, "Home", OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
        createOperationalEvent.setContentType(str2);
        return createOperationalEvent;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.OEInteractor
    public void recordBadRouteOccurrence(String str, String str2) {
        this.mobilytics.recordOccurrence(GeneratedOutlineSupport1.outline72("jasper-home-native.malformedAction", str2), true, HomeMetricsRecorder.APP_COMPONENT, str, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.OEInteractor
    public void recordEnterHome() {
        this.mobilytics.recordOccurrence("jasper-home-native.enterHome", true, HomeMetricsRecorder.APP_COMPONENT, ENTER_HOME, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.OEInteractor
    public void recordErrorPageView() {
        this.mobilytics.recordOccurrence("jasper-home-native.errorPageView", true, HomeMetricsRecorder.APP_COMPONENT, ERROR_PAGE_VIEW, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.OEInteractor
    public void recordMalformedDataOccurrence(String str) {
        this.mobilytics.recordOccurrence("jasper-home-native.malformed", true, HomeMetricsRecorder.APP_COMPONENT, str, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.OEInteractor
    public void recordMalformedViewOccurrence(String str, String str2) {
        this.mobilytics.recordOccurrence("jasper-home-native.malformedView", true, HomeMetricsRecorder.APP_COMPONENT, GeneratedOutlineSupport1.outline75(str2, ":", str), OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.OEInteractor
    public void recordPullToRefresh() {
        this.mobilytics.recordOccurrence("jasper-home-native.pullToRefresh", true, HomeMetricsRecorder.APP_COMPONENT, PULL_TO_REFRESH, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.OEInteractor
    public void recordReactCardRenderRequest(String str) {
        this.mobilytics.recordOperationalEvent(createOE(RN_RENDER_REQUEST, str));
    }
}
