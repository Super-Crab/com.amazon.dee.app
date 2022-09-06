package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.FirehoseAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class FirehoseActionJsonUnmarshaller implements Unmarshaller<FirehoseAction, JsonUnmarshallerContext> {
    private static FirehoseActionJsonUnmarshaller instance;

    FirehoseActionJsonUnmarshaller() {
    }

    public static FirehoseActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new FirehoseActionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public FirehoseAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        FirehoseAction firehoseAction = new FirehoseAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleArn")) {
                firehoseAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("deliveryStreamName")) {
                firehoseAction.setDeliveryStreamName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("separator")) {
                firehoseAction.setSeparator(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return firehoseAction;
    }
}
