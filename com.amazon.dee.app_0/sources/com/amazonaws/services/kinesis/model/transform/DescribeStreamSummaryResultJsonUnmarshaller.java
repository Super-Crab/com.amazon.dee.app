package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.DescribeStreamSummaryResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeStreamSummaryResultJsonUnmarshaller implements Unmarshaller<DescribeStreamSummaryResult, JsonUnmarshallerContext> {
    private static DescribeStreamSummaryResultJsonUnmarshaller instance;

    public static DescribeStreamSummaryResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeStreamSummaryResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeStreamSummaryResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeStreamSummaryResult describeStreamSummaryResult = new DescribeStreamSummaryResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("StreamDescriptionSummary")) {
                describeStreamSummaryResult.setStreamDescriptionSummary(StreamDescriptionSummaryJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeStreamSummaryResult;
    }
}
