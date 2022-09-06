package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.EditableNode;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class RestoreNodeFromTrashExtendedResponse extends NodeExtended {
    private String mLocation;

    @Override // com.amazon.clouddrive.extended.model.NodeExtended, com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RestoreNodeFromTrashExtendedResponse) && compareTo((EditableNode) ((RestoreNodeFromTrashExtendedResponse) obj)) == 0;
    }

    public String getLocation() {
        return this.mLocation;
    }

    @Override // com.amazon.clouddrive.extended.model.NodeExtended, com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public int hashCode() {
        return (((getLocation() == null ? 0 : getLocation().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setLocation(String str) {
        this.mLocation = str;
    }

    @Override // com.amazon.clouddrive.extended.model.NodeExtended, com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode, java.lang.Comparable
    public int compareTo(EditableNode editableNode) {
        if (editableNode == null) {
            return -1;
        }
        if (editableNode == this) {
            return 0;
        }
        if (!(editableNode instanceof RestoreNodeFromTrashExtendedResponse)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getLocation(), ((RestoreNodeFromTrashExtendedResponse) editableNode).getLocation());
        return compare != 0 ? compare : super.compareTo(editableNode);
    }
}
