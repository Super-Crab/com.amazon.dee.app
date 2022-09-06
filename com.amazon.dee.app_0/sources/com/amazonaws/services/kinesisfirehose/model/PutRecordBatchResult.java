package com.amazonaws.services.kinesisfirehose.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class PutRecordBatchResult implements Serializable {
    private Boolean encrypted;
    private Integer failedPutCount;
    private List<PutRecordBatchResponseEntry> requestResponses;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRecordBatchResult)) {
            return false;
        }
        PutRecordBatchResult putRecordBatchResult = (PutRecordBatchResult) obj;
        if ((putRecordBatchResult.getFailedPutCount() == null) ^ (getFailedPutCount() == null)) {
            return false;
        }
        if (putRecordBatchResult.getFailedPutCount() != null && !putRecordBatchResult.getFailedPutCount().equals(getFailedPutCount())) {
            return false;
        }
        if ((putRecordBatchResult.getEncrypted() == null) ^ (getEncrypted() == null)) {
            return false;
        }
        if (putRecordBatchResult.getEncrypted() != null && !putRecordBatchResult.getEncrypted().equals(getEncrypted())) {
            return false;
        }
        if ((putRecordBatchResult.getRequestResponses() == null) ^ (getRequestResponses() == null)) {
            return false;
        }
        return putRecordBatchResult.getRequestResponses() == null || putRecordBatchResult.getRequestResponses().equals(getRequestResponses());
    }

    public Boolean getEncrypted() {
        return this.encrypted;
    }

    public Integer getFailedPutCount() {
        return this.failedPutCount;
    }

    public List<PutRecordBatchResponseEntry> getRequestResponses() {
        return this.requestResponses;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getFailedPutCount() == null ? 0 : getFailedPutCount().hashCode()) + 31) * 31) + (getEncrypted() == null ? 0 : getEncrypted().hashCode())) * 31;
        if (getRequestResponses() != null) {
            i = getRequestResponses().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isEncrypted() {
        return this.encrypted;
    }

    public void setEncrypted(Boolean bool) {
        this.encrypted = bool;
    }

    public void setFailedPutCount(Integer num) {
        this.failedPutCount = num;
    }

    public void setRequestResponses(Collection<PutRecordBatchResponseEntry> collection) {
        if (collection == null) {
            this.requestResponses = null;
        } else {
            this.requestResponses = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFailedPutCount() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("FailedPutCount: ");
            outline1072.append(getFailedPutCount());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getEncrypted() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Encrypted: ");
            outline1073.append(getEncrypted());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRequestResponses() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("RequestResponses: ");
            outline1074.append(getRequestResponses());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutRecordBatchResult withEncrypted(Boolean bool) {
        this.encrypted = bool;
        return this;
    }

    public PutRecordBatchResult withFailedPutCount(Integer num) {
        this.failedPutCount = num;
        return this;
    }

    public PutRecordBatchResult withRequestResponses(PutRecordBatchResponseEntry... putRecordBatchResponseEntryArr) {
        if (getRequestResponses() == null) {
            this.requestResponses = new ArrayList(putRecordBatchResponseEntryArr.length);
        }
        for (PutRecordBatchResponseEntry putRecordBatchResponseEntry : putRecordBatchResponseEntryArr) {
            this.requestResponses.add(putRecordBatchResponseEntry);
        }
        return this;
    }

    public PutRecordBatchResult withRequestResponses(Collection<PutRecordBatchResponseEntry> collection) {
        setRequestResponses(collection);
        return this;
    }
}
