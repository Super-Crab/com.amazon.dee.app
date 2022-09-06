package com.amazon.clouddrive.cdasdk.cds.source;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SourceInfoResponse extends SourceInfo {
    @JsonProperty("statusCode")
    private Integer statusCode;

    @Override // com.amazon.clouddrive.cdasdk.cds.source.SourceInfo, com.amazon.clouddrive.cdasdk.cds.source.BaseSourceInfo
    protected boolean canEqual(Object obj) {
        return obj instanceof SourceInfoResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.source.SourceInfo, com.amazon.clouddrive.cdasdk.cds.source.BaseSourceInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SourceInfoResponse)) {
            return false;
        }
        SourceInfoResponse sourceInfoResponse = (SourceInfoResponse) obj;
        if (!sourceInfoResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Integer statusCode = getStatusCode();
        Integer statusCode2 = sourceInfoResponse.getStatusCode();
        return statusCode != null ? statusCode.equals(statusCode2) : statusCode2 == null;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.source.SourceInfo, com.amazon.clouddrive.cdasdk.cds.source.BaseSourceInfo
    public int hashCode() {
        int hashCode = super.hashCode();
        Integer statusCode = getStatusCode();
        return (hashCode * 59) + (statusCode == null ? 43 : statusCode.hashCode());
    }

    @JsonProperty("statusCode")
    public void setStatusCode(Integer num) {
        this.statusCode = num;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.source.SourceInfo, com.amazon.clouddrive.cdasdk.cds.source.BaseSourceInfo
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SourceInfoResponse(statusCode=");
        outline107.append(getStatusCode());
        outline107.append(")");
        return outline107.toString();
    }
}
