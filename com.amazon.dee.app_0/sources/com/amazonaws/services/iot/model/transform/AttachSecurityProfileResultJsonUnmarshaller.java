package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AttachSecurityProfileResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class AttachSecurityProfileResultJsonUnmarshaller implements Unmarshaller<AttachSecurityProfileResult, JsonUnmarshallerContext> {
    private static AttachSecurityProfileResultJsonUnmarshaller instance;

    public static AttachSecurityProfileResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AttachSecurityProfileResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AttachSecurityProfileResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new AttachSecurityProfileResult();
    }
}
