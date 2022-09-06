package com.amazonaws.services.iot.model.transform;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazonaws.services.iot.model.SigningProfileParameter;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class SigningProfileParameterJsonMarshaller {
    private static SigningProfileParameterJsonMarshaller instance;

    SigningProfileParameterJsonMarshaller() {
    }

    public static SigningProfileParameterJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SigningProfileParameterJsonMarshaller();
        }
        return instance;
    }

    public void marshall(SigningProfileParameter signingProfileParameter, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (signingProfileParameter.getCertificateArn() != null) {
            String certificateArn = signingProfileParameter.getCertificateArn();
            awsJsonWriter.name("certificateArn");
            awsJsonWriter.value(certificateArn);
        }
        if (signingProfileParameter.getPlatform() != null) {
            String platform = signingProfileParameter.getPlatform();
            awsJsonWriter.name(MetricsConfiguration.PLATFORM);
            awsJsonWriter.value(platform);
        }
        if (signingProfileParameter.getCertificatePathOnDevice() != null) {
            String certificatePathOnDevice = signingProfileParameter.getCertificatePathOnDevice();
            awsJsonWriter.name("certificatePathOnDevice");
            awsJsonWriter.value(certificatePathOnDevice);
        }
        awsJsonWriter.endObject();
    }
}
