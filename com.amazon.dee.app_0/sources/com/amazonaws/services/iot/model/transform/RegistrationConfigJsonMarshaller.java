package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RegistrationConfig;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class RegistrationConfigJsonMarshaller {
    private static RegistrationConfigJsonMarshaller instance;

    RegistrationConfigJsonMarshaller() {
    }

    public static RegistrationConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RegistrationConfigJsonMarshaller();
        }
        return instance;
    }

    public void marshall(RegistrationConfig registrationConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (registrationConfig.getTemplateBody() != null) {
            String templateBody = registrationConfig.getTemplateBody();
            awsJsonWriter.name("templateBody");
            awsJsonWriter.value(templateBody);
        }
        if (registrationConfig.getRoleArn() != null) {
            String roleArn = registrationConfig.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }
}
