package com.amazon.alexa.client.alexaservice.externalmediaplayer;

import androidx.annotation.Nullable;
import com.amazon.alexa.Iof;
import com.amazon.alexa.PUa;
import com.amazon.alexa.pfe;
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
public final class AutoValue_PlayerError extends PUa {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<pfe> {
        public volatile TypeAdapter<Long> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<Boolean> zQM;
        public volatile TypeAdapter<Iof> zZm;
        public volatile TypeAdapter<String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("errorName", "code", "fatal", "shouldCleanupSession", "description");
            this.Qle = gson;
            this.jiA = Util.renameFields(PUa.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, pfe pfeVar) throws IOException {
            if (pfeVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get("errorName"));
            PUa pUa = (PUa) pfeVar;
            if (pUa.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Iof> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(Iof.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, pUa.zZm);
            }
            jsonWriter.name(this.jiA.get("code"));
            TypeAdapter<Long> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.Qle.getAdapter(Long.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(pUa.BIo));
            jsonWriter.name(this.jiA.get("fatal"));
            TypeAdapter<Boolean> typeAdapter3 = this.zQM;
            if (typeAdapter3 == null) {
                typeAdapter3 = this.Qle.getAdapter(Boolean.class);
                this.zQM = typeAdapter3;
            }
            typeAdapter3.write(jsonWriter, Boolean.valueOf(pUa.zQM));
            jsonWriter.name(this.jiA.get("shouldCleanupSession"));
            TypeAdapter<Boolean> typeAdapter4 = this.zQM;
            if (typeAdapter4 == null) {
                typeAdapter4 = this.Qle.getAdapter(Boolean.class);
                this.zQM = typeAdapter4;
            }
            typeAdapter4.write(jsonWriter, Boolean.valueOf(pUa.zyO));
            jsonWriter.name(this.jiA.get("description"));
            if (pUa.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.zyO;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.Qle.getAdapter(String.class);
                    this.zyO = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, pUa.jiA);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public pfe mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            Iof iof = null;
            String str = null;
            boolean z = false;
            boolean z2 = false;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get("errorName").equals(nextName)) {
                        TypeAdapter<Iof> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(Iof.class);
                            this.zZm = typeAdapter;
                        }
                        iof = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get("code").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        j = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else if (this.jiA.get("fatal").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(Boolean.class);
                            this.zQM = typeAdapter3;
                        }
                        z = typeAdapter3.mo8353read(jsonReader).booleanValue();
                    } else if (this.jiA.get("shouldCleanupSession").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter4 = this.zQM;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(Boolean.class);
                            this.zQM = typeAdapter4;
                        }
                        z2 = typeAdapter4.mo8353read(jsonReader).booleanValue();
                    } else if (this.jiA.get("description").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.zyO;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.Qle.getAdapter(String.class);
                            this.zyO = typeAdapter5;
                        }
                        str = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayerError(iof, j, z, z2, str);
        }
    }

    public AutoValue_PlayerError(Iof iof, long j, boolean z, boolean z2, @Nullable String str) {
        super(iof, j, z, z2, str);
    }
}
