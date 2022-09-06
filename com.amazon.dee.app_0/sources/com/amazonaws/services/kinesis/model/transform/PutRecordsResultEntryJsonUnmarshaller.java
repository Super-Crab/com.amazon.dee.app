package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.PutRecordsResultEntry;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class PutRecordsResultEntryJsonUnmarshaller implements Unmarshaller<PutRecordsResultEntry, JsonUnmarshallerContext> {
    private static PutRecordsResultEntryJsonUnmarshaller instance;

    PutRecordsResultEntryJsonUnmarshaller() {
    }

    public static PutRecordsResultEntryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutRecordsResultEntryJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutRecordsResultEntry unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PutRecordsResultEntry putRecordsResultEntry = new PutRecordsResultEntry();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("SequenceNumber")) {
                putRecordsResultEntry.setSequenceNumber(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ShardId")) {
                putRecordsResultEntry.setShardId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ErrorCode")) {
                putRecordsResultEntry.setErrorCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ErrorMessage")) {
                putRecordsResultEntry.setErrorMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putRecordsResultEntry;
    }
}
