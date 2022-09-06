package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String streamId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteStreamRequest)) {
            return false;
        }
        DeleteStreamRequest deleteStreamRequest = (DeleteStreamRequest) obj;
        if ((deleteStreamRequest.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        return deleteStreamRequest.getStreamId() == null || deleteStreamRequest.getStreamId().equals(getStreamId());
    }

    public String getStreamId() {
        return this.streamId;
    }

    public int hashCode() {
        return 31 + (getStreamId() == null ? 0 : getStreamId().hashCode());
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("streamId: ");
            outline1072.append(getStreamId());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteStreamRequest withStreamId(String str) {
        this.streamId = str;
        return this;
    }
}
