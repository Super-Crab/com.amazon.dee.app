package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.HashKeyRange;
import com.amazonaws.services.kinesis.model.SequenceNumberRange;
import com.amazonaws.services.kinesis.model.Shard;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ShardJsonMarshaller {
    private static ShardJsonMarshaller instance;

    ShardJsonMarshaller() {
    }

    public static ShardJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ShardJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Shard shard, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (shard.getShardId() != null) {
            String shardId = shard.getShardId();
            awsJsonWriter.name("ShardId");
            awsJsonWriter.value(shardId);
        }
        if (shard.getParentShardId() != null) {
            String parentShardId = shard.getParentShardId();
            awsJsonWriter.name("ParentShardId");
            awsJsonWriter.value(parentShardId);
        }
        if (shard.getAdjacentParentShardId() != null) {
            String adjacentParentShardId = shard.getAdjacentParentShardId();
            awsJsonWriter.name("AdjacentParentShardId");
            awsJsonWriter.value(adjacentParentShardId);
        }
        if (shard.getHashKeyRange() != null) {
            HashKeyRange hashKeyRange = shard.getHashKeyRange();
            awsJsonWriter.name("HashKeyRange");
            HashKeyRangeJsonMarshaller.getInstance().marshall(hashKeyRange, awsJsonWriter);
        }
        if (shard.getSequenceNumberRange() != null) {
            SequenceNumberRange sequenceNumberRange = shard.getSequenceNumberRange();
            awsJsonWriter.name("SequenceNumberRange");
            SequenceNumberRangeJsonMarshaller.getInstance().marshall(sequenceNumberRange, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
