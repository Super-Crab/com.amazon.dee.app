package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes6.dex */
public class PhoneNumber {
    @SerializedName("countryCode")
    private String countryCode = null;
    @SerializedName("localPhoneNumber")
    private String localPhoneNumber = null;
    @SerializedName("phoneNumberType")
    private String phoneNumberType = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public PhoneNumber countryCode(String str) {
        this.countryCode = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PhoneNumber.class != obj.getClass()) {
            return false;
        }
        PhoneNumber phoneNumber = (PhoneNumber) obj;
        return Objects.equals(this.countryCode, phoneNumber.countryCode) && Objects.equals(this.localPhoneNumber, phoneNumber.localPhoneNumber) && Objects.equals(this.phoneNumberType, phoneNumber.phoneNumberType);
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getLocalPhoneNumber() {
        return this.localPhoneNumber;
    }

    public String getPhoneNumberType() {
        return this.phoneNumberType;
    }

    public int hashCode() {
        return Objects.hash(this.countryCode, this.localPhoneNumber, this.phoneNumberType);
    }

    public PhoneNumber localPhoneNumber(String str) {
        this.localPhoneNumber = str;
        return this;
    }

    public PhoneNumber phoneNumberType(String str) {
        this.phoneNumberType = str;
        return this;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setLocalPhoneNumber(String str) {
        this.localPhoneNumber = str;
    }

    public void setPhoneNumberType(String str) {
        this.phoneNumberType = str;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class PhoneNumber {\n", "    countryCode: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.countryCode), "\n", "    localPhoneNumber: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.localPhoneNumber), "\n", "    phoneNumberType: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.phoneNumberType), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
