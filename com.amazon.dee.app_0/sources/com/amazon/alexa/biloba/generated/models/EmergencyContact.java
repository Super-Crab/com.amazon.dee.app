package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes6.dex */
public class EmergencyContact {
    @SerializedName("contactId")
    private String contactId = null;
    @SerializedName("personIdV1")
    private String personIdV1 = null;
    @SerializedName("commsId")
    private String commsId = null;
    @SerializedName("firstName")
    private String firstName = null;
    @SerializedName("lastName")
    private String lastName = null;
    @SerializedName("phoneNumber")
    private PhoneNumber phoneNumber = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public EmergencyContact commsId(String str) {
        this.commsId = str;
        return this;
    }

    public EmergencyContact contactId(String str) {
        this.contactId = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EmergencyContact.class != obj.getClass()) {
            return false;
        }
        EmergencyContact emergencyContact = (EmergencyContact) obj;
        return Objects.equals(this.contactId, emergencyContact.contactId) && Objects.equals(this.personIdV1, emergencyContact.personIdV1) && Objects.equals(this.commsId, emergencyContact.commsId) && Objects.equals(this.firstName, emergencyContact.firstName) && Objects.equals(this.lastName, emergencyContact.lastName) && Objects.equals(this.phoneNumber, emergencyContact.phoneNumber);
    }

    public EmergencyContact firstName(String str) {
        this.firstName = str;
        return this;
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getContactId() {
        return this.contactId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPersonIdV1() {
        return this.personIdV1;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public int hashCode() {
        return Objects.hash(this.contactId, this.personIdV1, this.commsId, this.firstName, this.lastName, this.phoneNumber);
    }

    public EmergencyContact lastName(String str) {
        this.lastName = str;
        return this;
    }

    public EmergencyContact personIdV1(String str) {
        this.personIdV1 = str;
        return this;
    }

    public EmergencyContact phoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setContactId(String str) {
        this.contactId = str;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setPersonIdV1(String str) {
        this.personIdV1 = str;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class EmergencyContact {\n", "    contactId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.contactId), "\n", "    personIdV1: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.personIdV1), "\n", "    commsId: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.commsId), "\n", "    firstName: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.firstName), "\n", "    lastName: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.lastName), "\n", "    phoneNumber: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.phoneNumber), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
