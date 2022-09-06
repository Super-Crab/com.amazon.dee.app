package com.amazon.clouddrive.cdasdk.cds.histogram;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/* loaded from: classes11.dex */
public class GetHistogramResponse extends CloudDriveResponse {
    @JsonProperty("countMap")
    private Map<String, Long> countMap;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof GetHistogramResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetHistogramResponse)) {
            return false;
        }
        GetHistogramResponse getHistogramResponse = (GetHistogramResponse) obj;
        if (!getHistogramResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Map<String, Long> countMap = getCountMap();
        Map<String, Long> countMap2 = getHistogramResponse.getCountMap();
        return countMap != null ? countMap.equals(countMap2) : countMap2 == null;
    }

    public Map<String, Long> getCountMap() {
        return this.countMap;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        Map<String, Long> countMap = getCountMap();
        return (hashCode * 59) + (countMap == null ? 43 : countMap.hashCode());
    }

    @JsonProperty("countMap")
    public void setCountMap(Map<String, Long> map) {
        this.countMap = map;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetHistogramResponse(countMap=");
        outline107.append(getCountMap());
        outline107.append(")");
        return outline107.toString();
    }
}
