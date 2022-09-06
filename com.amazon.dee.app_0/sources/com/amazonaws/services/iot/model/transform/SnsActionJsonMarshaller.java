package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SnsAction;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class SnsActionJsonMarshaller {
    private static SnsActionJsonMarshaller instance;

    SnsActionJsonMarshaller() {
    }

    public static SnsActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SnsActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(SnsAction snsAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (snsAction.getTargetArn() != null) {
            String targetArn = snsAction.getTargetArn();
            awsJsonWriter.name("targetArn");
            awsJsonWriter.value(targetArn);
        }
        if (snsAction.getRoleArn() != null) {
            String roleArn = snsAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (snsAction.getMessageFormat() != null) {
            String messageFormat = snsAction.getMessageFormat();
            awsJsonWriter.name("messageFormat");
            awsJsonWriter.value(messageFormat);
        }
        awsJsonWriter.endObject();
    }
}
