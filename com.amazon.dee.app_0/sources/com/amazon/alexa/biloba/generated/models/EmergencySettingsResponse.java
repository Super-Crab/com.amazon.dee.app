package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes6.dex */
public class EmergencySettingsResponse {
    @SerializedName("emergencyHelplineStatus")
    private String emergencyHelplineStatus = null;
    @SerializedName("emergencyAddress")
    private EmergencyAddress emergencyAddress = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public EmergencySettingsResponse emergencyAddress(EmergencyAddress emergencyAddress) {
        this.emergencyAddress = emergencyAddress;
        return this;
    }

    public EmergencySettingsResponse emergencyHelplineStatus(String str) {
        this.emergencyHelplineStatus = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EmergencySettingsResponse.class != obj.getClass()) {
            return false;
        }
        EmergencySettingsResponse emergencySettingsResponse = (EmergencySettingsResponse) obj;
        return Objects.equals(this.emergencyHelplineStatus, emergencySettingsResponse.emergencyHelplineStatus) && Objects.equals(this.emergencyAddress, emergencySettingsResponse.emergencyAddress);
    }

    public EmergencyAddress getEmergencyAddress() {
        return this.emergencyAddress;
    }

    public String getEmergencyHelplineStatus() {
        return this.emergencyHelplineStatus;
    }

    public int hashCode() {
        return Objects.hash(this.emergencyHelplineStatus, this.emergencyAddress);
    }

    public void setEmergencyAddress(EmergencyAddress emergencyAddress) {
        this.emergencyAddress = emergencyAddress;
    }

    public void setEmergencyHelplineStatus(String str) {
        this.emergencyHelplineStatus = str;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class EmergencySettingsResponse {\n", "    emergencyHelplineStatus: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.emergencyHelplineStatus), "\n", "    emergencyAddress: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.emergencyAddress), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
