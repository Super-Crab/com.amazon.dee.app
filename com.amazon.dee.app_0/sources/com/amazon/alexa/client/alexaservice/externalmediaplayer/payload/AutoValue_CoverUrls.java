package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import android.net.Uri;
import com.amazon.alexa.cJg;
import com.amazon.alexa.zpo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.lwansbrough.RCTCamera.RCTCameraModule;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_CoverUrls extends cJg {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<zpo> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Uri> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("tiny", "small", RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM, "large", "full");
            this.zQM = gson;
            this.BIo = Util.renameFields(cJg.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, zpo zpoVar) throws IOException {
            if (zpoVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("tiny"));
            cJg cjg = (cJg) zpoVar;
            if (cjg.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Uri> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(Uri.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, cjg.zZm);
            }
            jsonWriter.name(this.BIo.get("small"));
            if (cjg.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Uri> typeAdapter2 = this.zZm;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zQM.getAdapter(Uri.class);
                    this.zZm = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, cjg.BIo);
            }
            jsonWriter.name(this.BIo.get(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM));
            if (cjg.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Uri> typeAdapter3 = this.zZm;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.zQM.getAdapter(Uri.class);
                    this.zZm = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, cjg.zQM);
            }
            jsonWriter.name(this.BIo.get("large"));
            if (cjg.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Uri> typeAdapter4 = this.zZm;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.zQM.getAdapter(Uri.class);
                    this.zZm = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, cjg.zyO);
            }
            jsonWriter.name(this.BIo.get("full"));
            if (cjg.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Uri> typeAdapter5 = this.zZm;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.zQM.getAdapter(Uri.class);
                    this.zZm = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, cjg.jiA);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public zpo mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Uri uri = null;
            Uri uri2 = null;
            Uri uri3 = null;
            Uri uri4 = null;
            Uri uri5 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("tiny").equals(nextName)) {
                        TypeAdapter<Uri> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Uri.class);
                            this.zZm = typeAdapter;
                        }
                        uri = typeAdapter.mo8353read(jsonReader);
                    } else if (this.BIo.get("small").equals(nextName)) {
                        TypeAdapter<Uri> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zQM.getAdapter(Uri.class);
                            this.zZm = typeAdapter2;
                        }
                        uri2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.BIo.get(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM).equals(nextName)) {
                        TypeAdapter<Uri> typeAdapter3 = this.zZm;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.zQM.getAdapter(Uri.class);
                            this.zZm = typeAdapter3;
                        }
                        uri3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.BIo.get("large").equals(nextName)) {
                        TypeAdapter<Uri> typeAdapter4 = this.zZm;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.zQM.getAdapter(Uri.class);
                            this.zZm = typeAdapter4;
                        }
                        uri4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.BIo.get("full").equals(nextName)) {
                        TypeAdapter<Uri> typeAdapter5 = this.zZm;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.zQM.getAdapter(Uri.class);
                            this.zZm = typeAdapter5;
                        }
                        uri5 = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_CoverUrls(uri, uri2, uri3, uri4, uri5);
        }
    }

    public AutoValue_CoverUrls(Uri uri, Uri uri2, Uri uri3, Uri uri4, Uri uri5) {
        super(uri, uri2, uri3, uri4, uri5);
    }
}
