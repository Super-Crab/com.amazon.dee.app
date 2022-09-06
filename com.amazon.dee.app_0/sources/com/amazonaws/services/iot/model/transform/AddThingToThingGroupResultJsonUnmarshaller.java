package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AddThingToThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class AddThingToThingGroupResultJsonUnmarshaller implements Unmarshaller<AddThingToThingGroupResult, JsonUnmarshallerContext> {
    private static AddThingToThingGroupResultJsonUnmarshaller instance;

    public static AddThingToThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AddThingToThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AddThingToThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new AddThingToThingGroupResult();
    }
}
