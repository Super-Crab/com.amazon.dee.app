package com.amazon.alexa.client.alexaservice.iocomponent;

import com.amazon.alexa.KIc;
import com.amazon.alexa.URU;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
public final class AutoValue_IOComponentContext extends KIc {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<URU> {
        public volatile TypeAdapter<Name> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<Integer> zQM;
        public volatile TypeAdapter<Namespace> zZm;
        public volatile TypeAdapter<String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("namespace", "name", "value", "timeOfSample", "uncertaintyInMilliseconds");
            this.Qle = gson;
            this.jiA = Util.renameFields(KIc.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, URU uru) throws IOException {
            if (uru == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get("namespace"));
            KIc kIc = (KIc) uru;
            if (kIc.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Namespace> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(Namespace.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, kIc.zZm);
            }
            jsonWriter.name(this.jiA.get("name"));
            if (kIc.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Name> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Qle.getAdapter(Name.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, kIc.BIo);
            }
            jsonWriter.name(this.jiA.get("value"));
            if (kIc.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Qle.getAdapter(Integer.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, kIc.zQM);
            }
            jsonWriter.name(this.jiA.get("timeOfSample"));
            if (kIc.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Qle.getAdapter(String.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, kIc.zyO);
            }
            jsonWriter.name(this.jiA.get("uncertaintyInMilliseconds"));
            if (kIc.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter5 = this.zQM;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.Qle.getAdapter(Integer.class);
                    this.zQM = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, kIc.jiA);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public URU mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Namespace namespace = null;
            Name name = null;
            Integer num = null;
            String str = null;
            Integer num2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get("namespace").equals(nextName)) {
                        TypeAdapter<Namespace> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(Namespace.class);
                            this.zZm = typeAdapter;
                        }
                        namespace = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get("name").equals(nextName)) {
                        TypeAdapter<Name> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(Name.class);
                            this.BIo = typeAdapter2;
                        }
                        name = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.jiA.get("value").equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(Integer.class);
                            this.zQM = typeAdapter3;
                        }
                        num = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.jiA.get("timeOfSample").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(String.class);
                            this.zyO = typeAdapter4;
                        }
                        str = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.jiA.get("uncertaintyInMilliseconds").equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter5 = this.zQM;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.Qle.getAdapter(Integer.class);
                            this.zQM = typeAdapter5;
                        }
                        num2 = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_IOComponentContext(namespace, name, num, str, num2);
        }
    }

    public AutoValue_IOComponentContext(Namespace namespace, Name name, Integer num, String str, Integer num2) {
        super(namespace, name, num, str, num2);
    }
}
