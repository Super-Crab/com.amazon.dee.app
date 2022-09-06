package com.amazon.rtcmedia.webrtc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class RTCDataChannelEvent {
    private final Type eventType;
    private Map<String, Object> params;

    /* loaded from: classes13.dex */
    public static class RTCDataChannelEventBuilder {
        private Type eventType;
        private ArrayList<String> params$key;
        private ArrayList<Object> params$value;

        RTCDataChannelEventBuilder() {
        }

        public RTCDataChannelEvent build() {
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
            return new RTCDataChannelEvent(this.eventType, emptyMap);
        }

        public RTCDataChannelEventBuilder clearParams() {
            ArrayList<String> arrayList = this.params$key;
            if (arrayList != null) {
                arrayList.clear();
                this.params$value.clear();
            }
            return this;
        }

        public RTCDataChannelEventBuilder eventType(Type type) {
            this.eventType = type;
            return this;
        }

        public RTCDataChannelEventBuilder param(String str, Object obj) {
            if (this.params$key == null) {
                this.params$key = new ArrayList<>();
                this.params$value = new ArrayList<>();
            }
            this.params$key.add(str);
            this.params$value.add(obj);
            return this;
        }

        public RTCDataChannelEventBuilder params(Map<? extends String, ? extends Object> map) {
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
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RTCDataChannelEvent.RTCDataChannelEventBuilder(eventType=");
            outline107.append(this.eventType);
            outline107.append(", params$key=");
            outline107.append(this.params$key);
            outline107.append(", params$value=");
            outline107.append(this.params$value);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* loaded from: classes13.dex */
    public enum Type {
        RECEIVED_MESSAGE,
        STATE_CHANGED
    }

    RTCDataChannelEvent(Type type, Map<String, Object> map) {
        this.eventType = type;
        this.params = map;
    }

    public static RTCDataChannelEventBuilder builder() {
        return new RTCDataChannelEventBuilder();
    }

    public Type getEventType() {
        return this.eventType;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }
}
