package com.amazonaws.services.iot.model.transform;

import com.amazon.commscore.api.metrics.MobilyticsCustomEntries;
import com.amazonaws.services.iot.model.IotAnalyticsAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class IotAnalyticsActionJsonUnmarshaller implements Unmarshaller<IotAnalyticsAction, JsonUnmarshallerContext> {
    private static IotAnalyticsActionJsonUnmarshaller instance;

    IotAnalyticsActionJsonUnmarshaller() {
    }

    public static IotAnalyticsActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new IotAnalyticsActionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public IotAnalyticsAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        IotAnalyticsAction iotAnalyticsAction = new IotAnalyticsAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("channelArn")) {
                iotAnalyticsAction.setChannelArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(MobilyticsCustomEntries.CHANNEL_NAME)) {
                iotAnalyticsAction.setChannelName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("roleArn")) {
                iotAnalyticsAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return iotAnalyticsAction;
    }
}
