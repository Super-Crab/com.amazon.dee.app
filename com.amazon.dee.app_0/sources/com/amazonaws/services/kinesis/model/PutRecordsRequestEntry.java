package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class PutRecordsRequestEntry implements Serializable {
    private ByteBuffer data;
    private String explicitHashKey;
    private String partitionKey;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRecordsRequestEntry)) {
            return false;
        }
        PutRecordsRequestEntry putRecordsRequestEntry = (PutRecordsRequestEntry) obj;
        if ((putRecordsRequestEntry.getData() == null) ^ (getData() == null)) {
            return false;
        }
        if (putRecordsRequestEntry.getData() != null && !putRecordsRequestEntry.getData().equals(getData())) {
            return false;
        }
        if ((putRecordsRequestEntry.getExplicitHashKey() == null) ^ (getExplicitHashKey() == null)) {
            return false;
        }
        if (putRecordsRequestEntry.getExplicitHashKey() != null && !putRecordsRequestEntry.getExplicitHashKey().equals(getExplicitHashKey())) {
            return false;
        }
        if ((putRecordsRequestEntry.getPartitionKey() == null) ^ (getPartitionKey() == null)) {
            return false;
        }
        return putRecordsRequestEntry.getPartitionKey() == null || putRecordsRequestEntry.getPartitionKey().equals(getPartitionKey());
    }

    public ByteBuffer getData() {
        return this.data;
    }

    public String getExplicitHashKey() {
        return this.explicitHashKey;
    }

    public String getPartitionKey() {
        return this.partitionKey;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getData() == null ? 0 : getData().hashCode()) + 31) * 31) + (getExplicitHashKey() == null ? 0 : getExplicitHashKey().hashCode())) * 31;
        if (getPartitionKey() != null) {
            i = getPartitionKey().hashCode();
        }
        return hashCode + i;
    }

    public void setData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    public void setExplicitHashKey(String str) {
        this.explicitHashKey = str;
    }

    public void setPartitionKey(String str) {
        this.partitionKey = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getData() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Data: ");
            outline1072.append(getData());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getExplicitHashKey() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ExplicitHashKey: ");
            outline1073.append(getExplicitHashKey());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPartitionKey() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("PartitionKey: ");
            outline1074.append(getPartitionKey());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutRecordsRequestEntry withData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
        return this;
    }

    public PutRecordsRequestEntry withExplicitHashKey(String str) {
        this.explicitHashKey = str;
        return this;
    }

    public PutRecordsRequestEntry withPartitionKey(String str) {
        this.partitionKey = str;
        return this;
    }
}
