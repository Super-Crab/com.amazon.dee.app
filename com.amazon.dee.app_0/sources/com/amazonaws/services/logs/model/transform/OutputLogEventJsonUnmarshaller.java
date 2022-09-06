package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.OutputLogEvent;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class OutputLogEventJsonUnmarshaller implements Unmarshaller<OutputLogEvent, JsonUnmarshallerContext> {
    private static OutputLogEventJsonUnmarshaller instance;

    OutputLogEventJsonUnmarshaller() {
    }

    public static OutputLogEventJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new OutputLogEventJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public OutputLogEvent unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        OutputLogEvent outputLogEvent = new OutputLogEvent();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("timestamp")) {
                outputLogEvent.setTimestamp(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("message")) {
                outputLogEvent.setMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ingestionTime")) {
                outputLogEvent.setIngestionTime(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return outputLogEvent;
    }
}
