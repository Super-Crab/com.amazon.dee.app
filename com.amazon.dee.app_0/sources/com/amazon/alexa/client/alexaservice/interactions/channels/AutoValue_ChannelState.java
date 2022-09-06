package com.amazon.alexa.client.alexaservice.interactions.channels;

import com.amazon.alexa.aNh;
import com.amazon.alexa.dnp;
import com.amazon.alexa.hgr;
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
public final class AutoValue_ChannelState extends hgr {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<aNh> {
        public volatile TypeAdapter<Long> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<Boolean> zQM;
        public volatile TypeAdapter<dnp> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("latestInterfaceName", "idleStartTimeSystemMilliseconds", "isActive");
            this.jiA = gson;
            this.zyO = Util.renameFields(hgr.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, aNh anh) throws IOException {
            if (anh == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("latestInterfaceName"));
            hgr hgrVar = (hgr) anh;
            if (hgrVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<dnp> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(dnp.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, hgrVar.zZm);
            }
            jsonWriter.name(this.zyO.get("idleStartTimeSystemMilliseconds"));
            TypeAdapter<Long> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.jiA.getAdapter(Long.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(hgrVar.BIo));
            jsonWriter.name(this.zyO.get("isActive"));
            TypeAdapter<Boolean> typeAdapter3 = this.zQM;
            if (typeAdapter3 == null) {
                typeAdapter3 = this.jiA.getAdapter(Boolean.class);
                this.zQM = typeAdapter3;
            }
            typeAdapter3.write(jsonWriter, Boolean.valueOf(hgrVar.zQM));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public aNh mo8353read(JsonReader jsonReader) throws IOException {
            dnp dnpVar = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            boolean z = false;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("latestInterfaceName").equals(nextName)) {
                        TypeAdapter<dnp> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(dnp.class);
                            this.zZm = typeAdapter;
                        }
                        dnpVar = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("idleStartTimeSystemMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        j = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else if (this.zyO.get("isActive").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(Boolean.class);
                            this.zQM = typeAdapter3;
                        }
                        z = typeAdapter3.mo8353read(jsonReader).booleanValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ChannelState(dnpVar, j, z);
        }
    }

    public AutoValue_ChannelState(dnp dnpVar, long j, boolean z) {
        super(dnpVar, j, z);
    }
}
