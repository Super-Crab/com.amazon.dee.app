package com.amazon.alexa.client.alexaservice.externalmediaplayer;

import androidx.annotation.Nullable;
import com.amazon.alexa.AbstractC0197ddD;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.WlR;
import com.amazon.alexa.mRm;
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
public final class AutoValue_MediaStructure extends mRm {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<AbstractC0197ddD> {
        public volatile TypeAdapter<WlR> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<String> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "type", (Object) "value");
            this.zyO = gson;
            this.zQM = Util.renameFields(mRm.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, AbstractC0197ddD abstractC0197ddD) throws IOException {
            if (abstractC0197ddD == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("type"));
            mRm mrm = (mRm) abstractC0197ddD;
            if (mrm.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, mrm.BIo);
            }
            jsonWriter.name(this.zQM.get("value"));
            if (mrm.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<WlR> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(WlR.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, mrm.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AbstractC0197ddD mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            WlR wlR = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("type").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("value").equals(nextName)) {
                        TypeAdapter<WlR> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(WlR.class);
                            this.BIo = typeAdapter2;
                        }
                        wlR = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_MediaStructure(str, wlR);
        }
    }

    public AutoValue_MediaStructure(@Nullable String str, WlR wlR) {
        super(str, wlR);
    }
}
