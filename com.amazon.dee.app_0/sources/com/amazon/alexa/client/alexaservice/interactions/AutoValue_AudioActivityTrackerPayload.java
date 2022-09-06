package com.amazon.alexa.client.alexaservice.interactions;

import androidx.annotation.Nullable;
import com.amazon.alexa.ezo;
import com.amazon.alexa.plk;
import com.amazon.deecomms.calling.incallcommands.constants.CommsFocusConstants;
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
public final class AutoValue_AudioActivityTrackerPayload extends ezo {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<plk> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<ActivityTrackerChannelState> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("dialog");
            arrayList.add(CommsFocusConstants.COMMUNICATIONS);
            arrayList.add("alert");
            arrayList.add("content");
            this.zQM = gson;
            this.BIo = Util.renameFields(ezo.class, arrayList, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, plk plkVar) throws IOException {
            if (plkVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("dialog"));
            ezo ezoVar = (ezo) plkVar;
            if (ezoVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ActivityTrackerChannelState> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(ActivityTrackerChannelState.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ezoVar.zZm);
            }
            jsonWriter.name(this.BIo.get(CommsFocusConstants.COMMUNICATIONS));
            if (ezoVar.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ActivityTrackerChannelState> typeAdapter2 = this.zZm;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zQM.getAdapter(ActivityTrackerChannelState.class);
                    this.zZm = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, ezoVar.BIo);
            }
            jsonWriter.name(this.BIo.get("alert"));
            if (ezoVar.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ActivityTrackerChannelState> typeAdapter3 = this.zZm;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.zQM.getAdapter(ActivityTrackerChannelState.class);
                    this.zZm = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, ezoVar.zQM);
            }
            jsonWriter.name(this.BIo.get("content"));
            if (ezoVar.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ActivityTrackerChannelState> typeAdapter4 = this.zZm;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.zQM.getAdapter(ActivityTrackerChannelState.class);
                    this.zZm = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, ezoVar.zyO);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public plk mo8353read(JsonReader jsonReader) throws IOException {
            ActivityTrackerChannelState activityTrackerChannelState = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            ActivityTrackerChannelState activityTrackerChannelState2 = null;
            ActivityTrackerChannelState activityTrackerChannelState3 = null;
            ActivityTrackerChannelState activityTrackerChannelState4 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("dialog").equals(nextName)) {
                        TypeAdapter<ActivityTrackerChannelState> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(ActivityTrackerChannelState.class);
                            this.zZm = typeAdapter;
                        }
                        activityTrackerChannelState = typeAdapter.mo8353read(jsonReader);
                    } else if (this.BIo.get(CommsFocusConstants.COMMUNICATIONS).equals(nextName)) {
                        TypeAdapter<ActivityTrackerChannelState> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zQM.getAdapter(ActivityTrackerChannelState.class);
                            this.zZm = typeAdapter2;
                        }
                        activityTrackerChannelState2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.BIo.get("alert").equals(nextName)) {
                        TypeAdapter<ActivityTrackerChannelState> typeAdapter3 = this.zZm;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.zQM.getAdapter(ActivityTrackerChannelState.class);
                            this.zZm = typeAdapter3;
                        }
                        activityTrackerChannelState3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.BIo.get("content").equals(nextName)) {
                        TypeAdapter<ActivityTrackerChannelState> typeAdapter4 = this.zZm;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.zQM.getAdapter(ActivityTrackerChannelState.class);
                            this.zZm = typeAdapter4;
                        }
                        activityTrackerChannelState4 = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AudioActivityTrackerPayload(activityTrackerChannelState, activityTrackerChannelState2, activityTrackerChannelState3, activityTrackerChannelState4);
        }
    }

    public AutoValue_AudioActivityTrackerPayload(@Nullable ActivityTrackerChannelState activityTrackerChannelState, @Nullable ActivityTrackerChannelState activityTrackerChannelState2, @Nullable ActivityTrackerChannelState activityTrackerChannelState3, @Nullable ActivityTrackerChannelState activityTrackerChannelState4) {
        super(activityTrackerChannelState, activityTrackerChannelState2, activityTrackerChannelState3, activityTrackerChannelState4);
    }
}
