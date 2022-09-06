package com.amazon.alexa.drive.navigation;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.navigation.AutoValue_SavedLocations;
import com.amazon.alexa.drive.navigation.AutoValue_SavedLocations_Item;
import com.amazon.alexa.drive.navigation.AutoValue_SavedLocations_Item_Address;
import com.amazon.alexa.drive.navigation.AutoValue_SavedLocations_Item_Coordinate;
import com.amazon.alexa.drive.navigation.AutoValue_SavedLocations_Links;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
@AutoValue
/* loaded from: classes7.dex */
public abstract class SavedLocations {

    @AutoValue
    /* loaded from: classes7.dex */
    public static abstract class Item {

        @AutoValue
        /* loaded from: classes7.dex */
        public static abstract class Address {
            public static Address create(String str, @Nullable String str2, @Nullable String str3, String str4, String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
                return new AutoValue_SavedLocations_Item_Address(str, str2, str3, str4, str5, str6, str7, str8);
            }

            public static TypeAdapter<Address> typeAdapter(Gson gson) {
                return new AutoValue_SavedLocations_Item_Address.GsonTypeAdapter(gson);
            }

            @Nullable
            public abstract String getAddressLine1();

            @Nullable
            public abstract String getAddressLine2();

            @Nullable
            public abstract String getAddressLine3();

            @Nullable
            public abstract String getCity();

            @Nullable
            public abstract String getCountryCode();

            @Nullable
            public abstract String getDistrictOrCounty();

            @Nullable
            public abstract String getPostalCode();

            @Nullable
            public abstract String getStateOrRegion();
        }

        @AutoValue
        /* loaded from: classes7.dex */
        public static abstract class Coordinate {
            public static Coordinate create(Double d, Double d2) {
                return new AutoValue_SavedLocations_Item_Coordinate(d, d2);
            }

            public static TypeAdapter<Coordinate> typeAdapter(Gson gson) {
                return new AutoValue_SavedLocations_Item_Coordinate.GsonTypeAdapter(gson);
            }

            public abstract Double getLatitudeInDegrees();

            public abstract Double getLongitudeInDegrees();
        }

        public static Item create(Address address, String str, String str2, Coordinate coordinate) {
            return new AutoValue_SavedLocations_Item(address, str, str2, coordinate);
        }

        public static TypeAdapter<Item> typeAdapter(Gson gson) {
            return new AutoValue_SavedLocations_Item.GsonTypeAdapter(gson);
        }

        public abstract Address getAddress();

        public abstract String getAddressId();

        public abstract String getAddressLabel();

        public abstract Coordinate getCoordinate();
    }

    @AutoValue
    /* loaded from: classes7.dex */
    public static abstract class Links {
        public static Links create(String str) {
            return new AutoValue_SavedLocations_Links(str);
        }

        public static TypeAdapter<Links> typeAdapter(Gson gson) {
            return new AutoValue_SavedLocations_Links.GsonTypeAdapter(gson);
        }

        @Nullable
        public abstract String getNext();
    }

    public static SavedLocations create(int i, List<Item> list) {
        return new AutoValue_SavedLocations(i, list, null);
    }

    public static TypeAdapter<SavedLocations> typeAdapter(Gson gson) {
        return new AutoValue_SavedLocations.GsonTypeAdapter(gson);
    }

    public abstract List<Item> getItems();

    @Nullable
    public abstract Links getLinks();

    public abstract int getTotalCount();

    public static SavedLocations create(int i, List<Item> list, @Nullable Links links) {
        return new AutoValue_SavedLocations(i, list, links);
    }
}
