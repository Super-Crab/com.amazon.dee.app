package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Destination;
import com.amazonaws.services.iot.model.S3Destination;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class DestinationJsonMarshaller {
    private static DestinationJsonMarshaller instance;

    DestinationJsonMarshaller() {
    }

    public static DestinationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DestinationJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Destination destination, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (destination.getS3Destination() != null) {
            S3Destination s3Destination = destination.getS3Destination();
            awsJsonWriter.name("s3Destination");
            S3DestinationJsonMarshaller.getInstance().marshall(s3Destination, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
