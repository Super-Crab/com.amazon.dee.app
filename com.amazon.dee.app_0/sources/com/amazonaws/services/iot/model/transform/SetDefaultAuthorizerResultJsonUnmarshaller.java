package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SetDefaultAuthorizerResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class SetDefaultAuthorizerResultJsonUnmarshaller implements Unmarshaller<SetDefaultAuthorizerResult, JsonUnmarshallerContext> {
    private static SetDefaultAuthorizerResultJsonUnmarshaller instance;

    public static SetDefaultAuthorizerResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SetDefaultAuthorizerResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SetDefaultAuthorizerResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        SetDefaultAuthorizerResult setDefaultAuthorizerResult = new SetDefaultAuthorizerResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("authorizerName")) {
                setDefaultAuthorizerResult.setAuthorizerName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("authorizerArn")) {
                setDefaultAuthorizerResult.setAuthorizerArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return setDefaultAuthorizerResult;
    }
}
