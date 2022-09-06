package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class PaginatedCloudDriveResponse extends CloudDriveResponse {
    @JsonProperty("count")
    private Long count;
    @JsonProperty("nextToken")
    private String nextToken;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof PaginatedCloudDriveResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PaginatedCloudDriveResponse)) {
            return false;
        }
        PaginatedCloudDriveResponse paginatedCloudDriveResponse = (PaginatedCloudDriveResponse) obj;
        if (!paginatedCloudDriveResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Long count = getCount();
        Long count2 = paginatedCloudDriveResponse.getCount();
        if (count != null ? !count.equals(count2) : count2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = paginatedCloudDriveResponse.getNextToken();
        return nextToken != null ? nextToken.equals(nextToken2) : nextToken2 == null;
    }

    public Long getCount() {
        return this.count;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        Long count = getCount();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (count == null ? 43 : count.hashCode());
        String nextToken = getNextToken();
        int i2 = hashCode2 * 59;
        if (nextToken != null) {
            i = nextToken.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("count")
    public void setCount(Long l) {
        this.count = l;
    }

    @JsonProperty("nextToken")
    public void setNextToken(String str) {
        this.nextToken = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PaginatedCloudDriveResponse(count=");
        outline107.append(getCount());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(")");
        return outline107.toString();
    }
}
