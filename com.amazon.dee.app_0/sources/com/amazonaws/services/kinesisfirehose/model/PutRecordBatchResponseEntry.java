package com.amazonaws.services.kinesisfirehose.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutRecordBatchResponseEntry implements Serializable {
    private String errorCode;
    private String errorMessage;
    private String recordId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRecordBatchResponseEntry)) {
            return false;
        }
        PutRecordBatchResponseEntry putRecordBatchResponseEntry = (PutRecordBatchResponseEntry) obj;
        if ((putRecordBatchResponseEntry.getRecordId() == null) ^ (getRecordId() == null)) {
            return false;
        }
        if (putRecordBatchResponseEntry.getRecordId() != null && !putRecordBatchResponseEntry.getRecordId().equals(getRecordId())) {
            return false;
        }
        if ((putRecordBatchResponseEntry.getErrorCode() == null) ^ (getErrorCode() == null)) {
            return false;
        }
        if (putRecordBatchResponseEntry.getErrorCode() != null && !putRecordBatchResponseEntry.getErrorCode().equals(getErrorCode())) {
            return false;
        }
        if ((putRecordBatchResponseEntry.getErrorMessage() == null) ^ (getErrorMessage() == null)) {
            return false;
        }
        return putRecordBatchResponseEntry.getErrorMessage() == null || putRecordBatchResponseEntry.getErrorMessage().equals(getErrorMessage());
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getRecordId() == null ? 0 : getRecordId().hashCode()) + 31) * 31) + (getErrorCode() == null ? 0 : getErrorCode().hashCode())) * 31;
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

    public void setRecordId(String str) {
        this.recordId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRecordId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("RecordId: ");
            outline1072.append(getRecordId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getErrorCode() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ErrorCode: ");
            outline1073.append(getErrorCode());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getErrorMessage() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ErrorMessage: ");
            outline1074.append(getErrorMessage());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutRecordBatchResponseEntry withErrorCode(String str) {
        this.errorCode = str;
        return this;
    }

    public PutRecordBatchResponseEntry withErrorMessage(String str) {
        this.errorMessage = str;
        return this;
    }

    public PutRecordBatchResponseEntry withRecordId(String str) {
        this.recordId = str;
        return this;
    }
}
