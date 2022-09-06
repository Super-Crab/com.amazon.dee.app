package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class BaseBatchNodeRequest implements CloudDriveRequest {
    private final String eventKey;
    private final List<String> groupIds;
    private final List<String> nodeIds;
    private final OperationType operationType;

    /* loaded from: classes11.dex */
    public enum OperationType {
        ADD,
        REMOVE
    }

    public BaseBatchNodeRequest(List<String> list, List<String> list2, OperationType operationType, String str) {
        this.groupIds = list;
        this.nodeIds = list2;
        this.operationType = operationType;
        this.eventKey = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseBatchNodeRequest)) {
            return false;
        }
        BaseBatchNodeRequest baseBatchNodeRequest = (BaseBatchNodeRequest) obj;
        if (getGroupIds() == null ? baseBatchNodeRequest.getGroupIds() == null : getGroupIds().equals(baseBatchNodeRequest.getGroupIds())) {
            if (getNodeIds() == null ? baseBatchNodeRequest.getNodeIds() == null : getNodeIds().equals(baseBatchNodeRequest.getNodeIds())) {
                if (getOperationType() == baseBatchNodeRequest.getOperationType()) {
                    if (getEventKey() != null) {
                        if (getEventKey().equals(baseBatchNodeRequest.getEventKey())) {
                            return true;
                        }
                    } else if (baseBatchNodeRequest.getEventKey() == null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getEventKey() {
        return this.eventKey;
    }

    public List<String> getGroupIds() {
        return this.groupIds;
    }

    public List<String> getNodeIds() {
        return this.nodeIds;
    }

    public OperationType getOperationType() {
        return this.operationType;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getOperationType().hashCode() + ((((getGroupIds() != null ? getGroupIds().hashCode() : 0) * 31) + (getNodeIds() != null ? getNodeIds().hashCode() : 0)) * 31)) * 31;
        if (getEventKey() != null) {
            i = getEventKey().hashCode();
        }
        return hashCode + i;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof BaseBatchNodeRequest)) {
            return 1;
        }
        BaseBatchNodeRequest baseBatchNodeRequest = (BaseBatchNodeRequest) cloudDriveRequest;
        int compareCollections = ObjectComparator.compareCollections(getGroupIds(), baseBatchNodeRequest.getGroupIds());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compareCollections2 = ObjectComparator.compareCollections(getNodeIds(), baseBatchNodeRequest.getNodeIds());
        if (compareCollections2 != 0) {
            return compareCollections2;
        }
        int compare = ObjectComparator.compare(getOperationType(), baseBatchNodeRequest.getOperationType());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getEventKey(), baseBatchNodeRequest.getEventKey());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
