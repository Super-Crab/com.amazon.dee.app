package com.amazon.alexa.sendtoapp.activitycard.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.sendtoapp.activitycard.model.P13NMetadata;
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
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class AutoValue_P13NMetadata extends C$AutoValue_P13NMetadata {

    /* loaded from: classes10.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<P13NMetadata> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126(EntertainmentConstants.TYPE_SECTION);
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_P13NMetadata.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public P13NMetadata mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
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
                    if (this.realFieldNames.get(EntertainmentConstants.TYPE_SECTION).equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_P13NMetadata(str);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, P13NMetadata p13NMetadata) throws IOException {
            if (p13NMetadata == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get(EntertainmentConstants.TYPE_SECTION));
            if (p13NMetadata.getSection() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, p13NMetadata.getSection());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_P13NMetadata(final String str) {
        new P13NMetadata(str) { // from class: com.amazon.alexa.sendtoapp.activitycard.model.$AutoValue_P13NMetadata
            private final String section;

            /* renamed from: com.amazon.alexa.sendtoapp.activitycard.model.$AutoValue_P13NMetadata$Builder */
            /* loaded from: classes10.dex */
            static final class Builder extends P13NMetadata.Builder {
                private String section;

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.P13NMetadata.Builder
                public P13NMetadata build() {
                    String str = "";
                    if (this.section == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " section");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_P13NMetadata(this.section);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.P13NMetadata.Builder
                public P13NMetadata.Builder setSection(String str) {
                    if (str != null) {
                        this.section = str;
                        return this;
                    }
                    throw new NullPointerException("Null section");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.section = str;
                    return;
                }
                throw new NullPointerException("Null section");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof P13NMetadata)) {
                    return false;
                }
                return this.section.equals(((P13NMetadata) obj).getSection());
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.P13NMetadata
            public String getSection() {
                return this.section;
            }

            public int hashCode() {
                return this.section.hashCode() ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("P13NMetadata{section="), this.section, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
