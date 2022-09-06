package amazon.speech.model;

import amazon.speech.simclient.directive.Directive;
import amazon.speech.simclient.metrics.deviceeventemitter.DeviceEventEmitterSingleton;
import amazon.speech.util.DebugUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.deviceevents.model.event.UplMetric;
import com.amazon.metrics.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class UserPerceivedLatencyRecorder {
    static final long DEFAULT_TIMESTAMP = -1;
    static final String METADATA_KEY_DIRECTIVE_NAME = "directiveName";
    static final String METADATA_KEY_DIRECTIVE_NAMESPACE = "directiveNamespace";
    static final String METADATA_KEY_EVENT_ID = "eventId";
    static final String METADATA_KEY_SPEECH_INTENT_NAME = "intentName";
    static final String STRING_FIRST_BYTE_TO_PARSE_COMPLETE = "FirstByteToParseComplete";
    static final String STRING_PARSE_COMPLETE_TO_DISPATCH = "ParseCompleteToDispatch";
    static final String STRING_STOP_CAPTURE_TO_FIRST_BYTE = "StopCaptureToFirstByte";
    static final String STRING_USER_PERCEIVED_LATENCY = "UserPerceivedLatency";
    static final String STRING_UTTERANCE_END_TO_STOP_CAPTURE = "UtteranceEndToStopCapture";
    private static final String TAG = "UserPerceivedLatencyRecorder";
    private final Context mContext;
    private final IMetricConvention[] mConventions = {new ModernConvention()};
    private final Bundle mIntentExtras;
    private final MetricsUtil.MetadataHelper mMetadata;
    private final MetricsUtil mMetricsUtil;
    private final Set<UplType> mReportedMetricTypes;
    private final Map<UplType, List<DeviceProcessingSegment>> mSegments;
    private static final boolean DEBUG = DebugUtil.getShouldDebug(DebugUtil.Module.SIM);
    private static final HashSet<String> SAFE_UPL_METRIC_NAMES = new HashSet<>();

    /* renamed from: amazon.speech.model.UserPerceivedLatencyRecorder$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$model$UserPerceivedLatencyRecorder$UplType = new int[UplType.values().length];

        static {
            try {
                $SwitchMap$amazon$speech$model$UserPerceivedLatencyRecorder$UplType[UplType.Tts.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$speech$model$UserPerceivedLatencyRecorder$UplType[UplType.Audio.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$speech$model$UserPerceivedLatencyRecorder$UplType[UplType.Visual.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DeviceProcessingSegment implements Comparable<DeviceProcessingSegment> {
        public final String name;
        public final long timestamp;

        public DeviceProcessingSegment(String str, long j) {
            this.name = str;
            this.timestamp = j;
        }

        @Override // java.lang.Comparable
        public int compareTo(DeviceProcessingSegment deviceProcessingSegment) {
            return Long.compare(this.timestamp, deviceProcessingSegment.timestamp);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface IMetricConvention {
        void reportMetric(Context context, InteractionType interactionType, UplType uplType, ProcessingType processingType, String str, String str2, long j, MetricsUtil.MetadataHelper metadataHelper, MetricsUtil metricsUtil);
    }

    /* loaded from: classes.dex */
    public enum InteractionType {
        VoiceRequest
    }

    /* loaded from: classes.dex */
    static class ModernConvention implements IMetricConvention {
        ModernConvention() {
        }

        @Override // amazon.speech.model.UserPerceivedLatencyRecorder.IMetricConvention
        public void reportMetric(Context context, InteractionType interactionType, UplType uplType, ProcessingType processingType, String str, String str2, long j, MetricsUtil.MetadataHelper metadataHelper, MetricsUtil metricsUtil) {
            String name;
            String metadataValue = metadataHelper.getMetadataValue("intentName");
            String metadataValue2 = metadataHelper.getMetadataValue("directiveNamespace");
            String metadataValue3 = metadataHelper.getMetadataValue("eventId");
            if (!(!TextUtils.isEmpty(metadataValue))) {
                Log.w(UserPerceivedLatencyRecorder.TAG, String.format("%s : Intent name is empty. Proceeding nonetheless.", ModernConvention.class.getSimpleName()));
            }
            if (TextUtils.isEmpty(metadataValue2)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not publishing metric for ");
                outline107.append(ModernConvention.class.getSimpleName());
                outline107.append(" : Namespace is empty.");
                Log.w(UserPerceivedLatencyRecorder.TAG, outline107.toString());
                return;
            }
            if (metadataValue3 == null) {
                metadataValue3 = "";
            }
            String str3 = metadataValue3;
            if (uplType != UplType.Audio && uplType != UplType.Tts) {
                name = uplType.name();
            } else {
                name = UplType.Audio.name();
            }
            String join = UserPerceivedLatencyRecorder.join(".", new String[]{"UserPerceivedLatency", name, processingType != null ? processingType.name() : null, str2});
            UserPerceivedLatencyRecorder.join(".", new String[]{str, metadataValue2});
            String name2 = interactionType.name();
            UserPerceivedLatencyRecorder.join(".", new String[]{name2, metadataValue});
            metricsUtil.addTimer(context, name2, str, join, j, 1, metadataHelper);
            Log.i(UserPerceivedLatencyRecorder.TAG, "Publishing metric [" + join + "], value = " + j + " , eventID = " + str3);
            if (!UserPerceivedLatencyRecorder.SAFE_UPL_METRIC_NAMES.contains(join)) {
                return;
            }
            DeviceEventEmitterSingleton.getDeviceEventEmitter().emitUplMetric(new UplMetric(join, j, str3));
        }
    }

    /* loaded from: classes.dex */
    public enum ProcessingType {
        ServerProcessing,
        DeviceProcessing
    }

    /* loaded from: classes.dex */
    public enum UplType {
        Audio,
        Visual,
        Tts
    }

    static {
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.ServerProcessing.UtteranceEndToStopCapture");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.ServerProcessing.StopCaptureToFirstByte");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.FirstByteToParseComplete");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.ParseCompleteToDispatch");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.DispatchToAudio");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.DispatchToDispatchToTtsConnection");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.DispatchToTtsConnectionToTtsConnection");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.TtsConnectionToTtsMediaPreparation");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.TtsMediaPreparationToAudio");
        SAFE_UPL_METRIC_NAMES.add(AccessoryMetricsConstants.USER_PERCEIVED_LATENCY_AUDIO);
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.ServerProcessing");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.ServerProcessing.UtteranceEndToStopCapture");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.ServerProcessing.StopCaptureToFirstByte");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.DeviceProcessing.FirstByteToParseComplete");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.DeviceProcessing.ParseCompleteToDispatch");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.DeviceProcessing.DispatchToVisual");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.ServerProcessing");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.DeviceProcessing");
    }

    UserPerceivedLatencyRecorder(Context context, Bundle bundle, MetricsUtil metricsUtil) {
        if (context != null && bundle != null && metricsUtil != null) {
            this.mContext = context;
            this.mIntentExtras = bundle;
            this.mMetricsUtil = metricsUtil;
            this.mSegments = new HashMap();
            this.mSegments.put(UplType.Audio, new ArrayList());
            this.mSegments.put(UplType.Visual, new ArrayList());
            this.mSegments.put(UplType.Tts, new ArrayList());
            this.mReportedMetricTypes = new HashSet();
            this.mMetadata = parseMetadata(bundle);
            return;
        }
        throw new IllegalArgumentException("Parameters cannot be null.");
    }

    public static UserPerceivedLatencyRecorder createRecorder(Context context, Intent intent, MetricsUtil metricsUtil) {
        if (context != null && intent != null && metricsUtil != null) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            return new UserPerceivedLatencyRecorder(context, extras, metricsUtil);
        }
        throw new IllegalArgumentException("Parameters cannot be null.");
    }

    private long getTimestamp(String str) {
        return this.mIntentExtras.getLong(str, -1L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String join(CharSequence charSequence, String[] strArr) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                if (z) {
                    z = false;
                } else {
                    sb.append(charSequence);
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    static MetricsUtil.MetadataHelper parseMetadata(Bundle bundle) {
        MetricsUtil.MetadataHelper metadataHelper = new MetricsUtil.MetadataHelper();
        tryParseDirectiveMetadata(bundle, metadataHelper);
        tryParseUPLMetadata(bundle, metadataHelper);
        if (metadataHelper.isEmpty()) {
            Log.w(TAG, "Failed to extract metadata from intent bundle");
            return metadataHelper;
        }
        try {
            metadataHelper.addMetadata("intentName", bundle.getString("intent_name"));
        } catch (IllegalArgumentException unused) {
            Log.w(TAG, "Missing optional 'intentName' for directive, proceeding with UPL.");
        }
        return metadataHelper;
    }

    private void publishApplicationProcessingSegements(InteractionType interactionType, UplType uplType, long j) {
        if (j > 0) {
            List<DeviceProcessingSegment> list = this.mSegments.get(uplType);
            Collections.sort(list);
            long j2 = j;
            for (DeviceProcessingSegment deviceProcessingSegment : list) {
                publishTimer(interactionType, uplType, ProcessingType.DeviceProcessing, deviceProcessingSegment.name, j2, deviceProcessingSegment.timestamp);
                j2 = deviceProcessingSegment.timestamp;
            }
        }
    }

    private void publishTimer(InteractionType interactionType, UplType uplType, ProcessingType processingType, String str, long j, long j2) {
        if (j < 0) {
            Log.w(TAG, "Not publishing timer. Begin timestamp not valid.");
        } else if (j2 < 0) {
            Log.w(TAG, "Not publishing timer. End timestamp not valid.");
        } else {
            String string = this.mIntentExtras.getString("metrics_program_name");
            long j3 = j2 - j;
            IMetricConvention[] iMetricConventionArr = this.mConventions;
            int i = 0;
            for (int length = iMetricConventionArr.length; i < length; length = length) {
                iMetricConventionArr[i].reportMetric(this.mContext, interactionType, uplType, processingType, string, str, j3, this.mMetadata, this.mMetricsUtil);
                i++;
            }
        }
    }

    private static void tryParseDirectiveMetadata(Bundle bundle, MetricsUtil.MetadataHelper metadataHelper) {
        tryParseDirectiveMetadata(amazon.speech.simclient.directive.DirectiveIntent.fromIntentExtras(bundle), metadataHelper);
    }

    private static void tryParseUPLMetadata(Bundle bundle, MetricsUtil.MetadataHelper metadataHelper) {
        for (Map.Entry<String, String> entry : new HashMap<String, String>() { // from class: amazon.speech.model.UserPerceivedLatencyRecorder.1
            {
                put("intentName", "intent_name");
                put("eventId", "event_id");
                put("directiveName", "name");
                put("directiveNamespace", "namespace");
            }
        }.entrySet()) {
            try {
                metadataHelper.addMetadata(entry.getKey(), bundle.getString(entry.getValue()));
            } catch (IllegalArgumentException unused) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not reporting UPL metrics for directive. Missing '");
                outline107.append(entry.getValue());
                outline107.append("'.");
                Log.w(TAG, outline107.toString());
                return;
            }
        }
    }

    public boolean hasReportedUserPerceivedLatency(UplType uplType) {
        return this.mReportedMetricTypes.contains(uplType);
    }

    public void recordDeviceProcessingSegment(String str, long j, UplType uplType) {
        if (!TextUtils.isEmpty(str)) {
            if (uplType == null) {
                throw new IllegalArgumentException("type cannot be null.");
            }
            if (j >= 0) {
                this.mSegments.get(uplType).add(new DeviceProcessingSegment(str, j));
                return;
            }
            throw new IllegalArgumentException("timestamp must be a positive integer.");
        }
        throw new IllegalArgumentException("segmentName must not be empty.");
    }

    public void recordUserPerceivedLatency(long j, UplType uplType) {
        recordUserPerceivedLatency(j, uplType, false, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0072, code lost:
        if (r10 != 2) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean shouldPublishMetrics(amazon.speech.model.UserPerceivedLatencyRecorder.UplType r10, boolean r11, java.util.List<java.lang.String> r12) {
        /*
            r9 = this;
            com.amazon.metrics.MetricsUtil$MetadataHelper r0 = r9.mMetadata
            r1 = 0
            java.lang.String r2 = "UserPerceivedLatencyRecorder"
            if (r0 != 0) goto Ld
            java.lang.String r10 = "Not reporting UPL metrics for directive. Incomplete metadata."
            android.util.Log.i(r2, r10)
            return r1
        Ld:
            boolean r0 = r9.hasReportedUserPerceivedLatency(r10)
            if (r0 == 0) goto L19
            java.lang.String r10 = "Not reporting UPL metrics for directive. Already published."
            android.util.Log.i(r2, r10)
            return r1
        L19:
            android.os.Bundle r0 = r9.mIntentExtras
            java.lang.String r3 = "metrics_program_name"
            java.lang.String r0 = r0.getString(r3)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L2d
            java.lang.String r10 = "Not reporting UPL metrics for directive. Product name is missing."
            android.util.Log.i(r2, r10)
            return r1
        L2d:
            com.amazon.metrics.MetricsUtil$MetadataHelper r0 = r9.mMetadata
            java.lang.String r3 = "intentName"
            java.lang.String r0 = r0.getMetadataValue(r3)
            if (r12 == 0) goto L57
            boolean r12 = r12.contains(r0)
            if (r12 != 0) goto L57
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Not reporting UPL metrics for directive. '"
            r10.append(r11)
            r10.append(r0)
            java.lang.String r11 = "' is not in the supported list of intents."
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            android.util.Log.i(r2, r10)
            return r1
        L57:
            r12 = 1
            if (r11 == 0) goto L5b
            return r12
        L5b:
            java.lang.String r11 = "last_audio_directive_time"
            long r3 = r9.getTimestamp(r11)
            java.lang.String r11 = "last_visual_directive_time"
            long r5 = r9.getTimestamp(r11)
            int r10 = r10.ordinal()
            r7 = 0
            if (r10 == 0) goto L7f
            if (r10 == r12) goto L75
            r11 = 2
            if (r10 == r11) goto L7f
            goto L89
        L75:
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 <= 0) goto L89
            java.lang.String r10 = "Not reporting UPL metrics for directive. Not first in visual channel."
            android.util.Log.i(r2, r10)
            return r1
        L7f:
            int r10 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r10 <= 0) goto L89
            java.lang.String r10 = "Not reporting UPL metrics for directive. Not first in audio channel."
            android.util.Log.i(r2, r10)
            return r1
        L89:
            int r10 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r10 > 0) goto La2
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 > 0) goto La2
            java.lang.String r10 = "previous_directive_complete_time"
            long r10 = r9.getTimestamp(r10)
            int r10 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r10 <= 0) goto La2
            java.lang.String r10 = "Not reporting UPL metrics for directive. Not first in sequence."
            android.util.Log.i(r2, r10)
            return r1
        La2:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: amazon.speech.model.UserPerceivedLatencyRecorder.shouldPublishMetrics(amazon.speech.model.UserPerceivedLatencyRecorder$UplType, boolean, java.util.List):boolean");
    }

    public void recordUserPerceivedLatency(long j, UplType uplType, boolean z, List<String> list) {
        if (uplType != null) {
            if (j >= 0) {
                try {
                    Trace.beginSection("recordUserPerceivedLatency");
                    if (!shouldPublishMetrics(uplType, z, list)) {
                        Log.i(TAG, "Not publishing UPL metrics for directive.");
                        return;
                    }
                    InteractionType interactionType = InteractionType.VoiceRequest;
                    long timestamp = getTimestamp("end_of_speech_time");
                    long timestamp2 = getTimestamp("stop_capture_time");
                    long timestamp3 = getTimestamp("directive_first_byte_time");
                    long timestamp4 = getTimestamp("directive_parse_complete_time");
                    long timestamp5 = getTimestamp("directive_dispatch_time");
                    publishTimer(interactionType, uplType, null, null, timestamp, j);
                    publishTimer(interactionType, uplType, ProcessingType.ServerProcessing, null, timestamp, timestamp3);
                    publishTimer(interactionType, uplType, ProcessingType.ServerProcessing, STRING_UTTERANCE_END_TO_STOP_CAPTURE, timestamp, timestamp2);
                    publishTimer(interactionType, uplType, ProcessingType.ServerProcessing, STRING_STOP_CAPTURE_TO_FIRST_BYTE, timestamp2, timestamp3);
                    publishTimer(interactionType, uplType, ProcessingType.DeviceProcessing, null, timestamp3, j);
                    publishTimer(interactionType, uplType, ProcessingType.DeviceProcessing, STRING_FIRST_BYTE_TO_PARSE_COMPLETE, timestamp3, timestamp4);
                    publishTimer(interactionType, uplType, ProcessingType.DeviceProcessing, STRING_PARSE_COMPLETE_TO_DISPATCH, timestamp4, timestamp5);
                    publishApplicationProcessingSegements(interactionType, uplType, timestamp5);
                    this.mReportedMetricTypes.add(uplType);
                    return;
                } finally {
                    Trace.endSection();
                }
            }
            throw new IllegalArgumentException("timestamp must be a positive integer.");
        }
        throw new IllegalArgumentException("type cannot be null.");
    }

    static void tryParseDirectiveMetadata(Directive directive, MetricsUtil.MetadataHelper metadataHelper) {
        if (directive == null) {
            Log.w(TAG, "Unable to extract directive from intent");
            return;
        }
        String sequenceId = directive.getSequenceId();
        String namespace = directive.getNamespace();
        String name = directive.getName();
        if (sequenceId != null && !sequenceId.isEmpty()) {
            if (namespace != null && !namespace.isEmpty()) {
                if (name != null && !name.isEmpty()) {
                    metadataHelper.addMetadata("eventId", sequenceId);
                    metadataHelper.addMetadata("directiveNamespace", namespace);
                    metadataHelper.addMetadata("directiveName", name);
                    return;
                }
                Log.w(TAG, "Not reporting UPL metrics for directive. Missing 'name'.");
                return;
            }
            Log.w(TAG, "Not reporting UPL metrics for directive. Missing 'namespace'.");
            return;
        }
        Log.w(TAG, "Not reporting UPL metrics for directive. Missing 'eventId'.");
    }
}
