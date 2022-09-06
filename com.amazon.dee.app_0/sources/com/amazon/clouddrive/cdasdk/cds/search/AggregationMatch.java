package com.amazon.clouddrive.cdasdk.cds.search;

import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class AggregationMatch {
    @JsonProperty("count")
    private Long count;
    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    private String nodeId;
    @JsonProperty("searchData")
    private SearchData searchData;
    @JsonProperty("value")
    private String value;

    protected boolean canEqual(Object obj) {
        return obj instanceof AggregationMatch;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AggregationMatch)) {
            return false;
        }
        AggregationMatch aggregationMatch = (AggregationMatch) obj;
        if (!aggregationMatch.canEqual(this)) {
            return false;
        }
        String value = getValue();
        String value2 = aggregationMatch.getValue();
        if (value != null ? !value.equals(value2) : value2 != null) {
            return false;
        }
        Long count = getCount();
        Long count2 = aggregationMatch.getCount();
        if (count != null ? !count.equals(count2) : count2 != null) {
            return false;
        }
        String nodeId = getNodeId();
        String nodeId2 = aggregationMatch.getNodeId();
        if (nodeId != null ? !nodeId.equals(nodeId2) : nodeId2 != null) {
            return false;
        }
        SearchData searchData = getSearchData();
        SearchData searchData2 = aggregationMatch.getSearchData();
        return searchData != null ? searchData.equals(searchData2) : searchData2 == null;
    }

    public Long getCount() {
        return this.count;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public SearchData getSearchData() {
        return this.searchData;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        String value = getValue();
        int i = 43;
        int hashCode = value == null ? 43 : value.hashCode();
        Long count = getCount();
        int hashCode2 = ((hashCode + 59) * 59) + (count == null ? 43 : count.hashCode());
        String nodeId = getNodeId();
        int hashCode3 = (hashCode2 * 59) + (nodeId == null ? 43 : nodeId.hashCode());
        SearchData searchData = getSearchData();
        int i2 = hashCode3 * 59;
        if (searchData != null) {
            i = searchData.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("count")
    public void setCount(Long l) {
        this.count = l;
    }

    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    public void setNodeId(String str) {
        this.nodeId = str;
    }

    @JsonProperty("searchData")
    public void setSearchData(SearchData searchData) {
        this.searchData = searchData;
    }

    @JsonProperty("value")
    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AggregationMatch(value=");
        outline107.append(getValue());
        outline107.append(", count=");
        outline107.append(getCount());
        outline107.append(", nodeId=");
        outline107.append(getNodeId());
        outline107.append(", searchData=");
        outline107.append(getSearchData());
        outline107.append(")");
        return outline107.toString();
    }
}
