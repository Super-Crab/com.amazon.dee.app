package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RegisterThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class RegisterThingResultJsonUnmarshaller implements Unmarshaller<RegisterThingResult, JsonUnmarshallerContext> {
    private static RegisterThingResultJsonUnmarshaller instance;

    public static RegisterThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RegisterThingResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RegisterThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        RegisterThingResult registerThingResult = new RegisterThingResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificatePem")) {
                registerThingResult.setCertificatePem(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("resourceArns")) {
                registerThingResult.setResourceArns(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return registerThingResult;
    }
}
