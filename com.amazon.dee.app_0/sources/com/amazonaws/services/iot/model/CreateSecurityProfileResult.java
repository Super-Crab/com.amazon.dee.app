package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateSecurityProfileResult implements Serializable {
    private String securityProfileArn;
    private String securityProfileName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateSecurityProfileResult)) {
            return false;
        }
        CreateSecurityProfileResult createSecurityProfileResult = (CreateSecurityProfileResult) obj;
        if ((createSecurityProfileResult.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (createSecurityProfileResult.getSecurityProfileName() != null && !createSecurityProfileResult.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((createSecurityProfileResult.getSecurityProfileArn() == null) ^ (getSecurityProfileArn() == null)) {
            return false;
        }
        return createSecurityProfileResult.getSecurityProfileArn() == null || createSecurityProfileResult.getSecurityProfileArn().equals(getSecurityProfileArn());
    }

    public String getSecurityProfileArn() {
        return this.securityProfileArn;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31;
        if (getSecurityProfileArn() != null) {
            i = getSecurityProfileArn().hashCode();
        }
        return hashCode + i;
    }

    public void setSecurityProfileArn(String str) {
        this.securityProfileArn = str;
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
        if (getSecurityProfileArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("securityProfileArn: ");
            outline1073.append(getSecurityProfileArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateSecurityProfileResult withSecurityProfileArn(String str) {
        this.securityProfileArn = str;
        return this;
    }

    public CreateSecurityProfileResult withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }
}
