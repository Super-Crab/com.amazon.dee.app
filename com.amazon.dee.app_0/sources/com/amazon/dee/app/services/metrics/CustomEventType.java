package com.amazon.dee.app.services.metrics;

import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
/* loaded from: classes12.dex */
public enum CustomEventType {
    OCCURRENCE(new String[]{AlexaMetricsConstants.EventTypes.EVENT_TYPE_OCCURRENCE, MetricType.AVAILABILITY.toString()}),
    ERROR(new String[]{AlexaMetricsConstants.EventTypes.EVENT_TYPE_FATAL, MetricType.FAULT.toString()}),
    DIAGNOSTIC(new String[]{"Event", "MobilyticsOperational", OperationalEventType.DIAGNOSTIC}),
    TIMER(new String[]{OperationalEventType.TIMER, MetricType.TIME.toString()}),
    OPERATIONAL(new String[]{OperationalEventType.COUNTER, "data", "error", MetricType.COUNT.toString()}),
    INTERACTION(new String[]{"Engagement", "Impression", "MobilyticsInteraction", InteractionType.APP_START, "click", InteractionType.COMMS_CALL, InteractionType.COMMS_MESSAGE, InteractionType.DEEP_LINK_CLICK, InteractionType.LONG_PRESS, InteractionType.PAGE_VIEW, InteractionType.SLIDER, InteractionType.UTTERANCE, "view"}),
    UNKNOWN(new String[0]);
    
    public String[] events;

    CustomEventType(String[] strArr) {
        this.events = strArr;
    }

    public static CustomEventType getEventType(String str) {
        CustomEventType[] values;
        if (AlexaMetricsConstants.EventTypes.EVENT_TYPE_ENGAGEMENT_PMET.equals(str)) {
            return DIAGNOSTIC;
        }
        for (CustomEventType customEventType : values()) {
            if (hasEventType(customEventType, str)) {
                return customEventType;
            }
        }
        return UNKNOWN;
    }

    private static boolean hasEventType(CustomEventType customEventType, String str) {
        for (String str2 : customEventType.events) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
