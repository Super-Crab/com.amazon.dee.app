package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RegisterCACertificateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class RegisterCACertificateResultJsonUnmarshaller implements Unmarshaller<RegisterCACertificateResult, JsonUnmarshallerContext> {
    private static RegisterCACertificateResultJsonUnmarshaller instance;

    public static RegisterCACertificateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RegisterCACertificateResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RegisterCACertificateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        RegisterCACertificateResult registerCACertificateResult = new RegisterCACertificateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificateArn")) {
                registerCACertificateResult.setCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("certificateId")) {
                registerCACertificateResult.setCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return registerCACertificateResult;
    }
}
