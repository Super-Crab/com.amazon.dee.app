package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class PutRecordsResult implements Serializable {
    private String encryptionType;
    private Integer failedRecordCount;
    private List<PutRecordsResultEntry> records = new ArrayList();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRecordsResult)) {
            return false;
        }
        PutRecordsResult putRecordsResult = (PutRecordsResult) obj;
        if ((putRecordsResult.getFailedRecordCount() == null) ^ (getFailedRecordCount() == null)) {
            return false;
        }
        if (putRecordsResult.getFailedRecordCount() != null && !putRecordsResult.getFailedRecordCount().equals(getFailedRecordCount())) {
            return false;
        }
        if ((putRecordsResult.getRecords() == null) ^ (getRecords() == null)) {
            return false;
        }
        if (putRecordsResult.getRecords() != null && !putRecordsResult.getRecords().equals(getRecords())) {
            return false;
        }
        if ((putRecordsResult.getEncryptionType() == null) ^ (getEncryptionType() == null)) {
            return false;
        }
        return putRecordsResult.getEncryptionType() == null || putRecordsResult.getEncryptionType().equals(getEncryptionType());
    }

    public String getEncryptionType() {
        return this.encryptionType;
    }

    public Integer getFailedRecordCount() {
        return this.failedRecordCount;
    }

    public List<PutRecordsResultEntry> getRecords() {
        return this.records;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getFailedRecordCount() == null ? 0 : getFailedRecordCount().hashCode()) + 31) * 31) + (getRecords() == null ? 0 : getRecords().hashCode())) * 31;
        if (getEncryptionType() != null) {
            i = getEncryptionType().hashCode();
        }
        return hashCode + i;
    }

    public void setEncryptionType(String str) {
        this.encryptionType = str;
    }

    public void setFailedRecordCount(Integer num) {
        this.failedRecordCount = num;
    }

    public void setRecords(Collection<PutRecordsResultEntry> collection) {
        if (collection == null) {
            this.records = null;
        } else {
            this.records = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFailedRecordCount() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("FailedRecordCount: ");
            outline1072.append(getFailedRecordCount());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRecords() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Records: ");
            outline1073.append(getRecords());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getEncryptionType() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("EncryptionType: ");
            outline1074.append(getEncryptionType());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutRecordsResult withEncryptionType(String str) {
        this.encryptionType = str;
        return this;
    }

    public PutRecordsResult withFailedRecordCount(Integer num) {
        this.failedRecordCount = num;
        return this;
    }

    public PutRecordsResult withRecords(PutRecordsResultEntry... putRecordsResultEntryArr) {
        if (getRecords() == null) {
            this.records = new ArrayList(putRecordsResultEntryArr.length);
        }
        for (PutRecordsResultEntry putRecordsResultEntry : putRecordsResultEntryArr) {
            this.records.add(putRecordsResultEntry);
        }
        return this;
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
    }

    public PutRecordsResult withEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
        return this;
    }

    public PutRecordsResult withRecords(Collection<PutRecordsResultEntry> collection) {
        setRecords(collection);
        return this;
    }
}
