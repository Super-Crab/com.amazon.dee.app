package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteSecurityProfileResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteSecurityProfileResultJsonUnmarshaller implements Unmarshaller<DeleteSecurityProfileResult, JsonUnmarshallerContext> {
    private static DeleteSecurityProfileResultJsonUnmarshaller instance;

    public static DeleteSecurityProfileResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteSecurityProfileResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteSecurityProfileResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteSecurityProfileResult();
    }
}
