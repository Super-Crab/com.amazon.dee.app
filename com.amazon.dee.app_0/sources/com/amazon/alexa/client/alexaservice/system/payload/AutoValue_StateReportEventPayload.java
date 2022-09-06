package com.amazon.alexa.client.alexaservice.system.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.keq;
import com.amazon.alexa.rff;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_StateReportEventPayload extends keq {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<rff> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<List<Message>> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "settingStateList");
            this.zQM = gson;
            this.BIo = Util.renameFields(keq.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, rff rffVar) throws IOException {
            if (rffVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("settingStateList"));
            keq keqVar = (keq) rffVar;
            if (keqVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Message>> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(List.class, Message.class));
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, keqVar.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public rff mo8353read(JsonReader jsonReader) throws IOException {
            List<Message> list = null;
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
                    if (this.BIo.get("settingStateList").equals(nextName)) {
                        TypeAdapter<List<Message>> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(List.class, Message.class));
                            this.zZm = typeAdapter;
                        }
                        list = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_StateReportEventPayload(list);
        }
    }

    public AutoValue_StateReportEventPayload(List<Message> list) {
        super(list);
    }
}
