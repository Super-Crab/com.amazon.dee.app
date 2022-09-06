package com.amazon.alexa.client.alexaservice.system.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.GUj;
import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import com.amazon.alexa.sGd;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_LocalesPayload extends GUj {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<sGd> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<List<Locale>> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) SystemModelI18nConfigTransformer.KEY_LOCALES);
            this.zQM = gson;
            this.BIo = Util.renameFields(GUj.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, sGd sgd) throws IOException {
            if (sgd == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get(SystemModelI18nConfigTransformer.KEY_LOCALES));
            GUj gUj = (GUj) sgd;
            if (gUj.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Locale>> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(List.class, Locale.class));
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, gUj.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public sGd mo8353read(JsonReader jsonReader) throws IOException {
            List<Locale> list = null;
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
                    if (this.BIo.get(SystemModelI18nConfigTransformer.KEY_LOCALES).equals(nextName)) {
                        TypeAdapter<List<Locale>> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(List.class, Locale.class));
                            this.zZm = typeAdapter;
                        }
                        list = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_LocalesPayload(list);
        }
    }

    public AutoValue_LocalesPayload(List<Locale> list) {
        super(list);
    }
}
