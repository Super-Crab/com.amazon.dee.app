package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LambdaAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class LambdaActionJsonUnmarshaller implements Unmarshaller<LambdaAction, JsonUnmarshallerContext> {
    private static LambdaActionJsonUnmarshaller instance;

    LambdaActionJsonUnmarshaller() {
    }

    public static LambdaActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new LambdaActionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public LambdaAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        LambdaAction lambdaAction = new LambdaAction();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("functionArn")) {
                lambdaAction.setFunctionArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return lambdaAction;
    }
}
