package com.amazon.alexa.client.alexaservice.componentstate;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Zte;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
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
public final class AutoValue_ComponentStateHeader extends Zte {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<ComponentStateHeader> {
        public volatile TypeAdapter<Name> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Namespace> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "namespace", (Object) "name");
            this.zyO = gson;
            this.zQM = Util.renameFields(Zte.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, ComponentStateHeader componentStateHeader) throws IOException {
            if (componentStateHeader == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("namespace"));
            if (componentStateHeader.BIo() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Namespace> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(Namespace.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, componentStateHeader.BIo());
            }
            jsonWriter.name(this.zQM.get("name"));
            if (componentStateHeader.zZm() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Name> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(Name.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, componentStateHeader.zZm());
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ComponentStateHeader mo8353read(JsonReader jsonReader) throws IOException {
            Namespace namespace = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Name name = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("namespace").equals(nextName)) {
                        TypeAdapter<Namespace> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Namespace.class);
                            this.zZm = typeAdapter;
                        }
                        namespace = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("name").equals(nextName)) {
                        TypeAdapter<Name> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(Name.class);
                            this.BIo = typeAdapter2;
                        }
                        name = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ComponentStateHeader(namespace, name);
        }
    }

    public AutoValue_ComponentStateHeader(Namespace namespace, Name name) {
        super(namespace, name);
    }
}
