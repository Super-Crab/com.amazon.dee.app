package com.amazon.comms.ringservice;

import android.content.Context;
import com.adobe.xmp.XMPConst;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.Priority;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.sipclient.CallDetails;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.metrics.MetricsDestination;
import com.amazon.comms.metrics.TachyonMetricsFactory;
import com.amazon.comms.metrics.TachyonMetricsFactoryImpl;
import com.amazon.comms.ringservice.util.ThermalMitigationDetails;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import javax.annotation.Nonnull;
import net.danlew.android.joda.JodaTimeAndroid;
/* loaded from: classes12.dex */
public class MetricsSession {
    public static final String APP_VERSION_PIVOT = "AppVersion";
    public static final String CALL_PROVIDER_PIVOT = "CallProvider";
    public static final String PAYLOAD_ENCODING_BASE64_JSON = "application/json";
    public static final String SERVICE_UNAVAILABLE = "503";
    public static final String TRICKLE_ICE_PIVOT = "TrickleIceEnabled";
    private final String appLabel;
    private final String appVersion;
    private final TachyonMetricsFactory metricsFactory;
    private static final CommsLogger log = CommsLogger.getLogger(MetricsSession.class);
    public static final MetricsDestination RS_DEFAULT_METRIC_DESTINATION = MetricsDestination.ALL;
    public static final Priority DEFAULT_METRIC_PRIORITY = Priority.NORMAL;

    public MetricsSession(@Nonnull Context context, String str) {
        JodaTimeAndroid.init(context);
        this.metricsFactory = TachyonMetricsFactoryImpl.getInstance(context);
        this.appLabel = context.getPackageName();
        this.appVersion = str;
        log.i(String.format("MetricsSession logging metrics with appLabel: %s, appVersion: %s", context.getPackageName(), str));
    }

    public MetricEvent createEvent(String str) {
        return this.metricsFactory.createMetricEvent(this.appLabel, str);
    }

    public void recordCallCompletionStatus(String str, boolean z, ErrorCode errorCode, String str2, String str3, Boolean bool, String str4) {
        if (!z && errorCode == null) {
            log.w("Call failed with no errorCode, NOT HANDLED");
            return;
        }
        CommsLogger commsLogger = log;
        commsLogger.i("CallCompletionStatus for CallId:" + str + " completionStatus=" + z + " reason:" + str2);
        MetricEvent createEvent = createEvent("CallSummary");
        if (!Strings.isNullOrEmpty(this.appVersion)) {
            createEvent.addString("AppVersion", this.appVersion);
        }
        if (!Strings.isNullOrEmpty(str)) {
            createEvent.addString("CallId", str);
        }
        if (bool != null) {
            createEvent.addString(TRICKLE_ICE_PIVOT, bool.booleanValue() ? XMPConst.TRUESTR : XMPConst.FALSESTR);
        }
        if (!Strings.isNullOrEmpty(str4)) {
            createEvent.addString(CALL_PROVIDER_PIVOT, str4);
        }
        createEvent.addCounter("CallFailure", (z || ErrorCode.isMediaError(errorCode)) ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : 1.0d);
        if (!z) {
            reportError(errorCode, str);
            if (ErrorCode.isMediaError(errorCode) && !Strings.isNullOrEmpty(str3)) {
                createEvent.addCounter(String.format("%s_%s", Integer.toString(errorCode.getValue()), str3), 1.0d);
            }
        }
        recordEvent(createEvent);
    }

