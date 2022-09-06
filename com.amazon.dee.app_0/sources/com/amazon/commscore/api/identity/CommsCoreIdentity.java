package com.amazon.commscore.api.identity;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class CommsCoreIdentity {
    private String commsId;
    private boolean commsProvisioned;
    private String countryOfResidence;
    private String directedId;
    private String email;
    private boolean enrolledInAlexa;
    private String hashedCommsId;
    private String homegroupId;
    private String homegroupPceId;
    private MarketplaceInfo marketplaceInfo;
    private Name name;
    private String pceId;
    private String personIdV2;
    private String phoneCountryCode;
    private String phoneNumber;
    private String provisioningStatus;
    private boolean signedInUser;
    private boolean speakerProvisioned;

    @Nullable
    public String getCommsId() {
        return this.commsId;
    }

    @Nullable
    public String getCountryOfResidence() {
        return this.countryOfResidence;
    }

    @Nullable
    public String getDirectedId() {
        return this.directedId;
    }

    @Nullable
    public String getEmail() {
        return this.email;
    }

    @Nullable
    public String getHashedCommsId() {
        return this.hashedCommsId;
    }

    @Nullable
    public String getHomegroupId() {
        return this.homegroupId;
    }

    @Nullable
    public String getHomegroupPceId() {
        return this.homegroupPceId;
    }

    @Nullable
    public MarketplaceInfo getMarketplaceInfo() {
        return this.marketplaceInfo;
    }

    @Nullable
    public Name getName() {
        return this.name;
    }

    @Nullable
    public String getPceId() {
        return this.pceId;
    }

    @Nullable
    public String getPersonIdV2() {
        return this.personIdV2;
    }

    @Nullable
    public String getPhoneCountryCode() {
        return this.phoneCountryCode;
    }

    @Nullable
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Nullable
    public String getProvisioningStatus() {
        return this.provisioningStatus;
    }

    public boolean isCommsProvisioned() {
        return this.commsProvisioned;
    }

    public boolean isEnrolledInAlexa() {
        return this.enrolledInAlexa;
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

    public void setCommsProvisioned(boolean z) {
        this.commsProvisioned = z;
    }

    public void setCountryOfResidence(String str) {
        this.countryOfResidence = str;
    }

    public void setDirectedId(String str) {
        this.directedId = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setEnrolledInAlexa(boolean z) {
        this.enrolledInAlexa = z;
    }

    public void setHashedCommsId(String str) {
        this.hashedCommsId = str;
    }

    public void setHomegroupId(String str) {
        this.homegroupId = str;
    }

    public void setHomegroupPceId(String str) {
        this.homegroupPceId = str;
    }

    public void setMarketplaceInfo(MarketplaceInfo marketplaceInfo) {
        this.marketplaceInfo = marketplaceInfo;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setPceId(String str) {
        this.pceId = str;
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

    public void setProvisioningStatus(String str) {
        this.provisioningStatus = str;
    }

    public void setSignedInUser(boolean z) {
        this.signedInUser = z;
    }

    public void setSpeakerProvisioned(boolean z) {
        this.speakerProvisioned = z;
    }
}
