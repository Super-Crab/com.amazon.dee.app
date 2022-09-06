package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RegistrationConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class RegistrationConfigJsonUnmarshaller implements Unmarshaller<RegistrationConfig, JsonUnmarshallerContext> {
    private static RegistrationConfigJsonUnmarshaller instance;

    RegistrationConfigJsonUnmarshaller() {
    }

    public static RegistrationConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RegistrationConfigJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RegistrationConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RegistrationConfig registrationConfig = new RegistrationConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("templateBody")) {
                registrationConfig.setTemplateBody(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("roleArn")) {
                registrationConfig.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return registrationConfig;
    }
}
