package com.amazon.dee.app.elements.bridges;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.util.MapUtils;
import com.amazon.latencyinfra.LatencyInfra;
import com.dee.app.metrics.MetricsService;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.module.annotations.ReactModule;
import dagger.Lazy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
@ReactModule(name = "Metrics")
/* loaded from: classes12.dex */
public class MetricsModule extends ReactContextBaseJavaModule {
    private static final int MILLISECONDS_IN_SECONDS = 1000;
    private static final String MODULE_NAME = "ElementsMetricsBridge";
    private static final String TAG = Log.tag(MetricsModule.class);
    private static final String TTCF_TAG = Log.tag("[PERF PROFILE]");
    private final Context context;
    private final EventBus eventBus;
    private final IdentityService identityService;
    private final LatencyInfra latencyInfra;
    private final MetricsService metricsService;
    private final Lazy<Mobilytics> mobilytics;
    private final Lazy<MobilyticsEventFactory> mobilyticsEventFactory;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface MobilyticsEvent {
        public static final String INTERACTION = "MobilyticsInteraction";
        public static final String OPERATIONAL = "MobilyticsOperational";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface TimelineState {
        public static final String ABORT = "abort";
        public static final String PAUSE = "pause";
        public static final String RESUME = "resume";
        public static final String START = "start";
        public static final String STOP = "stop";
    }

    public MetricsModule(ReactApplicationContext reactApplicationContext, MetricsService metricsService, IdentityService identityService, Lazy<Mobilytics> lazy, Lazy<MobilyticsEventFactory> lazy2, EventBus eventBus, LatencyInfra latencyInfra) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
        this.metricsService = metricsService;
        this.identityService = identityService;
        this.mobilytics = lazy;
        this.mobilyticsEventFactory = lazy2;
        this.eventBus = eventBus;
        this.latencyInfra = latencyInfra;
    }

    private boolean isInvalidTimeline(String str, String str2) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2);
    }

    private void logTTCFMetrics(String str, String str2, HashMap<String, Object> hashMap) {
        if (str.matches(".*core.(route|page-loading).time|.*channels-entertainment.recentlyPlayedCarouselLoadTime.mobilytics-operational")) {
            String str3 = TTCF_TAG;
            Log.i(str3, str2 + " " + str + " " + hashMap.get("TimerValue"));
        }
    }

    private void updateTime(Map<String, Object> map, String str) {
        Double asDouble = MapUtils.getAsDouble(map, str);
        if (asDouble == null) {
            return;
        }
        map.put(str, Long.valueOf((long) (asDouble.doubleValue() * 1000.0d)));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void manageTimeline(String str, String str2, String str3, String str4, Promise promise) {
        if (isInvalidTimeline(str2, str3)) {
            promise.reject((String) null, "timelineName or timelineNamespace cannot be empty");
            return;
        }
        Mobilytics mo358get = this.mobilytics.mo358get();
        char c = 65535;
        switch (str.hashCode()) {
            case -934426579:
                if (str.equals("resume")) {
                    c = 3;
                    break;
                }
                break;
            case 3540994:
                if (str.equals("stop")) {
                    c = 1;
                    break;
                }
                break;
            case 92611376:
                if (str.equals(TimelineState.ABORT)) {
                    c = 4;
                    break;
                }
                break;
            case 106440182:
                if (str.equals("pause")) {
                    c = 2;
                    break;
                }
                break;
            case 109757538:
                if (str.equals("start")) {
                    c = 0;
                    break;
                }
                break;
        }
        if (c == 0) {
            mo358get.startTimeline(str2, str3);
            promise.resolve(String.format("Timeline with name: %s started", str2));
        } else if (c == 1) {
            mo358get.stopTimeline(str2, str3);
            promise.resolve(String.format("Timeline with name: %s stopped", str2));
        } else if (c == 2) {
            mo358get.pauseTimeline(str2, str3);
            promise.resolve(String.format("Timeline with name: %s paused", str2));
        } else if (c == 3) {
            mo358get.resumeTimeline(str2, str3);
            promise.resolve(String.format("Timeline with name: %s resumed", str2));
        } else if (c != 4) {
            Log.e(TAG, "Timeline state not present");
            promise.reject((String) null, "Timeline state not present");
        } else {
            mo358get.abortTimeline(str2, str3, str4);
            promise.resolve(String.format("Timeline with name: %s aborted", str2));
        }
    }

    @ReactMethod
    public void record(String str, String str2, String str3, ReadableMap readableMap) {
        String.format("type: \"%s\", eventName: \"%s\", component: \"%s\"", str, str2, str3);
        if (str.equalsIgnoreCase("MobilyticsInteraction") && readableMap != null && readableMap.hasKey("interactionMetrics") && readableMap.getType("interactionMetrics").equals(ReadableType.Boolean) && readableMap.getBoolean("interactionMetrics")) {
            this.mobilytics.mo358get().recordUserInteractionEvent(this.mobilyticsEventFactory.mo358get().getUserInteractionEvent(str2, str3, readableMap.toHashMap()));
            return;
        }
        HashMap<String, Object> hashMap = null;
        if (readableMap != null) {
            hashMap = readableMap.toHashMap();
        }
        updateTime(hashMap, "TimerValue");
        updateTime(hashMap, "EventTimestamp");
        this.metricsService.recordCustom(str, str2, str3, hashMap);
        logTTCFMetrics(str2, str3, hashMap);
    }

    @ReactMethod
    public void recordWithTimeline(String str, String str2, String str3, String str4, String str5, ReadableMap readableMap, Promise promise) {
        String.format("eventType: \"%s\", eventName: \"%s\", component: \"%s\"", str, str2, str3);
        if (readableMap == null) {
            promise.reject((String) null, "eventData cannot be empty");
        } else if (isInvalidTimeline(str4, str5)) {
            promise.reject((String) null, "timelineName or timelineNamespace cannot be empty");
        } else {
            MobilyticsEventFactory mo358get = this.mobilyticsEventFactory.mo358get();
            Mobilytics mo358get2 = this.mobilytics.mo358get();
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1311117993) {
                if (hashCode == 403281815 && str.equals("MobilyticsInteraction")) {
                    c = 1;
                }
            } else if (str.equals("MobilyticsOperational")) {
                c = 0;
            }
            if (c == 0) {
                MobilyticsOperationalEvent operationalEvent = mo358get.getOperationalEvent(str2, str3, readableMap.toHashMap());
                if (operationalEvent != null) {
                    mo358get2.recordEvent(operationalEvent, str4, str5);
                    promise.resolve(String.format("Recorded metric with eventName %s", str2));
                    return;
                }
                Log.e(TAG, "Could not create mobilytics event");
                promise.reject((String) null, "Could not create mobilytics event");
            } else if (c != 1) {
                Log.e(TAG, String.format("Invalid mobilytics event type: %s", str));
                promise.reject((String) null, String.format("Invalid mobilytics event type: %s", str));
            } else {
                MobilyticsUserInteractionEvent userInteractionEvent = mo358get.getUserInteractionEvent(str2, str3, readableMap.toHashMap());
                if (userInteractionEvent != null) {
                    mo358get2.recordEvent(userInteractionEvent, str4, str5);
                    promise.resolve(String.format("Recorded metric with eventName %s", str2));
                    return;
                }
                Log.e(TAG, "Could not create mobilytics event");
                promise.reject((String) null, "Could not create mobilytics event");
            }
        }
    }
}
