package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CloudwatchAlarmAction;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class CloudwatchAlarmActionJsonMarshaller {
    private static CloudwatchAlarmActionJsonMarshaller instance;

    CloudwatchAlarmActionJsonMarshaller() {
    }

    public static CloudwatchAlarmActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CloudwatchAlarmActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(CloudwatchAlarmAction cloudwatchAlarmAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (cloudwatchAlarmAction.getRoleArn() != null) {
            String roleArn = cloudwatchAlarmAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (cloudwatchAlarmAction.getAlarmName() != null) {
            String alarmName = cloudwatchAlarmAction.getAlarmName();
            awsJsonWriter.name("alarmName");
            awsJsonWriter.value(alarmName);
        }
        if (cloudwatchAlarmAction.getStateReason() != null) {
            String stateReason = cloudwatchAlarmAction.getStateReason();
            awsJsonWriter.name("stateReason");
            awsJsonWriter.value(stateReason);
        }
        if (cloudwatchAlarmAction.getStateValue() != null) {
            String stateValue = cloudwatchAlarmAction.getStateValue();
            awsJsonWriter.name("stateValue");
            awsJsonWriter.value(stateValue);
        }
        awsJsonWriter.endObject();
    }
}
