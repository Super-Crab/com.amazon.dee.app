package com.amazon.alexa.client.alexaservice.audioplayer;

import com.amazon.alexa.AUQ;
import com.amazon.alexa.BkS;
import com.amazon.alexa.Puy;
import com.amazon.alexa.Vma;
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
public final class AutoValue_AudioPlayerStatePayload extends BkS {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Vma> {
        public volatile TypeAdapter<Long> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<AUQ> zQM;
        public volatile TypeAdapter<Puy> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("token", "offsetInMilliseconds", "playerActivity");
            this.jiA = gson;
            this.zyO = Util.renameFields(BkS.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Vma vma) throws IOException {
            if (vma == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("token"));
            BkS bkS = (BkS) vma;
            if (bkS.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Puy> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(Puy.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, bkS.zZm);
            }
            jsonWriter.name(this.zyO.get("offsetInMilliseconds"));
            TypeAdapter<Long> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.jiA.getAdapter(Long.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(bkS.BIo));
            jsonWriter.name(this.zyO.get("playerActivity"));
            if (bkS.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AUQ> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(AUQ.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, bkS.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Vma mo8353read(JsonReader jsonReader) throws IOException {
            Puy puy = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            AUQ auq = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("token").equals(nextName)) {
                        TypeAdapter<Puy> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(Puy.class);
                            this.zZm = typeAdapter;
                        }
                        puy = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("offsetInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        j = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else if (this.zyO.get("playerActivity").equals(nextName)) {
                        TypeAdapter<AUQ> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(AUQ.class);
                            this.zQM = typeAdapter3;
                        }
                        auq = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AudioPlayerStatePayload(puy, j, auq);
        }
    }

    public AutoValue_AudioPlayerStatePayload(Puy puy, long j, AUQ auq) {
        super(puy, j, auq);
    }
}
