package com.amazon.alexa.client.alexaservice.display.window.payload;

import com.amazon.alexa.Bsa;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.KOy;
import com.amazon.alexa.uEF;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public final class AutoValue_DisplayWindowStatePayload extends KOy {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Bsa> {
        public volatile TypeAdapter<Set<uEF>> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<String> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "defaultWindowId", (Object) "instances");
            this.zyO = gson;
            this.zQM = Util.renameFields(KOy.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Bsa bsa) throws IOException {
            if (bsa == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("defaultWindowId"));
            KOy kOy = (KOy) bsa;
            if (kOy.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, kOy.zZm);
            }
            jsonWriter.name(this.zQM.get("instances"));
            if (kOy.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<uEF>> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(TypeToken.getParameterized(Set.class, uEF.class));
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, kOy.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Bsa mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Set<uEF> set = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("defaultWindowId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("instances").equals(nextName)) {
                        TypeAdapter<Set<uEF>> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(TypeToken.getParameterized(Set.class, uEF.class));
                            this.BIo = typeAdapter2;
                        }
                        set = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_DisplayWindowStatePayload(str, set);
        }
    }

    public AutoValue_DisplayWindowStatePayload(String str, Set<uEF> set) {
        super(str, set);
    }
}
