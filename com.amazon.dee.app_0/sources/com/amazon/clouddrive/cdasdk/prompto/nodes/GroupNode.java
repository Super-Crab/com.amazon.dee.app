package com.amazon.clouddrive.cdasdk.prompto.nodes;

import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.prompto.reactions.ReactionSummaryResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class GroupNode {
    @JsonProperty("addedDate")
    private String addedDate;
    @JsonProperty("collectionIds")
    private List<String> collectionIds;
    @JsonProperty("node")
    private NodeInfo node;
    @JsonProperty("reactionSummary")
    private ReactionSummaryResponse reactionSummary;

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
        NodeInfo node = getNode();
        NodeInfo node2 = groupNode.getNode();
        if (node != null ? !node.equals(node2) : node2 != null) {
            return false;
        }
        ReactionSummaryResponse reactionSummary = getReactionSummary();
        ReactionSummaryResponse reactionSummary2 = groupNode.getReactionSummary();
        if (reactionSummary != null ? !reactionSummary.equals(reactionSummary2) : reactionSummary2 != null) {
            return false;
        }
        List<String> collectionIds = getCollectionIds();
        List<String> collectionIds2 = groupNode.getCollectionIds();
        if (collectionIds != null ? !collectionIds.equals(collectionIds2) : collectionIds2 != null) {
            return false;
        }
        String addedDate = getAddedDate();
        String addedDate2 = groupNode.getAddedDate();
        return addedDate != null ? addedDate.equals(addedDate2) : addedDate2 == null;
    }

    public String getAddedDate() {
        return this.addedDate;
    }

    public List<String> getCollectionIds() {
        return this.collectionIds;
    }

    public NodeInfo getNode() {
        return this.node;
    }

    public ReactionSummaryResponse getReactionSummary() {
        return this.reactionSummary;
    }

    public int hashCode() {
        NodeInfo node = getNode();
        int i = 43;
        int hashCode = node == null ? 43 : node.hashCode();
        ReactionSummaryResponse reactionSummary = getReactionSummary();
        int hashCode2 = ((hashCode + 59) * 59) + (reactionSummary == null ? 43 : reactionSummary.hashCode());
        List<String> collectionIds = getCollectionIds();
        int hashCode3 = (hashCode2 * 59) + (collectionIds == null ? 43 : collectionIds.hashCode());
        String addedDate = getAddedDate();
        int i2 = hashCode3 * 59;
        if (addedDate != null) {
            i = addedDate.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("addedDate")
    public void setAddedDate(String str) {
        this.addedDate = str;
    }

    @JsonProperty("collectionIds")
    public void setCollectionIds(List<String> list) {
        this.collectionIds = list;
    }

    @JsonProperty("node")
    public void setNode(NodeInfo nodeInfo) {
        this.node = nodeInfo;
    }

    @JsonProperty("reactionSummary")
    public void setReactionSummary(ReactionSummaryResponse reactionSummaryResponse) {
        this.reactionSummary = reactionSummaryResponse;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupNode(node=");
        outline107.append(getNode());
        outline107.append(", reactionSummary=");
        outline107.append(getReactionSummary());
        outline107.append(", collectionIds=");
        outline107.append(getCollectionIds());
        outline107.append(", addedDate=");
        outline107.append(getAddedDate());
        outline107.append(")");
        return outline107.toString();
    }
}
