package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CertificateValidity;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class CertificateValidityJsonUnmarshaller implements Unmarshaller<CertificateValidity, JsonUnmarshallerContext> {
    private static CertificateValidityJsonUnmarshaller instance;

    CertificateValidityJsonUnmarshaller() {
    }

    public static CertificateValidityJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CertificateValidityJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CertificateValidity unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CertificateValidity certificateValidity = new CertificateValidity();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("notBefore")) {
                certificateValidity.setNotBefore(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("notAfter")) {
                certificateValidity.setNotAfter(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return certificateValidity;
    }
}
