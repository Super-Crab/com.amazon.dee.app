package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeCACertificateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeCACertificateResultJsonUnmarshaller implements Unmarshaller<DescribeCACertificateResult, JsonUnmarshallerContext> {
    private static DescribeCACertificateResultJsonUnmarshaller instance;

    public static DescribeCACertificateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeCACertificateResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeCACertificateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeCACertificateResult describeCACertificateResult = new DescribeCACertificateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificateDescription")) {
                describeCACertificateResult.setCertificateDescription(CACertificateDescriptionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("registrationConfig")) {
                describeCACertificateResult.setRegistrationConfig(RegistrationConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeCACertificateResult;
    }
}
