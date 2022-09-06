package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String securityProfileName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteSecurityProfileRequest)) {
            return false;
        }
        DeleteSecurityProfileRequest deleteSecurityProfileRequest = (DeleteSecurityProfileRequest) obj;
        if ((deleteSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (deleteSecurityProfileRequest.getSecurityProfileName() != null && !deleteSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((deleteSecurityProfileRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return deleteSecurityProfileRequest.getExpectedVersion() == null || deleteSecurityProfileRequest.getExpectedVersion().equals(getExpectedVersion());
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31;
        if (getExpectedVersion() != null) {
            i = getExpectedVersion().hashCode();
        }
        return hashCode + i;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileName: ");
            outline1072.append(getSecurityProfileName());
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

    public DeleteSecurityProfileRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public DeleteSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }
}
