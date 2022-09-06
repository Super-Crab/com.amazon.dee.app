package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeDefaultAuthorizerResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeDefaultAuthorizerResultJsonUnmarshaller implements Unmarshaller<DescribeDefaultAuthorizerResult, JsonUnmarshallerContext> {
    private static DescribeDefaultAuthorizerResultJsonUnmarshaller instance;

    public static DescribeDefaultAuthorizerResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeDefaultAuthorizerResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeDefaultAuthorizerResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeDefaultAuthorizerResult describeDefaultAuthorizerResult = new DescribeDefaultAuthorizerResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("authorizerDescription")) {
                describeDefaultAuthorizerResult.setAuthorizerDescription(AuthorizerDescriptionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeDefaultAuthorizerResult;
    }
}
