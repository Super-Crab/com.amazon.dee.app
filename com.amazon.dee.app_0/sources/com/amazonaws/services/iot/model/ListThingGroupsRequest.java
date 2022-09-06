package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListThingGroupsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String namePrefixFilter;
    private String nextToken;
    private String parentGroup;
    private Boolean recursive;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingGroupsRequest)) {
            return false;
        }
        ListThingGroupsRequest listThingGroupsRequest = (ListThingGroupsRequest) obj;
        if ((listThingGroupsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingGroupsRequest.getNextToken() != null && !listThingGroupsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingGroupsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listThingGroupsRequest.getMaxResults() != null && !listThingGroupsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listThingGroupsRequest.getParentGroup() == null) ^ (getParentGroup() == null)) {
            return false;
        }
        if (listThingGroupsRequest.getParentGroup() != null && !listThingGroupsRequest.getParentGroup().equals(getParentGroup())) {
            return false;
        }
        if ((listThingGroupsRequest.getNamePrefixFilter() == null) ^ (getNamePrefixFilter() == null)) {
            return false;
        }
        if (listThingGroupsRequest.getNamePrefixFilter() != null && !listThingGroupsRequest.getNamePrefixFilter().equals(getNamePrefixFilter())) {
            return false;
        }
        if ((listThingGroupsRequest.getRecursive() == null) ^ (getRecursive() == null)) {
            return false;
        }
        return listThingGroupsRequest.getRecursive() == null || listThingGroupsRequest.getRecursive().equals(getRecursive());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNamePrefixFilter() {
        return this.namePrefixFilter;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getParentGroup() {
        return this.parentGroup;
    }

    public Boolean getRecursive() {
        return this.recursive;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getParentGroup() == null ? 0 : getParentGroup().hashCode())) * 31) + (getNamePrefixFilter() == null ? 0 : getNamePrefixFilter().hashCode())) * 31;
        if (getRecursive() != null) {
            i = getRecursive().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isRecursive() {
        return this.recursive;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNamePrefixFilter(String str) {
        this.namePrefixFilter = str;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setParentGroup(String str) {
        this.parentGroup = str;
    }

    public void setRecursive(Boolean bool) {
        this.recursive = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getNextToken() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1072.append(getNextToken());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1073.append(getMaxResults());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getParentGroup() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("parentGroup: ");
            outline1074.append(getParentGroup());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getNamePrefixFilter() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("namePrefixFilter: ");
            outline1075.append(getNamePrefixFilter());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getRecursive() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("recursive: ");
            outline1076.append(getRecursive());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingGroupsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListThingGroupsRequest withNamePrefixFilter(String str) {
        this.namePrefixFilter = str;
        return this;
    }

    public ListThingGroupsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingGroupsRequest withParentGroup(String str) {
        this.parentGroup = str;
        return this;
    }

    public ListThingGroupsRequest withRecursive(Boolean bool) {
        this.recursive = bool;
        return this;
    }
}
