package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.UpdateShardCountResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class UpdateShardCountResultJsonUnmarshaller implements Unmarshaller<UpdateShardCountResult, JsonUnmarshallerContext> {
    private static UpdateShardCountResultJsonUnmarshaller instance;

    public static UpdateShardCountResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateShardCountResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateShardCountResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateShardCountResult updateShardCountResult = new UpdateShardCountResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("StreamName")) {
                updateShardCountResult.setStreamName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CurrentShardCount")) {
                updateShardCountResult.setCurrentShardCount(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("TargetShardCount")) {
                updateShardCountResult.setTargetShardCount(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateShardCountResult;
    }
}
