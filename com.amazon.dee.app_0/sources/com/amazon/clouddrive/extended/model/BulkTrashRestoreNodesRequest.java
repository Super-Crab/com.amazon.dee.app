package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class BulkTrashRestoreNodesRequest implements BulkAddRemoveRequest {
    private String dedupeContext;
    private List<String> nodeIds;
    private String op;
    private Boolean recurse;
    private String resourceVersion;

    public BulkTrashRestoreNodesRequest(List<String> list, String str) {
        this.nodeIds = list;
        this.op = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BulkTrashRestoreNodesRequest) && compareTo((CloudDriveRequest) ((BulkTrashRestoreNodesRequest) obj)) == 0;
    }

    public String getDedupeContext() {
        return this.dedupeContext;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public List<String> getNodeIds() {
        return this.nodeIds;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public String getOp() {
        return this.op;
    }

    public Boolean getRecurse() {
        return this.recurse;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public String getResourceVersion() {
        return this.resourceVersion;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getNodeIds() == null ? 0 : getNodeIds().hashCode()) + 1 + (getOp() == null ? 0 : getOp().hashCode()) + (getRecurse() == null ? 0 : getRecurse().hashCode()) + (getResourceVersion() == null ? 0 : getResourceVersion().hashCode());
        if (getDedupeContext() != null) {
            i = getDedupeContext().hashCode();
        }
        return hashCode + i;
    }

    public void setDedupeContext(String str) {
        this.dedupeContext = str;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public void setNodeIds(List<String> list) {
        this.nodeIds = list;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public void setOp(String str) {
        this.op = str;
    }

    public void setRecurse(Boolean bool) {
        this.recurse = bool;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkAddRemoveRequest
    public void setResourceVersion(String str) {
        this.resourceVersion = str;
    }

    public BulkTrashRestoreNodesRequest withDedupeContext(String str) {
        setDedupeContext(str);
        return this;
    }

    public BulkTrashRestoreNodesRequest withRecurse(Boolean bool) {
        setRecurse(bool);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof BulkTrashRestoreNodesRequest)) {
            return 1;
        }
        BulkTrashRestoreNodesRequest bulkTrashRestoreNodesRequest = (BulkTrashRestoreNodesRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getNodeIds(), bulkTrashRestoreNodesRequest.getNodeIds());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getOp(), bulkTrashRestoreNodesRequest.getOp());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getRecurse(), bulkTrashRestoreNodesRequest.getRecurse());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getResourceVersion(), bulkTrashRestoreNodesRequest.getResourceVersion());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getDedupeContext(), bulkTrashRestoreNodesRequest.getDedupeContext());
        if (compare5 == 0) {
            return 0;
        }
        return compare5;
    }
}
