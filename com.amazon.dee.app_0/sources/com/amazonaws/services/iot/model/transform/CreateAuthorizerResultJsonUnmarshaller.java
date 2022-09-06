package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateAuthorizerResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateAuthorizerResultJsonUnmarshaller implements Unmarshaller<CreateAuthorizerResult, JsonUnmarshallerContext> {
    private static CreateAuthorizerResultJsonUnmarshaller instance;

    public static CreateAuthorizerResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateAuthorizerResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateAuthorizerResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateAuthorizerResult createAuthorizerResult = new CreateAuthorizerResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("authorizerName")) {
                createAuthorizerResult.setAuthorizerName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("authorizerArn")) {
                createAuthorizerResult.setAuthorizerArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createAuthorizerResult;
    }
}
