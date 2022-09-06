package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.amazon.clouddrive.model.PaginatedCloudDriveRequest;
/* loaded from: classes11.dex */
public class SearchKeyRequest extends PaginatedCloudDriveRequest {
    private String groupId;
    private String mAssetMapping;
    private String mDedupeContext;
    private String mGroupByForTime;
    private String mLanguage;
    private String mSearchContext;
    private String mSessionId;
    private Boolean mTempLink;

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SearchKeyRequest) && compareTo((CloudDriveRequest) ((SearchKeyRequest) obj)) == 0;
    }

    public String getAssetMapping() {
        return this.mAssetMapping;
    }

    public String getDedupeContext() {
        return this.mDedupeContext;
    }

    public String getGroupByForTime() {
        return this.mGroupByForTime;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public String getSearchContext() {
        return this.mSearchContext;
    }

    public String getSessionId() {
        return this.mSessionId;
    }

    public Boolean getTempLink() {
        return this.mTempLink;
    }

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public int hashCode() {
        int i = 0;
        int hashCode = (getAssetMapping() == null ? 0 : getAssetMapping().hashCode()) + 1 + (getDedupeContext() == null ? 0 : getDedupeContext().hashCode()) + (getTempLink().booleanValue() ? 1 : 0) + (getLanguage() == null ? 0 : getLanguage().hashCode()) + (getGroupByForTime() == null ? 0 : getGroupByForTime().hashCode()) + (getSearchContext() == null ? 0 : getSearchContext().hashCode()) + (getSessionId() == null ? 0 : getSessionId().hashCode());
        if (getGroupId() != null) {
            i = getGroupId().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setAssetMapping(String str) {
        this.mAssetMapping = str;
    }

    public void setDedupeContext(String str) {
        this.mDedupeContext = str;
    }

    public void setGroupByForTime(String str) {
        this.mGroupByForTime = str;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setLanguage(String str) {
        this.mLanguage = str;
    }

    public void setSearchContext(String str) {
        this.mSearchContext = str;
    }

    public void setSessionId(String str) {
        this.mSessionId = str;
    }

    public void setTempLink(Boolean bool) {
        this.mTempLink = bool;
    }

    /* renamed from: withAssetMapping */
    public SearchKeyRequest mo3000withAssetMapping(String str) {
        setAssetMapping(str);
        return this;
    }

    /* renamed from: withDedupeContext */
    public SearchKeyRequest mo3001withDedupeContext(String str) {
        setDedupeContext(str);
        return this;
    }

    /* renamed from: withFields */
    public SearchKeyRequest mo3002withFields(String str) {
        setFields(str);
        return this;
    }

    /* renamed from: withFilters */
    public SearchKeyRequest mo3003withFilters(String str) {
        setFilters(str);
        return this;
    }

    /* renamed from: withGroupByForTime */
    public SearchKeyRequest mo3004withGroupByForTime(String str) {
        setGroupByForTime(str);
        return this;
    }

    /* renamed from: withGroupId */
    public SearchKeyRequest mo3005withGroupId(String str) {
        setGroupId(str);
        return this;
    }

    /* renamed from: withLanguage */
    public SearchKeyRequest mo3006withLanguage(String str) {
        setLanguage(str);
        return this;
    }

    /* renamed from: withLimit */
    public SearchKeyRequest mo3007withLimit(Integer num) {
        setLimit(num);
        return this;
    }

    /* renamed from: withOffset */
    public SearchKeyRequest mo3008withOffset(Integer num) {
        setOffset(num);
        return this;
    }

    /* renamed from: withSearchContext */
    public SearchKeyRequest mo3009withSearchContext(String str) {
        setSearchContext(str);
        return this;
    }

    /* renamed from: withSessionId */
    public SearchKeyRequest mo3010withSessionId(String str) {
        setSessionId(str);
        return this;
    }

    /* renamed from: withSort */
    public SearchKeyRequest mo3011withSort(String str) {
        setSort(str);
        return this;
    }

    /* renamed from: withStartToken */
    public SearchKeyRequest mo3012withStartToken(String str) {
        setStartToken(str);
        return this;
    }

    /* renamed from: withTempLink */
    public SearchKeyRequest mo3013withTempLink(boolean z) {
        setTempLink(Boolean.valueOf(z));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof SearchKeyRequest)) {
            return 1;
        }
        SearchKeyRequest searchKeyRequest = (SearchKeyRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getAssetMapping(), searchKeyRequest.getAssetMapping());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getDedupeContext(), searchKeyRequest.getDedupeContext());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getTempLink(), searchKeyRequest.getTempLink());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getGroupByForTime(), searchKeyRequest.getGroupByForTime());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getSearchContext(), searchKeyRequest.getSearchContext());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getSessionId(), searchKeyRequest.getSessionId());
        if (compare6 != 0) {
            return compare6;
        }
        int compare7 = ObjectComparator.compare(getGroupId(), searchKeyRequest.getGroupId());
        if (compare7 != 0) {
            return compare7;
        }
        int compare8 = ObjectComparator.compare(getLanguage(), searchKeyRequest.getLanguage());
        return compare8 != 0 ? compare8 : super.compareTo(cloudDriveRequest);
    }
}
