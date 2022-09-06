package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeStreamSummaryResult implements Serializable {
    private StreamDescriptionSummary streamDescriptionSummary;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeStreamSummaryResult)) {
            return false;
        }
        DescribeStreamSummaryResult describeStreamSummaryResult = (DescribeStreamSummaryResult) obj;
        if ((describeStreamSummaryResult.getStreamDescriptionSummary() == null) ^ (getStreamDescriptionSummary() == null)) {
            return false;
        }
        return describeStreamSummaryResult.getStreamDescriptionSummary() == null || describeStreamSummaryResult.getStreamDescriptionSummary().equals(getStreamDescriptionSummary());
    }

    public StreamDescriptionSummary getStreamDescriptionSummary() {
        return this.streamDescriptionSummary;
    }

    public int hashCode() {
        return 31 + (getStreamDescriptionSummary() == null ? 0 : getStreamDescriptionSummary().hashCode());
    }

    public void setStreamDescriptionSummary(StreamDescriptionSummary streamDescriptionSummary) {
        this.streamDescriptionSummary = streamDescriptionSummary;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamDescriptionSummary() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamDescriptionSummary: ");
            outline1072.append(getStreamDescriptionSummary());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeStreamSummaryResult withStreamDescriptionSummary(StreamDescriptionSummary streamDescriptionSummary) {
        this.streamDescriptionSummary = streamDescriptionSummary;
        return this;
    }
}
