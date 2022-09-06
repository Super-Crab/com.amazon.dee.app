package com.amazon.alexa.client.alexaservice.iocomponent;

import com.amazon.alexa.CMx;
import com.amazon.alexa.PcE;
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
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class AutoValue_SessionState extends CMx {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<PcE> {
        public volatile TypeAdapter<Long> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<String> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("sessionStartTime", "trustSessionStartTime", "longestTimeUntrustedInMilliseconds", "deviceType", "deviceSerialNumber");
            this.zyO = gson;
            this.zQM = Util.renameFields(CMx.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, PcE pcE) throws IOException {
            if (pcE == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("sessionStartTime"));
            CMx cMx = (CMx) pcE;
            if (cMx.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, cMx.zZm);
            }
            jsonWriter.name(this.zQM.get("trustSessionStartTime"));
            if (cMx.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.zZm;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, cMx.BIo);
            }
            jsonWriter.name(this.zQM.get("longestTimeUntrustedInMilliseconds"));
            TypeAdapter<Long> typeAdapter3 = this.BIo;
            if (typeAdapter3 == null) {
                typeAdapter3 = this.zyO.getAdapter(Long.class);
                this.BIo = typeAdapter3;
            }
            typeAdapter3.write(jsonWriter, Long.valueOf(cMx.zQM));
            jsonWriter.name(this.zQM.get("deviceType"));
            if (cMx.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.zZm;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, cMx.zyO);
            }
            jsonWriter.name(this.zQM.get("deviceSerialNumber"));
            if (cMx.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.zZm;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, cMx.jiA);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public PcE mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("sessionStartTime").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("trustSessionStartTime").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zQM.get("longestTimeUntrustedInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter3 = this.BIo;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.zyO.getAdapter(Long.class);
                            this.BIo = typeAdapter3;
                        }
                        j = typeAdapter3.mo8353read(jsonReader).longValue();
                    } else if (this.zQM.get("deviceType").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.zZm;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter4;
                        }
                        str3 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.zQM.get("deviceSerialNumber").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.zZm;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter5;
                        }
                        str4 = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SessionState(str, str2, j, str3, str4);
        }
    }

    public AutoValue_SessionState(String str, @Nullable String str2, long j, String str3, String str4) {
        super(str, str2, j, str3, str4);
    }
}
