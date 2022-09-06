package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.AkY;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Ims;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public final class AutoValue_AuthorizeDiscoveredPlayersPayload extends AkY {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Ims> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Set<Ims.zZm>> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "players");
            this.zQM = gson;
            this.BIo = Util.renameFields(AkY.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Ims ims) throws IOException {
            if (ims == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("players"));
            AkY akY = (AkY) ims;
            if (akY.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<Ims.zZm>> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(Set.class, Ims.zZm.class));
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, akY.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Ims mo8353read(JsonReader jsonReader) throws IOException {
            Set<Ims.zZm> set = null;
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
                    if (this.BIo.get("players").equals(nextName)) {
                        TypeAdapter<Set<Ims.zZm>> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(Set.class, Ims.zZm.class));
                            this.zZm = typeAdapter;
                        }
                        set = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AuthorizeDiscoveredPlayersPayload(set);
        }
    }

    public AutoValue_AuthorizeDiscoveredPlayersPayload(Set<Ims.zZm> set) {
        super(set);
    }
}
