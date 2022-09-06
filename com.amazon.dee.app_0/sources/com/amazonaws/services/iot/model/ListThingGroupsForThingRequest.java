package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListThingGroupsForThingRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingGroupsForThingRequest)) {
            return false;
        }
        ListThingGroupsForThingRequest listThingGroupsForThingRequest = (ListThingGroupsForThingRequest) obj;
        if ((listThingGroupsForThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (listThingGroupsForThingRequest.getThingName() != null && !listThingGroupsForThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((listThingGroupsForThingRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingGroupsForThingRequest.getNextToken() != null && !listThingGroupsForThingRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingGroupsForThingRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listThingGroupsForThingRequest.getMaxResults() == null || listThingGroupsForThingRequest.getMaxResults().equals(getMaxResults());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getMaxResults() != null) {
            i = getMaxResults().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1074.append(getMaxResults());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingGroupsForThingRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListThingGroupsForThingRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingGroupsForThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
