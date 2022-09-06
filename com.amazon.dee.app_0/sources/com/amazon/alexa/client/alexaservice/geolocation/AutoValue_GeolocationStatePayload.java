package com.amazon.alexa.client.alexaservice.geolocation;

import androidx.annotation.Nullable;
import com.amazon.alexa.Gkq;
import com.amazon.alexa.MQV;
import com.amazon.alexa.Suv;
import com.amazon.alexa.Xdr;
import com.amazon.alexa.hYy;
import com.amazon.alexa.rfd;
import com.amazon.alexa.wze;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_GeolocationStatePayload extends MQV {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Suv> {
        public volatile TypeAdapter<Date> BIo;
        public final Map<String, String> JTe;
        public final Gson LPk;
        public volatile TypeAdapter<rfd> Qle;
        public volatile TypeAdapter<wze> jiA;
        public volatile TypeAdapter<hYy> zQM;
        public volatile TypeAdapter<Xdr> zZm;
        public volatile TypeAdapter<Gkq> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("locationServices", "timestamp", "coordinate", "altitude", "heading");
            outline129.add("speed");
            this.LPk = gson;
            this.JTe = Util.renameFields(MQV.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Suv suv) throws IOException {
            if (suv == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.JTe.get("locationServices"));
            MQV mqv = (MQV) suv;
            if (mqv.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Xdr> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.LPk.getAdapter(Xdr.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, mqv.zZm);
            }
            jsonWriter.name(this.JTe.get("timestamp"));
            if (mqv.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Date> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.LPk.getAdapter(Date.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, mqv.BIo);
            }
            jsonWriter.name(this.JTe.get("coordinate"));
            if (mqv.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<hYy> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.LPk.getAdapter(hYy.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, mqv.zQM);
            }
            jsonWriter.name(this.JTe.get("altitude"));
            if (mqv.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Gkq> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.LPk.getAdapter(Gkq.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, mqv.zyO);
            }
            jsonWriter.name(this.JTe.get("heading"));
            if (mqv.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<wze> typeAdapter5 = this.jiA;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.LPk.getAdapter(wze.class);
                    this.jiA = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, mqv.jiA);
            }
            jsonWriter.name(this.JTe.get("speed"));
            if (mqv.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<rfd> typeAdapter6 = this.Qle;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.LPk.getAdapter(rfd.class);
                    this.Qle = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, mqv.Qle);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Suv mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Xdr xdr = null;
            Date date = null;
            hYy hyy = null;
            Gkq gkq = null;
            wze wzeVar = null;
            rfd rfdVar = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.JTe.get("locationServices").equals(nextName)) {
                        TypeAdapter<Xdr> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.LPk.getAdapter(Xdr.class);
                            this.zZm = typeAdapter;
                        }
                        xdr = typeAdapter.mo8353read(jsonReader);
                    } else if (this.JTe.get("timestamp").equals(nextName)) {
                        TypeAdapter<Date> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.LPk.getAdapter(Date.class);
                            this.BIo = typeAdapter2;
                        }
                        date = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.JTe.get("coordinate").equals(nextName)) {
                        TypeAdapter<hYy> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.LPk.getAdapter(hYy.class);
                            this.zQM = typeAdapter3;
                        }
                        hyy = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.JTe.get("altitude").equals(nextName)) {
                        TypeAdapter<Gkq> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.LPk.getAdapter(Gkq.class);
                            this.zyO = typeAdapter4;
                        }
                        gkq = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.JTe.get("heading").equals(nextName)) {
                        TypeAdapter<wze> typeAdapter5 = this.jiA;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.LPk.getAdapter(wze.class);
                            this.jiA = typeAdapter5;
                        }
                        wzeVar = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.JTe.get("speed").equals(nextName)) {
                        TypeAdapter<rfd> typeAdapter6 = this.Qle;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.LPk.getAdapter(rfd.class);
                            this.Qle = typeAdapter6;
                        }
                        rfdVar = typeAdapter6.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_GeolocationStatePayload(xdr, date, hyy, gkq, wzeVar, rfdVar);
        }
    }

    public AutoValue_GeolocationStatePayload(Xdr xdr, Date date, @Nullable hYy hyy, @Nullable Gkq gkq, @Nullable wze wzeVar, @Nullable rfd rfdVar) {
        super(xdr, date, hyy, gkq, wzeVar, rfdVar);
    }
}
