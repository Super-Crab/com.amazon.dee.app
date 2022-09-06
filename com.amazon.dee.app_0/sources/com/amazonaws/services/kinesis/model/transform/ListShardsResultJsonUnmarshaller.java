package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.ListShardsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListShardsResultJsonUnmarshaller implements Unmarshaller<ListShardsResult, JsonUnmarshallerContext> {
    private static ListShardsResultJsonUnmarshaller instance;

    public static ListShardsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListShardsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListShardsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListShardsResult listShardsResult = new ListShardsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Shards")) {
                listShardsResult.setShards(new ListUnmarshaller(ShardJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("NextToken")) {
                listShardsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listShardsResult;
    }
}
