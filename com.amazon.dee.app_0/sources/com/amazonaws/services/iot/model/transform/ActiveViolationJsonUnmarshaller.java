package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.accessorykit.InputBehaviorConfigurationTransformer;
import com.amazonaws.services.iot.model.ActiveViolation;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ActiveViolationJsonUnmarshaller implements Unmarshaller<ActiveViolation, JsonUnmarshallerContext> {
    private static ActiveViolationJsonUnmarshaller instance;

    ActiveViolationJsonUnmarshaller() {
    }

    public static ActiveViolationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ActiveViolationJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ActiveViolation unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ActiveViolation activeViolation = new ActiveViolation();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("violationId")) {
                activeViolation.setViolationId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("thingName")) {
                activeViolation.setThingName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("securityProfileName")) {
                activeViolation.setSecurityProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(InputBehaviorConfigurationTransformer.KEY_BEHAVIOR_VALUE)) {
                activeViolation.setBehavior(BehaviorJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("lastViolationValue")) {
                activeViolation.setLastViolationValue(MetricValueJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("lastViolationTime")) {
                activeViolation.setLastViolationTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("violationStartTime")) {
                activeViolation.setViolationStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return activeViolation;
    }
}
