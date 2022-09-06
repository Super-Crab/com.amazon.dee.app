package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListTagsForStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String exclusiveStartTagKey;
    private Integer limit;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTagsForStreamRequest)) {
            return false;
        }
        ListTagsForStreamRequest listTagsForStreamRequest = (ListTagsForStreamRequest) obj;
        if ((listTagsForStreamRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (listTagsForStreamRequest.getStreamName() != null && !listTagsForStreamRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((listTagsForStreamRequest.getExclusiveStartTagKey() == null) ^ (getExclusiveStartTagKey() == null)) {
            return false;
        }
        if (listTagsForStreamRequest.getExclusiveStartTagKey() != null && !listTagsForStreamRequest.getExclusiveStartTagKey().equals(getExclusiveStartTagKey())) {
            return false;
        }
        if ((listTagsForStreamRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        return listTagsForStreamRequest.getLimit() == null || listTagsForStreamRequest.getLimit().equals(getLimit());
    }

    public String getExclusiveStartTagKey() {
        return this.exclusiveStartTagKey;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getExclusiveStartTagKey() == null ? 0 : getExclusiveStartTagKey().hashCode())) * 31;
        if (getLimit() != null) {
            i = getLimit().hashCode();
        }
        return hashCode + i;
    }

    public void setExclusiveStartTagKey(String str) {
        this.exclusiveStartTagKey = str;
    }

    public void setLimit(Integer num) {
        this.limit = num;
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
        if (getExclusiveStartTagKey() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ExclusiveStartTagKey: ");
            outline1073.append(getExclusiveStartTagKey());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Limit: ");
            outline1074.append(getLimit());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTagsForStreamRequest withExclusiveStartTagKey(String str) {
        this.exclusiveStartTagKey = str;
        return this;
    }

    public ListTagsForStreamRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public ListTagsForStreamRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
