package com.amazon.alexa.drive.navigation;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.SavedLocations;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class AutoValue_SavedLocations extends C$AutoValue_SavedLocations {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SavedLocations> {
        private final Gson gson;
        private volatile TypeAdapter<Integer> int__adapter;
        private volatile TypeAdapter<SavedLocations.Links> links_adapter;
        private volatile TypeAdapter<List<SavedLocations.Item>> list__item_adapter;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("totalCount", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "links");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SavedLocations.class, outline128, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SavedLocations mo8353read(JsonReader jsonReader) throws IOException {
            List<SavedLocations.Item> list = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            int i = 0;
            SavedLocations.Links links = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("totalCount").equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter = this.int__adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Integer.class);
                            this.int__adapter = typeAdapter;
                        }
                        i = typeAdapter.mo8353read(jsonReader).intValue();
                    } else if (this.realFieldNames.get(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS).equals(nextName)) {
                        TypeAdapter<List<SavedLocations.Item>> typeAdapter2 = this.list__item_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(TypeToken.getParameterized(List.class, SavedLocations.Item.class));
                            this.list__item_adapter = typeAdapter2;
                        }
                        list = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("links").equals(nextName)) {
                        TypeAdapter<SavedLocations.Links> typeAdapter3 = this.links_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(SavedLocations.Links.class);
                            this.links_adapter = typeAdapter3;
                        }
                        links = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SavedLocations(i, list, links);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SavedLocations savedLocations) throws IOException {
            if (savedLocations == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("totalCount"));
            TypeAdapter<Integer> typeAdapter = this.int__adapter;
            if (typeAdapter == null) {
                typeAdapter = this.gson.getAdapter(Integer.class);
                this.int__adapter = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Integer.valueOf(savedLocations.getTotalCount()));
            jsonWriter.name(this.realFieldNames.get(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS));
            if (savedLocations.getItems() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<SavedLocations.Item>> typeAdapter2 = this.list__item_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(TypeToken.getParameterized(List.class, SavedLocations.Item.class));
                    this.list__item_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, savedLocations.getItems());
            }
            jsonWriter.name(this.realFieldNames.get("links"));
            if (savedLocations.getLinks() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<SavedLocations.Links> typeAdapter3 = this.links_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(SavedLocations.Links.class);
                    this.links_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, savedLocations.getLinks());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SavedLocations(final int i, final List<SavedLocations.Item> list, @Nullable final SavedLocations.Links links) {
        new SavedLocations(i, list, links) { // from class: com.amazon.alexa.drive.navigation.$AutoValue_SavedLocations
            private final List<SavedLocations.Item> items;
            private final SavedLocations.Links links;
            private final int totalCount;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.totalCount = i;
                if (list != null) {
                    this.items = list;
                    this.links = links;
                    return;
                }
                throw new NullPointerException("Null items");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SavedLocations)) {
                    return false;
                }
                SavedLocations savedLocations = (SavedLocations) obj;
                if (this.totalCount == savedLocations.getTotalCount() && this.items.equals(savedLocations.getItems())) {
                    SavedLocations.Links links2 = this.links;
                    if (links2 == null) {
                        if (savedLocations.getLinks() == null) {
                            return true;
                        }
                    } else if (links2.equals(savedLocations.getLinks())) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations
            public List<SavedLocations.Item> getItems() {
                return this.items;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations
            @Nullable
            public SavedLocations.Links getLinks() {
                return this.links;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations
            public int getTotalCount() {
                return this.totalCount;
            }

            public int hashCode() {
                int hashCode = (((this.totalCount ^ 1000003) * 1000003) ^ this.items.hashCode()) * 1000003;
                SavedLocations.Links links2 = this.links;
                return hashCode ^ (links2 == null ? 0 : links2.hashCode());
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SavedLocations{totalCount=");
                outline107.append(this.totalCount);
                outline107.append(", items=");
                outline107.append(this.items);
                outline107.append(", links=");
                outline107.append(this.links);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
