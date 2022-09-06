package com.amazon.clouddrive.cdasdk.cds.search;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class AggregationResponse extends CloudDriveResponse {
    @JsonProperty("aggregations")
    private List<AggregationMatch> aggregations;
    @JsonProperty("category")
    private String category;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof AggregationResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AggregationResponse)) {
            return false;
        }
        AggregationResponse aggregationResponse = (AggregationResponse) obj;
        if (!aggregationResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String category = getCategory();
        String category2 = aggregationResponse.getCategory();
        if (category != null ? !category.equals(category2) : category2 != null) {
            return false;
        }
        List<AggregationMatch> aggregations = getAggregations();
        List<AggregationMatch> aggregations2 = aggregationResponse.getAggregations();
        return aggregations != null ? aggregations.equals(aggregations2) : aggregations2 == null;
    }

    public List<AggregationMatch> getAggregations() {
        return this.aggregations;
    }

    public String getCategory() {
        return this.category;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        String category = getCategory();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (category == null ? 43 : category.hashCode());
        List<AggregationMatch> aggregations = getAggregations();
        int i2 = hashCode2 * 59;
        if (aggregations != null) {
            i = aggregations.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("aggregations")
    public void setAggregations(List<AggregationMatch> list) {
        this.aggregations = list;
    }

    @JsonProperty("category")
    public void setCategory(String str) {
        this.category = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AggregationResponse(category=");
        outline107.append(getCategory());
        outline107.append(", aggregations=");
        outline107.append(getAggregations());
        outline107.append(")");
        return outline107.toString();
    }
}
