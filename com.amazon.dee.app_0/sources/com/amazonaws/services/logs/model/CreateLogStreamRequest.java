package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateLogStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String logGroupName;
    private String logStreamName;

    public CreateLogStreamRequest() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateLogStreamRequest)) {
            return false;
        }
        CreateLogStreamRequest createLogStreamRequest = (CreateLogStreamRequest) obj;
        if ((createLogStreamRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (createLogStreamRequest.getLogGroupName() != null && !createLogStreamRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((createLogStreamRequest.getLogStreamName() == null) ^ (getLogStreamName() == null)) {
            return false;
        }
        return createLogStreamRequest.getLogStreamName() == null || createLogStreamRequest.getLogStreamName().equals(getLogStreamName());
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public String getLogStreamName() {
        return this.logStreamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31;
        if (getLogStreamName() != null) {
            i = getLogStreamName().hashCode();
        }
        return hashCode + i;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setLogStreamName(String str) {
        this.logStreamName = str;
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
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateLogStreamRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public CreateLogStreamRequest withLogStreamName(String str) {
        this.logStreamName = str;
        return this;
    }

    public CreateLogStreamRequest(String str, String str2) {
        setLogGroupName(str);
        setLogStreamName(str2);
    }
}
