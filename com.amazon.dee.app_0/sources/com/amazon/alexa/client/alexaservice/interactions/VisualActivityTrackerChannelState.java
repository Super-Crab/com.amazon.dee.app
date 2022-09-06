package com.amazon.alexa.client.alexaservice.interactions;

import com.amazon.alexa.NZn;
import com.amazon.alexa.dnp;
import com.amazon.deecomms.calling.incallcommands.constants.CommsFocusConstants;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
@AutoValue
/* loaded from: classes6.dex */
public abstract class VisualActivityTrackerChannelState {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<VisualActivityTrackerChannelState> {
        public final TypeAdapter<dnp> zZm;

        public GsonTypeAdapter(Gson gson) {
            this.zZm = gson.getAdapter(dnp.class);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, VisualActivityTrackerChannelState visualActivityTrackerChannelState) throws IOException {
            if (visualActivityTrackerChannelState == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(CommsFocusConstants.INTERFACE);
            this.zZm.write(jsonWriter, ((NZn) visualActivityTrackerChannelState).zZm);
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public VisualActivityTrackerChannelState mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            dnp dnpVar = dnp.zZm;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    char c = 65535;
                    if (nextName.hashCode() == 502623545 && nextName.equals(CommsFocusConstants.INTERFACE)) {
                        c = 0;
                    }
                    if (c != 0) {
                        jsonReader.skipValue();
                    } else {
                        dnpVar = this.zZm.mo8353read(jsonReader);
                    }
                }
            }
            jsonReader.endObject();
            return VisualActivityTrackerChannelState.zZm(dnpVar);
        }
    }

    public static VisualActivityTrackerChannelState zZm(dnp dnpVar) {
        return new AutoValue_VisualActivityTrackerChannelState(dnpVar);
    }
}
