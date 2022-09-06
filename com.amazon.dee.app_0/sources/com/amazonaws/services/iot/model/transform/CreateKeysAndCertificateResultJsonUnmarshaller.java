package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateKeysAndCertificateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateKeysAndCertificateResultJsonUnmarshaller implements Unmarshaller<CreateKeysAndCertificateResult, JsonUnmarshallerContext> {
    private static CreateKeysAndCertificateResultJsonUnmarshaller instance;

    public static CreateKeysAndCertificateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateKeysAndCertificateResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateKeysAndCertificateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateKeysAndCertificateResult createKeysAndCertificateResult = new CreateKeysAndCertificateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificateArn")) {
                createKeysAndCertificateResult.setCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("certificateId")) {
                createKeysAndCertificateResult.setCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("certificatePem")) {
                createKeysAndCertificateResult.setCertificatePem(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("keyPair")) {
                createKeysAndCertificateResult.setKeyPair(KeyPairJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createKeysAndCertificateResult;
    }
}
