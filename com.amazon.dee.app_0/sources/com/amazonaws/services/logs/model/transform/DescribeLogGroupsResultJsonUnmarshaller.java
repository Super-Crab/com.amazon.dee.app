package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.DescribeLogGroupsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeLogGroupsResultJsonUnmarshaller implements Unmarshaller<DescribeLogGroupsResult, JsonUnmarshallerContext> {
    private static DescribeLogGroupsResultJsonUnmarshaller instance;

    public static DescribeLogGroupsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeLogGroupsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeLogGroupsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeLogGroupsResult describeLogGroupsResult = new DescribeLogGroupsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("logGroups")) {
                describeLogGroupsResult.setLogGroups(new ListUnmarshaller(LogGroupJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                describeLogGroupsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeLogGroupsResult;
    }
}
