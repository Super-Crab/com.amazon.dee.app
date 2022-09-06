package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.MetricFilter;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class MetricFilterJsonUnmarshaller implements Unmarshaller<MetricFilter, JsonUnmarshallerContext> {
    private static MetricFilterJsonUnmarshaller instance;

    MetricFilterJsonUnmarshaller() {
    }

    public static MetricFilterJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MetricFilterJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MetricFilter unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MetricFilter metricFilter = new MetricFilter();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("filterName")) {
                metricFilter.setFilterName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("filterPattern")) {
                metricFilter.setFilterPattern(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("metricTransformations")) {
                metricFilter.setMetricTransformations(new ListUnmarshaller(MetricTransformationJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("creationTime")) {
                metricFilter.setCreationTime(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("logGroupName")) {
                metricFilter.setLogGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return metricFilter;
    }
}
