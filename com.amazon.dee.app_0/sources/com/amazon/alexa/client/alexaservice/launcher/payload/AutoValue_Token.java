package com.amazon.alexa.client.alexaservice.launcher.payload;

import com.amazon.alexa.IUU;
import com.amazon.alexa.bHH;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_Token extends bHH {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<IUU> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<String> zZm;

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, IUU iuu) throws IOException {
            if (iuu == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("value"));
            if (iuu.getValue() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, iuu.getValue());
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public IUU mo8353read(JsonReader jsonReader) throws IOException {
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
                    if (this.BIo.get("value").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Token(str);
        }
    }

    public AutoValue_Token(String str) {
        super(str);
    }
}
