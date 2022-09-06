package com.amazon.alexa.client.core.messages;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_DialogRequestIdentifier extends C$AutoValue_DialogRequestIdentifier {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DialogRequestIdentifier> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("value");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_DialogRequestIdentifier.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public DialogRequestIdentifier mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("value").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_DialogRequestIdentifier(str);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, DialogRequestIdentifier dialogRequestIdentifier) throws IOException {
            if (dialogRequestIdentifier == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("value"));
            if (dialogRequestIdentifier.getValue() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, dialogRequestIdentifier.getValue());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_DialogRequestIdentifier(final String str) {
        new DialogRequestIdentifier(str) { // from class: com.amazon.alexa.client.core.messages.$AutoValue_DialogRequestIdentifier
            private final String value;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.value = str;
                    return;
                }
                throw new NullPointerException("Null value");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof DialogRequestIdentifier)) {
                    return false;
                }
                return this.value.equals(((DialogRequestIdentifier) obj).getValue());
            }

            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString
            public String getValue() {
                return this.value;
            }

            public int hashCode() {
                return this.value.hashCode() ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("DialogRequestIdentifier{value="), this.value, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
