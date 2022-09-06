package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class PutRecordsResultJsonUnmarshaller implements Unmarshaller<PutRecordsResult, JsonUnmarshallerContext> {
    private static PutRecordsResultJsonUnmarshaller instance;

    public static PutRecordsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutRecordsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutRecordsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        PutRecordsResult putRecordsResult = new PutRecordsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("FailedRecordCount")) {
                putRecordsResult.setFailedRecordCount(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Records")) {
                putRecordsResult.setRecords(new ListUnmarshaller(PutRecordsResultEntryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("EncryptionType")) {
                putRecordsResult.setEncryptionType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putRecordsResult;
    }
}
