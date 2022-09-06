package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class OverwriteFileResponse extends Node {
    @Override // com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof OverwriteFileResponse) && compareTo((EditableNode) ((OverwriteFileResponse) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode, java.lang.Comparable
    public int compareTo(EditableNode editableNode) {
        if (editableNode == null) {
            return -1;
        }
        if (editableNode == this) {
            return 0;
        }
        if (editableNode instanceof OverwriteFileResponse) {
            return super.compareTo(editableNode);
        }
        return 1;
    }
}
