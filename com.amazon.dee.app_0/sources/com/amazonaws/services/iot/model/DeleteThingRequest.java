package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteThingRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteThingRequest)) {
            return false;
        }
        DeleteThingRequest deleteThingRequest = (DeleteThingRequest) obj;
        if ((deleteThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (deleteThingRequest.getThingName() != null && !deleteThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((deleteThingRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return deleteThingRequest.getExpectedVersion() == null || deleteThingRequest.getExpectedVersion().equals(getExpectedVersion());
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31;
        if (getExpectedVersion() != null) {
            i = getExpectedVersion().hashCode();
        }
        return hashCode + i;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
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

    public DeleteThingRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public DeleteThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
