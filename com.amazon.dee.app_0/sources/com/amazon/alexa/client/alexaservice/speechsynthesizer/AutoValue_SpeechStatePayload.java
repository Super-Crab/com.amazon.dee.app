package com.amazon.alexa.client.alexaservice.speechsynthesizer;

import com.amazon.alexa.C0176Pce;
import com.amazon.alexa.Jxc;
import com.amazon.alexa.QeM;
import com.amazon.alexa.tui;
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
public final class AutoValue_SpeechStatePayload extends Jxc {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<tui> {
        public volatile TypeAdapter<Long> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<QeM.zZm> zQM;
        public volatile TypeAdapter<C0176Pce> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("token", "offsetInMilliseconds", "playerActivity");
            this.jiA = gson;
            this.zyO = Util.renameFields(Jxc.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, tui tuiVar) throws IOException {
            if (tuiVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("token"));
            Jxc jxc = (Jxc) tuiVar;
            if (jxc.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<C0176Pce> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(C0176Pce.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, jxc.zZm);
            }
            jsonWriter.name(this.zyO.get("offsetInMilliseconds"));
            TypeAdapter<Long> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.jiA.getAdapter(Long.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(jxc.BIo));
            jsonWriter.name(this.zyO.get("playerActivity"));
            if (jxc.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<QeM.zZm> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(QeM.zZm.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, jxc.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public tui mo8353read(JsonReader jsonReader) throws IOException {
            C0176Pce c0176Pce = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            QeM.zZm zzm = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("token").equals(nextName)) {
                        TypeAdapter<C0176Pce> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(C0176Pce.class);
                            this.zZm = typeAdapter;
                        }
                        c0176Pce = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("offsetInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        j = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else if (this.zyO.get("playerActivity").equals(nextName)) {
                        TypeAdapter<QeM.zZm> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(QeM.zZm.class);
                            this.zQM = typeAdapter3;
                        }
                        zzm = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SpeechStatePayload(c0176Pce, j, zzm);
        }
    }

    public AutoValue_SpeechStatePayload(C0176Pce c0176Pce, long j, QeM.zZm zzm) {
        super(c0176Pce, j, zzm);
    }
}
