package com.amazon.clouddrive.model;

import java.util.List;
/* loaded from: classes11.dex */
public class GetChangesResponse implements IGetChangesResponse<Node> {
    private String checkpoint;
    private List<Node> nodes;
    private boolean reset;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetChangesResponse) && compareTo((CloudDriveResponse) ((GetChangesResponse) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.model.IGetChangesResponse
    public String getCheckpoint() {
        return this.checkpoint;
    }

    @Override // com.amazon.clouddrive.model.IGetChangesResponse
    public List<Node> getNodes() {
        return this.nodes;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (isReset() ? 1 : 0) + 1 + (getCheckpoint() == null ? 0 : getCheckpoint().hashCode());
        if (getNodes() != null) {
            i = getNodes().hashCode();
        }
        return hashCode + i;
    }

    @Override // com.amazon.clouddrive.model.IGetChangesResponse
    public boolean isReset() {
        return this.reset;
    }

    @Override // com.amazon.clouddrive.model.IGetChangesResponse
    public void setCheckpoint(String str) {
        this.checkpoint = str;
    }

    @Override // com.amazon.clouddrive.model.IGetChangesResponse
    public void setNodes(List<Node> list) {
        this.nodes = list;
    }

    @Override // com.amazon.clouddrive.model.IGetChangesResponse
    public void setReset(boolean z) {
        this.reset = z;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetChangesResponse)) {
            return 1;
        }
        GetChangesResponse getChangesResponse = (GetChangesResponse) cloudDriveResponse;
        if (!isReset() && getChangesResponse.isReset()) {
            return -1;
        }
        if (isReset() && !getChangesResponse.isReset()) {
            return 1;
        }
        String checkpoint = getCheckpoint();
        String checkpoint2 = getChangesResponse.getCheckpoint();
        if (checkpoint != checkpoint2) {
            if (checkpoint == null) {
                return -1;
            }
            if (checkpoint2 == null) {
                return 1;
            }
            int compareTo = checkpoint.compareTo(checkpoint2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        List<Node> nodes = getNodes();
        List<Node> nodes2 = getChangesResponse.getNodes();
        if (nodes != nodes2) {
            if (nodes == null) {
                return -1;
            }
            if (nodes2 == null) {
                return 1;
            }
            if (nodes instanceof Comparable) {
                int compareTo2 = ((Comparable) nodes).compareTo(nodes2);
                if (compareTo2 != 0) {
                    return compareTo2;
                }
            } else if (!nodes.equals(nodes2)) {
                int hashCode = nodes.hashCode();
                int hashCode2 = nodes2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
