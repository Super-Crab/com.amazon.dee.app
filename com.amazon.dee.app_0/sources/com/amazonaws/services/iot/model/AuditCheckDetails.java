package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AuditCheckDetails implements Serializable {
    private Boolean checkCompliant;
    private String checkRunStatus;
    private String errorCode;
    private String message;
    private Long nonCompliantResourcesCount;
    private Long totalResourcesCount;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditCheckDetails)) {
            return false;
        }
        AuditCheckDetails auditCheckDetails = (AuditCheckDetails) obj;
        if ((auditCheckDetails.getCheckRunStatus() == null) ^ (getCheckRunStatus() == null)) {
            return false;
        }
        if (auditCheckDetails.getCheckRunStatus() != null && !auditCheckDetails.getCheckRunStatus().equals(getCheckRunStatus())) {
            return false;
        }
        if ((auditCheckDetails.getCheckCompliant() == null) ^ (getCheckCompliant() == null)) {
            return false;
        }
        if (auditCheckDetails.getCheckCompliant() != null && !auditCheckDetails.getCheckCompliant().equals(getCheckCompliant())) {
            return false;
        }
        if ((auditCheckDetails.getTotalResourcesCount() == null) ^ (getTotalResourcesCount() == null)) {
            return false;
        }
        if (auditCheckDetails.getTotalResourcesCount() != null && !auditCheckDetails.getTotalResourcesCount().equals(getTotalResourcesCount())) {
            return false;
        }
        if ((auditCheckDetails.getNonCompliantResourcesCount() == null) ^ (getNonCompliantResourcesCount() == null)) {
            return false;
        }
        if (auditCheckDetails.getNonCompliantResourcesCount() != null && !auditCheckDetails.getNonCompliantResourcesCount().equals(getNonCompliantResourcesCount())) {
            return false;
        }
        if ((auditCheckDetails.getErrorCode() == null) ^ (getErrorCode() == null)) {
            return false;
        }
        if (auditCheckDetails.getErrorCode() != null && !auditCheckDetails.getErrorCode().equals(getErrorCode())) {
            return false;
        }
        if ((auditCheckDetails.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        return auditCheckDetails.getMessage() == null || auditCheckDetails.getMessage().equals(getMessage());
    }

    public Boolean getCheckCompliant() {
        return this.checkCompliant;
    }

    public String getCheckRunStatus() {
        return this.checkRunStatus;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getNonCompliantResourcesCount() {
        return this.nonCompliantResourcesCount;
    }

    public Long getTotalResourcesCount() {
        return this.totalResourcesCount;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getCheckRunStatus() == null ? 0 : getCheckRunStatus().hashCode()) + 31) * 31) + (getCheckCompliant() == null ? 0 : getCheckCompliant().hashCode())) * 31) + (getTotalResourcesCount() == null ? 0 : getTotalResourcesCount().hashCode())) * 31) + (getNonCompliantResourcesCount() == null ? 0 : getNonCompliantResourcesCount().hashCode())) * 31) + (getErrorCode() == null ? 0 : getErrorCode().hashCode())) * 31;
        if (getMessage() != null) {
            i = getMessage().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isCheckCompliant() {
        return this.checkCompliant;
    }

    public void setCheckCompliant(Boolean bool) {
        this.checkCompliant = bool;
    }

    public void setCheckRunStatus(String str) {
        this.checkRunStatus = str;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setNonCompliantResourcesCount(Long l) {
        this.nonCompliantResourcesCount = l;
    }

    public void setTotalResourcesCount(Long l) {
        this.totalResourcesCount = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCheckRunStatus() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("checkRunStatus: ");
            outline1072.append(getCheckRunStatus());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCheckCompliant() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("checkCompliant: ");
            outline1073.append(getCheckCompliant());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTotalResourcesCount() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("totalResourcesCount: ");
            outline1074.append(getTotalResourcesCount());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getNonCompliantResourcesCount() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("nonCompliantResourcesCount: ");
            outline1075.append(getNonCompliantResourcesCount());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getErrorCode() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("errorCode: ");
            outline1076.append(getErrorCode());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getMessage() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("message: ");
            outline1077.append(getMessage());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AuditCheckDetails withCheckCompliant(Boolean bool) {
        this.checkCompliant = bool;
        return this;
    }

    public AuditCheckDetails withCheckRunStatus(String str) {
        this.checkRunStatus = str;
        return this;
    }

    public AuditCheckDetails withErrorCode(String str) {
        this.errorCode = str;
        return this;
    }

    public AuditCheckDetails withMessage(String str) {
        this.message = str;
        return this;
    }

    public AuditCheckDetails withNonCompliantResourcesCount(Long l) {
        this.nonCompliantResourcesCount = l;
        return this;
    }

    public AuditCheckDetails withTotalResourcesCount(Long l) {
        this.totalResourcesCount = l;
        return this;
    }

    public void setCheckRunStatus(AuditCheckRunStatus auditCheckRunStatus) {
        this.checkRunStatus = auditCheckRunStatus.toString();
    }

    public AuditCheckDetails withCheckRunStatus(AuditCheckRunStatus auditCheckRunStatus) {
        this.checkRunStatus = auditCheckRunStatus.toString();
        return this;
    }
}
