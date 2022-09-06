package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutRecordsResultEntry implements Serializable {
    private String errorCode;
    private String errorMessage;
    private String sequenceNumber;
    private String shardId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRecordsResultEntry)) {
            return false;
        }
        PutRecordsResultEntry putRecordsResultEntry = (PutRecordsResultEntry) obj;
        if ((putRecordsResultEntry.getSequenceNumber() == null) ^ (getSequenceNumber() == null)) {
            return false;
        }
        if (putRecordsResultEntry.getSequenceNumber() != null && !putRecordsResultEntry.getSequenceNumber().equals(getSequenceNumber())) {
            return false;
        }
        if ((putRecordsResultEntry.getShardId() == null) ^ (getShardId() == null)) {
            return false;
        }
        if (putRecordsResultEntry.getShardId() != null && !putRecordsResultEntry.getShardId().equals(getShardId())) {
            return false;
        }
        if ((putRecordsResultEntry.getErrorCode() == null) ^ (getErrorCode() == null)) {
            return false;
        }
        if (putRecordsResultEntry.getErrorCode() != null && !putRecordsResultEntry.getErrorCode().equals(getErrorCode())) {
            return false;
        }
        if ((putRecordsResultEntry.getErrorMessage() == null) ^ (getErrorMessage() == null)) {
            return false;
        }
        return putRecordsResultEntry.getErrorMessage() == null || putRecordsResultEntry.getErrorMessage().equals(getErrorMessage());
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getShardId() {
        return this.shardId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getSequenceNumber() == null ? 0 : getSequenceNumber().hashCode()) + 31) * 31) + (getShardId() == null ? 0 : getShardId().hashCode())) * 31) + (getErrorCode() == null ? 0 : getErrorCode().hashCode())) * 31;
        if (getErrorMessage() != null) {
            i = getErrorMessage().hashCode();
        }
        return hashCode + i;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setSequenceNumber(String str) {
        this.sequenceNumber = str;
    }

    public void setShardId(String str) {
        this.shardId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSequenceNumber() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SequenceNumber: ");
            outline1072.append(getSequenceNumber());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getShardId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ShardId: ");
            outline1073.append(getShardId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getErrorCode() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ErrorCode: ");
            outline1074.append(getErrorCode());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getErrorMessage() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("ErrorMessage: ");
            outline1075.append(getErrorMessage());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutRecordsResultEntry withErrorCode(String str) {
        this.errorCode = str;
        return this;
    }

    public PutRecordsResultEntry withErrorMessage(String str) {
        this.errorMessage = str;
        return this;
    }

    public PutRecordsResultEntry withSequenceNumber(String str) {
        this.sequenceNumber = str;
        return this;
    }

    public PutRecordsResultEntry withShardId(String str) {
        this.shardId = str;
        return this;
    }
}
