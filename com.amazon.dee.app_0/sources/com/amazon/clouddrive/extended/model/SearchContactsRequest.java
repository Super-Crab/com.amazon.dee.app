package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public final class SearchContactsRequest implements CloudDriveRequest {
    private final List<String> groupFilter;
    private final List<String> inviteFilter;
    private final List<String> kindFilter;
    private final String lang;
    private final Integer maxResults;
    private final List<String> memberFilter;
    private final String nextToken;
    private final String term;

    /* loaded from: classes11.dex */
    public static class SearchContactsRequestBuilder {
        private List<String> groupFilter;
        private List<String> inviteFilter;
        private List<String> kindFilter;
        private String lang;
        private Integer maxResults;
        private List<String> memberFilter;
        private String nextToken;
        private String term;

        SearchContactsRequestBuilder() {
        }

        public SearchContactsRequest build() {
            return new SearchContactsRequest(this.kindFilter, this.memberFilter, this.inviteFilter, this.groupFilter, this.nextToken, this.maxResults, this.term, this.lang);
        }

        public SearchContactsRequestBuilder groupFilter(List<String> list) {
            this.groupFilter = list;
            return this;
        }

        public SearchContactsRequestBuilder inviteFilter(List<String> list) {
            this.inviteFilter = list;
            return this;
        }

        public SearchContactsRequestBuilder kindFilter(List<String> list) {
            this.kindFilter = list;
            return this;
        }

        public SearchContactsRequestBuilder lang(String str) {
            this.lang = str;
            return this;
        }

        public SearchContactsRequestBuilder maxResults(Integer num) {
            this.maxResults = num;
            return this;
        }

        public SearchContactsRequestBuilder memberFilter(List<String> list) {
            this.memberFilter = list;
            return this;
        }

        public SearchContactsRequestBuilder nextToken(String str) {
            this.nextToken = str;
            return this;
        }

        public SearchContactsRequestBuilder term(String str) {
            this.term = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchContactsRequest.SearchContactsRequestBuilder(kindFilter=");
            outline107.append(this.kindFilter);
            outline107.append(", memberFilter=");
            outline107.append(this.memberFilter);
            outline107.append(", inviteFilter=");
            outline107.append(this.inviteFilter);
            outline107.append(", groupFilter=");
            outline107.append(this.groupFilter);
            outline107.append(", nextToken=");
            outline107.append(this.nextToken);
            outline107.append(", maxResults=");
            outline107.append(this.maxResults);
            outline107.append(", term=");
            outline107.append(this.term);
            outline107.append(", lang=");
            return GeneratedOutlineSupport1.outline91(outline107, this.lang, ")");
        }
    }

    SearchContactsRequest(List<String> list, List<String> list2, List<String> list3, List<String> list4, String str, Integer num, String str2, String str3) {
        this.kindFilter = list;
        this.memberFilter = list2;
        this.inviteFilter = list3;
        this.groupFilter = list4;
        this.nextToken = str;
        this.maxResults = num;
        this.term = str2;
        this.lang = str3;
    }

    public static SearchContactsRequestBuilder builder() {
        return new SearchContactsRequestBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchContactsRequest)) {
            return false;
        }
        SearchContactsRequest searchContactsRequest = (SearchContactsRequest) obj;
        List<String> kindFilter = getKindFilter();
        List<String> kindFilter2 = searchContactsRequest.getKindFilter();
        if (kindFilter != null ? !kindFilter.equals(kindFilter2) : kindFilter2 != null) {
            return false;
        }
        List<String> memberFilter = getMemberFilter();
        List<String> memberFilter2 = searchContactsRequest.getMemberFilter();
        if (memberFilter != null ? !memberFilter.equals(memberFilter2) : memberFilter2 != null) {
            return false;
        }
        List<String> inviteFilter = getInviteFilter();
        List<String> inviteFilter2 = searchContactsRequest.getInviteFilter();
        if (inviteFilter != null ? !inviteFilter.equals(inviteFilter2) : inviteFilter2 != null) {
            return false;
        }
        List<String> groupFilter = getGroupFilter();
        List<String> groupFilter2 = searchContactsRequest.getGroupFilter();
        if (groupFilter != null ? !groupFilter.equals(groupFilter2) : groupFilter2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = searchContactsRequest.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Integer maxResults = getMaxResults();
        Integer maxResults2 = searchContactsRequest.getMaxResults();
        if (maxResults != null ? !maxResults.equals(maxResults2) : maxResults2 != null) {
            return false;
        }
        String term = getTerm();
        String term2 = searchContactsRequest.getTerm();
        if (term != null ? !term.equals(term2) : term2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = searchContactsRequest.getLang();
        return lang != null ? lang.equals(lang2) : lang2 == null;
    }

    public List<String> getGroupFilter() {
        return this.groupFilter;
    }

    public List<String> getInviteFilter() {
        return this.inviteFilter;
    }

    public List<String> getKindFilter() {
        return this.kindFilter;
    }

    public String getLang() {
        return this.lang;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public List<String> getMemberFilter() {
        return this.memberFilter;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getTerm() {
        return this.term;
    }

    public int hashCode() {
        List<String> kindFilter = getKindFilter();
        int i = 43;
        int hashCode = kindFilter == null ? 43 : kindFilter.hashCode();
        List<String> memberFilter = getMemberFilter();
        int hashCode2 = ((hashCode + 59) * 59) + (memberFilter == null ? 43 : memberFilter.hashCode());
        List<String> inviteFilter = getInviteFilter();
        int hashCode3 = (hashCode2 * 59) + (inviteFilter == null ? 43 : inviteFilter.hashCode());
        List<String> groupFilter = getGroupFilter();
        int hashCode4 = (hashCode3 * 59) + (groupFilter == null ? 43 : groupFilter.hashCode());
        String nextToken = getNextToken();
        int hashCode5 = (hashCode4 * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        Integer maxResults = getMaxResults();
        int hashCode6 = (hashCode5 * 59) + (maxResults == null ? 43 : maxResults.hashCode());
        String term = getTerm();
        int hashCode7 = (hashCode6 * 59) + (term == null ? 43 : term.hashCode());
        String lang = getLang();
        int i2 = hashCode7 * 59;
        if (lang != null) {
            i = lang.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchContactsRequest(kindFilter=");
        outline107.append(getKindFilter());
        outline107.append(", memberFilter=");
        outline107.append(getMemberFilter());
        outline107.append(", inviteFilter=");
        outline107.append(getInviteFilter());
        outline107.append(", groupFilter=");
        outline107.append(getGroupFilter());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(", maxResults=");
        outline107.append(getMaxResults());
        outline107.append(", term=");
        outline107.append(getTerm());
        outline107.append(", lang=");
        outline107.append(getLang());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof SearchContactsRequest)) {
            return SearchContactsRequest.class.getName().compareTo(cloudDriveRequest.getClass().getName());
        }
        SearchContactsRequest searchContactsRequest = (SearchContactsRequest) cloudDriveRequest;
        int compareCollections = ObjectComparator.compareCollections(getGroupFilter(), searchContactsRequest.getGroupFilter());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compareCollections2 = ObjectComparator.compareCollections(getKindFilter(), searchContactsRequest.getKindFilter());
        if (compareCollections2 != 0) {
            return compareCollections2;
        }
        int compare = ObjectComparator.compare(getMaxResults(), searchContactsRequest.getMaxResults());
        if (compare != 0) {
            return compare;
        }
        int compareCollections3 = ObjectComparator.compareCollections(getMemberFilter(), searchContactsRequest.getMemberFilter());
        if (compareCollections3 != 0) {
            return compareCollections3;
        }
        int compareCollections4 = ObjectComparator.compareCollections(getInviteFilter(), searchContactsRequest.getInviteFilter());
        if (compareCollections4 != 0) {
            return compareCollections4;
        }
        int compare2 = ObjectComparator.compare(getNextToken(), searchContactsRequest.getNextToken());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getTerm(), searchContactsRequest.getTerm());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getLang(), searchContactsRequest.getLang());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }
}
