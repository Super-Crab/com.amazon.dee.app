package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateSecurityProfileResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateSecurityProfileResultJsonUnmarshaller implements Unmarshaller<CreateSecurityProfileResult, JsonUnmarshallerContext> {
    private static CreateSecurityProfileResultJsonUnmarshaller instance;

    public static CreateSecurityProfileResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateSecurityProfileResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateSecurityProfileResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateSecurityProfileResult createSecurityProfileResult = new CreateSecurityProfileResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("securityProfileName")) {
                createSecurityProfileResult.setSecurityProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("securityProfileArn")) {
                createSecurityProfileResult.setSecurityProfileArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createSecurityProfileResult;
    }
}
