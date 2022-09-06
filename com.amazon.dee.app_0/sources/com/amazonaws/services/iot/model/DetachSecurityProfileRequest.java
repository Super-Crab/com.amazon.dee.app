package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DetachSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private String securityProfileName;
    private String securityProfileTargetArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DetachSecurityProfileRequest)) {
            return false;
        }
        DetachSecurityProfileRequest detachSecurityProfileRequest = (DetachSecurityProfileRequest) obj;
        if ((detachSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (detachSecurityProfileRequest.getSecurityProfileName() != null && !detachSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((detachSecurityProfileRequest.getSecurityProfileTargetArn() == null) ^ (getSecurityProfileTargetArn() == null)) {
            return false;
        }
        return detachSecurityProfileRequest.getSecurityProfileTargetArn() == null || detachSecurityProfileRequest.getSecurityProfileTargetArn().equals(getSecurityProfileTargetArn());
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public String getSecurityProfileTargetArn() {
        return this.securityProfileTargetArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31;
        if (getSecurityProfileTargetArn() != null) {
            i = getSecurityProfileTargetArn().hashCode();
        }
        return hashCode + i;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public void setSecurityProfileTargetArn(String str) {
        this.securityProfileTargetArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileName: ");
            outline1072.append(getSecurityProfileName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSecurityProfileTargetArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("securityProfileTargetArn: ");
            outline1073.append(getSecurityProfileTargetArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DetachSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public DetachSecurityProfileRequest withSecurityProfileTargetArn(String str) {
        this.securityProfileTargetArn = str;
        return this;
    }
}
