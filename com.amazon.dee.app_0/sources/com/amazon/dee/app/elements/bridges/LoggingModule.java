package com.amazon.dee.app.elements.bridges;

import android.text.TextUtils;
import com.amazon.alexa.voice.tta.Constants;
import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.SingleEventInputArgument;
import com.amazon.latencyinfra.SingleLatencyAction;
import com.amazon.latencyinfra.TimelineInputArgument;
import com.amazon.latencyinfra.TimelineLatencyAction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.wav.WavDirectory;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.module.annotations.ReactModule;
import dagger.Lazy;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ReactModule(name = LoggingModule.MODULE_NAME)
/* loaded from: classes12.dex */
public class LoggingModule extends ReactContextBaseJavaModule {
    private static final String ELEMENTS_LOG_PREFIX = "[ELEMENTS]";
    private static final String END_TIMESTAMP = "endTimestamp";
    private static final String EVENTS = "events";
    private static final String IS_INTERNAL_BUILD = "isInternalBuild";
    private static final String KEY_CUSTOMER_OPTION = "customer";
    private static final String KEY_LOG_OPTION = "log";
    private static final String KEY_METRIC_OPTION = "metric";
    private static final String KEY_PERFTEST_OPTION = "perfTest";
    private static final String KEY_SINGLE_EVENT = "single";
    private static final String KEY_TIMELINE_EVENT = "timeline";
    private static final String METADATA = "metadata";
    private static final String MODULE_NAME = "Logging";
    private static final String NAME = "name";
    private static final String NAMESPACE = "namespace";
    private static final String OPTIONS = "options";
    private static final String SINGLE_TIMESTMP = "startTimestamp";
    private static final String START_TIMESTAMP = "startTimestamp";
    private static final String TYPE = "type";
    private final Lazy<CertificateReaderService> certificateReaderService;
    private final LatencyInfra latencyInfra;

