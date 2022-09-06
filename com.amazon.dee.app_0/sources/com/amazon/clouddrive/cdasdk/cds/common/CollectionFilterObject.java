package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class CollectionFilterObject {
    @JsonProperty("folderIds")
    private List<String> folderIds;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionFilterObject;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionFilterObject)) {
            return false;
        }
        CollectionFilterObject collectionFilterObject = (CollectionFilterObject) obj;
        if (!collectionFilterObject.canEqual(this)) {
            return false;
        }
        List<String> folderIds = getFolderIds();
        List<String> folderIds2 = collectionFilterObject.getFolderIds();
        return folderIds != null ? folderIds.equals(folderIds2) : folderIds2 == null;
    }

    public List<String> getFolderIds() {
        return this.folderIds;
    }

    public int hashCode() {
        List<String> folderIds = getFolderIds();
        return 59 + (folderIds == null ? 43 : folderIds.hashCode());
    }

    @JsonProperty("folderIds")
    public void setFolderIds(List<String> list) {
        this.folderIds = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CollectionFilterObject(folderIds=");
        outline107.append(getFolderIds());
        outline107.append(")");
        return outline107.toString();
    }
}
