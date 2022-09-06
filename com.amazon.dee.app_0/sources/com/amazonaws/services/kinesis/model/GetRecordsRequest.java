package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetRecordsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer limit;
    private String shardIterator;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetRecordsRequest)) {
            return false;
        }
        GetRecordsRequest getRecordsRequest = (GetRecordsRequest) obj;
        if ((getRecordsRequest.getShardIterator() == null) ^ (getShardIterator() == null)) {
            return false;
        }
        if (getRecordsRequest.getShardIterator() != null && !getRecordsRequest.getShardIterator().equals(getShardIterator())) {
            return false;
        }
        if ((getRecordsRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        return getRecordsRequest.getLimit() == null || getRecordsRequest.getLimit().equals(getLimit());
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getShardIterator() {
        return this.shardIterator;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getShardIterator() == null ? 0 : getShardIterator().hashCode()) + 31) * 31;
        if (getLimit() != null) {
            i = getLimit().hashCode();
        }
        return hashCode + i;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setShardIterator(String str) {
        this.shardIterator = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getShardIterator() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ShardIterator: ");
            outline1072.append(getShardIterator());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Limit: ");
            outline1073.append(getLimit());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetRecordsRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public GetRecordsRequest withShardIterator(String str) {
        this.shardIterator = str;
        return this;
    }
}
