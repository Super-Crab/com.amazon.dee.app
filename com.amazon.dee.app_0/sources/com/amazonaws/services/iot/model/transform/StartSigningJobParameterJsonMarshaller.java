package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Destination;
import com.amazonaws.services.iot.model.SigningProfileParameter;
import com.amazonaws.services.iot.model.StartSigningJobParameter;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class StartSigningJobParameterJsonMarshaller {
    private static StartSigningJobParameterJsonMarshaller instance;

    StartSigningJobParameterJsonMarshaller() {
    }

    public static StartSigningJobParameterJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new StartSigningJobParameterJsonMarshaller();
        }
        return instance;
    }

    public void marshall(StartSigningJobParameter startSigningJobParameter, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (startSigningJobParameter.getSigningProfileParameter() != null) {
            SigningProfileParameter signingProfileParameter = startSigningJobParameter.getSigningProfileParameter();
            awsJsonWriter.name("signingProfileParameter");
            SigningProfileParameterJsonMarshaller.getInstance().marshall(signingProfileParameter, awsJsonWriter);
        }
        if (startSigningJobParameter.getSigningProfileName() != null) {
            String signingProfileName = startSigningJobParameter.getSigningProfileName();
            awsJsonWriter.name("signingProfileName");
            awsJsonWriter.value(signingProfileName);
        }
        if (startSigningJobParameter.getDestination() != null) {
            Destination destination = startSigningJobParameter.getDestination();
            awsJsonWriter.name("destination");
            DestinationJsonMarshaller.getInstance().marshall(destination, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
