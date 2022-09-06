package com.amazonaws.services.iot.model.transform;

import com.amazon.commscore.api.metrics.MobilyticsCustomEntries;
import com.amazonaws.services.iot.model.IotAnalyticsAction;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class IotAnalyticsActionJsonMarshaller {
    private static IotAnalyticsActionJsonMarshaller instance;

    IotAnalyticsActionJsonMarshaller() {
    }

    public static IotAnalyticsActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new IotAnalyticsActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(IotAnalyticsAction iotAnalyticsAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (iotAnalyticsAction.getChannelArn() != null) {
            String channelArn = iotAnalyticsAction.getChannelArn();
            awsJsonWriter.name("channelArn");
            awsJsonWriter.value(channelArn);
        }
        if (iotAnalyticsAction.getChannelName() != null) {
            String channelName = iotAnalyticsAction.getChannelName();
            awsJsonWriter.name(MobilyticsCustomEntries.CHANNEL_NAME);
            awsJsonWriter.value(channelName);
        }
        if (iotAnalyticsAction.getRoleArn() != null) {
            String roleArn = iotAnalyticsAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }
}
