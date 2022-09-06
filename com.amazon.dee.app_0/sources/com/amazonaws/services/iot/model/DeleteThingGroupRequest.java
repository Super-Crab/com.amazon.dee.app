package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String thingGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteThingGroupRequest)) {
            return false;
        }
        DeleteThingGroupRequest deleteThingGroupRequest = (DeleteThingGroupRequest) obj;
        if ((deleteThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (deleteThingGroupRequest.getThingGroupName() != null && !deleteThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((deleteThingGroupRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return deleteThingGroupRequest.getExpectedVersion() == null || deleteThingGroupRequest.getExpectedVersion().equals(getExpectedVersion());
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31;
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

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupName: ");
            outline1072.append(getThingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getExpectedVersion() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("expectedVersion: ");
            outline1073.append(getExpectedVersion());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteThingGroupRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public DeleteThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }
}
