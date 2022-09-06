package com.amazon.alexa.client.alexaservice.capabilities;

import com.amazon.alexa.CAj;
import com.amazon.alexa.Cta;
import com.amazon.alexa.TpD;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
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
public final class AutoValue_CapabilityPublishRequest extends TpD {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<CAj> {
        public volatile TypeAdapter<List<Capability>> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<Cta> zQM;
        public volatile TypeAdapter<String> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("envelopeVersion", CamerasRouteParameter.CAPABILITIES, "legacyFlags");
            this.jiA = gson;
            this.zyO = Util.renameFields(TpD.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, CAj cAj) throws IOException {
            if (cAj == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("envelopeVersion"));
            TpD tpD = (TpD) cAj;
            if (tpD.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, tpD.zZm);
            }
            jsonWriter.name(this.zyO.get(CamerasRouteParameter.CAPABILITIES));
            if (tpD.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Capability>> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.jiA.getAdapter(TypeToken.getParameterized(List.class, Capability.class));
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, tpD.BIo);
            }
            jsonWriter.name(this.zyO.get("legacyFlags"));
            if (tpD.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Cta> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(Cta.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, tpD.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public CAj mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<Capability> list = null;
            Cta cta = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("envelopeVersion").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get(CamerasRouteParameter.CAPABILITIES).equals(nextName)) {
                        TypeAdapter<List<Capability>> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(TypeToken.getParameterized(List.class, Capability.class));
                            this.BIo = typeAdapter2;
                        }
                        list = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zyO.get("legacyFlags").equals(nextName)) {
                        TypeAdapter<Cta> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(Cta.class);
                            this.zQM = typeAdapter3;
                        }
                        cta = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_CapabilityPublishRequest(str, list, cta);
        }
    }

    public AutoValue_CapabilityPublishRequest(String str, List<Capability> list, Cta cta) {
        super(str, list, cta);
    }
}
