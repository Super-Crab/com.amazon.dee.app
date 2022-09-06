package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeStreamResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeStreamResultJsonUnmarshaller implements Unmarshaller<DescribeStreamResult, JsonUnmarshallerContext> {
    private static DescribeStreamResultJsonUnmarshaller instance;

    public static DescribeStreamResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeStreamResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeStreamResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeStreamResult describeStreamResult = new DescribeStreamResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("streamInfo")) {
                describeStreamResult.setStreamInfo(StreamInfoJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeStreamResult;
    }
}
