package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class NodeRequest extends NodeInfo {
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("conflictResolution")
    private ConflictResolution conflictResolution;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("resourceVersion")
    private ResourceVersion resourceVersion;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    protected boolean canEqual(Object obj) {
        return obj instanceof NodeRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NodeRequest)) {
            return false;
        }
        NodeRequest nodeRequest = (NodeRequest) obj;
        if (!nodeRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String customerId = getCustomerId();
        String customerId2 = nodeRequest.getCustomerId();
        if (customerId != null ? !customerId.equals(customerId2) : customerId2 != null) {
            return false;
        }
        String applicationId = getApplicationId();
        String applicationId2 = nodeRequest.getApplicationId();
        if (applicationId != null ? !applicationId.equals(applicationId2) : applicationId2 != null) {
            return false;
        }
        ConflictResolution conflictResolution = getConflictResolution();
        ConflictResolution conflictResolution2 = nodeRequest.getConflictResolution();
        if (conflictResolution != null ? !conflictResolution.equals(conflictResolution2) : conflictResolution2 != null) {
            return false;
        }
        ResourceVersion resourceVersion = getResourceVersion();
        ResourceVersion resourceVersion2 = nodeRequest.getResourceVersion();
        return resourceVersion != null ? resourceVersion.equals(resourceVersion2) : resourceVersion2 == null;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public ConflictResolution getConflictResolution() {
        return this.conflictResolution;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public ResourceVersion getResourceVersion() {
        return this.resourceVersion;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public int hashCode() {
        int hashCode = super.hashCode();
        String customerId = getCustomerId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (customerId == null ? 43 : customerId.hashCode());
        String applicationId = getApplicationId();
        int hashCode3 = (hashCode2 * 59) + (applicationId == null ? 43 : applicationId.hashCode());
        ConflictResolution conflictResolution = getConflictResolution();
        int hashCode4 = (hashCode3 * 59) + (conflictResolution == null ? 43 : conflictResolution.hashCode());
        ResourceVersion resourceVersion = getResourceVersion();
        int i2 = hashCode4 * 59;
        if (resourceVersion != null) {
            i = resourceVersion.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(String str) {
        this.applicationId = str;
    }

    @JsonProperty("conflictResolution")
    public void setConflictResolution(ConflictResolution conflictResolution) {
        this.conflictResolution = conflictResolution;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String str) {
        this.customerId = str;
    }

    @JsonProperty("resourceVersion")
    public void setResourceVersion(ResourceVersion resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.NodeInfo, com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NodeRequest(customerId=");
        outline107.append(getCustomerId());
        outline107.append(", applicationId=");
        outline107.append(getApplicationId());
        outline107.append(", conflictResolution=");
        outline107.append(getConflictResolution());
        outline107.append(", resourceVersion=");
        outline107.append(getResourceVersion());
        outline107.append(")");
        return outline107.toString();
    }
}
