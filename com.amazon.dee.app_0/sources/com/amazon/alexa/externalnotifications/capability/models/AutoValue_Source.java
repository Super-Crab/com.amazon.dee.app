package com.amazon.alexa.externalnotifications.capability.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
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
public final class AutoValue_Source extends C$AutoValue_Source {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Source> {
        private volatile TypeAdapter<Contact> contact_adapter;
        private volatile TypeAdapter<Group> group_adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<SourceType> sourceType_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128(ContactProviderContract.CONTACT_PATH, "group", "type");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Source.class, outline128, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Source mo8353read(JsonReader jsonReader) throws IOException {
            Contact contact = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Group group = null;
            SourceType sourceType = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get(ContactProviderContract.CONTACT_PATH).equals(nextName)) {
                        TypeAdapter<Contact> typeAdapter = this.contact_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Contact.class);
                            this.contact_adapter = typeAdapter;
                        }
                        contact = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("group").equals(nextName)) {
                        TypeAdapter<Group> typeAdapter2 = this.group_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Group.class);
                            this.group_adapter = typeAdapter2;
                        }
                        group = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("type").equals(nextName)) {
                        TypeAdapter<SourceType> typeAdapter3 = this.sourceType_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(SourceType.class);
                            this.sourceType_adapter = typeAdapter3;
                        }
                        sourceType = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Source(contact, group, sourceType);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Source source) throws IOException {
            if (source == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get(ContactProviderContract.CONTACT_PATH));
            if (source.getContact() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Contact> typeAdapter = this.contact_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(Contact.class);
                    this.contact_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, source.getContact());
            }
            jsonWriter.name(this.realFieldNames.get("group"));
            if (source.getGroup() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Group> typeAdapter2 = this.group_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(Group.class);
                    this.group_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, source.getGroup());
            }
            jsonWriter.name(this.realFieldNames.get("type"));
            if (source.getType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<SourceType> typeAdapter3 = this.sourceType_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(SourceType.class);
                    this.sourceType_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, source.getType());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Source(final Contact contact, @Nullable final Group group, final SourceType sourceType) {
        new Source(contact, group, sourceType) { // from class: com.amazon.alexa.externalnotifications.capability.models.$AutoValue_Source
            private final Contact contact;
            private final Group group;
            private final SourceType type;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (contact != null) {
                    this.contact = contact;
                    this.group = group;
                    if (sourceType != null) {
                        this.type = sourceType;
                        return;
                    }
                    throw new NullPointerException("Null type");
                }
                throw new NullPointerException("Null contact");
            }

            public boolean equals(Object obj) {
                Group group2;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Source)) {
                    return false;
                }
                Source source = (Source) obj;
                return this.contact.equals(source.getContact()) && ((group2 = this.group) != null ? group2.equals(source.getGroup()) : source.getGroup() == null) && this.type.equals(source.getType());
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Source
            public Contact getContact() {
                return this.contact;
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Source
            @Nullable
            public Group getGroup() {
                return this.group;
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Source
            public SourceType getType() {
                return this.type;
            }

            public int hashCode() {
                int hashCode = (this.contact.hashCode() ^ 1000003) * 1000003;
                Group group2 = this.group;
                return ((hashCode ^ (group2 == null ? 0 : group2.hashCode())) * 1000003) ^ this.type.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Source{contact=");
                outline107.append(this.contact);
                outline107.append(", group=");
                outline107.append(this.group);
                outline107.append(", type=");
                outline107.append(this.type);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
