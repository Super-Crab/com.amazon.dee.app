package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class FacePair {
    private String mClusterId;
    private Face mFace;
    private String mFaceId;
    private String mNodeId;
    private String mNodeOwnerId;
    private Long mOriginalHeight;
    private Long mOriginalWidth;
    private String mTemplink;

    public int compareTo(FacePair facePair) {
        if (facePair == null) {
            return -1;
        }
        if (facePair == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getFace(), facePair.getFace());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getClusterId(), facePair.getClusterId());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getFaceId(), facePair.getFaceId());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getTemplink(), facePair.getTemplink());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getNodeId(), facePair.getNodeId());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getNodeOwnerId(), facePair.getNodeOwnerId());
        if (compare6 != 0) {
            return compare6;
        }
        int compare7 = ObjectComparator.compare(getOriginalHeight(), facePair.getOriginalHeight());
        if (compare7 != 0) {
            return compare7;
        }
        int compare8 = ObjectComparator.compare(getOriginalWidth(), facePair.getOriginalWidth());
        if (compare8 == 0) {
            return 0;
        }
        return compare8;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof FacePair) && compareTo((FacePair) obj) == 0;
    }

    public String getClusterId() {
        return this.mClusterId;
    }

    public Face getFace() {
        return this.mFace;
    }

    public String getFaceId() {
        return this.mFaceId;
    }

    public String getNodeId() {
        return this.mNodeId;
    }

    public String getNodeOwnerId() {
        return this.mNodeOwnerId;
    }

    public Long getOriginalHeight() {
        return this.mOriginalHeight;
    }

    public Long getOriginalWidth() {
        return this.mOriginalWidth;
    }

    public String getTemplink() {
        return this.mTemplink;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getFace() == null ? 0 : getFace().hashCode()) + 1 + (getClusterId() == null ? 0 : getClusterId().hashCode()) + (getFaceId() == null ? 0 : getFaceId().hashCode()) + (getTemplink() == null ? 0 : getTemplink().hashCode()) + (getNodeId() == null ? 0 : getNodeId().hashCode()) + (getNodeOwnerId() == null ? 0 : getNodeOwnerId().hashCode()) + (getOriginalHeight() == null ? 0 : getOriginalHeight().hashCode());
        if (getOriginalWidth() != null) {
            i = getOriginalWidth().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setClusterId(String str) {
        this.mClusterId = str;
    }

    public void setFace(Face face) {
        this.mFace = face;
    }

    public void setFaceId(String str) {
        this.mFaceId = str;
    }

    public void setNodeId(String str) {
        this.mNodeId = str;
    }

    public void setNodeOwnerId(String str) {
        this.mNodeOwnerId = str;
    }

    public void setOriginalHeight(Long l) {
        this.mOriginalHeight = l;
    }

    public void setOriginalWidth(Long l) {
        this.mOriginalWidth = l;
    }

    public void setTemplink(String str) {
        this.mTemplink = str;
    }
}
