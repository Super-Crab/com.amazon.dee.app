package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.DescribeLogStreamsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeLogStreamsResultJsonUnmarshaller implements Unmarshaller<DescribeLogStreamsResult, JsonUnmarshallerContext> {
    private static DescribeLogStreamsResultJsonUnmarshaller instance;

    public static DescribeLogStreamsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeLogStreamsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeLogStreamsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeLogStreamsResult describeLogStreamsResult = new DescribeLogStreamsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("logStreams")) {
                describeLogStreamsResult.setLogStreams(new ListUnmarshaller(LogStreamJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                describeLogStreamsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeLogStreamsResult;
    }
}
