package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditCheckDetails;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AuditCheckDetailsJsonUnmarshaller implements Unmarshaller<AuditCheckDetails, JsonUnmarshallerContext> {
    private static AuditCheckDetailsJsonUnmarshaller instance;

    AuditCheckDetailsJsonUnmarshaller() {
    }

    public static AuditCheckDetailsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditCheckDetailsJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditCheckDetails unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditCheckDetails auditCheckDetails = new AuditCheckDetails();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("checkRunStatus")) {
                auditCheckDetails.setCheckRunStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("checkCompliant")) {
                auditCheckDetails.setCheckCompliant(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("totalResourcesCount")) {
                auditCheckDetails.setTotalResourcesCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nonCompliantResourcesCount")) {
                auditCheckDetails.setNonCompliantResourcesCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("errorCode")) {
                auditCheckDetails.setErrorCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("message")) {
                auditCheckDetails.setMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditCheckDetails;
    }
}
