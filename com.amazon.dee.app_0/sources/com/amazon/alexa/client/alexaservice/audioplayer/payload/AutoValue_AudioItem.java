package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import com.amazon.alexa.BIn;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.QCY;
import com.amazon.alexa.fcj;
import com.amazon.alexa.xNT;
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
public final class AutoValue_AudioItem extends QCY {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<BIn> {
        public volatile TypeAdapter<fcj> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<xNT> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "audioItemId", (Object) "stream");
            this.zyO = gson;
            this.zQM = Util.renameFields(QCY.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, BIn bIn) throws IOException {
            if (bIn == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("audioItemId"));
            QCY qcy = (QCY) bIn;
            if (qcy.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<xNT> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(xNT.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, qcy.zZm);
            }
            jsonWriter.name(this.zQM.get("stream"));
            if (qcy.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<fcj> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(fcj.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, qcy.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public BIn mo8353read(JsonReader jsonReader) throws IOException {
            xNT xnt = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            fcj fcjVar = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("audioItemId").equals(nextName)) {
                        TypeAdapter<xNT> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(xNT.class);
                            this.zZm = typeAdapter;
                        }
                        xnt = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("stream").equals(nextName)) {
                        TypeAdapter<fcj> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(fcj.class);
                            this.BIo = typeAdapter2;
                        }
                        fcjVar = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AudioItem(xnt, fcjVar);
        }
    }

    public AutoValue_AudioItem(xNT xnt, fcj fcjVar) {
        super(xnt, fcjVar);
    }
}
