package com.amazon.alexa.drive.navigation;

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
public final class AutoValue_SavedLocations_Item extends C$AutoValue_SavedLocations_Item {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SavedLocations.Item> {
        private volatile TypeAdapter<SavedLocations.Item.Address> address_adapter;
        private volatile TypeAdapter<SavedLocations.Item.Coordinate> coordinate_adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("address");
            arrayList.add("addressId");
            arrayList.add("addressLabel");
            arrayList.add("coordinate");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SavedLocations_Item.class, arrayList, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SavedLocations.Item mo8353read(JsonReader jsonReader) throws IOException {
            SavedLocations.Item.Address address = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            SavedLocations.Item.Coordinate coordinate = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("address").equals(nextName)) {
                        TypeAdapter<SavedLocations.Item.Address> typeAdapter = this.address_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(SavedLocations.Item.Address.class);
                            this.address_adapter = typeAdapter;
                        }
                        address = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("addressId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("addressLabel").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str2 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("coordinate").equals(nextName)) {
                        TypeAdapter<SavedLocations.Item.Coordinate> typeAdapter4 = this.coordinate_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(SavedLocations.Item.Coordinate.class);
                            this.coordinate_adapter = typeAdapter4;
                        }
                        coordinate = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SavedLocations_Item(address, str, str2, coordinate);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SavedLocations.Item item) throws IOException {
            if (item == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("address"));
            if (item.getAddress() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<SavedLocations.Item.Address> typeAdapter = this.address_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(SavedLocations.Item.Address.class);
                    this.address_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, item.getAddress());
            }
            jsonWriter.name(this.realFieldNames.get("addressId"));
            if (item.getAddressId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, item.getAddressId());
            }
            jsonWriter.name(this.realFieldNames.get("addressLabel"));
            if (item.getAddressLabel() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, item.getAddressLabel());
            }
            jsonWriter.name(this.realFieldNames.get("coordinate"));
            if (item.getCoordinate() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<SavedLocations.Item.Coordinate> typeAdapter4 = this.coordinate_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(SavedLocations.Item.Coordinate.class);
                    this.coordinate_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, item.getCoordinate());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SavedLocations_Item(final SavedLocations.Item.Address address, final String str, final String str2, final SavedLocations.Item.Coordinate coordinate) {
        new SavedLocations.Item(address, str, str2, coordinate) { // from class: com.amazon.alexa.drive.navigation.$AutoValue_SavedLocations_Item
            private final SavedLocations.Item.Address address;
            private final String addressId;
            private final String addressLabel;
            private final SavedLocations.Item.Coordinate coordinate;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (address != null) {
                    this.address = address;
                    if (str != null) {
                        this.addressId = str;
                        if (str2 != null) {
                            this.addressLabel = str2;
                            if (coordinate != null) {
                                this.coordinate = coordinate;
                                return;
                            }
                            throw new NullPointerException("Null coordinate");
                        }
                        throw new NullPointerException("Null addressLabel");
                    }
                    throw new NullPointerException("Null addressId");
                }
                throw new NullPointerException("Null address");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SavedLocations.Item)) {
                    return false;
                }
                SavedLocations.Item item = (SavedLocations.Item) obj;
                return this.address.equals(item.getAddress()) && this.addressId.equals(item.getAddressId()) && this.addressLabel.equals(item.getAddressLabel()) && this.coordinate.equals(item.getCoordinate());
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item
            public SavedLocations.Item.Address getAddress() {
                return this.address;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item
            public String getAddressId() {
                return this.addressId;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item
            public String getAddressLabel() {
                return this.addressLabel;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item
            public SavedLocations.Item.Coordinate getCoordinate() {
                return this.coordinate;
            }

            public int hashCode() {
                return ((((((this.address.hashCode() ^ 1000003) * 1000003) ^ this.addressId.hashCode()) * 1000003) ^ this.addressLabel.hashCode()) * 1000003) ^ this.coordinate.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Item{address=");
                outline107.append(this.address);
                outline107.append(", addressId=");
                outline107.append(this.addressId);
                outline107.append(", addressLabel=");
                outline107.append(this.addressLabel);
                outline107.append(", coordinate=");
                outline107.append(this.coordinate);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
