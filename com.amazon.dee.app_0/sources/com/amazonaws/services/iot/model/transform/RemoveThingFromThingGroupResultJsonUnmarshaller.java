package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RemoveThingFromThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class RemoveThingFromThingGroupResultJsonUnmarshaller implements Unmarshaller<RemoveThingFromThingGroupResult, JsonUnmarshallerContext> {
    private static RemoveThingFromThingGroupResultJsonUnmarshaller instance;

    public static RemoveThingFromThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RemoveThingFromThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RemoveThingFromThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new RemoveThingFromThingGroupResult();
    }
}
