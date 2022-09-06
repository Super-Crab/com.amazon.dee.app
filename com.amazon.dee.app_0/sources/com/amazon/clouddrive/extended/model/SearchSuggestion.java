package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class SearchSuggestion {
    private Long mCount;
    private SearchMetadata mMetadata;
    private String mSuggestionCategory;
    private String mSuggestionKeyword;

    public int compareTo(SearchSuggestion searchSuggestion) {
        if (searchSuggestion == null) {
            return -1;
        }
        if (searchSuggestion == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getSuggestionKeyword(), searchSuggestion.getSuggestionKeyword());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getSuggestionCategory(), searchSuggestion.getSuggestionCategory());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getCount(), searchSuggestion.getCount());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getMetadata(), searchSuggestion.getMetadata());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SearchSuggestion) && compareTo((SearchSuggestion) obj) == 0;
    }

    public Long getCount() {
        return this.mCount;
    }

    public SearchMetadata getMetadata() {
        return this.mMetadata;
    }

    public String getSuggestionCategory() {
        return this.mSuggestionCategory;
    }

    public String getSuggestionKeyword() {
        return this.mSuggestionKeyword;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getSuggestionKeyword() == null ? 0 : getSuggestionKeyword().hashCode()) + 1 + (getSuggestionCategory() == null ? 0 : getSuggestionCategory().hashCode()) + (getCount() == null ? 0 : getCount().hashCode());
        if (getMetadata() != null) {
            i = getMetadata().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setCount(Long l) {
        this.mCount = l;
    }

    public void setMetadata(SearchMetadata searchMetadata) {
        this.mMetadata = searchMetadata;
    }

    public void setSuggestionCategory(String str) {
        this.mSuggestionCategory = str;
    }

    public void setSuggestionKeyword(String str) {
        this.mSuggestionKeyword = str;
    }
}
