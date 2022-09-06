package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import com.amazon.alexa.Puy;
import com.amazon.alexa.lcl;
import com.amazon.alexa.pLw;
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
/* loaded from: classes6.dex */
public final class AutoValue_PlaybackStutterFinishedEventPayload extends lcl {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<pLw> {
        public volatile TypeAdapter<Long> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Puy> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("token", "offsetInMilliseconds", "stutterDurationInMilliseconds");
            this.zyO = gson;
            this.zQM = Util.renameFields(lcl.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, pLw plw) throws IOException {
            if (plw == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("token"));
            lcl lclVar = (lcl) plw;
            if (lclVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Puy> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(Puy.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, lclVar.zZm);
            }
            jsonWriter.name(this.zQM.get("offsetInMilliseconds"));
            TypeAdapter<Long> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zyO.getAdapter(Long.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(lclVar.BIo));
            jsonWriter.name(this.zQM.get("stutterDurationInMilliseconds"));
            TypeAdapter<Long> typeAdapter3 = this.BIo;
            if (typeAdapter3 == null) {
                typeAdapter3 = this.zyO.getAdapter(Long.class);
                this.BIo = typeAdapter3;
            }
            typeAdapter3.write(jsonWriter, Long.valueOf(lclVar.zQM));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public pLw mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            long j2 = 0;
            Puy puy = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("token").equals(nextName)) {
                        TypeAdapter<Puy> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Puy.class);
                            this.zZm = typeAdapter;
                        }
                        puy = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("offsetInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        j = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else if (this.zQM.get("stutterDurationInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter3 = this.BIo;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.zyO.getAdapter(Long.class);
                            this.BIo = typeAdapter3;
                        }
                        j2 = typeAdapter3.mo8353read(jsonReader).longValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlaybackStutterFinishedEventPayload(puy, j, j2);
        }
    }

    public AutoValue_PlaybackStutterFinishedEventPayload(Puy puy, long j, long j2) {
        super(puy, j, j2);
    }
}
