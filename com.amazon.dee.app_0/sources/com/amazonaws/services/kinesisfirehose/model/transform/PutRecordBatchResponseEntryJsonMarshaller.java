package com.amazonaws.services.kinesisfirehose.model.transform;

import com.amazonaws.services.kinesisfirehose.model.PutRecordBatchResponseEntry;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class PutRecordBatchResponseEntryJsonMarshaller {
    private static PutRecordBatchResponseEntryJsonMarshaller instance;

    PutRecordBatchResponseEntryJsonMarshaller() {
    }

    public static PutRecordBatchResponseEntryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PutRecordBatchResponseEntryJsonMarshaller();
        }
        return instance;
    }

    public void marshall(PutRecordBatchResponseEntry putRecordBatchResponseEntry, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (putRecordBatchResponseEntry.getRecordId() != null) {
            String recordId = putRecordBatchResponseEntry.getRecordId();
            awsJsonWriter.name("RecordId");
            awsJsonWriter.value(recordId);
        }
        if (putRecordBatchResponseEntry.getErrorCode() != null) {
            String errorCode = putRecordBatchResponseEntry.getErrorCode();
            awsJsonWriter.name("ErrorCode");
            awsJsonWriter.value(errorCode);
        }
        if (putRecordBatchResponseEntry.getErrorMessage() != null) {
            String errorMessage = putRecordBatchResponseEntry.getErrorMessage();
            awsJsonWriter.name("ErrorMessage");
            awsJsonWriter.value(errorMessage);
        }
        awsJsonWriter.endObject();
    }
}
