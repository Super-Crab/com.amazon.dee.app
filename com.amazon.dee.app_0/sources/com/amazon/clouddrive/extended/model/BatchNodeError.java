package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class BatchNodeError implements Comparable<BatchNodeError> {
    private String nodeId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BatchNodeError) && compareTo((BatchNodeError) obj) == 0;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public int hashCode() {
        return (getNodeId() == null ? 0 : getNodeId().hashCode()) + 1;
    }

    public void setNodeId(String str) {
        this.nodeId = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(BatchNodeError batchNodeError) {
        int compare;
        if (batchNodeError == null) {
            return -1;
        }
        if (batchNodeError != this && (compare = ObjectComparator.compare(getNodeId(), batchNodeError.getNodeId())) != 0) {
            return compare;
        }
        return 0;
    }
}
