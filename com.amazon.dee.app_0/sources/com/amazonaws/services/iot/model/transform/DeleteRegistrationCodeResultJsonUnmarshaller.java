package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteRegistrationCodeResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteRegistrationCodeResultJsonUnmarshaller implements Unmarshaller<DeleteRegistrationCodeResult, JsonUnmarshallerContext> {
    private static DeleteRegistrationCodeResultJsonUnmarshaller instance;

    public static DeleteRegistrationCodeResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteRegistrationCodeResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteRegistrationCodeResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteRegistrationCodeResult();
    }
}
