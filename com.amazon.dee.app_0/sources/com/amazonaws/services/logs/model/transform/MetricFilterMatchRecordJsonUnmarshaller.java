package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.MetricFilterMatchRecord;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class MetricFilterMatchRecordJsonUnmarshaller implements Unmarshaller<MetricFilterMatchRecord, JsonUnmarshallerContext> {
    private static MetricFilterMatchRecordJsonUnmarshaller instance;

    MetricFilterMatchRecordJsonUnmarshaller() {
    }

    public static MetricFilterMatchRecordJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MetricFilterMatchRecordJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MetricFilterMatchRecord unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MetricFilterMatchRecord metricFilterMatchRecord = new MetricFilterMatchRecord();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("eventNumber")) {
                metricFilterMatchRecord.setEventNumber(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("eventMessage")) {
                metricFilterMatchRecord.setEventMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("extractedValues")) {
                metricFilterMatchRecord.setExtractedValues(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return metricFilterMatchRecord;
    }
}
