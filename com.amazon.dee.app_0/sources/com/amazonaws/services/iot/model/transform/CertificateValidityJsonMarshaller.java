package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CertificateValidity;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class CertificateValidityJsonMarshaller {
    private static CertificateValidityJsonMarshaller instance;

    CertificateValidityJsonMarshaller() {
    }

    public static CertificateValidityJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CertificateValidityJsonMarshaller();
        }
        return instance;
    }

    public void marshall(CertificateValidity certificateValidity, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (certificateValidity.getNotBefore() != null) {
            Date notBefore = certificateValidity.getNotBefore();
            awsJsonWriter.name("notBefore");
            awsJsonWriter.value(notBefore);
        }
        if (certificateValidity.getNotAfter() != null) {
            Date notAfter = certificateValidity.getNotAfter();
            awsJsonWriter.name("notAfter");
            awsJsonWriter.value(notAfter);
        }
        awsJsonWriter.endObject();
    }
}
