package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class GroupPermission implements Comparable<GroupPermission> {
    private List<String> albumIds;
    private String groupId;
    private String lastModifiedTime;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GroupPermission) && compareTo((GroupPermission) obj) == 0;
    }

    public List<String> getAlbumIds() {
        return this.albumIds;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getLastModifiedTime() == null ? 0 : getLastModifiedTime().hashCode()) + 1 + (getGroupId() == null ? 0 : getGroupId().hashCode());
        if (getAlbumIds() != null) {
            i = getAlbumIds().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setAlbumIds(List<String> list) {
        this.albumIds = list;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setLastModifiedTime(String str) {
        this.lastModifiedTime = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(GroupPermission groupPermission) {
        if (groupPermission == null) {
            return -1;
        }
        if (groupPermission == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getLastModifiedTime(), groupPermission.getLastModifiedTime());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getGroupId(), groupPermission.getGroupId());
        if (compare2 != 0) {
            return compare2;
        }
        int compareCollections = ObjectComparator.compareCollections(getAlbumIds(), groupPermission.getAlbumIds());
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
