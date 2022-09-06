package com.amazon.deecomms.contacts.model;

import androidx.annotation.NonNull;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class UserInfo {
    @JsonProperty("commsProvisionStatus")
    private CommsProvisionStatus commsProvisionStatus;
    @JsonProperty("commsId")
    private String commsId = null;
    @JsonProperty(MetricKeys.META_HASHED_COMMS_ID)
    private String hashedCommsId = null;
    @JsonProperty("homeGroupId")
    private String homeGroupId = null;
    @JsonProperty(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR)
    private String aor = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserInfo)) {
            return false;
        }
        UserInfo userInfo = (UserInfo) obj;
        return Objects.equal(this.commsId, userInfo.commsId) && Objects.equal(this.hashedCommsId, userInfo.hashedCommsId) && Objects.equal(this.homeGroupId, userInfo.homeGroupId) && Objects.equal(this.aor, userInfo.aor) && this.commsProvisionStatus.equals(userInfo.commsProvisionStatus);
    }

    public String getAor() {
        return this.aor;
    }

    public String getCommsId() {
        return this.commsId;
    }

    @NonNull
    public CommsProvisionStatus getCommsProvisionStatus() {
        return this.commsProvisionStatus;
    }

    public String getHashedCommsId() {
        return this.hashedCommsId;
    }

    public String getHomeGroupId() {
        return this.homeGroupId;
    }

    public int hashCode() {
        return Objects.hashCode(this.commsId, this.hashedCommsId, this.homeGroupId, this.aor, this.commsProvisionStatus);
    }

    public void setAor(String str) {
        this.aor = str;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setCommsProvisionStatus(CommsProvisionStatus commsProvisionStatus) {
        this.commsProvisionStatus = commsProvisionStatus;
    }

    public void setHashedCommsId(String str) {
        this.hashedCommsId = str;
    }

    public void setHomeGroupId(String str) {
        this.homeGroupId = str;
    }
}
