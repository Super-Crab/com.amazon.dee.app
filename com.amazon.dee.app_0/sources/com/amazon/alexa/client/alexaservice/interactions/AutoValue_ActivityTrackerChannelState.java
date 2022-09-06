package com.amazon.alexa.client.alexaservice.interactions;

import com.amazon.alexa.Aoi;
import com.amazon.alexa.dnp;
import com.amazon.deecomms.calling.incallcommands.constants.CommsFocusConstants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_ActivityTrackerChannelState extends Aoi {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<ActivityTrackerChannelState> {
        public volatile TypeAdapter<Long> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<dnp> zZm;
        public final Gson zyO;

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, ActivityTrackerChannelState activityTrackerChannelState) throws IOException {
            if (activityTrackerChannelState == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("interfaceName"));
            Aoi aoi = (Aoi) activityTrackerChannelState;
            if (aoi.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<dnp> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(dnp.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, aoi.zZm);
            }
            jsonWriter.name(this.zQM.get(CommsFocusConstants.IDLE_TIME_MILLISECONDS));
            TypeAdapter<Long> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zyO.getAdapter(Long.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(aoi.BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ActivityTrackerChannelState mo8353read(JsonReader jsonReader) throws IOException {
            dnp dnpVar = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("interfaceName").equals(nextName)) {
                        TypeAdapter<dnp> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(dnp.class);
                            this.zZm = typeAdapter;
                        }
                        dnpVar = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get(CommsFocusConstants.IDLE_TIME_MILLISECONDS).equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        j = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ActivityTrackerChannelState(dnpVar, j);
        }
    }

    public AutoValue_ActivityTrackerChannelState(dnp dnpVar, long j) {
        super(dnpVar, j);
    }
}