    /* renamed from: com.amazon.dee.app.elements.bridges.LoggingModule$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public LoggingModule(ReactApplicationContext reactApplicationContext, LatencyInfra latencyInfra, Lazy<CertificateReaderService> lazy) {
        super(reactApplicationContext);
        this.latencyInfra = latencyInfra;
        this.certificateReaderService = lazy;
    }

    private void addOptionsToSingleEventArgument(SingleEventInputArgument.Builder builder, List<Object> list) {
        for (Object obj : list) {
            String valueOf = String.valueOf(obj);
            char c = 65535;
            switch (valueOf.hashCode()) {
                case -1077545552:
                    if (valueOf.equals(KEY_METRIC_OPTION)) {
                        c = 2;
                        break;
                    }
                    break;
                case 107332:
                    if (valueOf.equals(KEY_LOG_OPTION)) {
                        c = 1;
                        break;
                    }
                    break;
                case 430346011:
                    if (valueOf.equals(KEY_PERFTEST_OPTION)) {
                        c = 0;
                        break;
                    }
                    break;
                case 606175198:
                    if (valueOf.equals(KEY_CUSTOMER_OPTION)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.withPerfTestOption(true);
            } else if (c == 1) {
                builder.withLogOption(true);
            } else if (c == 2) {
                builder.withMetricsOption(true);
            } else if (c != 3) {
                Log.w("[Latency Infra]:", String.valueOf(obj) + " is not a valid option.");
            } else {
                builder.withCustomerOption(true);
            }
        }
    }

    private void addOptionsToTimelineEventArgument(TimelineInputArgument.Builder builder, List<Object> list) {
        for (Object obj : list) {
            String valueOf = String.valueOf(obj);
            char c = 65535;
            switch (valueOf.hashCode()) {
                case -1077545552:
                    if (valueOf.equals(KEY_METRIC_OPTION)) {
                        c = 2;
                        break;
                    }
                    break;
                case 107332:
                    if (valueOf.equals(KEY_LOG_OPTION)) {
                        c = 1;
                        break;
                    }
                    break;
                case 430346011:
                    if (valueOf.equals(KEY_PERFTEST_OPTION)) {
                        c = 0;
                        break;
                    }
                    break;
                case 606175198:
                    if (valueOf.equals(KEY_CUSTOMER_OPTION)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.withPerfTestOption(true);
            } else if (c == 1) {
                builder.withLogOption(true);
            } else if (c == 2) {
                builder.withMetricsOption(true);
            } else if (c != 3) {
                Log.w("[Latency Infra]:", String.valueOf(obj) + " is not a valid option.");
            } else {
                builder.withCustomerOption(true);
            }
        }
    }

    private boolean hasNonNullValueWithType(ReadableMap readableMap, String str, ReadableType readableType) {
        return readableMap.hasKey(str) && !readableMap.isNull(str) && readableMap.getType(str).equals(readableType);
    }

    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    private Map<String, Long> processArgumentEvents(ReadableMap readableMap, Promise promise) {
        HashMap hashMap = new HashMap();
        ReadableMap mo6945getMap = readableMap.mo6945getMap("events");
        ReadableMapKeySetIterator keySetIterator = mo6945getMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            if (mo6945getMap.getType(nextKey).ordinal() != 2) {
                promise.reject("[Latency Infra]:", "events should only contain numbers.");
                return null;
            }
            hashMap.put(nextKey, Long.valueOf(Math.round(mo6945getMap.getDouble(nextKey))));
        }
        return hashMap;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put(IS_INTERNAL_BUILD, Boolean.valueOf(this.certificateReaderService.mo358get().isDebugSigned()));
        return hashMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void log(String str, String str2, ReadableArray readableArray) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str2);
        if (readableArray != null) {
            outline107.append(TextUtils.join(" ", ((ReadableNativeArray) readableArray).toArrayList()));
        }
        String upperCase = str.toUpperCase();
        char c = 65535;
        switch (upperCase.hashCode()) {
            case 2251950:
                if (upperCase.equals(WavDirectory.LIST_INFO)) {
                    c = 1;
                    break;
                }
                break;
            case 2656902:
                if (upperCase.equals(DriveModeVoxUiConstants.WARN)) {
                    c = 2;
                    break;
                }
                break;
            case 64921139:
                if (upperCase.equals("DEBUG")) {
                    c = 0;
                    break;
                }
                break;
            case 66247144:
                if (upperCase.equals("ERROR")) {
                    c = 3;
                    break;
                }
                break;
        }
        if (c == 0) {
            outline107.toString();
        } else if (c == 1) {
            Log.i(ELEMENTS_LOG_PREFIX, outline107.toString());
        } else if (c == 2) {
            Log.w(ELEMENTS_LOG_PREFIX, outline107.toString());
        } else if (c != 3) {
            Log.wtf(ELEMENTS_LOG_PREFIX, outline107.toString());
        } else {
            Log.e(ELEMENTS_LOG_PREFIX, outline107.toString());
        }
    }

    @ReactMethod
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public void processLatencyEvent(ReadableMap readableMap, Promise promise) {
        if (!hasNonNullValueWithType(readableMap, "options", ReadableType.Array)) {
            promise.reject("[Latency Infra]:", "Options should not be undefined and should be an array.");
        } else if (!hasNonNullValueWithType(readableMap, "namespace", ReadableType.String)) {
            promise.reject("[Latency Infra]:", "Namespace should not be undefined and should be a string.");
        } else if (!hasNonNullValueWithType(readableMap, "type", ReadableType.String)) {
            promise.reject("[Latency Infra]:", "Type should not be undefined and should be a string.");
        } else if (!hasNonNullValueWithType(readableMap, "name", ReadableType.String)) {
            promise.reject("[Latency Infra]:", "Event name should not be undefined and should be a string.");
        } else {
            ArrayList<Object> arrayList = readableMap.getArray("options").toArrayList();
            String obj = (!readableMap.hasKey("metadata") || readableMap.mo6945getMap("metadata") == null) ? "" : readableMap.mo6945getMap("metadata").toString();
            String string = readableMap.getString("type");
            char c = 65535;
            int hashCode = string.hashCode();
            if (hashCode != -2076650431) {
                if (hashCode == -902265784 && string.equals(KEY_SINGLE_EVENT)) {
                    c = 0;
                }
            } else if (string.equals("timeline")) {
                c = 1;
            }
            if (c != 0) {
                if (c != 1) {
                    promise.reject("[Latency Infra]:", "Illegal latency type argument.");
                    return;
                } else if (!hasNonNullValueWithType(readableMap, Constants.IntentParameters.START_TIMESTAMP, ReadableType.Number)) {
                    promise.reject("[Latency Infra]:", "Start timestamp should not be undefined and should be a number.");
                    return;
                } else if (!hasNonNullValueWithType(readableMap, END_TIMESTAMP, ReadableType.Number)) {
                    promise.reject("[Latency Infra]:", "End timestamp should not be undefined and should be a number for timeline event.");
                    return;
                } else {
                    TimelineInputArgument.Builder withEndTimestamp = new TimelineInputArgument.Builder().withNamespace(readableMap.getString("namespace")).withTimelineName(readableMap.getString("name")).withStartTimestamp(Long.valueOf(Math.round(readableMap.getDouble(Constants.IntentParameters.START_TIMESTAMP)))).withEndTimestamp(Long.valueOf(Math.round(readableMap.getDouble(END_TIMESTAMP))));
                    withEndTimestamp.withMetaData(obj);
                    if (readableMap.hasKey("events")) {
                        withEndTimestamp.withEvents(processArgumentEvents(readableMap, promise));
                    }
                    addOptionsToTimelineEventArgument(withEndTimestamp, arrayList);
                    try {
                        this.latencyInfra.recordTimeline(TimelineLatencyAction.RECORD_TIMELINE_WITH_CACHED_START_AND_END_TIMESTAMP, withEndTimestamp.build());
                    } catch (Exception e) {
                        promise.reject("[Latency Infra]:", e.getMessage());
                    }
                }
            } else if (!hasNonNullValueWithType(readableMap, Constants.IntentParameters.START_TIMESTAMP, ReadableType.Number)) {
                promise.reject("[Latency Infra]:", "Start timestamp should not be undefined and should be a number.");
                return;
            } else {
                SingleEventInputArgument.Builder withEndTimestamp2 = new SingleEventInputArgument.Builder().withNamespace(readableMap.getString("namespace")).withEventName(readableMap.getString("name")).withEndTimestamp(Long.valueOf(Math.round(readableMap.getDouble(Constants.IntentParameters.START_TIMESTAMP))));
                withEndTimestamp2.withMetaData(obj);
                addOptionsToSingleEventArgument(withEndTimestamp2, arrayList);
                try {
                    this.latencyInfra.record(SingleLatencyAction.RECORD_DURATION_FROM_CACHED_TIMESTAMP, withEndTimestamp2.build());
                } catch (Exception e2) {
                    promise.reject("[Latency Infra]:", e2.getMessage());
                    return;
                }
            }
            promise.resolve(null);
        }
    }
}
