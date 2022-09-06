package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeEndpointRequest extends AmazonWebServiceRequest implements Serializable {
    private String endpointType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeEndpointRequest)) {
            return false;
        }
        DescribeEndpointRequest describeEndpointRequest = (DescribeEndpointRequest) obj;
        if ((describeEndpointRequest.getEndpointType() == null) ^ (getEndpointType() == null)) {
            return false;
        }
        return describeEndpointRequest.getEndpointType() == null || describeEndpointRequest.getEndpointType().equals(getEndpointType());
    }

    public String getEndpointType() {
        return this.endpointType;
    }

    public int hashCode() {
        return 31 + (getEndpointType() == null ? 0 : getEndpointType().hashCode());
    }

    public void setEndpointType(String str) {
        this.endpointType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEndpointType() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("endpointType: ");
            outline1072.append(getEndpointType());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeEndpointRequest withEndpointType(String str) {
        this.endpointType = str;
        return this;
    }
}
