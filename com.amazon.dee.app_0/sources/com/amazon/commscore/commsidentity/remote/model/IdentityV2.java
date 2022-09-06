package com.amazon.commscore.commsidentity.remote.model;

import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes12.dex */
public class IdentityV2 {
    @SerializedName(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR)
    private String aor;
    @SerializedName("commsId")
    private String commsId;
    @SerializedName("commsProvisionStatus")
    private String commsProvisionStatus;
    @SerializedName(MetricKeys.META_HASHED_COMMS_ID)
    private String hashedCommsId;
    @SerializedName("homeGroupId")
    private String homeGroupId;
    @SerializedName("homeGroupPceId")
    private String homeGroupPceId;
    @SerializedName("name")
    private Name name;
    @SerializedName("pceId")
    private String pceId;

    public String getAor() {
        return this.aor;
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getCommsProvisionStatus() {
        return this.commsProvisionStatus;
    }

    public String getHashedCommsId() {
        return this.hashedCommsId;
    }

    public String getHomeGroupId() {
        return this.homeGroupId;
    }

    public String getHomeGroupPceId() {
        return this.homeGroupPceId;
    }

    public Name getName() {
        return this.name;
    }

    public String getPceId() {
        return this.pceId;
    }

    public void setAor(String str) {
        this.aor = str;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setCommsProvisionStatus(String str) {
        this.commsProvisionStatus = str;
    }

    public void setHashedCommsId(String str) {
        this.hashedCommsId = str;
    }

    public void setHomeGroupId(String str) {
        this.homeGroupId = str;
    }

    public void setHomeGroupPceId(String str) {
        this.homeGroupPceId = str;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setPceId(String str) {
        this.pceId = str;
    }
}
