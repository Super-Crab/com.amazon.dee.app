package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeThingTypeRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingTypeName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingTypeRequest)) {
            return false;
        }
        DescribeThingTypeRequest describeThingTypeRequest = (DescribeThingTypeRequest) obj;
        if ((describeThingTypeRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        return describeThingTypeRequest.getThingTypeName() == null || describeThingTypeRequest.getThingTypeName().equals(getThingTypeName());
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public int hashCode() {
        return 31 + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode());
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingTypeName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1072.append(getThingTypeName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeThingTypeRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }
}
