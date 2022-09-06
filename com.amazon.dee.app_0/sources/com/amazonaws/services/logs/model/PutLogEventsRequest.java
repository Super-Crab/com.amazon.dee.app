package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class PutLogEventsRequest extends AmazonWebServiceRequest implements Serializable {
    private List<InputLogEvent> logEvents;
    private String logGroupName;
    private String logStreamName;
    private String sequenceToken;

    public PutLogEventsRequest() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutLogEventsRequest)) {
            return false;
        }
        PutLogEventsRequest putLogEventsRequest = (PutLogEventsRequest) obj;
        if ((putLogEventsRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (putLogEventsRequest.getLogGroupName() != null && !putLogEventsRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((putLogEventsRequest.getLogStreamName() == null) ^ (getLogStreamName() == null)) {
            return false;
        }
        if (putLogEventsRequest.getLogStreamName() != null && !putLogEventsRequest.getLogStreamName().equals(getLogStreamName())) {
            return false;
        }
        if ((putLogEventsRequest.getLogEvents() == null) ^ (getLogEvents() == null)) {
            return false;
        }
        if (putLogEventsRequest.getLogEvents() != null && !putLogEventsRequest.getLogEvents().equals(getLogEvents())) {
            return false;
        }
        if ((putLogEventsRequest.getSequenceToken() == null) ^ (getSequenceToken() == null)) {
            return false;
        }
        return putLogEventsRequest.getSequenceToken() == null || putLogEventsRequest.getSequenceToken().equals(getSequenceToken());
    }

    public List<InputLogEvent> getLogEvents() {
        return this.logEvents;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public String getLogStreamName() {
        return this.logStreamName;
    }

    public String getSequenceToken() {
        return this.sequenceToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getLogStreamName() == null ? 0 : getLogStreamName().hashCode())) * 31) + (getLogEvents() == null ? 0 : getLogEvents().hashCode())) * 31;
        if (getSequenceToken() != null) {
            i = getSequenceToken().hashCode();
        }
        return hashCode + i;
    }

    public void setLogEvents(Collection<InputLogEvent> collection) {
        if (collection == null) {
            this.logEvents = null;
        } else {
            this.logEvents = new ArrayList(collection);
        }
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setLogStreamName(String str) {
        this.logStreamName = str;
    }

    public void setSequenceToken(String str) {
        this.sequenceToken = str;
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
        if (getLogEvents() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("logEvents: ");
            outline1074.append(getLogEvents());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getSequenceToken() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("sequenceToken: ");
            outline1075.append(getSequenceToken());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutLogEventsRequest withLogEvents(InputLogEvent... inputLogEventArr) {
        if (getLogEvents() == null) {
            this.logEvents = new ArrayList(inputLogEventArr.length);
        }
        for (InputLogEvent inputLogEvent : inputLogEventArr) {
            this.logEvents.add(inputLogEvent);
        }
        return this;
    }

    public PutLogEventsRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public PutLogEventsRequest withLogStreamName(String str) {
        this.logStreamName = str;
        return this;
    }

    public PutLogEventsRequest withSequenceToken(String str) {
        this.sequenceToken = str;
        return this;
    }

    public PutLogEventsRequest(String str, String str2, List<InputLogEvent> list) {
        setLogGroupName(str);
        setLogStreamName(str2);
        setLogEvents(list);
    }

    public PutLogEventsRequest withLogEvents(Collection<InputLogEvent> collection) {
        setLogEvents(collection);
        return this;
    }
}
