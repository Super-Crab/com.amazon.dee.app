package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class InputLogEvent implements Serializable {
    private String message;
    private Long timestamp;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof InputLogEvent)) {
            return false;
        }
        InputLogEvent inputLogEvent = (InputLogEvent) obj;
        if ((inputLogEvent.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        if (inputLogEvent.getTimestamp() != null && !inputLogEvent.getTimestamp().equals(getTimestamp())) {
            return false;
        }
        if ((inputLogEvent.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        return inputLogEvent.getMessage() == null || inputLogEvent.getMessage().equals(getMessage());
    }

    public String getMessage() {
        return this.message;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTimestamp() == null ? 0 : getTimestamp().hashCode()) + 31) * 31;
        if (getMessage() != null) {
            i = getMessage().hashCode();
        }
        return hashCode + i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTimestamp() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("timestamp: ");
            outline1072.append(getTimestamp());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMessage() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("message: ");
            outline1073.append(getMessage());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public InputLogEvent withMessage(String str) {
        this.message = str;
        return this;
    }

    public InputLogEvent withTimestamp(Long l) {
        this.timestamp = l;
        return this;
    }
}
