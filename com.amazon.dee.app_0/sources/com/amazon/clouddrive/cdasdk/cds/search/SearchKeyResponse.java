package com.amazon.clouddrive.cdasdk.cds.search;

import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class SearchKeyResponse extends PaginatedCloudDriveResponse {
    @JsonProperty("aggregations")
    private Map<String, List<Match>> aggregations;
    @JsonProperty("data")
    private List<NodeInfo> data;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof SearchKeyResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchKeyResponse)) {
            return false;
        }
        SearchKeyResponse searchKeyResponse = (SearchKeyResponse) obj;
        if (!searchKeyResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<NodeInfo> data = getData();
        List<NodeInfo> data2 = searchKeyResponse.getData();
        if (data != null ? !data.equals(data2) : data2 != null) {
            return false;
        }
        Map<String, List<Match>> aggregations = getAggregations();
        Map<String, List<Match>> aggregations2 = searchKeyResponse.getAggregations();
        return aggregations != null ? aggregations.equals(aggregations2) : aggregations2 == null;
    }

    public Map<String, List<Match>> getAggregations() {
        return this.aggregations;
    }

    public List<NodeInfo> getData() {
        return this.data;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        List<NodeInfo> data = getData();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (data == null ? 43 : data.hashCode());
        Map<String, List<Match>> aggregations = getAggregations();
        int i2 = hashCode2 * 59;
        if (aggregations != null) {
            i = aggregations.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("aggregations")
    public void setAggregations(Map<String, List<Match>> map) {
        this.aggregations = map;
    }

    @JsonProperty("data")
    public void setData(List<NodeInfo> list) {
        this.data = list;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveResponse, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchKeyResponse(data=");
        outline107.append(getData());
        outline107.append(", aggregations=");
        outline107.append(getAggregations());
        outline107.append(")");
        return outline107.toString();
    }
}
