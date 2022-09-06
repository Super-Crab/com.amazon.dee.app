package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String thingGroupName;
    private ThingGroupProperties thingGroupProperties;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateThingGroupRequest)) {
            return false;
        }
        UpdateThingGroupRequest updateThingGroupRequest = (UpdateThingGroupRequest) obj;
        if ((updateThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (updateThingGroupRequest.getThingGroupName() != null && !updateThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((updateThingGroupRequest.getThingGroupProperties() == null) ^ (getThingGroupProperties() == null)) {
            return false;
        }
        if (updateThingGroupRequest.getThingGroupProperties() != null && !updateThingGroupRequest.getThingGroupProperties().equals(getThingGroupProperties())) {
            return false;
        }
        if ((updateThingGroupRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return updateThingGroupRequest.getExpectedVersion() == null || updateThingGroupRequest.getExpectedVersion().equals(getExpectedVersion());
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public ThingGroupProperties getThingGroupProperties() {
        return this.thingGroupProperties;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupProperties() == null ? 0 : getThingGroupProperties().hashCode())) * 31;
        if (getExpectedVersion() != null) {
            i = getExpectedVersion().hashCode();
        }
        return hashCode + i;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
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
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateThingGroupRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public UpdateThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public UpdateThingGroupRequest withThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
        return this;
    }
}
