package com.amazon.alexa.mobilytics.timeline;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public final class TimelineExceptionLogger {
    private static final String ILLEGAL_STATE_EXCEPTION = "IllegalStateException";
    private static final String INVALID_INPUT = "InvalidInputException";
    private static final String INVALID_STATE_TRANSITION_EXCEPTION = "InvalidStateTransitionException";
    private static final String LOG_TAG = "TimelineExceptionLogger";
    public static final String NO_SUCH_TIMELINE_EXCEPTION = "NoSuchTimelineException";
    public static final String PARAM_MOBILYTICS_EVENT = "Event";
    public static final String PARAM_TIMELINE_ABORT_REASON = "AbortReason";
    public static final String PARAM_TIMELINE_NAME = "TimelineName";
    public static final String PARAM_TIMELINE_NAMESPACE = "TimelineNamespace";
    public static final String TIMELINE_ALREADY_EXISTS_EXCEPTION = "TimelineAlreadyExistsException";

    private TimelineExceptionLogger() {
    }

    public static void logException(String str, String str2, String str3) {
        String str4 = LOG_TAG;
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("\n", str, "\nTimeline name: ", str2, "\nTimeline namespace: ");
        outline116.append(str3);
        Log.e(str4, outline116.toString());
    }

    public static void logIllegalStateToRecord(MobilyticsEvent mobilyticsEvent, TimelineEvent timelineEvent) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\nIllegalStateException: Event not recordable on timeline with current state.");
        if (mobilyticsEvent != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("\nEvent name: ");
            outline1072.append(mobilyticsEvent.getEventName());
            outline107.append(outline1072.toString());
        }
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("\nTimeline name: ");
        outline1073.append(timelineEvent.timelineName());
        outline107.append(outline1073.toString());
        outline107.append("\nTimeline namespace: " + timelineEvent.timelineNamespace());
        outline107.append("\nTimeline state: " + timelineEvent.stateString());
        Log.e(LOG_TAG, outline107.toString());
    }

    public static void logInvalidInput(@NonNull String str) {
        String str2 = LOG_TAG;
        Log.e(str2, "InvalidInputException: " + str + " is required and can't be null or empty.");
    }

    public static void logInvalidStateTransition(String str, TimelineEvent timelineEvent) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\nInvalidStateTransitionException:");
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("\nTimeline name: ");
        outline1072.append(timelineEvent.timelineName());
        outline107.append(outline1072.toString());
        outline107.append("\nTimeline namespace: " + timelineEvent.timelineNamespace());
        outline107.append("\nTimeline current state: " + timelineEvent.stateString());
        outline107.append("\nTimeline target state: " + str);
        Log.e(LOG_TAG, outline107.toString());
    }
}
