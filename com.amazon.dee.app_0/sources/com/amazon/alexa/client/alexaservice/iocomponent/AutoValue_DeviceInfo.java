package com.amazon.alexa.client.alexaservice.iocomponent;

import androidx.annotation.Nullable;
import com.amazon.alexa.IKe;
import com.amazon.alexa.MTM;
import com.amazon.alexa.cMY;
import com.amazon.alexa.cVW;
import com.amazon.alexa.pGm;
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
public final class AutoValue_DeviceInfo extends MTM {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<cVW> {
        public volatile TypeAdapter<pGm> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<cMY> zQM;
        public volatile TypeAdapter<IKe> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("deviceType", "deviceSerialNumber", "firmwareVersion");
            this.jiA = gson;
            this.zyO = Util.renameFields(MTM.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, cVW cvw) throws IOException {
            if (cvw == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("deviceType"));
            MTM mtm = (MTM) cvw;
            if (mtm.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<IKe> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(IKe.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, mtm.zZm);
            }
            jsonWriter.name(this.zyO.get("deviceSerialNumber"));
            if (mtm.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<pGm> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.jiA.getAdapter(pGm.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, mtm.BIo);
            }
            jsonWriter.name(this.zyO.get("firmwareVersion"));
            if (mtm.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<cMY> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(cMY.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, mtm.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public cVW mo8353read(JsonReader jsonReader) throws IOException {
            IKe iKe = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            pGm pgm = null;
            cMY cmy = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("deviceType").equals(nextName)) {
                        TypeAdapter<IKe> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(IKe.class);
                            this.zZm = typeAdapter;
                        }
                        iKe = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("deviceSerialNumber").equals(nextName)) {
                        TypeAdapter<pGm> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(pGm.class);
                            this.BIo = typeAdapter2;
                        }
                        pgm = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zyO.get("firmwareVersion").equals(nextName)) {
                        TypeAdapter<cMY> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(cMY.class);
                            this.zQM = typeAdapter3;
                        }
                        cmy = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_DeviceInfo(iKe, pgm, cmy);
        }
    }

    public AutoValue_DeviceInfo(IKe iKe, pGm pgm, @Nullable cMY cmy) {
        super(iKe, pgm, cmy);
    }
}
