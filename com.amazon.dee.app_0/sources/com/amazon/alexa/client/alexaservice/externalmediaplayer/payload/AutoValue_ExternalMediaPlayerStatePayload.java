package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.AbstractC0188bKf;
import com.amazon.alexa.BGK;
import com.amazon.alexa.DZr;
import com.amazon.alexa.HkJ;
import com.amazon.alexa.vQe;
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
public final class AutoValue_ExternalMediaPlayerStatePayload extends DZr {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<BGK> {
        public volatile TypeAdapter<AbstractC0188bKf> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<vQe> zQM;
        public volatile TypeAdapter<String> zZm;
        public volatile TypeAdapter<Set<HkJ>> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("agent");
            arrayList.add("spiVersion");
            arrayList.add("playerInFocus");
            arrayList.add("players");
            this.Qle = gson;
            this.jiA = Util.renameFields(DZr.class, arrayList, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, BGK bgk) throws IOException {
            if (bgk == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get("agent"));
            DZr dZr = (DZr) bgk;
            if (dZr.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, dZr.zZm);
            }
            jsonWriter.name(this.jiA.get("spiVersion"));
            if (dZr.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AbstractC0188bKf> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Qle.getAdapter(AbstractC0188bKf.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, dZr.BIo);
            }
            jsonWriter.name(this.jiA.get("playerInFocus"));
            if (dZr.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Qle.getAdapter(vQe.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, dZr.zQM);
            }
            jsonWriter.name(this.jiA.get("players"));
            if (dZr.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<HkJ>> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Qle.getAdapter(TypeToken.getParameterized(Set.class, HkJ.class));
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, dZr.zyO);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public BGK mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            AbstractC0188bKf abstractC0188bKf = null;
            vQe vqe = null;
            Set<HkJ> set = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get("agent").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get("spiVersion").equals(nextName)) {
                        TypeAdapter<AbstractC0188bKf> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(AbstractC0188bKf.class);
                            this.BIo = typeAdapter2;
                        }
                        abstractC0188bKf = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.jiA.get("playerInFocus").equals(nextName)) {
                        TypeAdapter<vQe> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(vQe.class);
                            this.zQM = typeAdapter3;
                        }
                        vqe = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.jiA.get("players").equals(nextName)) {
                        TypeAdapter<Set<HkJ>> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(TypeToken.getParameterized(Set.class, HkJ.class));
                            this.zyO = typeAdapter4;
                        }
                        set = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ExternalMediaPlayerStatePayload(str, abstractC0188bKf, vqe, set);
        }
    }

    public AutoValue_ExternalMediaPlayerStatePayload(String str, AbstractC0188bKf abstractC0188bKf, vQe vqe, Set<HkJ> set) {
        super(str, abstractC0188bKf, vqe, set);
    }
}
