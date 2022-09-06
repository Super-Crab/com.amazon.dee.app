package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.PutRecordsResultEntry;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class PutRecordsResultEntryJsonMarshaller {
    private static PutRecordsResultEntryJsonMarshaller instance;

    PutRecordsResultEntryJsonMarshaller() {
    }

    public static PutRecordsResultEntryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PutRecordsResultEntryJsonMarshaller();
        }
        return instance;
    }

    public void marshall(PutRecordsResultEntry putRecordsResultEntry, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (putRecordsResultEntry.getSequenceNumber() != null) {
            String sequenceNumber = putRecordsResultEntry.getSequenceNumber();
            awsJsonWriter.name("SequenceNumber");
            awsJsonWriter.value(sequenceNumber);
        }
        if (putRecordsResultEntry.getShardId() != null) {
            String shardId = putRecordsResultEntry.getShardId();
            awsJsonWriter.name("ShardId");
            awsJsonWriter.value(shardId);
        }
        if (putRecordsResultEntry.getErrorCode() != null) {
            String errorCode = putRecordsResultEntry.getErrorCode();
            awsJsonWriter.name("ErrorCode");
            awsJsonWriter.value(errorCode);
        }
        if (putRecordsResultEntry.getErrorMessage() != null) {
            String errorMessage = putRecordsResultEntry.getErrorMessage();
            awsJsonWriter.name("ErrorMessage");
            awsJsonWriter.value(errorMessage);
        }
        awsJsonWriter.endObject();
    }
}
