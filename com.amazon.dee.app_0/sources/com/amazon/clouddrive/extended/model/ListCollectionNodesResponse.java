package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class ListCollectionNodesResponse implements CloudDriveResponse {
    private Long count;
    private List<GroupNode> groupNodes;
    private String nextToken;

    protected boolean canEqual(Object obj) {
        return obj instanceof ListCollectionNodesResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListCollectionNodesResponse)) {
            return false;
        }
        ListCollectionNodesResponse listCollectionNodesResponse = (ListCollectionNodesResponse) obj;
        if (!listCollectionNodesResponse.canEqual(this)) {
            return false;
        }
        List<GroupNode> groupNodes = getGroupNodes();
        List<GroupNode> groupNodes2 = listCollectionNodesResponse.getGroupNodes();
        if (groupNodes != null ? !groupNodes.equals(groupNodes2) : groupNodes2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listCollectionNodesResponse.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Long count = getCount();
        Long count2 = listCollectionNodesResponse.getCount();
        return count != null ? count.equals(count2) : count2 == null;
    }

    public Long getCount() {
        return this.count;
    }

    public List<GroupNode> getGroupNodes() {
        return this.groupNodes;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        List<GroupNode> groupNodes = getGroupNodes();
        int i = 43;
        int hashCode = groupNodes == null ? 43 : groupNodes.hashCode();
        String nextToken = getNextToken();
        int hashCode2 = ((hashCode + 59) * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        Long count = getCount();
        int i2 = hashCode2 * 59;
        if (count != null) {
            i = count.hashCode();
        }
        return i2 + i;
    }

    public void setCount(Long l) {
        this.count = l;
    }

    public void setGroupNodes(List<GroupNode> list) {
        this.groupNodes = list;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListCollectionNodesResponse(groupNodes=");
        outline107.append(getGroupNodes());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(", count=");
        outline107.append(getCount());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse instanceof ListCollectionNodesResponse) {
            return cloudDriveResponse.hashCode() - hashCode();
        }
        return -1;
    }
}
