package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DescribeLogStreamsResult implements Serializable {
    private List<LogStream> logStreams;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeLogStreamsResult)) {
            return false;
        }
        DescribeLogStreamsResult describeLogStreamsResult = (DescribeLogStreamsResult) obj;
        if ((describeLogStreamsResult.getLogStreams() == null) ^ (getLogStreams() == null)) {
            return false;
        }
        if (describeLogStreamsResult.getLogStreams() != null && !describeLogStreamsResult.getLogStreams().equals(getLogStreams())) {
            return false;
        }
        if ((describeLogStreamsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return describeLogStreamsResult.getNextToken() == null || describeLogStreamsResult.getNextToken().equals(getNextToken());
    }

    public List<LogStream> getLogStreams() {
        return this.logStreams;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogStreams() == null ? 0 : getLogStreams().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setLogStreams(Collection<LogStream> collection) {
        if (collection == null) {
            this.logStreams = null;
        } else {
            this.logStreams = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogStreams() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logStreams: ");
            outline1072.append(getLogStreams());
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

    public DescribeLogStreamsResult withLogStreams(LogStream... logStreamArr) {
        if (getLogStreams() == null) {
            this.logStreams = new ArrayList(logStreamArr.length);
        }
        for (LogStream logStream : logStreamArr) {
            this.logStreams.add(logStream);
        }
        return this;
    }

    public DescribeLogStreamsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeLogStreamsResult withLogStreams(Collection<LogStream> collection) {
        setLogStreams(collection);
        return this;
    }
}
