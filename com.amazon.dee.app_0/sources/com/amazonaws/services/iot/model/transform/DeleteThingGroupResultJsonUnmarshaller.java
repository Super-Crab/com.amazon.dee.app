package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteThingGroupResultJsonUnmarshaller implements Unmarshaller<DeleteThingGroupResult, JsonUnmarshallerContext> {
    private static DeleteThingGroupResultJsonUnmarshaller instance;

    public static DeleteThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteThingGroupResult();
    }
}
