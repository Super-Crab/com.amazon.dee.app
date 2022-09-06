package com.amazon.alexa.externalnotifications.capability.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
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
public final class AutoValue_Contact extends C$AutoValue_Contact {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Contact> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("id", "name");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Contact.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Contact mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("id").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("name").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Contact(str, str2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Contact contact) throws IOException {
            if (contact == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("id"));
            if (contact.getId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, contact.getId());
            }
            jsonWriter.name(this.realFieldNames.get("name"));
            if (contact.getName() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, contact.getName());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Contact(final String str, final String str2) {
        new Contact(str, str2) { // from class: com.amazon.alexa.externalnotifications.capability.models.$AutoValue_Contact
            private final String id;
            private final String name;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.id = str;
                    if (str2 != null) {
                        this.name = str2;
                        return;
                    }
                    throw new NullPointerException("Null name");
                }
                throw new NullPointerException("Null id");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Contact)) {
                    return false;
                }
                Contact contact = (Contact) obj;
                return this.id.equals(contact.getId()) && this.name.equals(contact.getName());
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Contact
            public String getId() {
                return this.id;
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Contact
            public String getName() {
                return this.name;
            }

            public int hashCode() {
                return ((this.id.hashCode() ^ 1000003) * 1000003) ^ this.name.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Contact{id=");
                outline107.append(this.id);
                outline107.append(", name=");
                return GeneratedOutlineSupport1.outline91(outline107, this.name, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
