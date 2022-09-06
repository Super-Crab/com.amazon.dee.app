package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class MetricValueJsonUnmarshaller implements Unmarshaller<MetricValue, JsonUnmarshallerContext> {
    private static MetricValueJsonUnmarshaller instance;

    MetricValueJsonUnmarshaller() {
    }

    public static MetricValueJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MetricValueJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MetricValue unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MetricValue metricValue = new MetricValue();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("count")) {
                metricValue.setCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("cidrs")) {
                metricValue.setCidrs(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ports")) {
                metricValue.setPorts(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return metricValue;
    }
}
