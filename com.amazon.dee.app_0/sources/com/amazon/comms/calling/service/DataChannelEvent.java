package com.amazon.comms.calling.service;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class DataChannelEvent {
    public static final String BUFFERED_AMOUNT = "bufferedAmount";
    public static final String DATA = "data";
    public static final String LABEL = "label";
    public static final String STATE = "state";
    private final Type eventType;
    private Map<String, Object> params;

    /* loaded from: classes11.dex */
    public static class DataChannelEventBuilder {
        private Type eventType;
        private ArrayList<String> params$key;
        private ArrayList<Object> params$value;

        DataChannelEventBuilder() {
        }

        public DataChannelEvent build() {
            Map emptyMap;
            ArrayList<String> arrayList = this.params$key;
            int size = arrayList == null ? 0 : arrayList.size();
            if (size == 0) {
                emptyMap = Collections.emptyMap();
            } else if (size != 1) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(this.params$key.size() < 1073741824 ? ((this.params$key.size() - 3) / 3) + this.params$key.size() + 1 : Integer.MAX_VALUE);
                for (int i = 0; i < this.params$key.size(); i++) {
                    linkedHashMap.put(this.params$key.get(i), this.params$value.get(i));
                }
                emptyMap = Collections.unmodifiableMap(linkedHashMap);
            } else {
                emptyMap = Collections.singletonMap(this.params$key.get(0), this.params$value.get(0));
            }
            return new DataChannelEvent(this.eventType, emptyMap);
        }

        public DataChannelEventBuilder clearParams() {
            ArrayList<String> arrayList = this.params$key;
            if (arrayList != null) {
                arrayList.clear();
                this.params$value.clear();
            }
            return this;
        }

        public DataChannelEventBuilder eventType(Type type) {
            this.eventType = type;
            return this;
        }

        public DataChannelEventBuilder param(String str, Object obj) {
            if (this.params$key == null) {
                this.params$key = new ArrayList<>();
                this.params$value = new ArrayList<>();
            }
            this.params$key.add(str);
            this.params$value.add(obj);
            return this;
        }

        public DataChannelEventBuilder params(Map<? extends String, ? extends Object> map) {
            if (this.params$key == null) {
                this.params$key = new ArrayList<>();
                this.params$value = new ArrayList<>();
            }
            for (Map.Entry<? extends String, ? extends Object> entry : map.entrySet()) {
                this.params$key.add(entry.getKey());
                this.params$value.add(entry.getValue());
            }
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DataChannelEvent.DataChannelEventBuilder(eventType=");
            outline107.append(this.eventType);
            outline107.append(", params$key=");
            outline107.append(this.params$key);
            outline107.append(", params$value=");
            outline107.append(this.params$value);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* loaded from: classes11.dex */
    public enum Type {
        ReceivedMessage,
        StateChanged,
        BufferedAmountChange
    }

    DataChannelEvent(Type type, Map<String, Object> map) {
        this.eventType = type;
        this.params = map;
    }

    public static DataChannelEventBuilder builder() {
        return new DataChannelEventBuilder();
    }

    public Type getEventType() {
        return this.eventType;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }
}
