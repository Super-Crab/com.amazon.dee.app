package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditNotificationTarget;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AuditNotificationTargetJsonUnmarshaller implements Unmarshaller<AuditNotificationTarget, JsonUnmarshallerContext> {
    private static AuditNotificationTargetJsonUnmarshaller instance;

    AuditNotificationTargetJsonUnmarshaller() {
    }

    public static AuditNotificationTargetJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditNotificationTargetJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditNotificationTarget unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditNotificationTarget auditNotificationTarget = new AuditNotificationTarget();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("targetArn")) {
                auditNotificationTarget.setTargetArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("roleArn")) {
                auditNotificationTarget.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("enabled")) {
                auditNotificationTarget.setEnabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditNotificationTarget;
    }
}
