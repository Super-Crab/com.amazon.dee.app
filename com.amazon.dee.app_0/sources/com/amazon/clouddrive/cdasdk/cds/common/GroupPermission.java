package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class GroupPermission {
    @JsonProperty("albumIds")
    private List<String> albumIds;
    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("lastModifiedTime")
    private ISO8601 lastModifiedTime;

    protected boolean canEqual(Object obj) {
        return obj instanceof GroupPermission;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupPermission)) {
            return false;
        }
        GroupPermission groupPermission = (GroupPermission) obj;
        if (!groupPermission.canEqual(this)) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = groupPermission.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        List<String> albumIds = getAlbumIds();
        List<String> albumIds2 = groupPermission.getAlbumIds();
        if (albumIds != null ? !albumIds.equals(albumIds2) : albumIds2 != null) {
            return false;
        }
        ISO8601 lastModifiedTime = getLastModifiedTime();
        ISO8601 lastModifiedTime2 = groupPermission.getLastModifiedTime();
        return lastModifiedTime != null ? lastModifiedTime.equals(lastModifiedTime2) : lastModifiedTime2 == null;
    }

    public List<String> getAlbumIds() {
        return this.albumIds;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public ISO8601 getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public int hashCode() {
        String groupId = getGroupId();
        int i = 43;
        int hashCode = groupId == null ? 43 : groupId.hashCode();
        List<String> albumIds = getAlbumIds();
        int hashCode2 = ((hashCode + 59) * 59) + (albumIds == null ? 43 : albumIds.hashCode());
        ISO8601 lastModifiedTime = getLastModifiedTime();
        int i2 = hashCode2 * 59;
        if (lastModifiedTime != null) {
            i = lastModifiedTime.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("albumIds")
    public void setAlbumIds(List<String> list) {
        this.albumIds = list;
    }

    @JsonProperty("groupId")
    public void setGroupId(String str) {
        this.groupId = str;
    }

    @JsonProperty("lastModifiedTime")
    public void setLastModifiedTime(ISO8601 iso8601) {
        this.lastModifiedTime = iso8601;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPermission(groupId=");
        outline107.append(getGroupId());
        outline107.append(", albumIds=");
        outline107.append(getAlbumIds());
        outline107.append(", lastModifiedTime=");
        outline107.append(getLastModifiedTime());
        outline107.append(")");
        return outline107.toString();
    }
}
