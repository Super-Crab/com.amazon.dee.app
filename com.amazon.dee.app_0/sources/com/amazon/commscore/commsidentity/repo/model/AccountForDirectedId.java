package com.amazon.commscore.commsidentity.repo.model;
/* loaded from: classes12.dex */
public class AccountForDirectedId {
    private String commsId;
    private String commsProvisionStatus;
    private boolean commsProvisioned;
    private String directedId;
    private boolean enrolledInAlexa;
    private String firstName;
    private boolean isChild;
    private String lastName;
    private String personIdV2;
    private String phoneCountryCode;
    private String phoneNumber;
    private String phoneticFirstName;
    private String phoneticLastName;
    private boolean signedInUser;
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
