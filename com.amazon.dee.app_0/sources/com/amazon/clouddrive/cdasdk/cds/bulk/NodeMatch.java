package com.amazon.clouddrive.cdasdk.cds.bulk;

import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class NodeMatch {
    @JsonProperty("multipleHits")
    private boolean multipleHits;
    @JsonProperty("nodeInfo")
    private NodeInfo nodeInfo;

    protected boolean canEqual(Object obj) {
        return obj instanceof NodeMatch;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NodeMatch)) {
            return false;
        }
        NodeMatch nodeMatch = (NodeMatch) obj;
        if (!nodeMatch.canEqual(this)) {
            return false;
        }
        NodeInfo nodeInfo = getNodeInfo();
        NodeInfo nodeInfo2 = nodeMatch.getNodeInfo();
        if (nodeInfo != null ? !nodeInfo.equals(nodeInfo2) : nodeInfo2 != null) {
            return false;
        }
        return isMultipleHits() == nodeMatch.isMultipleHits();
    }

    public NodeInfo getNodeInfo() {
        return this.nodeInfo;
    }

    public int hashCode() {
        NodeInfo nodeInfo = getNodeInfo();
        return (((nodeInfo == null ? 43 : nodeInfo.hashCode()) + 59) * 59) + (isMultipleHits() ? 79 : 97);
    }

    public boolean isMultipleHits() {
        return this.multipleHits;
    }

    @JsonProperty("multipleHits")
    public void setMultipleHits(boolean z) {
        this.multipleHits = z;
    }

    @JsonProperty("nodeInfo")
    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NodeMatch(nodeInfo=");
        outline107.append(getNodeInfo());
        outline107.append(", multipleHits=");
        outline107.append(isMultipleHits());
        outline107.append(")");
        return outline107.toString();
    }
}
