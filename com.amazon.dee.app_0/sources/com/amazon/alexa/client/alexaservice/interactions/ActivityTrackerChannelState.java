package com.amazon.alexa.client.alexaservice.interactions;

import com.amazon.alexa.Aoi;
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
public abstract class ActivityTrackerChannelState {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<ActivityTrackerChannelState> {
        public final TypeAdapter<Long> BIo;
        public final TypeAdapter<dnp> zZm;

        public GsonTypeAdapter(Gson gson) {
            this.zZm = gson.getAdapter(dnp.class);
            this.BIo = gson.getAdapter(Long.class);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, ActivityTrackerChannelState activityTrackerChannelState) throws IOException {
            if (activityTrackerChannelState == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(CommsFocusConstants.INTERFACE);
            Aoi aoi = (Aoi) activityTrackerChannelState;
            this.zZm.write(jsonWriter, aoi.zZm);
            jsonWriter.name(CommsFocusConstants.IDLE_TIME_MILLISECONDS);
            this.BIo.write(jsonWriter, Long.valueOf(aoi.BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ActivityTrackerChannelState mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            dnp dnpVar = dnp.zZm;
            long j = Long.MAX_VALUE;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    char c = 65535;
                    int hashCode = nextName.hashCode();
                    if (hashCode != -54939912) {
                        if (hashCode == 502623545 && nextName.equals(CommsFocusConstants.INTERFACE)) {
                            c = 0;
                        }
                    } else if (nextName.equals(CommsFocusConstants.IDLE_TIME_MILLISECONDS)) {
                        c = 1;
                    }
                    if (c == 0) {
                        dnpVar = this.zZm.mo8353read(jsonReader);
                    } else if (c != 1) {
                        jsonReader.skipValue();
                    } else {
                        j = this.BIo.mo8353read(jsonReader).longValue();
                    }
                }
            }
            jsonReader.endObject();
            return ActivityTrackerChannelState.zZm(dnpVar, j);
        }
    }

    public static ActivityTrackerChannelState zZm(dnp dnpVar, long j) {
        return new AutoValue_ActivityTrackerChannelState(dnpVar, j);
    }
}
