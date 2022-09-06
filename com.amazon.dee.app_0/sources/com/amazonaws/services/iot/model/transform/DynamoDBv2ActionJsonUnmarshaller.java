package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DynamoDBv2Action;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class DynamoDBv2ActionJsonUnmarshaller implements Unmarshaller<DynamoDBv2Action, JsonUnmarshallerContext> {
    private static DynamoDBv2ActionJsonUnmarshaller instance;

    DynamoDBv2ActionJsonUnmarshaller() {
    }

    public static DynamoDBv2ActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DynamoDBv2ActionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DynamoDBv2Action unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DynamoDBv2Action dynamoDBv2Action = new DynamoDBv2Action();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleArn")) {
                dynamoDBv2Action.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("putItem")) {
                dynamoDBv2Action.setPutItem(PutItemInputJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return dynamoDBv2Action;
    }
}
