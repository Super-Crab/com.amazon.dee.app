package com.amazon.clouddrive.cdasdk.dps.collections;

import com.amazon.clouddrive.cdasdk.dps.common.ExtendedNodeInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class CollectionContentsResponse {
    @JsonProperty("collectionIds")
    private List<String> collectionIds;
    @JsonProperty("nextToken")
    private String nextToken;
    @JsonProperty("nodes")
    private List<ExtendedNodeInfo> nodes;
    @JsonProperty("previousToken")
    private String previousToken;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionContentsResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionContentsResponse)) {
            return false;
        }
        CollectionContentsResponse collectionContentsResponse = (CollectionContentsResponse) obj;
        if (!collectionContentsResponse.canEqual(this)) {
            return false;
        }
        List<String> collectionIds = getCollectionIds();
        List<String> collectionIds2 = collectionContentsResponse.getCollectionIds();
        if (collectionIds != null ? !collectionIds.equals(collectionIds2) : collectionIds2 != null) {
            return false;
        }
        List<ExtendedNodeInfo> nodes = getNodes();
        List<ExtendedNodeInfo> nodes2 = collectionContentsResponse.getNodes();
        if (nodes != null ? !nodes.equals(nodes2) : nodes2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = collectionContentsResponse.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        String previousToken = getPreviousToken();
        String previousToken2 = collectionContentsResponse.getPreviousToken();
        return previousToken != null ? previousToken.equals(previousToken2) : previousToken2 == null;
    }

    public List<String> getCollectionIds() {
        return this.collectionIds;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<ExtendedNodeInfo> getNodes() {
        return this.nodes;
    }

    public String getPreviousToken() {
        return this.previousToken;
    }

    public int hashCode() {
        List<String> collectionIds = getCollectionIds();
        int i = 43;
        int hashCode = collectionIds == null ? 43 : collectionIds.hashCode();
        List<ExtendedNodeInfo> nodes = getNodes();
        int hashCode2 = ((hashCode + 59) * 59) + (nodes == null ? 43 : nodes.hashCode());
        String nextToken = getNextToken();
        int hashCode3 = (hashCode2 * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        String previousToken = getPreviousToken();
        int i2 = hashCode3 * 59;
        if (previousToken != null) {
            i = previousToken.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("collectionIds")
    public void setCollectionIds(List<String> list) {
        this.collectionIds = list;
    }

    @JsonProperty("nextToken")
    public void setNextToken(String str) {
        this.nextToken = str;
    }

    @JsonProperty("nodes")
    public void setNodes(List<ExtendedNodeInfo> list) {
        this.nodes = list;
    }

    @JsonProperty("previousToken")
    public void setPreviousToken(String str) {
        this.previousToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CollectionContentsResponse(collectionIds=");
        outline107.append(getCollectionIds());
        outline107.append(", nodes=");
        outline107.append(getNodes());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(", previousToken=");
        outline107.append(getPreviousToken());
        outline107.append(")");
        return outline107.toString();
    }
}
