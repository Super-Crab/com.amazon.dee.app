package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteDynamicThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteDynamicThingGroupResultJsonUnmarshaller implements Unmarshaller<DeleteDynamicThingGroupResult, JsonUnmarshallerContext> {
    private static DeleteDynamicThingGroupResultJsonUnmarshaller instance;

    public static DeleteDynamicThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteDynamicThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteDynamicThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteDynamicThingGroupResult();
    }
}
