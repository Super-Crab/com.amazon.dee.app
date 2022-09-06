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
public final class AutoValue_SavedLocations_Item_Address extends C$AutoValue_SavedLocations_Item_Address {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SavedLocations.Item.Address> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("addressLine1", "addressLine2", "addressLine3", "city", "countryCode");
            outline129.add("districtOrCounty");
            outline129.add("postalCode");
            outline129.add("stateOrRegion");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SavedLocations_Item_Address.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SavedLocations.Item.Address mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            String str7 = null;
            String str8 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("addressLine1").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("addressLine2").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("addressLine3").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("city").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("countryCode").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.string_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter5;
                        }
                        str5 = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("districtOrCounty").equals(nextName)) {
                        TypeAdapter<String> typeAdapter6 = this.string_adapter;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter6;
                        }
                        str6 = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("postalCode").equals(nextName)) {
                        TypeAdapter<String> typeAdapter7 = this.string_adapter;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter7;
                        }
                        str7 = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("stateOrRegion").equals(nextName)) {
                        TypeAdapter<String> typeAdapter8 = this.string_adapter;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter8;
                        }
                        str8 = typeAdapter8.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SavedLocations_Item_Address(str, str2, str3, str4, str5, str6, str7, str8);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SavedLocations.Item.Address address) throws IOException {
            if (address == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("addressLine1"));
            if (address.getAddressLine1() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, address.getAddressLine1());
            }
            jsonWriter.name(this.realFieldNames.get("addressLine2"));
            if (address.getAddressLine2() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, address.getAddressLine2());
            }
            jsonWriter.name(this.realFieldNames.get("addressLine3"));
            if (address.getAddressLine3() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, address.getAddressLine3());
            }
            jsonWriter.name(this.realFieldNames.get("city"));
            if (address.getCity() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, address.getCity());
            }
            jsonWriter.name(this.realFieldNames.get("countryCode"));
            if (address.getCountryCode() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.string_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, address.getCountryCode());
            }
            jsonWriter.name(this.realFieldNames.get("districtOrCounty"));
            if (address.getDistrictOrCounty() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.string_adapter;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, address.getDistrictOrCounty());
            }
            jsonWriter.name(this.realFieldNames.get("postalCode"));
            if (address.getPostalCode() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter7 = this.string_adapter;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, address.getPostalCode());
            }
            jsonWriter.name(this.realFieldNames.get("stateOrRegion"));
            if (address.getStateOrRegion() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter8 = this.string_adapter;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, address.getStateOrRegion());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SavedLocations_Item_Address(@Nullable final String str, @Nullable final String str2, @Nullable final String str3, @Nullable final String str4, @Nullable final String str5, @Nullable final String str6, @Nullable final String str7, @Nullable final String str8) {
        new SavedLocations.Item.Address(str, str2, str3, str4, str5, str6, str7, str8) { // from class: com.amazon.alexa.drive.navigation.$AutoValue_SavedLocations_Item_Address
            private final String addressLine1;
            private final String addressLine2;
            private final String addressLine3;
            private final String city;
            private final String countryCode;
            private final String districtOrCounty;
            private final String postalCode;
            private final String stateOrRegion;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.addressLine1 = str;
                this.addressLine2 = str2;
                this.addressLine3 = str3;
                this.city = str4;
                this.countryCode = str5;
                this.districtOrCounty = str6;
                this.postalCode = str7;
                this.stateOrRegion = str8;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SavedLocations.Item.Address)) {
                    return false;
                }
                SavedLocations.Item.Address address = (SavedLocations.Item.Address) obj;
                String str9 = this.addressLine1;
                if (str9 != null ? str9.equals(address.getAddressLine1()) : address.getAddressLine1() == null) {
                    String str10 = this.addressLine2;
                    if (str10 != null ? str10.equals(address.getAddressLine2()) : address.getAddressLine2() == null) {
                        String str11 = this.addressLine3;
                        if (str11 != null ? str11.equals(address.getAddressLine3()) : address.getAddressLine3() == null) {
                            String str12 = this.city;
                            if (str12 != null ? str12.equals(address.getCity()) : address.getCity() == null) {
                                String str13 = this.countryCode;
                                if (str13 != null ? str13.equals(address.getCountryCode()) : address.getCountryCode() == null) {
                                    String str14 = this.districtOrCounty;
                                    if (str14 != null ? str14.equals(address.getDistrictOrCounty()) : address.getDistrictOrCounty() == null) {
                                        String str15 = this.postalCode;
                                        if (str15 != null ? str15.equals(address.getPostalCode()) : address.getPostalCode() == null) {
                                            String str16 = this.stateOrRegion;
                                            if (str16 == null) {
                                                if (address.getStateOrRegion() == null) {
                                                    return true;
                                                }
                                            } else if (str16.equals(address.getStateOrRegion())) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Address
            @Nullable
            public String getAddressLine1() {
                return this.addressLine1;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Address
            @Nullable
            public String getAddressLine2() {
                return this.addressLine2;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Address
            @Nullable
            public String getAddressLine3() {
                return this.addressLine3;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Address
            @Nullable
            public String getCity() {
                return this.city;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Address
            @Nullable
            public String getCountryCode() {
                return this.countryCode;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Address
            @Nullable
            public String getDistrictOrCounty() {
                return this.districtOrCounty;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Address
            @Nullable
            public String getPostalCode() {
                return this.postalCode;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Address
            @Nullable
            public String getStateOrRegion() {
                return this.stateOrRegion;
            }

            public int hashCode() {
                String str9 = this.addressLine1;
                int i = 0;
                int hashCode = ((str9 == null ? 0 : str9.hashCode()) ^ 1000003) * 1000003;
                String str10 = this.addressLine2;
                int hashCode2 = (hashCode ^ (str10 == null ? 0 : str10.hashCode())) * 1000003;
                String str11 = this.addressLine3;
                int hashCode3 = (hashCode2 ^ (str11 == null ? 0 : str11.hashCode())) * 1000003;
                String str12 = this.city;
                int hashCode4 = (hashCode3 ^ (str12 == null ? 0 : str12.hashCode())) * 1000003;
                String str13 = this.countryCode;
                int hashCode5 = (hashCode4 ^ (str13 == null ? 0 : str13.hashCode())) * 1000003;
                String str14 = this.districtOrCounty;
                int hashCode6 = (hashCode5 ^ (str14 == null ? 0 : str14.hashCode())) * 1000003;
                String str15 = this.postalCode;
                int hashCode7 = (hashCode6 ^ (str15 == null ? 0 : str15.hashCode())) * 1000003;
                String str16 = this.stateOrRegion;
                if (str16 != null) {
                    i = str16.hashCode();
                }
                return hashCode7 ^ i;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Address{addressLine1=");
                outline107.append(this.addressLine1);
                outline107.append(", addressLine2=");
                outline107.append(this.addressLine2);
                outline107.append(", addressLine3=");
                outline107.append(this.addressLine3);
                outline107.append(", city=");
                outline107.append(this.city);
                outline107.append(", countryCode=");
                outline107.append(this.countryCode);
                outline107.append(", districtOrCounty=");
                outline107.append(this.districtOrCounty);
                outline107.append(", postalCode=");
                outline107.append(this.postalCode);
                outline107.append(", stateOrRegion=");
                return GeneratedOutlineSupport1.outline91(outline107, this.stateOrRegion, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
