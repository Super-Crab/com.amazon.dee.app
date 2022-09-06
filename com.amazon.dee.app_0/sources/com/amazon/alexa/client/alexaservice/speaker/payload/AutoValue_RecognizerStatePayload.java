package com.amazon.alexa.client.alexaservice.speaker.payload;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.Bfv;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Ueh;
import com.amazon.alexa.eYr;
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
public final class AutoValue_RecognizerStatePayload extends Ueh {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Bfv> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<eYr> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) Settings.ListeningSettings.REASON_WAKEWORD);
            this.zQM = gson;
            this.BIo = Util.renameFields(Ueh.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Bfv bfv) throws IOException {
            if (bfv == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get(Settings.ListeningSettings.REASON_WAKEWORD));
            Ueh ueh = (Ueh) bfv;
            if (ueh.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<eYr> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(eYr.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ueh.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Bfv mo8353read(JsonReader jsonReader) throws IOException {
            eYr eyr = null;
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
                    if (this.BIo.get(Settings.ListeningSettings.REASON_WAKEWORD).equals(nextName)) {
                        TypeAdapter<eYr> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(eYr.class);
                            this.zZm = typeAdapter;
                        }
                        eyr = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_RecognizerStatePayload(eyr);
        }
    }

    public AutoValue_RecognizerStatePayload(eYr eyr) {
        super(eyr);
    }
}
