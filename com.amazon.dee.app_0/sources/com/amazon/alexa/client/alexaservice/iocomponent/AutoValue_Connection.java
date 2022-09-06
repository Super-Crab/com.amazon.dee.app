package com.amazon.alexa.client.alexaservice.iocomponent;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.kJm;
import com.amazon.alexa.urO;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_Connection extends kJm {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<urO> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<urO.zZm> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "type");
            this.zQM = gson;
            this.BIo = Util.renameFields(kJm.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, urO uro) throws IOException {
            if (uro == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("type"));
            kJm kjm = (kJm) uro;
            if (kjm.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<urO.zZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(urO.zZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, kjm.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public urO mo8353read(JsonReader jsonReader) throws IOException {
            urO.zZm zzm = null;
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
                    if (this.BIo.get("type").equals(nextName)) {
                        TypeAdapter<urO.zZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(urO.zZm.class);
                            this.zZm = typeAdapter;
                        }
                        zzm = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Connection(zzm);
        }
    }

    public AutoValue_Connection(urO.zZm zzm) {
        super(zzm);
    }
}
