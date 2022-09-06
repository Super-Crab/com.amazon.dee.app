package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.DescribeResourcePoliciesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeResourcePoliciesResultJsonUnmarshaller implements Unmarshaller<DescribeResourcePoliciesResult, JsonUnmarshallerContext> {
    private static DescribeResourcePoliciesResultJsonUnmarshaller instance;

    public static DescribeResourcePoliciesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeResourcePoliciesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeResourcePoliciesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeResourcePoliciesResult describeResourcePoliciesResult = new DescribeResourcePoliciesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("resourcePolicies")) {
                describeResourcePoliciesResult.setResourcePolicies(new ListUnmarshaller(ResourcePolicyJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                describeResourcePoliciesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeResourcePoliciesResult;
    }
}
