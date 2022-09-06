package com.amazon.alexa.client.alexaservice.navigation;

import com.amazon.alexa.jiW;
import com.amazon.alexa.pfY;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class AutoValue_CancelNavigationPayload extends jiW {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<pfY> {
        public GsonTypeAdapter(Gson gson) {
            Util.renameFields(jiW.class, new ArrayList(), gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, pfY pfy) throws IOException {
            if (pfy == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public pfY mo8353read(JsonReader jsonReader) throws IOException {
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
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            return new AutoValue_CancelNavigationPayload();
        }
    }
}
