package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DescribeLogGroupsResult implements Serializable {
    private List<LogGroup> logGroups;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeLogGroupsResult)) {
            return false;
        }
        DescribeLogGroupsResult describeLogGroupsResult = (DescribeLogGroupsResult) obj;
        if ((describeLogGroupsResult.getLogGroups() == null) ^ (getLogGroups() == null)) {
            return false;
        }
        if (describeLogGroupsResult.getLogGroups() != null && !describeLogGroupsResult.getLogGroups().equals(getLogGroups())) {
            return false;
        }
        if ((describeLogGroupsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return describeLogGroupsResult.getNextToken() == null || describeLogGroupsResult.getNextToken().equals(getNextToken());
    }

    public List<LogGroup> getLogGroups() {
        return this.logGroups;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogGroups() == null ? 0 : getLogGroups().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setLogGroups(Collection<LogGroup> collection) {
        if (collection == null) {
            this.logGroups = null;
        } else {
            this.logGroups = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroups() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroups: ");
            outline1072.append(getLogGroups());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeLogGroupsResult withLogGroups(LogGroup... logGroupArr) {
        if (getLogGroups() == null) {
            this.logGroups = new ArrayList(logGroupArr.length);
        }
        for (LogGroup logGroup : logGroupArr) {
            this.logGroups.add(logGroup);
        }
        return this;
    }

    public DescribeLogGroupsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeLogGroupsResult withLogGroups(Collection<LogGroup> collection) {
        setLogGroups(collection);
        return this;
    }
}
