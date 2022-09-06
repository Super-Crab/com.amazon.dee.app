package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.Objects;
/* loaded from: classes11.dex */
public class GetReUpdateNodesRequest implements CloudDriveRequest {
    private int limit;
    private String startToken;

    public boolean equals(Object obj) {
        return (obj instanceof GetReUpdateNodesRequest) && compareTo((CloudDriveRequest) ((GetReUpdateNodesRequest) obj)) == 0;
    }

    public int getLimit() {
        return this.limit;
    }

    public String getStartToken() {
        return this.startToken;
    }

    public int hashCode() {
        return Objects.hash(this.startToken, Integer.valueOf(this.limit));
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public void setStartToken(String str) {
        this.startToken = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetReUpdateNodesRequest)) {
            return 1;
        }
        GetReUpdateNodesRequest getReUpdateNodesRequest = (GetReUpdateNodesRequest) cloudDriveRequest;
        int compare = Integer.compare(this.limit, getReUpdateNodesRequest.getLimit());
        return compare != 0 ? compare : ObjectComparator.compare(this.startToken, getReUpdateNodesRequest.getStartToken());
    }
}
