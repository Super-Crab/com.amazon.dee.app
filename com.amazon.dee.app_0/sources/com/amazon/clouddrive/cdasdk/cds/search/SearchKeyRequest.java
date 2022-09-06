package com.amazon.clouddrive.cdasdk.cds.search;

import com.amazon.clouddrive.cdasdk.cds.common.AssetMapping;
import com.amazon.clouddrive.cdasdk.cds.common.DedupeContext;
import com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest;
import com.amazon.clouddrive.cdasdk.cds.common.SearchContext;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SearchKeyRequest extends PaginatedCloudDriveRequest {
    @JsonProperty("assetMapping")
    private AssetMapping assetMapping;
    @JsonProperty("dedupeContext")
    private DedupeContext dedupeContext;
    @JsonProperty("groupByForTime")
    private String groupByForTime;
    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("language")
    private String language;
    @JsonProperty("searchContext")
    private SearchContext searchContext;
    @JsonProperty("tempLink")
    private Boolean tempLink;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof SearchKeyRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchKeyRequest)) {
            return false;
        }
        SearchKeyRequest searchKeyRequest = (SearchKeyRequest) obj;
        if (!searchKeyRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        AssetMapping assetMapping = getAssetMapping();
        AssetMapping assetMapping2 = searchKeyRequest.getAssetMapping();
        if (assetMapping != null ? !assetMapping.equals(assetMapping2) : assetMapping2 != null) {
            return false;
        }
        Boolean tempLink = getTempLink();
        Boolean tempLink2 = searchKeyRequest.getTempLink();
        if (tempLink != null ? !tempLink.equals(tempLink2) : tempLink2 != null) {
            return false;
        }
        DedupeContext dedupeContext = getDedupeContext();
        DedupeContext dedupeContext2 = searchKeyRequest.getDedupeContext();
        if (dedupeContext != null ? !dedupeContext.equals(dedupeContext2) : dedupeContext2 != null) {
            return false;
        }
        String groupByForTime = getGroupByForTime();
        String groupByForTime2 = searchKeyRequest.getGroupByForTime();
        if (groupByForTime != null ? !groupByForTime.equals(groupByForTime2) : groupByForTime2 != null) {
            return false;
        }
        SearchContext searchContext = getSearchContext();
        SearchContext searchContext2 = searchKeyRequest.getSearchContext();
        if (searchContext != null ? !searchContext.equals(searchContext2) : searchContext2 != null) {
            return false;
        }
        String language = getLanguage();
        String language2 = searchKeyRequest.getLanguage();
        if (language != null ? !language.equals(language2) : language2 != null) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = searchKeyRequest.getGroupId();
        return groupId != null ? groupId.equals(groupId2) : groupId2 == null;
    }

    public AssetMapping getAssetMapping() {
        return this.assetMapping;
    }

    public DedupeContext getDedupeContext() {
        return this.dedupeContext;
    }

    public String getGroupByForTime() {
        return this.groupByForTime;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLanguage() {
        return this.language;
    }

    public SearchContext getSearchContext() {
        return this.searchContext;
    }

    public Boolean getTempLink() {
        return this.tempLink;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        AssetMapping assetMapping = getAssetMapping();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (assetMapping == null ? 43 : assetMapping.hashCode());
        Boolean tempLink = getTempLink();
        int hashCode3 = (hashCode2 * 59) + (tempLink == null ? 43 : tempLink.hashCode());
        DedupeContext dedupeContext = getDedupeContext();
        int hashCode4 = (hashCode3 * 59) + (dedupeContext == null ? 43 : dedupeContext.hashCode());
        String groupByForTime = getGroupByForTime();
        int hashCode5 = (hashCode4 * 59) + (groupByForTime == null ? 43 : groupByForTime.hashCode());
        SearchContext searchContext = getSearchContext();
        int hashCode6 = (hashCode5 * 59) + (searchContext == null ? 43 : searchContext.hashCode());
        String language = getLanguage();
        int hashCode7 = (hashCode6 * 59) + (language == null ? 43 : language.hashCode());
        String groupId = getGroupId();
        int i2 = hashCode7 * 59;
        if (groupId != null) {
            i = groupId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("assetMapping")
    public void setAssetMapping(AssetMapping assetMapping) {
        this.assetMapping = assetMapping;
    }

    @JsonProperty("dedupeContext")
    public void setDedupeContext(DedupeContext dedupeContext) {
        this.dedupeContext = dedupeContext;
    }

    @JsonProperty("groupByForTime")
    public void setGroupByForTime(String str) {
        this.groupByForTime = str;
    }

    @JsonProperty("groupId")
    public void setGroupId(String str) {
        this.groupId = str;
    }

    @JsonProperty("language")
    public void setLanguage(String str) {
        this.language = str;
    }

    @JsonProperty("searchContext")
    public void setSearchContext(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    @JsonProperty("tempLink")
    public void setTempLink(Boolean bool) {
        this.tempLink = bool;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchKeyRequest(assetMapping=");
        outline107.append(getAssetMapping());
        outline107.append(", tempLink=");
        outline107.append(getTempLink());
        outline107.append(", dedupeContext=");
        outline107.append(getDedupeContext());
        outline107.append(", groupByForTime=");
        outline107.append(getGroupByForTime());
        outline107.append(", searchContext=");
        outline107.append(getSearchContext());
        outline107.append(", language=");
        outline107.append(getLanguage());
        outline107.append(", groupId=");
        outline107.append(getGroupId());
        outline107.append(")");
        return outline107.toString();
    }
}
