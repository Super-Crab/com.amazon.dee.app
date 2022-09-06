package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.accessorykit.InputBehaviorConfigurationTransformer;
import com.amazonaws.services.iot.model.ViolationEvent;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ViolationEventJsonUnmarshaller implements Unmarshaller<ViolationEvent, JsonUnmarshallerContext> {
    private static ViolationEventJsonUnmarshaller instance;

    ViolationEventJsonUnmarshaller() {
    }

    public static ViolationEventJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ViolationEventJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ViolationEvent unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ViolationEvent violationEvent = new ViolationEvent();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("violationId")) {
                violationEvent.setViolationId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("thingName")) {
                violationEvent.setThingName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("securityProfileName")) {
                violationEvent.setSecurityProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(InputBehaviorConfigurationTransformer.KEY_BEHAVIOR_VALUE)) {
                violationEvent.setBehavior(BehaviorJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("metricValue")) {
                violationEvent.setMetricValue(MetricValueJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("violationEventType")) {
                violationEvent.setViolationEventType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("violationEventTime")) {
                violationEvent.setViolationEventTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return violationEvent;
    }
}
