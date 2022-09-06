package com.amazon.clouddrive.cdasdk.cds.node;

import com.amazon.clouddrive.cdasdk.cds.common.NodeRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class UpdateNodeRequest extends NodeRequest {
    @JsonProperty("contentETag")
    private String contentETag;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeRequest, com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    protected boolean canEqual(Object obj) {
        return obj instanceof UpdateNodeRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeRequest, com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateNodeRequest)) {
            return false;
        }
        UpdateNodeRequest updateNodeRequest = (UpdateNodeRequest) obj;
        if (!updateNodeRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String contentETag = getContentETag();
        String contentETag2 = updateNodeRequest.getContentETag();
        return contentETag != null ? contentETag.equals(contentETag2) : contentETag2 == null;
    }

    public String getContentETag() {
        return this.contentETag;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeRequest, com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public int hashCode() {
        int hashCode = super.hashCode();
        String contentETag = getContentETag();
        return (hashCode * 59) + (contentETag == null ? 43 : contentETag.hashCode());
    }

    @JsonProperty("contentETag")
    public void setContentETag(String str) {
        this.contentETag = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeRequest, com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpdateNodeRequest(contentETag=");
        outline107.append(getContentETag());
        outline107.append(")");
        return outline107.toString();
    }
}
