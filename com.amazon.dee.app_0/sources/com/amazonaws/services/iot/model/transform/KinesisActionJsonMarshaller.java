package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.KinesisAction;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class KinesisActionJsonMarshaller {
    private static KinesisActionJsonMarshaller instance;

    KinesisActionJsonMarshaller() {
    }

    public static KinesisActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new KinesisActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(KinesisAction kinesisAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (kinesisAction.getRoleArn() != null) {
            String roleArn = kinesisAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (kinesisAction.getStreamName() != null) {
            String streamName = kinesisAction.getStreamName();
            awsJsonWriter.name("streamName");
            awsJsonWriter.value(streamName);
        }
        if (kinesisAction.getPartitionKey() != null) {
            String partitionKey = kinesisAction.getPartitionKey();
            awsJsonWriter.name("partitionKey");
            awsJsonWriter.value(partitionKey);
        }
        awsJsonWriter.endObject();
    }
}
