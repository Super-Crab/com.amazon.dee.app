package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.util.json.AwsJsonWriter;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
class PutRecordsRequestEntryJsonMarshaller {
    private static PutRecordsRequestEntryJsonMarshaller instance;

    PutRecordsRequestEntryJsonMarshaller() {
    }

    public static PutRecordsRequestEntryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PutRecordsRequestEntryJsonMarshaller();
        }
        return instance;
    }

    public void marshall(PutRecordsRequestEntry putRecordsRequestEntry, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (putRecordsRequestEntry.getData() != null) {
            ByteBuffer data = putRecordsRequestEntry.getData();
            awsJsonWriter.name("Data");
            awsJsonWriter.value(data);
        }
        if (putRecordsRequestEntry.getExplicitHashKey() != null) {
            String explicitHashKey = putRecordsRequestEntry.getExplicitHashKey();
            awsJsonWriter.name("ExplicitHashKey");
            awsJsonWriter.value(explicitHashKey);
        }
        if (putRecordsRequestEntry.getPartitionKey() != null) {
            String partitionKey = putRecordsRequestEntry.getPartitionKey();
            awsJsonWriter.name("PartitionKey");
            awsJsonWriter.value(partitionKey);
        }
        awsJsonWriter.endObject();
    }
}
