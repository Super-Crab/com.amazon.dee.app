package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class NodeInfoResponse extends NodeInfo {
    @JsonProperty("location")
    private String location;
    @JsonProperty("statusCode")
    private Integer statusCode;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    protected boolean canEqual(Object obj) {
        return obj instanceof NodeInfoResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NodeInfoResponse)) {
            return false;
        }
        NodeInfoResponse nodeInfoResponse = (NodeInfoResponse) obj;
        if (!nodeInfoResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Integer statusCode = getStatusCode();
        Integer statusCode2 = nodeInfoResponse.getStatusCode();
        if (statusCode != null ? !statusCode.equals(statusCode2) : statusCode2 != null) {
            return false;
        }
        String location = getLocation();
        String location2 = nodeInfoResponse.getLocation();
        return location != null ? location.equals(location2) : location2 == null;
    }

    public String getLocation() {
        return this.location;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public int hashCode() {
        int hashCode = super.hashCode();
        Integer statusCode = getStatusCode();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (statusCode == null ? 43 : statusCode.hashCode());
        String location = getLocation();
        int i2 = hashCode2 * 59;
        if (location != null) {
            i = location.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("location")
    public void setLocation(String str) {
        this.location = str;
    }

    @JsonProperty("statusCode")
    public void setStatusCode(Integer num) {
        this.statusCode = num;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NodeInfoResponse(statusCode=");
        outline107.append(getStatusCode());
        outline107.append(", location=");
        outline107.append(getLocation());
        outline107.append(")");
        return outline107.toString();
    }
}
