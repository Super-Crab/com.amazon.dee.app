package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.DescribeMetricFiltersResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeMetricFiltersResultJsonUnmarshaller implements Unmarshaller<DescribeMetricFiltersResult, JsonUnmarshallerContext> {
    private static DescribeMetricFiltersResultJsonUnmarshaller instance;

    public static DescribeMetricFiltersResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeMetricFiltersResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeMetricFiltersResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeMetricFiltersResult describeMetricFiltersResult = new DescribeMetricFiltersResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("metricFilters")) {
                describeMetricFiltersResult.setMetricFilters(new ListUnmarshaller(MetricFilterJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                describeMetricFiltersResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeMetricFiltersResult;
    }
}
