package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetRegistrationCodeResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class GetRegistrationCodeResultJsonUnmarshaller implements Unmarshaller<GetRegistrationCodeResult, JsonUnmarshallerContext> {
    private static GetRegistrationCodeResultJsonUnmarshaller instance;

    public static GetRegistrationCodeResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetRegistrationCodeResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetRegistrationCodeResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetRegistrationCodeResult getRegistrationCodeResult = new GetRegistrationCodeResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("registrationCode")) {
                getRegistrationCodeResult.setRegistrationCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getRegistrationCodeResult;
    }
}
