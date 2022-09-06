package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeIndexResult implements Serializable {
    private String indexName;
    private String indexStatus;
    private String schema;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeIndexResult)) {
            return false;
        }
        DescribeIndexResult describeIndexResult = (DescribeIndexResult) obj;
        if ((describeIndexResult.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (describeIndexResult.getIndexName() != null && !describeIndexResult.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((describeIndexResult.getIndexStatus() == null) ^ (getIndexStatus() == null)) {
            return false;
        }
        if (describeIndexResult.getIndexStatus() != null && !describeIndexResult.getIndexStatus().equals(getIndexStatus())) {
            return false;
        }
        if ((describeIndexResult.getSchema() == null) ^ (getSchema() == null)) {
            return false;
        }
        return describeIndexResult.getSchema() == null || describeIndexResult.getSchema().equals(getSchema());
    }

    public String getIndexName() {
        return this.indexName;
    }

    public String getIndexStatus() {
        return this.indexStatus;
    }

    public String getSchema() {
        return this.schema;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getIndexName() == null ? 0 : getIndexName().hashCode()) + 31) * 31) + (getIndexStatus() == null ? 0 : getIndexStatus().hashCode())) * 31;
        if (getSchema() != null) {
            i = getSchema().hashCode();
        }
        return hashCode + i;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public void setIndexStatus(String str) {
        this.indexStatus = str;
    }

    public void setSchema(String str) {
        this.schema = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIndexName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("indexName: ");
            outline1072.append(getIndexName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getIndexStatus() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("indexStatus: ");
            outline1073.append(getIndexStatus());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSchema() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("schema: ");
            outline1074.append(getSchema());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeIndexResult withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public DescribeIndexResult withIndexStatus(String str) {
        this.indexStatus = str;
        return this;
    }

    public DescribeIndexResult withSchema(String str) {
        this.schema = str;
        return this;
    }

    public void setIndexStatus(IndexStatus indexStatus) {
        this.indexStatus = indexStatus.toString();
    }

    public DescribeIndexResult withIndexStatus(IndexStatus indexStatus) {
        this.indexStatus = indexStatus.toString();
        return this;
    }
}
