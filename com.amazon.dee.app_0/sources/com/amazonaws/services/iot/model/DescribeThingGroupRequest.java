package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingGroupRequest)) {
            return false;
        }
        DescribeThingGroupRequest describeThingGroupRequest = (DescribeThingGroupRequest) obj;
        if ((describeThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        return describeThingGroupRequest.getThingGroupName() == null || describeThingGroupRequest.getThingGroupName().equals(getThingGroupName());
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public int hashCode() {
        return 31 + (getThingGroupName() == null ? 0 : getThingGroupName().hashCode());
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupName: ");
            outline1072.append(getThingGroupName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }
}
