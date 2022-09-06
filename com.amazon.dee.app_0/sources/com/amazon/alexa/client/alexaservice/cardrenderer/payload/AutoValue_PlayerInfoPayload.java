package com.amazon.alexa.client.alexaservice.cardrenderer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.qgo;
import com.amazon.alexa.xNT;
import com.amazon.alexa.zXp;
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
public final class AutoValue_PlayerInfoPayload extends zXp {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<qgo> {
        public volatile TypeAdapter<qgo.zZm> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<qgo.zQM> zQM;
        public volatile TypeAdapter<xNT> zZm;
        public volatile TypeAdapter<qgo.BIo> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("mediaId");
            arrayList.add(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT);
            arrayList.add("template");
            arrayList.add("progress");
            this.Qle = gson;
            this.jiA = Util.renameFields(zXp.class, arrayList, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, qgo qgoVar) throws IOException {
            if (qgoVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get("mediaId"));
            zXp zxp = (zXp) qgoVar;
            if (zxp.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<xNT> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(xNT.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, zxp.zZm);
            }
            jsonWriter.name(this.jiA.get(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT));
            if (zxp.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<qgo.zZm> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Qle.getAdapter(qgo.zZm.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, zxp.BIo);
            }
            jsonWriter.name(this.jiA.get("template"));
            if (zxp.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<qgo.zQM> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Qle.getAdapter(qgo.zQM.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, zxp.zQM);
            }
            jsonWriter.name(this.jiA.get("progress"));
            if (zxp.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<qgo.BIo> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Qle.getAdapter(qgo.BIo.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, zxp.zyO);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public qgo mo8353read(JsonReader jsonReader) throws IOException {
            xNT xnt = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            qgo.zZm zzm = null;
            qgo.zQM zqm = null;
            qgo.BIo bIo = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get("mediaId").equals(nextName)) {
                        TypeAdapter<xNT> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(xNT.class);
                            this.zZm = typeAdapter;
                        }
                        xnt = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT).equals(nextName)) {
                        TypeAdapter<qgo.zZm> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(qgo.zZm.class);
                            this.BIo = typeAdapter2;
                        }
                        zzm = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.jiA.get("template").equals(nextName)) {
                        TypeAdapter<qgo.zQM> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(qgo.zQM.class);
                            this.zQM = typeAdapter3;
                        }
                        zqm = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.jiA.get("progress").equals(nextName)) {
                        TypeAdapter<qgo.BIo> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(qgo.BIo.class);
                            this.zyO = typeAdapter4;
                        }
                        bIo = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayerInfoPayload(xnt, zzm, zqm, bIo);
        }
    }

    public AutoValue_PlayerInfoPayload(xNT xnt, @Nullable qgo.zZm zzm, @Nullable qgo.zQM zqm, @Nullable qgo.BIo bIo) {
        super(xnt, zzm, zqm, bIo);
    }
}
