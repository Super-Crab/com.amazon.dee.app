package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RepublishAction;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class RepublishActionJsonMarshaller {
    private static RepublishActionJsonMarshaller instance;

    RepublishActionJsonMarshaller() {
    }

    public static RepublishActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RepublishActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(RepublishAction republishAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (republishAction.getRoleArn() != null) {
            String roleArn = republishAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (republishAction.getTopic() != null) {
            String topic = republishAction.getTopic();
            awsJsonWriter.name("topic");
            awsJsonWriter.value(topic);
        }
        awsJsonWriter.endObject();
    }
}
