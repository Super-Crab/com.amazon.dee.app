package com.amazon.alexa.client.alexaservice.iocomponent;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.IKe;
import com.amazon.alexa.pGm;
import com.amazon.alexa.ruk;
import com.amazon.alexa.vwO;
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
public final class AutoValue_ClusterDevice extends ruk {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<vwO> {
        public volatile TypeAdapter<pGm> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<IKe> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "clusterDeviceType", (Object) "clusterDeviceSerialNumber");
            this.zyO = gson;
            this.zQM = Util.renameFields(ruk.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, vwO vwo) throws IOException {
            if (vwo == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("clusterDeviceType"));
            ruk rukVar = (ruk) vwo;
            if (rukVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<IKe> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(IKe.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, rukVar.zZm);
            }
            jsonWriter.name(this.zQM.get("clusterDeviceSerialNumber"));
            if (rukVar.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<pGm> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(pGm.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, rukVar.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public vwO mo8353read(JsonReader jsonReader) throws IOException {
            IKe iKe = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            pGm pgm = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("clusterDeviceType").equals(nextName)) {
                        TypeAdapter<IKe> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(IKe.class);
                            this.zZm = typeAdapter;
                        }
                        iKe = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("clusterDeviceSerialNumber").equals(nextName)) {
                        TypeAdapter<pGm> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(pGm.class);
                            this.BIo = typeAdapter2;
                        }
                        pgm = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ClusterDevice(iKe, pgm);
        }
    }

    public AutoValue_ClusterDevice(IKe iKe, pGm pgm) {
        super(iKe, pgm);
    }
}
