package com.amazon.alexa.biloba.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.lwansbrough.RCTCamera.RCTCameraModule;
import java.io.IOException;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public class PersonIdentityStep {
    @SerializedName("config")
    PersonIdentityStepConfig config;
    @SerializedName("fallbackRoute")
    String fallbackRoute;
    @SerializedName(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM)
    String medium;
    @SerializedName("order")
    Integer order;
    @SerializedName("route")
    String route;
    @SerializedName("type")
    PersonIdentityStepTypeEnum type;

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum PersonIdentityStepTypeEnum {
        PRIMER("PRIMER"),
        INTRODUCTION("INTRODUCTION"),
        PHONE_LINKING("PHONE_LINKING"),
        VOICE_CODE("VOICE_CODE"),
        VOICE_RECOGNITION("VOICE_RECOGNITION"),
        COMPLETION("COMPLETION"),
        VOICE_CODE_VERIFICATION("VOICE_CODE_VERIFICATION"),
        PHONE_VERIFICATION("PHONE_VERIFICATION");
        
        private String value;

        /* loaded from: classes6.dex */
        public static class Adapter extends TypeAdapter<PersonIdentityStepTypeEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public PersonIdentityStepTypeEnum mo8353read(JsonReader jsonReader) throws IOException {
                return PersonIdentityStepTypeEnum.fromValue(String.valueOf(jsonReader.nextString()));
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, PersonIdentityStepTypeEnum personIdentityStepTypeEnum) throws IOException {
                jsonWriter.value(personIdentityStepTypeEnum.getValue());
            }
        }

        PersonIdentityStepTypeEnum(String str) {
            this.value = str;
        }

        public static PersonIdentityStepTypeEnum fromValue(String str) {
            PersonIdentityStepTypeEnum[] values;
            for (PersonIdentityStepTypeEnum personIdentityStepTypeEnum : values()) {
                if (String.valueOf(personIdentityStepTypeEnum.value).equals(str)) {
                    return personIdentityStepTypeEnum;
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

    public PersonIdentityStepConfig getConfig() {
        return this.config;
    }

    public String getFallbackRoute() {
        return this.fallbackRoute;
    }

    public String getMedium() {
        return this.medium;
    }

    public Integer getOrder() {
        return this.order;
    }

    public String getRoute() {
        return this.route;
    }

    public PersonIdentityStepTypeEnum getType() {
        return this.type;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PersonIdentityStep{order=");
        outline107.append(this.order);
        outline107.append(", type=");
        outline107.append(this.type);
        outline107.append(", medium='");
        GeneratedOutlineSupport1.outline176(outline107, this.medium, Chars.QUOTE, ", route='");
        GeneratedOutlineSupport1.outline176(outline107, this.route, Chars.QUOTE, ", fallbackRoute='");
        GeneratedOutlineSupport1.outline176(outline107, this.fallbackRoute, Chars.QUOTE, ", config=");
        outline107.append(this.config);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
