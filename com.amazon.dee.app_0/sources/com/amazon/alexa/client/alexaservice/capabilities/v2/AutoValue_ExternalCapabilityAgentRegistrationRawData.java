package com.amazon.alexa.client.alexaservice.capabilities.v2;

import com.amazon.alexa.EPu;
import com.amazon.alexa.Ubd;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.iaZ;
import com.amazon.alexa.jVi;
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
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_ExternalCapabilityAgentRegistrationRawData extends Ubd {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<iaZ> {
        public volatile TypeAdapter<EPu> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<ArrayList<Capability>> zQM;
        public volatile TypeAdapter<jVi> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("capabilityAgentVersion", "autoUpdate", "supportedCapabilities");
            this.jiA = gson;
            this.zyO = Util.renameFields(Ubd.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, iaZ iaz) throws IOException {
            if (iaz == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("capabilityAgentVersion"));
            Ubd ubd = (Ubd) iaz;
            if (ubd.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<jVi> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(jVi.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ubd.zZm);
            }
            jsonWriter.name(this.zyO.get("autoUpdate"));
            if (ubd.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<EPu> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.jiA.getAdapter(EPu.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, ubd.BIo);
            }
            jsonWriter.name(this.zyO.get("supportedCapabilities"));
            if (ubd.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ArrayList<Capability>> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(TypeToken.getParameterized(ArrayList.class, Capability.class));
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, ubd.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public iaZ mo8353read(JsonReader jsonReader) throws IOException {
            jVi jvi = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            EPu ePu = null;
            ArrayList<Capability> arrayList = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("capabilityAgentVersion").equals(nextName)) {
                        TypeAdapter<jVi> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(jVi.class);
                            this.zZm = typeAdapter;
                        }
                        jvi = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("autoUpdate").equals(nextName)) {
                        TypeAdapter<EPu> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(EPu.class);
                            this.BIo = typeAdapter2;
                        }
                        ePu = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zyO.get("supportedCapabilities").equals(nextName)) {
                        TypeAdapter<ArrayList<Capability>> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(TypeToken.getParameterized(ArrayList.class, Capability.class));
                            this.zQM = typeAdapter3;
                        }
                        arrayList = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ExternalCapabilityAgentRegistrationRawData(jvi, ePu, arrayList);
        }
    }

    public AutoValue_ExternalCapabilityAgentRegistrationRawData(jVi jvi, EPu ePu, ArrayList<Capability> arrayList) {
        super(jvi, ePu, arrayList);
    }
}
