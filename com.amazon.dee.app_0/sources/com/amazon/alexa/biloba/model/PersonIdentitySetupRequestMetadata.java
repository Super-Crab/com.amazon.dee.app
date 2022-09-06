package com.amazon.alexa.biloba.model;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
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
public class PersonIdentitySetupRequestMetadata {
    @SerializedName("client")
    PersonIdentityClient client;
    @SerializedName("locale")
    String locale;
    @SerializedName(MetricsConfiguration.PLATFORM)
    PersonIdentityClientPlatformEnum platform;

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum PersonIdentityClientPlatformEnum {
        IOS("IOS"),
        ANDROID("ANDROID"),
        MACOS("MACOS"),
        WINDOWS("WINDOWS"),
        WEB("WEB");
        
        private String value;

        /* loaded from: classes6.dex */
        public static class Adapter extends TypeAdapter<PersonIdentityClientPlatformEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public PersonIdentityClientPlatformEnum mo8353read(JsonReader jsonReader) throws IOException {
                return PersonIdentityClientPlatformEnum.fromValue(String.valueOf(jsonReader.nextString()));
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, PersonIdentityClientPlatformEnum personIdentityClientPlatformEnum) throws IOException {
                jsonWriter.value(personIdentityClientPlatformEnum.getValue());
            }
        }

        PersonIdentityClientPlatformEnum(String str) {
            this.value = str;
        }

        public static PersonIdentityClientPlatformEnum fromValue(String str) {
            PersonIdentityClientPlatformEnum[] values;
            for (PersonIdentityClientPlatformEnum personIdentityClientPlatformEnum : values()) {
                if (String.valueOf(personIdentityClientPlatformEnum.value).equals(str)) {
                    return personIdentityClientPlatformEnum;
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

    public PersonIdentitySetupRequestMetadata(String str, PersonIdentityClientPlatformEnum personIdentityClientPlatformEnum, PersonIdentityClient personIdentityClient) {
        this.locale = str;
        this.platform = personIdentityClientPlatformEnum;
        this.client = personIdentityClient;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PersonIdentitySetupRequest{locale='");
        GeneratedOutlineSupport1.outline176(outline107, this.locale, Chars.QUOTE, ", platform=");
        outline107.append(this.platform);
        outline107.append(", client=");
        outline107.append(this.client);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
