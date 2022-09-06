package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetNodeResponse extends Node {
    private String location;

    @Override // com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetNodeResponse) && compareTo((EditableNode) ((GetNodeResponse) obj)) == 0;
    }

    public String getLocation() {
        return this.location;
    }

    @Override // com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode
    public int hashCode() {
        return (((getLocation() == null ? 0 : getLocation().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setLocation(String str) {
        this.location = str;
    }

    @Override // com.amazon.clouddrive.model.Node, com.amazon.clouddrive.model.EditableNode, java.lang.Comparable
    public int compareTo(EditableNode editableNode) {
        if (editableNode == null) {
            return -1;
        }
        if (editableNode == this) {
            return 0;
        }
        if (!(editableNode instanceof GetNodeResponse)) {
            return 1;
        }
        String location = getLocation();
        String location2 = ((GetNodeResponse) editableNode).getLocation();
        if (location != location2) {
            if (location == null) {
                return -1;
            }
            if (location2 == null) {
                return 1;
            }
            int compareTo = location.compareTo(location2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return super.compareTo(editableNode);
    }
}
