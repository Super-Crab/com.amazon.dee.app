package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StreamSummary;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class StreamSummaryJsonMarshaller {
    private static StreamSummaryJsonMarshaller instance;

    StreamSummaryJsonMarshaller() {
    }

    public static StreamSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new StreamSummaryJsonMarshaller();
        }
        return instance;
    }

    public void marshall(StreamSummary streamSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (streamSummary.getStreamId() != null) {
            String streamId = streamSummary.getStreamId();
            awsJsonWriter.name("streamId");
            awsJsonWriter.value(streamId);
        }
        if (streamSummary.getStreamArn() != null) {
            String streamArn = streamSummary.getStreamArn();
            awsJsonWriter.name("streamArn");
            awsJsonWriter.value(streamArn);
        }
        if (streamSummary.getStreamVersion() != null) {
            Integer streamVersion = streamSummary.getStreamVersion();
            awsJsonWriter.name("streamVersion");
            awsJsonWriter.value(streamVersion);
        }
        if (streamSummary.getDescription() != null) {
            String description = streamSummary.getDescription();
            awsJsonWriter.name("description");
            awsJsonWriter.value(description);
        }
        awsJsonWriter.endObject();
    }
}
