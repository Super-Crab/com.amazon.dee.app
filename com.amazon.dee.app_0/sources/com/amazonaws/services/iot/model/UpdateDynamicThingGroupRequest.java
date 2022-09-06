package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateDynamicThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String indexName;
    private String queryString;
    private String queryVersion;
    private String thingGroupName;
    private ThingGroupProperties thingGroupProperties;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateDynamicThingGroupRequest)) {
            return false;
        }
        UpdateDynamicThingGroupRequest updateDynamicThingGroupRequest = (UpdateDynamicThingGroupRequest) obj;
        if ((updateDynamicThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getThingGroupName() != null && !updateDynamicThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getThingGroupProperties() == null) ^ (getThingGroupProperties() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getThingGroupProperties() != null && !updateDynamicThingGroupRequest.getThingGroupProperties().equals(getThingGroupProperties())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getExpectedVersion() != null && !updateDynamicThingGroupRequest.getExpectedVersion().equals(getExpectedVersion())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getIndexName() != null && !updateDynamicThingGroupRequest.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getQueryString() != null && !updateDynamicThingGroupRequest.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        return updateDynamicThingGroupRequest.getQueryVersion() == null || updateDynamicThingGroupRequest.getQueryVersion().equals(getQueryVersion());
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
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

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public ThingGroupProperties getThingGroupProperties() {
        return this.thingGroupProperties;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupProperties() == null ? 0 : getThingGroupProperties().hashCode())) * 31) + (getExpectedVersion() == null ? 0 : getExpectedVersion().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31;
        if (getQueryVersion() != null) {
            i = getQueryVersion().hashCode();
        }
        return hashCode + i;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
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

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public void setThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupName: ");
            outline1072.append(getThingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingGroupProperties() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingGroupProperties: ");
            outline1073.append(getThingGroupProperties());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getExpectedVersion() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("expectedVersion: ");
            outline1074.append(getExpectedVersion());
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

    public UpdateDynamicThingGroupRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public UpdateDynamicThingGroupRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public UpdateDynamicThingGroupRequest withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public UpdateDynamicThingGroupRequest withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public UpdateDynamicThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public UpdateDynamicThingGroupRequest withThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
        return this;
    }
}