    public void recordConnectedCallAttributes(boolean z, boolean z2, String str, String str2) {
        MetricEvent createEvent = createEvent("ConnectedCallAttributes");
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "Caller" : "Callee");
        sb.append("_calltype_");
        sb.append(z2 ? "video" : "audio");
        createEvent.addCounter(sb.toString(), 1.0d);
        createEvent.addString(CALL_PROVIDER_PIVOT, str);
        if (!Strings.isNullOrEmpty(str2)) {
            createEvent.addString("CallId", str2);
        }
        recordEvent(createEvent);
    }

    public void recordCount(String str, String str2, double d, String str3) {
        recordCount(str, str2, d, str3, RS_DEFAULT_METRIC_DESTINATION, DEFAULT_METRIC_PRIORITY, null, null);
    }

    public void recordEvent(MetricEvent metricEvent) {
        recordEvent(metricEvent, RS_DEFAULT_METRIC_DESTINATION, DEFAULT_METRIC_PRIORITY);
    }

    public void recordThermalMitigationStatus(ThermalMitigationDetails thermalMitigationDetails, CallDetails callDetails, String str) {
        MetricEvent createEvent = createEvent("ThermalMitigationSummary");
        double d = 1.0d;
        createEvent.addCounter("mitigationFPS", thermalMitigationDetails.getFps().isApplied() ? 1.0d : 0.0d);
        createEvent.addCounter("mitigationResolution", thermalMitigationDetails.getResolution().isApplied() ? 1.0d : 0.0d);
        createEvent.addCounter("mitigationAudioOnly", thermalMitigationDetails.getAudioOnly().isApplied() ? 1.0d : 0.0d);
        if (!thermalMitigationDetails.isMitigationApplied()) {
            d = 0.0d;
        }
        createEvent.addCounter("mitigationApplied", d);
        long millis = callDetails.getCallStartTime().getMillis();
        long millis2 = callDetails.getCallCompletedTime().getMillis();
        createEvent.addTimer("mitigationTimer", thermalMitigationDetails.getMitigationTimeTotal());
        createEvent.addTimer("callTimer", millis2 - millis);
        if (!Strings.isNullOrEmpty(str)) {
            createEvent.addString("CallId", str);
        }
        recordEvent(createEvent);
    }

    public void recordTimer(String str, String str2, double d, String str3) {
        recordTimer(str, str2, d, str3, RS_DEFAULT_METRIC_DESTINATION, DEFAULT_METRIC_PRIORITY);
    }

    public void recordTrace(String str, String str2, String str3, String str4, String str5) {
        this.metricsFactory.recordTrace(str, str2, str3, str4, str5);
    }

    public void recordValue(String str, String str2, String str3, String str4) {
        recordValue(str, str2, str3, str4, RS_DEFAULT_METRIC_DESTINATION, DEFAULT_METRIC_PRIORITY);
    }

    public void reportError(ErrorCode errorCode, String str) {
        MetricEvent createEvent = createEvent("DeviceCallingServiceError");
        if (!Strings.isNullOrEmpty(this.appVersion)) {
            createEvent.addString("AppVersion", this.appVersion);
        }
        if (!Strings.isNullOrEmpty(str)) {
            createEvent.addString("CallId", str);
        }
        createEvent.addCounter(Integer.toString(errorCode.getValue()), 1.0d);
        if (ErrorCode.isMediaError(errorCode)) {
            recordEvent(createEvent, RS_DEFAULT_METRIC_DESTINATION, Priority.HIGH);
        } else {
            recordEvent(createEvent);
        }
    }

    public void reportSipStatus(String str, String str2, boolean z, boolean z2, boolean z3) {
        MetricEvent createEvent = createEvent("DeviceCallingServiceSipError");
        if (!Strings.isNullOrEmpty(this.appVersion)) {
            createEvent.addString("AppVersion", this.appVersion);
        }
        if (!Strings.isNullOrEmpty(str2)) {
            createEvent.addString("CallId", str2);
        }
        if (z) {
            String[] split = str.split("_");
            if (z2) {
                createEvent.addCounter("SipRegistrationFailure", 1.0d);
                if (z3) {
                    createEvent.addCounter("SipRegistrationFailure_IPv6OnlyNetwork", 1.0d);
                }
                if (split != null && split.length > 0 && SERVICE_UNAVAILABLE.equals(split[0])) {
                    createEvent.addCounter(GeneratedOutlineSupport1.outline91(new StringBuilder(), split[0], "_cumulative"), 1.0d);
                } else {
                    createEvent.addCounter(str, 1.0d);
                }
            } else {
                log.i("SipRegistrationFailure metric not reported as this is not a new SIP register request");
                if (split != null && split.length > 0 && SERVICE_UNAVAILABLE.equals(split[0])) {
                    createEvent.addCounter(GeneratedOutlineSupport1.outline91(new StringBuilder(), split[0], "_cumulative_repetitive"), 1.0d);
                } else {
                    createEvent.addCounter(str + "_repetitive", 1.0d);
                }
            }
            if (split != null && split.length > 0 && SERVICE_UNAVAILABLE.equals(split[0])) {
                createEvent.addCounter(str, 1.0d);
            }
        } else {
            createEvent.addCounter("SipRegistrationFailure", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        }
        recordEvent(createEvent, RS_DEFAULT_METRIC_DESTINATION, Priority.HIGH);
    }

    private void recordTimer(String str, String str2, double d, String str3, MetricsDestination metricsDestination, Priority priority) {
        MetricEvent createEvent = createEvent(str);
        if (!Strings.isNullOrEmpty(str3)) {
            createEvent.addString("CallId", str3);
        }
        createEvent.addTimer(str2, d);
        this.metricsFactory.record(createEvent, metricsDestination, priority);
    }

    private void recordValue(String str, String str2, String str3, String str4, MetricsDestination metricsDestination, Priority priority) {
        MetricEvent createEvent = createEvent(str);
        if (!Strings.isNullOrEmpty(str4)) {
            createEvent.addString("CallId", str4);
        }
        createEvent.addString(str2, str3);
        this.metricsFactory.record(createEvent, metricsDestination, priority);
    }

    public void recordCount(String str, String str2, double d, String str3, Boolean bool) {
        recordCount(str, str2, d, str3, RS_DEFAULT_METRIC_DESTINATION, DEFAULT_METRIC_PRIORITY, null, bool);
    }

    public void recordEvent(MetricEvent metricEvent, MetricsDestination metricsDestination, Priority priority) {
        this.metricsFactory.record(metricEvent, metricsDestination, priority);
    }

    public void recordCount(String str, String str2, double d, String str3, String str4) {
        recordCount(str, str2, d, str3, RS_DEFAULT_METRIC_DESTINATION, DEFAULT_METRIC_PRIORITY, str4, null);
    }

    private void recordCount(String str, String str2, double d, String str3, MetricsDestination metricsDestination, Priority priority, String str4, Boolean bool) {
        MetricEvent createEvent = createEvent(str);
        if (!Strings.isNullOrEmpty(this.appVersion)) {
            createEvent.addString("AppVersion", this.appVersion);
        }
        if (!Strings.isNullOrEmpty(str3)) {
            createEvent.addString("CallId", str3);
        }
        if (!Strings.isNullOrEmpty(str4)) {
            createEvent.addString(CALL_PROVIDER_PIVOT, str4);
        }
        if (bool != null) {
            createEvent.addString(TRICKLE_ICE_PIVOT, bool.booleanValue() ? XMPConst.TRUESTR : XMPConst.FALSESTR);
        }
        createEvent.addCounter(str2, d);
        this.metricsFactory.record(createEvent, metricsDestination, priority);
    }
}
