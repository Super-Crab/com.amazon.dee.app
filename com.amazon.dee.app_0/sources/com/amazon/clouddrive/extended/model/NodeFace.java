package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class NodeFace implements Comparable<NodeFace> {
    private FaceBoundingBox boundingBox;
    private String faceId;
    private String nodeId;
    private String personId;
    private String personName;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof NodeFace) && compareTo((NodeFace) obj) == 0;
    }

    public FaceBoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    public String getFaceId() {
        return this.faceId;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public String getPersonId() {
        return this.personId;
    }

    public String getPersonName() {
        return this.personName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getFaceId() == null ? 0 : getFaceId().hashCode()) + 1 + (getNodeId() == null ? 0 : getNodeId().hashCode()) + (getPersonId() == null ? 0 : getPersonId().hashCode()) + (getPersonName() == null ? 0 : getPersonName().hashCode());
        if (getBoundingBox() != null) {
            i = getBoundingBox().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setBoundingBox(FaceBoundingBox faceBoundingBox) {
        this.boundingBox = faceBoundingBox;
    }

    public void setFaceId(String str) {
        this.faceId = str;
    }

    public void setNodeId(String str) {
        this.nodeId = str;
    }

    public void setPersonId(String str) {
        this.personId = str;
    }

    public void setPersonName(String str) {
        this.personName = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(NodeFace nodeFace) {
        if (nodeFace == null) {
            return -1;
        }
        if (nodeFace == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getFaceId(), nodeFace.getFaceId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getNodeId(), nodeFace.getNodeId());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getPersonId(), nodeFace.getPersonId());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getPersonName(), nodeFace.getPersonName());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getBoundingBox(), nodeFace.getBoundingBox());
        if (compare5 == 0) {
            return 0;
        }
        return compare5;
    }
}
