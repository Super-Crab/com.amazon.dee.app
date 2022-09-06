package com.amazon.alexa.client.metrics.kinesis.event;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.metrics.kinesis.context.KinesisContext;
import com.amazon.alexa.client.metrics.kinesis.system.AppDetails;
import com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails;
import com.amazon.alexa.client.metrics.kinesis.util.KinesisContextIdUtil;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.ClientContext;
import com.amazonaws.util.VersionInfoUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class KinesisDefaultEvent implements KinesisInternalEvent {
    private static final String COGNITO_ID_UNKNOWN = "Unknown";
    private static final String SDK_NAME = "aws-sdk-android";
    private static final String SDK_VERSION = VersionInfoUtils.getVersion();
    private static final String TAG = "KinesisDefaultEvent";
    private final AppDetails appDetails;
    private final String applicationCognitoId;
    private final String clientCognitoId;
    private final DeviceDetails deviceDetails;
    private final String eventType;
    private final Long sessionDuration;
    private final String sessionId;
    private final Long sessionStart;
    private final Long sessionStop;
    private final Long timestamp;
    private final KinesisContextIdUtil uniqueId;
    private final Map<String, String> attributes = new ConcurrentHashMap();
    private final Map<String, String> pivots = new ConcurrentHashMap();
    private final Map<String, Double> metrics = new ConcurrentHashMap();

    /* loaded from: classes6.dex */
    public static class KinesisDefaultEventBuilder {
        AppDetails appDetails;
        Map<String, String> attributes;
        KinesisContext context;
        DeviceDetails deviceDetails;
        String eventType;
        Map<String, Double> metrics;
        Map<String, String> pivots;
        Long sessionDuration;
        String sessionId;
        Long sessionStart;
        Long sessionStop;
        Long timestamp;
        KinesisContextIdUtil uniqueId;

        public KinesisDefaultEvent build() {
            return new KinesisDefaultEvent(this);
        }

        public KinesisDefaultEventBuilder withAppDetails(AppDetails appDetails) {
            this.appDetails = appDetails;
            return this;
        }

        public KinesisDefaultEventBuilder withAttributes(Map<String, String> map) {
            this.attributes = map;
            return this;
        }

        public KinesisDefaultEventBuilder withContext(KinesisContext kinesisContext) {
            this.context = kinesisContext;
            return this;
        }

        public KinesisDefaultEventBuilder withDeviceDetails(DeviceDetails deviceDetails) {
            this.deviceDetails = deviceDetails;
            return this;
        }

        public KinesisDefaultEventBuilder withEventType(String str) {
            this.eventType = str;
            return this;
        }

        public KinesisDefaultEventBuilder withMetrics(Map<String, Double> map) {
            this.metrics = map;
            return this;
        }

        public KinesisDefaultEventBuilder withPivots(Map<String, String> map) {
            this.pivots = map;
            return this;
        }

        public KinesisDefaultEventBuilder withSessionDuration(Long l) {
            this.sessionDuration = l;
            return this;
        }

        public KinesisDefaultEventBuilder withSessionId(String str) {
            this.sessionId = str;
            return this;
        }

        public KinesisDefaultEventBuilder withSessionStart(Long l) {
            this.sessionStart = l;
            return this;
        }

        public KinesisDefaultEventBuilder withSessionStop(Long l) {
            this.sessionStop = l;
            return this;
        }

        public KinesisDefaultEventBuilder withTimestamp(Long l) {
            this.timestamp = l;
            return this;
        }

        public KinesisDefaultEventBuilder withUniqueID(KinesisContextIdUtil kinesisContextIdUtil) {
            this.uniqueId = kinesisContextIdUtil;
            return this;
        }
    }

    KinesisDefaultEvent(KinesisDefaultEventBuilder kinesisDefaultEventBuilder) {
        this.eventType = kinesisDefaultEventBuilder.eventType;
        this.sessionId = kinesisDefaultEventBuilder.sessionId;
        this.sessionStart = kinesisDefaultEventBuilder.sessionStart;
        this.sessionStop = kinesisDefaultEventBuilder.sessionStop;
        this.sessionDuration = kinesisDefaultEventBuilder.sessionDuration;
        this.timestamp = kinesisDefaultEventBuilder.timestamp;
        this.uniqueId = kinesisDefaultEventBuilder.uniqueId;
        this.appDetails = kinesisDefaultEventBuilder.appDetails;
        this.deviceDetails = kinesisDefaultEventBuilder.deviceDetails;
        this.clientCognitoId = getCognitoIdFromCredentialProvider(kinesisDefaultEventBuilder.context.getAWSCredentialsProvider());
        this.applicationCognitoId = kinesisDefaultEventBuilder.context.getAppID();
        Map<String, String> map = kinesisDefaultEventBuilder.attributes;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                addAttribute(entry.getKey(), entry.getValue());
            }
        }
        Map<String, Double> map2 = kinesisDefaultEventBuilder.metrics;
        if (map2 != null) {
            for (Map.Entry<String, Double> entry2 : map2.entrySet()) {
                addMetric(entry2.getKey(), entry2.getValue());
            }
        }
        Map<String, String> map3 = kinesisDefaultEventBuilder.pivots;
        if (map3 != null) {
            for (Map.Entry<String, String> entry3 : map3.entrySet()) {
                addPivot(entry3.getKey(), entry3.getValue());
            }
        }
    }

    public static KinesisDefaultEvent createFromEvent(KinesisContext kinesisContext, String str, long j, KinesisInternalEvent kinesisInternalEvent) {
        return new KinesisDefaultEventBuilder().withEventType(kinesisInternalEvent.getEventType()).withAttributes(kinesisInternalEvent.getAllAttributes()).withMetrics(kinesisInternalEvent.getAllMetrics()).withPivots(kinesisInternalEvent.getAllPivots()).withSessionId(str).withSessionStart(Long.valueOf(kinesisInternalEvent.getSessionStart())).withSessionStop(kinesisInternalEvent.getSessionStop()).withSessionDuration(kinesisInternalEvent.getSessionDuration()).withTimestamp(Long.valueOf(j)).withUniqueID(kinesisContext.getUniqueId()).withAppDetails(kinesisContext.getAppDetails()).withDeviceDetails(kinesisContext.getDeviceDetails()).withContext(kinesisContext).build();
    }

    private String getCognitoIdFromCredentialProvider(AWSCredentialsProvider aWSCredentialsProvider) {
        if (aWSCredentialsProvider instanceof CognitoCachingCredentialsProvider) {
            String cachedIdentityId = ((CognitoCachingCredentialsProvider) aWSCredentialsProvider).getCachedIdentityId();
            if (cachedIdentityId != null) {
                return cachedIdentityId;
            }
            try {
                return ((CognitoCachingCredentialsProvider) aWSCredentialsProvider).getIdentityId();
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Error in fetching cognito details "), TAG);
                return "Unknown";
            }
        }
        return "Unknown";
    }

    private <T> JSONObject getJsonObjectFromMap(@NonNull Map<String, T> map) {
        Preconditions.checkNotNull(map);
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                Log.e(TAG, String.format("Error serializing metric. Key:'%s', Value: '%s' and Error: %s ", entry.getKey(), entry.getValue().toString(), e.getMessage()));
            }
        }
        return jSONObject;
    }

    public static KinesisDefaultEvent newInstance(KinesisContext kinesisContext, String str, Long l, Long l2, Long l3, long j, String str2) {
        return new KinesisDefaultEventBuilder().withEventType(str2).withSessionId(str).withSessionStart(l).withSessionStop(l2).withSessionDuration(l3).withTimestamp(Long.valueOf(j)).withUniqueID(kinesisContext.getUniqueId()).withAppDetails(kinesisContext.getAppDetails()).withDeviceDetails(kinesisContext.getDeviceDetails()).withContext(kinesisContext).build();
    }

    private <K, V> void safePutIntoMap(@NonNull Map<K, V> map, @NonNull K k, @Nullable V v) {
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(k);
        if (v != null) {
            map.put(k, v);
        } else {
            map.remove(k);
        }
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public void addAttribute(String str, String str2) {
        safePutIntoMap(this.attributes, str, str2);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public void addMetric(String str, Double d) {
        safePutIntoMap(this.metrics, str, d);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public void addPivot(String str, String str2) {
        safePutIntoMap(this.pivots, str, str2);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public Map<String, String> getAllAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public Map<String, Double> getAllMetrics() {
        return Collections.unmodifiableMap(this.metrics);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public Map<String, String> getAllPivots() {
        return Collections.unmodifiableMap(this.pivots);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public String getAttribute(String str) {
        if (str == null) {
            return null;
        }
        return this.attributes.get(str);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisInternalEvent
    public Long getEventTimestamp() {
        return this.timestamp;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public String getEventType() {
        return this.eventType;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public Double getMetric(String str) {
        if (str == null) {
            return null;
        }
        return this.metrics.get(str);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public String getPivot(String str) {
        if (str == null) {
            return null;
        }
        return this.pivots.get(str);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisInternalEvent
    public Long getSessionDuration() {
        return this.sessionDuration;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisInternalEvent
    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisInternalEvent
    public long getSessionStart() {
        return this.sessionStart.longValue();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisInternalEvent
    public Long getSessionStop() {
        return this.sessionStop;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisInternalEvent
    public KinesisContextIdUtil getUniqueId() {
        return this.uniqueId;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public boolean hasAttribute(String str) {
        if (str == null) {
            return false;
        }
        return this.attributes.containsKey(str);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public boolean hasMetric(String str) {
        if (str == null) {
            return false;
        }
        return this.metrics.containsKey(str);
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    public boolean hasPivot(String str) {
        if (str == null) {
            return false;
        }
        return this.pivots.containsKey(str);
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("event_type", getEventType());
            jSONObject.put("event_timestamp", getEventTimestamp());
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Error serializing system attributes "), TAG);
        }
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject3.put("name", this.deviceDetails.platform());
            jSONObject3.put("version", this.deviceDetails.platformVersion());
            jSONObject2.put(MetricsConfiguration.PLATFORM, jSONObject3);
            jSONObject2.put("make", this.deviceDetails.manufacturer());
            jSONObject2.put("model", this.deviceDetails.model());
            jSONObject4.put("code", this.deviceDetails.locale());
            jSONObject4.put("country", this.deviceDetails.getCountry());
            jSONObject4.put("language", this.deviceDetails.getLanguage());
            jSONObject2.put("locale", jSONObject4);
            jSONObject.put("device", jSONObject2);
        } catch (JSONException e2) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error serializing device information ");
            outline107.append(e2.getMessage());
            Log.e(str, outline107.toString());
        }
        JSONObject jSONObject5 = new JSONObject();
        try {
            jSONObject5.put("session_id", this.sessionId);
            if (this.sessionStart != null) {
                jSONObject5.put("start_timestamp", this.sessionStart);
            }
            if (this.sessionStop != null) {
                jSONObject5.put("stop_timestamp", this.sessionStop);
            }
            if (this.sessionDuration != null) {
                jSONObject5.put("duration", this.sessionDuration.longValue());
            }
            jSONObject.put("session", jSONObject5);
        } catch (JSONException e3) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Error serializing session information ");
            outline1072.append(e3.getMessage());
            Log.e(str2, outline1072.toString());
        }
        JSONObject jSONObject6 = new JSONObject();
        try {
            jSONObject6.put("client_id", getUniqueId().getValue());
            if (!TextUtils.isEmpty(this.clientCognitoId)) {
                jSONObject6.put("cognito_id", this.clientCognitoId);
            }
            jSONObject.put("client", jSONObject6);
        } catch (JSONException e4) {
            String str3 = TAG;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Error serializing client information ");
            outline1073.append(e4.getMessage());
            Log.e(str3, outline1073.toString());
        }
        JSONObject jSONObject7 = new JSONObject();
        JSONObject jSONObject8 = new JSONObject();
        try {
            jSONObject7.put("version_name", this.appDetails.versionName());
            jSONObject7.put("version_code", this.appDetails.versionCode());
            jSONObject7.put("package_name", this.appDetails.packageName());
            jSONObject7.put("title", this.appDetails.getAppTitle());
            jSONObject7.put(ClientContext.APP_ID_KEY, this.appDetails.getAppId());
            jSONObject7.put("cognito_identity_pool_id", this.applicationCognitoId);
            jSONObject8.put("name", SDK_NAME);
            jSONObject8.put("version", SDK_VERSION);
            jSONObject7.put("sdk", jSONObject8);
            jSONObject.put("application", jSONObject7);
        } catch (JSONException e5) {
            String str4 = TAG;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Error serializing application/sdk information ");
            outline1074.append(e5.getMessage());
            Log.e(str4, outline1074.toString());
        }
        JSONObject jsonObjectFromMap = getJsonObjectFromMap(this.attributes);
        JSONObject jsonObjectFromMap2 = getJsonObjectFromMap(this.metrics);
        JSONObject jsonObjectFromMap3 = getJsonObjectFromMap(this.pivots);
        try {
            if (jsonObjectFromMap.length() > 0) {
                jSONObject.put("attributes", jsonObjectFromMap);
            }
            if (jsonObjectFromMap2.length() > 0) {
                jSONObject.put("metrics", jsonObjectFromMap2);
            }
            if (jsonObjectFromMap3.length() > 0) {
                jSONObject.put("pivots", jsonObjectFromMap3);
            }
        } catch (JSONException e6) {
            Log.e(TAG, String.format("Error serializing json: %s ", e6.getMessage()));
        }
        return jSONObject;
    }

    public String toString() {
        JSONObject jSONObject = toJSONObject();
        try {
            return jSONObject.toString(4);
        } catch (JSONException unused) {
            return jSONObject.toString();
        }
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    /* renamed from: withAttribute */
    public KinesisDefaultEvent mo1141withAttribute(String str, String str2) {
        addAttribute(str, str2);
        return this;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    /* renamed from: withMetric */
    public KinesisDefaultEvent mo1142withMetric(String str, Double d) {
        addMetric(str, d);
        return this;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent
    /* renamed from: withPivot */
    public KinesisDefaultEvent mo1143withPivot(String str, String str2) {
        addPivot(str, str2);
        return this;
    }
}
