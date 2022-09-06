package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GetSearchSuggestionsRequest implements CloudDriveRequest {
    private String filters;
    private String language;
    private Long limit;
    private String queryString;
    private String sort;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetSearchSuggestionsRequest) && compareTo((CloudDriveRequest) ((GetSearchSuggestionsRequest) obj)) == 0;
    }

    public String getFilters() {
        return this.filters;
    }

    public String getLanguage() {
        return this.language;
    }

    public Long getLimit() {
        return this.limit;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public String getSort() {
        return this.sort;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getQueryString() == null ? 0 : getQueryString().hashCode()) + 1 + (getFilters() == null ? 0 : getFilters().hashCode()) + (getLimit() == null ? 0 : getLimit().hashCode()) + (getLanguage() == null ? 0 : getLanguage().hashCode());
        if (getSort() != null) {
            i = getSort().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setFilters(String str) {
        this.filters = str;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setLimit(Long l) {
        this.limit = l;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public void setSort(String str) {
        this.sort = str;
    }

    public GetSearchSuggestionsRequest withFilters(String str) {
        setFilters(str);
        return this;
    }

    public GetSearchSuggestionsRequest withLanguage(String str) {
        setLanguage(str);
        return this;
    }

    public GetSearchSuggestionsRequest withLimit(Long l) {
        setLimit(l);
        return this;
    }

    public GetSearchSuggestionsRequest withQueryString(String str) {
        setQueryString(str);
        return this;
    }

    public GetSearchSuggestionsRequest withSort(String str) {
        setSort(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetSearchSuggestionsRequest)) {
            return 1;
        }
        GetSearchSuggestionsRequest getSearchSuggestionsRequest = (GetSearchSuggestionsRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getQueryString(), getSearchSuggestionsRequest.getQueryString());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getFilters(), getSearchSuggestionsRequest.getFilters());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getLimit(), getSearchSuggestionsRequest.getLimit());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getSort(), getSearchSuggestionsRequest.getSort());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getLanguage(), getSearchSuggestionsRequest.getLanguage());
        if (compare5 == 0) {
            return 0;
        }
        return compare5;
    }
}
