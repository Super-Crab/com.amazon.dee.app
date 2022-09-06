package com.amazon.alexa.client.alexaservice.speechsynthesizer.payload;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.C0176Pce;
import com.amazon.alexa.DEe;
import com.amazon.alexa.UqQ;
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
public final class AutoValue_SpeakPayload extends UqQ {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DEe> {
        public volatile TypeAdapter<C0176Pce> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<DEe.zZm> zQM;
        public volatile TypeAdapter<Uri> zZm;
        public volatile TypeAdapter<String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("url", "token", "format", "caption", "ssml");
            this.Qle = gson;
            this.jiA = Util.renameFields(UqQ.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, DEe dEe) throws IOException {
            if (dEe == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get("url"));
            UqQ uqQ = (UqQ) dEe;
            if (uqQ.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Uri> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(Uri.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, uqQ.zZm);
            }
            jsonWriter.name(this.jiA.get("token"));
            if (uqQ.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<C0176Pce> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Qle.getAdapter(C0176Pce.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, uqQ.BIo);
            }
            jsonWriter.name(this.jiA.get("format"));
            if (uqQ.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<DEe.zZm> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Qle.getAdapter(DEe.zZm.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, uqQ.zQM);
            }
            jsonWriter.name(this.jiA.get("caption"));
            if (uqQ.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Qle.getAdapter(String.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, uqQ.zyO);
            }
            jsonWriter.name(this.jiA.get("ssml"));
            if (uqQ.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.zyO;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.Qle.getAdapter(String.class);
                    this.zyO = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, uqQ.jiA);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public DEe mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Uri uri = null;
            C0176Pce c0176Pce = null;
            DEe.zZm zzm = null;
            String str = null;
            String str2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get("url").equals(nextName)) {
                        TypeAdapter<Uri> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(Uri.class);
                            this.zZm = typeAdapter;
                        }
                        uri = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get("token").equals(nextName)) {
                        TypeAdapter<C0176Pce> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(C0176Pce.class);
                            this.BIo = typeAdapter2;
                        }
                        c0176Pce = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.jiA.get("format").equals(nextName)) {
                        TypeAdapter<DEe.zZm> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(DEe.zZm.class);
                            this.zQM = typeAdapter3;
                        }
                        zzm = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.jiA.get("caption").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(String.class);
                            this.zyO = typeAdapter4;
                        }
                        str = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.jiA.get("ssml").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.zyO;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.Qle.getAdapter(String.class);
                            this.zyO = typeAdapter5;
                        }
                        str2 = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SpeakPayload(uri, c0176Pce, zzm, str, str2);
        }
    }

    public AutoValue_SpeakPayload(Uri uri, C0176Pce c0176Pce, DEe.zZm zzm, @Nullable String str, @Nullable String str2) {
        super(uri, c0176Pce, zzm, str, str2);
    }
}
