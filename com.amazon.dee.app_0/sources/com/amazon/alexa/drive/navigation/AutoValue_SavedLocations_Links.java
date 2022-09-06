package com.amazon.alexa.drive.navigation;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.SavedLocations;
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
/* loaded from: classes7.dex */
public final class AutoValue_SavedLocations_Links extends C$AutoValue_SavedLocations_Links {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SavedLocations.Links> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("next");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SavedLocations_Links.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SavedLocations.Links mo8353read(JsonReader jsonReader) throws IOException {
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
                    if (this.realFieldNames.get("next").equals(nextName)) {
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
            return new AutoValue_SavedLocations_Links(str);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SavedLocations.Links links) throws IOException {
            if (links == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("next"));
            if (links.getNext() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, links.getNext());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SavedLocations_Links(@Nullable final String str) {
        new SavedLocations.Links(str) { // from class: com.amazon.alexa.drive.navigation.$AutoValue_SavedLocations_Links
            private final String next;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.next = str;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SavedLocations.Links)) {
                    return false;
                }
                String str2 = this.next;
                String next = ((SavedLocations.Links) obj).getNext();
                return str2 == null ? next == null : str2.equals(next);
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Links
            @Nullable
            public String getNext() {
                return this.next;
            }

            public int hashCode() {
                String str2 = this.next;
                return (str2 == null ? 0 : str2.hashCode()) ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Links{next="), this.next, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
