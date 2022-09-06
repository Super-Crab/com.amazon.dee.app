package com.amazon.deecomms.common.metrics;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.util.HttpStatusCodeFamily;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;
import org.webrtc.MediaCodecVideoDecoder;
import org.webrtc.MediaCodecVideoEncoder;
/* loaded from: classes12.dex */
public final class MetricsHelper {
    private static final String[] supportedMimes = {"video/avc", "video/x-vnd.on2.vp8", "video/x-vnd.on2.vp9", "audio/opus"};
    private static final NetworkExceptionClassifier baseClassifier = $$Lambda$MetricsHelper$JkmiFHOg7NofXM4Nbok2aM6Aw.INSTANCE;
    private static final NetworkExceptionClassifier ioExceptionClassifier = $$Lambda$MetricsHelper$MqjJpxIcmEHpZOrJEEkr_RZFBm0.INSTANCE;

    /* loaded from: classes12.dex */
    public static class IOExceptionWrapper extends IOException implements MetricsStatsProvider {
        private final Long callDuration;
        private final HttpStatusCodeFamily family;
        private final Integer httpStatusCode;
        private final String requestId;

        public IOExceptionWrapper(@NonNull Throwable th, @Nullable String str, @Nullable Integer num, @Nullable Long l) {
            super(th);
            this.requestId = str;
            this.httpStatusCode = num;
            this.callDuration = l;
            this.family = HttpStatusCodeFamily.familyFromStatusCode(num);
        }

        @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
        public Long getCallDuration() {
            return this.callDuration;
        }

        @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
        public int getFailureCount() {
            return this.family.getFailureCount();
        }

        @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
        public int getFaultCount() {
            return this.family.getFaultCount();
        }

        @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
        public Integer getHttpResponseCode() {
            Integer num = this.httpStatusCode;
            return Integer.valueOf(num != null ? num.intValue() : Constants.STATUS_CODE_EXCEPTION);
        }

        @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
        public String getRequestId() {
            return this.requestId;
        }

        @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
        public int getSuccessCount() {
            return this.family.getSuccessCount();
        }

        @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
        public int getUnknownCount() {
            return this.family.getUnknownCount();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public interface NetworkExceptionClassifier {
        boolean isNetworkException(@NonNull Throwable th);
    }

    private MetricsHelper() {
    }

    public static void addCallAttributes(@NonNull CommsMetric commsMetric, @Nullable Call call) {
        addCallId(commsMetric, call);
        addCallType(commsMetric, call);
        addConnectionType(commsMetric, call);
    }

    public static void addCallId(@NonNull CommsMetric commsMetric, @Nullable Call call) {
        if (call != null) {
            commsMetric.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, call.getCallId());
        }
    }

    private static void addCallType(@NonNull CommsMetric commsMetric, @Nullable Call call) {
        if (call != null) {
            addCallType(commsMetric, CommsDaggerWrapper.getComponent().getCallHistoryHelper().getCallType(call.getCallId()));
        }
    }

    private static void addCodecDataForSupportedMimeTypes() {
        String[] strArr;
        for (String str : supportedMimes) {
            recordMetricWithMetadata(GeneratedOutlineSupport.outline0(MetricKeys.CODECS_SUPPORTED_FOR_MIMETYPES, str), "source", TextUtils.join(",", Utils.getSupportedCodecsforGivenMimeType(str)));
        }
    }

    public static void addConnectionType(@NonNull CommsMetric commsMetric, @Nullable Call call) {
        if (call != null) {
            addConnectionType(commsMetric.getMetadata(), call.getCallId());
        }
    }

    @VisibleForTesting
    static void addException(@NonNull Map<String, Object> map, @NonNull Exception exc) {
        boolean z = exc instanceof ServiceException;
        Integer valueOf = Integer.valueOf((int) Constants.STATUS_CODE_EXCEPTION);
        if (z) {
            ServiceException serviceException = (ServiceException) exc;
            map.put("requestId", serviceException.getRequestId());
            if (serviceException.getHttpResponseCode() != null) {
                map.put("statusCode", serviceException.getHttpResponseCode());
            } else {
                map.put("statusCode", valueOf);
            }
        } else {
            map.put("statusCode", valueOf);
        }
        if (!map.containsKey("errorSource")) {
            map.put("errorSource", throwableAsString(exc));
        }
    }

