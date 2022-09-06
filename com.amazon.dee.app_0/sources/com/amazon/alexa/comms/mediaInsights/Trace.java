package com.amazon.alexa.comms.mediaInsights;

import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.impl.LoggingUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class Trace {
    public static final int MAP_KEY_MAX_LENGTH = 128;
    public static final int MAP_MAX_ENTRIES = 20;
    public static final int MAP_VALUE_MAX_LENGTH = 128;
    public static final int PAYLOAD_DATA_MAX_LENGTH = 32000;
    public static final int PAYLOAD_ENCODING_MAX_LENGTH = 64;
    public static final int TYPE_MAX_LENGTH = 64;
    private Map<String, String> annotations;
    private String parentSegmentId;
    private Payload payload;
    private Map<String, String> principals;
    private String segmentId;
    private Map<String, String> source;
    private long timestamp;
    private String traceId;
    private transient TraceRecorder traceRecorder;
    private String type;
    private static final String GUID_REGEX_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[4][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
    private static final Pattern GUID_PATTERN = Pattern.compile(GUID_REGEX_PATTERN);

    /* loaded from: classes6.dex */
    public static class Builder {
        private Map<String, String> annotations;
        private String parentSegmentId;
        private String payloadData;
        private String payloadEncoding;
        private Map<String, String> principals;
        private String segmentId;
        private Map<String, String> source;
        private Long timestamp;
        private String traceId;
        private TraceRecorder traceRecorder;
        private String type;

        Builder() {
        }

        private String getUUID() {
            return UUID.randomUUID().toString();
        }

        private void validate() {
            validateStringLength(this.type, "type", 64);
            validateStringLength(this.payloadEncoding, "payloadEncoding", 64);
            validateStringLength(this.payloadData, "payloadData", Trace.PAYLOAD_DATA_MAX_LENGTH);
            validateIds(this.traceId, "traceId");
            validateIds(this.segmentId, "segmentId");
            validateIds(this.parentSegmentId, "parentSegmentId");
            validateMaps(this.source, "source");
            validateMaps(this.annotations, "annotations");
            validateMaps(this.principals, "principals");
        }

        private void validateIds(String str, String str2) {
            if (str != null && !Trace.GUID_PATTERN.matcher(str).matches()) {
                throw new IllegalArgumentException(String.format("%s is not a GUID", str2));
            }
        }

        private void validateMaps(Map<String, String> map, String str) {
            if (map == null) {
                return;
            }
            if (map.keySet().size() <= 20) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String value = entry.getValue();
                    if (entry.getKey().length() <= 128) {
                        if (value != null && value.length() > 128) {
                            throw new IllegalArgumentException(String.format("Max size of value in %s is %s", str, 128));
                        }
                    } else {
                        throw new IllegalArgumentException(String.format("Max size of key in %s is %s", str, 128));
                    }
                }
                return;
            }
            throw new IllegalArgumentException(String.format("Max entries in %s is %s", str, 20));
        }

        private void validateStringLength(String str, String str2, int i) {
            if (str != null) {
                if (str.length() > i) {
                    throw new IllegalArgumentException(String.format("Max length of %s is %s", str2, Integer.valueOf(i)));
                }
                return;
            }
            throw new IllegalArgumentException(String.format("%s of a trace is a mandatory field", str2));
        }

        public Builder addAnnotation(@NonNull String str, @NonNull String str2) {
            if (str != null) {
                if (str2 != null) {
                    if (this.annotations == null) {
                        this.annotations = new HashMap();
                    }
                    this.annotations.put(str, str2);
                    return this;
                }
                throw new IllegalArgumentException("annotationValue is null");
            }
            throw new IllegalArgumentException("annotationKey is null");
        }

        public Builder addPrincipal(@NonNull String str, @NonNull String str2) {
            if (str != null) {
                if (str2 != null) {
                    if (this.principals == null) {
                        this.principals = new HashMap();
                    }
                    this.principals.put(str, str2);
                    return this;
                }
                throw new IllegalArgumentException("principalValue is null");
            }
            throw new IllegalArgumentException("principalKey is null");
        }

        public Builder addSourceInfo(@NonNull String str, @NonNull String str2) {
            if (str != null) {
                if (str2 != null) {
                    if (this.source == null) {
                        this.source = new HashMap();
                    }
                    this.source.put(str, str2);
                    return this;
                }
                throw new IllegalArgumentException("sourceValue is null");
            }
            throw new IllegalArgumentException("sourceKey is null");
        }

        public Trace build() {
            validate();
            Map<String, String> map = null;
            Trace trace = new Trace();
            String str = this.traceId;
            if (str == null) {
                str = getUUID();
            }
            trace.traceId = str;
            String str2 = this.segmentId;
            if (str2 == null) {
                str2 = getUUID();
            }
            trace.segmentId = str2;
            String str3 = this.parentSegmentId;
            if (str3 == null) {
                str3 = trace.traceId;
            }
            trace.parentSegmentId = str3;
            Long l = this.timestamp;
            trace.timestamp = l == null ? System.currentTimeMillis() : l.longValue();
            trace.type = this.type;
            Map<String, String> map2 = this.source;
            trace.source = (map2 == null || map2.isEmpty()) ? null : this.source;
            Map<String, String> map3 = this.annotations;
            trace.annotations = (map3 == null || map3.isEmpty()) ? null : this.annotations;
            Map<String, String> map4 = this.principals;
            if (map4 != null && !map4.isEmpty()) {
                map = this.principals;
            }
            trace.principals = map;
            trace.payload = Payload.builder().encoding(this.payloadEncoding).data(this.payloadData).build();
            trace.traceRecorder = this.traceRecorder;
            return trace;
        }

        public Builder withAnnotations(@NonNull Map<String, String> map) {
            if (map != null) {
                this.annotations = map;
                return this;
            }
            throw new IllegalArgumentException("annotations is null");
        }

        public Builder withParentSegmentId(@NonNull String str) {
            if (str != null) {
                this.parentSegmentId = str;
                return this;
            }
            throw new IllegalArgumentException("parentSegmentId is null");
        }

        public Builder withPayloadData(@NonNull String str) {
            if (str != null) {
                this.payloadData = str;
                return this;
            }
            throw new IllegalArgumentException("payloadData is null");
        }

        public Builder withPayloadEncoding(@NonNull String str) {
            if (str != null) {
                this.payloadEncoding = str;
                return this;
            }
            throw new IllegalArgumentException("payloadEncoding is null");
        }

        public Builder withPrincipals(@NonNull Map<String, String> map) {
            if (map != null) {
                this.principals = map;
                return this;
            }
            throw new IllegalArgumentException("principals is null");
        }

        public Builder withSegmentId(@NonNull String str) {
            if (str != null) {
                this.segmentId = str;
                return this;
            }
            throw new IllegalArgumentException("segmentId is null");
        }

        public Builder withSourceInfo(@NonNull Map<String, String> map) {
            if (map != null) {
                this.source = map;
                return this;
            }
            throw new IllegalArgumentException("source is null");
        }

        public Builder withTimestamp(@NonNull Long l) {
            if (l != null) {
                this.timestamp = l;
                return this;
            }
            throw new IllegalArgumentException("timestamp is null");
        }

        public Builder withTraceId(@NonNull String str) {
            if (str != null) {
                this.traceId = str;
                return this;
            }
            throw new IllegalArgumentException("traceId is null");
        }

        public Builder withType(@NonNull String str) {
            if (str != null) {
                this.type = str;
                return this;
            }
            throw new IllegalArgumentException("type is null");
        }

        Builder(@NonNull TraceRecorder traceRecorder) {
            if (traceRecorder != null) {
                this.traceRecorder = traceRecorder;
                return;
            }
            throw new IllegalArgumentException("recorder is null");
        }
    }

    /* loaded from: classes6.dex */
    public static class Payload {
        @NonNull
        private String data;
        @NonNull
        private String encoding;

        /* loaded from: classes6.dex */
        public static class PayloadBuilder {
            private String data;
            private String encoding;

            PayloadBuilder() {
            }

            public Payload build() {
                return new Payload(this.encoding, this.data);
            }

            public PayloadBuilder data(String str) {
                this.data = str;
                return this;
            }

            public PayloadBuilder encoding(String str) {
                this.encoding = str;
                return this;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trace.Payload.PayloadBuilder(encoding=");
                outline107.append(this.encoding);
                outline107.append(", data=");
                return GeneratedOutlineSupport1.outline91(outline107, this.data, ")");
            }
        }

        Payload(@NonNull String str, @NonNull String str2) {
            if (str != null) {
                if (str2 == null) {
                    throw new IllegalArgumentException("data is null");
                }
                this.encoding = str;
                this.data = str2;
                return;
            }
            throw new IllegalArgumentException("encoding is null");
        }

        public static PayloadBuilder builder() {
            return new PayloadBuilder();
        }

        @NonNull
        public String getData() {
            return this.data;
        }

        @NonNull
        public String getEncoding() {
            return this.encoding;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trace.Payload(encoding=");
            outline107.append(getEncoding());
            outline107.append(", data=");
            outline107.append(getData());
            outline107.append(")");
            return outline107.toString();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, String> getAnnotations() {
        return this.annotations;
    }

    public String getParentSegmentId() {
        return this.parentSegmentId;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public Map<String, String> getPrincipals() {
        return this.principals;
    }

    public String getSegmentId() {
        return this.segmentId;
    }

    public Map<String, String> getSource() {
        return this.source;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public String getType() {
        return this.type;
    }

    public Builder newChildTrace() {
        TraceRecorder traceRecorder = this.traceRecorder;
        return (traceRecorder == null ? builder() : builder(traceRecorder)).withParentSegmentId(this.traceId);
    }

    public void record() {
        TraceRecorder traceRecorder = this.traceRecorder;
        if (traceRecorder != null) {
            traceRecorder.record(this);
        } else {
            Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, "traceRecorder is set to null, trace is not being recorded");
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trace(traceId=");
        outline107.append(getTraceId());
        outline107.append(", segmentId=");
        outline107.append(getSegmentId());
        outline107.append(", parentSegmentId=");
        outline107.append(getParentSegmentId());
        outline107.append(", type=");
        outline107.append(getType());
        outline107.append(", timestamp=");
        outline107.append(getTimestamp());
        outline107.append(", source=");
        outline107.append(getSource());
        outline107.append(", annotations=");
        outline107.append(getAnnotations());
        outline107.append(", principals=");
        outline107.append(getPrincipals());
        outline107.append(", payload=");
        outline107.append(getPayload());
        outline107.append(", traceRecorder=");
        outline107.append(this.traceRecorder);
        outline107.append(")");
        return outline107.toString();
    }

    private Trace() {
    }

    public static Builder builder(TraceRecorder traceRecorder) {
        return new Builder(traceRecorder);
    }
}
