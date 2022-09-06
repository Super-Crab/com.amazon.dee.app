package com.amazon.alexa.client.alexaservice.geolocation;

import androidx.annotation.Nullable;
import com.amazon.alexa.DRc;
import com.amazon.alexa.Gkq;
import com.amazon.alexa.YOR;
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
public final class AutoValue_AlexaLocation extends YOR {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DRc> {
        public volatile TypeAdapter<hYy> BIo;
        public final Gson JTe;
        public final Map<String, String> Qle;
        public volatile TypeAdapter<rfd> jiA;
        public volatile TypeAdapter<Gkq> zQM;
        public volatile TypeAdapter<Date> zZm;
        public volatile TypeAdapter<wze> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("timestamp", "coordinate", "altitude", "heading", "speed");
            this.JTe = gson;
            this.Qle = Util.renameFields(YOR.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, DRc dRc) throws IOException {
            if (dRc == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.Qle.get("timestamp"));
            YOR yor = (YOR) dRc;
            if (yor.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Date> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.JTe.getAdapter(Date.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, yor.zZm);
            }
            jsonWriter.name(this.Qle.get("coordinate"));
            if (yor.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<hYy> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.JTe.getAdapter(hYy.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, yor.BIo);
            }
            jsonWriter.name(this.Qle.get("altitude"));
            if (yor.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Gkq> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.JTe.getAdapter(Gkq.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, yor.zQM);
            }
            jsonWriter.name(this.Qle.get("heading"));
            if (yor.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<wze> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.JTe.getAdapter(wze.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, yor.zyO);
            }
            jsonWriter.name(this.Qle.get("speed"));
            if (yor.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<rfd> typeAdapter5 = this.jiA;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.JTe.getAdapter(rfd.class);
                    this.jiA = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, yor.jiA);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public DRc mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
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
                    if (this.Qle.get("timestamp").equals(nextName)) {
                        TypeAdapter<Date> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.JTe.getAdapter(Date.class);
                            this.zZm = typeAdapter;
                        }
                        date = typeAdapter.mo8353read(jsonReader);
                    } else if (this.Qle.get("coordinate").equals(nextName)) {
                        TypeAdapter<hYy> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.JTe.getAdapter(hYy.class);
                            this.BIo = typeAdapter2;
                        }
                        hyy = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.Qle.get("altitude").equals(nextName)) {
                        TypeAdapter<Gkq> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.JTe.getAdapter(Gkq.class);
                            this.zQM = typeAdapter3;
                        }
                        gkq = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.Qle.get("heading").equals(nextName)) {
                        TypeAdapter<wze> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.JTe.getAdapter(wze.class);
                            this.zyO = typeAdapter4;
                        }
                        wzeVar = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.Qle.get("speed").equals(nextName)) {
                        TypeAdapter<rfd> typeAdapter5 = this.jiA;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.JTe.getAdapter(rfd.class);
                            this.jiA = typeAdapter5;
                        }
                        rfdVar = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AlexaLocation(date, hyy, gkq, wzeVar, rfdVar);
        }
    }

    public AutoValue_AlexaLocation(Date date, hYy hyy, @Nullable Gkq gkq, @Nullable wze wzeVar, @Nullable rfd rfdVar) {
        super(date, hyy, gkq, wzeVar, rfdVar);
    }
}
