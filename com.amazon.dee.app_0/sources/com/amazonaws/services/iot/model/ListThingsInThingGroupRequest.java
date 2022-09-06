package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListThingsInThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private Boolean recursive;
    private String thingGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingsInThingGroupRequest)) {
            return false;
        }
        ListThingsInThingGroupRequest listThingsInThingGroupRequest = (ListThingsInThingGroupRequest) obj;
        if ((listThingsInThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (listThingsInThingGroupRequest.getThingGroupName() != null && !listThingsInThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((listThingsInThingGroupRequest.getRecursive() == null) ^ (getRecursive() == null)) {
            return false;
        }
        if (listThingsInThingGroupRequest.getRecursive() != null && !listThingsInThingGroupRequest.getRecursive().equals(getRecursive())) {
            return false;
        }
        if ((listThingsInThingGroupRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingsInThingGroupRequest.getNextToken() != null && !listThingsInThingGroupRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingsInThingGroupRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listThingsInThingGroupRequest.getMaxResults() == null || listThingsInThingGroupRequest.getMaxResults().equals(getMaxResults());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public Boolean getRecursive() {
        return this.recursive;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getRecursive() == null ? 0 : getRecursive().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getMaxResults() != null) {
            i = getMaxResults().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isRecursive() {
        return this.recursive;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setRecursive(Boolean bool) {
        this.recursive = bool;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupName: ");
            outline1072.append(getThingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRecursive() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("recursive: ");
            outline1073.append(getRecursive());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1074.append(getNextToken());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1075.append(getMaxResults());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingsInThingGroupRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListThingsInThingGroupRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingsInThingGroupRequest withRecursive(Boolean bool) {
        this.recursive = bool;
        return this;
    }

    public ListThingsInThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }
}
