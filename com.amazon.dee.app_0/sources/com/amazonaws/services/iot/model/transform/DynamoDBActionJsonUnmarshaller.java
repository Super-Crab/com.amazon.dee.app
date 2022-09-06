package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DynamoDBAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class DynamoDBActionJsonUnmarshaller implements Unmarshaller<DynamoDBAction, JsonUnmarshallerContext> {
    private static DynamoDBActionJsonUnmarshaller instance;

    DynamoDBActionJsonUnmarshaller() {
    }

    public static DynamoDBActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DynamoDBActionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DynamoDBAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DynamoDBAction dynamoDBAction = new DynamoDBAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("tableName")) {
                dynamoDBAction.setTableName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("roleArn")) {
                dynamoDBAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("operation")) {
                dynamoDBAction.setOperation(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("hashKeyField")) {
                dynamoDBAction.setHashKeyField(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("hashKeyValue")) {
                dynamoDBAction.setHashKeyValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("hashKeyType")) {
                dynamoDBAction.setHashKeyType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("rangeKeyField")) {
                dynamoDBAction.setRangeKeyField(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("rangeKeyValue")) {
                dynamoDBAction.setRangeKeyValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("rangeKeyType")) {
                dynamoDBAction.setRangeKeyType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("payloadField")) {
                dynamoDBAction.setPayloadField(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return dynamoDBAction;
    }
}
