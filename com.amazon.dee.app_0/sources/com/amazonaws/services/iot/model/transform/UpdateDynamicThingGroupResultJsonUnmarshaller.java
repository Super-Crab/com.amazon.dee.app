package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateDynamicThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class UpdateDynamicThingGroupResultJsonUnmarshaller implements Unmarshaller<UpdateDynamicThingGroupResult, JsonUnmarshallerContext> {
    private static UpdateDynamicThingGroupResultJsonUnmarshaller instance;

    public static UpdateDynamicThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateDynamicThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateDynamicThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateDynamicThingGroupResult updateDynamicThingGroupResult = new UpdateDynamicThingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("version")) {
                updateDynamicThingGroupResult.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateDynamicThingGroupResult;
    }
}
