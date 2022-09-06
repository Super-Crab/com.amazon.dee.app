package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.HashKeyRange;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class HashKeyRangeJsonMarshaller {
    private static HashKeyRangeJsonMarshaller instance;

    HashKeyRangeJsonMarshaller() {
    }

    public static HashKeyRangeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new HashKeyRangeJsonMarshaller();
        }
        return instance;
    }

    public void marshall(HashKeyRange hashKeyRange, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (hashKeyRange.getStartingHashKey() != null) {
            String startingHashKey = hashKeyRange.getStartingHashKey();
            awsJsonWriter.name("StartingHashKey");
            awsJsonWriter.value(startingHashKey);
        }
        if (hashKeyRange.getEndingHashKey() != null) {
            String endingHashKey = hashKeyRange.getEndingHashKey();
            awsJsonWriter.name("EndingHashKey");
            awsJsonWriter.value(endingHashKey);
        }
        awsJsonWriter.endObject();
    }
}
