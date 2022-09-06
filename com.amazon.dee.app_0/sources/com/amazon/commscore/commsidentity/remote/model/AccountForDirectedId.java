package com.amazon.commscore.commsidentity.remote.model;

import com.amazon.alexa.identity.api.ProfileAttributes;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes12.dex */
public class AccountForDirectedId {
    @SerializedName("commsId")
    private String commsId;
    @SerializedName("commsProvisionStatus")
    private String commsProvisionStatus;
    @SerializedName("commsProvisioned")
    private boolean commsProvisioned;
    @SerializedName("directedId")
    private String directedId;
    @SerializedName("enrolledInAlexa")
    private boolean enrolledInAlexa;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD)
    private boolean isChild;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName(ProfileAttributes.PERSON_ID)
    private String personIdV2;
    @SerializedName("phoneCountryCode")
    private String phoneCountryCode;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME)
    private String phoneticFirstName;
    @SerializedName(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME)
    private String phoneticLastName;
    @SerializedName("signedInUser")
    private boolean signedInUser;
    @SerializedName("speakerProvisioned")
    private boolean speakerProvisioned;

    public String getCommsId() {
        return this.commsId;
    }

    public String getCommsProvisionStatus() {
        return this.commsProvisionStatus;
    }

    public String getDirectedId() {
        return this.directedId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPersonIdV2() {
        return this.personIdV2;
    }

    public String getPhoneCountryCode() {
        return this.phoneCountryCode;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPhoneticFirstName() {
        return this.phoneticFirstName;
    }

    public String getPhoneticLastName() {
        return this.phoneticLastName;
    }

    public boolean isCommsProvisioned() {
        return this.commsProvisioned;
    }

    public boolean isEnrolledInAlexa() {
        return this.enrolledInAlexa;
    }

    public boolean isIsChild() {
        return this.isChild;
    }

    public boolean isSignedInUser() {
        return this.signedInUser;
    }

    public boolean isSpeakerProvisioned() {
        return this.speakerProvisioned;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setCommsProvisionStatus(String str) {
        this.commsProvisionStatus = str;
    }

    public void setCommsProvisioned(boolean z) {
        this.commsProvisioned = z;
    }

    public void setDirectedId(String str) {
        this.directedId = str;
    }

    public void setEnrolledInAlexa(boolean z) {
        this.enrolledInAlexa = z;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setIsChild(boolean z) {
        this.isChild = z;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setPersonIdV2(String str) {
        this.personIdV2 = str;
    }

    public void setPhoneCountryCode(String str) {
        this.phoneCountryCode = str;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setPhoneticFirstName(String str) {
        this.phoneticFirstName = str;
    }

    public void setPhoneticLastName(String str) {
        this.phoneticLastName = str;
    }

    public void setSignedInUser(boolean z) {
        this.signedInUser = z;
    }

    public void setSpeakerProvisioned(boolean z) {
        this.speakerProvisioned = z;
    }
}
