package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BehaviorCriteria;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class BehaviorCriteriaJsonUnmarshaller implements Unmarshaller<BehaviorCriteria, JsonUnmarshallerContext> {
    private static BehaviorCriteriaJsonUnmarshaller instance;

    BehaviorCriteriaJsonUnmarshaller() {
    }

    public static BehaviorCriteriaJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BehaviorCriteriaJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public BehaviorCriteria unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        BehaviorCriteria behaviorCriteria = new BehaviorCriteria();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("comparisonOperator")) {
                behaviorCriteria.setComparisonOperator(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("value")) {
                behaviorCriteria.setValue(MetricValueJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("durationSeconds")) {
                behaviorCriteria.setDurationSeconds(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return behaviorCriteria;
    }
}
