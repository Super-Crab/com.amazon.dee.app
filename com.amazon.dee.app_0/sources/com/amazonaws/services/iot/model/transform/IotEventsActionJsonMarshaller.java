package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IotEventsAction;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class IotEventsActionJsonMarshaller {
    private static IotEventsActionJsonMarshaller instance;

    IotEventsActionJsonMarshaller() {
    }

    public static IotEventsActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new IotEventsActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(IotEventsAction iotEventsAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (iotEventsAction.getInputName() != null) {
            String inputName = iotEventsAction.getInputName();
            awsJsonWriter.name("inputName");
            awsJsonWriter.value(inputName);
        }
        if (iotEventsAction.getMessageId() != null) {
            String messageId = iotEventsAction.getMessageId();
            awsJsonWriter.name("messageId");
            awsJsonWriter.value(messageId);
        }
        if (iotEventsAction.getRoleArn() != null) {
            String roleArn = iotEventsAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }
}
