package com.amazon.alexa.client.alexaservice.iocomponent.payload;

import com.amazon.alexa.Agi;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.JdP;
import com.amazon.alexa.tWv;
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
public final class AutoValue_IOComponentsStatePayload extends JdP {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Agi> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Set<tWv>> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "activeIOComponents", (Object) "allIOComponents");
            this.zQM = gson;
            this.BIo = Util.renameFields(JdP.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Agi agi) throws IOException {
            if (agi == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("activeIOComponents"));
            JdP jdP = (JdP) agi;
            if (jdP.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<tWv>> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(Set.class, tWv.class));
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, jdP.zZm);
            }
            jsonWriter.name(this.BIo.get("allIOComponents"));
            if (jdP.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<tWv>> typeAdapter2 = this.zZm;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zQM.getAdapter(TypeToken.getParameterized(Set.class, tWv.class));
                    this.zZm = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, jdP.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Agi mo8353read(JsonReader jsonReader) throws IOException {
            Set<tWv> set = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Set<tWv> set2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("activeIOComponents").equals(nextName)) {
                        TypeAdapter<Set<tWv>> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(Set.class, tWv.class));
                            this.zZm = typeAdapter;
                        }
                        set = typeAdapter.mo8353read(jsonReader);
                    } else if (this.BIo.get("allIOComponents").equals(nextName)) {
                        TypeAdapter<Set<tWv>> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zQM.getAdapter(TypeToken.getParameterized(Set.class, tWv.class));
                            this.zZm = typeAdapter2;
                        }
                        set2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_IOComponentsStatePayload(set, set2);
        }
    }

    public AutoValue_IOComponentsStatePayload(Set<tWv> set, Set<tWv> set2) {
        super(set, set2);
    }
}
