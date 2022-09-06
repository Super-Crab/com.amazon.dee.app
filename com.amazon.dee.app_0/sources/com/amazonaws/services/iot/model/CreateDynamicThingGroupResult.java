package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateDynamicThingGroupResult implements Serializable {
    private String indexName;
    private String queryString;
    private String queryVersion;
    private String thingGroupArn;
    private String thingGroupId;
    private String thingGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateDynamicThingGroupResult)) {
            return false;
        }
        CreateDynamicThingGroupResult createDynamicThingGroupResult = (CreateDynamicThingGroupResult) obj;
        if ((createDynamicThingGroupResult.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getThingGroupName() != null && !createDynamicThingGroupResult.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getThingGroupArn() == null) ^ (getThingGroupArn() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getThingGroupArn() != null && !createDynamicThingGroupResult.getThingGroupArn().equals(getThingGroupArn())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getThingGroupId() != null && !createDynamicThingGroupResult.getThingGroupId().equals(getThingGroupId())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getIndexName() != null && !createDynamicThingGroupResult.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getQueryString() != null && !createDynamicThingGroupResult.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        return createDynamicThingGroupResult.getQueryVersion() == null || createDynamicThingGroupResult.getQueryVersion().equals(getQueryVersion());
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

    public String getThingGroupArn() {
        return this.thingGroupArn;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupArn() == null ? 0 : getThingGroupArn().hashCode())) * 31) + (getThingGroupId() == null ? 0 : getThingGroupId().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31;
        if (getQueryVersion() != null) {
            i = getQueryVersion().hashCode();
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

    public void setThingGroupArn(String str) {
        this.thingGroupArn = str;
    }

    public void setThingGroupId(String str) {
        this.thingGroupId = str;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupName: ");
            outline1072.append(getThingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingGroupArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingGroupArn: ");
            outline1073.append(getThingGroupArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingGroupId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingGroupId: ");
            outline1074.append(getThingGroupId());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getIndexName() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("indexName: ");
            outline1075.append(getIndexName());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getQueryString() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("queryString: ");
            outline1076.append(getQueryString());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getQueryVersion() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("queryVersion: ");
            outline1077.append(getQueryVersion());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateDynamicThingGroupResult withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public CreateDynamicThingGroupResult withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public CreateDynamicThingGroupResult withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public CreateDynamicThingGroupResult withThingGroupArn(String str) {
        this.thingGroupArn = str;
        return this;
    }

    public CreateDynamicThingGroupResult withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public CreateDynamicThingGroupResult withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }
}
