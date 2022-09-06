package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RejectedLogEventsInfo implements Serializable {
    private Integer expiredLogEventEndIndex;
    private Integer tooNewLogEventStartIndex;
    private Integer tooOldLogEventEndIndex;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RejectedLogEventsInfo)) {
            return false;
        }
        RejectedLogEventsInfo rejectedLogEventsInfo = (RejectedLogEventsInfo) obj;
        if ((rejectedLogEventsInfo.getTooNewLogEventStartIndex() == null) ^ (getTooNewLogEventStartIndex() == null)) {
            return false;
        }
        if (rejectedLogEventsInfo.getTooNewLogEventStartIndex() != null && !rejectedLogEventsInfo.getTooNewLogEventStartIndex().equals(getTooNewLogEventStartIndex())) {
            return false;
        }
        if ((rejectedLogEventsInfo.getTooOldLogEventEndIndex() == null) ^ (getTooOldLogEventEndIndex() == null)) {
            return false;
        }
        if (rejectedLogEventsInfo.getTooOldLogEventEndIndex() != null && !rejectedLogEventsInfo.getTooOldLogEventEndIndex().equals(getTooOldLogEventEndIndex())) {
            return false;
        }
        if ((rejectedLogEventsInfo.getExpiredLogEventEndIndex() == null) ^ (getExpiredLogEventEndIndex() == null)) {
            return false;
        }
        return rejectedLogEventsInfo.getExpiredLogEventEndIndex() == null || rejectedLogEventsInfo.getExpiredLogEventEndIndex().equals(getExpiredLogEventEndIndex());
    }

    public Integer getExpiredLogEventEndIndex() {
        return this.expiredLogEventEndIndex;
    }

    public Integer getTooNewLogEventStartIndex() {
        return this.tooNewLogEventStartIndex;
    }

    public Integer getTooOldLogEventEndIndex() {
        return this.tooOldLogEventEndIndex;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getTooNewLogEventStartIndex() == null ? 0 : getTooNewLogEventStartIndex().hashCode()) + 31) * 31) + (getTooOldLogEventEndIndex() == null ? 0 : getTooOldLogEventEndIndex().hashCode())) * 31;
        if (getExpiredLogEventEndIndex() != null) {
            i = getExpiredLogEventEndIndex().hashCode();
        }
        return hashCode + i;
    }

    public void setExpiredLogEventEndIndex(Integer num) {
        this.expiredLogEventEndIndex = num;
    }

    public void setTooNewLogEventStartIndex(Integer num) {
        this.tooNewLogEventStartIndex = num;
    }

    public void setTooOldLogEventEndIndex(Integer num) {
        this.tooOldLogEventEndIndex = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTooNewLogEventStartIndex() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("tooNewLogEventStartIndex: ");
            outline1072.append(getTooNewLogEventStartIndex());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTooOldLogEventEndIndex() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("tooOldLogEventEndIndex: ");
            outline1073.append(getTooOldLogEventEndIndex());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getExpiredLogEventEndIndex() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("expiredLogEventEndIndex: ");
            outline1074.append(getExpiredLogEventEndIndex());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RejectedLogEventsInfo withExpiredLogEventEndIndex(Integer num) {
        this.expiredLogEventEndIndex = num;
        return this;
    }

    public RejectedLogEventsInfo withTooNewLogEventStartIndex(Integer num) {
        this.tooNewLogEventStartIndex = num;
        return this;
    }

    public RejectedLogEventsInfo withTooOldLogEventEndIndex(Integer num) {
        this.tooOldLogEventEndIndex = num;
        return this;
    }
}
