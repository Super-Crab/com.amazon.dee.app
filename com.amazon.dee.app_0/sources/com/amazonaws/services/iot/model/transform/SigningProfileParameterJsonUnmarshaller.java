package com.amazonaws.services.iot.model.transform;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazonaws.services.iot.model.SigningProfileParameter;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class SigningProfileParameterJsonUnmarshaller implements Unmarshaller<SigningProfileParameter, JsonUnmarshallerContext> {
    private static SigningProfileParameterJsonUnmarshaller instance;

    SigningProfileParameterJsonUnmarshaller() {
    }

    public static SigningProfileParameterJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SigningProfileParameterJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SigningProfileParameter unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SigningProfileParameter signingProfileParameter = new SigningProfileParameter();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificateArn")) {
                signingProfileParameter.setCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(MetricsConfiguration.PLATFORM)) {
                signingProfileParameter.setPlatform(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("certificatePathOnDevice")) {
                signingProfileParameter.setCertificatePathOnDevice(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return signingProfileParameter;
    }
}
