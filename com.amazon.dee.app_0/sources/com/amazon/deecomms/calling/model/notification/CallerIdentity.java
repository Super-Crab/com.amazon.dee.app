package com.amazon.deecomms.calling.model.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class CallerIdentity {
    @JsonProperty("Id")
    private String commsId;
    @JsonProperty("Uri")
    private String sipUri;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CallerIdentity)) {
            return false;
        }
        CallerIdentity callerIdentity = (CallerIdentity) obj;
        return Objects.equal(getCommsId(), callerIdentity.getCommsId()) && Objects.equal(getSipUri(), callerIdentity.getSipUri());
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getSipUri() {
        return this.sipUri;
    }

    public int hashCode() {
        return Objects.hashCode(getCommsId(), getSipUri());
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setSipUri(String str) {
        this.sipUri = str;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("Id", getCommsId()).add("Uri", getSipUri()).toString();
    }
}
