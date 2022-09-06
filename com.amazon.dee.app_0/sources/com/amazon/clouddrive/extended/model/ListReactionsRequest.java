package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class ListReactionsRequest implements CloudDriveRequest {
    private final String direction;
    private final String groupId;
    private final boolean includeCoverPhotos;
    private final String kind;
    private final String kindSet;
    private final Integer maxResults;
    private final String nextToken;
    private final String sort;
    private final String topic;

    /* loaded from: classes11.dex */
    public static class ListReactionsRequestBuilder {
        private String direction;
        private String groupId;
        private boolean includeCoverPhotos;
        private String kind;
        private String kindSet;
        private Integer maxResults;
        private String nextToken;
        private String sort;
        private String topic;

        ListReactionsRequestBuilder() {
        }

        public ListReactionsRequest build() {
            return new ListReactionsRequest(this.topic, this.groupId, this.nextToken, this.maxResults, this.sort, this.direction, this.includeCoverPhotos, this.kind, this.kindSet);
        }

        public ListReactionsRequestBuilder direction(String str) {
            this.direction = str;
            return this;
        }

        public ListReactionsRequestBuilder groupId(String str) {
            this.groupId = str;
            return this;
        }

        public ListReactionsRequestBuilder includeCoverPhotos(boolean z) {
            this.includeCoverPhotos = z;
            return this;
        }

        public ListReactionsRequestBuilder kind(String str) {
            this.kind = str;
            return this;
        }

        public ListReactionsRequestBuilder kindSet(String str) {
            this.kindSet = str;
            return this;
        }

        public ListReactionsRequestBuilder maxResults(Integer num) {
            this.maxResults = num;
            return this;
        }

        public ListReactionsRequestBuilder nextToken(String str) {
            this.nextToken = str;
            return this;
        }

        public ListReactionsRequestBuilder sort(String str) {
            this.sort = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListReactionsRequest.ListReactionsRequestBuilder(topic=");
            outline107.append(this.topic);
            outline107.append(", groupId=");
            outline107.append(this.groupId);
            outline107.append(", nextToken=");
            outline107.append(this.nextToken);
            outline107.append(", maxResults=");
            outline107.append(this.maxResults);
            outline107.append(", sort=");
            outline107.append(this.sort);
            outline107.append(", direction=");
            outline107.append(this.direction);
            outline107.append(", includeCoverPhotos=");
            outline107.append(this.includeCoverPhotos);
            outline107.append(", kind=");
            outline107.append(this.kind);
            outline107.append(", kindSet=");
            return GeneratedOutlineSupport1.outline91(outline107, this.kindSet, ")");
        }

        public ListReactionsRequestBuilder topic(String str) {
            this.topic = str;
            return this;
        }
    }

    ListReactionsRequest(String str, String str2, String str3, Integer num, String str4, String str5, boolean z, String str6, String str7) {
        this.topic = str;
        this.groupId = str2;
        this.nextToken = str3;
        this.maxResults = num;
        this.sort = str4;
        this.direction = str5;
        this.includeCoverPhotos = z;
        this.kind = str6;
        this.kindSet = str7;
    }

    public static ListReactionsRequestBuilder builder() {
        return new ListReactionsRequestBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ListReactionsRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListReactionsRequest)) {
            return false;
        }
        ListReactionsRequest listReactionsRequest = (ListReactionsRequest) obj;
        if (!listReactionsRequest.canEqual(this)) {
            return false;
        }
        String topic = getTopic();
        String topic2 = listReactionsRequest.getTopic();
        if (topic != null ? !topic.equals(topic2) : topic2 != null) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = listReactionsRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listReactionsRequest.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Integer maxResults = getMaxResults();
        Integer maxResults2 = listReactionsRequest.getMaxResults();
        if (maxResults != null ? !maxResults.equals(maxResults2) : maxResults2 != null) {
            return false;
        }
        String sort = getSort();
        String sort2 = listReactionsRequest.getSort();
        if (sort != null ? !sort.equals(sort2) : sort2 != null) {
            return false;
        }
        String direction = getDirection();
        String direction2 = listReactionsRequest.getDirection();
        if (direction != null ? !direction.equals(direction2) : direction2 != null) {
            return false;
        }
        if (isIncludeCoverPhotos() != listReactionsRequest.isIncludeCoverPhotos()) {
            return false;
        }
        String kind = getKind();
        String kind2 = listReactionsRequest.getKind();
        if (kind != null ? !kind.equals(kind2) : kind2 != null) {
            return false;
        }
        String kindSet = getKindSet();
        String kindSet2 = listReactionsRequest.getKindSet();
        return kindSet != null ? kindSet.equals(kindSet2) : kindSet2 == null;
    }

    public String getDirection() {
        return this.direction;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getKind() {
        return this.kind;
    }

    public String getKindSet() {
        return this.kindSet;
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

    public String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        String topic = getTopic();
        int i = 43;
        int hashCode = topic == null ? 43 : topic.hashCode();
        String groupId = getGroupId();
        int hashCode2 = ((hashCode + 59) * 59) + (groupId == null ? 43 : groupId.hashCode());
        String nextToken = getNextToken();
        int hashCode3 = (hashCode2 * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        Integer maxResults = getMaxResults();
        int hashCode4 = (hashCode3 * 59) + (maxResults == null ? 43 : maxResults.hashCode());
        String sort = getSort();
        int hashCode5 = (hashCode4 * 59) + (sort == null ? 43 : sort.hashCode());
        String direction = getDirection();
        int hashCode6 = (((hashCode5 * 59) + (direction == null ? 43 : direction.hashCode())) * 59) + (isIncludeCoverPhotos() ? 79 : 97);
        String kind = getKind();
        int hashCode7 = (hashCode6 * 59) + (kind == null ? 43 : kind.hashCode());
        String kindSet = getKindSet();
        int i2 = hashCode7 * 59;
        if (kindSet != null) {
            i = kindSet.hashCode();
        }
        return i2 + i;
    }

    public boolean isIncludeCoverPhotos() {
        return this.includeCoverPhotos;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListReactionsRequest(topic=");
        outline107.append(getTopic());
        outline107.append(", groupId=");
        outline107.append(getGroupId());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(", maxResults=");
        outline107.append(getMaxResults());
        outline107.append(", sort=");
        outline107.append(getSort());
        outline107.append(", direction=");
        outline107.append(getDirection());
        outline107.append(", includeCoverPhotos=");
        outline107.append(isIncludeCoverPhotos());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(", kindSet=");
        outline107.append(getKindSet());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest instanceof ListReactionsRequest) {
            return cloudDriveRequest.hashCode() - hashCode();
        }
        return -1;
    }
}
