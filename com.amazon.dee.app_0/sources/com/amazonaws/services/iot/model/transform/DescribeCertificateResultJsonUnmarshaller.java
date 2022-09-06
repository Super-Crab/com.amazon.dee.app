package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeCertificateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeCertificateResultJsonUnmarshaller implements Unmarshaller<DescribeCertificateResult, JsonUnmarshallerContext> {
    private static DescribeCertificateResultJsonUnmarshaller instance;

    public static DescribeCertificateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeCertificateResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeCertificateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeCertificateResult describeCertificateResult = new DescribeCertificateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("certificateDescription")) {
                describeCertificateResult.setCertificateDescription(CertificateDescriptionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeCertificateResult;
    }
}
