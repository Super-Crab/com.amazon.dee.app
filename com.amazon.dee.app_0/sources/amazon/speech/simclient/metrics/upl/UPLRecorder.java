package amazon.speech.simclient.metrics.upl;

import amazon.speech.simclient.directive.Directive;
import amazon.speech.simclient.directive.DirectiveIntent;
import amazon.speech.simclient.metrics.MetricsClient;
import amazon.speech.simclient.metrics.MetricsRecord;
import amazon.speech.simclient.metrics.deviceeventemitter.DeviceEventEmitterSingleton;
import amazon.speech.simclient.metrics.upl.ProcessingPoint;
import amazon.speech.simclient.metrics.upl.UPLConstants;
import amazon.speech.simclient.metrics.upl.data.InteractionRequestData;
import amazon.speech.simclient.metrics.upl.data.RequestData;
import amazon.speech.util.DebugUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.deviceevents.model.event.UplMetric;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class UPLRecorder {
    static final long DEFAULT_TIMESTAMP = -1;
    static final String METADATA_KEY_DIRECTIVE_NAME = "directiveName";
    static final String METADATA_KEY_DIRECTIVE_NAMESPACE = "directiveNamespace";
    static final String METADATA_KEY_EVENT_ID = "eventId";
    static final String METADATA_KEY_INTERACTION_LABEL = "interactionLabel";
    static final String METADATA_KEY_SPEECH_INTENT_NAME = "intentName";
    static final String STRING_PROCESSING_POINT_DISPATCH = "Dispatch";
    static final String STRING_PROCESSING_POINT_FIRST_BYTE = "FirstByte";
    static final String STRING_PROCESSING_POINT_PARSE_COMPLETE = "ParseComplete";
    static final String STRING_SEGMENT_FORMAT = "%sTo%s";
    static final String STRING_USER_PERCEIVED_LATENCY = "UserPerceivedLatency";
    private static final String TAG = "UPLRecorder";
    private final IMetricConvention[] mConventions = {new ModernConvention()};
    private final String mInteractionLabel;
    private final long mLastAudioDirectiveTime;
    private final long mLastMediaDirectiveTime;
    private final long mLastVisualDirectiveTime;
    private final MetricsRecord.Metadata mMetadata;
    private final MetricsClient mMetricsUtil;
    private final String mProductName;
    private final Set<UplType> mReportedMetricTypes;
    private RequestData.Type mRequestType;
    private final Map<UplType, List<ProcessingPoint>> mSegments;
    private final List<ProcessingPoint> mSystemProcessingPoints;
    private static final boolean DEBUG = DebugUtil.getShouldDebug(null);
    private static final HashSet<String> SAFE_UPL_METRIC_NAMES = new HashSet<>();
    static final Map<String, String> BUNDLE_TO_METADATA_KEYS = new HashMap();
    static final Map<String, String> OPTION_BUNDLE_TO_METADATA_KEYS = new HashMap();

    /* renamed from: amazon.speech.simclient.metrics.upl.UPLRecorder$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$simclient$metrics$upl$UPLRecorder$UplType = new int[UplType.values().length];

        static {
            try {
                $SwitchMap$amazon$speech$simclient$metrics$upl$UPLRecorder$UplType[UplType.Audio.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$metrics$upl$UPLRecorder$UplType[UplType.Visual.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$metrics$upl$UPLRecorder$UplType[UplType.Media.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface IMetricConvention {
        void reportMetric(RequestData.Type type, UplType uplType, ProcessingPoint.ProcessingType processingType, String str, String str2, long j, MetricsRecord.Metadata metadata, MetricsClient metricsClient);
    }

    /* loaded from: classes.dex */
    static class ModernConvention implements IMetricConvention {
        ModernConvention() {
        }

        @Override // amazon.speech.simclient.metrics.upl.UPLRecorder.IMetricConvention
        public void reportMetric(RequestData.Type type, UplType uplType, ProcessingPoint.ProcessingType processingType, String str, String str2, long j, MetricsRecord.Metadata metadata, MetricsClient metricsClient) {
            String str3 = metadata.get("intentName");
            String str4 = metadata.get("directiveNamespace");
            String str5 = metadata.get("eventId");
            String str6 = metadata.get("interactionLabel");
            if (!(!TextUtils.isEmpty(str3))) {
                Log.w(UPLRecorder.TAG, ModernConvention.class.getSimpleName() + " : Intent name is empty.");
            }
            if (TextUtils.isEmpty(str4)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not publishing metric for ");
                outline107.append(ModernConvention.class.getSimpleName());
                outline107.append(" : Namespace is empty.");
                Log.w(UPLRecorder.TAG, outline107.toString());
                return;
            }
            if (str5 == null) {
                str5 = "";
            }
            String join = UPLRecorder.join(".", new String[]{"UserPerceivedLatency", uplType.name(), processingType != null ? processingType.name() : null, str2});
            String description = type.getDescription();
            String join2 = UPLRecorder.join(".", new String[]{description, str6});
            metricsClient.obtainTimer(join).setProgramName(str).setSourceName(description).setDuration(j).addMetadata(metadata).record();
            if (!TextUtils.isEmpty(str6)) {
                metricsClient.obtainTimer(join).setProgramName(str).setSourceName(join2).setDuration(j).addMetadata(metadata).record();
                Log.i(UPLRecorder.TAG, "Publishing metric [" + join + "], value = " + j + " , sourceName = " + join2);
            }
            Log.i(UPLRecorder.TAG, "Publishing metric [" + join + "], value = " + j + " , eventID = " + str5);
            if (!UPLRecorder.SAFE_UPL_METRIC_NAMES.contains(join)) {
                return;
            }
            DeviceEventEmitterSingleton.getDeviceEventEmitter().emitUplMetric(new UplMetric(join, j, str5));
        }
    }

    /* loaded from: classes.dex */
    public enum UplType {
        Audio,
        Visual,
        Media
    }

    static {
        OPTION_BUNDLE_TO_METADATA_KEYS.put("intentName", "intent_name");
        OPTION_BUNDLE_TO_METADATA_KEYS.put("interactionLabel", UPLConstants.IntentKey.INTENT_KEY_INTERACTION_LABEL);
        BUNDLE_TO_METADATA_KEYS.put("eventId", "event_id");
        BUNDLE_TO_METADATA_KEYS.put("directiveName", "name");
        BUNDLE_TO_METADATA_KEYS.put("directiveNamespace", "namespace");
        SAFE_UPL_METRIC_NAMES.add(AccessoryMetricsConstants.USER_PERCEIVED_LATENCY_AUDIO);
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.DispatchToAudio");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.DispatchToDispatchToTtsConnection");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.DispatchToTtsConnectionToTtsConnection");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.FirstByteToParseComplete");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.ParseCompleteToDispatch");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.TtsConnectionToTtsMediaPreparation");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.DeviceProcessing.TtsMediaPreparationToAudio");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.ServerProcessing");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.ServerProcessing.StopCaptureToFirstByte");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Audio.ServerProcessing.UtteranceEndToStopCapture");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.DeviceProcessing");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.DeviceProcessing.DispatchToVisual");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.DeviceProcessing.FirstByteToParseComplete");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.DeviceProcessing.ParseCompleteToDispatch");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.ServerProcessing");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.ServerProcessing.StopCaptureToFirstByte");
        SAFE_UPL_METRIC_NAMES.add("UserPerceivedLatency.Visual.ServerProcessing.UtteranceEndToStopCapture");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UPLRecorder(RequestData.Type type, Bundle bundle, MetricsClient metricsClient) {
        if (type != null && bundle != null && metricsClient != null) {
            this.mMetricsUtil = metricsClient;
            this.mSegments = new HashMap();
            this.mSegments.put(UplType.Audio, new ArrayList());
            this.mSegments.put(UplType.Visual, new ArrayList());
            this.mSegments.put(UplType.Media, new ArrayList());
            this.mReportedMetricTypes = new HashSet();
            this.mRequestType = type;
            this.mLastAudioDirectiveTime = getTimestamp(bundle, "last_audio_directive_time");
            this.mLastVisualDirectiveTime = getTimestamp(bundle, "last_visual_directive_time");
            this.mLastMediaDirectiveTime = getTimestamp(bundle, UPLConstants.IntentKey.INTENT_KEY_LAST_MEDIA_DIRECTIVE_TIME);
            this.mProductName = bundle.getString("metrics_program_name");
            this.mInteractionLabel = bundle.getString(UPLConstants.IntentKey.INTENT_KEY_INTERACTION_LABEL);
            this.mMetadata = parseMetadata(bundle);
            this.mSystemProcessingPoints = parseSystemProcessingPoints(bundle);
            return;
        }
        throw new IllegalArgumentException("Parameters cannot be null.");
    }

    public static UPLRecorder createRecorder(String str, InteractionRequestData interactionRequestData, String str2, String str3, MetricsClient metricsClient) {
        if (interactionRequestData != null) {
            if (metricsClient != null) {
                if (!TextUtils.isEmpty(str)) {
                    if (!TextUtils.isEmpty(str3)) {
                        if (!TextUtils.isEmpty(str2)) {
                            Bundle outline11 = GeneratedOutlineSupport1.outline11("metrics_program_name", str);
                            if (DEBUG) {
                                GeneratedOutlineSupport1.outline158("adding metrics program name:", str);
                            }
                            outline11.putString("name", str3);
                            if (DEBUG) {
                                GeneratedOutlineSupport1.outline158("adding directive name:", str3);
                            }
                            outline11.putString("namespace", str2);
                            if (DEBUG) {
                                GeneratedOutlineSupport1.outline158("adding directive namespace:", str2);
                            }
                            parseInteractionRequestToBundle(outline11, interactionRequestData);
                            return new EventRequestUPLRecorder(outline11, metricsClient);
                        }
                        throw new IllegalArgumentException("applicationName cannot be null or empty.");
                    }
                    throw new IllegalArgumentException("actionName cannot be null or empty.");
                }
                throw new IllegalArgumentException("metricsProgramName cannot be null or empty.");
            }
            throw new IllegalArgumentException("metricsClient cannot be null.");
        }
        throw new IllegalArgumentException("interactionRequestData cannot be null.");
    }

    private void doRecordUserPerceivedLatency(UplType uplType, List<ProcessingPoint> list) {
        UPLRecorder uPLRecorder;
        UplType uplType2;
        if (list != null) {
            int i = 1;
            if (list.size() >= 1) {
                int size = list.size();
                ProcessingPoint processingPoint = list.get(0);
                ProcessingPoint processingPoint2 = list.get(size - 1);
                ProcessingPoint processingPoint3 = null;
                ProcessingPoint processingPoint4 = processingPoint;
                ProcessingPoint processingPoint5 = null;
                while (i < size) {
                    ProcessingPoint processingPoint6 = list.get(i);
                    if (processingPoint4.mType == ProcessingPoint.ProcessingType.ServerProcessing && processingPoint3 == null) {
                        processingPoint3 = processingPoint4;
                    }
                    if (processingPoint6.mType == ProcessingPoint.ProcessingType.DeviceProcessing && processingPoint5 == null) {
                        uPLRecorder = this;
                        uplType2 = uplType;
                        processingPoint5 = processingPoint6;
                    } else {
                        uPLRecorder = this;
                        uplType2 = uplType;
                    }
                    uPLRecorder.publishSegment(uplType2, processingPoint4, processingPoint6);
                    i++;
                    processingPoint4 = processingPoint6;
                }
                publishTimer(uplType, null, null, processingPoint.mTimestamp, processingPoint2.mTimestamp);
                if (processingPoint3 != null && processingPoint5 != null) {
                    publishTimer(uplType, processingPoint3.mType, null, processingPoint3.mTimestamp, processingPoint5.mTimestamp);
                }
                if (processingPoint5 == null || processingPoint5 == processingPoint2) {
                    return;
                }
                publishTimer(uplType, processingPoint5.mType, null, processingPoint5.mTimestamp, processingPoint2.mTimestamp);
            }
        }
    }

    static String join(CharSequence charSequence, String[] strArr) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : strArr) {
            if (str != null && !str.isEmpty()) {
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

    private static void parseDirectiveV1Metadata(Bundle bundle, MetricsRecord.Metadata metadata) {
        for (Map.Entry<String, String> entry : BUNDLE_TO_METADATA_KEYS.entrySet()) {
            String string = bundle.getString(entry.getValue());
            if (string == null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not reporting UPL metrics for directive. Missing '");
                outline107.append(entry.getValue());
                outline107.append("'.");
                Log.w(TAG, outline107.toString());
                return;
            }
            metadata.add(entry.getKey(), string);
        }
    }

    private static void parseDirectiveV2Metadata(Bundle bundle, MetricsRecord.Metadata metadata) {
        parseDirectiveV2Metadata(DirectiveIntent.fromIntentExtras(bundle), metadata);
    }

    private static void parseInteractionRequestToBundle(Bundle bundle, InteractionRequestData interactionRequestData) {
        long initiationTimestamp = interactionRequestData.getInitiationTimestamp();
        if (initiationTimestamp > 0) {
            bundle.putLong(UPLConstants.IntentKey.INTENT_KEY_INTERACTION_START_TIME, initiationTimestamp);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline153("adding initiation time: ", initiationTimestamp);
            }
        }
        String interactionLabel = interactionRequestData.getInteractionLabel();
        if (!TextUtils.isEmpty(interactionLabel)) {
            bundle.putString(UPLConstants.IntentKey.INTENT_KEY_INTERACTION_LABEL, interactionLabel);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline158("adding interaction label: ", interactionLabel);
            }
        }
        String eventId = interactionRequestData.getEventId();
        if (!TextUtils.isEmpty(eventId)) {
            bundle.putString("event_id", eventId);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline158("adding eventId: ", eventId);
            }
        }
        bundle.putInt("event_type", interactionRequestData.getType().ordinal());
    }

    static MetricsRecord.Metadata parseMetadata(Bundle bundle) {
        MetricsRecord.Metadata metadata = new MetricsRecord.Metadata();
        parseDirectiveV2Metadata(bundle, metadata);
        if (metadata.entrySet().isEmpty()) {
            parseDirectiveV1Metadata(bundle, metadata);
            if (metadata.entrySet().isEmpty()) {
                Log.w(TAG, "Failed to extract metadata from Intent bundle");
                return null;
            }
        }
        for (Map.Entry<String, String> entry : OPTION_BUNDLE_TO_METADATA_KEYS.entrySet()) {
            String string = bundle.getString(entry.getValue());
            if (string == null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Missing '");
                outline107.append(entry.getValue());
                outline107.append("', which is optional. Continue");
                Log.w(TAG, outline107.toString());
            } else {
                metadata.add(entry.getKey(), string);
            }
        }
        return metadata;
    }

    private List<ProcessingPoint> parseSystemProcessingPoints(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        parseRequestProcessingPoints(bundle, arrayList);
        tryExtractProcessingPoint(bundle, "directive_first_byte_time", ProcessingPoint.ProcessingType.DeviceProcessing, STRING_PROCESSING_POINT_FIRST_BYTE, arrayList);
        tryExtractProcessingPoint(bundle, "directive_parse_complete_time", ProcessingPoint.ProcessingType.DeviceProcessing, STRING_PROCESSING_POINT_PARSE_COMPLETE, arrayList);
        tryExtractProcessingPoint(bundle, "directive_dispatch_time", ProcessingPoint.ProcessingType.DeviceProcessing, STRING_PROCESSING_POINT_DISPATCH, arrayList);
        return arrayList;
    }

    private void publishSegment(UplType uplType, ProcessingPoint processingPoint, ProcessingPoint processingPoint2) {
        publishTimer(uplType, processingPoint.mType, String.format(STRING_SEGMENT_FORMAT, processingPoint.mName, processingPoint2.mName), processingPoint.mTimestamp, processingPoint2.mTimestamp);
    }

    long getTimestamp(Bundle bundle, String str) {
        return bundle.getLong(str, -1L);
    }

    boolean hasReportedUserPerceivedLatency(UplType uplType) {
        return this.mReportedMetricTypes.contains(uplType);
    }

    protected abstract void parseRequestProcessingPoints(Bundle bundle, List<ProcessingPoint> list);

    void publishTimer(UplType uplType, ProcessingPoint.ProcessingType processingType, String str, long j, long j2) {
        if (j < 0) {
            Log.w(TAG, "Not publishing timer. Begin timestamp not valid.");
        } else if (j2 < 0) {
            Log.w(TAG, "Not publishing timer. End timestamp not valid.");
        } else {
            long j3 = j2 - j;
            if (j3 < 0) {
                Log.w(TAG, "Not publishing timer. Timer duration not valid");
                return;
            }
            for (IMetricConvention iMetricConvention : this.mConventions) {
                iMetricConvention.reportMetric(this.mRequestType, uplType, processingType, this.mProductName, str, j3, this.mMetadata, this.mMetricsUtil);
            }
        }
    }

    public void recordDeviceProcessingSegment(String str, long j, UplType uplType) {
        if (!TextUtils.isEmpty(str)) {
            if (uplType == null) {
                throw new IllegalArgumentException("type cannot be null.");
            }
            if (j >= 0) {
                this.mSegments.get(uplType).add(new ProcessingPoint(ProcessingPoint.ProcessingType.DeviceProcessing, str, j));
                return;
            }
            throw new IllegalArgumentException("timestamp must be a positive integer.");
        }
        throw new IllegalArgumentException("point name must not be empty.");
    }

    public void recordUserPerceivedLatency(long j, UplType uplType) {
        recordUserPerceivedLatency(j, uplType, false, null);
    }

    boolean shouldPublishMetrics(UplType uplType, boolean z, List<String> list) {
        if (this.mMetadata == null) {
            Log.i(TAG, "Not reporting UPL metrics for directive. Incomplete metadata.");
            return false;
        } else if (hasReportedUserPerceivedLatency(uplType)) {
            Log.i(TAG, "Not reporting UPL metrics for directive. Already published.");
            return false;
        } else if (TextUtils.isEmpty(this.mProductName)) {
            Log.i(TAG, "Not reporting UPL metrics for directive. Product name is missing.");
            return false;
        } else {
            String str = this.mMetadata.get("intentName");
            if (list != null && !list.contains(str)) {
                Log.i(TAG, "Not reporting UPL metrics for directive. '" + str + "' is not in the supported list of intents.");
                return false;
            } else if (z) {
                return true;
            } else {
                int ordinal = uplType.ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal == 2) {
                            if (this.mLastMediaDirectiveTime > 0) {
                                Log.i(TAG, "Not reporting UPL metrics for directive. Not first in media channel.");
                                return false;
                            }
                        } else {
                            throw new IllegalStateException("Unknown type: " + uplType);
                        }
                    } else if (this.mLastVisualDirectiveTime > 0) {
                        Log.i(TAG, "Not reporting UPL metrics for directive. Not first in visual channel.");
                        return false;
                    }
                } else if (this.mLastAudioDirectiveTime > 0) {
                    Log.i(TAG, "Not reporting UPL metrics for directive. Not first in audio channel.");
                    return false;
                }
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void tryExtractProcessingPoint(Bundle bundle, String str, ProcessingPoint.ProcessingType processingType, String str2, List<ProcessingPoint> list) {
        long timestamp = getTimestamp(bundle, str);
        if (timestamp > 0) {
            list.add(new ProcessingPoint(processingType, str2, timestamp));
        }
    }

    static void parseDirectiveV2Metadata(Directive directive, MetricsRecord.Metadata metadata) {
        if (directive == null) {
            Log.w(TAG, "Failed to create directive from Intent bundle.");
            return;
        }
        String sequenceId = directive.getSequenceId();
        String namespace = directive.getNamespace();
        String name = directive.getName();
        if (sequenceId != null && !sequenceId.isEmpty()) {
            if (namespace != null && !namespace.isEmpty()) {
                if (name != null && !name.isEmpty()) {
                    metadata.add("eventId", sequenceId);
                    metadata.add("directiveNamespace", namespace);
                    metadata.add("directiveName", name);
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

    public void recordUserPerceivedLatency(long j, UplType uplType, boolean z, List<String> list) {
        if (uplType != null) {
            if (j >= 0) {
                try {
                    Trace.beginSection("recordUserPerceivedLatency");
                    if (!shouldPublishMetrics(uplType, z, list)) {
                        Log.i(TAG, "Not publishing UPL metrics for directive.");
                        return;
                    }
                    ProcessingPoint processingPoint = new ProcessingPoint(ProcessingPoint.ProcessingType.DeviceProcessing, uplType.name(), j);
                    List<ProcessingPoint> list2 = this.mSegments.get(uplType);
                    list2.add(processingPoint);
                    Collections.sort(list2);
                    List<ProcessingPoint> arrayList = new ArrayList<>();
                    arrayList.addAll(this.mSystemProcessingPoints);
                    arrayList.addAll(list2);
                    doRecordUserPerceivedLatency(uplType, arrayList);
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

    public static UPLRecorder createRecorder(Intent intent, MetricsClient metricsClient) {
        if (intent != null && metricsClient != null) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            int i = extras.getInt("event_type", RequestData.Type.SPEECH.ordinal());
            if (i == RequestData.Type.INTERACTION.ordinal()) {
                return new EventRequestUPLRecorder(extras, metricsClient);
            }
            if (i == RequestData.Type.SPEECH.ordinal()) {
                return new SpeechRequestUPLRecorder(extras, metricsClient);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("The initiation type is illegal: ", i));
        }
        throw new IllegalArgumentException("Parameters cannot be null.");
    }
}
