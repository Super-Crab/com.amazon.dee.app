package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class BulkAddRemoveChildRequest implements BulkAddRemoveRequest {
    private List<String> nodeIds;
    private String op;
    private String parentId;
    private String resourceVersion;

    public BulkAddRemoveChildRequest(String str, List<String> list, String str2) {
        this.parentId = str;
        this.nodeIds = list;
        this.op = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BulkAddRemoveChildRequest) && compareTo((CloudDriveRequest) ((BulkAddRemoveChildRequest) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public List<String> getNodeIds() {
        return this.nodeIds;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public String getOp() {
        return this.op;
    }

    public String getParentId() {
        return this.parentId;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public String getResourceVersion() {
        return this.resourceVersion;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getParentId() == null ? 0 : getParentId().hashCode()) + 1 + (getNodeIds() == null ? 0 : getNodeIds().hashCode()) + (getOp() == null ? 0 : getOp().hashCode());
        if (getResourceVersion() != null) {
            i = getResourceVersion().hashCode();
        }
        return hashCode + i;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public void setNodeIds(List<String> list) {
        this.nodeIds = list;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public void setOp(String str) {
        this.op = str;
    }

    public void setParentId(String str) {
        this.parentId = str;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public void setResourceVersion(String str) {
        this.resourceVersion = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof BulkAddRemoveChildRequest)) {
            return 1;
        }
        BulkAddRemoveChildRequest bulkAddRemoveChildRequest = (BulkAddRemoveChildRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getParentId(), bulkAddRemoveChildRequest.getParentId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getNodeIds(), bulkAddRemoveChildRequest.getNodeIds());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getOp(), bulkAddRemoveChildRequest.getOp());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getResourceVersion(), bulkAddRemoveChildRequest.getResourceVersion());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }
}
