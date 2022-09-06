package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.SequenceNumberRange;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class SequenceNumberRangeJsonUnmarshaller implements Unmarshaller<SequenceNumberRange, JsonUnmarshallerContext> {
    private static SequenceNumberRangeJsonUnmarshaller instance;

    SequenceNumberRangeJsonUnmarshaller() {
    }

    public static SequenceNumberRangeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SequenceNumberRangeJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SequenceNumberRange unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SequenceNumberRange sequenceNumberRange = new SequenceNumberRange();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("StartingSequenceNumber")) {
                sequenceNumberRange.setStartingSequenceNumber(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("EndingSequenceNumber")) {
                sequenceNumberRange.setEndingSequenceNumber(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return sequenceNumberRange;
    }
}
