package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import lombok.NonNull;
/* loaded from: classes11.dex */
public final class ListInvitesRequest implements CloudDriveRequest {
    private final String direction;
    @NonNull
    private final String groupId;
    private final String lang;
    private final Integer maxResults;
    private final String nextToken;
    private final String sort;
    private final String state;

    /* loaded from: classes11.dex */
    public static class ListInvitesRequestBuilder {
        private String direction;
        private String groupId;
        private String lang;
        private Integer maxResults;
        private String nextToken;
        private String sort;
        private String state;

        ListInvitesRequestBuilder() {
        }

        public ListInvitesRequest build() {
            return new ListInvitesRequest(this.groupId, this.sort, this.direction, this.state, this.nextToken, this.maxResults, this.lang);
        }

        public ListInvitesRequestBuilder direction(String str) {
            this.direction = str;
            return this;
        }

        public ListInvitesRequestBuilder groupId(String str) {
            this.groupId = str;
            return this;
        }

        public ListInvitesRequestBuilder lang(String str) {
            this.lang = str;
            return this;
        }

        public ListInvitesRequestBuilder maxResults(Integer num) {
            this.maxResults = num;
            return this;
        }

        public ListInvitesRequestBuilder nextToken(String str) {
            this.nextToken = str;
            return this;
        }

        public ListInvitesRequestBuilder sort(String str) {
            this.sort = str;
            return this;
        }

        public ListInvitesRequestBuilder state(String str) {
            this.state = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListInvitesRequest.ListInvitesRequestBuilder(groupId=");
            outline107.append(this.groupId);
            outline107.append(", sort=");
            outline107.append(this.sort);
            outline107.append(", direction=");
            outline107.append(this.direction);
            outline107.append(", state=");
            outline107.append(this.state);
            outline107.append(", nextToken=");
            outline107.append(this.nextToken);
            outline107.append(", maxResults=");
            outline107.append(this.maxResults);
            outline107.append(", lang=");
            return GeneratedOutlineSupport1.outline91(outline107, this.lang, ")");
        }
    }

    ListInvitesRequest(@NonNull String str, String str2, String str3, String str4, String str5, Integer num, String str6) {
        if (str != null) {
            this.groupId = str;
            this.sort = str2;
            this.direction = str3;
            this.state = str4;
            this.nextToken = str5;
            this.maxResults = num;
            this.lang = str6;
            return;
        }
        throw new IllegalArgumentException("groupId is null");
    }

    public static ListInvitesRequestBuilder builder() {
        return new ListInvitesRequestBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListInvitesRequest)) {
            return false;
        }
        ListInvitesRequest listInvitesRequest = (ListInvitesRequest) obj;
        String groupId = getGroupId();
        String groupId2 = listInvitesRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String sort = getSort();
        String sort2 = listInvitesRequest.getSort();
        if (sort != null ? !sort.equals(sort2) : sort2 != null) {
            return false;
        }
        String direction = getDirection();
        String direction2 = listInvitesRequest.getDirection();
        if (direction != null ? !direction.equals(direction2) : direction2 != null) {
            return false;
        }
        String state = getState();
        String state2 = listInvitesRequest.getState();
        if (state != null ? !state.equals(state2) : state2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listInvitesRequest.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Integer maxResults = getMaxResults();
        Integer maxResults2 = listInvitesRequest.getMaxResults();
        if (maxResults != null ? !maxResults.equals(maxResults2) : maxResults2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = listInvitesRequest.getLang();
        return lang != null ? lang.equals(lang2) : lang2 == null;
    }

    public String getDirection() {
        return this.direction;
    }

    @NonNull
    public String getGroupId() {
        return this.groupId;
    }

    public String getLang() {
        return this.lang;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getSort() {
        return this.sort;
    }

    public String getState() {
        return this.state;
    }

    public int hashCode() {
        String groupId = getGroupId();
        int i = 43;
        int hashCode = groupId == null ? 43 : groupId.hashCode();
        String sort = getSort();
        int hashCode2 = ((hashCode + 59) * 59) + (sort == null ? 43 : sort.hashCode());
        String direction = getDirection();
        int hashCode3 = (hashCode2 * 59) + (direction == null ? 43 : direction.hashCode());
        String state = getState();
        int hashCode4 = (hashCode3 * 59) + (state == null ? 43 : state.hashCode());
        String nextToken = getNextToken();
        int hashCode5 = (hashCode4 * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        Integer maxResults = getMaxResults();
        int hashCode6 = (hashCode5 * 59) + (maxResults == null ? 43 : maxResults.hashCode());
        String lang = getLang();
        int i2 = hashCode6 * 59;
        if (lang != null) {
            i = lang.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListInvitesRequest(groupId=");
        outline107.append(getGroupId());
        outline107.append(", sort=");
        outline107.append(getSort());
        outline107.append(", direction=");
        outline107.append(getDirection());
        outline107.append(", state=");
        outline107.append(getState());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(", maxResults=");
        outline107.append(getMaxResults());
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
        if (!(cloudDriveRequest instanceof ListInvitesRequest)) {
            return 1;
        }
        ListInvitesRequest listInvitesRequest = (ListInvitesRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), listInvitesRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getSort(), listInvitesRequest.getSort());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getDirection(), listInvitesRequest.getDirection());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getState(), listInvitesRequest.getState());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getNextToken(), listInvitesRequest.getNextToken());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getMaxResults(), listInvitesRequest.getMaxResults());
        if (compare6 != 0) {
            return compare6;
        }
        int compare7 = ObjectComparator.compare(getLang(), listInvitesRequest.getLang());
        if (compare7 == 0) {
            return 0;
        }
        return compare7;
    }
}
