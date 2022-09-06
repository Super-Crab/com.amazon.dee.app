package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.GetRecordsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class GetRecordsResultJsonUnmarshaller implements Unmarshaller<GetRecordsResult, JsonUnmarshallerContext> {
    private static GetRecordsResultJsonUnmarshaller instance;

    public static GetRecordsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetRecordsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetRecordsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetRecordsResult getRecordsResult = new GetRecordsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Records")) {
                getRecordsResult.setRecords(new ListUnmarshaller(RecordJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("NextShardIterator")) {
                getRecordsResult.setNextShardIterator(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("MillisBehindLatest")) {
                getRecordsResult.setMillisBehindLatest(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getRecordsResult;
    }
}
