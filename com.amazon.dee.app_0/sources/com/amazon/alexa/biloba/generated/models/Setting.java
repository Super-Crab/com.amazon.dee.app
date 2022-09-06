package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Objects;
/* loaded from: classes6.dex */
public class Setting {
    @SerializedName("value")
    private Object value = null;
    @SerializedName("type")
    private TypeEnum type = null;

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum TypeEnum {
        TIMEZONE("timezone");
        
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
        if (obj == null || Setting.class != obj.getClass()) {
            return false;
        }
        Setting setting = (Setting) obj;
        return Objects.equals(this.value, setting.value) && Objects.equals(this.type, setting.type);
    }

    public TypeEnum getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }

    public int hashCode() {
        return Objects.hash(this.value, this.type);
    }

    public void setType(TypeEnum typeEnum) {
        this.type = typeEnum;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class Setting {\n", "    value: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.value), "\n", "    type: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.type), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public Setting type(TypeEnum typeEnum) {
        this.type = typeEnum;
        return this;
    }

    public Setting value(Object obj) {
        this.value = obj;
        return this;
    }
}
