package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class ListViolationEventsRequest extends AmazonWebServiceRequest implements Serializable {
    private Date endTime;
    private Integer maxResults;
    private String nextToken;
    private String securityProfileName;
    private Date startTime;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListViolationEventsRequest)) {
            return false;
        }
        ListViolationEventsRequest listViolationEventsRequest = (ListViolationEventsRequest) obj;
        if ((listViolationEventsRequest.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (listViolationEventsRequest.getStartTime() != null && !listViolationEventsRequest.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((listViolationEventsRequest.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        if (listViolationEventsRequest.getEndTime() != null && !listViolationEventsRequest.getEndTime().equals(getEndTime())) {
            return false;
        }
        if ((listViolationEventsRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (listViolationEventsRequest.getThingName() != null && !listViolationEventsRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((listViolationEventsRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (listViolationEventsRequest.getSecurityProfileName() != null && !listViolationEventsRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((listViolationEventsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listViolationEventsRequest.getNextToken() != null && !listViolationEventsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listViolationEventsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listViolationEventsRequest.getMaxResults() == null || listViolationEventsRequest.getMaxResults().equals(getMaxResults());
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getStartTime() == null ? 0 : getStartTime().hashCode()) + 31) * 31) + (getEndTime() == null ? 0 : getEndTime().hashCode())) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getMaxResults() != null) {
            i = getMaxResults().hashCode();
        }
        return hashCode + i;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStartTime() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("startTime: ");
            outline1072.append(getStartTime());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getEndTime() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("endTime: ");
            outline1073.append(getEndTime());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1074.append(getThingName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getSecurityProfileName() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("securityProfileName: ");
            outline1075.append(getSecurityProfileName());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1076.append(getNextToken());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1077.append(getMaxResults());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListViolationEventsRequest withEndTime(Date date) {
        this.endTime = date;
        return this;
    }

    public ListViolationEventsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListViolationEventsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListViolationEventsRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public ListViolationEventsRequest withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public ListViolationEventsRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
