package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LoggingOptionsPayload;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class LoggingOptionsPayloadJsonMarshaller {
    private static LoggingOptionsPayloadJsonMarshaller instance;

    LoggingOptionsPayloadJsonMarshaller() {
    }

    public static LoggingOptionsPayloadJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new LoggingOptionsPayloadJsonMarshaller();
        }
        return instance;
    }

    public void marshall(LoggingOptionsPayload loggingOptionsPayload, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (loggingOptionsPayload.getRoleArn() != null) {
            String roleArn = loggingOptionsPayload.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (loggingOptionsPayload.getLogLevel() != null) {
            String logLevel = loggingOptionsPayload.getLogLevel();
            awsJsonWriter.name("logLevel");
            awsJsonWriter.value(logLevel);
        }
        awsJsonWriter.endObject();
    }
}
