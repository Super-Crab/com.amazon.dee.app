package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class SearchGroupNodesRequest extends SearchKeyRequest {
    private String groupShareToken;

    public String getGroupShareToken() {
        return this.groupShareToken;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest, com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public int hashCode() {
        return (((getGroupShareToken() == null ? 0 : getGroupShareToken().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setGroupShareToken(String str) {
        this.groupShareToken = str;
    }

    public SearchKeyRequest withGroupShareToken(String str) {
        setGroupShareToken(str);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest, com.amazon.clouddrive.model.PaginatedCloudDriveRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof SearchGroupNodesRequest)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getGroupShareToken(), ((SearchGroupNodesRequest) cloudDriveRequest).getGroupShareToken());
        return compare != 0 ? compare : super.compareTo(cloudDriveRequest);
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withAssetMapping */
    public SearchGroupNodesRequest mo3000withAssetMapping(String str) {
        setAssetMapping(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withDedupeContext */
    public SearchGroupNodesRequest mo3001withDedupeContext(String str) {
        setDedupeContext(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withFields */
    public SearchGroupNodesRequest mo3002withFields(String str) {
        setFields(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withFilters */
    public SearchGroupNodesRequest mo3003withFilters(String str) {
        setFilters(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withGroupByForTime */
    public SearchGroupNodesRequest mo3004withGroupByForTime(String str) {
        setGroupByForTime(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withGroupId */
    public SearchGroupNodesRequest mo3005withGroupId(String str) {
        setGroupId(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withLanguage */
    public SearchGroupNodesRequest mo3006withLanguage(String str) {
        setLanguage(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withLimit */
    public SearchGroupNodesRequest mo3007withLimit(Integer num) {
        setLimit(num);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withOffset */
    public SearchGroupNodesRequest mo3008withOffset(Integer num) {
        setOffset(num);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withSearchContext */
    public SearchGroupNodesRequest mo3009withSearchContext(String str) {
        setSearchContext(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withSessionId */
    public SearchGroupNodesRequest mo3010withSessionId(String str) {
        setSessionId(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withSort */
    public SearchGroupNodesRequest mo3011withSort(String str) {
        setSort(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withStartToken */
    public SearchGroupNodesRequest mo3012withStartToken(String str) {
        setStartToken(str);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyRequest
    /* renamed from: withTempLink */
    public SearchGroupNodesRequest mo3013withTempLink(boolean z) {
        setTempLink(Boolean.valueOf(z));
        return this;
    }
}