    public static void addResponseMetadata(@NonNull Map<String, Object> map, @NonNull MetricsStatsProvider metricsStatsProvider) {
        if (metricsStatsProvider.getRequestId() != null) {
            map.put("requestId", metricsStatsProvider.getRequestId());
        }
        if (metricsStatsProvider.getHttpResponseCode() != null) {
            map.put("statusCode", metricsStatsProvider.getHttpResponseCode());
        }
    }

    public static TimerMetric createTimerMetric(String str) {
        TimerMetric timerMetric = new TimerMetric(CommsMetric.MetricType.Operational, str);
        timerMetric.startTimer();
        return timerMetric;
    }

    public static boolean isNetworkException(@NonNull Exception exc) {
        return baseClassifier.isNetworkException(exc) || ioExceptionClassifier.isNetworkException(exc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(Throwable th) {
        return (th instanceof SocketException) || (th instanceof UnknownHostException) || (th instanceof SSLException) || (th instanceof SocketTimeoutException) || (th instanceof EOFException);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$1(Throwable th) {
        if (th.getClass() != IOException.class) {
            return false;
        }
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            if (baseClassifier.isNetworkException(cause)) {
                return true;
            }
        }
        return false;
    }

    public static void recordAlertDialogMetric(String str, String str2, @NonNull AlertSource alertSource) {
        recordAlertDialogMetric(str, str2, alertSource, null);
    }

    public static <R> void recordApiCallCompleted(@NonNull CommsMetric.MetricType metricType, @NonNull String str, @Nullable R r, @Nullable Map<String, Object> map) {
        Throwable th;
        TimerMetric generateOperational;
        HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        HttpStatusCodeFamily httpStatusCodeFamily = HttpStatusCodeFamily.UNKNOWN;
        int failureCount = httpStatusCodeFamily.getFailureCount();
        int failureCount2 = httpStatusCodeFamily.getFailureCount();
        int unknownCount = httpStatusCodeFamily.getUnknownCount();
        int successCount = httpStatusCodeFamily.getSuccessCount();
        boolean z = false;
        Long l = null;
        if (r instanceof IOExceptionWrapper) {
            th = ((IOExceptionWrapper) r).getCause();
        } else {
            th = r instanceof Exception ? (Throwable) r : null;
        }
        if (th instanceof Exception) {
            Exception exc = (Exception) th;
            addException(hashMap, exc);
            z = isNetworkException(exc);
        }
        if (r instanceof MetricsStatsProvider) {
            MetricsStatsProvider metricsStatsProvider = (MetricsStatsProvider) r;
            addResponseMetadata(hashMap, metricsStatsProvider);
            l = metricsStatsProvider.getCallDuration();
            failureCount = metricsStatsProvider.getFailureCount();
            failureCount2 = metricsStatsProvider.getFaultCount();
            unknownCount = metricsStatsProvider.getUnknownCount();
            successCount = metricsStatsProvider.getSuccessCount();
        }
        if (successCount > 0) {
            recordCounterMetric(metricType, GeneratedOutlineSupport.outline0(str, ".success"), successCount, hashMap);
        }
        recordCounterMetric(metricType, GeneratedOutlineSupport.outline0(str, ".fail"), failureCount, hashMap);
        recordCounterMetric(metricType, str + ".fault", failureCount2, hashMap);
        String str2 = str + ".unknown";
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        recordCounterMetric(metricType, str2, z ? 0.0d : unknownCount, hashMap);
        String str3 = str + ".network";
        if (z) {
            d = unknownCount;
        }
        recordCounterMetric(metricType, str3, d, hashMap);
        if (l == null || l.longValue() < 0) {
            return;
        }
        if (metricType == CommsMetric.MetricType.ClickStream) {
            generateOperational = TimerMetric.generateClickstream(str + ".latency");
        } else {
            generateOperational = TimerMetric.generateOperational(str + ".latency");
        }
        generateOperational.setTimeDelta(l.longValue());
        generateOperational.getMetadata().putAll(hashMap);
        generateOperational.stopTimer();
        CommsDaggerWrapper.getComponent().getMetricsService().recordTimerMetric(generateOperational);
    }

    public static CounterMetric[] recordCDSErrorMetrics(@NonNull String str, @NonNull Exception exc, @NonNull int i, @NonNull String str2, @NonNull String str3) {
        CounterMetric generateOperationalException = CounterMetric.generateOperationalException(exc, str, str3, i, str2);
        recordSingleOccurrence(generateOperationalException);
        CounterMetric generateCodeSpecificOperationalException = CounterMetric.generateCodeSpecificOperationalException(exc, str, str3, i, str2);
        recordSingleOccurrence(generateCodeSpecificOperationalException);
        CounterMetric generateStatusCodeSpecificOperationalException = CounterMetric.generateStatusCodeSpecificOperationalException(exc, str, str3, i, str2);
        recordSingleOccurrence(generateStatusCodeSpecificOperationalException);
        return new CounterMetric[]{generateOperationalException, generateCodeSpecificOperationalException, generateStatusCodeSpecificOperationalException};
    }

    public static void recordCallConnectedMetrics(@Nullable Call call) {
        if (call != null && call.getOrigin() == Call.Side.Remote) {
            TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.CALL_TIME_TO_ANSWER);
            addCallAttributes(generateClickstream, call);
            stopTimerMetric(generateClickstream);
        }
        CounterMetric generateClickstream2 = CounterMetric.generateClickstream(MetricKeys.CALL_CONNECTED);
        addCallId(generateClickstream2, call);
        recordSingleOccurrence(generateClickstream2);
    }

    public static void recordCallConnectionTypeMetric(@NonNull Call call) {
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.CALL_CONNECTION_TYPE_SET);
        addCallAttributes(generateOperational, call);
        recordCounterMetric(generateOperational, Double.valueOf(1.0d));
    }

