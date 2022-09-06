package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class Address {
    @NonNull
    @Deprecated
    public final Map<String, String> additionalData;
    @NonNull
    public final String addressText;
    @NonNull
    public final String block;
    @NonNull
    public final String city;
    @NonNull
    public final String country;
    @NonNull
    public final String countryCode;
    @NonNull
    public final String countyName;
    @NonNull
    public final String district;
    @NonNull
    public final String houseNumOrName;
    @NonNull
    public final String postalCode;
    @NonNull
    public final String stateName;
    @NonNull
    public final String streetName;
    @NonNull
    public final String subBlock;
    @NonNull
    public final String subdistrict;

    public Address(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7, @NonNull String str8, @NonNull String str9, @NonNull String str10, @NonNull String str11, @NonNull String str12, @NonNull String str13, @NonNull @Deprecated Map<String, String> map) {
        this.city = str;
        this.countryCode = str2;
        this.country = str3;
        this.district = str4;
        this.subdistrict = str5;
        this.houseNumOrName = str6;
        this.postalCode = str7;
        this.stateName = str8;
        this.countyName = str9;
        this.streetName = str10;
        this.block = str11;
        this.subBlock = str12;
        this.addressText = str13;
        this.additionalData = map;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        return Objects.equals(this.city, address.city) && Objects.equals(this.countryCode, address.countryCode) && Objects.equals(this.country, address.country) && Objects.equals(this.district, address.district) && Objects.equals(this.subdistrict, address.subdistrict) && Objects.equals(this.houseNumOrName, address.houseNumOrName) && Objects.equals(this.postalCode, address.postalCode) && Objects.equals(this.stateName, address.stateName) && Objects.equals(this.countyName, address.countyName) && Objects.equals(this.streetName, address.streetName) && Objects.equals(this.block, address.block) && Objects.equals(this.subBlock, address.subBlock) && Objects.equals(this.addressText, address.addressText) && Objects.equals(this.additionalData, address.additionalData);
    }

    public int hashCode() {
        String str = this.city;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        String str2 = this.countryCode;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.country;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.district;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.subdistrict;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.houseNumOrName;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.postalCode;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.stateName;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.countyName;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.streetName;
        int hashCode10 = (hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.block;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.subBlock;
        int hashCode12 = (hashCode11 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.addressText;
        int hashCode13 = (hashCode12 + (str13 != null ? str13.hashCode() : 0)) * 31;
        Map<String, String> map = this.additionalData;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode13 + i;
    }
}
