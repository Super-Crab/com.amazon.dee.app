package com.amazon.alexa.client.alexaservice.launcher.payload;

import com.amazon.alexa.IUU;
import com.amazon.alexa.gUg;
import com.amazon.alexa.pUc;
import com.amazon.alexa.rSg;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_DisambiguateAndLaunchTargetPayload extends rSg {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<gUg> {
        public volatile TypeAdapter<List<pUc>> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<String> zQM;
        public volatile TypeAdapter<IUU> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("token", "targets", "description");
            this.jiA = gson;
            this.zyO = Util.renameFields(rSg.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, gUg gug) throws IOException {
            if (gug == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("token"));
            rSg rsg = (rSg) gug;
            if (rsg.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<IUU> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(IUU.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, rsg.zZm);
            }
            jsonWriter.name(this.zyO.get("targets"));
            if (rsg.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<pUc>> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.jiA.getAdapter(TypeToken.getParameterized(List.class, pUc.class));
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, rsg.BIo);
            }
            jsonWriter.name(this.zyO.get("description"));
            if (rsg.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(String.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, rsg.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public gUg mo8353read(JsonReader jsonReader) throws IOException {
            IUU iuu = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<pUc> list = null;
            String str = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("token").equals(nextName)) {
                        TypeAdapter<IUU> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(IUU.class);
                            this.zZm = typeAdapter;
                        }
                        iuu = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("targets").equals(nextName)) {
                        TypeAdapter<List<pUc>> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(TypeToken.getParameterized(List.class, pUc.class));
                            this.BIo = typeAdapter2;
                        }
                        list = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zyO.get("description").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(String.class);
                            this.zQM = typeAdapter3;
                        }
                        str = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_DisambiguateAndLaunchTargetPayload(iuu, list, str);
        }
    }

    public AutoValue_DisambiguateAndLaunchTargetPayload(IUU iuu, List<pUc> list, String str) {
        super(iuu, list, str);
    }
}