    public static void recordCounterMetric(CounterMetric counterMetric, Double d) {
        if (counterMetric == null || d == null) {
            return;
        }
        counterMetric.setCounter(d);
        CommsDaggerWrapper.getComponent().getMetricsService().recordCounterMetric(counterMetric);
    }

    public static void recordCounterMetricOperational(String str, double d) {
        if (!TextUtils.isEmpty(str)) {
            recordCounterMetric(CounterMetric.generateOperational(str), Double.valueOf(d));
        }
    }

    public static void recordDevicesCodecInfo() {
        double d = 1.0d;
        recordCounterMetricOperational(MetricKeys.ENCODING_H264_HWSUPPORTED, MediaCodecVideoEncoder.isH264HwSupported() ? 1.0d : 0.0d);
        recordCounterMetricOperational(MetricKeys.ENCODING_H264HWSUPPORTED_USINGTEXTURES, MediaCodecVideoEncoder.isH264HwSupportedUsingTextures() ? 1.0d : 0.0d);
        recordCounterMetricOperational(MetricKeys.ENCODING_VP8_HWSUPPORTED, MediaCodecVideoEncoder.isVp8HwSupported() ? 1.0d : 0.0d);
        recordCounterMetricOperational(MetricKeys.ENCODING_VP8_HWSUPPORTED_USINGTEXTURES, MediaCodecVideoEncoder.isVp8HwSupportedUsingTextures() ? 1.0d : 0.0d);
        recordCounterMetricOperational(MetricKeys.ENCODING_VP9_HWSUPPORTED, MediaCodecVideoEncoder.isVp9HwSupported() ? 1.0d : 0.0d);
        recordCounterMetricOperational(MetricKeys.ENCODING_VP9_HWSUPPORTED_USINGTEXTURES, MediaCodecVideoEncoder.isVp9HwSupportedUsingTextures() ? 1.0d : 0.0d);
        recordCounterMetricOperational(MetricKeys.DECODING_H264_HWSUPPORTED, MediaCodecVideoDecoder.isH264HwSupported() ? 1.0d : 0.0d);
        recordCounterMetricOperational(MetricKeys.DECODING_VP8_HWSUPPORTED, MediaCodecVideoDecoder.isVp8HwSupported() ? 1.0d : 0.0d);
        if (!MediaCodecVideoDecoder.isVp9HwSupported()) {
            d = 0.0d;
        }
        recordCounterMetricOperational(MetricKeys.DECODING_VP9HW_SUPPORTED, d);
        addCodecDataForSupportedMimeTypes();
    }

