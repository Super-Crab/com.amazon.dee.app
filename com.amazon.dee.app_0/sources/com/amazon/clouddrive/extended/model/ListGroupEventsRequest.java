package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class ListGroupEventsRequest implements CloudDriveRequest {
    private String addedBy;
    private String direction;
    private String groupId;
    private String kindSet;
    private String lang;
    private Integer maxCovers;
    private Integer maxResults;
    private String nextToken;
    private String previousToken;
    private String sort;

    protected boolean canEqual(Object obj) {
        return obj instanceof ListGroupEventsRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListGroupEventsRequest)) {
            return false;
        }
        ListGroupEventsRequest listGroupEventsRequest = (ListGroupEventsRequest) obj;
        if (!listGroupEventsRequest.canEqual(this)) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = listGroupEventsRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String addedBy = getAddedBy();
        String addedBy2 = listGroupEventsRequest.getAddedBy();
        if (addedBy != null ? !addedBy.equals(addedBy2) : addedBy2 != null) {
            return false;
        }
        String sort = getSort();
        String sort2 = listGroupEventsRequest.getSort();
        if (sort != null ? !sort.equals(sort2) : sort2 != null) {
            return false;
        }
        String direction = getDirection();
        String direction2 = listGroupEventsRequest.getDirection();
        if (direction != null ? !direction.equals(direction2) : direction2 != null) {
            return false;
        }
        String kindSet = getKindSet();
        String kindSet2 = listGroupEventsRequest.getKindSet();
        if (kindSet != null ? !kindSet.equals(kindSet2) : kindSet2 != null) {
            return false;
        }
        String previousToken = getPreviousToken();
        String previousToken2 = listGroupEventsRequest.getPreviousToken();
        if (previousToken != null ? !previousToken.equals(previousToken2) : previousToken2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listGroupEventsRequest.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Integer maxResults = getMaxResults();
        Integer maxResults2 = listGroupEventsRequest.getMaxResults();
        if (maxResults != null ? !maxResults.equals(maxResults2) : maxResults2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = listGroupEventsRequest.getLang();
        if (lang != null ? !lang.equals(lang2) : lang2 != null) {
            return false;
        }
        Integer maxCovers = getMaxCovers();
        Integer maxCovers2 = listGroupEventsRequest.getMaxCovers();
        return maxCovers != null ? maxCovers.equals(maxCovers2) : maxCovers2 == null;
    }

    public String getAddedBy() {
        return this.addedBy;
    }

    public String getDirection() {
        return this.direction;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getKindSet() {
        return this.kindSet;
    }

    public String getLang() {
        return this.lang;
    }

    public Integer getMaxCovers() {
        return this.maxCovers;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getPreviousToken() {
        return this.previousToken;
    }

    public String getSort() {
        return this.sort;
    }

    public int hashCode() {
        String groupId = getGroupId();
        int i = 43;
        int hashCode = groupId == null ? 43 : groupId.hashCode();
        String addedBy = getAddedBy();
        int hashCode2 = ((hashCode + 59) * 59) + (addedBy == null ? 43 : addedBy.hashCode());
        String sort = getSort();
        int hashCode3 = (hashCode2 * 59) + (sort == null ? 43 : sort.hashCode());
        String direction = getDirection();
        int hashCode4 = (hashCode3 * 59) + (direction == null ? 43 : direction.hashCode());
        String kindSet = getKindSet();
        int hashCode5 = (hashCode4 * 59) + (kindSet == null ? 43 : kindSet.hashCode());
        String previousToken = getPreviousToken();
        int hashCode6 = (hashCode5 * 59) + (previousToken == null ? 43 : previousToken.hashCode());
        String nextToken = getNextToken();
        int hashCode7 = (hashCode6 * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        Integer maxResults = getMaxResults();
        int hashCode8 = (hashCode7 * 59) + (maxResults == null ? 43 : maxResults.hashCode());
        String lang = getLang();
        int hashCode9 = (hashCode8 * 59) + (lang == null ? 43 : lang.hashCode());
        Integer maxCovers = getMaxCovers();
        int i2 = hashCode9 * 59;
        if (maxCovers != null) {
            i = maxCovers.hashCode();
        }
        return i2 + i;
    }

    public void setAddedBy(String str) {
        this.addedBy = str;
    }

    public void setDirection(String str) {
        this.direction = str;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setKindSet(String str) {
        this.kindSet = str;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setMaxCovers(Integer num) {
        this.maxCovers = num;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setPreviousToken(String str) {
        this.previousToken = str;
    }

    public void setSort(String str) {
        this.sort = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListGroupEventsRequest(groupId=");
        outline107.append(getGroupId());
        outline107.append(", addedBy=");
        outline107.append(getAddedBy());
        outline107.append(", sort=");
        outline107.append(getSort());
        outline107.append(", direction=");
        outline107.append(getDirection());
        outline107.append(", kindSet=");
        outline107.append(getKindSet());
        outline107.append(", previousToken=");
        outline107.append(getPreviousToken());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(", maxResults=");
        outline107.append(getMaxResults());
        outline107.append(", lang=");
        outline107.append(getLang());
        outline107.append(", maxCovers=");
        outline107.append(getMaxCovers());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest instanceof ListGroupEventsRequest) {
            return cloudDriveRequest.hashCode() - hashCode();
        }
        return -1;
    }
}
