package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.SubscriptionFilter;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class SubscriptionFilterJsonUnmarshaller implements Unmarshaller<SubscriptionFilter, JsonUnmarshallerContext> {
    private static SubscriptionFilterJsonUnmarshaller instance;

    SubscriptionFilterJsonUnmarshaller() {
    }

    public static SubscriptionFilterJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SubscriptionFilterJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SubscriptionFilter unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SubscriptionFilter subscriptionFilter = new SubscriptionFilter();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("filterName")) {
                subscriptionFilter.setFilterName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("logGroupName")) {
                subscriptionFilter.setLogGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("filterPattern")) {
                subscriptionFilter.setFilterPattern(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("destinationArn")) {
                subscriptionFilter.setDestinationArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("roleArn")) {
                subscriptionFilter.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("distribution")) {
                subscriptionFilter.setDistribution(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("creationTime")) {
                subscriptionFilter.setCreationTime(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return subscriptionFilter;
    }
}
