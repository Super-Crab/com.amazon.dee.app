package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public abstract class BridgeMessage {
    public static final String MSG_ID_KEY = "id";
    public static final String MSG_NAME_KEY = "name";
    public static final String MSG_PAYLOAD_KEY = "payload";
    public static final String MSG_TYPE_KEY = "type";
    private final String id;
    private final String name;
    private final Object payload;
    private final MessageType type;

    /* loaded from: classes12.dex */
    public enum MessageType {
        REQUEST("request"),
        RESPONSE("response"),
        EVENT("event");
        
        private final String key;

        MessageType(String str) {
            this.key = str;
        }

        public static MessageType fromString(String str) {
            MessageType[] values;
            for (MessageType messageType : values()) {
                if (messageType.getKey().equalsIgnoreCase(str)) {
                    return messageType;
                }
            }
            return null;
        }

        public String getKey() {
            return this.key;
        }

        public String toMetricKey() {
            return this.key.equals("request") ? "req" : this.key.equals("event") ? "event" : "resp";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BridgeMessage(@NonNull String str, @NonNull String str2, @NonNull MessageType messageType, @Nullable Object obj) {
        this.name = str;
        this.id = str2;
        this.type = messageType;
        this.payload = obj;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @Nullable
    public Object getPayload() {
        return this.payload;
    }

    @NonNull
    public MessageType getType() {
        return this.type;
    }
}
