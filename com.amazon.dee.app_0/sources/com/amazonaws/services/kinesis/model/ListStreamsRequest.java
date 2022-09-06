package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListStreamsRequest extends AmazonWebServiceRequest implements Serializable {
    private String exclusiveStartStreamName;
    private Integer limit;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListStreamsRequest)) {
            return false;
        }
        ListStreamsRequest listStreamsRequest = (ListStreamsRequest) obj;
        if ((listStreamsRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        if (listStreamsRequest.getLimit() != null && !listStreamsRequest.getLimit().equals(getLimit())) {
            return false;
        }
        if ((listStreamsRequest.getExclusiveStartStreamName() == null) ^ (getExclusiveStartStreamName() == null)) {
            return false;
        }
        return listStreamsRequest.getExclusiveStartStreamName() == null || listStreamsRequest.getExclusiveStartStreamName().equals(getExclusiveStartStreamName());
    }

    public String getExclusiveStartStreamName() {
        return this.exclusiveStartStreamName;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLimit() == null ? 0 : getLimit().hashCode()) + 31) * 31;
        if (getExclusiveStartStreamName() != null) {
            i = getExclusiveStartStreamName().hashCode();
        }
        return hashCode + i;
    }

    public void setExclusiveStartStreamName(String str) {
        this.exclusiveStartStreamName = str;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLimit() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Limit: ");
            outline1072.append(getLimit());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getExclusiveStartStreamName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ExclusiveStartStreamName: ");
            outline1073.append(getExclusiveStartStreamName());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListStreamsRequest withExclusiveStartStreamName(String str) {
        this.exclusiveStartStreamName = str;
        return this;
    }

    public ListStreamsRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
