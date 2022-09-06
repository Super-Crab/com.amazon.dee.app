package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteThingResultJsonUnmarshaller implements Unmarshaller<DeleteThingResult, JsonUnmarshallerContext> {
    private static DeleteThingResultJsonUnmarshaller instance;

    public static DeleteThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteThingResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteThingResult();
    }
}
