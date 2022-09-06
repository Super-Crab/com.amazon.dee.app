package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeJobResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeJobResultJsonUnmarshaller implements Unmarshaller<DescribeJobResult, JsonUnmarshallerContext> {
    private static DescribeJobResultJsonUnmarshaller instance;

    public static DescribeJobResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeJobResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeJobResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeJobResult describeJobResult = new DescribeJobResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("documentSource")) {
                describeJobResult.setDocumentSource(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("job")) {
                describeJobResult.setJob(JobJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeJobResult;
    }
}
