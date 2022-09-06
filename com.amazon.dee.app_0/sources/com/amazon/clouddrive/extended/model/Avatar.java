package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class Avatar implements Comparable<Avatar> {
    private String color;
    private GroupCoverPhoto photo;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Avatar) && compareTo((Avatar) obj) == 0;
    }

    public String getColor() {
        return this.color;
    }

    public GroupCoverPhoto getPhoto() {
        return this.photo;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getColor() == null ? 0 : getColor().hashCode()) + 1;
        if (getPhoto() != null) {
            i = getPhoto().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setColor(String str) {
        this.color = str;
    }

    public void setPhoto(GroupCoverPhoto groupCoverPhoto) {
        this.photo = groupCoverPhoto;
    }

    @Override // java.lang.Comparable
    public int compareTo(Avatar avatar) {
        if (avatar == null) {
            return -1;
        }
        if (avatar == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getColor(), avatar.getColor());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getPhoto(), avatar.getPhoto());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
