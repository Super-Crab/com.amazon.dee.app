package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.TestMetricFilterResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class TestMetricFilterResultJsonUnmarshaller implements Unmarshaller<TestMetricFilterResult, JsonUnmarshallerContext> {
    private static TestMetricFilterResultJsonUnmarshaller instance;

    public static TestMetricFilterResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TestMetricFilterResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TestMetricFilterResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        TestMetricFilterResult testMetricFilterResult = new TestMetricFilterResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("matches")) {
                testMetricFilterResult.setMatches(new ListUnmarshaller(MetricFilterMatchRecordJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return testMetricFilterResult;
    }
}
