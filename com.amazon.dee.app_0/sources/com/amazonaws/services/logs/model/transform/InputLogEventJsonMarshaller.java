package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.InputLogEvent;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class InputLogEventJsonMarshaller {
    private static InputLogEventJsonMarshaller instance;

    InputLogEventJsonMarshaller() {
    }

    public static InputLogEventJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new InputLogEventJsonMarshaller();
        }
        return instance;
    }

    public void marshall(InputLogEvent inputLogEvent, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (inputLogEvent.getTimestamp() != null) {
            Long timestamp = inputLogEvent.getTimestamp();
            awsJsonWriter.name("timestamp");
            awsJsonWriter.value(timestamp);
        }
        if (inputLogEvent.getMessage() != null) {
            String message = inputLogEvent.getMessage();
            awsJsonWriter.name("message");
            awsJsonWriter.value(message);
        }
        awsJsonWriter.endObject();
    }
}
