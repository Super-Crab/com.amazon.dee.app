package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CloudwatchAlarmAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class CloudwatchAlarmActionJsonUnmarshaller implements Unmarshaller<CloudwatchAlarmAction, JsonUnmarshallerContext> {
    private static CloudwatchAlarmActionJsonUnmarshaller instance;

    CloudwatchAlarmActionJsonUnmarshaller() {
    }

    public static CloudwatchAlarmActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CloudwatchAlarmActionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CloudwatchAlarmAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CloudwatchAlarmAction cloudwatchAlarmAction = new CloudwatchAlarmAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleArn")) {
                cloudwatchAlarmAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("alarmName")) {
                cloudwatchAlarmAction.setAlarmName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("stateReason")) {
                cloudwatchAlarmAction.setStateReason(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("stateValue")) {
                cloudwatchAlarmAction.setStateValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return cloudwatchAlarmAction;
    }
}
