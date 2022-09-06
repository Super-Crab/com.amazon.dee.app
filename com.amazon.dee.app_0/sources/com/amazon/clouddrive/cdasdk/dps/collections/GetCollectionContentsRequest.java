package com.amazon.clouddrive.cdasdk.dps.collections;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class GetCollectionContentsRequest extends DPSCollectionsRequest {
    @JsonProperty("collectionIds")
    private List<String> collectionIds;
    @JsonProperty("deviceType")
    private String deviceType;
    @JsonProperty("dsn")
    private String dsn;
    @JsonProperty(MetricsUtil.LegacyMetricTypes.LIMIT)
    private Integer limit;
    @JsonProperty("nextToken")
    private String nextToken;

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof GetCollectionContentsRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetCollectionContentsRequest)) {
            return false;
        }
        GetCollectionContentsRequest getCollectionContentsRequest = (GetCollectionContentsRequest) obj;
        if (!getCollectionContentsRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<String> collectionIds = getCollectionIds();
        List<String> collectionIds2 = getCollectionContentsRequest.getCollectionIds();
        if (collectionIds != null ? !collectionIds.equals(collectionIds2) : collectionIds2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = getCollectionContentsRequest.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Integer limit = getLimit();
        Integer limit2 = getCollectionContentsRequest.getLimit();
        if (limit != null ? !limit.equals(limit2) : limit2 != null) {
            return false;
        }
        String deviceType = getDeviceType();
        String deviceType2 = getCollectionContentsRequest.getDeviceType();
        if (deviceType != null ? !deviceType.equals(deviceType2) : deviceType2 != null) {
            return false;
        }
        String dsn = getDsn();
        String dsn2 = getCollectionContentsRequest.getDsn();
        return dsn != null ? dsn.equals(dsn2) : dsn2 == null;
    }

    public List<String> getCollectionIds() {
        return this.collectionIds;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getDsn() {
        return this.dsn;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        List<String> collectionIds = getCollectionIds();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (collectionIds == null ? 43 : collectionIds.hashCode());
        String nextToken = getNextToken();
        int hashCode3 = (hashCode2 * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        Integer limit = getLimit();
        int hashCode4 = (hashCode3 * 59) + (limit == null ? 43 : limit.hashCode());
        String deviceType = getDeviceType();
        int hashCode5 = (hashCode4 * 59) + (deviceType == null ? 43 : deviceType.hashCode());
        String dsn = getDsn();
        int i2 = hashCode5 * 59;
        if (dsn != null) {
            i = dsn.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("collectionIds")
    public void setCollectionIds(List<String> list) {
        this.collectionIds = list;
    }

    @JsonProperty("deviceType")
    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    @JsonProperty("dsn")
    public void setDsn(String str) {
        this.dsn = str;
    }

    @JsonProperty(MetricsUtil.LegacyMetricTypes.LIMIT)
    public void setLimit(Integer num) {
        this.limit = num;
    }

    @JsonProperty("nextToken")
    public void setNextToken(String str) {
        this.nextToken = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetCollectionContentsRequest(collectionIds=");
        outline107.append(getCollectionIds());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(", limit=");
        outline107.append(getLimit());
        outline107.append(", deviceType=");
        outline107.append(getDeviceType());
        outline107.append(", dsn=");
        outline107.append(getDsn());
        outline107.append(")");
        return outline107.toString();
    }
}
