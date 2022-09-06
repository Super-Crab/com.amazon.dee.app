package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.DescribeSubscriptionFiltersResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeSubscriptionFiltersResultJsonUnmarshaller implements Unmarshaller<DescribeSubscriptionFiltersResult, JsonUnmarshallerContext> {
    private static DescribeSubscriptionFiltersResultJsonUnmarshaller instance;

    public static DescribeSubscriptionFiltersResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeSubscriptionFiltersResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeSubscriptionFiltersResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeSubscriptionFiltersResult describeSubscriptionFiltersResult = new DescribeSubscriptionFiltersResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("subscriptionFilters")) {
                describeSubscriptionFiltersResult.setSubscriptionFilters(new ListUnmarshaller(SubscriptionFilterJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                describeSubscriptionFiltersResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeSubscriptionFiltersResult;
    }
}
