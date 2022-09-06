package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Stream;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class StreamJsonMarshaller {
    private static StreamJsonMarshaller instance;

    StreamJsonMarshaller() {
    }

    public static StreamJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new StreamJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Stream stream, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (stream.getStreamId() != null) {
            String streamId = stream.getStreamId();
            awsJsonWriter.name("streamId");
            awsJsonWriter.value(streamId);
        }
        if (stream.getFileId() != null) {
            Integer fileId = stream.getFileId();
            awsJsonWriter.name("fileId");
            awsJsonWriter.value(fileId);
        }
        awsJsonWriter.endObject();
    }
}
