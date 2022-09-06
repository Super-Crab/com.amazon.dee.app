package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SequenceNumberRange implements Serializable {
    private String endingSequenceNumber;
    private String startingSequenceNumber;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SequenceNumberRange)) {
            return false;
        }
        SequenceNumberRange sequenceNumberRange = (SequenceNumberRange) obj;
        if ((sequenceNumberRange.getStartingSequenceNumber() == null) ^ (getStartingSequenceNumber() == null)) {
            return false;
        }
        if (sequenceNumberRange.getStartingSequenceNumber() != null && !sequenceNumberRange.getStartingSequenceNumber().equals(getStartingSequenceNumber())) {
            return false;
        }
        if ((sequenceNumberRange.getEndingSequenceNumber() == null) ^ (getEndingSequenceNumber() == null)) {
            return false;
        }
        return sequenceNumberRange.getEndingSequenceNumber() == null || sequenceNumberRange.getEndingSequenceNumber().equals(getEndingSequenceNumber());
    }

    public String getEndingSequenceNumber() {
        return this.endingSequenceNumber;
    }

    public String getStartingSequenceNumber() {
        return this.startingSequenceNumber;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStartingSequenceNumber() == null ? 0 : getStartingSequenceNumber().hashCode()) + 31) * 31;
        if (getEndingSequenceNumber() != null) {
            i = getEndingSequenceNumber().hashCode();
        }
        return hashCode + i;
    }

    public void setEndingSequenceNumber(String str) {
        this.endingSequenceNumber = str;
    }

    public void setStartingSequenceNumber(String str) {
        this.startingSequenceNumber = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStartingSequenceNumber() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StartingSequenceNumber: ");
            outline1072.append(getStartingSequenceNumber());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getEndingSequenceNumber() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("EndingSequenceNumber: ");
            outline1073.append(getEndingSequenceNumber());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SequenceNumberRange withEndingSequenceNumber(String str) {
        this.endingSequenceNumber = str;
        return this;
    }

    public SequenceNumberRange withStartingSequenceNumber(String str) {
        this.startingSequenceNumber = str;
        return this;
    }
}
