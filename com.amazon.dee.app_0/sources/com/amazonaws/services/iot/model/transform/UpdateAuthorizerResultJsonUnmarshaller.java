package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateAuthorizerResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class UpdateAuthorizerResultJsonUnmarshaller implements Unmarshaller<UpdateAuthorizerResult, JsonUnmarshallerContext> {
    private static UpdateAuthorizerResultJsonUnmarshaller instance;

    public static UpdateAuthorizerResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateAuthorizerResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateAuthorizerResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateAuthorizerResult updateAuthorizerResult = new UpdateAuthorizerResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("authorizerName")) {
                updateAuthorizerResult.setAuthorizerName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("authorizerArn")) {
                updateAuthorizerResult.setAuthorizerArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateAuthorizerResult;
    }
}
