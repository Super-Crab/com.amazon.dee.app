package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RepublishAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class RepublishActionJsonUnmarshaller implements Unmarshaller<RepublishAction, JsonUnmarshallerContext> {
    private static RepublishActionJsonUnmarshaller instance;

    RepublishActionJsonUnmarshaller() {
    }

    public static RepublishActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RepublishActionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RepublishAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RepublishAction republishAction = new RepublishAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleArn")) {
                republishAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("topic")) {
                republishAction.setTopic(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return republishAction;
    }
}
