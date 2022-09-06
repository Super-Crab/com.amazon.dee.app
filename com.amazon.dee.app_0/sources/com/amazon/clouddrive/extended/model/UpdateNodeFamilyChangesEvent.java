package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class UpdateNodeFamilyChangesEvent implements FamilyChangesEvent {
    private NodeExtended mNode;
    private String mOwnerId;

    @Override // com.amazon.clouddrive.extended.model.FamilyChangesEvent
    public String getEvent() {
        return FamilyChangesEventType.UPDATE_NODE;
    }

    public NodeExtended getNode() {
        return this.mNode;
    }

    public String getOwnerId() {
        return this.mOwnerId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getOwnerId() == null ? 0 : getOwnerId().hashCode()) + 1;
        if (getNode() != null) {
            i = getNode().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setNode(NodeExtended nodeExtended) {
        this.mNode = nodeExtended;
    }

    public void setOwnerId(String str) {
        this.mOwnerId = str;
    }

    public UpdateNodeFamilyChangesEvent withNode(NodeExtended nodeExtended) {
        setNode(nodeExtended);
        return this;
    }

    public UpdateNodeFamilyChangesEvent withOwnerId(String str) {
        setOwnerId(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(FamilyChangesEvent familyChangesEvent) {
        if (!(familyChangesEvent instanceof UpdateNodeFamilyChangesEvent)) {
            return 1;
        }
        UpdateNodeFamilyChangesEvent updateNodeFamilyChangesEvent = (UpdateNodeFamilyChangesEvent) familyChangesEvent;
        int compare = ObjectComparator.compare(getOwnerId(), updateNodeFamilyChangesEvent.getOwnerId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getNode(), updateNodeFamilyChangesEvent.getNode());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
