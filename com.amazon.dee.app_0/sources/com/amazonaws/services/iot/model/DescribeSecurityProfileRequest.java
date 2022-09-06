package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private String securityProfileName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeSecurityProfileRequest)) {
            return false;
        }
        DescribeSecurityProfileRequest describeSecurityProfileRequest = (DescribeSecurityProfileRequest) obj;
        if ((describeSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        return describeSecurityProfileRequest.getSecurityProfileName() == null || describeSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName());
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public int hashCode() {
        return 31 + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode());
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileName: ");
            outline1072.append(getSecurityProfileName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }
}
