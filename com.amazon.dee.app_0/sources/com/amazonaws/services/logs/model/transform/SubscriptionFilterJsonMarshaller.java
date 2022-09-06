package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.SubscriptionFilter;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class SubscriptionFilterJsonMarshaller {
    private static SubscriptionFilterJsonMarshaller instance;

    SubscriptionFilterJsonMarshaller() {
    }

    public static SubscriptionFilterJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SubscriptionFilterJsonMarshaller();
        }
        return instance;
    }

    public void marshall(SubscriptionFilter subscriptionFilter, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (subscriptionFilter.getFilterName() != null) {
            String filterName = subscriptionFilter.getFilterName();
            awsJsonWriter.name("filterName");
            awsJsonWriter.value(filterName);
        }
        if (subscriptionFilter.getLogGroupName() != null) {
            String logGroupName = subscriptionFilter.getLogGroupName();
            awsJsonWriter.name("logGroupName");
            awsJsonWriter.value(logGroupName);
        }
        if (subscriptionFilter.getFilterPattern() != null) {
            String filterPattern = subscriptionFilter.getFilterPattern();
            awsJsonWriter.name("filterPattern");
            awsJsonWriter.value(filterPattern);
        }
        if (subscriptionFilter.getDestinationArn() != null) {
            String destinationArn = subscriptionFilter.getDestinationArn();
            awsJsonWriter.name("destinationArn");
            awsJsonWriter.value(destinationArn);
        }
        if (subscriptionFilter.getRoleArn() != null) {
            String roleArn = subscriptionFilter.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (subscriptionFilter.getDistribution() != null) {
            String distribution = subscriptionFilter.getDistribution();
            awsJsonWriter.name("distribution");
            awsJsonWriter.value(distribution);
        }
        if (subscriptionFilter.getCreationTime() != null) {
            Long creationTime = subscriptionFilter.getCreationTime();
            awsJsonWriter.name("creationTime");
            awsJsonWriter.value(creationTime);
        }
        awsJsonWriter.endObject();
    }
}
