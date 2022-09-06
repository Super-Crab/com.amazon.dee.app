package com.amazonaws.services.kinesisfirehose.model.transform;

import com.amazonaws.services.kinesisfirehose.model.Record;
import com.amazonaws.util.json.AwsJsonWriter;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
class RecordJsonMarshaller {
    private static RecordJsonMarshaller instance;

    RecordJsonMarshaller() {
    }

    public static RecordJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RecordJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Record record, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (record.getData() != null) {
            ByteBuffer data = record.getData();
            awsJsonWriter.name("Data");
            awsJsonWriter.value(data);
        }
        awsJsonWriter.endObject();
    }
}
