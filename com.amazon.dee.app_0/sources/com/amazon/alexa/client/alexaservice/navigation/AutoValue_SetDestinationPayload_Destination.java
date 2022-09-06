package com.amazon.alexa.client.alexaservice.navigation;

import androidx.annotation.Nullable;
import com.amazon.alexa.Alc;
import com.amazon.alexa.QqQ;
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
public final class AutoValue_SetDestinationPayload_Destination extends QqQ {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Alc.zZm> {
        public volatile TypeAdapter<String> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Alc.zZm.AbstractC0010zZm> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("coordinate");
            arrayList.add("singleLineDisplayAddress");
            arrayList.add("multipleLineDisplayAddress");
            arrayList.add("name");
            this.zyO = gson;
            this.zQM = Util.renameFields(QqQ.class, arrayList, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Alc.zZm zzm) throws IOException {
            if (zzm == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("coordinate"));
            QqQ qqQ = (QqQ) zzm;
            if (qqQ.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Alc.zZm.AbstractC0010zZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(Alc.zZm.AbstractC0010zZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, qqQ.zZm);
            }
            jsonWriter.name(this.zQM.get("singleLineDisplayAddress"));
            if (qqQ.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, qqQ.BIo);
            }
            jsonWriter.name(this.zQM.get("multipleLineDisplayAddress"));
            if (qqQ.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.BIo;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.zyO.getAdapter(String.class);
                    this.BIo = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, qqQ.zQM);
            }
            jsonWriter.name(this.zQM.get("name"));
            if (qqQ.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.BIo;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.zyO.getAdapter(String.class);
                    this.BIo = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, qqQ.zyO);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Alc.zZm mo8353read(JsonReader jsonReader) throws IOException {
            Alc.zZm.AbstractC0010zZm abstractC0010zZm = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("coordinate").equals(nextName)) {
                        TypeAdapter<Alc.zZm.AbstractC0010zZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Alc.zZm.AbstractC0010zZm.class);
                            this.zZm = typeAdapter;
                        }
                        abstractC0010zZm = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("singleLineDisplayAddress").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(String.class);
                            this.BIo = typeAdapter2;
                        }
                        str = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zQM.get("multipleLineDisplayAddress").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.BIo;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.zyO.getAdapter(String.class);
                            this.BIo = typeAdapter3;
                        }
                        str2 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.zQM.get("name").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.BIo;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.zyO.getAdapter(String.class);
                            this.BIo = typeAdapter4;
                        }
                        str3 = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SetDestinationPayload_Destination(abstractC0010zZm, str, str2, str3);
        }
    }

    public AutoValue_SetDestinationPayload_Destination(Alc.zZm.AbstractC0010zZm abstractC0010zZm, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        super(abstractC0010zZm, str, str2, str3);
    }
}
