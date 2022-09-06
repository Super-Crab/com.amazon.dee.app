package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Date;
/* loaded from: classes13.dex */
public class Record implements Serializable {
    private Date approximateArrivalTimestamp;
    private ByteBuffer data;
    private String encryptionType;
    private String partitionKey;
    private String sequenceNumber;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Record)) {
            return false;
        }
        Record record = (Record) obj;
        if ((record.getSequenceNumber() == null) ^ (getSequenceNumber() == null)) {
            return false;
        }
        if (record.getSequenceNumber() != null && !record.getSequenceNumber().equals(getSequenceNumber())) {
            return false;
        }
        if ((record.getApproximateArrivalTimestamp() == null) ^ (getApproximateArrivalTimestamp() == null)) {
            return false;
        }
        if (record.getApproximateArrivalTimestamp() != null && !record.getApproximateArrivalTimestamp().equals(getApproximateArrivalTimestamp())) {
            return false;
        }
        if ((record.getData() == null) ^ (getData() == null)) {
            return false;
        }
        if (record.getData() != null && !record.getData().equals(getData())) {
            return false;
        }
        if ((record.getPartitionKey() == null) ^ (getPartitionKey() == null)) {
            return false;
        }
        if (record.getPartitionKey() != null && !record.getPartitionKey().equals(getPartitionKey())) {
            return false;
        }
        if ((record.getEncryptionType() == null) ^ (getEncryptionType() == null)) {
            return false;
        }
        return record.getEncryptionType() == null || record.getEncryptionType().equals(getEncryptionType());
    }

    public Date getApproximateArrivalTimestamp() {
        return this.approximateArrivalTimestamp;
    }

    public ByteBuffer getData() {
        return this.data;
    }

    public String getEncryptionType() {
        return this.encryptionType;
    }

    public String getPartitionKey() {
        return this.partitionKey;
    }

    public String getSequenceNumber() {
        return this.sequenceNumber;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getSequenceNumber() == null ? 0 : getSequenceNumber().hashCode()) + 31) * 31) + (getApproximateArrivalTimestamp() == null ? 0 : getApproximateArrivalTimestamp().hashCode())) * 31) + (getData() == null ? 0 : getData().hashCode())) * 31) + (getPartitionKey() == null ? 0 : getPartitionKey().hashCode())) * 31;
        if (getEncryptionType() != null) {
            i = getEncryptionType().hashCode();
        }
        return hashCode + i;
    }

    public void setApproximateArrivalTimestamp(Date date) {
        this.approximateArrivalTimestamp = date;
    }

    public void setData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    public void setEncryptionType(String str) {
        this.encryptionType = str;
    }

    public void setPartitionKey(String str) {
        this.partitionKey = str;
    }

    public void setSequenceNumber(String str) {
        this.sequenceNumber = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSequenceNumber() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SequenceNumber: ");
            outline1072.append(getSequenceNumber());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getApproximateArrivalTimestamp() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ApproximateArrivalTimestamp: ");
            outline1073.append(getApproximateArrivalTimestamp());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getData() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Data: ");
            outline1074.append(getData());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getPartitionKey() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("PartitionKey: ");
            outline1075.append(getPartitionKey());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getEncryptionType() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("EncryptionType: ");
            outline1076.append(getEncryptionType());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Record withApproximateArrivalTimestamp(Date date) {
        this.approximateArrivalTimestamp = date;
        return this;
    }

    public Record withData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
        return this;
    }

    public Record withEncryptionType(String str) {
        this.encryptionType = str;
        return this;
    }

    public Record withPartitionKey(String str) {
        this.partitionKey = str;
        return this;
    }

    public Record withSequenceNumber(String str) {
        this.sequenceNumber = str;
        return this;
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
    }

    public Record withEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
        return this;
    }
}
