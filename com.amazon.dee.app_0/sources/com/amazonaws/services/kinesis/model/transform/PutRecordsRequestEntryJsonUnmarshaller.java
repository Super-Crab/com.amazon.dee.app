package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class PutRecordsRequestEntryJsonUnmarshaller implements Unmarshaller<PutRecordsRequestEntry, JsonUnmarshallerContext> {
    private static PutRecordsRequestEntryJsonUnmarshaller instance;

    PutRecordsRequestEntryJsonUnmarshaller() {
    }

    public static PutRecordsRequestEntryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutRecordsRequestEntryJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutRecordsRequestEntry unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PutRecordsRequestEntry putRecordsRequestEntry = new PutRecordsRequestEntry();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Data")) {
                putRecordsRequestEntry.setData(SimpleTypeJsonUnmarshallers.ByteBufferJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ExplicitHashKey")) {
                putRecordsRequestEntry.setExplicitHashKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PartitionKey")) {
                putRecordsRequestEntry.setPartitionKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putRecordsRequestEntry;
    }
}
