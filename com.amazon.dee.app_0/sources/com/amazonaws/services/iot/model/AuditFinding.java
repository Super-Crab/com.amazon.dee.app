package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
public class AuditFinding implements Serializable {
    private String checkName;
    private Date findingTime;
    private NonCompliantResource nonCompliantResource;
    private String reasonForNonCompliance;
    private String reasonForNonComplianceCode;
    private List<RelatedResource> relatedResources;
    private String severity;
    private String taskId;
    private Date taskStartTime;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditFinding)) {
            return false;
        }
        AuditFinding auditFinding = (AuditFinding) obj;
        if ((auditFinding.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (auditFinding.getTaskId() != null && !auditFinding.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((auditFinding.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (auditFinding.getCheckName() != null && !auditFinding.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((auditFinding.getTaskStartTime() == null) ^ (getTaskStartTime() == null)) {
            return false;
        }
        if (auditFinding.getTaskStartTime() != null && !auditFinding.getTaskStartTime().equals(getTaskStartTime())) {
            return false;
        }
        if ((auditFinding.getFindingTime() == null) ^ (getFindingTime() == null)) {
            return false;
        }
        if (auditFinding.getFindingTime() != null && !auditFinding.getFindingTime().equals(getFindingTime())) {
            return false;
        }
        if ((auditFinding.getSeverity() == null) ^ (getSeverity() == null)) {
            return false;
        }
        if (auditFinding.getSeverity() != null && !auditFinding.getSeverity().equals(getSeverity())) {
            return false;
        }
        if ((auditFinding.getNonCompliantResource() == null) ^ (getNonCompliantResource() == null)) {
            return false;
        }
        if (auditFinding.getNonCompliantResource() != null && !auditFinding.getNonCompliantResource().equals(getNonCompliantResource())) {
            return false;
        }
        if ((auditFinding.getRelatedResources() == null) ^ (getRelatedResources() == null)) {
            return false;
        }
        if (auditFinding.getRelatedResources() != null && !auditFinding.getRelatedResources().equals(getRelatedResources())) {
            return false;
        }
        if ((auditFinding.getReasonForNonCompliance() == null) ^ (getReasonForNonCompliance() == null)) {
            return false;
        }
        if (auditFinding.getReasonForNonCompliance() != null && !auditFinding.getReasonForNonCompliance().equals(getReasonForNonCompliance())) {
            return false;
        }
        if ((auditFinding.getReasonForNonComplianceCode() == null) ^ (getReasonForNonComplianceCode() == null)) {
            return false;
        }
        return auditFinding.getReasonForNonComplianceCode() == null || auditFinding.getReasonForNonComplianceCode().equals(getReasonForNonComplianceCode());
    }

    public String getCheckName() {
        return this.checkName;
    }

    public Date getFindingTime() {
        return this.findingTime;
    }

    public NonCompliantResource getNonCompliantResource() {
        return this.nonCompliantResource;
    }

    public String getReasonForNonCompliance() {
        return this.reasonForNonCompliance;
    }

    public String getReasonForNonComplianceCode() {
        return this.reasonForNonComplianceCode;
    }

    public List<RelatedResource> getRelatedResources() {
        return this.relatedResources;
    }

    public String getSeverity() {
        return this.severity;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public Date getTaskStartTime() {
        return this.taskStartTime;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getCheckName() == null ? 0 : getCheckName().hashCode())) * 31) + (getTaskStartTime() == null ? 0 : getTaskStartTime().hashCode())) * 31) + (getFindingTime() == null ? 0 : getFindingTime().hashCode())) * 31) + (getSeverity() == null ? 0 : getSeverity().hashCode())) * 31) + (getNonCompliantResource() == null ? 0 : getNonCompliantResource().hashCode())) * 31) + (getRelatedResources() == null ? 0 : getRelatedResources().hashCode())) * 31) + (getReasonForNonCompliance() == null ? 0 : getReasonForNonCompliance().hashCode())) * 31;
        if (getReasonForNonComplianceCode() != null) {
            i = getReasonForNonComplianceCode().hashCode();
        }
        return hashCode + i;
    }

    public void setCheckName(String str) {
        this.checkName = str;
    }

    public void setFindingTime(Date date) {
        this.findingTime = date;
    }

    public void setNonCompliantResource(NonCompliantResource nonCompliantResource) {
        this.nonCompliantResource = nonCompliantResource;
    }

    public void setReasonForNonCompliance(String str) {
        this.reasonForNonCompliance = str;
    }

    public void setReasonForNonComplianceCode(String str) {
        this.reasonForNonComplianceCode = str;
    }

    public void setRelatedResources(Collection<RelatedResource> collection) {
        if (collection == null) {
            this.relatedResources = null;
        } else {
            this.relatedResources = new ArrayList(collection);
        }
    }

    public void setSeverity(String str) {
        this.severity = str;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public void setTaskStartTime(Date date) {
        this.taskStartTime = date;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskId: ");
            outline1072.append(getTaskId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCheckName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("checkName: ");
            outline1073.append(getCheckName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTaskStartTime() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("taskStartTime: ");
            outline1074.append(getTaskStartTime());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getFindingTime() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("findingTime: ");
            outline1075.append(getFindingTime());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getSeverity() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("severity: ");
            outline1076.append(getSeverity());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getNonCompliantResource() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("nonCompliantResource: ");
            outline1077.append(getNonCompliantResource());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getRelatedResources() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("relatedResources: ");
            outline1078.append(getRelatedResources());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getReasonForNonCompliance() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("reasonForNonCompliance: ");
            outline1079.append(getReasonForNonCompliance());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getReasonForNonComplianceCode() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("reasonForNonComplianceCode: ");
            outline10710.append(getReasonForNonComplianceCode());
            outline107.append(outline10710.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AuditFinding withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public AuditFinding withFindingTime(Date date) {
        this.findingTime = date;
        return this;
    }

    public AuditFinding withNonCompliantResource(NonCompliantResource nonCompliantResource) {
        this.nonCompliantResource = nonCompliantResource;
        return this;
    }

    public AuditFinding withReasonForNonCompliance(String str) {
        this.reasonForNonCompliance = str;
        return this;
    }

    public AuditFinding withReasonForNonComplianceCode(String str) {
        this.reasonForNonComplianceCode = str;
        return this;
    }

    public AuditFinding withRelatedResources(RelatedResource... relatedResourceArr) {
        if (getRelatedResources() == null) {
            this.relatedResources = new ArrayList(relatedResourceArr.length);
        }
        for (RelatedResource relatedResource : relatedResourceArr) {
            this.relatedResources.add(relatedResource);
        }
        return this;
    }

    public AuditFinding withSeverity(String str) {
        this.severity = str;
        return this;
    }

    public AuditFinding withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public AuditFinding withTaskStartTime(Date date) {
        this.taskStartTime = date;
        return this;
    }

    public void setSeverity(AuditFindingSeverity auditFindingSeverity) {
        this.severity = auditFindingSeverity.toString();
    }

    public AuditFinding withSeverity(AuditFindingSeverity auditFindingSeverity) {
        this.severity = auditFindingSeverity.toString();
        return this;
    }

    public AuditFinding withRelatedResources(Collection<RelatedResource> collection) {
        setRelatedResources(collection);
        return this;
    }
}
