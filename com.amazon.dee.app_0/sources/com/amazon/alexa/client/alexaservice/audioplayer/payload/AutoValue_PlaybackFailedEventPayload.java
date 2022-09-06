package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.AbstractC0181Rbt;
import com.amazon.alexa.Puy;
import com.amazon.alexa.Vma;
import com.amazon.alexa.hFk;
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
public final class AutoValue_PlaybackFailedEventPayload extends AbstractC0181Rbt {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<hFk> {
        public volatile TypeAdapter<Vma> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<hFk.BIo> zQM;
        public volatile TypeAdapter<Puy> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("token", "currentPlaybackState", "error");
            this.jiA = gson;
            this.zyO = Util.renameFields(AbstractC0181Rbt.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, hFk hfk) throws IOException {
            if (hfk == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get("token"));
            AbstractC0181Rbt abstractC0181Rbt = (AbstractC0181Rbt) hfk;
            if (abstractC0181Rbt.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Puy> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(Puy.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, abstractC0181Rbt.zZm);
            }
            jsonWriter.name(this.zyO.get("currentPlaybackState"));
            if (abstractC0181Rbt.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Vma> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.jiA.getAdapter(Vma.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, abstractC0181Rbt.BIo);
            }
            jsonWriter.name(this.zyO.get("error"));
            if (abstractC0181Rbt.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<hFk.BIo> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(hFk.BIo.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, abstractC0181Rbt.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public hFk mo8353read(JsonReader jsonReader) throws IOException {
            Puy puy = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Vma vma = null;
            hFk.BIo bIo = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get("token").equals(nextName)) {
                        TypeAdapter<Puy> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(Puy.class);
                            this.zZm = typeAdapter;
                        }
                        puy = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("currentPlaybackState").equals(nextName)) {
                        TypeAdapter<Vma> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(Vma.class);
                            this.BIo = typeAdapter2;
                        }
                        vma = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zyO.get("error").equals(nextName)) {
                        TypeAdapter<hFk.BIo> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(hFk.BIo.class);
                            this.zQM = typeAdapter3;
                        }
                        bIo = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlaybackFailedEventPayload(puy, vma, bIo);
        }
    }

    public AutoValue_PlaybackFailedEventPayload(@Nullable Puy puy, @Nullable Vma vma, @Nullable hFk.BIo bIo) {
        super(puy, vma, bIo);
    }
}
