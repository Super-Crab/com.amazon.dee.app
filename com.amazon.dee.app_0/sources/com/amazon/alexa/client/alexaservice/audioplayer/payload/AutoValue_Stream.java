package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.CiJ;
import com.amazon.alexa.Puy;
import com.amazon.alexa.fcj;
import com.amazon.alexa.oFL;
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
public final class AutoValue_Stream extends oFL {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<fcj> {
        public volatile TypeAdapter<Long> BIo;
        public volatile TypeAdapter<fcj.zZm> JTe;
        public final Map<String, String> LPk;
        public volatile TypeAdapter<CiJ> Qle;
        public volatile TypeAdapter<fcj.BIo> jiA;
        public final Gson yPL;
        public volatile TypeAdapter<Puy> zQM;
        public volatile TypeAdapter<Uri> zZm;
        public volatile TypeAdapter<Date> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("url", "offsetInMilliseconds", "token", "expiryTime", "streamFormat");
            outline129.add("expectedPreviousToken");
            outline129.add("progressReport");
            outline129.add("interruptedBehavior");
            this.yPL = gson;
            this.LPk = Util.renameFields(oFL.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, fcj fcjVar) throws IOException {
            if (fcjVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.LPk.get("url"));
            oFL ofl = (oFL) fcjVar;
            if (ofl.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Uri> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.yPL.getAdapter(Uri.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ofl.zZm);
            }
            jsonWriter.name(this.LPk.get("offsetInMilliseconds"));
            TypeAdapter<Long> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.yPL.getAdapter(Long.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(ofl.BIo));
            jsonWriter.name(this.LPk.get("token"));
            if (ofl.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Puy> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.yPL.getAdapter(Puy.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, ofl.zQM);
            }
            jsonWriter.name(this.LPk.get("expiryTime"));
            if (ofl.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Date> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.yPL.getAdapter(Date.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, ofl.zyO);
            }
            jsonWriter.name(this.LPk.get("streamFormat"));
            if (ofl.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<fcj.BIo> typeAdapter5 = this.jiA;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.yPL.getAdapter(fcj.BIo.class);
                    this.jiA = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, ofl.jiA);
            }
            jsonWriter.name(this.LPk.get("expectedPreviousToken"));
            if (ofl.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Puy> typeAdapter6 = this.zQM;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.yPL.getAdapter(Puy.class);
                    this.zQM = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, ofl.Qle);
            }
            jsonWriter.name(this.LPk.get("progressReport"));
            if (ofl.JTe == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<CiJ> typeAdapter7 = this.Qle;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.yPL.getAdapter(CiJ.class);
                    this.Qle = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, ofl.JTe);
            }
            jsonWriter.name(this.LPk.get("interruptedBehavior"));
            if (ofl.LPk == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<fcj.zZm> typeAdapter8 = this.JTe;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.yPL.getAdapter(fcj.zZm.class);
                    this.JTe = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, ofl.LPk);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public fcj mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            Uri uri = null;
            Puy puy = null;
            Date date = null;
            fcj.BIo bIo = null;
            Puy puy2 = null;
            CiJ ciJ = null;
            fcj.zZm zzm = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.LPk.get("url").equals(nextName)) {
                        TypeAdapter<Uri> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.yPL.getAdapter(Uri.class);
                            this.zZm = typeAdapter;
                        }
                        uri = typeAdapter.mo8353read(jsonReader);
                    } else if (this.LPk.get("offsetInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.yPL.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        j = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else if (this.LPk.get("token").equals(nextName)) {
                        TypeAdapter<Puy> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.yPL.getAdapter(Puy.class);
                            this.zQM = typeAdapter3;
                        }
                        puy = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.LPk.get("expiryTime").equals(nextName)) {
                        TypeAdapter<Date> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.yPL.getAdapter(Date.class);
                            this.zyO = typeAdapter4;
                        }
                        date = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.LPk.get("streamFormat").equals(nextName)) {
                        TypeAdapter<fcj.BIo> typeAdapter5 = this.jiA;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.yPL.getAdapter(fcj.BIo.class);
                            this.jiA = typeAdapter5;
                        }
                        bIo = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.LPk.get("expectedPreviousToken").equals(nextName)) {
                        TypeAdapter<Puy> typeAdapter6 = this.zQM;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.yPL.getAdapter(Puy.class);
                            this.zQM = typeAdapter6;
                        }
                        puy2 = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.LPk.get("progressReport").equals(nextName)) {
                        TypeAdapter<CiJ> typeAdapter7 = this.Qle;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.yPL.getAdapter(CiJ.class);
                            this.Qle = typeAdapter7;
                        }
                        ciJ = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.LPk.get("interruptedBehavior").equals(nextName)) {
                        TypeAdapter<fcj.zZm> typeAdapter8 = this.JTe;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.yPL.getAdapter(fcj.zZm.class);
                            this.JTe = typeAdapter8;
                        }
                        zzm = typeAdapter8.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Stream(uri, j, puy, date, bIo, puy2, ciJ, zzm);
        }
    }

    public AutoValue_Stream(Uri uri, long j, Puy puy, @Nullable Date date, @Nullable fcj.BIo bIo, @Nullable Puy puy2, @Nullable CiJ ciJ, @Nullable fcj.zZm zzm) {
        super(uri, j, puy, date, bIo, puy2, ciJ, zzm);
    }
}
