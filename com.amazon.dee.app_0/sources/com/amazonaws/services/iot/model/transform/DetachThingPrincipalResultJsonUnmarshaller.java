package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DetachThingPrincipalResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DetachThingPrincipalResultJsonUnmarshaller implements Unmarshaller<DetachThingPrincipalResult, JsonUnmarshallerContext> {
    private static DetachThingPrincipalResultJsonUnmarshaller instance;

    public static DetachThingPrincipalResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DetachThingPrincipalResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DetachThingPrincipalResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DetachThingPrincipalResult();
    }
}
