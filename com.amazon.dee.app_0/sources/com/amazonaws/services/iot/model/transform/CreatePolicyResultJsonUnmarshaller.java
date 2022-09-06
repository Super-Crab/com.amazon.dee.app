package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreatePolicyResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreatePolicyResultJsonUnmarshaller implements Unmarshaller<CreatePolicyResult, JsonUnmarshallerContext> {
    private static CreatePolicyResultJsonUnmarshaller instance;

    public static CreatePolicyResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreatePolicyResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreatePolicyResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreatePolicyResult createPolicyResult = new CreatePolicyResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("policyName")) {
                createPolicyResult.setPolicyName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("policyArn")) {
                createPolicyResult.setPolicyArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("policyDocument")) {
                createPolicyResult.setPolicyDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("policyVersionId")) {
                createPolicyResult.setPolicyVersionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createPolicyResult;
    }
}
