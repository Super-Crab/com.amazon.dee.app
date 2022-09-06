package com.amazon.alexa.sharing.api.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Objects;
/* loaded from: classes10.dex */
public class Message {
    public static final String SERIALIZED_NAME_CONVERSATION_ID = "conversationId";
    public static final String SERIALIZED_NAME_ID = "id";
    public static final String SERIALIZED_NAME_LAST_UPDATE_ID = "lastUpdateId";
    public static final String SERIALIZED_NAME_MODIFIED_DATE = "modifiedDate";
    public static final String SERIALIZED_NAME_PARENT_ID = "parentId";
    public static final String SERIALIZED_NAME_PAYLOAD = "payload";
    public static final String SERIALIZED_NAME_SENDER_ID = "senderId";
    public static final String SERIALIZED_NAME_SENT_DATE = "sentDate";
    public static final String SERIALIZED_NAME_SORT_ID = "sortId";
    public static final String SERIALIZED_NAME_STATUS = "status";
    public static final String SERIALIZED_NAME_TYPE = "type";
    @SerializedName("conversationId")
    private String conversationId;
    @SerializedName("id")
    private String id;
    @SerializedName(SERIALIZED_NAME_LAST_UPDATE_ID)
    private Long lastUpdateId;
    @SerializedName(SERIALIZED_NAME_MODIFIED_DATE)
    private String modifiedDate;
    @SerializedName(SERIALIZED_NAME_PARENT_ID)
    private String parentId;
    @SerializedName("payload")
    private Payload payload;
    @SerializedName(SERIALIZED_NAME_SENDER_ID)
    private String senderId;
    @SerializedName(SERIALIZED_NAME_SENT_DATE)
    private String sentDate;
    @SerializedName(SERIALIZED_NAME_SORT_ID)
    private Long sortId;
    @SerializedName("status")
    private StatusEnum status;
    @SerializedName("type")
    private TypeEnum type;

    @JsonAdapter(Adapter.class)
    /* loaded from: classes10.dex */
    public enum StatusEnum {
        CREATED("created"),
        ACTIVE("active"),
        DELETED("deleted"),
        RECALLED("recalled");
        
        private final String value;

        /* loaded from: classes10.dex */
        public static class Adapter extends TypeAdapter<StatusEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public StatusEnum mo8353read(JsonReader jsonReader) throws IOException {
                return StatusEnum.fromValue(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, StatusEnum statusEnum) throws IOException {
                jsonWriter.value(statusEnum.getValue());
            }
        }

        StatusEnum(String str) {
            this.value = str;
        }

        public static StatusEnum fromValue(String str) {
            StatusEnum[] values;
            for (StatusEnum statusEnum : values()) {
                if (statusEnum.value.equals(str)) {
                    return statusEnum;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Unexpected value '", str, "'"));
        }

        public String getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    @JsonAdapter(Adapter.class)
    /* loaded from: classes10.dex */
    public enum TypeEnum {
        MESSAGE("message"),
        REACTION("reaction");
        
        private final String value;

        /* loaded from: classes10.dex */
        public static class Adapter extends TypeAdapter<TypeEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public TypeEnum mo8353read(JsonReader jsonReader) throws IOException {
                return TypeEnum.fromValue(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, TypeEnum typeEnum) throws IOException {
                jsonWriter.value(typeEnum.getValue());
            }
        }

        TypeEnum(String str) {
            this.value = str;
        }

        public static TypeEnum fromValue(String str) {
            TypeEnum[] values;
            for (TypeEnum typeEnum : values()) {
                if (typeEnum.value.equals(str)) {
                    return typeEnum;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Unexpected value '", str, "'"));
        }

        public String getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Message.class != obj.getClass()) {
            return false;
        }
        Message message = (Message) obj;
        return Objects.equals(this.id, message.id) && Objects.equals(this.conversationId, message.conversationId) && Objects.equals(this.sortId, message.sortId) && Objects.equals(this.lastUpdateId, message.lastUpdateId) && Objects.equals(this.parentId, message.parentId) && Objects.equals(this.senderId, message.senderId) && Objects.equals(this.sentDate, message.sentDate) && Objects.equals(this.modifiedDate, message.modifiedDate) && Objects.equals(this.status, message.status) && Objects.equals(this.type, message.type) && Objects.equals(this.payload, message.payload);
    }

    @NonNull
    public String getConversationId() {
        return this.conversationId;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public Long getLastUpdateId() {
        return this.lastUpdateId;
    }

    @NonNull
    public String getModifiedDate() {
        return this.modifiedDate;
    }

    @Nullable
    public String getParentId() {
        return this.parentId;
    }

    @NonNull
    public Payload getPayload() {
        return this.payload;
    }

    @NonNull
    public String getSenderId() {
        return this.senderId;
    }

    @NonNull
    public String getSentDate() {
        return this.sentDate;
    }

    @NonNull
    public Long getSortId() {
        return this.sortId;
    }

    @NonNull
    public StatusEnum getStatus() {
        return this.status;
    }

    @NonNull
    public TypeEnum getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.conversationId, this.sortId, this.lastUpdateId, this.parentId, this.senderId, this.sentDate, this.modifiedDate, this.status, this.type, this.payload);
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLastUpdateId(Long l) {
        this.lastUpdateId = l;
    }

    public void setModifiedDate(String str) {
        this.modifiedDate = str;
    }

    public void setParentId(String str) {
        this.parentId = str;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public void setSenderId(String str) {
        this.senderId = str;
    }

    public void setSentDate(String str) {
        this.sentDate = str;
    }

    public void setSortId(Long l) {
        this.sortId = l;
    }

    public void setStatus(StatusEnum statusEnum) {
        this.status = statusEnum;
    }

    public void setType(TypeEnum typeEnum) {
        this.type = typeEnum;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class Message {\n", "    id: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.id), "\n", "    conversationId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.conversationId), "\n", "    sortId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.sortId), "\n", "    lastUpdateId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.lastUpdateId), "\n", "    parentId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.parentId), "\n", "    senderId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.senderId), "\n", "    sentDate: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.sentDate), "\n", "    modifiedDate: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.modifiedDate), "\n", "    status: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.status), "\n", "    type: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.type), "\n", "    payload: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.payload), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
