package com.amazon.clouddrive.cdasdk.cds.node;

import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class ListNodeResponse extends PaginatedCloudDriveResponse {
    @JsonProperty("data")
    private List<NodeInfo> data;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof ListNodeResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListNodeResponse)) {
            return false;
        }
        ListNodeResponse listNodeResponse = (ListNodeResponse) obj;
        if (!listNodeResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<NodeInfo> data = getData();
        List<NodeInfo> data2 = listNodeResponse.getData();
        return data != null ? data.equals(data2) : data2 == null;
    }

    public List<NodeInfo> getData() {
        return this.data;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        List<NodeInfo> data = getData();
        return (hashCode * 59) + (data == null ? 43 : data.hashCode());
    }

    @JsonProperty("data")
    public void setData(List<NodeInfo> list) {
        this.data = list;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListNodeResponse(data=");
        outline107.append(getData());
        outline107.append(")");
        return outline107.toString();
    }
}
