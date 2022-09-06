package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StopThingRegistrationTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class StopThingRegistrationTaskResultJsonUnmarshaller implements Unmarshaller<StopThingRegistrationTaskResult, JsonUnmarshallerContext> {
    private static StopThingRegistrationTaskResultJsonUnmarshaller instance;

    public static StopThingRegistrationTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StopThingRegistrationTaskResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public StopThingRegistrationTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new StopThingRegistrationTaskResult();
    }
}
