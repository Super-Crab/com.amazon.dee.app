package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class FilterLogEventsRequest extends AmazonWebServiceRequest implements Serializable {
    private Long endTime;
    private String filterPattern;
    private Boolean interleaved;
    private Integer limit;
    private String logGroupName;
    private String logStreamNamePrefix;
    private List<String> logStreamNames;
    private String nextToken;
    private Long startTime;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FilterLogEventsRequest)) {
            return false;
        }
        FilterLogEventsRequest filterLogEventsRequest = (FilterLogEventsRequest) obj;
        if ((filterLogEventsRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (filterLogEventsRequest.getLogGroupName() != null && !filterLogEventsRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((filterLogEventsRequest.getLogStreamNames() == null) ^ (getLogStreamNames() == null)) {
            return false;
        }
        if (filterLogEventsRequest.getLogStreamNames() != null && !filterLogEventsRequest.getLogStreamNames().equals(getLogStreamNames())) {
            return false;
        }
        if ((filterLogEventsRequest.getLogStreamNamePrefix() == null) ^ (getLogStreamNamePrefix() == null)) {
            return false;
        }
        if (filterLogEventsRequest.getLogStreamNamePrefix() != null && !filterLogEventsRequest.getLogStreamNamePrefix().equals(getLogStreamNamePrefix())) {
            return false;
        }
        if ((filterLogEventsRequest.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (filterLogEventsRequest.getStartTime() != null && !filterLogEventsRequest.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((filterLogEventsRequest.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        if (filterLogEventsRequest.getEndTime() != null && !filterLogEventsRequest.getEndTime().equals(getEndTime())) {
            return false;
        }
        if ((filterLogEventsRequest.getFilterPattern() == null) ^ (getFilterPattern() == null)) {
            return false;
        }
        if (filterLogEventsRequest.getFilterPattern() != null && !filterLogEventsRequest.getFilterPattern().equals(getFilterPattern())) {
            return false;
        }
        if ((filterLogEventsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (filterLogEventsRequest.getNextToken() != null && !filterLogEventsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((filterLogEventsRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        if (filterLogEventsRequest.getLimit() != null && !filterLogEventsRequest.getLimit().equals(getLimit())) {
            return false;
        }
        if ((filterLogEventsRequest.getInterleaved() == null) ^ (getInterleaved() == null)) {
            return false;
        }
        return filterLogEventsRequest.getInterleaved() == null || filterLogEventsRequest.getInterleaved().equals(getInterleaved());
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public String getFilterPattern() {
        return this.filterPattern;
    }

    public Boolean getInterleaved() {
        return this.interleaved;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public String getLogStreamNamePrefix() {
        return this.logStreamNamePrefix;
    }

    public List<String> getLogStreamNames() {
        return this.logStreamNames;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getLogStreamNames() == null ? 0 : getLogStreamNames().hashCode())) * 31) + (getLogStreamNamePrefix() == null ? 0 : getLogStreamNamePrefix().hashCode())) * 31) + (getStartTime() == null ? 0 : getStartTime().hashCode())) * 31) + (getEndTime() == null ? 0 : getEndTime().hashCode())) * 31) + (getFilterPattern() == null ? 0 : getFilterPattern().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getLimit() == null ? 0 : getLimit().hashCode())) * 31;
        if (getInterleaved() != null) {
            i = getInterleaved().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isInterleaved() {
        return this.interleaved;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public void setFilterPattern(String str) {
        this.filterPattern = str;
    }

    public void setInterleaved(Boolean bool) {
        this.interleaved = bool;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setLogStreamNamePrefix(String str) {
        this.logStreamNamePrefix = str;
    }

    public void setLogStreamNames(Collection<String> collection) {
        if (collection == null) {
            this.logStreamNames = null;
        } else {
            this.logStreamNames = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
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
        if (getLogStreamNames() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("logStreamNames: ");
            outline1073.append(getLogStreamNames());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLogStreamNamePrefix() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("logStreamNamePrefix: ");
            outline1074.append(getLogStreamNamePrefix());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStartTime() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("startTime: ");
            outline1075.append(getStartTime());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getEndTime() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("endTime: ");
            outline1076.append(getEndTime());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getFilterPattern() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("filterPattern: ");
            outline1077.append(getFilterPattern());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1078.append(getNextToken());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("limit: ");
            outline1079.append(getLimit());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getInterleaved() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("interleaved: ");
            outline10710.append(getInterleaved());
            outline107.append(outline10710.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public FilterLogEventsRequest withEndTime(Long l) {
        this.endTime = l;
        return this;
    }

    public FilterLogEventsRequest withFilterPattern(String str) {
        this.filterPattern = str;
        return this;
    }

    public FilterLogEventsRequest withInterleaved(Boolean bool) {
        this.interleaved = bool;
        return this;
    }

    public FilterLogEventsRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public FilterLogEventsRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public FilterLogEventsRequest withLogStreamNamePrefix(String str) {
        this.logStreamNamePrefix = str;
        return this;
    }

    public FilterLogEventsRequest withLogStreamNames(String... strArr) {
        if (getLogStreamNames() == null) {
            this.logStreamNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.logStreamNames.add(str);
        }
        return this;
    }

    public FilterLogEventsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public FilterLogEventsRequest withStartTime(Long l) {
        this.startTime = l;
        return this;
    }

    public FilterLogEventsRequest withLogStreamNames(Collection<String> collection) {
        setLogStreamNames(collection);
        return this;
    }
}
