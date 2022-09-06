package com.amazon.clouddrive.cdasdk.dps.collections;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class GetCollectionsByIdRequest extends DPSCollectionsRequest {
    @JsonProperty("collectionIds")
    private List<String> collectionIds;

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof GetCollectionsByIdRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetCollectionsByIdRequest)) {
            return false;
        }
        GetCollectionsByIdRequest getCollectionsByIdRequest = (GetCollectionsByIdRequest) obj;
        if (!getCollectionsByIdRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<String> collectionIds = getCollectionIds();
        List<String> collectionIds2 = getCollectionsByIdRequest.getCollectionIds();
        return collectionIds != null ? collectionIds.equals(collectionIds2) : collectionIds2 == null;
    }

    public List<String> getCollectionIds() {
        return this.collectionIds;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        List<String> collectionIds = getCollectionIds();
        return (hashCode * 59) + (collectionIds == null ? 43 : collectionIds.hashCode());
    }

    @JsonProperty("collectionIds")
    public void setCollectionIds(List<String> list) {
        this.collectionIds = list;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest, com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetCollectionsByIdRequest(collectionIds=");
        outline107.append(getCollectionIds());
        outline107.append(")");
        return outline107.toString();
    }
}
