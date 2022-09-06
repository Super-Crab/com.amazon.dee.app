package com.amazon.clouddrive.cdasdk.dps.collections;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ListCollectionsRequest extends DPSCollectionsRequest {
    @JsonProperty("collectionType")
    private String collectionType;
    @JsonProperty("coverPhotoCount")
    private Integer coverPhotoCount;
    @JsonProperty(MetricsUtil.LegacyMetricTypes.LIMIT)
    private Integer limit;
    @JsonProperty("nextToken")
    private String nextToken;

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof ListCollectionsRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListCollectionsRequest)) {
            return false;
        }
        ListCollectionsRequest listCollectionsRequest = (ListCollectionsRequest) obj;
        if (!listCollectionsRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listCollectionsRequest.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Integer limit = getLimit();
        Integer limit2 = listCollectionsRequest.getLimit();
        if (limit != null ? !limit.equals(limit2) : limit2 != null) {
            return false;
        }
        Integer coverPhotoCount = getCoverPhotoCount();
        Integer coverPhotoCount2 = listCollectionsRequest.getCoverPhotoCount();
        if (coverPhotoCount != null ? !coverPhotoCount.equals(coverPhotoCount2) : coverPhotoCount2 != null) {
            return false;
        }
        String collectionType = getCollectionType();
        String collectionType2 = listCollectionsRequest.getCollectionType();
        return collectionType != null ? collectionType.equals(collectionType2) : collectionType2 == null;
    }

    public String getCollectionType() {
        return this.collectionType;
    }

    public Integer getCoverPhotoCount() {
        return this.coverPhotoCount;
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
        String nextToken = getNextToken();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        Integer limit = getLimit();
        int hashCode3 = (hashCode2 * 59) + (limit == null ? 43 : limit.hashCode());
        Integer coverPhotoCount = getCoverPhotoCount();
        int hashCode4 = (hashCode3 * 59) + (coverPhotoCount == null ? 43 : coverPhotoCount.hashCode());
        String collectionType = getCollectionType();
        int i2 = hashCode4 * 59;
        if (collectionType != null) {
            i = collectionType.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("collectionType")
    public void setCollectionType(String str) {
        this.collectionType = str;
    }

    @JsonProperty("coverPhotoCount")
    public void setCoverPhotoCount(Integer num) {
        this.coverPhotoCount = num;
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListCollectionsRequest(nextToken=");
        outline107.append(getNextToken());
        outline107.append(", limit=");
        outline107.append(getLimit());
        outline107.append(", coverPhotoCount=");
        outline107.append(getCoverPhotoCount());
        outline107.append(", collectionType=");
        outline107.append(getCollectionType());
        outline107.append(")");
        return outline107.toString();
    }
}
