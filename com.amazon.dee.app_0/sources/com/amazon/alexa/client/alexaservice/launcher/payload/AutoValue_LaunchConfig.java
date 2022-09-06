package com.amazon.alexa.client.alexaservice.launcher.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.GhS;
import com.amazon.alexa.tIB;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class AutoValue_LaunchConfig extends tIB {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<GhS> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Boolean> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "isMandatoryToLaunchTarget");
            this.zQM = gson;
            this.BIo = Util.renameFields(tIB.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, GhS ghS) throws IOException {
            if (ghS == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("isMandatoryToLaunchTarget"));
            tIB tib = (tIB) ghS;
            if (tib.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(Boolean.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, tib.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public GhS mo8353read(JsonReader jsonReader) throws IOException {
            Boolean bool = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("isMandatoryToLaunchTarget").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Boolean.class);
                            this.zZm = typeAdapter;
                        }
                        bool = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_LaunchConfig(bool);
        }
    }

    public AutoValue_LaunchConfig(@Nullable Boolean bool) {
        super(bool);
    }
}
