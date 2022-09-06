package com.amazonaws.services.kinesisfirehose.model.transform;

import com.amazonaws.services.kinesisfirehose.model.PutRecordBatchResponseEntry;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class PutRecordBatchResponseEntryJsonUnmarshaller implements Unmarshaller<PutRecordBatchResponseEntry, JsonUnmarshallerContext> {
    private static PutRecordBatchResponseEntryJsonUnmarshaller instance;

    PutRecordBatchResponseEntryJsonUnmarshaller() {
    }

    public static PutRecordBatchResponseEntryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutRecordBatchResponseEntryJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutRecordBatchResponseEntry unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PutRecordBatchResponseEntry putRecordBatchResponseEntry = new PutRecordBatchResponseEntry();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("RecordId")) {
                putRecordBatchResponseEntry.setRecordId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ErrorCode")) {
                putRecordBatchResponseEntry.setErrorCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ErrorMessage")) {
                putRecordBatchResponseEntry.setErrorMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putRecordBatchResponseEntry;
    }
}
