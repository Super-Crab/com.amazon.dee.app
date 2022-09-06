package com.amazon.alexa.biloba.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Objects;
/* loaded from: classes6.dex */
public class TriggerCondition {
    @SerializedName(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE)
    private String type = null;
    @SerializedName("conditionType")
    private ConditionTypeEnum conditionType = null;

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum ConditionTypeEnum {
        NO_ACTIVITY_DETECTED("NO_ACTIVITY_DETECTED"),
        ANY_ACTIVITY_DETECTED("ANY_ACTIVITY_DETECTED"),
        FIRST_ACTIVITY_OF_THE_DAY("FIRST_ACTIVITY_OF_THE_DAY");
        
        private String value;

        /* loaded from: classes6.dex */
        public static class Adapter extends TypeAdapter<ConditionTypeEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public ConditionTypeEnum mo8353read(JsonReader jsonReader) throws IOException {
                return ConditionTypeEnum.fromValue(String.valueOf(jsonReader.nextString()));
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, ConditionTypeEnum conditionTypeEnum) throws IOException {
                jsonWriter.value(conditionTypeEnum.getValue());
            }
        }

        ConditionTypeEnum(String str) {
            this.value = str;
        }

        public static ConditionTypeEnum fromValue(String str) {
            ConditionTypeEnum[] values;
            for (ConditionTypeEnum conditionTypeEnum : values()) {
                if (String.valueOf(conditionTypeEnum.value).equals(str)) {
                    return conditionTypeEnum;
                }
            }
            return null;
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
        return obj != null && TriggerCondition.class == obj.getClass();
    }

    public String getConditionType() {
        return this.conditionType.getValue();
    }

    public int hashCode() {
        return Objects.hash(new Object[0]);
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class TriggerCondition {\n", "    @type: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.type), "\n", "    conditionType: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.conditionType), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
