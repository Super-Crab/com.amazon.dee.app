package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AttachThingPrincipalResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class AttachThingPrincipalResultJsonUnmarshaller implements Unmarshaller<AttachThingPrincipalResult, JsonUnmarshallerContext> {
    private static AttachThingPrincipalResultJsonUnmarshaller instance;

    public static AttachThingPrincipalResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AttachThingPrincipalResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AttachThingPrincipalResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new AttachThingPrincipalResult();
    }
}
