package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.MetricFilterMatchRecord;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Map;
/* loaded from: classes13.dex */
class MetricFilterMatchRecordJsonMarshaller {
    private static MetricFilterMatchRecordJsonMarshaller instance;

    MetricFilterMatchRecordJsonMarshaller() {
    }

    public static MetricFilterMatchRecordJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MetricFilterMatchRecordJsonMarshaller();
        }
        return instance;
    }

    public void marshall(MetricFilterMatchRecord metricFilterMatchRecord, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (metricFilterMatchRecord.getEventNumber() != null) {
            Long eventNumber = metricFilterMatchRecord.getEventNumber();
            awsJsonWriter.name("eventNumber");
            awsJsonWriter.value(eventNumber);
        }
        if (metricFilterMatchRecord.getEventMessage() != null) {
            String eventMessage = metricFilterMatchRecord.getEventMessage();
            awsJsonWriter.name("eventMessage");
            awsJsonWriter.value(eventMessage);
        }
        if (metricFilterMatchRecord.getExtractedValues() != null) {
            Map<String, String> extractedValues = metricFilterMatchRecord.getExtractedValues();
            awsJsonWriter.name("extractedValues");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : extractedValues.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        awsJsonWriter.endObject();
    }
}
