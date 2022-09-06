package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.HashKeyRange;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class HashKeyRangeJsonUnmarshaller implements Unmarshaller<HashKeyRange, JsonUnmarshallerContext> {
    private static HashKeyRangeJsonUnmarshaller instance;

    HashKeyRangeJsonUnmarshaller() {
    }

    public static HashKeyRangeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new HashKeyRangeJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public HashKeyRange unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        HashKeyRange hashKeyRange = new HashKeyRange();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("StartingHashKey")) {
                hashKeyRange.setStartingHashKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("EndingHashKey")) {
                hashKeyRange.setEndingHashKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return hashKeyRange;
    }
}
