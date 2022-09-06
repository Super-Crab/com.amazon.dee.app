package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.utils.MetricComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: AggregatedMetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/AggregatedMetricsConstants;", "", "()V", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AggregatedMetricsConstants {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final MetricComponent GUI_PAUSE = new MetricComponent(Metrics.GUI_PAUSE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent GUI_RESUME = new MetricComponent(Metrics.GUI_RESUME, Components.AFX, "session");
    @NotNull
    private static final MetricComponent GUI_START = new MetricComponent(Metrics.GUI_START, Components.AFX, "session");
    @NotNull
    private static final MetricComponent GUI_STOP = new MetricComponent(Metrics.GUI_STOP, Components.AFX, "session");
    @NotNull
    private static final MetricComponent VUI_PAUSE = new MetricComponent(Metrics.VUI_PAUSE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent VUI_RESUME = new MetricComponent(Metrics.VUI_RESUME, Components.AFX, "session");
    @NotNull
    private static final MetricComponent VUI_START = new MetricComponent(Metrics.VUI_START, Components.AFX, "session");
    @NotNull
    private static final MetricComponent VUI_STOP = new MetricComponent(Metrics.VUI_STOP, Components.AFX, "session");
    @NotNull
    private static final MetricComponent ACCESSORY_START_COMMAND_LATENCY = new MetricComponent(Metrics.ACCESSORY_START_COMMAND_LATENCY, Components.AFX, "session");
    @NotNull
    private static final MetricComponent BACKGROUNDED = new MetricComponent(Metrics.BACKGROUNDED, Components.AFX, "session");
    @NotNull
    private static final MetricComponent BACKGROUNDED_DURATION = new MetricComponent(Metrics.BACKGROUNDED_DURATION, Components.AFX, "session");
    @NotNull
    private static final MetricComponent BACKGROUNDED_PERCENTAGE = new MetricComponent(Metrics.BACKGROUNDED_PERCENTAGE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent CALORIES = new MetricComponent(Metrics.CALORIES, Components.AFX, "session");
    @NotNull
    private static final MetricComponent DISCONNECT = new MetricComponent(Metrics.DISCONNECT, Components.AFX, "session");
    @NotNull
    private static final MetricComponent DISCONNECTED_DURATION = new MetricComponent(Metrics.DISCONNECTED_DURATION, Components.AFX, "session");
    @NotNull
    private static final MetricComponent DISCONNECTED_PERCENTAGE = new MetricComponent(Metrics.DISCONNECTED_PERCENTAGE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent DISCONNECTED_STOP = new MetricComponent(Metrics.DISCONNECTED_STOP, Components.AFX, "session");
    @NotNull
    private static final MetricComponent DISTANCE_IN_FEET = new MetricComponent(Metrics.DISTANCE_IN_FEET, Components.AFX, "session");
    @NotNull
    private static final MetricComponent FOREGROUNDED = new MetricComponent(Metrics.FOREGROUNDED, Components.AFX, "session");
    @NotNull
    private static final MetricComponent FOREGROUNDED_DURATION = new MetricComponent(Metrics.FOREGROUNDED_DURATION, Components.AFX, "session");
    @NotNull
    private static final MetricComponent FOREGROUNDED_PERCENTAGE = new MetricComponent(Metrics.FOREGROUNDED_PERCENTAGE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent LOCATION_SAMPLE_RAW_COUNT = new MetricComponent(Metrics.LOCATION_SAMPLE_RAW_COUNT, Components.AFX, "session");
    @NotNull
    private static final MetricComponent MESSAGE_COUNT = new MetricComponent(Metrics.MESSAGE_COUNT, Components.AFX, "session");
    @NotNull
    private static final MetricComponent PROCESSING_DURATION = new MetricComponent(Metrics.PROCESSING_DURATION, Components.AFX, "session");
    @NotNull
    private static final MetricComponent PROCESSING_PERCENTAGE = new MetricComponent(Metrics.PROCESSING_PERCENTAGE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent RECONNECT = new MetricComponent(Metrics.RECONNECT, Components.AFX, "session");
    @NotNull
    private static final MetricComponent RECOVERY = new MetricComponent(Metrics.WAS_RECOVERED, Components.AFX, "session");
    @NotNull
    private static final MetricComponent SESSION_EXISTENCE_DURATION = new MetricComponent(Metrics.SESSION_EXISTENCE_DURATION, Components.AFX, "session");
    @NotNull
    private static final MetricComponent SESSION_DURATION = new MetricComponent(Metrics.SESSION_DURATION, Components.AFX, "session");
    @NotNull
    private static final MetricComponent SENSOR_PAUSE = new MetricComponent(Metrics.SENSOR_PAUSE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent SENSOR_RESUME = new MetricComponent(Metrics.SENSOR_RESUME, Components.AFX, "session");
    @NotNull
    private static final MetricComponent SENSOR_STOP = new MetricComponent(Metrics.SENSOR_STOP, Components.AFX, "session");
    @NotNull
    private static final MetricComponent SPEED = new MetricComponent("speed", Components.AFX, "session");
    @NotNull
    private static final MetricComponent STEPS = new MetricComponent(Metrics.STEPS, Components.AFX, "session");
    @NotNull
    private static final MetricComponent STOP_TIMEOUT = new MetricComponent(Metrics.STOP_TIMEOUT, Components.AFX, "session");
    @NotNull
    private static final MetricComponent STOP_ACCESSORY = new MetricComponent(Metrics.STOP_ACCESSORY, Components.AFX, "session");
    @NotNull
    private static final MetricComponent STOP_NO_SAMPLE = new MetricComponent(Metrics.STOP_NO_SAMPLE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent RECOVERY_COUNT = new MetricComponent(Metrics.RECOVERY_COUNT, Components.AFX, "session");
    @NotNull
    private static final MetricComponent RECOVER_FAILED_INTERNAL = new MetricComponent(Metrics.RECOVER_FAILED_INTERNAL, Components.AFX, "session");
    @NotNull
    private static final MetricComponent RECOVER_FAILED_NO_DATA = new MetricComponent(Metrics.RECOVER_FAILED_NO_DATA, Components.AFX, "session");
    @NotNull
    private static final MetricComponent RECOVERY_FAILED_STALE = new MetricComponent(Metrics.RECOVERY_FAILED_STALE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent START_WITH_FULL_FITNESS_PROFILE = new MetricComponent(Metrics.START_WITH_FULL_FITNESS_PROFILE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent START_WITH_PARTIAL_FITNESS_PROFILE = new MetricComponent(Metrics.START_WITH_PARTIAL_FITNESS_PROFILE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent SESSION_COMPLETE = new MetricComponent(Metrics.SESSION_COMPLETE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent SESSION_COMPLETE_WITH_ROUTE = new MetricComponent(Metrics.SESSION_COMPLETE_WITH_ROUTE, Components.AFX, "session");
    @NotNull
    private static final MetricComponent AVS_SUCCESS_EVENT_COUNT = new MetricComponent(Metrics.EVENT_SEND_SUCCESS, Components.CAPABILITY_AGENT, SubComponents.EVENT_EMITTER);
    @NotNull
    private static final MetricComponent AVS_FAILURE_EVENT_COUNT = new MetricComponent(Metrics.EVENT_SEND_FAILURE, Components.CAPABILITY_AGENT, SubComponents.EVENT_EMITTER);
    @NotNull
    private static final MetricComponent AVS_EVENT_DURATION = new MetricComponent(Metrics.EVENT_SEND_DURATION, Components.CAPABILITY_AGENT, SubComponents.EVENT_EMITTER);

    /* compiled from: AggregatedMetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\ba\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0011\u0010\u001f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0011\u0010!\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0011\u0010#\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0011\u0010%\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0011\u0010'\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0011\u0010)\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0011\u0010+\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0011\u0010-\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0011\u0010/\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0006R\u0011\u00101\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0006R\u0011\u00103\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0006R\u0011\u00105\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0006R\u0011\u00107\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0006R\u0011\u00109\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0006R\u0011\u0010;\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0006R\u0011\u0010=\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0006R\u0011\u0010?\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0006R\u0011\u0010A\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0006R\u0011\u0010C\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0006R\u0011\u0010E\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0006R\u0011\u0010G\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0006R\u0011\u0010I\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0006R\u0011\u0010K\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\u0006R\u0011\u0010M\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0006R\u0011\u0010O\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0006R\u0011\u0010Q\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0006R\u0011\u0010S\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\u0006R\u0011\u0010U\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\u0006R\u0011\u0010W\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u0006R\u0011\u0010Y\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0006R\u0011\u0010[\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\u0006R\u0011\u0010]\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0006R\u0011\u0010_\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0006R\u0011\u0010a\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0006R\u0011\u0010c\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0006¨\u0006e"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/AggregatedMetricsConstants$Companion;", "", "()V", "ACCESSORY_START_COMMAND_LATENCY", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "getACCESSORY_START_COMMAND_LATENCY", "()Lcom/amazon/alexa/fitness/utils/MetricComponent;", "AVS_EVENT_DURATION", "getAVS_EVENT_DURATION", "AVS_FAILURE_EVENT_COUNT", "getAVS_FAILURE_EVENT_COUNT", "AVS_SUCCESS_EVENT_COUNT", "getAVS_SUCCESS_EVENT_COUNT", "BACKGROUNDED", "getBACKGROUNDED", "BACKGROUNDED_DURATION", "getBACKGROUNDED_DURATION", "BACKGROUNDED_PERCENTAGE", "getBACKGROUNDED_PERCENTAGE", "CALORIES", "getCALORIES", "DISCONNECT", "getDISCONNECT", "DISCONNECTED_DURATION", "getDISCONNECTED_DURATION", "DISCONNECTED_PERCENTAGE", "getDISCONNECTED_PERCENTAGE", "DISCONNECTED_STOP", "getDISCONNECTED_STOP", "DISTANCE_IN_FEET", "getDISTANCE_IN_FEET", "FOREGROUNDED", "getFOREGROUNDED", "FOREGROUNDED_DURATION", "getFOREGROUNDED_DURATION", "FOREGROUNDED_PERCENTAGE", "getFOREGROUNDED_PERCENTAGE", "GUI_PAUSE", "getGUI_PAUSE", "GUI_RESUME", "getGUI_RESUME", "GUI_START", "getGUI_START", "GUI_STOP", "getGUI_STOP", "LOCATION_SAMPLE_RAW_COUNT", "getLOCATION_SAMPLE_RAW_COUNT", "MESSAGE_COUNT", "getMESSAGE_COUNT", "PROCESSING_DURATION", "getPROCESSING_DURATION", "PROCESSING_PERCENTAGE", "getPROCESSING_PERCENTAGE", "RECONNECT", "getRECONNECT", "RECOVERY", "getRECOVERY", "RECOVERY_COUNT", "getRECOVERY_COUNT", "RECOVERY_FAILED_STALE", "getRECOVERY_FAILED_STALE", "RECOVER_FAILED_INTERNAL", "getRECOVER_FAILED_INTERNAL", "RECOVER_FAILED_NO_DATA", "getRECOVER_FAILED_NO_DATA", "SENSOR_PAUSE", "getSENSOR_PAUSE", "SENSOR_RESUME", "getSENSOR_RESUME", "SENSOR_STOP", "getSENSOR_STOP", "SESSION_COMPLETE", "getSESSION_COMPLETE", "SESSION_COMPLETE_WITH_ROUTE", "getSESSION_COMPLETE_WITH_ROUTE", "SESSION_DURATION", "getSESSION_DURATION", "SESSION_EXISTENCE_DURATION", "getSESSION_EXISTENCE_DURATION", "SPEED", "getSPEED", "START_WITH_FULL_FITNESS_PROFILE", "getSTART_WITH_FULL_FITNESS_PROFILE", "START_WITH_PARTIAL_FITNESS_PROFILE", "getSTART_WITH_PARTIAL_FITNESS_PROFILE", "STEPS", "getSTEPS", "STOP_ACCESSORY", "getSTOP_ACCESSORY", "STOP_NO_SAMPLE", "getSTOP_NO_SAMPLE", "STOP_TIMEOUT", "getSTOP_TIMEOUT", "VUI_PAUSE", "getVUI_PAUSE", "VUI_RESUME", "getVUI_RESUME", "VUI_START", "getVUI_START", "VUI_STOP", "getVUI_STOP", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final MetricComponent getACCESSORY_START_COMMAND_LATENCY() {
            return AggregatedMetricsConstants.ACCESSORY_START_COMMAND_LATENCY;
        }

        @NotNull
        public final MetricComponent getAVS_EVENT_DURATION() {
            return AggregatedMetricsConstants.AVS_EVENT_DURATION;
        }

        @NotNull
        public final MetricComponent getAVS_FAILURE_EVENT_COUNT() {
            return AggregatedMetricsConstants.AVS_FAILURE_EVENT_COUNT;
        }

        @NotNull
        public final MetricComponent getAVS_SUCCESS_EVENT_COUNT() {
            return AggregatedMetricsConstants.AVS_SUCCESS_EVENT_COUNT;
        }

        @NotNull
        public final MetricComponent getBACKGROUNDED() {
            return AggregatedMetricsConstants.BACKGROUNDED;
        }

        @NotNull
        public final MetricComponent getBACKGROUNDED_DURATION() {
            return AggregatedMetricsConstants.BACKGROUNDED_DURATION;
        }

        @NotNull
        public final MetricComponent getBACKGROUNDED_PERCENTAGE() {
            return AggregatedMetricsConstants.BACKGROUNDED_PERCENTAGE;
        }

        @NotNull
        public final MetricComponent getCALORIES() {
            return AggregatedMetricsConstants.CALORIES;
        }

        @NotNull
        public final MetricComponent getDISCONNECT() {
            return AggregatedMetricsConstants.DISCONNECT;
        }

        @NotNull
        public final MetricComponent getDISCONNECTED_DURATION() {
            return AggregatedMetricsConstants.DISCONNECTED_DURATION;
        }

        @NotNull
        public final MetricComponent getDISCONNECTED_PERCENTAGE() {
            return AggregatedMetricsConstants.DISCONNECTED_PERCENTAGE;
        }

        @NotNull
        public final MetricComponent getDISCONNECTED_STOP() {
            return AggregatedMetricsConstants.DISCONNECTED_STOP;
        }

        @NotNull
        public final MetricComponent getDISTANCE_IN_FEET() {
            return AggregatedMetricsConstants.DISTANCE_IN_FEET;
        }

        @NotNull
        public final MetricComponent getFOREGROUNDED() {
            return AggregatedMetricsConstants.FOREGROUNDED;
        }

        @NotNull
        public final MetricComponent getFOREGROUNDED_DURATION() {
            return AggregatedMetricsConstants.FOREGROUNDED_DURATION;
        }

        @NotNull
        public final MetricComponent getFOREGROUNDED_PERCENTAGE() {
            return AggregatedMetricsConstants.FOREGROUNDED_PERCENTAGE;
        }

        @NotNull
        public final MetricComponent getGUI_PAUSE() {
            return AggregatedMetricsConstants.GUI_PAUSE;
        }

        @NotNull
        public final MetricComponent getGUI_RESUME() {
            return AggregatedMetricsConstants.GUI_RESUME;
        }

        @NotNull
        public final MetricComponent getGUI_START() {
            return AggregatedMetricsConstants.GUI_START;
        }

        @NotNull
        public final MetricComponent getGUI_STOP() {
            return AggregatedMetricsConstants.GUI_STOP;
        }

        @NotNull
        public final MetricComponent getLOCATION_SAMPLE_RAW_COUNT() {
            return AggregatedMetricsConstants.LOCATION_SAMPLE_RAW_COUNT;
        }

        @NotNull
        public final MetricComponent getMESSAGE_COUNT() {
            return AggregatedMetricsConstants.MESSAGE_COUNT;
        }

        @NotNull
        public final MetricComponent getPROCESSING_DURATION() {
            return AggregatedMetricsConstants.PROCESSING_DURATION;
        }

        @NotNull
        public final MetricComponent getPROCESSING_PERCENTAGE() {
            return AggregatedMetricsConstants.PROCESSING_PERCENTAGE;
        }

        @NotNull
        public final MetricComponent getRECONNECT() {
            return AggregatedMetricsConstants.RECONNECT;
        }

        @NotNull
        public final MetricComponent getRECOVERY() {
            return AggregatedMetricsConstants.RECOVERY;
        }

        @NotNull
        public final MetricComponent getRECOVERY_COUNT() {
            return AggregatedMetricsConstants.RECOVERY_COUNT;
        }

        @NotNull
        public final MetricComponent getRECOVERY_FAILED_STALE() {
            return AggregatedMetricsConstants.RECOVERY_FAILED_STALE;
        }

        @NotNull
        public final MetricComponent getRECOVER_FAILED_INTERNAL() {
            return AggregatedMetricsConstants.RECOVER_FAILED_INTERNAL;
        }

        @NotNull
        public final MetricComponent getRECOVER_FAILED_NO_DATA() {
            return AggregatedMetricsConstants.RECOVER_FAILED_NO_DATA;
        }

        @NotNull
        public final MetricComponent getSENSOR_PAUSE() {
            return AggregatedMetricsConstants.SENSOR_PAUSE;
        }

        @NotNull
        public final MetricComponent getSENSOR_RESUME() {
            return AggregatedMetricsConstants.SENSOR_RESUME;
        }

        @NotNull
        public final MetricComponent getSENSOR_STOP() {
            return AggregatedMetricsConstants.SENSOR_STOP;
        }

        @NotNull
        public final MetricComponent getSESSION_COMPLETE() {
            return AggregatedMetricsConstants.SESSION_COMPLETE;
        }

        @NotNull
        public final MetricComponent getSESSION_COMPLETE_WITH_ROUTE() {
            return AggregatedMetricsConstants.SESSION_COMPLETE_WITH_ROUTE;
        }

        @NotNull
        public final MetricComponent getSESSION_DURATION() {
            return AggregatedMetricsConstants.SESSION_DURATION;
        }

        @NotNull
        public final MetricComponent getSESSION_EXISTENCE_DURATION() {
            return AggregatedMetricsConstants.SESSION_EXISTENCE_DURATION;
        }

        @NotNull
        public final MetricComponent getSPEED() {
            return AggregatedMetricsConstants.SPEED;
        }

        @NotNull
        public final MetricComponent getSTART_WITH_FULL_FITNESS_PROFILE() {
            return AggregatedMetricsConstants.START_WITH_FULL_FITNESS_PROFILE;
        }

        @NotNull
        public final MetricComponent getSTART_WITH_PARTIAL_FITNESS_PROFILE() {
            return AggregatedMetricsConstants.START_WITH_PARTIAL_FITNESS_PROFILE;
        }

        @NotNull
        public final MetricComponent getSTEPS() {
            return AggregatedMetricsConstants.STEPS;
        }

        @NotNull
        public final MetricComponent getSTOP_ACCESSORY() {
            return AggregatedMetricsConstants.STOP_ACCESSORY;
        }

        @NotNull
        public final MetricComponent getSTOP_NO_SAMPLE() {
            return AggregatedMetricsConstants.STOP_NO_SAMPLE;
        }

        @NotNull
        public final MetricComponent getSTOP_TIMEOUT() {
            return AggregatedMetricsConstants.STOP_TIMEOUT;
        }

        @NotNull
        public final MetricComponent getVUI_PAUSE() {
            return AggregatedMetricsConstants.VUI_PAUSE;
        }

        @NotNull
        public final MetricComponent getVUI_RESUME() {
            return AggregatedMetricsConstants.VUI_RESUME;
        }

        @NotNull
        public final MetricComponent getVUI_START() {
            return AggregatedMetricsConstants.VUI_START;
        }

        @NotNull
        public final MetricComponent getVUI_STOP() {
            return AggregatedMetricsConstants.VUI_STOP;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
