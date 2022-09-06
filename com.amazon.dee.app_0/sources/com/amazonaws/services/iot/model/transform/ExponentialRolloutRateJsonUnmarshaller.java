package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ExponentialRolloutRate;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ExponentialRolloutRateJsonUnmarshaller implements Unmarshaller<ExponentialRolloutRate, JsonUnmarshallerContext> {
    private static ExponentialRolloutRateJsonUnmarshaller instance;

    ExponentialRolloutRateJsonUnmarshaller() {
    }

    public static ExponentialRolloutRateJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ExponentialRolloutRateJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ExponentialRolloutRate unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ExponentialRolloutRate exponentialRolloutRate = new ExponentialRolloutRate();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("baseRatePerMinute")) {
                exponentialRolloutRate.setBaseRatePerMinute(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("incrementFactor")) {
                exponentialRolloutRate.setIncrementFactor(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("rateIncreaseCriteria")) {
                exponentialRolloutRate.setRateIncreaseCriteria(RateIncreaseCriteriaJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return exponentialRolloutRate;
    }
}
