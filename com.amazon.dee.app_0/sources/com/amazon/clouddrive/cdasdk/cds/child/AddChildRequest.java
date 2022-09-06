package com.amazon.clouddrive.cdasdk.cds.child;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.cdasdk.cds.common.ConflictResolution;
import com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class AddChildRequest extends VersionedNodeRequest {
    @JsonProperty("childId")
    private String childId;
    @JsonProperty("childOwnerId")
    private String childOwnerId;
    @JsonProperty("conflictResolution")
    private ConflictResolution conflictResolution;
    @JsonProperty(Message.SERIALIZED_NAME_PARENT_ID)
    private String parentId;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof AddChildRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddChildRequest)) {
            return false;
        }
        AddChildRequest addChildRequest = (AddChildRequest) obj;
        if (!addChildRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String parentId = getParentId();
        String parentId2 = addChildRequest.getParentId();
        if (parentId != null ? !parentId.equals(parentId2) : parentId2 != null) {
            return false;
        }
        String childId = getChildId();
        String childId2 = addChildRequest.getChildId();
        if (childId != null ? !childId.equals(childId2) : childId2 != null) {
            return false;
        }
        String childOwnerId = getChildOwnerId();
        String childOwnerId2 = addChildRequest.getChildOwnerId();
        if (childOwnerId != null ? !childOwnerId.equals(childOwnerId2) : childOwnerId2 != null) {
            return false;
        }
        ConflictResolution conflictResolution = getConflictResolution();
        ConflictResolution conflictResolution2 = addChildRequest.getConflictResolution();
        return conflictResolution != null ? conflictResolution.equals(conflictResolution2) : conflictResolution2 == null;
    }

    public String getChildId() {
        return this.childId;
    }

    public String getChildOwnerId() {
        return this.childOwnerId;
    }

    public ConflictResolution getConflictResolution() {
        return this.conflictResolution;
    }

    public String getParentId() {
        return this.parentId;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String parentId = getParentId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (parentId == null ? 43 : parentId.hashCode());
        String childId = getChildId();
        int hashCode3 = (hashCode2 * 59) + (childId == null ? 43 : childId.hashCode());
        String childOwnerId = getChildOwnerId();
        int hashCode4 = (hashCode3 * 59) + (childOwnerId == null ? 43 : childOwnerId.hashCode());
        ConflictResolution conflictResolution = getConflictResolution();
        int i2 = hashCode4 * 59;
        if (conflictResolution != null) {
            i = conflictResolution.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("childId")
    public void setChildId(String str) {
        this.childId = str;
    }

    @JsonProperty("childOwnerId")
    public void setChildOwnerId(String str) {
        this.childOwnerId = str;
    }

    @JsonProperty("conflictResolution")
    public void setConflictResolution(ConflictResolution conflictResolution) {
        this.conflictResolution = conflictResolution;
    }

    @JsonProperty(Message.SERIALIZED_NAME_PARENT_ID)
    public void setParentId(String str) {
        this.parentId = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AddChildRequest(parentId=");
        outline107.append(getParentId());
        outline107.append(", childId=");
        outline107.append(getChildId());
        outline107.append(", childOwnerId=");
        outline107.append(getChildOwnerId());
        outline107.append(", conflictResolution=");
        outline107.append(getConflictResolution());
        outline107.append(")");
        return outline107.toString();
    }
}
