package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class UpdateThingGroupResultJsonUnmarshaller implements Unmarshaller<UpdateThingGroupResult, JsonUnmarshallerContext> {
    private static UpdateThingGroupResultJsonUnmarshaller instance;

    public static UpdateThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateThingGroupResult updateThingGroupResult = new UpdateThingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("version")) {
                updateThingGroupResult.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateThingGroupResult;
    }
}
