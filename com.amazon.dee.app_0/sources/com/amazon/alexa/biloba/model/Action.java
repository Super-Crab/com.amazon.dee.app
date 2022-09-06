package com.amazon.alexa.biloba.model;

import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.metrics.VoxLaunchConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Objects;
/* loaded from: classes6.dex */
public class Action {
    @SerializedName("localizedTitle")
    private String localizedTitle = null;
    @SerializedName("type")
    private TypeEnum type = null;
    @SerializedName("target")
    private TargetEnum target = null;

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum TargetEnum {
        INITIATE_CALL("/comms/call"),
        INITIATE_DROPIN("/comms/dropin"),
        INITIATE_REMOTE_ASSIST("remote_assist/initiate"),
        SEND_MESSAGE("/comms/message"),
        SHARE_PHOTO("/comms/share"),
        SETUP_TIMEZONE("/settings/timezone"),
        SETUP_NOTIFICATION("/notification/setup"),
        LEARN_NOTIFICATION("/notification/learn"),
        SETUP_EC("/emergency/contact/setup"),
        LEARN_MORE_EC("/emergency/contact/learn"),
        CARE_PLUS_UPSELL(Routes.BILOBA_CARE_PLUS_UPSELL),
        CARE_PLUS_WELCOME(Routes.BILOBA_CARE_PLUS_WELCOME),
        INVITE_CARE_GIVER(Routes.BILOBA_INVITE_CARE_GIVER),
        EMERGENCY_HELPLINE_CALL_ENDED(Routes.BILOBA_EMERGENCY_HELPLINE_CALL_ENDED),
        EMERGENCY_HELPLINE_CALL_STARTED(Routes.BILOBA_EMERGENCY_HELPLINE_CALL_STARTED),
        ENABLE_REMOTE_MANAGEMENT("biloba/external/dashboard"),
        FINISH_EMERGENCY_ADDRESS_SETUP(Routes.BILOBA_FINISH_EMERGENCY_ADDRESS_SETUP),
        FINISH_CARE_PLUS_SETUP(Routes.BILOBA_FINISH_EMERGENCY_ADDRESS_SETUP),
        FINISH_COMMS_SETUP(Routes.BILOBA_FINISH_COMMS_SETUP),
        REMOTE_MANAGEMENT("biloba/external/dashboard"),
        TEST_EMERGENCY_HELPLINE(Routes.BILOBA_TEST_EMERGENCY_HELPLINE),
        VERIFIED_AND_UNVERIFIED_FALL(Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_AND_UNVERIFIED_FALL),
        VERIFIED_NO_FALL(Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_NO_FALL);
        
        private String value;

        /* loaded from: classes6.dex */
        public static class Adapter extends TypeAdapter<TargetEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public TargetEnum mo8353read(JsonReader jsonReader) throws IOException {
                return TargetEnum.fromValue(String.valueOf(jsonReader.nextString()));
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, TargetEnum targetEnum) throws IOException {
                jsonWriter.value(targetEnum.getValue());
            }
        }

        TargetEnum(String str) {
            this.value = str;
        }

        public static TargetEnum fromValue(String str) {
            TargetEnum[] values;
            for (TargetEnum targetEnum : values()) {
                if (String.valueOf(targetEnum.value).equals(str)) {
                    return targetEnum;
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

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum TypeEnum {
        URI("URI"),
        ROUTE(VoxLaunchConstants.ROUTE),
        COMMAND("COMMAND");
        
        private String value;

        /* loaded from: classes6.dex */
        public static class Adapter extends TypeAdapter<TypeEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public TypeEnum mo8353read(JsonReader jsonReader) throws IOException {
                return TypeEnum.fromValue(String.valueOf(jsonReader.nextString()));
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
                if (String.valueOf(typeEnum.value).equals(str)) {
                    return typeEnum;
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
        if (obj == null || Action.class != obj.getClass()) {
            return false;
        }
        Action action = (Action) obj;
        return Objects.equals(this.localizedTitle, action.localizedTitle) && Objects.equals(this.type, action.type) && Objects.equals(this.target, action.target);
    }

    public String getLocalizedTitle() {
        return this.localizedTitle;
    }

    public TargetEnum getTarget() {
        return this.target;
    }

    public TypeEnum getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(this.localizedTitle, this.type, this.target);
    }

    public Action localizedTitle(String str) {
        this.localizedTitle = str;
        return this;
    }

    public void setLocalizedTitle(String str) {
        this.localizedTitle = str;
    }

    public void setTarget(TargetEnum targetEnum) {
        this.target = targetEnum;
    }

    public void setType(TypeEnum typeEnum) {
        this.type = typeEnum;
    }

    public Action target(TargetEnum targetEnum) {
        this.target = targetEnum;
        return this;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class Action {\n", "    localizedTitle: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.localizedTitle), "\n", "    type: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.type), "\n", "    target: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.target), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public Action type(TypeEnum typeEnum) {
        this.type = typeEnum;
        return this;
    }
}
