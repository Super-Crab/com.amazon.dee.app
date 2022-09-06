package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AlertTarget;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AlertTargetJsonUnmarshaller implements Unmarshaller<AlertTarget, JsonUnmarshallerContext> {
    private static AlertTargetJsonUnmarshaller instance;

    AlertTargetJsonUnmarshaller() {
    }

    public static AlertTargetJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AlertTargetJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AlertTarget unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AlertTarget alertTarget = new AlertTarget();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("alertTargetArn")) {
                alertTarget.setAlertTargetArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("roleArn")) {
                alertTarget.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return alertTarget;
    }
}
