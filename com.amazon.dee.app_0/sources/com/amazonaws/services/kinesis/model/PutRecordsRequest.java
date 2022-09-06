package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class PutRecordsRequest extends AmazonWebServiceRequest implements Serializable {
    private List<PutRecordsRequestEntry> records = new ArrayList();
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRecordsRequest)) {
            return false;
        }
        PutRecordsRequest putRecordsRequest = (PutRecordsRequest) obj;
        if ((putRecordsRequest.getRecords() == null) ^ (getRecords() == null)) {
            return false;
        }
        if (putRecordsRequest.getRecords() != null && !putRecordsRequest.getRecords().equals(getRecords())) {
            return false;
        }
        if ((putRecordsRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        return putRecordsRequest.getStreamName() == null || putRecordsRequest.getStreamName().equals(getStreamName());
    }

    public List<PutRecordsRequestEntry> getRecords() {
        return this.records;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRecords() == null ? 0 : getRecords().hashCode()) + 31) * 31;
        if (getStreamName() != null) {
            i = getStreamName().hashCode();
        }
        return hashCode + i;
    }

    public void setRecords(Collection<PutRecordsRequestEntry> collection) {
        if (collection == null) {
            this.records = null;
        } else {
            this.records = new ArrayList(collection);
        }
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRecords() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Records: ");
            outline1072.append(getRecords());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getStreamName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1073.append(getStreamName());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutRecordsRequest withRecords(PutRecordsRequestEntry... putRecordsRequestEntryArr) {
        if (getRecords() == null) {
            this.records = new ArrayList(putRecordsRequestEntryArr.length);
        }
        for (PutRecordsRequestEntry putRecordsRequestEntry : putRecordsRequestEntryArr) {
            this.records.add(putRecordsRequestEntry);
        }
        return this;
    }

    public PutRecordsRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public PutRecordsRequest withRecords(Collection<PutRecordsRequestEntry> collection) {
        setRecords(collection);
        return this;
    }
}
