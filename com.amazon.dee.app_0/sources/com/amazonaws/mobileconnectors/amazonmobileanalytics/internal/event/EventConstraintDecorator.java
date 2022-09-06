package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.Id;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.StringUtil;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes13.dex */
public class EventConstraintDecorator implements InternalEvent {
    static final int MAX_EVENT_ATTRIBUTE_METRIC_KEY_LENGTH = 40;
    static final int MAX_EVENT_ATTRIBUTE_VALUE_LENGTH = 200;
    static final int MAX_NUM_OF_METRICS_AND_ATTRIBUTES = 50;
    private static final String TAG = "EventConstraintDecorator";
    private final AtomicInteger currentNumOfAttributesAndMetrics = new AtomicInteger(0);
    private final InternalEvent decoratedEvent;
    private final int maxAttributesAndMetrics;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class Pair<T, U> {
        private T key;
        private U value;

        public Pair(T t, U u) {
            this.key = t;
            this.value = u;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public T getKey() {
            return this.key;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public U getValue() {
            return this.value;
        }
    }

    public EventConstraintDecorator(InternalEvent internalEvent, int i) {
        this.decoratedEvent = internalEvent;
        this.maxAttributesAndMetrics = i;
    }

    public static EventConstraintDecorator newInstance(InternalEvent internalEvent) {
        return new EventConstraintDecorator(internalEvent, 50);
    }

    private static Pair<String, String> processAttribute(String str, String str2) {
        String clipString = StringUtil.clipString(str, 40, false);
        if (clipString.length() < str.length()) {
            Log.w(TAG, "The attribute key has been trimmed to a length of 40 characters");
        }
        String clipString2 = StringUtil.clipString(str2, 200, false);
        if (clipString2.length() < str2.length()) {
            Log.w(TAG, "The attribute value has been trimmed to a length of 200 characters");
        }
        return new Pair<>(clipString, clipString2);
    }

    private static Pair<String, Double> processMetric(String str, Double d) {
        String clipString = StringUtil.clipString(str, 40, false);
        if (clipString.length() < str.length()) {
            Log.w(TAG, "The metric key has been trimmed to a length of 40 characters");
        }
        return new Pair<>(clipString, d);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public synchronized void addAttribute(String str, String str2) {
        if (str != null && str2 != null) {
            if (this.currentNumOfAttributesAndMetrics.get() < this.maxAttributesAndMetrics && !this.decoratedEvent.hasAttribute(str)) {
                Pair<String, String> processAttribute = processAttribute(str, str2);
                this.decoratedEvent.addAttribute((String) processAttribute.getKey(), (String) processAttribute.getValue());
                this.currentNumOfAttributesAndMetrics.incrementAndGet();
            } else if (this.decoratedEvent.hasAttribute(str)) {
                Pair<String, String> processAttribute2 = processAttribute(str, str2);
                this.decoratedEvent.addAttribute((String) processAttribute2.getKey(), (String) processAttribute2.getValue());
            }
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public synchronized void addMetric(String str, Double d) {
        if (str != null && d != null) {
            if (this.currentNumOfAttributesAndMetrics.get() < this.maxAttributesAndMetrics && !this.decoratedEvent.hasMetric(str)) {
                Pair<String, Double> processMetric = processMetric(str, d);
                this.decoratedEvent.addMetric((String) processMetric.getKey(), (Double) processMetric.getValue());
                this.currentNumOfAttributesAndMetrics.incrementAndGet();
            } else if (this.decoratedEvent.hasMetric(str)) {
                Pair<String, Double> processMetric2 = processMetric(str, d);
                this.decoratedEvent.addMetric((String) processMetric2.getKey(), (Double) processMetric2.getValue());
            }
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public ClientContext createClientContext(String str) {
        return this.decoratedEvent.createClientContext(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public Map<String, String> getAllAttributes() {
        return this.decoratedEvent.getAllAttributes();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public Map<String, Double> getAllMetrics() {
        return this.decoratedEvent.getAllMetrics();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public String getAttribute(String str) {
        return this.decoratedEvent.getAttribute(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public Long getEventTimestamp() {
        return this.decoratedEvent.getEventTimestamp();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public String getEventType() {
        return this.decoratedEvent.getEventType();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public Double getMetric(String str) {
        return this.decoratedEvent.getMetric(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public String getSdkName() {
        return this.decoratedEvent.getSdkName();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public String getSdkVersion() {
        return this.decoratedEvent.getSdkVersion();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public Long getSessionDuration() {
        return this.decoratedEvent.getSessionDuration();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public String getSessionId() {
        return this.decoratedEvent.getSessionId();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public long getSessionStart() {
        return this.decoratedEvent.getSessionStart();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public Long getSessionStop() {
        return this.decoratedEvent.getSessionStop();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent
    public Id getUniqueId() {
        return this.decoratedEvent.getUniqueId();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public boolean hasAttribute(String str) {
        return this.decoratedEvent.hasAttribute(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    public boolean hasMetric(String str) {
        return this.decoratedEvent.hasMetric(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONSerializable
    public JSONObject toJSONObject() {
        return this.decoratedEvent.toJSONObject();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    /* renamed from: withAttribute */
    public AnalyticsEvent mo6692withAttribute(String str, String str2) {
        addAttribute(str, str2);
        return this.decoratedEvent;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent
    /* renamed from: withMetric */
    public AnalyticsEvent mo6693withMetric(String str, Double d) {
        addMetric(str, d);
        return this.decoratedEvent;
    }
}
