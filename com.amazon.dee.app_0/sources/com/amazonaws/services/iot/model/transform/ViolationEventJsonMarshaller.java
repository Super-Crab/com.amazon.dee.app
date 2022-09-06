package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.accessorykit.InputBehaviorConfigurationTransformer;
import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.services.iot.model.ViolationEvent;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class ViolationEventJsonMarshaller {
    private static ViolationEventJsonMarshaller instance;

    ViolationEventJsonMarshaller() {
    }

    public static ViolationEventJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ViolationEventJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ViolationEvent violationEvent, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (violationEvent.getViolationId() != null) {
            String violationId = violationEvent.getViolationId();
            awsJsonWriter.name("violationId");
            awsJsonWriter.value(violationId);
        }
        if (violationEvent.getThingName() != null) {
            String thingName = violationEvent.getThingName();
            awsJsonWriter.name("thingName");
            awsJsonWriter.value(thingName);
        }
        if (violationEvent.getSecurityProfileName() != null) {
            String securityProfileName = violationEvent.getSecurityProfileName();
            awsJsonWriter.name("securityProfileName");
            awsJsonWriter.value(securityProfileName);
        }
        if (violationEvent.getBehavior() != null) {
            Behavior behavior = violationEvent.getBehavior();
            awsJsonWriter.name(InputBehaviorConfigurationTransformer.KEY_BEHAVIOR_VALUE);
            BehaviorJsonMarshaller.getInstance().marshall(behavior, awsJsonWriter);
        }
        if (violationEvent.getMetricValue() != null) {
            MetricValue metricValue = violationEvent.getMetricValue();
            awsJsonWriter.name("metricValue");
            MetricValueJsonMarshaller.getInstance().marshall(metricValue, awsJsonWriter);
        }
        if (violationEvent.getViolationEventType() != null) {
            String violationEventType = violationEvent.getViolationEventType();
            awsJsonWriter.name("violationEventType");
            awsJsonWriter.value(violationEventType);
        }
        if (violationEvent.getViolationEventTime() != null) {
            Date violationEventTime = violationEvent.getViolationEventTime();
            awsJsonWriter.name("violationEventTime");
            awsJsonWriter.value(violationEventTime);
        }
        awsJsonWriter.endObject();
    }
}
