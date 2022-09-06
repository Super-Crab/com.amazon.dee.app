package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DetachSecurityProfileResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DetachSecurityProfileResultJsonUnmarshaller implements Unmarshaller<DetachSecurityProfileResult, JsonUnmarshallerContext> {
    private static DetachSecurityProfileResultJsonUnmarshaller instance;

    public static DetachSecurityProfileResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DetachSecurityProfileResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DetachSecurityProfileResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DetachSecurityProfileResult();
    }
}
