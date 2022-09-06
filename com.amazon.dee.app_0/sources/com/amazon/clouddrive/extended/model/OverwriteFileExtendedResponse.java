package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.EditableNode;
/* loaded from: classes11.dex */
public class OverwriteFileExtendedResponse extends NodeExtended {
    @Override // com.amazon.clouddrive.extended.model.NodeExtended, com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof OverwriteFileExtendedResponse) && compareTo((EditableNode) ((OverwriteFileExtendedResponse) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.extended.model.NodeExtended, com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.amazon.clouddrive.extended.model.NodeExtended, com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode, java.lang.Comparable
    public int compareTo(EditableNode editableNode) {
        if (editableNode == null) {
            return -1;
        }
        if (editableNode == this) {
            return 0;
        }
        if (editableNode instanceof OverwriteFileExtendedResponse) {
            return super.compareTo(editableNode);
        }
        return 1;
    }
}
