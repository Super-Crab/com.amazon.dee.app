package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutRecordResult implements Serializable {
    private String encryptionType;
    private String sequenceNumber;
    private String shardId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRecordResult)) {
            return false;
        }
        PutRecordResult putRecordResult = (PutRecordResult) obj;
        if ((putRecordResult.getShardId() == null) ^ (getShardId() == null)) {
            return false;
        }
        if (putRecordResult.getShardId() != null && !putRecordResult.getShardId().equals(getShardId())) {
            return false;
        }
        if ((putRecordResult.getSequenceNumber() == null) ^ (getSequenceNumber() == null)) {
            return false;
        }
        if (putRecordResult.getSequenceNumber() != null && !putRecordResult.getSequenceNumber().equals(getSequenceNumber())) {
            return false;
        }
        if ((putRecordResult.getEncryptionType() == null) ^ (getEncryptionType() == null)) {
            return false;
        }
        return putRecordResult.getEncryptionType() == null || putRecordResult.getEncryptionType().equals(getEncryptionType());
    }

    public String getEncryptionType() {
        return this.encryptionType;
    }

    public String getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getShardId() {
        return this.shardId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getShardId() == null ? 0 : getShardId().hashCode()) + 31) * 31) + (getSequenceNumber() == null ? 0 : getSequenceNumber().hashCode())) * 31;
        if (getEncryptionType() != null) {
            i = getEncryptionType().hashCode();
        }
        return hashCode + i;
    }

    public void setEncryptionType(String str) {
        this.encryptionType = str;
    }

    public void setSequenceNumber(String str) {
        this.sequenceNumber = str;
    }

    public void setShardId(String str) {
        this.shardId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getShardId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ShardId: ");
            outline1072.append(getShardId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSequenceNumber() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("SequenceNumber: ");
            outline1073.append(getSequenceNumber());
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

    public PutRecordResult withEncryptionType(String str) {
        this.encryptionType = str;
        return this;
    }

    public PutRecordResult withSequenceNumber(String str) {
        this.sequenceNumber = str;
        return this;
    }

    public PutRecordResult withShardId(String str) {
        this.shardId = str;
        return this;
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
    }

    public PutRecordResult withEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
        return this;
    }
}
