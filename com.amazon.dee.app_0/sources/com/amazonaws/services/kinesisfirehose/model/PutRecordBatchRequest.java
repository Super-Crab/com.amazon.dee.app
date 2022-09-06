package com.amazonaws.services.kinesisfirehose.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class PutRecordBatchRequest extends AmazonWebServiceRequest implements Serializable {
    private String deliveryStreamName;
    private List<Record> records;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRecordBatchRequest)) {
            return false;
        }
        PutRecordBatchRequest putRecordBatchRequest = (PutRecordBatchRequest) obj;
        if ((putRecordBatchRequest.getDeliveryStreamName() == null) ^ (getDeliveryStreamName() == null)) {
            return false;
        }
        if (putRecordBatchRequest.getDeliveryStreamName() != null && !putRecordBatchRequest.getDeliveryStreamName().equals(getDeliveryStreamName())) {
            return false;
        }
        if ((putRecordBatchRequest.getRecords() == null) ^ (getRecords() == null)) {
            return false;
        }
        return putRecordBatchRequest.getRecords() == null || putRecordBatchRequest.getRecords().equals(getRecords());
    }

    public String getDeliveryStreamName() {
        return this.deliveryStreamName;
    }

    public List<Record> getRecords() {
        return this.records;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getDeliveryStreamName() == null ? 0 : getDeliveryStreamName().hashCode()) + 31) * 31;
        if (getRecords() != null) {
            i = getRecords().hashCode();
        }
        return hashCode + i;
    }

    public void setDeliveryStreamName(String str) {
        this.deliveryStreamName = str;
    }

    public void setRecords(Collection<Record> collection) {
        if (collection == null) {
            this.records = null;
        } else {
            this.records = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDeliveryStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("DeliveryStreamName: ");
            outline1072.append(getDeliveryStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRecords() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Records: ");
            outline1073.append(getRecords());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutRecordBatchRequest withDeliveryStreamName(String str) {
        this.deliveryStreamName = str;
        return this;
    }

    public PutRecordBatchRequest withRecords(Record... recordArr) {
        if (getRecords() == null) {
            this.records = new ArrayList(recordArr.length);
        }
        for (Record record : recordArr) {
            this.records.add(record);
        }
        return this;
    }

    public PutRecordBatchRequest withRecords(Collection<Record> collection) {
        setRecords(collection);
        return this;
    }
}
