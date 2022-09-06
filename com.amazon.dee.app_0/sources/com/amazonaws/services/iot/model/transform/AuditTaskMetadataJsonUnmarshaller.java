package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditTaskMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AuditTaskMetadataJsonUnmarshaller implements Unmarshaller<AuditTaskMetadata, JsonUnmarshallerContext> {
    private static AuditTaskMetadataJsonUnmarshaller instance;

    AuditTaskMetadataJsonUnmarshaller() {
    }

    public static AuditTaskMetadataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditTaskMetadataJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditTaskMetadata unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditTaskMetadata auditTaskMetadata = new AuditTaskMetadata();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("taskId")) {
                auditTaskMetadata.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("taskStatus")) {
                auditTaskMetadata.setTaskStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("taskType")) {
                auditTaskMetadata.setTaskType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditTaskMetadata;
    }
}
