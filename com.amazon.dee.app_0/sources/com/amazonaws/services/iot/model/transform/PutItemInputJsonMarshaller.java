package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PutItemInput;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class PutItemInputJsonMarshaller {
    private static PutItemInputJsonMarshaller instance;

    PutItemInputJsonMarshaller() {
    }

    public static PutItemInputJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PutItemInputJsonMarshaller();
        }
        return instance;
    }

    public void marshall(PutItemInput putItemInput, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (putItemInput.getTableName() != null) {
            String tableName = putItemInput.getTableName();
            awsJsonWriter.name("tableName");
            awsJsonWriter.value(tableName);
        }
        awsJsonWriter.endObject();
    }
}
