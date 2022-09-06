package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONBuilder;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONSerializable;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.Preconditions;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.StringUtil;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes13.dex */
public class DefaultEventClient implements InternalEventClient, JSONSerializable {
    private static final String ANALYTICS_ENABLED = "isAnalyticsEnabled";
    private static final int MAX_EVENT_TYPE_LENGTH = 50;
    private static String TAG = "DefaultEventClient";
    private boolean allowEventCollection;
    private final AnalyticsContext context;
    private final DeliveryClient deliveryClient;
    private String sessionId;
    private long sessionStartTime;
    private final Map<String, String> globalAttributes = new ConcurrentHashMap();
    private final Map<String, Double> globalMetrics = new ConcurrentHashMap();
    private final Map<String, Map<String, String>> eventTypeAttributes = new ConcurrentHashMap();
    private final Map<String, Map<String, Double>> eventTypeMetrics = new ConcurrentHashMap();
    private List<EventObserver> observers = new CopyOnWriteArrayList();

    public DefaultEventClient(AnalyticsContext analyticsContext, boolean z) {
        this.allowEventCollection = true;
        Preconditions.checkNotNull(analyticsContext, "A valid context must be provided");
        Preconditions.checkNotNull(analyticsContext.getDeliveryClient(), "A valid DeliveryClient must be provided");
        this.allowEventCollection = z;
        this.context = analyticsContext;
        this.deliveryClient = analyticsContext.getDeliveryClient();
        addEventObserver(this.deliveryClient);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient
    public void addEventObserver(EventObserver eventObserver) {
        if (eventObserver == null) {
            Log.w(TAG, "Null EventObserver provided to addObserver");
        } else if (!getEventObservers().contains(eventObserver)) {
            getEventObservers().add(eventObserver);
        } else {
            Log.w(TAG, "Observer was already registered with this EventRecorder");
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
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

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public void addGlobalMetric(String str, Double d) {
        if (str == null) {
            Log.w(TAG, "Null metric name provided to addGlobalMetric");
        } else if (d == null) {
            GeneratedOutlineSupport1.outline164("Null metric value provided to addGlobalMetric.  metric name:", str, TAG);
        } else {
            this.globalMetrics.put(str, d);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public AnalyticsEvent createEvent(String str) {
        return createEvent(str, true);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient
    public InternalEvent createInternalEvent(String str, long j, Long l, Long l2) {
        return DefaultEvent.newInstance(this.context, this.sessionId, Long.valueOf(j), l, l2, System.currentTimeMillis(), str);
    }

    public boolean getAllowEventCollection() {
        return this.allowEventCollection;
    }

    protected List<EventObserver> getEventObservers() {
        if (this.observers == null) {
            this.observers = new ArrayList();
        }
        return this.observers;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient
    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient
    public long getSessionStartTime() {
        return this.sessionStartTime;
    }

    protected void notifyObservers(InternalEvent internalEvent) {
        for (EventObserver eventObserver : getEventObservers()) {
            eventObserver.notify(internalEvent);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public void recordEvent(AnalyticsEvent analyticsEvent) {
        if (analyticsEvent == null) {
            Log.i(TAG, "The provided event was null");
        } else if (!this.context.getConfiguration().optBoolean(ANALYTICS_ENABLED, true).booleanValue() || !getAllowEventCollection()) {
        } else {
            if (analyticsEvent instanceof InternalEvent) {
                notifyObservers(DefaultEvent.createFromEvent(this.context, this.sessionId, System.currentTimeMillis(), (InternalEvent) analyticsEvent));
                return;
            }
            Log.e(TAG, "Error recording event, this event cannot be stored");
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient
    public void removeEventObserver(EventObserver eventObserver) {
        String str = TAG;
        if (eventObserver == null) {
            Log.w(str, "Null EventObserver provided to removeObserver");
            return;
        }
        eventObserver.toString();
        if (getEventObservers().contains(eventObserver)) {
            getEventObservers().remove(eventObserver);
        } else {
            Log.w(TAG, "Observer was not registered with this EventRecorder because it already exists");
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public void removeGlobalAttribute(String str) {
        if (str == null) {
            Log.w(TAG, "Null attribute name provided to removeGlobalAttribute");
        } else {
            this.globalAttributes.remove(str);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public void removeGlobalMetric(String str) {
        if (str == null) {
            Log.w(TAG, "Null metric name provided to removeGlobalMetric");
        } else {
            this.globalMetrics.remove(str);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient
    public void setSessionId(String str) {
        this.sessionId = str;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient
    public void setSessionStartTime(long j) {
        this.sessionStartTime = j;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public void submitEvents() {
        this.deliveryClient.attemptDelivery();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONSerializable
    public JSONObject toJSONObject() {
        JSONArray jSONArray = new JSONArray();
        List<EventObserver> list = this.observers;
        if (list != null) {
            for (EventObserver eventObserver : list) {
                if (JSONSerializable.class.isAssignableFrom(eventObserver.getClass())) {
                    jSONArray.put(((JSONSerializable) eventObserver).toJSONObject());
                } else {
                    jSONArray.put(eventObserver);
                }
            }
        }
        JSONArray jSONArray2 = new JSONArray();
        Map<String, String> map = this.globalAttributes;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(entry.getKey(), entry.getValue());
                    jSONArray2.put(jSONObject);
                } catch (JSONException unused) {
                }
            }
        }
        JSONArray jSONArray3 = new JSONArray();
        Map<String, Double> map2 = this.globalMetrics;
        if (map2 != null) {
            for (Map.Entry<String, Double> entry2 : map2.entrySet()) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(entry2.getKey(), entry2.getValue());
                    jSONArray3.put(jSONObject2);
                } catch (JSONException unused2) {
                }
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        Map<String, Map<String, String>> map3 = this.eventTypeAttributes;
        if (map3 != null) {
            for (Map.Entry<String, Map<String, String>> entry3 : map3.entrySet()) {
                JSONArray jSONArray4 = new JSONArray();
                for (Map.Entry<String, String> entry4 : entry3.getValue().entrySet()) {
                    try {
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put(entry4.getKey(), entry4.getValue());
                        jSONArray4.put(jSONObject4);
                    } catch (JSONException unused3) {
                    }
                }
                try {
                    jSONObject3.put(entry3.getKey(), jSONArray4);
                } catch (JSONException unused4) {
                }
            }
        }
        JSONObject jSONObject5 = new JSONObject();
        Map<String, Map<String, Double>> map4 = this.eventTypeMetrics;
        if (map4 != null) {
            for (Map.Entry<String, Map<String, Double>> entry5 : map4.entrySet()) {
                JSONArray jSONArray5 = new JSONArray();
                for (Map.Entry<String, Double> entry6 : entry5.getValue().entrySet()) {
                    try {
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put(entry6.getKey(), entry6.getValue());
                        jSONArray5.put(jSONObject6);
                    } catch (JSONException unused5) {
                    }
                }
                try {
                    jSONObject5.put(entry5.getKey(), jSONArray5);
                } catch (JSONException unused6) {
                }
            }
        }
        return new JSONBuilder(this).withAttribute("uniqueId", this.context.getUniqueId()).withAttribute("observers", jSONArray).withAttribute("globalAttributes", jSONArray2).withAttribute("globalMetrics", jSONArray3).withAttribute("eventTypeAttributes", jSONObject3).withAttribute("eventTypeMetrics", jSONObject5).toJSONObject();
    }

    public String toString() {
        JSONObject jSONObject = toJSONObject();
        try {
            return jSONObject.toString(4);
        } catch (JSONException unused) {
            return jSONObject.toString();
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient
    public AnalyticsEvent createEvent(String str, boolean z) {
        if (str != null) {
            String clipString = StringUtil.clipString(str, 50, false);
            if (clipString.length() < str.length()) {
                Log.w(TAG, "The event type has been trimmed to a length of 50 characters");
            }
            EventConstraintDecorator newInstance = EventConstraintDecorator.newInstance(createInternalEvent(clipString, this.sessionStartTime, null, null));
            synchronized (this) {
                if (z) {
                    for (Map.Entry<String, String> entry : this.globalAttributes.entrySet()) {
                        newInstance.addAttribute(entry.getKey(), entry.getValue());
                    }
                    if (this.eventTypeAttributes.containsKey(newInstance.getEventType())) {
                        for (Map.Entry<String, String> entry2 : this.eventTypeAttributes.get(newInstance.getEventType()).entrySet()) {
                            newInstance.addAttribute(entry2.getKey(), entry2.getValue());
                        }
                    }
                    for (Map.Entry<String, Double> entry3 : this.globalMetrics.entrySet()) {
                        newInstance.addMetric(entry3.getKey(), entry3.getValue());
                    }
                    if (this.eventTypeMetrics.containsKey(newInstance.getEventType())) {
                        for (Map.Entry<String, Double> entry4 : this.eventTypeMetrics.get(newInstance.getEventType()).entrySet()) {
                            newInstance.addMetric(entry4.getKey(), entry4.getValue());
                        }
                    }
                }
            }
            return newInstance;
        }
        throw new IllegalArgumentException("The eventType passed into create event was null");
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public void addGlobalAttribute(String str, String str2, String str3) {
        if (str == null) {
            Log.w(TAG, "Null eventType provided to addGlobalAttribute");
        } else if (str2 == null) {
            GeneratedOutlineSupport1.outline164("Null attribute name provided to addGlobalAttribute. eventType:", str, TAG);
        } else if (str3 == null) {
            String str4 = TAG;
            Log.w(str4, "Null value provided to addGlobalAttribute. eventType:" + str + ", attributeName:" + str2);
        } else {
            Map<String, String> map = this.eventTypeAttributes.get(str);
            if (map == null) {
                map = new ConcurrentHashMap<>();
                this.eventTypeAttributes.put(str, map);
            }
            map.put(str2, str3);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public void removeGlobalAttribute(String str, String str2) {
        if (str == null) {
            Log.w(TAG, "Null eventType provided to removeGlobalAttribute");
        } else if (str2 == null) {
            Log.w(TAG, "Null attribute name provided to removeGlobalAttribute");
        } else {
            Map<String, String> map = this.eventTypeAttributes.get(str);
            if (map == null) {
                return;
            }
            map.remove(str2);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public void removeGlobalMetric(String str, String str2) {
        if (str == null) {
            Log.w(TAG, "Null eventType provided to removeGlobalMetric");
        } else if (str2 == null) {
            Log.w(TAG, "Null metric name provided to removeGlobalMetric");
        } else {
            Map<String, Double> map = this.eventTypeMetrics.get(str);
            if (map == null) {
                return;
            }
            map.remove(str2);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient
    public void addGlobalMetric(String str, String str2, Double d) {
        if (str == null) {
            Log.w(TAG, "Null eventType provided to addGlobalMetric");
        } else if (str2 == null) {
            GeneratedOutlineSupport1.outline164("Null metric name provided to addGlobalMetric. eventType:", str, TAG);
        } else if (d == null) {
            String str3 = TAG;
            Log.w(str3, "Null metric value provided to addGlobalMetric. eventType:" + str + ", metric name:" + str2);
        } else {
            Map<String, Double> map = this.eventTypeMetrics.get(str);
            if (map == null) {
                map = new ConcurrentHashMap<>();
                this.eventTypeMetrics.put(str, map);
            }
            map.put(str2, d);
        }
    }
}
