package com.amazon.deecomms.calling.controller;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.sipclient.CallQualityMetrics;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.dagger.RingService;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.model.AbstractCallModel;
import com.amazon.deecomms.calling.model.CallQualityMetricsModel;
import com.amazon.deecomms.calling.model.CallingMetricsModel;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Iso8601DateFormatter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Locale;
/* loaded from: classes12.dex */
public class CallMetricsFactory {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallMetricsFactory.class);
    private final ApplicationManager applicationManager;
    private final CallHistoryHelper callHistoryHelper;
    private final Context context;
    private final Iso8601DateFormatter iso8601DateFormatter = new Iso8601DateFormatter();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.deecomms.calling.controller.CallMetricsFactory$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$Call$Side = new int[Call.Side.values().length];

        static {
            try {
                $SwitchMap$com$amazon$comms$calling$service$Call$Side[Call.Side.Local.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$Call$Side[Call.Side.Remote.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public CallMetricsFactory(@NonNull CallHistoryHelper callHistoryHelper, @NonNull ApplicationManager applicationManager, @NonNull Context context) {
        this.callHistoryHelper = callHistoryHelper;
        this.applicationManager = applicationManager;
        this.context = context;
    }

    private void addDeviceInfo(@NonNull AbstractCallModel abstractCallModel, @NonNull Call call) {
        abstractCallModel.setCallId(call.getCallId());
        abstractCallModel.setPlatform(Utils.getOSType());
        long currentTimeMillis = System.currentTimeMillis();
        abstractCallModel.setCreationDate(currentTimeMillis);
        abstractCallModel.setLocalTime(this.iso8601DateFormatter.formatDateTime(currentTimeMillis));
        abstractCallModel.setDeviceMake(Build.MANUFACTURER);
        abstractCallModel.setDeviceModel(Build.MODEL);
        abstractCallModel.setDeviceScreenSize(DeviceInfo.getDeviceScreenSize(this.context));
    }

    private static boolean validateCallLatency(long j, long j2) {
        return (j2 == 0 && j == 0) ? false : true;
    }

    @NonNull
    public CallQualityMetricsModel createCallQualityMetrics(@NonNull Call call) {
        long j;
        String callId = call.getCallId();
        CallQualityMetrics callQualityMetrics = RingService.INSTANCE.getCallQualityMetrics(callId);
        if (callQualityMetrics == null) {
            GeneratedOutlineSupport.outline3("sendCallQualityMetrics - no CallQualityMetrics found with callId ", callId, LOG);
        } else {
            CallQualityMetrics.CodecData codecData = callQualityMetrics.getCodecData();
            if (codecData != null) {
                if (callQualityMetrics.getVideo() != null) {
                    MetricsHelper.recordMetricWithMetadata(MetricKeys.VIDEO_CODEC_USED, "source", codecData.getVideoCodec());
                }
                MetricsHelper.recordMetricWithMetadata(MetricKeys.AUDIO_CODEC_USED, "source", codecData.getAudioCodec());
            }
        }
        CallQualityMetricsModel callQualityMetricsModel = new CallQualityMetricsModel(callQualityMetrics);
        addDeviceInfo(callQualityMetricsModel, call);
        int callStatusCode = this.callHistoryHelper.getCallStatusCode(callId);
        if (callStatusCode == -1) {
            callStatusCode = ErrorCode.Unknown.getValue();
        }
        callQualityMetricsModel.setStatusCode(Integer.toString(callStatusCode));
        callQualityMetricsModel.setIsCaller(call.getOrigin() == Call.Side.Local);
        callQualityMetricsModel.setDeviceType(this.applicationManager.getDeviceTypeId());
        callQualityMetricsModel.setAnsweredCall(this.callHistoryHelper.didUserAnswerCall(callId));
        callQualityMetricsModel.setUserEndedCall(this.callHistoryHelper.didUserEndCall(callId));
        long j2 = 0;
        if (call.getCallDetails() == null || call.getCallDetails().getCallConnectedTime() == null) {
            j = 0;
        } else {
            j = call.getCallDetails().getCallCompletedTime() != null ? call.getCallDetails().getCallCompletedTime().getMillis() - call.getCallDetails().getCallConnectedTime().getMillis() : 0L;
            if (call.getCallDetails().getCallStartTime() != null) {
                j2 = call.getCallDetails().getCallConnectedTime().getMillis() - call.getCallDetails().getCallStartTime().getMillis();
            }
        }
        callQualityMetricsModel.setCallTotalDurationInMillis(j);
        callQualityMetricsModel.setCallConnectDurationInMillis(j2);
        if (callQualityMetrics != null && callQualityMetrics.getAudio() != null && validateCallLatency(callQualityMetrics.getAudio().getCallTxTotalBytes(), callQualityMetrics.getAudio().getCallRxTotalBytes())) {
            TimerMetric timerMetric = new TimerMetric(CommsMetric.MetricType.Operational, MetricKeys.CALL_IN_CALL_LATENCY);
            MetricsHelper.addCallAttributes(timerMetric, call);
            timerMetric.setTimeDelta(callQualityMetrics.getAudio().getCallRoundTripDelayUsec() / 1000);
            timerMetric.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, callId);
            if (Call.Side.Local == call.getOrigin()) {
                timerMetric.getMetadata().put("direction", MetricKeys.VALUE_DIRECTION_OUTGOING);
            } else {
                timerMetric.getMetadata().put("direction", MetricKeys.VALUE_DIRECTION_INCOMING);
            }
            MetricsHelper.recordTimerMetric(timerMetric);
        } else {
            CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.CALL_IN_CALL_LATENCY_INVALID);
            generateOperational.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, callId);
            MetricsHelper.recordSingleOccurrence(generateOperational);
        }
        return callQualityMetricsModel;
    }

    @NonNull
    public CallingMetricsModel createCallingMetrics(@NonNull Call call) {
        ContactPhoneNumber calleePhoneNumber;
        String callId = call.getCallId();
        CallType callType = this.callHistoryHelper.getCallType(callId);
        CallingMetricsModel callingMetricsModel = new CallingMetricsModel();
        addDeviceInfo(callingMetricsModel, call);
        callingMetricsModel.setCountry(Locale.getDefault().getCountry());
        int callStatusCode = this.callHistoryHelper.getCallStatusCode(callId);
        if (callStatusCode == -1) {
            callStatusCode = ErrorCode.Unknown.getValue();
        }
        callingMetricsModel.setStatusCode(Integer.toString(callStatusCode));
        callingMetricsModel.setCallStartTime(call.getCallDetails().getCallStartTime().getMillis());
        callingMetricsModel.setCallEndTime(call.getCallDetails().getCallCompletedTime().getMillis());
        callingMetricsModel.setA2A(callType.isA2A());
        callingMetricsModel.setDropIn(call.isDropInCall());
        String deviceTypeId = this.applicationManager.getDeviceTypeId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$comms$calling$service$Call$Side[call.getOrigin().ordinal()];
        if (i == 1) {
            callingMetricsModel.setCallerId(call.getLocalParticipant().getProviderSpecifiedId());
            callingMetricsModel.setCalleeId(call.getRemoteParticipant().getProviderSpecifiedId());
            callingMetricsModel.setCallerDeviceType(deviceTypeId);
            callingMetricsModel.setDeviceTargeted(Boolean.valueOf(callType.isDeviceTargeted()));
        } else if (i != 2) {
            LOG.w("Unable to detect origin of call, not including callerId and calleeId");
        } else {
            callingMetricsModel.setCallerId(call.getRemoteParticipant().getProviderSpecifiedId());
            callingMetricsModel.setCalleeId(call.getLocalParticipant().getProviderSpecifiedId());
            callingMetricsModel.setCalleeDeviceType(deviceTypeId);
        }
        String callProvider = this.callHistoryHelper.getCallProvider(callId);
        if ("".equalsIgnoreCase(callProvider)) {
            callProvider = callType.isA2A() ? CallProvider.A2A : CallProvider.COBO;
        }
        callingMetricsModel.setCallProvider(callProvider);
        if (callType == CallType.PSTN && (calleePhoneNumber = this.callHistoryHelper.getCalleePhoneNumber(callId)) != null) {
            callingMetricsModel.setCalleePhoneNumber(calleePhoneNumber.getObfuscatedPhoneNumber());
        }
        return callingMetricsModel;
    }
}
