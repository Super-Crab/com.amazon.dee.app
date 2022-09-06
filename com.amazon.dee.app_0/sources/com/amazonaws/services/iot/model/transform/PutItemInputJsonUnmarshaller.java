package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PutItemInput;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class PutItemInputJsonUnmarshaller implements Unmarshaller<PutItemInput, JsonUnmarshallerContext> {
    private static PutItemInputJsonUnmarshaller instance;

    PutItemInputJsonUnmarshaller() {
    }

    public static PutItemInputJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutItemInputJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutItemInput unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PutItemInput putItemInput = new PutItemInput();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("tableName")) {
                putItemInput.setTableName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putItemInput;
    }
}
