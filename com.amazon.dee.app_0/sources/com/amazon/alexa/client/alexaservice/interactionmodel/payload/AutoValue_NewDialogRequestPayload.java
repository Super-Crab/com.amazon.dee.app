package com.amazon.alexa.client.alexaservice.interactionmodel.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.JTD;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.xfe;
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
public final class AutoValue_NewDialogRequestPayload extends xfe {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<JTD> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<DialogRequestIdentifier> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "dialogRequestId");
            this.zQM = gson;
            this.BIo = Util.renameFields(xfe.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, JTD jtd) throws IOException {
            if (jtd == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("dialogRequestId"));
            xfe xfeVar = (xfe) jtd;
            if (xfeVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<DialogRequestIdentifier> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(DialogRequestIdentifier.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, xfeVar.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public JTD mo8353read(JsonReader jsonReader) throws IOException {
            DialogRequestIdentifier dialogRequestIdentifier = null;
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
                    if (this.BIo.get("dialogRequestId").equals(nextName)) {
                        TypeAdapter<DialogRequestIdentifier> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(DialogRequestIdentifier.class);
                            this.zZm = typeAdapter;
                        }
                        dialogRequestIdentifier = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_NewDialogRequestPayload(dialogRequestIdentifier);
        }
    }

    public AutoValue_NewDialogRequestPayload(DialogRequestIdentifier dialogRequestIdentifier) {
        super(dialogRequestIdentifier);
    }
}
