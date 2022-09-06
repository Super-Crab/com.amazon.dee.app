package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class GroupNode {
    private List<String> collectionIds;
    private NodeExtended nodeExtended;
    private ReactionSummary reactionSummary;

    protected boolean canEqual(Object obj) {
        return obj instanceof GroupNode;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupNode)) {
            return false;
        }
        GroupNode groupNode = (GroupNode) obj;
        if (!groupNode.canEqual(this)) {
            return false;
        }
        NodeExtended nodeExtended = getNodeExtended();
        NodeExtended nodeExtended2 = groupNode.getNodeExtended();
        if (nodeExtended != null ? !nodeExtended.equals(nodeExtended2) : nodeExtended2 != null) {
            return false;
        }
        ReactionSummary reactionSummary = getReactionSummary();
        ReactionSummary reactionSummary2 = groupNode.getReactionSummary();
        if (reactionSummary != null ? !reactionSummary.equals(reactionSummary2) : reactionSummary2 != null) {
            return false;
        }
        List<String> collectionIds = getCollectionIds();
        List<String> collectionIds2 = groupNode.getCollectionIds();
        return collectionIds != null ? collectionIds.equals(collectionIds2) : collectionIds2 == null;
    }

    public List<String> getCollectionIds() {
        return this.collectionIds;
    }

    public NodeExtended getNodeExtended() {
        return this.nodeExtended;
    }

    public ReactionSummary getReactionSummary() {
        return this.reactionSummary;
    }

    public int hashCode() {
        NodeExtended nodeExtended = getNodeExtended();
        int i = 43;
        int hashCode = nodeExtended == null ? 43 : nodeExtended.hashCode();
        ReactionSummary reactionSummary = getReactionSummary();
        int hashCode2 = ((hashCode + 59) * 59) + (reactionSummary == null ? 43 : reactionSummary.hashCode());
        List<String> collectionIds = getCollectionIds();
        int i2 = hashCode2 * 59;
        if (collectionIds != null) {
            i = collectionIds.hashCode();
        }
        return i2 + i;
    }

    public void setCollectionIds(List<String> list) {
        this.collectionIds = list;
    }

    public void setNodeExtended(NodeExtended nodeExtended) {
        this.nodeExtended = nodeExtended;
    }

    public void setReactionSummary(ReactionSummary reactionSummary) {
        this.reactionSummary = reactionSummary;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupNode(nodeExtended=");
        outline107.append(getNodeExtended());
        outline107.append(", reactionSummary=");
        outline107.append(getReactionSummary());
        outline107.append(", collectionIds=");
        outline107.append(getCollectionIds());
        outline107.append(")");
        return outline107.toString();
    }
}