    public static void recordFailedToConnectToTurnMetric(@Nullable String str, @Nullable Integer num, @Nullable String str2) {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.CALL_FAIL_CONN_TO_TURN);
        Map<String, Object> metadata = generateClickstream.getMetadata();
        if (!TextUtils.isEmpty(str)) {
            metadata.put(MetricKeys.META_COMMS_ITEM_ID, str);
        }
        if (num != null) {
            metadata.put("statusCode", num);
        }
        if (!TextUtils.isEmpty(str2)) {
            metadata.put("requestId", str2);
        }
        recordSingleOccurrence(generateClickstream);
    }

    public static void recordMetricWithMetadata(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        CounterMetric generateOperational = CounterMetric.generateOperational(str);
        generateOperational.getMetadata().put(str2, str3);
        recordSingleOccurrence(generateOperational);
    }

    public static void recordMetricsWithSourceAndEventValue(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(str);
        generateClickstream.getMetadata().put("source", str3);
        generateClickstream.getMetadata().put("EventValue", str2);
        recordSingleOccurrence(generateClickstream);
    }

    public static void recordOperationalMetricWithSource(@NonNull String str, @NonNull String str2) {
        CounterMetric generateOperational = CounterMetric.generateOperational(str);
        generateOperational.getMetadata().put("source", str2);
        recordSingleOccurrence(generateOperational);
    }

