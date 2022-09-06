package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditFinding;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AuditFindingJsonUnmarshaller implements Unmarshaller<AuditFinding, JsonUnmarshallerContext> {
    private static AuditFindingJsonUnmarshaller instance;

    AuditFindingJsonUnmarshaller() {
    }

    public static AuditFindingJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditFindingJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditFinding unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditFinding auditFinding = new AuditFinding();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("taskId")) {
                auditFinding.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("checkName")) {
                auditFinding.setCheckName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("taskStartTime")) {
                auditFinding.setTaskStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("findingTime")) {
                auditFinding.setFindingTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("severity")) {
                auditFinding.setSeverity(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nonCompliantResource")) {
                auditFinding.setNonCompliantResource(NonCompliantResourceJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("relatedResources")) {
                auditFinding.setRelatedResources(new ListUnmarshaller(RelatedResourceJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("reasonForNonCompliance")) {
                auditFinding.setReasonForNonCompliance(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("reasonForNonComplianceCode")) {
                auditFinding.setReasonForNonComplianceCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditFinding;
    }
}
