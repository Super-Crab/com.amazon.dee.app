package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeThingRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingRequest)) {
            return false;
        }
        DescribeThingRequest describeThingRequest = (DescribeThingRequest) obj;
        if ((describeThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        return describeThingRequest.getThingName() == null || describeThingRequest.getThingName().equals(getThingName());
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        return 31 + (getThingName() == null ? 0 : getThingName().hashCode());
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
