package com.amazon.clouddrive.cdasdk.cds.search;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class LocationInfo {
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("countryIso3Code")
    private String countryIso3Code;
    @JsonProperty("state")
    private String state;
    @JsonProperty("stateCode")
    private String stateCode;

    protected boolean canEqual(Object obj) {
        return obj instanceof LocationInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocationInfo)) {
            return false;
        }
        LocationInfo locationInfo = (LocationInfo) obj;
        if (!locationInfo.canEqual(this)) {
            return false;
        }
        String state = getState();
        String state2 = locationInfo.getState();
        if (state != null ? !state.equals(state2) : state2 != null) {
            return false;
        }
        String stateCode = getStateCode();
        String stateCode2 = locationInfo.getStateCode();
        if (stateCode != null ? !stateCode.equals(stateCode2) : stateCode2 != null) {
            return false;
        }
        String city = getCity();
        String city2 = locationInfo.getCity();
        if (city != null ? !city.equals(city2) : city2 != null) {
            return false;
        }
        String country = getCountry();
        String country2 = locationInfo.getCountry();
        if (country != null ? !country.equals(country2) : country2 != null) {
            return false;
        }
        String countryIso3Code = getCountryIso3Code();
        String countryIso3Code2 = locationInfo.getCountryIso3Code();
        return countryIso3Code != null ? countryIso3Code.equals(countryIso3Code2) : countryIso3Code2 == null;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCountryIso3Code() {
        return this.countryIso3Code;
    }

    public String getState() {
        return this.state;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public int hashCode() {
        String state = getState();
        int i = 43;
        int hashCode = state == null ? 43 : state.hashCode();
        String stateCode = getStateCode();
        int hashCode2 = ((hashCode + 59) * 59) + (stateCode == null ? 43 : stateCode.hashCode());
        String city = getCity();
        int hashCode3 = (hashCode2 * 59) + (city == null ? 43 : city.hashCode());
        String country = getCountry();
        int hashCode4 = (hashCode3 * 59) + (country == null ? 43 : country.hashCode());
        String countryIso3Code = getCountryIso3Code();
        int i2 = hashCode4 * 59;
        if (countryIso3Code != null) {
            i = countryIso3Code.hashCode();
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

    @JsonProperty("countryIso3Code")
    public void setCountryIso3Code(String str) {
        this.countryIso3Code = str;
    }

    @JsonProperty("state")
    public void setState(String str) {
        this.state = str;
    }

    @JsonProperty("stateCode")
    public void setStateCode(String str) {
        this.stateCode = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocationInfo(state=");
        outline107.append(getState());
        outline107.append(", stateCode=");
        outline107.append(getStateCode());
        outline107.append(", city=");
        outline107.append(getCity());
        outline107.append(", country=");
        outline107.append(getCountry());
        outline107.append(", countryIso3Code=");
        outline107.append(getCountryIso3Code());
        outline107.append(")");
        return outline107.toString();
    }
}
