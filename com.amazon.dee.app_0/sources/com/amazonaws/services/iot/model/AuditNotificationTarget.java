package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AuditNotificationTarget implements Serializable {
    private Boolean enabled;
    private String roleArn;
    private String targetArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditNotificationTarget)) {
            return false;
        }
        AuditNotificationTarget auditNotificationTarget = (AuditNotificationTarget) obj;
        if ((auditNotificationTarget.getTargetArn() == null) ^ (getTargetArn() == null)) {
            return false;
        }
        if (auditNotificationTarget.getTargetArn() != null && !auditNotificationTarget.getTargetArn().equals(getTargetArn())) {
            return false;
        }
        if ((auditNotificationTarget.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (auditNotificationTarget.getRoleArn() != null && !auditNotificationTarget.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((auditNotificationTarget.getEnabled() == null) ^ (getEnabled() == null)) {
            return false;
        }
        return auditNotificationTarget.getEnabled() == null || auditNotificationTarget.getEnabled().equals(getEnabled());
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getTargetArn() {
        return this.targetArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getTargetArn() == null ? 0 : getTargetArn().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31;
        if (getEnabled() != null) {
            i = getEnabled().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean bool) {
        this.enabled = bool;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setTargetArn(String str) {
        this.targetArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTargetArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("targetArn: ");
            outline1072.append(getTargetArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1073.append(getRoleArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getEnabled() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("enabled: ");
            outline1074.append(getEnabled());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AuditNotificationTarget withEnabled(Boolean bool) {
        this.enabled = bool;
        return this;
    }

    public AuditNotificationTarget withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public AuditNotificationTarget withTargetArn(String str) {
        this.targetArn = str;
        return this;
    }
}
