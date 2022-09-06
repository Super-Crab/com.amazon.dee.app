package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.JGr;
import com.amazon.alexa.XgI;
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
public final class AutoValue_AuthorizationCompletePayload extends XgI {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<JGr> {
        public volatile TypeAdapter<Set<JGr.zQM>> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Set<JGr.zZm>> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "authorized", (Object) "deauthorized");
            this.zyO = gson;
            this.zQM = Util.renameFields(XgI.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, JGr jGr) throws IOException {
            if (jGr == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("authorized"));
            XgI xgI = (XgI) jGr;
            if (xgI.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<JGr.zZm>> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(TypeToken.getParameterized(Set.class, JGr.zZm.class));
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, xgI.zZm);
            }
            jsonWriter.name(this.zQM.get("deauthorized"));
            if (xgI.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<JGr.zQM>> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(TypeToken.getParameterized(Set.class, JGr.zQM.class));
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, xgI.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public JGr mo8353read(JsonReader jsonReader) throws IOException {
            Set<JGr.zZm> set = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Set<JGr.zQM> set2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("authorized").equals(nextName)) {
                        TypeAdapter<Set<JGr.zZm>> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(TypeToken.getParameterized(Set.class, JGr.zZm.class));
                            this.zZm = typeAdapter;
                        }
                        set = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("deauthorized").equals(nextName)) {
                        TypeAdapter<Set<JGr.zQM>> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(TypeToken.getParameterized(Set.class, JGr.zQM.class));
                            this.BIo = typeAdapter2;
                        }
                        set2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AuthorizationCompletePayload(set, set2);
        }
    }

    public AutoValue_AuthorizationCompletePayload(Set<JGr.zZm> set, Set<JGr.zQM> set2) {
        super(set, set2);
    }
}
