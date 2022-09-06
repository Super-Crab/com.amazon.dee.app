package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateCertificateFromCsrResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateCertificateFromCsrResultJsonUnmarshaller implements Unmarshaller<CreateCertificateFromCsrResult, JsonUnmarshallerContext> {
    private static CreateCertificateFromCsrResultJsonUnmarshaller instance;

    public static CreateCertificateFromCsrResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateCertificateFromCsrResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateCertificateFromCsrResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateCertificateFromCsrResult createCertificateFromCsrResult = new CreateCertificateFromCsrResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificateArn")) {
                createCertificateFromCsrResult.setCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("certificateId")) {
                createCertificateFromCsrResult.setCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("certificatePem")) {
                createCertificateFromCsrResult.setCertificatePem(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createCertificateFromCsrResult;
    }
}
