package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class PutRecordResultJsonUnmarshaller implements Unmarshaller<PutRecordResult, JsonUnmarshallerContext> {
    private static PutRecordResultJsonUnmarshaller instance;

    public static PutRecordResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutRecordResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutRecordResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        PutRecordResult putRecordResult = new PutRecordResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("ShardId")) {
                putRecordResult.setShardId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("SequenceNumber")) {
                putRecordResult.setSequenceNumber(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("EncryptionType")) {
                putRecordResult.setEncryptionType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putRecordResult;
    }
}
