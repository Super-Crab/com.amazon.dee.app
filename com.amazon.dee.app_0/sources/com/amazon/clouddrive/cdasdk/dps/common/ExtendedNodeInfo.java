package com.amazon.clouddrive.cdasdk.dps.common;

import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ExtendedNodeInfo extends NodeInfo {
    @JsonProperty("sourceCollectionId")
    private String sourceCollectionId;
    @JsonProperty("sourceCollectionTitle")
    private String sourceCollectionTitle;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    protected boolean canEqual(Object obj) {
        return obj instanceof ExtendedNodeInfo;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExtendedNodeInfo)) {
            return false;
        }
        ExtendedNodeInfo extendedNodeInfo = (ExtendedNodeInfo) obj;
        if (!extendedNodeInfo.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String sourceCollectionTitle = getSourceCollectionTitle();
        String sourceCollectionTitle2 = extendedNodeInfo.getSourceCollectionTitle();
        if (sourceCollectionTitle != null ? !sourceCollectionTitle.equals(sourceCollectionTitle2) : sourceCollectionTitle2 != null) {
            return false;
        }
        String sourceCollectionId = getSourceCollectionId();
        String sourceCollectionId2 = extendedNodeInfo.getSourceCollectionId();
        return sourceCollectionId != null ? sourceCollectionId.equals(sourceCollectionId2) : sourceCollectionId2 == null;
    }

    public String getSourceCollectionId() {
        return this.sourceCollectionId;
    }

    public String getSourceCollectionTitle() {
        return this.sourceCollectionTitle;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public int hashCode() {
        int hashCode = super.hashCode();
        String sourceCollectionTitle = getSourceCollectionTitle();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (sourceCollectionTitle == null ? 43 : sourceCollectionTitle.hashCode());
        String sourceCollectionId = getSourceCollectionId();
        int i2 = hashCode2 * 59;
        if (sourceCollectionId != null) {
            i = sourceCollectionId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("sourceCollectionId")
    public void setSourceCollectionId(String str) {
        this.sourceCollectionId = str;
    }

    @JsonProperty("sourceCollectionTitle")
    public void setSourceCollectionTitle(String str) {
        this.sourceCollectionTitle = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ExtendedNodeInfo(sourceCollectionTitle=");
        outline107.append(getSourceCollectionTitle());
        outline107.append(", sourceCollectionId=");
        outline107.append(getSourceCollectionId());
        outline107.append(")");
        return outline107.toString();
    }
}