    public static void recordOrientationMetric(String str, int i) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ".");
        outline113.append((i == 1 || i == 3) ? "landscape" : "portrait");
        recordSingleOccurrenceOperational(outline113.toString());
    }

    public static void recordRTTMetrics(@NonNull SipClientState sipClientState) {
        if (sipClientState.getRealTimeTextMetrics() == null || sipClientState.getRealTimeTextMetrics().getEnablementState() == null || sipClientState.getRealTimeTextMetrics().getRequestState() == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(MetricKeys.META_COMMS_ITEM_ID, sipClientState.getCallId());
        recordSingleOccurrenceClickstream(MetricKeys.REAL_TIME_TEXT + sipClientState.getRealTimeTextMetrics().getEnablementState().toLowerCase(), hashMap);
        recordSingleOccurrenceClickstream(MetricKeys.REAL_TIME_TEXT + sipClientState.getRealTimeTextMetrics().getRequestState().toLowerCase(), hashMap);
    }

    public static void recordSingleOccurrence(CounterMetric counterMetric) {
        recordCounterMetric(counterMetric, Double.valueOf(1.0d));
    }

    public static void recordSingleOccurrenceClickstream(String str) {
        recordSingleOccurrenceClickstream(str, null);
    }

    public static void recordSingleOccurrenceClickstreamByChild(@NonNull String str, @NonNull boolean z) {
        HashMap hashMap = new HashMap();
        if (z) {
            hashMap.put("source", MetricKeys.VALUE_IS_CHILD);
        }
        recordSingleOccurrenceClickstream(str, hashMap);
    }

    public static void recordSingleOccurrenceOperational(String str) {
        recordCounterMetricOperational(str, 1.0d);
    }

    public static void recordTimerMetric(@NonNull TimerMetric timerMetric) {
        timerMetric.stopTimer();
        CommsDaggerWrapper.getComponent().getMetricsService().recordTimerMetric(timerMetric);
    }

    public static void recordVoiceMsgConsentMetrics(@Nullable Boolean bool, @NonNull String str, @NonNull String str2, @NonNull AlertSource alertSource) {
        CounterMetric generateOperational;
        if (bool == null) {
            generateOperational = CounterMetric.generateOperational(str + ".error");
            alertSource.setupMetric(generateOperational);
        } else if (bool.booleanValue()) {
            generateOperational = CounterMetric.generateOperational(str + MetricKeys.GRANT_SFX);
        } else {
            generateOperational = CounterMetric.generateOperational(str + MetricKeys.REVOKE_SFX);
        }
        generateOperational.getMetadata().put("source", str2);
        recordSingleOccurrence(generateOperational);
    }

    public static void startCallDurationTimer(@Nullable Call call) {
        if (call == null) {
            return;
        }
        TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.CALL_DURATION);
        addCallId(generateClickstream, call);
        startTimerMetric(generateClickstream);
    }

    public static void startTimerMetric(CommsMetric commsMetric) {
        if (commsMetric != null) {
            CommsDaggerWrapper.getComponent().getMetricsService().startTimerMetric(commsMetric);
            if (!(commsMetric instanceof TimerMetric)) {
                return;
            }
            ((TimerMetric) commsMetric).startTimer();
        }
    }

    public static void stopAppDurationTimers() {
        TimerMetric generateOperational = TimerMetric.generateOperational(MetricKeys.APP_COLD_START_DURATION);
        generateOperational.getMetadata().put("source", "Comms");
        stopTimerMetric(generateOperational);
        TimerMetric generateOperational2 = TimerMetric.generateOperational(MetricKeys.APP_WARM_START_DURATION);
        generateOperational2.getMetadata().put("source", "Comms");
        stopTimerMetric(generateOperational2);
        TimerMetric generateOperational3 = TimerMetric.generateOperational(MetricKeys.NATIVE_START_DURATION);
        generateOperational3.getMetadata().put("source", "Comms");
        stopTimerMetric(generateOperational3);
    }

    public static void stopCallDurationTimer(@Nullable Call call) {
        if (call == null) {
            return;
        }
        TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.CALL_DURATION);
        addCallId(generateClickstream, call);
        stopTimerMetric(generateClickstream);
    }

    public static void stopTimerMetric(CommsMetric commsMetric) {
        if (commsMetric != null) {
            if (commsMetric instanceof TimerMetric) {
                ((TimerMetric) commsMetric).stopTimer();
            }
            CommsDaggerWrapper.getComponent().getMetricsService().stopTimerMetric(commsMetric);
        }
    }

    static String throwableAsString(@NonNull Throwable th) {
        return th.getClass().getSimpleName() + RealTimeTextConstants.COLON_SPACE + th.getMessage();
    }

    public static void recordAlertDialogMetric(String str, String str2, @NonNull AlertSource alertSource, @Nullable Bundle bundle) {
        CounterMetric generateOperational = CounterMetric.generateOperational(str);
        Map<String, Object> metadata = generateOperational.getMetadata();
        if (str2 != null) {
            metadata.put("errorSource", str2);
        }
        alertSource.setupMetric(generateOperational);
        if (bundle != null) {
            for (String str3 : bundle.keySet()) {
                metadata.put(str3, bundle.get(str3));
            }
        }
        recordSingleOccurrence(generateOperational);
    }

    public static void recordSingleOccurrenceClickstream(@NonNull String str, @Nullable Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            CounterMetric counterMetric = new CounterMetric(CommsMetric.MetricType.ClickStream, str);
            if (map != null) {
                counterMetric.getMetadata().putAll(map);
            }
            recordSingleOccurrence(counterMetric);
        }
    }

    public static void addConnectionType(@NonNull CommsMetric commsMetric, @Nullable String str) {
        addConnectionType(commsMetric.getMetadata(), str);
    }

    public static void recordCounterMetric(@NonNull CommsMetric.MetricType metricType, @NonNull String str, double d, @Nullable Map<String, Object> map) {
        CounterMetric generateOperational;
        if (metricType == CommsMetric.MetricType.ClickStream) {
            generateOperational = CounterMetric.generateClickstream(str);
        } else {
            generateOperational = CounterMetric.generateOperational(str);
        }
        if (map != null) {
            generateOperational.getMetadata().putAll(map);
        }
        recordCounterMetric(generateOperational, Double.valueOf(d));
    }

    public static void addCallType(@NonNull CommsMetric commsMetric, @NonNull CallType callType) {
        commsMetric.getMetadata().put("callType", callType.getMetricValue());
    }

    public static void addConnectionType(@NonNull Map<String, Object> map, @Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            String callConnectionType = CommsDaggerWrapper.getComponent().getCallHistoryHelper().getCallConnectionType(str);
            if (TextUtils.isEmpty(callConnectionType)) {
                return;
            }
            map.put("transport", callConnectionType);
        }
    }

    public static void addException(@NonNull CommsMetric commsMetric, @NonNull Exception exc) {
        addException(commsMetric.getMetadata(), exc);
    }
}
