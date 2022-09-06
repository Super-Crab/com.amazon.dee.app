package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes6.dex */
public class EmergencyAddress {
    @SerializedName("addressLine1")
    private String addressLine1 = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public EmergencyAddress addressLine1(String str) {
        this.addressLine1 = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && EmergencyAddress.class == obj.getClass()) {
            return Objects.equals(this.addressLine1, ((EmergencyAddress) obj).addressLine1);
        }
        return false;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public int hashCode() {
        return Objects.hash(this.addressLine1);
    }

    public void setAddressLine1(String str) {
        this.addressLine1 = str;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline113("class EmergencyAddress {\n", "    addressLine1: "), toIndentedString(this.addressLine1), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
