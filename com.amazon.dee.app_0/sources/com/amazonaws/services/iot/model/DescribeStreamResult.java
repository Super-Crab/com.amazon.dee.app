package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeStreamResult implements Serializable {
    private StreamInfo streamInfo;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeStreamResult)) {
            return false;
        }
        DescribeStreamResult describeStreamResult = (DescribeStreamResult) obj;
        if ((describeStreamResult.getStreamInfo() == null) ^ (getStreamInfo() == null)) {
            return false;
        }
        return describeStreamResult.getStreamInfo() == null || describeStreamResult.getStreamInfo().equals(getStreamInfo());
    }

    public StreamInfo getStreamInfo() {
        return this.streamInfo;
    }

    public int hashCode() {
        return 31 + (getStreamInfo() == null ? 0 : getStreamInfo().hashCode());
    }

    public void setStreamInfo(StreamInfo streamInfo) {
        this.streamInfo = streamInfo;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamInfo() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("streamInfo: ");
            outline1072.append(getStreamInfo());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeStreamResult withStreamInfo(StreamInfo streamInfo) {
        this.streamInfo = streamInfo;
        return this;
    }
}
