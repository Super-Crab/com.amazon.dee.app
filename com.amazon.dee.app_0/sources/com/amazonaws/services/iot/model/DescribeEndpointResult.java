package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeEndpointResult implements Serializable {
    private String endpointAddress;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeEndpointResult)) {
            return false;
        }
        DescribeEndpointResult describeEndpointResult = (DescribeEndpointResult) obj;
        if ((describeEndpointResult.getEndpointAddress() == null) ^ (getEndpointAddress() == null)) {
            return false;
        }
        return describeEndpointResult.getEndpointAddress() == null || describeEndpointResult.getEndpointAddress().equals(getEndpointAddress());
    }

    public String getEndpointAddress() {
        return this.endpointAddress;
    }

    public int hashCode() {
        return 31 + (getEndpointAddress() == null ? 0 : getEndpointAddress().hashCode());
    }

    public void setEndpointAddress(String str) {
        this.endpointAddress = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEndpointAddress() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("endpointAddress: ");
            outline1072.append(getEndpointAddress());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeEndpointResult withEndpointAddress(String str) {
        this.endpointAddress = str;
        return this;
    }
}
