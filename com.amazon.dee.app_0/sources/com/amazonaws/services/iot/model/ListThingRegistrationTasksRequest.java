package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListThingRegistrationTasksRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String status;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingRegistrationTasksRequest)) {
            return false;
        }
        ListThingRegistrationTasksRequest listThingRegistrationTasksRequest = (ListThingRegistrationTasksRequest) obj;
        if ((listThingRegistrationTasksRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingRegistrationTasksRequest.getNextToken() != null && !listThingRegistrationTasksRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingRegistrationTasksRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listThingRegistrationTasksRequest.getMaxResults() != null && !listThingRegistrationTasksRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listThingRegistrationTasksRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return listThingRegistrationTasksRequest.getStatus() == null || listThingRegistrationTasksRequest.getStatus().equals(getStatus());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31;
        if (getStatus() != null) {
            i = getStatus().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setStatus(String str) {
        this.status = str;
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
        if (getStatus() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("status: ");
            outline1074.append(getStatus());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingRegistrationTasksRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListThingRegistrationTasksRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingRegistrationTasksRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status.toString();
    }

    public ListThingRegistrationTasksRequest withStatus(Status status) {
        this.status = status.toString();
        return this;
    }
}
