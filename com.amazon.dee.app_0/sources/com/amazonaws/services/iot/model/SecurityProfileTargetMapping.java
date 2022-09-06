package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SecurityProfileTargetMapping implements Serializable {
    private SecurityProfileIdentifier securityProfileIdentifier;
    private SecurityProfileTarget target;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SecurityProfileTargetMapping)) {
            return false;
        }
        SecurityProfileTargetMapping securityProfileTargetMapping = (SecurityProfileTargetMapping) obj;
        if ((securityProfileTargetMapping.getSecurityProfileIdentifier() == null) ^ (getSecurityProfileIdentifier() == null)) {
            return false;
        }
        if (securityProfileTargetMapping.getSecurityProfileIdentifier() != null && !securityProfileTargetMapping.getSecurityProfileIdentifier().equals(getSecurityProfileIdentifier())) {
            return false;
        }
        if ((securityProfileTargetMapping.getTarget() == null) ^ (getTarget() == null)) {
            return false;
        }
        return securityProfileTargetMapping.getTarget() == null || securityProfileTargetMapping.getTarget().equals(getTarget());
    }

    public SecurityProfileIdentifier getSecurityProfileIdentifier() {
        return this.securityProfileIdentifier;
    }

    public SecurityProfileTarget getTarget() {
        return this.target;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getSecurityProfileIdentifier() == null ? 0 : getSecurityProfileIdentifier().hashCode()) + 31) * 31;
        if (getTarget() != null) {
            i = getTarget().hashCode();
        }
        return hashCode + i;
    }

    public void setSecurityProfileIdentifier(SecurityProfileIdentifier securityProfileIdentifier) {
        this.securityProfileIdentifier = securityProfileIdentifier;
    }

    public void setTarget(SecurityProfileTarget securityProfileTarget) {
        this.target = securityProfileTarget;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileIdentifier() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileIdentifier: ");
            outline1072.append(getSecurityProfileIdentifier());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTarget() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("target: ");
            outline1073.append(getTarget());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SecurityProfileTargetMapping withSecurityProfileIdentifier(SecurityProfileIdentifier securityProfileIdentifier) {
        this.securityProfileIdentifier = securityProfileIdentifier;
        return this;
    }

    public SecurityProfileTargetMapping withTarget(SecurityProfileTarget securityProfileTarget) {
        this.target = securityProfileTarget;
        return this;
    }
}
