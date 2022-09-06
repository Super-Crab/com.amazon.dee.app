package com.amazon.alexa.client.alexaservice.iocomponent.payload;

import com.amazon.alexa.BcN;
import com.amazon.alexa.PcE;
import com.amazon.alexa.VIE;
import com.amazon.alexa.pUe;
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
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class AutoValue_TrustedStatesPayload extends BcN {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<pUe> {
        public volatile TypeAdapter<String> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<VIE.zZm> zQM;
        public volatile TypeAdapter<VIE.BIo> zZm;
        public volatile TypeAdapter<List<PcE>> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("unlockState");
            arrayList.add("lastTimeInUnlockedState");
            arrayList.add("unlockMethod");
            arrayList.add("sessionStates");
            this.Qle = gson;
            this.jiA = Util.renameFields(BcN.class, arrayList, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, pUe pue) throws IOException {
            if (pue == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get("unlockState"));
            BcN bcN = (BcN) pue;
            if (bcN.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<VIE.BIo> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(VIE.BIo.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, bcN.zZm);
            }
            jsonWriter.name(this.jiA.get("lastTimeInUnlockedState"));
            if (bcN.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Qle.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, bcN.BIo);
            }
            jsonWriter.name(this.jiA.get("unlockMethod"));
            if (bcN.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<VIE.zZm> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Qle.getAdapter(VIE.zZm.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, bcN.zQM);
            }
            jsonWriter.name(this.jiA.get("sessionStates"));
            if (bcN.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<PcE>> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Qle.getAdapter(TypeToken.getParameterized(List.class, PcE.class));
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, bcN.zyO);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public pUe mo8353read(JsonReader jsonReader) throws IOException {
            VIE.BIo bIo = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            VIE.zZm zzm = null;
            List<PcE> list = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get("unlockState").equals(nextName)) {
                        TypeAdapter<VIE.BIo> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(VIE.BIo.class);
                            this.zZm = typeAdapter;
                        }
                        bIo = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get("lastTimeInUnlockedState").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(String.class);
                            this.BIo = typeAdapter2;
                        }
                        str = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.jiA.get("unlockMethod").equals(nextName)) {
                        TypeAdapter<VIE.zZm> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(VIE.zZm.class);
                            this.zQM = typeAdapter3;
                        }
                        zzm = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.jiA.get("sessionStates").equals(nextName)) {
                        TypeAdapter<List<PcE>> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(TypeToken.getParameterized(List.class, PcE.class));
                            this.zyO = typeAdapter4;
                        }
                        list = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_TrustedStatesPayload(bIo, str, zzm, list);
        }
    }

    public AutoValue_TrustedStatesPayload(VIE.BIo bIo, @Nullable String str, @Nullable VIE.zZm zzm, List<PcE> list) {
        super(bIo, str, zzm, list);
    }
}
