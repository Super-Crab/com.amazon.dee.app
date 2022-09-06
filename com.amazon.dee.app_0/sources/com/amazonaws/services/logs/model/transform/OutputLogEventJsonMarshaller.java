package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.OutputLogEvent;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class OutputLogEventJsonMarshaller {
    private static OutputLogEventJsonMarshaller instance;

    OutputLogEventJsonMarshaller() {
    }

    public static OutputLogEventJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new OutputLogEventJsonMarshaller();
        }
        return instance;
    }

    public void marshall(OutputLogEvent outputLogEvent, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (outputLogEvent.getTimestamp() != null) {
            Long timestamp = outputLogEvent.getTimestamp();
            awsJsonWriter.name("timestamp");
            awsJsonWriter.value(timestamp);
        }
        if (outputLogEvent.getMessage() != null) {
            String message = outputLogEvent.getMessage();
            awsJsonWriter.name("message");
            awsJsonWriter.value(message);
        }
        if (outputLogEvent.getIngestionTime() != null) {
            Long ingestionTime = outputLogEvent.getIngestionTime();
            awsJsonWriter.name("ingestionTime");
            awsJsonWriter.value(ingestionTime);
        }
        awsJsonWriter.endObject();
    }
}
