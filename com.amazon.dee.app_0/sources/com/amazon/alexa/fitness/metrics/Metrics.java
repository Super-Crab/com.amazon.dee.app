package com.amazon.alexa.fitness.metrics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: AggregatedMetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/Metrics;", "", "()V", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Metrics {
    @NotNull
    public static final String ACCESSORY_START_COMMAND_LATENCY = "accessoryStartCommandLatency";
    @NotNull
    public static final String BACKGROUNDED = "backgrounded";
    @NotNull
    public static final String BACKGROUNDED_DURATION = "backgroundedDuration";
    @NotNull
    public static final String BACKGROUNDED_PERCENTAGE = "backgroundedPercent";
    @NotNull
    public static final String CALORIES = "kiloCalories";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String DISCONNECT = "disconnect";
    @NotNull
    public static final String DISCONNECTED_DURATION = "disconnectedDuration";
    @NotNull
    public static final String DISCONNECTED_PERCENTAGE = "disconnectedDurationPercent";
    @NotNull
    public static final String DISCONNECTED_STOP = "bluetoothWasDisconnectedWhenStopped";
    @NotNull
    public static final String DISTANCE_IN_FEET = "distanceInFeet";
    @NotNull
    public static final String EVENT_SEND_DURATION = "eventSendDuration";
    @NotNull
    public static final String EVENT_SEND_FAILURE = "eventSendFailure";
    @NotNull
    public static final String EVENT_SEND_SUCCESS = "eventSendSuccess";
    @NotNull
    public static final String FOREGROUNDED = "foregrounded";
    @NotNull
    public static final String FOREGROUNDED_DURATION = "foregroundedDuration";
    @NotNull
    public static final String FOREGROUNDED_PERCENTAGE = "foregroundedDurationPercent";
    @NotNull
    public static final String GUI_PAUSE = "guiPause";
    @NotNull
    public static final String GUI_RESUME = "guiResume";
    @NotNull
    public static final String GUI_START = "guiStart";
    @NotNull
    public static final String GUI_STOP = "guiStop";
    @NotNull
    public static final String LOCATION_SAMPLE_RAW_COUNT = "locationSampleRawCount";
    @NotNull
    public static final String MESSAGE_COUNT = "messageCount";
    @NotNull
    public static final String PROCESSING_DURATION = "processingDuration";
    @NotNull
    public static final String PROCESSING_PERCENTAGE = "processingDurationPercent";
    @NotNull
    public static final String RECONNECT = "reconnect";
    @NotNull
    public static final String RECOVERY_COUNT = "recoveringCount";
    @NotNull
    public static final String RECOVERY_FAILED_STALE = "recoveringFailedStale";
    @NotNull
    public static final String RECOVER_FAILED_INTERNAL = "recoveringFailedInternal";
    @NotNull
    public static final String RECOVER_FAILED_NO_DATA = "recoveringFailedNoData";
    @NotNull
    public static final String SENSOR_PAUSE = "sensorPause";
    @NotNull
    public static final String SENSOR_RESUME = "sensorResume";
    @NotNull
    public static final String SENSOR_STOP = "sensorStop";
    @NotNull
    public static final String SESSION_COMPLETE = "sessionComplete";
    @NotNull
    public static final String SESSION_COMPLETE_WITH_ROUTE = "sessionCompleteWithRoute";
    @NotNull
    public static final String SESSION_DURATION = "sessionDuration";
    @NotNull
    public static final String SESSION_EXISTENCE_DURATION = "sessionExistenceDuration";
    @NotNull
    public static final String SPEED = "speed";
    @NotNull
    public static final String START_WITH_FULL_FITNESS_PROFILE = "startWithFullFitnessProfile";
    @NotNull
    public static final String START_WITH_PARTIAL_FITNESS_PROFILE = "startWithPartialFitnessProfile";
    @NotNull
    public static final String STEPS = "steps";
    @NotNull
    public static final String STOP_ACCESSORY = "stopAccessoryDisconnect";
    @NotNull
    public static final String STOP_NO_SAMPLE = "stopNoSample";
    @NotNull
    public static final String STOP_TIMEOUT = "stopTimeout";
    @NotNull
    public static final String VUI_PAUSE = "vuiPause";
    @NotNull
    public static final String VUI_RESUME = "vuiResume";
    @NotNull
    public static final String VUI_START = "vuiStart";
    @NotNull
    public static final String VUI_STOP = "vuiStop";
    @NotNull
    public static final String WAS_RECOVERED = "wasRecovered";

    /* compiled from: AggregatedMetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b0\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/Metrics$Companion;", "", "()V", "ACCESSORY_START_COMMAND_LATENCY", "", "BACKGROUNDED", "BACKGROUNDED_DURATION", "BACKGROUNDED_PERCENTAGE", "CALORIES", "DISCONNECT", "DISCONNECTED_DURATION", "DISCONNECTED_PERCENTAGE", "DISCONNECTED_STOP", "DISTANCE_IN_FEET", "EVENT_SEND_DURATION", "EVENT_SEND_FAILURE", "EVENT_SEND_SUCCESS", "FOREGROUNDED", "FOREGROUNDED_DURATION", "FOREGROUNDED_PERCENTAGE", "GUI_PAUSE", "GUI_RESUME", "GUI_START", "GUI_STOP", "LOCATION_SAMPLE_RAW_COUNT", "MESSAGE_COUNT", "PROCESSING_DURATION", "PROCESSING_PERCENTAGE", "RECONNECT", "RECOVERY_COUNT", "RECOVERY_FAILED_STALE", "RECOVER_FAILED_INTERNAL", "RECOVER_FAILED_NO_DATA", "SENSOR_PAUSE", "SENSOR_RESUME", "SENSOR_STOP", "SESSION_COMPLETE", "SESSION_COMPLETE_WITH_ROUTE", "SESSION_DURATION", "SESSION_EXISTENCE_DURATION", "SPEED", "START_WITH_FULL_FITNESS_PROFILE", "START_WITH_PARTIAL_FITNESS_PROFILE", "STEPS", "STOP_ACCESSORY", "STOP_NO_SAMPLE", "STOP_TIMEOUT", "VUI_PAUSE", "VUI_RESUME", "VUI_START", "VUI_STOP", "WAS_RECOVERED", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
