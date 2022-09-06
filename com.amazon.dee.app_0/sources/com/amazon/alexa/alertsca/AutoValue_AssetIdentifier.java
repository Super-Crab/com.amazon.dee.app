package com.amazon.alexa.alertsca;

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
public final class AutoValue_AssetIdentifier extends C$AutoValue_AssetIdentifier {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<AssetIdentifier> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("value");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_AssetIdentifier.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AssetIdentifier mo8353read(JsonReader jsonReader) throws IOException {
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
            return new AutoValue_AssetIdentifier(str);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, AssetIdentifier assetIdentifier) throws IOException {
            if (assetIdentifier == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("value"));
            if (assetIdentifier.getValue() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, assetIdentifier.getValue());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AssetIdentifier(final String str) {
        new AssetIdentifier(str) { // from class: com.amazon.alexa.alertsca.$AutoValue_AssetIdentifier
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
                if (!(obj instanceof AssetIdentifier)) {
                    return false;
                }
                return this.value.equals(((AssetIdentifier) obj).getValue());
            }

            @Override // com.amazon.alexa.alertsca.utils.StronglyTypedString
            public String getValue() {
                return this.value;
            }

            public int hashCode() {
                return this.value.hashCode() ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("AssetIdentifier{value="), this.value, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
