package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/* loaded from: classes11.dex */
public class GetTaggingStatusResponse extends CloudDriveResponse {
    @JsonProperty("taggingStatuses")
    private Map<String, TaggingStatus> taggingStatuses;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof GetTaggingStatusResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetTaggingStatusResponse)) {
            return false;
        }
        GetTaggingStatusResponse getTaggingStatusResponse = (GetTaggingStatusResponse) obj;
        if (!getTaggingStatusResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Map<String, TaggingStatus> taggingStatuses = getTaggingStatuses();
        Map<String, TaggingStatus> taggingStatuses2 = getTaggingStatusResponse.getTaggingStatuses();
        return taggingStatuses != null ? taggingStatuses.equals(taggingStatuses2) : taggingStatuses2 == null;
    }

    public Map<String, TaggingStatus> getTaggingStatuses() {
        return this.taggingStatuses;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        Map<String, TaggingStatus> taggingStatuses = getTaggingStatuses();
        return (hashCode * 59) + (taggingStatuses == null ? 43 : taggingStatuses.hashCode());
    }

    @JsonProperty("taggingStatuses")
    public void setTaggingStatuses(Map<String, TaggingStatus> map) {
        this.taggingStatuses = map;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetTaggingStatusResponse(taggingStatuses=");
        outline107.append(getTaggingStatuses());
        outline107.append(")");
        return outline107.toString();
    }
}
