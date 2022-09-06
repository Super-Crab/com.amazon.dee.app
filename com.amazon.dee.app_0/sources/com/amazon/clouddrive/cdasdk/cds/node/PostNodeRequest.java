package com.amazon.clouddrive.cdasdk.cds.node;

import com.amazon.clouddrive.cdasdk.cds.common.NodeRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class PostNodeRequest extends NodeRequest {
    @JsonProperty("localId")
    private String localId;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeRequest, com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    protected boolean canEqual(Object obj) {
        return obj instanceof PostNodeRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeRequest, com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PostNodeRequest)) {
            return false;
        }
        PostNodeRequest postNodeRequest = (PostNodeRequest) obj;
        if (!postNodeRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String localId = getLocalId();
        String localId2 = postNodeRequest.getLocalId();
        return localId != null ? localId.equals(localId2) : localId2 == null;
    }

    public String getLocalId() {
        return this.localId;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeRequest, com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public int hashCode() {
        int hashCode = super.hashCode();
        String localId = getLocalId();
        return (hashCode * 59) + (localId == null ? 43 : localId.hashCode());
    }

    @JsonProperty("localId")
    public void setLocalId(String str) {
        this.localId = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeRequest, com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PostNodeRequest(localId=");
        outline107.append(getLocalId());
        outline107.append(")");
        return outline107.toString();
    }
}
