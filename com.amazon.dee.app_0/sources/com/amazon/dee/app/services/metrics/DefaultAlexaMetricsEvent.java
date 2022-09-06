package com.amazon.dee.app.services.metrics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.dee.app.metrics.AlexaMetricsEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class DefaultAlexaMetricsEvent implements AlexaMetricsEvent {
    @VisibleForTesting
    static final String BLANK_STRING = "BLANK";
    @VisibleForTesting
    static final String NULL_STRING = "NULL";
    private static final String TAG = "DefaultAlexaMetricsEvent";
    private final String componentName;
    private final long eventDate;
    private final String eventName;
    @VisibleForTesting
    final int priority;
    private final Map<String, Object> customEntries = new HashMap();
    private boolean invalidated = false;

    @VisibleForTesting
    /* loaded from: classes12.dex */
    static class WrappedMap implements Map<String, Object> {
        private final Map<String, Object> wrappedMap;

        WrappedMap(Map<String, Object> map) {
            this.wrappedMap = map;
        }

        @Override // java.util.Map
        public void clear() {
            this.wrappedMap.clear();
        }

        @Override // java.util.Map
        public boolean containsKey(@Nullable Object obj) {
            return this.wrappedMap.containsKey(obj);
        }

        @Override // java.util.Map
        public boolean containsValue(@Nullable Object obj) {
            return this.wrappedMap.containsValue(obj);
        }

        @Override // java.util.Map
        @NonNull
        public Set<Map.Entry<String, Object>> entrySet() {
            return this.wrappedMap.entrySet();
        }

        @Override // java.util.Map
        @Nullable
        public Object get(@Nullable Object obj) {
            return this.wrappedMap.get(obj);
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.wrappedMap.isEmpty();
        }

        @Override // java.util.Map
        @NonNull
        public Set<String> keySet() {
            return this.wrappedMap.keySet();
        }

        @Override // java.util.Map
        public void putAll(@NonNull Map<? extends String, ? extends Object> map) {
            for (Map.Entry<? extends String, ? extends Object> entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }

        @Override // java.util.Map
        @Nullable
        public Object remove(@Nullable Object obj) {
            return this.wrappedMap.remove(obj);
        }

        @Override // java.util.Map
        public int size() {
            return this.wrappedMap.size();
        }

        @Override // java.util.Map
        @NonNull
        public Collection<Object> values() {
            return this.wrappedMap.values();
        }

        @Override // java.util.Map
        @Nullable
        public Object put(String str, Object obj) {
            if (obj == null) {
                return this.wrappedMap.put(str, "NULL");
            }
            if (TextUtils.isEmpty(obj.toString())) {
                return this.wrappedMap.put(str, DefaultAlexaMetricsEvent.BLANK_STRING);
            }
            return this.wrappedMap.put(str, obj);
        }
    }

    public DefaultAlexaMetricsEvent(String str, String str2, Map<String, Object> map) {
        this.eventName = str;
        this.componentName = str2;
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() == null) {
                    this.customEntries.put(entry.getKey(), "NULL");
                } else if (TextUtils.isEmpty(entry.getValue().toString())) {
                    this.customEntries.put(entry.getKey(), BLANK_STRING);
                } else {
                    this.customEntries.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.customEntries.containsKey("sendPriority")) {
            this.priority = ((Integer) this.customEntries.get("sendPriority")).intValue();
            this.customEntries.remove("sendPriority");
        } else {
            this.priority = 0;
        }
        Object obj = this.customEntries.get("EventTimestamp");
        if (obj instanceof Number) {
            this.eventDate = ((Number) obj).longValue();
            this.customEntries.remove("EventTimestamp");
            return;
        }
        this.eventDate = System.currentTimeMillis();
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public String getComponentName() {
        return this.componentName;
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public Map<String, Object> getCustomEntries() {
        return new WrappedMap(this.customEntries);
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public long getEventDate() {
        return this.eventDate;
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public String getEventName() {
        return this.eventName;
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public void invalidateEvent() {
        this.invalidated = true;
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public boolean isInvalidated() {
        return this.invalidated;
    }
}
