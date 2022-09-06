package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BehaviorCriteria;
import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class BehaviorCriteriaJsonMarshaller {
    private static BehaviorCriteriaJsonMarshaller instance;

    BehaviorCriteriaJsonMarshaller() {
    }

    public static BehaviorCriteriaJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new BehaviorCriteriaJsonMarshaller();
        }
        return instance;
    }

    public void marshall(BehaviorCriteria behaviorCriteria, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (behaviorCriteria.getComparisonOperator() != null) {
            String comparisonOperator = behaviorCriteria.getComparisonOperator();
            awsJsonWriter.name("comparisonOperator");
            awsJsonWriter.value(comparisonOperator);
        }
        if (behaviorCriteria.getValue() != null) {
            MetricValue value = behaviorCriteria.getValue();
            awsJsonWriter.name("value");
            MetricValueJsonMarshaller.getInstance().marshall(value, awsJsonWriter);
        }
        if (behaviorCriteria.getDurationSeconds() != null) {
            Integer durationSeconds = behaviorCriteria.getDurationSeconds();
            awsJsonWriter.name("durationSeconds");
            awsJsonWriter.value(durationSeconds);
        }
        awsJsonWriter.endObject();
    }
}
