package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.accessorykit.InputBehaviorConfigurationTransformer;
import com.amazonaws.services.iot.model.ActiveViolation;
import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class ActiveViolationJsonMarshaller {
    private static ActiveViolationJsonMarshaller instance;

    ActiveViolationJsonMarshaller() {
    }

    public static ActiveViolationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ActiveViolationJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ActiveViolation activeViolation, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (activeViolation.getViolationId() != null) {
            String violationId = activeViolation.getViolationId();
            awsJsonWriter.name("violationId");
            awsJsonWriter.value(violationId);
        }
        if (activeViolation.getThingName() != null) {
            String thingName = activeViolation.getThingName();
            awsJsonWriter.name("thingName");
            awsJsonWriter.value(thingName);
        }
        if (activeViolation.getSecurityProfileName() != null) {
            String securityProfileName = activeViolation.getSecurityProfileName();
            awsJsonWriter.name("securityProfileName");
            awsJsonWriter.value(securityProfileName);
        }
        if (activeViolation.getBehavior() != null) {
            Behavior behavior = activeViolation.getBehavior();
            awsJsonWriter.name(InputBehaviorConfigurationTransformer.KEY_BEHAVIOR_VALUE);
            BehaviorJsonMarshaller.getInstance().marshall(behavior, awsJsonWriter);
        }
        if (activeViolation.getLastViolationValue() != null) {
            MetricValue lastViolationValue = activeViolation.getLastViolationValue();
            awsJsonWriter.name("lastViolationValue");
            MetricValueJsonMarshaller.getInstance().marshall(lastViolationValue, awsJsonWriter);
        }
        if (activeViolation.getLastViolationTime() != null) {
            Date lastViolationTime = activeViolation.getLastViolationTime();
            awsJsonWriter.name("lastViolationTime");
            awsJsonWriter.value(lastViolationTime);
        }
        if (activeViolation.getViolationStartTime() != null) {
            Date violationStartTime = activeViolation.getViolationStartTime();
            awsJsonWriter.name("violationStartTime");
            awsJsonWriter.value(violationStartTime);
        }
        awsJsonWriter.endObject();
    }
}
