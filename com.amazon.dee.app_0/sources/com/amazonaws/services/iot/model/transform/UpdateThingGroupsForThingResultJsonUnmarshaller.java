package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateThingGroupsForThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class UpdateThingGroupsForThingResultJsonUnmarshaller implements Unmarshaller<UpdateThingGroupsForThingResult, JsonUnmarshallerContext> {
    private static UpdateThingGroupsForThingResultJsonUnmarshaller instance;

    public static UpdateThingGroupsForThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateThingGroupsForThingResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateThingGroupsForThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateThingGroupsForThingResult();
    }
}
