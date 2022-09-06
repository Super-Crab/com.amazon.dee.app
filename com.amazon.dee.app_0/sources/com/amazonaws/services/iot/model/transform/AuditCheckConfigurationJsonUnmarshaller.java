package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditCheckConfiguration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AuditCheckConfigurationJsonUnmarshaller implements Unmarshaller<AuditCheckConfiguration, JsonUnmarshallerContext> {
    private static AuditCheckConfigurationJsonUnmarshaller instance;

    AuditCheckConfigurationJsonUnmarshaller() {
    }

    public static AuditCheckConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditCheckConfigurationJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditCheckConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditCheckConfiguration auditCheckConfiguration = new AuditCheckConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("enabled")) {
                auditCheckConfiguration.setEnabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditCheckConfiguration;
    }
}
