package com.amazon.alexa.client.alexaservice.cardrenderer.payload;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.lsL;
import com.amazon.alexa.puf;
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
public final class AutoValue_AlexaImage extends puf {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<lsL> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Uri> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "url");
            this.zQM = gson;
            this.BIo = Util.renameFields(puf.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, lsL lsl) throws IOException {
            if (lsl == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("url"));
            puf pufVar = (puf) lsl;
            if (pufVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Uri> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(Uri.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, pufVar.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public lsL mo8353read(JsonReader jsonReader) throws IOException {
            Uri uri = null;
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
                    if (this.BIo.get("url").equals(nextName)) {
                        TypeAdapter<Uri> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Uri.class);
                            this.zZm = typeAdapter;
                        }
                        uri = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AlexaImage(uri);
        }
    }

    public AutoValue_AlexaImage(@Nullable Uri uri) {
        super(uri);
    }
}
