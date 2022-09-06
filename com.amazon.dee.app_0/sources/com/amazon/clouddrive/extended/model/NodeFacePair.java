package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class NodeFacePair implements Comparable<NodeFacePair> {
    private String faceId;
    private String nodeId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof NodeFacePair) && compareTo((NodeFacePair) obj) == 0;
    }

    public String getFaceId() {
        return this.faceId;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getFaceId() == null ? 0 : getFaceId().hashCode()) + 1;
        if (getNodeId() != null) {
            i = getNodeId().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setFaceId(String str) {
        this.faceId = str;
    }

    public void setNodeId(String str) {
        this.nodeId = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(NodeFacePair nodeFacePair) {
        if (nodeFacePair == null) {
            return -1;
        }
        if (nodeFacePair == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getFaceId(), nodeFacePair.getFaceId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getNodeId(), nodeFacePair.getNodeId());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
