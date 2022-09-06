package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.FilteredLogEvent;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class FilteredLogEventJsonUnmarshaller implements Unmarshaller<FilteredLogEvent, JsonUnmarshallerContext> {
    private static FilteredLogEventJsonUnmarshaller instance;

    FilteredLogEventJsonUnmarshaller() {
    }

    public static FilteredLogEventJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new FilteredLogEventJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public FilteredLogEvent unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        FilteredLogEvent filteredLogEvent = new FilteredLogEvent();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("logStreamName")) {
                filteredLogEvent.setLogStreamName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("timestamp")) {
                filteredLogEvent.setTimestamp(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("message")) {
                filteredLogEvent.setMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ingestionTime")) {
                filteredLogEvent.setIngestionTime(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("eventId")) {
                filteredLogEvent.setEventId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return filteredLogEvent;
    }
}
