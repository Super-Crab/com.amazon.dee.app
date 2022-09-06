package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class UpdateThingResultJsonUnmarshaller implements Unmarshaller<UpdateThingResult, JsonUnmarshallerContext> {
    private static UpdateThingResultJsonUnmarshaller instance;

    public static UpdateThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateThingResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateThingResult();
    }
}
