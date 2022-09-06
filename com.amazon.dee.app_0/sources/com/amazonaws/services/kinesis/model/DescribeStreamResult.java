package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeStreamResult implements Serializable {
    private StreamDescription streamDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeStreamResult)) {
            return false;
        }
        DescribeStreamResult describeStreamResult = (DescribeStreamResult) obj;
        if ((describeStreamResult.getStreamDescription() == null) ^ (getStreamDescription() == null)) {
            return false;
        }
        return describeStreamResult.getStreamDescription() == null || describeStreamResult.getStreamDescription().equals(getStreamDescription());
    }

    public StreamDescription getStreamDescription() {
        return this.streamDescription;
    }

    public int hashCode() {
        return 31 + (getStreamDescription() == null ? 0 : getStreamDescription().hashCode());
    }

    public void setStreamDescription(StreamDescription streamDescription) {
        this.streamDescription = streamDescription;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamDescription() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamDescription: ");
            outline1072.append(getStreamDescription());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeStreamResult withStreamDescription(StreamDescription streamDescription) {
        this.streamDescription = streamDescription;
        return this;
    }
}
