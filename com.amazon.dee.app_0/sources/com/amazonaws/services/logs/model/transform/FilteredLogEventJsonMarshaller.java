package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.FilteredLogEvent;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class FilteredLogEventJsonMarshaller {
    private static FilteredLogEventJsonMarshaller instance;

    FilteredLogEventJsonMarshaller() {
    }

    public static FilteredLogEventJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new FilteredLogEventJsonMarshaller();
        }
        return instance;
    }

    public void marshall(FilteredLogEvent filteredLogEvent, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (filteredLogEvent.getLogStreamName() != null) {
            String logStreamName = filteredLogEvent.getLogStreamName();
            awsJsonWriter.name("logStreamName");
            awsJsonWriter.value(logStreamName);
        }
        if (filteredLogEvent.getTimestamp() != null) {
            Long timestamp = filteredLogEvent.getTimestamp();
            awsJsonWriter.name("timestamp");
            awsJsonWriter.value(timestamp);
        }
        if (filteredLogEvent.getMessage() != null) {
            String message = filteredLogEvent.getMessage();
            awsJsonWriter.name("message");
            awsJsonWriter.value(message);
        }
        if (filteredLogEvent.getIngestionTime() != null) {
            Long ingestionTime = filteredLogEvent.getIngestionTime();
            awsJsonWriter.name("ingestionTime");
            awsJsonWriter.value(ingestionTime);
        }
        if (filteredLogEvent.getEventId() != null) {
            String eventId = filteredLogEvent.getEventId();
            awsJsonWriter.name("eventId");
            awsJsonWriter.value(eventId);
        }
        awsJsonWriter.endObject();
    }
}
