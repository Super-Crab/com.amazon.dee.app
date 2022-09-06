package com.amazon.alexa.client.alexaservice.comms.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.HbJ;
import com.amazon.alexa.KLX;
import com.amazon.alexa.Sas;
import com.amazon.alexa.iqq;
import com.amazon.alexa.rAH;
import com.amazon.alexa.vUA;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
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
public final class AutoValue_PhoneCallControllerStatePayload extends iqq {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<KLX> {
        public volatile TypeAdapter<HbJ> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<Sas> zQM;
        public volatile TypeAdapter<List<vUA>> zZm;
        public volatile TypeAdapter<rAH> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(PCCConstants.PHONE_CALL_CONTROLLER_ALL_CALLS_KEY);
            arrayList.add(PCCConstants.PHONE_CALL_CONTROLLER_CURRENT_CALL_KEY);
            arrayList.add("device");
            arrayList.add(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
            this.Qle = gson;
            this.jiA = Util.renameFields(iqq.class, arrayList, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, KLX klx) throws IOException {
            if (klx == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get(PCCConstants.PHONE_CALL_CONTROLLER_ALL_CALLS_KEY));
            iqq iqqVar = (iqq) klx;
            if (iqqVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<vUA>> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(TypeToken.getParameterized(List.class, vUA.class));
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, iqqVar.zZm);
            }
            jsonWriter.name(this.jiA.get(PCCConstants.PHONE_CALL_CONTROLLER_CURRENT_CALL_KEY));
            if (iqqVar.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<HbJ> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Qle.getAdapter(HbJ.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, iqqVar.BIo);
            }
            jsonWriter.name(this.jiA.get("device"));
            if (iqqVar.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Sas> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Qle.getAdapter(Sas.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, iqqVar.zQM);
            }
            jsonWriter.name(this.jiA.get(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY));
            if (iqqVar.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<rAH> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Qle.getAdapter(rAH.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, iqqVar.zyO);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public KLX mo8353read(JsonReader jsonReader) throws IOException {
            List<vUA> list = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            HbJ hbJ = null;
            Sas sas = null;
            rAH rah = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get(PCCConstants.PHONE_CALL_CONTROLLER_ALL_CALLS_KEY).equals(nextName)) {
                        TypeAdapter<List<vUA>> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(TypeToken.getParameterized(List.class, vUA.class));
                            this.zZm = typeAdapter;
                        }
                        list = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get(PCCConstants.PHONE_CALL_CONTROLLER_CURRENT_CALL_KEY).equals(nextName)) {
                        TypeAdapter<HbJ> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(HbJ.class);
                            this.BIo = typeAdapter2;
                        }
                        hbJ = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.jiA.get("device").equals(nextName)) {
                        TypeAdapter<Sas> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(Sas.class);
                            this.zQM = typeAdapter3;
                        }
                        sas = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.jiA.get(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY).equals(nextName)) {
                        TypeAdapter<rAH> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(rAH.class);
                            this.zyO = typeAdapter4;
                        }
                        rah = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PhoneCallControllerStatePayload(list, hbJ, sas, rah);
        }
    }

    public AutoValue_PhoneCallControllerStatePayload(@Nullable List<vUA> list, @Nullable HbJ hbJ, Sas sas, @Nullable rAH rah) {
        super(list, hbJ, sas, rah);
    }
}
