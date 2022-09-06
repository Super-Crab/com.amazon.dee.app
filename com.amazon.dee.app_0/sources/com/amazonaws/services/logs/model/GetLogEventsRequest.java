package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetLogEventsRequest extends AmazonWebServiceRequest implements Serializable {
    private Long endTime;
    private Integer limit;
    private String logGroupName;
    private String logStreamName;
    private String nextToken;
    private Boolean startFromHead;
    private Long startTime;

    public GetLogEventsRequest() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetLogEventsRequest)) {
            return false;
        }
        GetLogEventsRequest getLogEventsRequest = (GetLogEventsRequest) obj;
        if ((getLogEventsRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (getLogEventsRequest.getLogGroupName() != null && !getLogEventsRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((getLogEventsRequest.getLogStreamName() == null) ^ (getLogStreamName() == null)) {
            return false;
        }
        if (getLogEventsRequest.getLogStreamName() != null && !getLogEventsRequest.getLogStreamName().equals(getLogStreamName())) {
            return false;
        }
        if ((getLogEventsRequest.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (getLogEventsRequest.getStartTime() != null && !getLogEventsRequest.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((getLogEventsRequest.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        if (getLogEventsRequest.getEndTime() != null && !getLogEventsRequest.getEndTime().equals(getEndTime())) {
            return false;
        }
        if ((getLogEventsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (getLogEventsRequest.getNextToken() != null && !getLogEventsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((getLogEventsRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        if (getLogEventsRequest.getLimit() != null && !getLogEventsRequest.getLimit().equals(getLimit())) {
            return false;
        }
        if ((getLogEventsRequest.getStartFromHead() == null) ^ (getStartFromHead() == null)) {
            return false;
        }
        return getLogEventsRequest.getStartFromHead() == null || getLogEventsRequest.getStartFromHead().equals(getStartFromHead());
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public String getLogStreamName() {
        return this.logStreamName;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public Boolean getStartFromHead() {
        return this.startFromHead;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getLogStreamName() == null ? 0 : getLogStreamName().hashCode())) * 31) + (getStartTime() == null ? 0 : getStartTime().hashCode())) * 31) + (getEndTime() == null ? 0 : getEndTime().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getLimit() == null ? 0 : getLimit().hashCode())) * 31;
        if (getStartFromHead() != null) {
            i = getStartFromHead().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isStartFromHead() {
        return this.startFromHead;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setLogStreamName(String str) {
        this.logStreamName = str;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setStartFromHead(Boolean bool) {
        this.startFromHead = bool;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLogStreamName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("logStreamName: ");
            outline1073.append(getLogStreamName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getStartTime() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("startTime: ");
            outline1074.append(getStartTime());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getEndTime() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("endTime: ");
            outline1075.append(getEndTime());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1076.append(getNextToken());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("limit: ");
            outline1077.append(getLimit());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getStartFromHead() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("startFromHead: ");
            outline1078.append(getStartFromHead());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetLogEventsRequest withEndTime(Long l) {
        this.endTime = l;
        return this;
    }

    public GetLogEventsRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public GetLogEventsRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public GetLogEventsRequest withLogStreamName(String str) {
        this.logStreamName = str;
        return this;
    }

    public GetLogEventsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public GetLogEventsRequest withStartFromHead(Boolean bool) {
        this.startFromHead = bool;
        return this;
    }

    public GetLogEventsRequest withStartTime(Long l) {
        this.startTime = l;
        return this;
    }

    public GetLogEventsRequest(String str, String str2) {
        setLogGroupName(str);
        setLogStreamName(str2);
    }
}
