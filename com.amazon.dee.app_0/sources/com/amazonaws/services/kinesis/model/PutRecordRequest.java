package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class PutRecordRequest extends AmazonWebServiceRequest implements Serializable {
    private ByteBuffer data;
    private String explicitHashKey;
    private String partitionKey;
    private String sequenceNumberForOrdering;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRecordRequest)) {
            return false;
        }
        PutRecordRequest putRecordRequest = (PutRecordRequest) obj;
        if ((putRecordRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (putRecordRequest.getStreamName() != null && !putRecordRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((putRecordRequest.getData() == null) ^ (getData() == null)) {
            return false;
        }
        if (putRecordRequest.getData() != null && !putRecordRequest.getData().equals(getData())) {
            return false;
        }
        if ((putRecordRequest.getPartitionKey() == null) ^ (getPartitionKey() == null)) {
            return false;
        }
        if (putRecordRequest.getPartitionKey() != null && !putRecordRequest.getPartitionKey().equals(getPartitionKey())) {
            return false;
        }
        if ((putRecordRequest.getExplicitHashKey() == null) ^ (getExplicitHashKey() == null)) {
            return false;
        }
        if (putRecordRequest.getExplicitHashKey() != null && !putRecordRequest.getExplicitHashKey().equals(getExplicitHashKey())) {
            return false;
        }
        if ((putRecordRequest.getSequenceNumberForOrdering() == null) ^ (getSequenceNumberForOrdering() == null)) {
            return false;
        }
        return putRecordRequest.getSequenceNumberForOrdering() == null || putRecordRequest.getSequenceNumberForOrdering().equals(getSequenceNumberForOrdering());
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

    public String getSequenceNumberForOrdering() {
        return this.sequenceNumberForOrdering;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getData() == null ? 0 : getData().hashCode())) * 31) + (getPartitionKey() == null ? 0 : getPartitionKey().hashCode())) * 31) + (getExplicitHashKey() == null ? 0 : getExplicitHashKey().hashCode())) * 31;
        if (getSequenceNumberForOrdering() != null) {
            i = getSequenceNumberForOrdering().hashCode();
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

    public void setSequenceNumberForOrdering(String str) {
        this.sequenceNumberForOrdering = str;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1072.append(getStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getData() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Data: ");
            outline1073.append(getData());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPartitionKey() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("PartitionKey: ");
            outline1074.append(getPartitionKey());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getExplicitHashKey() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("ExplicitHashKey: ");
            outline1075.append(getExplicitHashKey());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getSequenceNumberForOrdering() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("SequenceNumberForOrdering: ");
            outline1076.append(getSequenceNumberForOrdering());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutRecordRequest withData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
        return this;
    }

    public PutRecordRequest withExplicitHashKey(String str) {
        this.explicitHashKey = str;
        return this;
    }

    public PutRecordRequest withPartitionKey(String str) {
        this.partitionKey = str;
        return this;
    }

    public PutRecordRequest withSequenceNumberForOrdering(String str) {
        this.sequenceNumberForOrdering = str;
        return this;
    }

    public PutRecordRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
