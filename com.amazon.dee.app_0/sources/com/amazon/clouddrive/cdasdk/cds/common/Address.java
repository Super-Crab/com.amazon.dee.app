package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class Address {
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("county")
    private String county;
    @JsonProperty("district")
    private String district;
    @JsonProperty("landmark")
    private String landmark;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("state")
    private String state;

    protected boolean canEqual(Object obj) {
        return obj instanceof Address;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        if (!address.canEqual(this)) {
            return false;
        }
        String country = getCountry();
        String country2 = address.getCountry();
        if (country != null ? !country.equals(country2) : country2 != null) {
            return false;
        }
        String state = getState();
        String state2 = address.getState();
        if (state != null ? !state.equals(state2) : state2 != null) {
            return false;
        }
        String county = getCounty();
        String county2 = address.getCounty();
        if (county != null ? !county.equals(county2) : county2 != null) {
            return false;
        }
        String city = getCity();
        String city2 = address.getCity();
        if (city != null ? !city.equals(city2) : city2 != null) {
            return false;
        }
        String district = getDistrict();
        String district2 = address.getDistrict();
        if (district != null ? !district.equals(district2) : district2 != null) {
            return false;
        }
        String landmark = getLandmark();
        String landmark2 = address.getLandmark();
        if (landmark != null ? !landmark.equals(landmark2) : landmark2 != null) {
            return false;
        }
        Double latitude = getLatitude();
        Double latitude2 = address.getLatitude();
        if (latitude != null ? !latitude.equals(latitude2) : latitude2 != null) {
            return false;
        }
        Double longitude = getLongitude();
        Double longitude2 = address.getLongitude();
        if (longitude != null ? !longitude.equals(longitude2) : longitude2 != null) {
            return false;
        }
        String countryCode = getCountryCode();
        String countryCode2 = address.getCountryCode();
        return countryCode != null ? countryCode.equals(countryCode2) : countryCode2 == null;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getCounty() {
        return this.county;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getLandmark() {
        return this.landmark;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public String getState() {
        return this.state;
    }

    public int hashCode() {
        String country = getCountry();
        int i = 43;
        int hashCode = country == null ? 43 : country.hashCode();
        String state = getState();
        int hashCode2 = ((hashCode + 59) * 59) + (state == null ? 43 : state.hashCode());
        String county = getCounty();
        int hashCode3 = (hashCode2 * 59) + (county == null ? 43 : county.hashCode());
        String city = getCity();
        int hashCode4 = (hashCode3 * 59) + (city == null ? 43 : city.hashCode());
        String district = getDistrict();
        int hashCode5 = (hashCode4 * 59) + (district == null ? 43 : district.hashCode());
        String landmark = getLandmark();
        int hashCode6 = (hashCode5 * 59) + (landmark == null ? 43 : landmark.hashCode());
        Double latitude = getLatitude();
        int hashCode7 = (hashCode6 * 59) + (latitude == null ? 43 : latitude.hashCode());
        Double longitude = getLongitude();
        int hashCode8 = (hashCode7 * 59) + (longitude == null ? 43 : longitude.hashCode());
        String countryCode = getCountryCode();
        int i2 = hashCode8 * 59;
        if (countryCode != null) {
            i = countryCode.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("city")
    public void setCity(String str) {
        this.city = str;
    }

    @JsonProperty("country")
    public void setCountry(String str) {
        this.country = str;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    @JsonProperty("county")
    public void setCounty(String str) {
        this.county = str;
    }

    @JsonProperty("district")
    public void setDistrict(String str) {
        this.district = str;
    }

    @JsonProperty("landmark")
    public void setLandmark(String str) {
        this.landmark = str;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double d) {
        this.latitude = d;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double d) {
        this.longitude = d;
    }

    @JsonProperty("state")
    public void setState(String str) {
        this.state = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Address(country=");
        outline107.append(getCountry());
        outline107.append(", state=");
        outline107.append(getState());
        outline107.append(", county=");
        outline107.append(getCounty());
        outline107.append(", city=");
        outline107.append(getCity());
        outline107.append(", district=");
        outline107.append(getDistrict());
        outline107.append(", landmark=");
        outline107.append(getLandmark());
        outline107.append(", latitude=");
        outline107.append(getLatitude());
        outline107.append(", longitude=");
        outline107.append(getLongitude());
        outline107.append(", countryCode=");
        outline107.append(getCountryCode());
        outline107.append(")");
        return outline107.toString();
    }
}
