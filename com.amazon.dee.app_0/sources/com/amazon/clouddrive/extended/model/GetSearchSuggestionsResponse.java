package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class GetSearchSuggestionsResponse implements CloudDriveResponse {
    private List<SearchSuggestion> searchSuggestions;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetSearchSuggestionsResponse) && compareTo((CloudDriveResponse) ((GetSearchSuggestionsResponse) obj)) == 0;
    }

    public List<SearchSuggestion> getSearchSuggestions() {
        return this.searchSuggestions;
    }

    public int hashCode() {
        List<SearchSuggestion> list = this.searchSuggestions;
        return (((list == null ? 0 : list.hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setSearchSuggestions(List<SearchSuggestion> list) {
        this.searchSuggestions = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetSearchSuggestionsResponse)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getSearchSuggestions(), ((GetSearchSuggestionsResponse) cloudDriveResponse).getSearchSuggestions());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
