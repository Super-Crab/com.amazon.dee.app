package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteStreamRequest)) {
            return false;
        }
        DeleteStreamRequest deleteStreamRequest = (DeleteStreamRequest) obj;
        if ((deleteStreamRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        return deleteStreamRequest.getStreamName() == null || deleteStreamRequest.getStreamName().equals(getStreamName());
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        return 31 + (getStreamName() == null ? 0 : getStreamName().hashCode());
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1072.append(getStreamName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteStreamRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
