package com.amazon.clouddrive.cdasdk.cds.histogram;

import com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetHistogramRequest extends PaginatedCloudDriveRequest {
    @JsonProperty("bucket")
    private String bucket;
    @JsonProperty("groupBy")
    private String groupBy;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof GetHistogramRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetHistogramRequest)) {
            return false;
        }
        GetHistogramRequest getHistogramRequest = (GetHistogramRequest) obj;
        if (!getHistogramRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String groupBy = getGroupBy();
        String groupBy2 = getHistogramRequest.getGroupBy();
        if (groupBy != null ? !groupBy.equals(groupBy2) : groupBy2 != null) {
            return false;
        }
        String bucket = getBucket();
        String bucket2 = getHistogramRequest.getBucket();
        return bucket != null ? bucket.equals(bucket2) : bucket2 == null;
    }

    public String getBucket() {
        return this.bucket;
    }

    public String getGroupBy() {
        return this.groupBy;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String groupBy = getGroupBy();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (groupBy == null ? 43 : groupBy.hashCode());
        String bucket = getBucket();
        int i2 = hashCode2 * 59;
        if (bucket != null) {
            i = bucket.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("bucket")
    public void setBucket(String str) {
        this.bucket = str;
    }

    @JsonProperty("groupBy")
    public void setGroupBy(String str) {
        this.groupBy = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetHistogramRequest(groupBy=");
        outline107.append(getGroupBy());
        outline107.append(", bucket=");
        outline107.append(getBucket());
        outline107.append(")");
        return outline107.toString();
    }
}
