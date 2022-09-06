package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutLogEventsResult implements Serializable {
    private String nextSequenceToken;
    private RejectedLogEventsInfo rejectedLogEventsInfo;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutLogEventsResult)) {
            return false;
        }
        PutLogEventsResult putLogEventsResult = (PutLogEventsResult) obj;
        if ((putLogEventsResult.getNextSequenceToken() == null) ^ (getNextSequenceToken() == null)) {
            return false;
        }
        if (putLogEventsResult.getNextSequenceToken() != null && !putLogEventsResult.getNextSequenceToken().equals(getNextSequenceToken())) {
            return false;
        }
        if ((putLogEventsResult.getRejectedLogEventsInfo() == null) ^ (getRejectedLogEventsInfo() == null)) {
            return false;
        }
        return putLogEventsResult.getRejectedLogEventsInfo() == null || putLogEventsResult.getRejectedLogEventsInfo().equals(getRejectedLogEventsInfo());
    }

    public String getNextSequenceToken() {
        return this.nextSequenceToken;
    }

    public RejectedLogEventsInfo getRejectedLogEventsInfo() {
        return this.rejectedLogEventsInfo;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getNextSequenceToken() == null ? 0 : getNextSequenceToken().hashCode()) + 31) * 31;
        if (getRejectedLogEventsInfo() != null) {
            i = getRejectedLogEventsInfo().hashCode();
        }
        return hashCode + i;
    }

    public void setNextSequenceToken(String str) {
        this.nextSequenceToken = str;
    }

    public void setRejectedLogEventsInfo(RejectedLogEventsInfo rejectedLogEventsInfo) {
        this.rejectedLogEventsInfo = rejectedLogEventsInfo;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getNextSequenceToken() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("nextSequenceToken: ");
            outline1072.append(getNextSequenceToken());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRejectedLogEventsInfo() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("rejectedLogEventsInfo: ");
            outline1073.append(getRejectedLogEventsInfo());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutLogEventsResult withNextSequenceToken(String str) {
        this.nextSequenceToken = str;
        return this;
    }

    public PutLogEventsResult withRejectedLogEventsInfo(RejectedLogEventsInfo rejectedLogEventsInfo) {
        this.rejectedLogEventsInfo = rejectedLogEventsInfo;
        return this;
    }
}
