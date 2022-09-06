package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.LogStream;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class LogStreamJsonMarshaller {
    private static LogStreamJsonMarshaller instance;

    LogStreamJsonMarshaller() {
    }

    public static LogStreamJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new LogStreamJsonMarshaller();
        }
        return instance;
    }

    public void marshall(LogStream logStream, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (logStream.getLogStreamName() != null) {
            String logStreamName = logStream.getLogStreamName();
            awsJsonWriter.name("logStreamName");
            awsJsonWriter.value(logStreamName);
        }
        if (logStream.getCreationTime() != null) {
            Long creationTime = logStream.getCreationTime();
            awsJsonWriter.name("creationTime");
            awsJsonWriter.value(creationTime);
        }
        if (logStream.getFirstEventTimestamp() != null) {
            Long firstEventTimestamp = logStream.getFirstEventTimestamp();
            awsJsonWriter.name("firstEventTimestamp");
            awsJsonWriter.value(firstEventTimestamp);
        }
        if (logStream.getLastEventTimestamp() != null) {
            Long lastEventTimestamp = logStream.getLastEventTimestamp();
            awsJsonWriter.name("lastEventTimestamp");
            awsJsonWriter.value(lastEventTimestamp);
        }
        if (logStream.getLastIngestionTime() != null) {
            Long lastIngestionTime = logStream.getLastIngestionTime();
            awsJsonWriter.name("lastIngestionTime");
            awsJsonWriter.value(lastIngestionTime);
        }
        if (logStream.getUploadSequenceToken() != null) {
            String uploadSequenceToken = logStream.getUploadSequenceToken();
            awsJsonWriter.name("uploadSequenceToken");
            awsJsonWriter.value(uploadSequenceToken);
        }
        if (logStream.getArn() != null) {
            String arn = logStream.getArn();
            awsJsonWriter.name("arn");
            awsJsonWriter.value(arn);
        }
        if (logStream.getStoredBytes() != null) {
            Long storedBytes = logStream.getStoredBytes();
            awsJsonWriter.name("storedBytes");
            awsJsonWriter.value(storedBytes);
        }
        awsJsonWriter.endObject();
    }
}
