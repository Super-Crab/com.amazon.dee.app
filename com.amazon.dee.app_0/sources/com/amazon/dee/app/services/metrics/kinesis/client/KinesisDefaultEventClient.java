package com.amazon.dee.app.services.metrics.kinesis.client;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext;
import com.amazon.dee.app.services.metrics.kinesis.event.KinesisDefaultEvent;
import com.amazon.dee.app.services.metrics.kinesis.event.KinesisEvent;
import com.amazon.dee.app.services.metrics.kinesis.event.KinesisInternalEvent;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSession;
import com.amazon.dee.app.services.metrics.kinesis.session.SessionObserver;
import com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.KinesisRecorder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class KinesisDefaultEventClient implements KinesisInternalEventClient, SessionObserver {
    private static final long DEFAULT_SEND_EVENTS_INTERVAL = 60000;
    private static final long PERSISTED_RECORD_BUFFER = 100000;
    private static final String TAG = Log.tag(KinesisDefaultEventClient.class);
    private AppSession appSession;
    private final KinesisContext kinesisContext;
    private KinesisRecorder kinesisRecorder;
    private final String kinesisStreamName;
    private long lastSubmitAttemptTime;
    private boolean appInBackground = false;
    private String userDirectedId = "unknown";
    private final Map<String, String> globalAttributes = new ConcurrentHashMap();
    private final Map<String, Double> globalMetrics = new ConcurrentHashMap();
    private final Map<String, Map<String, String>> eventTypeAttributes = new ConcurrentHashMap();
    private final Map<String, Map<String, Double>> eventTypeMetrics = new ConcurrentHashMap();

    public KinesisDefaultEventClient(@NonNull Context context, @NonNull KinesisContext kinesisContext) {
        this.lastSubmitAttemptTime = 0L;
        this.lastSubmitAttemptTime = System.currentTimeMillis();
        this.kinesisContext = kinesisContext;
        this.kinesisStreamName = kinesisContext.getKinesisStreamName();
        File file = new File(context.getFilesDir() + File.separator + this.kinesisContext.getAppID());
        if (file.exists() || file.mkdirs()) {
            try {
                this.kinesisRecorder = new KinesisRecorder(file, this.kinesisContext.getKinesisAwsRegion(), kinesisContext.getAWSCredentialsProvider());
            } catch (IllegalArgumentException unused) {
                Log.e(TAG, "Init: Failed to instantiate KinesisRecorder");
            }
        }
    }

    private void addDirectedIdAttribute(KinesisEvent kinesisEvent) {
        kinesisEvent.addAttribute("DirectedID", this.userDirectedId);
    }

    @Nullable
    private String getSessionId() {
        AppSession appSession = this.appSession;
        if (appSession != null) {
            return appSession.getSessionID();
        }
        return null;
    }

    private long getSessionStartTime() {
        AppSession appSession = this.appSession;
        if (appSession != null) {
            return appSession.getStartTime().longValue();
        }
        return 0L;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void addGlobalAttribute(String str, String str2) {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            GeneratedOutlineSupport1.outline158("Null attribute value provided to addGlobalAttribute. attribute name:", str);
        } else {
            this.globalAttributes.put(str, str2);
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void addGlobalMetric(String str, Double d) {
        if (str == null) {
            return;
        }
        if (d == null) {
            GeneratedOutlineSupport1.outline158("Null metric value provided to addGlobalMetric.  metric name:", str);
        } else {
            this.globalMetrics.put(str, d);
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public KinesisEvent createEvent(String str) {
        return createEvent(str, true);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisInternalEventClient
    public KinesisInternalEvent createInternalEvent(String str, long j, Long l, Long l2) {
        return KinesisDefaultEvent.newInstance(this.kinesisContext, getSessionId(), Long.valueOf(j), l, l2, System.currentTimeMillis(), str);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisInternalEventClient
    public String getDirectedId() {
        return this.userDirectedId;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventRecorder
    public long getSizeOnDisk() {
        KinesisRecorder kinesisRecorder = this.kinesisRecorder;
        if (kinesisRecorder == null) {
            return 0L;
        }
        return kinesisRecorder.getDiskBytesUsed();
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.SessionObserver
    public void onSessionPause(AppSession appSession) {
        this.appSession = appSession;
        KinesisInternalEvent createInternalEvent = createInternalEvent("_session.pause", appSession.getStartTime().longValue(), null, appSession.getSessionDuration());
        addDirectedIdAttribute(createInternalEvent);
        recordEvent(createInternalEvent);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.SessionObserver
    public void onSessionResume(AppSession appSession) {
        this.appSession = appSession;
        KinesisEvent createEvent = createEvent("_session.resume");
        addDirectedIdAttribute(createEvent);
        recordEvent(createEvent);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.SessionObserver
    public void onSessionStart(AppSession appSession) {
        this.appSession = appSession;
        KinesisEvent createEvent = createEvent("_session.start");
        addDirectedIdAttribute(createEvent);
        recordEvent(createEvent);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.SessionObserver
    public void onSessionStop(AppSession appSession) {
        this.appSession = appSession;
        KinesisInternalEvent createInternalEvent = createInternalEvent("_session.stop", appSession.getStartTime().longValue(), Long.valueOf(appSession.getStopTime() == null ? 0L : appSession.getStopTime().longValue()), appSession.getSessionDuration());
        addDirectedIdAttribute(createInternalEvent);
        recordEvent(createInternalEvent);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void recordEvent(KinesisEvent kinesisEvent) {
        if (kinesisEvent == null || this.kinesisRecorder == null || !(kinesisEvent instanceof KinesisInternalEvent)) {
            return;
        }
        KinesisDefaultEvent createFromEvent = KinesisDefaultEvent.createFromEvent(this.kinesisContext, getSessionId(), System.currentTimeMillis(), (KinesisInternalEvent) kinesisEvent);
        if (TextUtils.isEmpty(createFromEvent.toString())) {
            return;
        }
        recordEvent(createFromEvent.toString());
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void removeGlobalAttribute(String str) {
        if (str == null) {
            return;
        }
        this.globalAttributes.remove(str);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void removeGlobalMetric(String str) {
        if (str == null) {
            return;
        }
        this.globalMetrics.remove(str);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisInternalEventClient
    public void setDirectedId(String str) {
        this.userDirectedId = str;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventRecorder
    public void submitEvents() {
        KinesisRecorder kinesisRecorder = this.kinesisRecorder;
        if (kinesisRecorder == null) {
            return;
        }
        try {
            try {
                try {
                    kinesisRecorder.submitAllRecords();
                } catch (Exception e) {
                    String str = TAG;
                    Log.e(str, e, "submitEvents: Operation failed with exception: " + e.getMessage(), new Object[0]);
                }
            } catch (InternalError e2) {
                String str2 = TAG;
                Log.e(str2, e2, "submitEvents: Operation failed with InternalError: " + e2.getMessage(), new Object[0]);
                if (!e2.getMessage().contains("Thread starting during runtime shutdown")) {
                    throw e2;
                }
            }
        } finally {
            this.lastSubmitAttemptTime = System.currentTimeMillis();
        }
    }

    public JSONObject toJSONObject() {
        JSONArray jSONArray = new JSONArray();
        Map<String, String> map = this.globalAttributes;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(entry.getKey(), entry.getValue());
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("toJSONObject: Operation failed with exception: ");
                    outline107.append(e.getMessage());
                    outline107.toString();
                }
            }
        }
        JSONArray jSONArray2 = new JSONArray();
        Map<String, Double> map2 = this.globalMetrics;
        if (map2 != null) {
            for (Map.Entry<String, Double> entry2 : map2.entrySet()) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(entry2.getKey(), entry2.getValue());
                    jSONArray2.put(jSONObject2);
                } catch (JSONException e2) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("toJSONObject: Operation failed with exception: ");
                    outline1072.append(e2.getMessage());
                    outline1072.toString();
                }
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        Map<String, Map<String, String>> map3 = this.eventTypeAttributes;
        if (map3 != null) {
            for (Map.Entry<String, Map<String, String>> entry3 : map3.entrySet()) {
                JSONArray jSONArray3 = new JSONArray();
                for (Map.Entry<String, String> entry4 : entry3.getValue().entrySet()) {
                    try {
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put(entry4.getKey(), entry4.getValue());
                        jSONArray3.put(jSONObject4);
                    } catch (JSONException e3) {
                        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("toJSONObject: Operation failed with exception: ");
                        outline1073.append(e3.getMessage());
                        outline1073.toString();
                    }
                }
                try {
                    jSONObject3.put(entry3.getKey(), jSONArray3);
                } catch (JSONException e4) {
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("toJSONObject: Operation failed with exception: ");
                    outline1074.append(e4.getMessage());
                    outline1074.toString();
                }
            }
        }
        JSONObject jSONObject5 = new JSONObject();
        Map<String, Map<String, Double>> map4 = this.eventTypeMetrics;
        if (map4 != null) {
            for (Map.Entry<String, Map<String, Double>> entry5 : map4.entrySet()) {
                JSONArray jSONArray4 = new JSONArray();
                for (Map.Entry<String, Double> entry6 : entry5.getValue().entrySet()) {
                    try {
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put(entry6.getKey(), entry6.getValue());
                        jSONArray4.put(jSONObject6);
                    } catch (JSONException e5) {
                        StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("toJSONObject: Operation failed with exception: ");
                        outline1075.append(e5.getMessage());
                        outline1075.toString();
                    }
                }
                try {
                    jSONObject5.put(entry5.getKey(), jSONArray4);
                } catch (JSONException e6) {
                    StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("toJSONObject: Operation failed with exception: ");
                    outline1076.append(e6.getMessage());
                    outline1076.toString();
                }
            }
        }
        JSONObject jSONObject7 = new JSONObject();
        try {
            jSONObject7.put("globalAttributes", jSONArray).put("globalMetrics", jSONArray2).put("eventTypeAttributes", jSONObject3).put("eventTypeMetrics", jSONObject5);
        } catch (JSONException e7) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("Error serializing session information ");
            outline1077.append(e7.getMessage());
            outline1077.toString();
        }
        return jSONObject7;
    }

    public String toString() {
        JSONObject jSONObject = toJSONObject();
        try {
            return jSONObject.toString(4);
        } catch (JSONException unused) {
            return jSONObject.toString();
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void updateAppBackgroundStatus(boolean z) {
        this.appInBackground = z;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisInternalEventClient
    public KinesisEvent createEvent(String str, boolean z) {
        if (str != null) {
            KinesisInternalEvent createInternalEvent = createInternalEvent(str, getSessionStartTime(), null, null);
            synchronized (this) {
                if (z) {
                    for (Map.Entry<String, String> entry : this.globalAttributes.entrySet()) {
                        createInternalEvent.addAttribute(entry.getKey(), entry.getValue());
                    }
                    if (this.eventTypeAttributes.containsKey(createInternalEvent.getEventType())) {
                        for (Map.Entry<String, String> entry2 : this.eventTypeAttributes.get(createInternalEvent.getEventType()).entrySet()) {
                            createInternalEvent.addAttribute(entry2.getKey(), entry2.getValue());
                        }
                    }
                    for (Map.Entry<String, Double> entry3 : this.globalMetrics.entrySet()) {
                        createInternalEvent.addMetric(entry3.getKey(), entry3.getValue());
                    }
                    if (this.eventTypeMetrics.containsKey(createInternalEvent.getEventType())) {
                        for (Map.Entry<String, Double> entry4 : this.eventTypeMetrics.get(createInternalEvent.getEventType()).entrySet()) {
                            createInternalEvent.addMetric(entry4.getKey(), entry4.getValue());
                        }
                    }
                }
            }
            return createInternalEvent;
        }
        throw new IllegalArgumentException("The eventType passed into create event was null");
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void removeGlobalAttribute(String str, String str2) {
        Map<String, String> map;
        if (str == null || str2 == null || (map = this.eventTypeAttributes.get(str)) == null) {
            return;
        }
        map.remove(str2);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void removeGlobalMetric(String str, String str2) {
        Map<String, Double> map;
        if (str == null || str2 == null || (map = this.eventTypeMetrics.get(str)) == null) {
            return;
        }
        map.remove(str2);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void addGlobalAttribute(String str, String str2, String str3) {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            GeneratedOutlineSupport1.outline158("Null attribute name provided to addGlobalAttribute ", str);
        } else if (str3 == null) {
            String.format("Null value provided to addGlobalAttribute %s, attributeName: %s", str, str2);
        } else {
            Map<String, String> map = this.eventTypeAttributes.get(str);
            if (map == null) {
                map = new ConcurrentHashMap<>();
                this.eventTypeAttributes.put(str, map);
            }
            map.put(str2, str3);
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient
    public void addGlobalMetric(String str, String str2, Double d) {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            GeneratedOutlineSupport1.outline158("Null metric name provided to addGlobalMetric ", str);
        } else if (d == null) {
            String.format("Null metric value provided to addGlobalMetric: %s, metric name: %s", str, str2);
        } else {
            Map<String, Double> map = this.eventTypeMetrics.get(str);
            if (map == null) {
                map = new ConcurrentHashMap<>();
                this.eventTypeMetrics.put(str, map);
            }
            map.put(str2, d);
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventRecorder
    public void recordEvent(@NonNull String str) {
        try {
            this.kinesisRecorder.saveRecord(str, this.kinesisStreamName);
            if (this.appInBackground || System.currentTimeMillis() - this.lastSubmitAttemptTime <= 60000 || getSizeOnDisk() < 100000) {
                return;
            }
            submitEvents();
        } catch (Exception e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("recordEvent: Failed to save record with exception: ");
            outline107.append(e.getMessage());
            outline107.toString();
        }
    }
}
