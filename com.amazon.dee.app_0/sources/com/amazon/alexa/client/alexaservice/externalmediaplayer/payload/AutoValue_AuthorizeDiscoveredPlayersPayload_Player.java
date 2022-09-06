package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.FHI;
import com.amazon.alexa.Ims;
import com.amazon.alexa.ygD;
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
public final class AutoValue_AuthorizeDiscoveredPlayersPayload_Player extends ygD {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Ims.zZm> {
        public volatile TypeAdapter<Boolean> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<Ims.zZm.AbstractC0011zZm> zQM;
        public volatile TypeAdapter<FHI> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("localPlayerId", "authorized", "metadata");
            this.jiA = gson;
            this.zyO = Util.renameFields(ygD.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Ims.zZm zzm) throws IOException {
            if (zzm == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("localPlayerId"));
            ygD ygd = (ygD) zzm;
            if (ygd.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<FHI> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(FHI.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ygd.zZm);
            }
            jsonWriter.name(this.zyO.get("authorized"));
            TypeAdapter<Boolean> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.jiA.getAdapter(Boolean.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Boolean.valueOf(ygd.BIo));
            jsonWriter.name(this.zyO.get("metadata"));
            if (ygd.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Ims.zZm.AbstractC0011zZm> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(Ims.zZm.AbstractC0011zZm.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, ygd.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Ims.zZm mo8353read(JsonReader jsonReader) throws IOException {
            FHI fhi = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            boolean z = false;
            Ims.zZm.AbstractC0011zZm abstractC0011zZm = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("localPlayerId").equals(nextName)) {
                        TypeAdapter<FHI> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(FHI.class);
                            this.zZm = typeAdapter;
                        }
                        fhi = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("authorized").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(Boolean.class);
                            this.BIo = typeAdapter2;
                        }
                        z = typeAdapter2.mo8353read(jsonReader).booleanValue();
                    } else if (this.zyO.get("metadata").equals(nextName)) {
                        TypeAdapter<Ims.zZm.AbstractC0011zZm> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(Ims.zZm.AbstractC0011zZm.class);
                            this.zQM = typeAdapter3;
                        }
                        abstractC0011zZm = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AuthorizeDiscoveredPlayersPayload_Player(fhi, z, abstractC0011zZm);
        }
    }

    public AutoValue_AuthorizeDiscoveredPlayersPayload_Player(FHI fhi, boolean z, @Nullable Ims.zZm.AbstractC0011zZm abstractC0011zZm) {
        super(fhi, z, abstractC0011zZm);
    }
}
