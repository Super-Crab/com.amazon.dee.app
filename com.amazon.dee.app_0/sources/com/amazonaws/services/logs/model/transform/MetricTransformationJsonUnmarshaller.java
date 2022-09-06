package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.MetricTransformation;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class MetricTransformationJsonUnmarshaller implements Unmarshaller<MetricTransformation, JsonUnmarshallerContext> {
    private static MetricTransformationJsonUnmarshaller instance;

    MetricTransformationJsonUnmarshaller() {
    }

    public static MetricTransformationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MetricTransformationJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MetricTransformation unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MetricTransformation metricTransformation = new MetricTransformation();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("metricName")) {
                metricTransformation.setMetricName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("metricNamespace")) {
                metricTransformation.setMetricNamespace(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("metricValue")) {
                metricTransformation.setMetricValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("defaultValue")) {
                metricTransformation.setDefaultValue(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return metricTransformation;
    }
}
