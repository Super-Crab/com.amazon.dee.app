package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ClientProperties {
    @JsonProperty("dateCreated")
    private ISO8601 dateCreated;
    @JsonProperty("dateUpdated")
    private ISO8601 dateUpdated;

    protected boolean canEqual(Object obj) {
        return obj instanceof ClientProperties;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientProperties)) {
            return false;
        }
        ClientProperties clientProperties = (ClientProperties) obj;
        if (!clientProperties.canEqual(this)) {
            return false;
        }
        ISO8601 dateCreated = getDateCreated();
        ISO8601 dateCreated2 = clientProperties.getDateCreated();
        if (dateCreated != null ? !dateCreated.equals(dateCreated2) : dateCreated2 != null) {
            return false;
        }
        ISO8601 dateUpdated = getDateUpdated();
        ISO8601 dateUpdated2 = clientProperties.getDateUpdated();
        return dateUpdated != null ? dateUpdated.equals(dateUpdated2) : dateUpdated2 == null;
    }

    public ISO8601 getDateCreated() {
        return this.dateCreated;
    }

    public ISO8601 getDateUpdated() {
        return this.dateUpdated;
    }

    public int hashCode() {
        ISO8601 dateCreated = getDateCreated();
        int i = 43;
        int hashCode = dateCreated == null ? 43 : dateCreated.hashCode();
        ISO8601 dateUpdated = getDateUpdated();
        int i2 = (hashCode + 59) * 59;
        if (dateUpdated != null) {
            i = dateUpdated.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("dateCreated")
    public void setDateCreated(ISO8601 iso8601) {
        this.dateCreated = iso8601;
    }

    @JsonProperty("dateUpdated")
    public void setDateUpdated(ISO8601 iso8601) {
        this.dateUpdated = iso8601;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClientProperties(dateCreated=");
        outline107.append(getDateCreated());
        outline107.append(", dateUpdated=");
        outline107.append(getDateUpdated());
        outline107.append(")");
        return outline107.toString();
    }
}
