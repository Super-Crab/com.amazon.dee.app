package com.amazon.communication.heartbeat;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public abstract class HeartbeatControlMessage {

    /* loaded from: classes12.dex */
    public enum Type {
        TriggerLearning("TLC");
        
        private static Map<String, Type> MESSAGE_TYPE_MAP = null;
        public static final int SIZE_OF_MESSAGE_TYPE = 3;
        private String mMessageType;

        static {
            Type[] values;
            HashMap hashMap = new HashMap();
            for (Type type : values()) {
                hashMap.put(type.getMessageType(), type);
            }
            MESSAGE_TYPE_MAP = Collections.unmodifiableMap(hashMap);
        }

        Type(String str) {
            this.mMessageType = str;
        }

        public static Type forMessageType(String str) {
            Type type = MESSAGE_TYPE_MAP.get(str);
            if (type != null) {
                return type;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unknown messageType: ", str));
        }

        public String getMessageType() {
            return this.mMessageType;
        }
    }

    public abstract Type getType();
}
