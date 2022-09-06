package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditFinding;
import com.amazonaws.services.iot.model.NonCompliantResource;
import com.amazonaws.services.iot.model.RelatedResource;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
class AuditFindingJsonMarshaller {
    private static AuditFindingJsonMarshaller instance;

    AuditFindingJsonMarshaller() {
    }

    public static AuditFindingJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuditFindingJsonMarshaller();
        }
        return instance;
    }

    public void marshall(AuditFinding auditFinding, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (auditFinding.getTaskId() != null) {
            String taskId = auditFinding.getTaskId();
            awsJsonWriter.name("taskId");
            awsJsonWriter.value(taskId);
        }
        if (auditFinding.getCheckName() != null) {
            String checkName = auditFinding.getCheckName();
            awsJsonWriter.name("checkName");
            awsJsonWriter.value(checkName);
        }
        if (auditFinding.getTaskStartTime() != null) {
            Date taskStartTime = auditFinding.getTaskStartTime();
            awsJsonWriter.name("taskStartTime");
            awsJsonWriter.value(taskStartTime);
        }
        if (auditFinding.getFindingTime() != null) {
            Date findingTime = auditFinding.getFindingTime();
            awsJsonWriter.name("findingTime");
            awsJsonWriter.value(findingTime);
        }
        if (auditFinding.getSeverity() != null) {
            String severity = auditFinding.getSeverity();
            awsJsonWriter.name("severity");
            awsJsonWriter.value(severity);
        }
        if (auditFinding.getNonCompliantResource() != null) {
            NonCompliantResource nonCompliantResource = auditFinding.getNonCompliantResource();
            awsJsonWriter.name("nonCompliantResource");
            NonCompliantResourceJsonMarshaller.getInstance().marshall(nonCompliantResource, awsJsonWriter);
        }
        if (auditFinding.getRelatedResources() != null) {
            List<RelatedResource> relatedResources = auditFinding.getRelatedResources();
            awsJsonWriter.name("relatedResources");
            awsJsonWriter.beginArray();
            for (RelatedResource relatedResource : relatedResources) {
                if (relatedResource != null) {
                    RelatedResourceJsonMarshaller.getInstance().marshall(relatedResource, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (auditFinding.getReasonForNonCompliance() != null) {
            String reasonForNonCompliance = auditFinding.getReasonForNonCompliance();
            awsJsonWriter.name("reasonForNonCompliance");
            awsJsonWriter.value(reasonForNonCompliance);
        }
        if (auditFinding.getReasonForNonComplianceCode() != null) {
            String reasonForNonComplianceCode = auditFinding.getReasonForNonComplianceCode();
            awsJsonWriter.name("reasonForNonComplianceCode");
            awsJsonWriter.value(reasonForNonComplianceCode);
        }
        awsJsonWriter.endObject();
    }
}
