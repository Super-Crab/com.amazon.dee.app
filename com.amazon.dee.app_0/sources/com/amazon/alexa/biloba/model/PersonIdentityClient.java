package com.amazon.alexa.biloba.model;

import com.amazon.comms.models.gui.MediaSettingsInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public class PersonIdentityClient {
    @SerializedName("appVersion")
    String appVersion;
    @SerializedName("componentVersion")
    String componentVersion;
    @SerializedName("id")
    String id;
    @SerializedName("supported")
    PersonIdentityClientSupportedEnum supported;

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum PersonIdentityClientSupportedEnum {
        NOT_APPLICABLE(MediaSettingsInfo.ENHANCED_PROCESSING_NOT_APPLICABLE),
        SUPPORTED("SUPPORTED"),
        UNSUPPORTED_APP_VERSION("UNSUPPORTED_APP_VERSION"),
        UNSUPPORTED_COMPONENT_VERSION("UNSUPPORTED_COMPONENT_VERSION"),
        UNSUPPORTED_PLATFORM("UNSUPPORTED_PLATFORM"),
        UNSUPPORTED_CLIENT("UNSUPPORTED_CLIENT");
        
        private String value;

        /* loaded from: classes6.dex */
        public static class Adapter extends TypeAdapter<PersonIdentityClientSupportedEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public PersonIdentityClientSupportedEnum mo8353read(JsonReader jsonReader) throws IOException {
                return PersonIdentityClientSupportedEnum.fromValue(String.valueOf(jsonReader.nextString()));
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, PersonIdentityClientSupportedEnum personIdentityClientSupportedEnum) throws IOException {
                jsonWriter.value(personIdentityClientSupportedEnum.getValue());
            }
        }

        PersonIdentityClientSupportedEnum(String str) {
            this.value = str;
        }

        public static PersonIdentityClientSupportedEnum fromValue(String str) {
            PersonIdentityClientSupportedEnum[] values;
            for (PersonIdentityClientSupportedEnum personIdentityClientSupportedEnum : values()) {
                if (String.valueOf(personIdentityClientSupportedEnum.value).equals(str)) {
                    return personIdentityClientSupportedEnum;
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

    public PersonIdentityClient(String str, String str2, String str3, PersonIdentityClientSupportedEnum personIdentityClientSupportedEnum) {
        this.appVersion = str;
        this.componentVersion = str2;
        this.id = str3;
        this.supported = personIdentityClientSupportedEnum;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getComponentVersion() {
        return this.componentVersion;
    }

    public String getId() {
        return this.id;
    }

    public PersonIdentityClientSupportedEnum getSupported() {
        return this.supported;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PersonIdentityClient{appVersion='");
        GeneratedOutlineSupport1.outline176(outline107, this.appVersion, Chars.QUOTE, ", componentVersion='");
        GeneratedOutlineSupport1.outline176(outline107, this.componentVersion, Chars.QUOTE, ", id='");
        GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", supported=");
        outline107.append(this.supported);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
