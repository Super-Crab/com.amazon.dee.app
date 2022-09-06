package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class ListContactsRequest implements CloudDriveRequest {
    private final String groupFilter;
    private final Kind kindFilter;
    private final Integer maxResults;
    private final String memberFilter;
    private final String nextToken;
    private final String term;

    /* loaded from: classes11.dex */
    public enum Kind {
        GROUP,
        PERSON
    }

    /* loaded from: classes11.dex */
    public static class ListContactsRequestBuilder {
        private String groupFilter;
        private Kind kindFilter;
        private Integer maxResults;
        private String memberFilter;
        private String nextToken;
        private String term;

        ListContactsRequestBuilder() {
        }

        public ListContactsRequest build() {
            return new ListContactsRequest(this.kindFilter, this.memberFilter, this.groupFilter, this.nextToken, this.maxResults, this.term);
        }

        public ListContactsRequestBuilder groupFilter(String str) {
            this.groupFilter = str;
            return this;
        }

        public ListContactsRequestBuilder kindFilter(Kind kind) {
            this.kindFilter = kind;
            return this;
        }

        public ListContactsRequestBuilder maxResults(Integer num) {
            this.maxResults = num;
            return this;
        }

        public ListContactsRequestBuilder memberFilter(String str) {
            this.memberFilter = str;
            return this;
        }

        public ListContactsRequestBuilder nextToken(String str) {
            this.nextToken = str;
            return this;
        }

        public ListContactsRequestBuilder term(String str) {
            this.term = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListContactsRequest.ListContactsRequestBuilder(kindFilter=");
            outline107.append(this.kindFilter);
            outline107.append(", memberFilter=");
            outline107.append(this.memberFilter);
            outline107.append(", groupFilter=");
            outline107.append(this.groupFilter);
            outline107.append(", nextToken=");
            outline107.append(this.nextToken);
            outline107.append(", maxResults=");
            outline107.append(this.maxResults);
            outline107.append(", term=");
            return GeneratedOutlineSupport1.outline91(outline107, this.term, ")");
        }
    }

    ListContactsRequest(Kind kind, String str, String str2, String str3, Integer num, String str4) {
        this.kindFilter = kind;
        this.memberFilter = str;
        this.groupFilter = str2;
        this.nextToken = str3;
        this.maxResults = num;
        this.term = str4;
    }

    public static ListContactsRequestBuilder builder() {
        return new ListContactsRequestBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListContactsRequest)) {
            return false;
        }
        ListContactsRequest listContactsRequest = (ListContactsRequest) obj;
        Kind kindFilter = getKindFilter();
        Kind kindFilter2 = listContactsRequest.getKindFilter();
        if (kindFilter != null ? !kindFilter.equals(kindFilter2) : kindFilter2 != null) {
            return false;
        }
        String memberFilter = getMemberFilter();
        String memberFilter2 = listContactsRequest.getMemberFilter();
        if (memberFilter != null ? !memberFilter.equals(memberFilter2) : memberFilter2 != null) {
            return false;
        }
        String groupFilter = getGroupFilter();
        String groupFilter2 = listContactsRequest.getGroupFilter();
        if (groupFilter != null ? !groupFilter.equals(groupFilter2) : groupFilter2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listContactsRequest.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Integer maxResults = getMaxResults();
        Integer maxResults2 = listContactsRequest.getMaxResults();
        if (maxResults != null ? !maxResults.equals(maxResults2) : maxResults2 != null) {
            return false;
        }
        String term = getTerm();
        String term2 = listContactsRequest.getTerm();
        return term != null ? term.equals(term2) : term2 == null;
    }

    public String getGroupFilter() {
        return this.groupFilter;
    }

    public Kind getKindFilter() {
        return this.kindFilter;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getMemberFilter() {
        return this.memberFilter;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getTerm() {
        return this.term;
    }

    public int hashCode() {
        Kind kindFilter = getKindFilter();
        int i = 43;
        int hashCode = kindFilter == null ? 43 : kindFilter.hashCode();
        String memberFilter = getMemberFilter();
        int hashCode2 = ((hashCode + 59) * 59) + (memberFilter == null ? 43 : memberFilter.hashCode());
        String groupFilter = getGroupFilter();
        int hashCode3 = (hashCode2 * 59) + (groupFilter == null ? 43 : groupFilter.hashCode());
        String nextToken = getNextToken();
        int hashCode4 = (hashCode3 * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        Integer maxResults = getMaxResults();
        int hashCode5 = (hashCode4 * 59) + (maxResults == null ? 43 : maxResults.hashCode());
        String term = getTerm();
        int i2 = hashCode5 * 59;
        if (term != null) {
            i = term.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListContactsRequest(kindFilter=");
        outline107.append(getKindFilter());
        outline107.append(", memberFilter=");
        outline107.append(getMemberFilter());
        outline107.append(", groupFilter=");
        outline107.append(getGroupFilter());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(", maxResults=");
        outline107.append(getMaxResults());
        outline107.append(", term=");
        outline107.append(getTerm());
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
        if (!(cloudDriveRequest instanceof ListContactsRequest)) {
            return ListContactsRequest.class.getName().compareTo(cloudDriveRequest.getClass().getName());
        }
        ListContactsRequest listContactsRequest = (ListContactsRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupFilter(), listContactsRequest.getGroupFilter());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getKindFilter(), listContactsRequest.getKindFilter());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getMaxResults(), listContactsRequest.getMaxResults());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getMemberFilter(), listContactsRequest.getMemberFilter());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getNextToken(), listContactsRequest.getNextToken());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getTerm(), listContactsRequest.getTerm());
        if (compare6 == 0) {
            return 0;
        }
        return compare6;
    }
}
