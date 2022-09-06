package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GroupCoverPhoto implements Comparable<GroupCoverPhoto> {
    private CropBox cropBox;
    private String nodeId;
    private String ownerId;
    private String url;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GroupCoverPhoto) && compareTo((GroupCoverPhoto) obj) == 0;
    }

    public CropBox getCropBox() {
        return this.cropBox;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getNodeId() == null ? 0 : getNodeId().hashCode()) + 1 + (getOwnerId() == null ? 0 : getOwnerId().hashCode()) + (getCropBox() == null ? 0 : getCropBox().hashCode());
        if (getUrl() != null) {
            i = getUrl().hashCode();
        }
        return hashCode + i;
    }

    public void setCropBox(CropBox cropBox) {
        this.cropBox = cropBox;
    }

    public void setNodeId(String str) {
        this.nodeId = str;
    }

    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(GroupCoverPhoto groupCoverPhoto) {
        if (groupCoverPhoto == null) {
            return -1;
        }
        if (groupCoverPhoto == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getNodeId(), groupCoverPhoto.getNodeId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getOwnerId(), groupCoverPhoto.getOwnerId());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getCropBox(), groupCoverPhoto.getCropBox());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getUrl(), groupCoverPhoto.getUrl());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }
}
