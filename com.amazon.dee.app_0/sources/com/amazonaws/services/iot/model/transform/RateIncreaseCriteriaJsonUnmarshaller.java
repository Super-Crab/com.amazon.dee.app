package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RateIncreaseCriteria;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class RateIncreaseCriteriaJsonUnmarshaller implements Unmarshaller<RateIncreaseCriteria, JsonUnmarshallerContext> {
    private static RateIncreaseCriteriaJsonUnmarshaller instance;

    RateIncreaseCriteriaJsonUnmarshaller() {
    }

    public static RateIncreaseCriteriaJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RateIncreaseCriteriaJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RateIncreaseCriteria unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RateIncreaseCriteria rateIncreaseCriteria = new RateIncreaseCriteria();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("numberOfNotifiedThings")) {
                rateIncreaseCriteria.setNumberOfNotifiedThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("numberOfSucceededThings")) {
                rateIncreaseCriteria.setNumberOfSucceededThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return rateIncreaseCriteria;
    }
}
