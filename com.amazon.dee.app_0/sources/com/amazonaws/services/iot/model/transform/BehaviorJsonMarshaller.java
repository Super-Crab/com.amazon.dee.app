package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.BehaviorCriteria;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class BehaviorJsonMarshaller {
    private static BehaviorJsonMarshaller instance;

    BehaviorJsonMarshaller() {
    }

    public static BehaviorJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new BehaviorJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Behavior behavior, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (behavior.getName() != null) {
            String name = behavior.getName();
            awsJsonWriter.name("name");
            awsJsonWriter.value(name);
        }
        if (behavior.getMetric() != null) {
            String metric = behavior.getMetric();
            awsJsonWriter.name("metric");
            awsJsonWriter.value(metric);
        }
        if (behavior.getCriteria() != null) {
            BehaviorCriteria criteria = behavior.getCriteria();
            awsJsonWriter.name("criteria");
            BehaviorCriteriaJsonMarshaller.getInstance().marshall(criteria, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
