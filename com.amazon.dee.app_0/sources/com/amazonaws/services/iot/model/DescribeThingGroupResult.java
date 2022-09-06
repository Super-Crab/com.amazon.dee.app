package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeThingGroupResult implements Serializable {
    private String indexName;
    private String queryString;
    private String queryVersion;
    private String status;
    private String thingGroupArn;
    private String thingGroupId;
    private ThingGroupMetadata thingGroupMetadata;
    private String thingGroupName;
    private ThingGroupProperties thingGroupProperties;
    private Long version;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingGroupResult)) {
            return false;
        }
        DescribeThingGroupResult describeThingGroupResult = (DescribeThingGroupResult) obj;
        if ((describeThingGroupResult.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupName() != null && !describeThingGroupResult.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((describeThingGroupResult.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupId() != null && !describeThingGroupResult.getThingGroupId().equals(getThingGroupId())) {
            return false;
        }
        if ((describeThingGroupResult.getThingGroupArn() == null) ^ (getThingGroupArn() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupArn() != null && !describeThingGroupResult.getThingGroupArn().equals(getThingGroupArn())) {
            return false;
        }
        if ((describeThingGroupResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (describeThingGroupResult.getVersion() != null && !describeThingGroupResult.getVersion().equals(getVersion())) {
            return false;
        }
        if ((describeThingGroupResult.getThingGroupProperties() == null) ^ (getThingGroupProperties() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupProperties() != null && !describeThingGroupResult.getThingGroupProperties().equals(getThingGroupProperties())) {
            return false;
        }
        if ((describeThingGroupResult.getThingGroupMetadata() == null) ^ (getThingGroupMetadata() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupMetadata() != null && !describeThingGroupResult.getThingGroupMetadata().equals(getThingGroupMetadata())) {
            return false;
        }
        if ((describeThingGroupResult.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (describeThingGroupResult.getIndexName() != null && !describeThingGroupResult.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((describeThingGroupResult.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (describeThingGroupResult.getQueryString() != null && !describeThingGroupResult.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((describeThingGroupResult.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        if (describeThingGroupResult.getQueryVersion() != null && !describeThingGroupResult.getQueryVersion().equals(getQueryVersion())) {
            return false;
        }
        if ((describeThingGroupResult.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return describeThingGroupResult.getStatus() == null || describeThingGroupResult.getStatus().equals(getStatus());
    }

    public String getIndexName() {
        return this.indexName;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public String getStatus() {
        return this.status;
    }

    public String getThingGroupArn() {
        return this.thingGroupArn;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public ThingGroupMetadata getThingGroupMetadata() {
        return this.thingGroupMetadata;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public ThingGroupProperties getThingGroupProperties() {
        return this.thingGroupProperties;
    }

    public Long getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupId() == null ? 0 : getThingGroupId().hashCode())) * 31) + (getThingGroupArn() == null ? 0 : getThingGroupArn().hashCode())) * 31) + (getVersion() == null ? 0 : getVersion().hashCode())) * 31) + (getThingGroupProperties() == null ? 0 : getThingGroupProperties().hashCode())) * 31) + (getThingGroupMetadata() == null ? 0 : getThingGroupMetadata().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getQueryVersion() == null ? 0 : getQueryVersion().hashCode())) * 31;
        if (getStatus() != null) {
            i = getStatus().hashCode();
        }
        return hashCode + i;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setThingGroupArn(String str) {
        this.thingGroupArn = str;
    }

    public void setThingGroupId(String str) {
        this.thingGroupId = str;
    }

    public void setThingGroupMetadata(ThingGroupMetadata thingGroupMetadata) {
        this.thingGroupMetadata = thingGroupMetadata;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public void setThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupName: ");
            outline1072.append(getThingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingGroupId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingGroupId: ");
            outline1073.append(getThingGroupId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingGroupArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingGroupArn: ");
            outline1074.append(getThingGroupArn());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getVersion() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("version: ");
            outline1075.append(getVersion());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getThingGroupProperties() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("thingGroupProperties: ");
            outline1076.append(getThingGroupProperties());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getThingGroupMetadata() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("thingGroupMetadata: ");
            outline1077.append(getThingGroupMetadata());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getIndexName() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("indexName: ");
            outline1078.append(getIndexName());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getQueryString() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("queryString: ");
            outline1079.append(getQueryString());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getQueryVersion() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("queryVersion: ");
            outline10710.append(getQueryVersion());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("status: ");
            outline10711.append(getStatus());
            outline107.append(outline10711.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeThingGroupResult withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public DescribeThingGroupResult withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public DescribeThingGroupResult withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public DescribeThingGroupResult withStatus(String str) {
        this.status = str;
        return this;
    }

    public DescribeThingGroupResult withThingGroupArn(String str) {
        this.thingGroupArn = str;
        return this;
    }

    public DescribeThingGroupResult withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public DescribeThingGroupResult withThingGroupMetadata(ThingGroupMetadata thingGroupMetadata) {
        this.thingGroupMetadata = thingGroupMetadata;
        return this;
    }

    public DescribeThingGroupResult withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public DescribeThingGroupResult withThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
        return this;
    }

    public DescribeThingGroupResult withVersion(Long l) {
        this.version = l;
        return this;
    }

    public void setStatus(DynamicGroupStatus dynamicGroupStatus) {
        this.status = dynamicGroupStatus.toString();
    }

    public DescribeThingGroupResult withStatus(DynamicGroupStatus dynamicGroupStatus) {
        this.status = dynamicGroupStatus.toString();
        return this;
    }
}
