package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.Shard;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ShardJsonUnmarshaller implements Unmarshaller<Shard, JsonUnmarshallerContext> {
    private static ShardJsonUnmarshaller instance;

    ShardJsonUnmarshaller() {
    }

    public static ShardJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ShardJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Shard unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Shard shard = new Shard();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("ShardId")) {
                shard.setShardId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ParentShardId")) {
                shard.setParentShardId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AdjacentParentShardId")) {
                shard.setAdjacentParentShardId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("HashKeyRange")) {
                shard.setHashKeyRange(HashKeyRangeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("SequenceNumberRange")) {
                shard.setSequenceNumberRange(SequenceNumberRangeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return shard;
    }
}
