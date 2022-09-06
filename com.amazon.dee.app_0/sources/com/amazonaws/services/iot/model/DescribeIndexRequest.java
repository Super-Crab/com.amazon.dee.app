package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeIndexRequest extends AmazonWebServiceRequest implements Serializable {
    private String indexName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeIndexRequest)) {
            return false;
        }
        DescribeIndexRequest describeIndexRequest = (DescribeIndexRequest) obj;
        if ((describeIndexRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        return describeIndexRequest.getIndexName() == null || describeIndexRequest.getIndexName().equals(getIndexName());
    }

    public String getIndexName() {
        return this.indexName;
    }

    public int hashCode() {
        return 31 + (getIndexName() == null ? 0 : getIndexName().hashCode());
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIndexName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("indexName: ");
            outline1072.append(getIndexName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeIndexRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }
}
