package com.amazon.alexa.client.alexaservice.interactions;

import com.amazon.alexa.NZn;
import com.amazon.alexa.dnp;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_VisualActivityTrackerChannelState extends NZn {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<VisualActivityTrackerChannelState> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<dnp> zZm;

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, VisualActivityTrackerChannelState visualActivityTrackerChannelState) throws IOException {
            if (visualActivityTrackerChannelState == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("interfaceName"));
            NZn nZn = (NZn) visualActivityTrackerChannelState;
            if (nZn.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<dnp> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(dnp.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, nZn.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public VisualActivityTrackerChannelState mo8353read(JsonReader jsonReader) throws IOException {
            dnp dnpVar = null;
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
                    if (this.BIo.get("interfaceName").equals(nextName)) {
                        TypeAdapter<dnp> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(dnp.class);
                            this.zZm = typeAdapter;
                        }
                        dnpVar = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_VisualActivityTrackerChannelState(dnpVar);
        }
    }

    public AutoValue_VisualActivityTrackerChannelState(dnp dnpVar) {
        super(dnpVar);
    }
}
