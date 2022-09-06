package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.S3Destination;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class S3DestinationJsonMarshaller {
    private static S3DestinationJsonMarshaller instance;

    S3DestinationJsonMarshaller() {
    }

    public static S3DestinationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new S3DestinationJsonMarshaller();
        }
        return instance;
    }

    public void marshall(S3Destination s3Destination, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (s3Destination.getBucket() != null) {
            String bucket = s3Destination.getBucket();
            awsJsonWriter.name("bucket");
            awsJsonWriter.value(bucket);
        }
        if (s3Destination.getPrefix() != null) {
            String prefix = s3Destination.getPrefix();
            awsJsonWriter.name("prefix");
            awsJsonWriter.value(prefix);
        }
        awsJsonWriter.endObject();
    }
}
