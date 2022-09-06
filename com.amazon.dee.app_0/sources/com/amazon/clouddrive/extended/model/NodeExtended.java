package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.EditableNode;
import com.amazon.clouddrive.model.Node;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class NodeExtended extends Node implements EditableNodeExtended {
    private List<GroupPermission> groupPermissions;
    private String mFamilyId;
    private String mOwnerId;
    private Boolean mRestricted;
    private String mShareId;
    private String mShareURL;
    private List<NodeOwnerPair> mXAccntParents;
    private Map<String, List<NodeOwnerPair>> mXAccntParentsMap;
    private List<String> thingTags;

    @Override // com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof NodeExtended) && compareTo((EditableNode) ((NodeExtended) obj)) == 0;
    }

    public String getFamilyId() {
        return this.mFamilyId;
    }

    public List<GroupPermission> getGroupPermissions() {
        return this.groupPermissions;
    }

    public String getOwnerId() {
        return this.mOwnerId;
    }

    public String getShareId() {
        return this.mShareId;
    }

    public String getShareURL() {
        return this.mShareURL;
    }

    public List<String> getThingTags() {
        return this.thingTags;
    }

    public List<NodeOwnerPair> getXAccntParents() {
        return this.mXAccntParents;
    }

    public Map<String, List<NodeOwnerPair>> getXAccntParentsMap() {
        return this.mXAccntParentsMap;
    }

    @Override // com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public int hashCode() {
        int i = 0;
        int hashCode = (isRestricted() == null ? 0 : isRestricted().hashCode()) + 1 + (getShareId() == null ? 0 : getShareId().hashCode()) + (getShareURL() == null ? 0 : getShareURL().hashCode()) + (getFamilyId() == null ? 0 : getFamilyId().hashCode()) + (getThingTags() == null ? 0 : getThingTags().hashCode()) + (getOwnerId() == null ? 0 : getOwnerId().hashCode());
        if (getGroupPermissions() != null) {
            i = getGroupPermissions().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    @Override // com.amazon.clouddrive.extended.model.EditableNodeExtended
    public Boolean isRestricted() {
        return this.mRestricted;
    }

    public void setFamilyId(String str) {
        this.mFamilyId = str;
    }

    public void setGroupPermissions(List<GroupPermission> list) {
        this.groupPermissions = list;
    }

    public void setOwnerId(String str) {
        this.mOwnerId = str;
    }

    @Override // com.amazon.clouddrive.extended.model.EditableNodeExtended
    public void setRestricted(Boolean bool) {
        this.mRestricted = bool;
    }

    public void setShareId(String str) {
        this.mShareId = str;
    }

    public void setShareURL(String str) {
        this.mShareURL = str;
    }

    public void setThingTags(List<String> list) {
        this.thingTags = list;
    }

    public void setXAccntParents(List<NodeOwnerPair> list) {
        this.mXAccntParents = list;
    }

    public void setXAccntParentsMap(Map<String, List<NodeOwnerPair>> map) {
        this.mXAccntParentsMap = map;
    }

    @Override // com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode, java.lang.Comparable
    public int compareTo(EditableNode editableNode) {
        if (editableNode == null) {
            return -1;
        }
        if (editableNode == this) {
            return 0;
        }
        if (!(editableNode instanceof NodeExtended)) {
            return 1;
        }
        NodeExtended nodeExtended = (NodeExtended) editableNode;
        int compare = ObjectComparator.compare(isRestricted(), nodeExtended.isRestricted());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getShareId(), nodeExtended.getShareId());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getShareURL(), nodeExtended.getShareURL());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getFamilyId(), nodeExtended.getFamilyId());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getThingTags(), nodeExtended.getThingTags());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getOwnerId(), nodeExtended.getOwnerId());
        if (compare6 != 0) {
            return compare6;
        }
        int compareCollections = ObjectComparator.compareCollections(getGroupPermissions(), nodeExtended.getGroupPermissions());
        return compareCollections != 0 ? compareCollections : super.compareTo(editableNode);
    }
}
