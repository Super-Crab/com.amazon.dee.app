package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditCheckDetails;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class AuditCheckDetailsJsonMarshaller {
    private static AuditCheckDetailsJsonMarshaller instance;

    AuditCheckDetailsJsonMarshaller() {
    }

    public static AuditCheckDetailsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuditCheckDetailsJsonMarshaller();
        }
        return instance;
    }

    public void marshall(AuditCheckDetails auditCheckDetails, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (auditCheckDetails.getCheckRunStatus() != null) {
            String checkRunStatus = auditCheckDetails.getCheckRunStatus();
            awsJsonWriter.name("checkRunStatus");
            awsJsonWriter.value(checkRunStatus);
        }
        if (auditCheckDetails.getCheckCompliant() != null) {
            Boolean checkCompliant = auditCheckDetails.getCheckCompliant();
            awsJsonWriter.name("checkCompliant");
            awsJsonWriter.value(checkCompliant.booleanValue());
        }
        if (auditCheckDetails.getTotalResourcesCount() != null) {
            Long totalResourcesCount = auditCheckDetails.getTotalResourcesCount();
            awsJsonWriter.name("totalResourcesCount");
            awsJsonWriter.value(totalResourcesCount);
        }
        if (auditCheckDetails.getNonCompliantResourcesCount() != null) {
            Long nonCompliantResourcesCount = auditCheckDetails.getNonCompliantResourcesCount();
            awsJsonWriter.name("nonCompliantResourcesCount");
            awsJsonWriter.value(nonCompliantResourcesCount);
        }
        if (auditCheckDetails.getErrorCode() != null) {
            String errorCode = auditCheckDetails.getErrorCode();
            awsJsonWriter.name("errorCode");
            awsJsonWriter.value(errorCode);
        }
        if (auditCheckDetails.getMessage() != null) {
            String message = auditCheckDetails.getMessage();
            awsJsonWriter.name("message");
            awsJsonWriter.value(message);
        }
        awsJsonWriter.endObject();
    }
}
