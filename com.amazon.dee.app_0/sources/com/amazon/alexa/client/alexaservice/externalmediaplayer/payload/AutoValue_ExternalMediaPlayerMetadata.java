package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.AbstractC0188bKf;
import com.amazon.alexa.MOI;
import com.amazon.alexa.ZYY;
import com.amazon.alexa.vKJ;
import com.amazon.alexa.zYH;
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
public final class AutoValue_ExternalMediaPlayerMetadata extends vKJ {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<MOI> {
        public volatile TypeAdapter<zYH> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<ZYY> zQM;
        public volatile TypeAdapter<AbstractC0188bKf> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("spiVersion", "playerCookie", "playerVersion");
            this.jiA = gson;
            this.zyO = Util.renameFields(vKJ.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, MOI moi) throws IOException {
            if (moi == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("spiVersion"));
            vKJ vkj = (vKJ) moi;
            if (vkj.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AbstractC0188bKf> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(AbstractC0188bKf.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, vkj.zZm);
            }
            jsonWriter.name(this.zyO.get("playerCookie"));
            if (vkj.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<zYH> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.jiA.getAdapter(zYH.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, vkj.BIo);
            }
            jsonWriter.name(this.zyO.get("playerVersion"));
            if (vkj.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ZYY> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(ZYY.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, vkj.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public MOI mo8353read(JsonReader jsonReader) throws IOException {
            AbstractC0188bKf abstractC0188bKf = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            zYH zyh = null;
            ZYY zyy = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("spiVersion").equals(nextName)) {
                        TypeAdapter<AbstractC0188bKf> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(AbstractC0188bKf.class);
                            this.zZm = typeAdapter;
                        }
                        abstractC0188bKf = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("playerCookie").equals(nextName)) {
                        TypeAdapter<zYH> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(zYH.class);
                            this.BIo = typeAdapter2;
                        }
                        zyh = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zyO.get("playerVersion").equals(nextName)) {
                        TypeAdapter<ZYY> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(ZYY.class);
                            this.zQM = typeAdapter3;
                        }
                        zyy = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ExternalMediaPlayerMetadata(abstractC0188bKf, zyh, zyy);
        }
    }

    public AutoValue_ExternalMediaPlayerMetadata(AbstractC0188bKf abstractC0188bKf, @Nullable zYH zyh, @Nullable ZYY zyy) {
        super(abstractC0188bKf, zyh, zyy);
    }
}
