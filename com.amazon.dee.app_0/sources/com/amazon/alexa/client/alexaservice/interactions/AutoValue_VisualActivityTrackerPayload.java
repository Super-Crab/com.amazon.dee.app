package com.amazon.alexa.client.alexaservice.interactions;

import androidx.annotation.Nullable;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Gzu;
import com.amazon.alexa.aQE;
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
public final class AutoValue_VisualActivityTrackerPayload extends aQE {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Gzu> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<VisualActivityTrackerChannelState> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "focused");
            this.zQM = gson;
            this.BIo = Util.renameFields(aQE.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Gzu gzu) throws IOException {
            if (gzu == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("focused"));
            aQE aqe = (aQE) gzu;
            if (aqe.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<VisualActivityTrackerChannelState> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(VisualActivityTrackerChannelState.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, aqe.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Gzu mo8353read(JsonReader jsonReader) throws IOException {
            VisualActivityTrackerChannelState visualActivityTrackerChannelState = null;
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
                    if (this.BIo.get("focused").equals(nextName)) {
                        TypeAdapter<VisualActivityTrackerChannelState> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(VisualActivityTrackerChannelState.class);
                            this.zZm = typeAdapter;
                        }
                        visualActivityTrackerChannelState = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_VisualActivityTrackerPayload(visualActivityTrackerChannelState);
        }
    }

    public AutoValue_VisualActivityTrackerPayload(@Nullable VisualActivityTrackerChannelState visualActivityTrackerChannelState) {
        super(visualActivityTrackerChannelState);
    }
}
