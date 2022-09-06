package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SetLoggingOptionsRequest extends AmazonWebServiceRequest implements Serializable {
    private LoggingOptionsPayload loggingOptionsPayload;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SetLoggingOptionsRequest)) {
            return false;
        }
        SetLoggingOptionsRequest setLoggingOptionsRequest = (SetLoggingOptionsRequest) obj;
        if ((setLoggingOptionsRequest.getLoggingOptionsPayload() == null) ^ (getLoggingOptionsPayload() == null)) {
            return false;
        }
        return setLoggingOptionsRequest.getLoggingOptionsPayload() == null || setLoggingOptionsRequest.getLoggingOptionsPayload().equals(getLoggingOptionsPayload());
    }

    public LoggingOptionsPayload getLoggingOptionsPayload() {
        return this.loggingOptionsPayload;
    }

    public int hashCode() {
        return 31 + (getLoggingOptionsPayload() == null ? 0 : getLoggingOptionsPayload().hashCode());
    }

    public void setLoggingOptionsPayload(LoggingOptionsPayload loggingOptionsPayload) {
        this.loggingOptionsPayload = loggingOptionsPayload;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLoggingOptionsPayload() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("loggingOptionsPayload: ");
            outline1072.append(getLoggingOptionsPayload());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SetLoggingOptionsRequest withLoggingOptionsPayload(LoggingOptionsPayload loggingOptionsPayload) {
        this.loggingOptionsPayload = loggingOptionsPayload;
        return this;
    }
}
