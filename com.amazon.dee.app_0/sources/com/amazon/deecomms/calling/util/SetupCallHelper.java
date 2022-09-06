package com.amazon.deecomms.calling.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.enums.AssistCspId;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.model.response.CallInitResponse;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes12.dex */
public final class SetupCallHelper {
    public static final int CONDITIONAL_REQUEST_FAILED = 412;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SetupCallHelper.class);
    private static final String FILE_NAME = SetupCallHelper.class.getSimpleName() + ".java";

    /* renamed from: com.amazon.deecomms.calling.util.SetupCallHelper$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily = new int[HttpStatusCodeFamily.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.INFORMATIONAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.REDIRECTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.CLIENT_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.SERVER_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum DeviceType {
        EchoAuto("EchoAuto"),
        Vox("Vox"),
        GUI("GUI");
        
        private final String name;

        DeviceType(@NonNull String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    /* loaded from: classes12.dex */
    public static class MetadataBuilder {
        private Call.Side callOrigin;
        private CallType callType;
        private DeviceType deviceType;
        private final Map<String, Object> metadata = Maps.newHashMap();
        private String reason;
        private String requestId;
        private Source source;
        private Transport transport;

        private static StackTraceElement findCallingMethod() {
            Iterator it2 = Arrays.asList(Thread.currentThread().getStackTrace()).iterator();
            while (it2.hasNext() && !SetupCallHelper.FILE_NAME.equals(((StackTraceElement) it2.next()).getFileName())) {
            }
            while (it2.hasNext()) {
                StackTraceElement stackTraceElement = (StackTraceElement) it2.next();
                if (!SetupCallHelper.FILE_NAME.equals(stackTraceElement.getFileName())) {
                    return stackTraceElement;
                }
            }
            return null;
        }

        public static MetadataBuilder newBuilder() {
            return new MetadataBuilder();
        }

        public Map<String, Object> build() {
            HashMap newHashMap = Maps.newHashMap();
            newHashMap.putAll(this.metadata);
            Source source = this.source;
            if (source != null) {
                newHashMap.put("source", source);
            }
            CallType callType = this.callType;
            if (callType != null) {
                newHashMap.put("callType", callType.getMetricValue());
            }
            Call.Side side = this.callOrigin;
            if (side != null) {
                newHashMap.put("direction", CallUtils.getCallSideMetricValue(side));
            }
            String str = this.requestId;
            if (str != null) {
                newHashMap.put("requestId", str);
            }
            DeviceType deviceType = this.deviceType;
            if (deviceType != null) {
                newHashMap.put("DeviceType", deviceType);
            }
            Transport transport = this.transport;
            if (transport != null) {
                newHashMap.put("transport", transport);
            }
            if (this.reason != null) {
                StackTraceElement findCallingMethod = findCallingMethod();
                if (findCallingMethod != null) {
                    newHashMap.put("errorSource", String.format("%s-%s: %s", findCallingMethod.getClassName(), findCallingMethod.getMethodName(), this.reason));
                } else {
                    newHashMap.put("errorSource", this.reason);
                }
            }
            return newHashMap;
        }

        public MetadataBuilder withCallOrigin(@NonNull Call.Side side) {
            this.callOrigin = side;
            return this;
        }

        public MetadataBuilder withCallType(@NonNull CallType callType) {
            this.callType = callType;
            return this;
        }

        public MetadataBuilder withDeviceType(@NonNull DeviceType deviceType) {
            this.deviceType = deviceType;
            return this;
        }

        public MetadataBuilder withReason(@NonNull String str) {
            this.reason = str;
            return this;
        }

        public MetadataBuilder withRequestId(@NonNull String str) {
            this.requestId = str;
            return this;
        }

        public MetadataBuilder withSource(@NonNull Source source) {
            this.source = source;
            return this;
        }

        public MetadataBuilder withTransport(@NonNull Transport transport) {
            this.transport = transport;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public enum ResultType {
        SUCCESS,
        EXPECTED,
        UNEXPECTED,
        UNKNOWN,
        CANCELLED
    }

    /* loaded from: classes12.dex */
    public enum Source {
        SipCallPreparation("SipCallPreparation"),
        FetchAor("fetchAOR"),
        FetchStunTurnIce("fetchSTUNTURNICE"),
        Connected("connectedState"),
        Disconnected("disconnectedState"),
        ActivePstnCall("activePSTNCall"),
        ActiveCommsCall("activeCommsCall");
        
        private final String name;

        Source(@NonNull String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    /* loaded from: classes12.dex */
    public enum Transport {
        EchoAutoTopologyA("EchoAutoTopologyA"),
        EchoAutoTopologyB("EchoAutoTopologyB"),
        EchoAutoTopologyC("EchoAutoTopologyC");
        
        private final String name;

        Transport(@NonNull String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    private SetupCallHelper() {
    }

    public static void addPCCInformationForCalling(@NonNull MetadataBuilder metadataBuilder, boolean z) {
        PCCContextProvider pCCContextProvider = CommsDaggerWrapper.getComponent().getPCCContextProvider();
        if (z) {
            if (Utils.areAccessoriesConnected()) {
                metadataBuilder.withDeviceType(DeviceType.EchoAuto);
                if (pCCContextProvider.isHFPPCCCompliantAccessorySessionAvailable()) {
                    metadataBuilder.withTransport(Transport.EchoAutoTopologyA);
                    return;
                } else if (pCCContextProvider.isA2DPPCCCompliantAccessorySessionAvailable()) {
                    metadataBuilder.withTransport(Transport.EchoAutoTopologyC);
                    return;
                } else {
                    metadataBuilder.withTransport(Transport.EchoAutoTopologyB);
                    return;
                }
            }
            metadataBuilder.withDeviceType(DeviceType.Vox);
            return;
        }
        metadataBuilder.withDeviceType(DeviceType.GUI);
    }

    public static void recordInitiationMetrics(@Nullable String str, @Nullable String str2, @NonNull ResultType resultType, @NonNull MetadataBuilder metadataBuilder) {
        recordInitiationMetrics(str, str2, resultType, null, metadataBuilder);
    }

    private static void recordMetrics(@NonNull String str, double d, @Nullable String str2, @Nullable Integer num, @NonNull Map<String, Object> map) {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(str);
        Map<String, Object> metadata = generateClickstream.getMetadata();
        if (!TextUtils.isEmpty(str2)) {
            metadata.put(MetricKeys.META_COMMS_ITEM_ID, str2);
            MetricsHelper.addConnectionType(generateClickstream, str2);
        }
        if (num != null) {
            metadata.put("statusCode", num);
        }
        metadata.putAll(map);
        MetricsHelper.recordCounterMetric(generateClickstream, Double.valueOf(d));
    }

    public static void recordInitiationMetrics(@Nullable String str, @Nullable String str2, @NonNull ResultType resultType, @Nullable Integer num, @NonNull MetadataBuilder metadataBuilder) {
        boolean z = true;
        if (str != null && CallUtils.isCallIdValid(str)) {
            CallHistoryHelper callHistoryHelper = CommsDaggerWrapper.getComponent().getCallHistoryHelper();
            if (callHistoryHelper.wereCallInitMetricsRecorded(str)) {
                GeneratedOutlineSupport1.outline159("Call initiation metrics already recorded for ", str, LOG);
                return;
            }
            callHistoryHelper.setCallInitMetricsWereRecorded(str, true);
        }
        Map<String, Object> build = metadataBuilder.build();
        if (metadataBuilder.callType == null || !metadataBuilder.callType.isFromVox()) {
            z = false;
        }
        addPCCInformationForCalling(metadataBuilder, z);
        if (resultType == ResultType.CANCELLED) {
            recordMetrics("comms.call.initiate.cancel", 1.0d, str, num, build);
            return;
        }
        if (num != null) {
            if (num.intValue() != SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode() && num.intValue() != SipStatusCode.BUSY_HERE.getCode() && num.intValue() != SipStatusCode.BUSY_EVERYWHERE.getCode() && num.intValue() != SipStatusCode.DECLINE.getCode() && num.intValue() != SipStatusCode.REQUEST_TERMINATED.getCode()) {
                if (num.intValue() == SipStatusCode.SERVICE_UNAVAILABLE.getCode()) {
                    resultType = ResultType.EXPECTED;
                } else if (num.intValue() >= 900 && num.intValue() <= 999) {
                    int intValue = num.intValue();
                    if (intValue == 902) {
                        resultType = ResultType.EXPECTED;
                    } else if (intValue != 904) {
                        resultType = ResultType.UNEXPECTED;
                    } else {
                        resultType = ResultType.EXPECTED;
                    }
                }
            } else {
                resultType = ResultType.SUCCESS;
            }
        }
        if (!TextUtils.isEmpty(str)) {
            build.put(MetricKeys.META_COMMS_ITEM_ID, str);
            MetricsHelper.addConnectionType(build, str);
        }
        CallInitResponse callInitResponse = new CallInitResponse(num, null, resultType);
        MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.ClickStream, MetricKeys.CALL_INITIATE, callInitResponse, build);
        if (str2 == null || !AssistCspId.isValidCsp(str2)) {
            return;
        }
        MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.ClickStream, MetricKeys.ASSIST_CALL, callInitResponse, build);
    }

    public static void recordInitiationMetrics(@Nullable String str, @Nullable String str2, int i, @NonNull MetadataBuilder metadataBuilder) {
        ResultType resultType;
        int ordinal = HttpStatusCodeFamily.familyFromStatusCode(Integer.valueOf(i)).ordinal();
        if (ordinal == 1 || ordinal == 2 || ordinal == 3) {
            resultType = ResultType.SUCCESS;
        } else if (ordinal == 4) {
            resultType = ResultType.EXPECTED;
        } else if (ordinal != 5) {
            resultType = ResultType.UNKNOWN;
        } else {
            resultType = ResultType.UNEXPECTED;
        }
        recordInitiationMetrics(str, str2, resultType, Integer.valueOf(i), metadataBuilder);
    }

    public static void recordInitiationMetrics(@Nullable String str, @Nullable String str2, SipStatusCode sipStatusCode, @NonNull MetadataBuilder metadataBuilder) {
        recordInitiationMetrics(str, str2, sipStatusCode.getCode(), metadataBuilder);
    }
}
