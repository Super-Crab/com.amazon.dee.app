package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.S3Location;
import com.amazonaws.services.iot.model.StreamFile;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class StreamFileJsonMarshaller {
    private static StreamFileJsonMarshaller instance;

    StreamFileJsonMarshaller() {
    }

    public static StreamFileJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new StreamFileJsonMarshaller();
        }
        return instance;
    }

    public void marshall(StreamFile streamFile, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (streamFile.getFileId() != null) {
            Integer fileId = streamFile.getFileId();
            awsJsonWriter.name("fileId");
            awsJsonWriter.value(fileId);
        }
        if (streamFile.getS3Location() != null) {
            S3Location s3Location = streamFile.getS3Location();
            awsJsonWriter.name("s3Location");
            S3LocationJsonMarshaller.getInstance().marshall(s3Location, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
