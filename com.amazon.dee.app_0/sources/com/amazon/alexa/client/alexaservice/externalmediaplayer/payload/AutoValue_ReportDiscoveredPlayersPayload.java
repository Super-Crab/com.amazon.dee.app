package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.LTt;
import com.amazon.alexa.vST;
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
public final class AutoValue_ReportDiscoveredPlayersPayload extends LTt {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<vST> {
        public volatile TypeAdapter<Set<vST.BIo>> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<String> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "agent", (Object) "players");
            this.zyO = gson;
            this.zQM = Util.renameFields(LTt.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, vST vst) throws IOException {
            if (vst == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("agent"));
            LTt lTt = (LTt) vst;
            if (lTt.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, lTt.zZm);
            }
            jsonWriter.name(this.zQM.get("players"));
            if (lTt.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<vST.BIo>> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(TypeToken.getParameterized(Set.class, vST.BIo.class));
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, lTt.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public vST mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Set<vST.BIo> set = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("agent").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("players").equals(nextName)) {
                        TypeAdapter<Set<vST.BIo>> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(TypeToken.getParameterized(Set.class, vST.BIo.class));
                            this.BIo = typeAdapter2;
                        }
                        set = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ReportDiscoveredPlayersPayload(str, set);
        }
    }

    public AutoValue_ReportDiscoveredPlayersPayload(String str, Set<vST.BIo> set) {
        super(str, set);
    }
}
